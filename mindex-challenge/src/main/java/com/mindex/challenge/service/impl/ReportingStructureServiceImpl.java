package com.mindex.challenge.service.impl;

import com.mindex.challenge.data.Employee;
import com.mindex.challenge.data.ReportingStructure;
import com.mindex.challenge.service.EmployeeService;
import com.mindex.challenge.service.ReportingStructureService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Implements the service(s) within the ReportingStructureService to obtain information
 * about employee reporting.
 *
 * @author Becky House
 */
@Service
public class ReportingStructureServiceImpl implements ReportingStructureService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ReportingStructureServiceImpl.class);

    @Autowired
    EmployeeService employeeService;

    @Override
    public ResponseEntity<ReportingStructure> numberOfEmployeeReports(String employeeId) {

        if (employeeId != null) {
            Employee employee = this.employeeService.read(employeeId);
            List<Employee> reportsList = new ArrayList<>();
            this.directReports(employee, reportsList);

            ReportingStructure reportingStructure = new ReportingStructure(employee, reportsList.size());
            if (reportingStructure != null) {
                return new ResponseEntity<>(reportingStructure, HttpStatus.OK);
            }

            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    /**
     * Loops through the list of direct reports and adds them to a list of total reports.
     *
     * @param employee the employee to find direct reports for
     * @param reportsList the list of direct reports
     */
    public void directReports(Employee employee, List<Employee> reportsList) {

        for (Employee directReport : employee.getDirectReports()) {
            Employee indirectReport = this.employeeService.read(directReport.getEmployeeId());
            reportsList.add(indirectReport);

            if (indirectReport.getDirectReports() != null && indirectReport.getDirectReports().size() > 0) {
                this.directReports(indirectReport, reportsList);
            }
        }
    }
}
