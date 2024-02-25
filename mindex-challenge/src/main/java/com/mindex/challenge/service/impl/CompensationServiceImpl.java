package com.mindex.challenge.service.impl;

import com.mindex.challenge.data.CompensationRequest;
import com.mindex.challenge.dao.CompensationRepository;
import com.mindex.challenge.data.Compensation;
import com.mindex.challenge.data.Employee;
import com.mindex.challenge.service.CompensationService;
import com.mindex.challenge.service.EmployeeService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Implements the services within CompensationService to create and retrieve compensation data
 * for given employees.
 *
 * @author Becky House
 */
@Service
public class CompensationServiceImpl implements CompensationService {

    private static final Logger LOGGGER = LoggerFactory.getLogger(CompensationServiceImpl.class);

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private CompensationRepository compensationRepository;

    @Override
    public ResponseEntity<Compensation> createEmployeeCompensation(CompensationRequest compensationRequest) {

        if (!StringUtils.isBlank(compensationRequest.getEmployee().getEmployeeId())) {
            Employee employee = this.employeeService.read(compensationRequest.getEmployee().getEmployeeId());
            LocalDate effectiveDate = LocalDate.parse(compensationRequest.getEffectiveDate(), DateTimeFormatter.ISO_DATE);

            Compensation compensation = Compensation
                    .builder()
                    .salary(compensationRequest.getSalary())
                    .effectiveDate(effectiveDate)
                    .employee(employee)
                    .build();

            this.compensationRepository.insert(compensation);
            return new ResponseEntity<>(compensation, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @Override
    public ResponseEntity<Compensation> retrieveEmployeeCompensation(String employeeId) {

        if (!StringUtils.isBlank(employeeId)) {
            Employee employee = this.employeeService.read(employeeId);
            Compensation compensation = this.compensationRepository.findByEmployee(employee);
            if (compensation != null) {
                return new ResponseEntity<>(compensation, HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
