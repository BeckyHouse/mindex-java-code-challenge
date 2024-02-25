package com.mindex.challenge.controller;

import com.mindex.challenge.data.Compensation;
import com.mindex.challenge.data.CompensationRequest;
import com.mindex.challenge.service.CompensationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


/**
 * REST controller containing services for creating and retrieving compensation data.
 *
 * @author Becky House
 */
@RestController
public class CompensationController {

    private static final Logger LOGGER = LoggerFactory.getLogger(CompensationController.class);

    @Autowired
    private CompensationService compensationService;

    /**
     * Endpoint for creating compensation data for an employee.
     *
     * @param compensationRequest the compensation data.
     * @return the compensation data for an employee.
     */
    @PostMapping("/compensation")
    public ResponseEntity<Compensation> createEmployeeCompensation(@RequestBody CompensationRequest compensationRequest) {
        LOGGER.debug("Received create compensation request for [{}]", compensationRequest.getEmployee());

        return this.compensationService.createEmployeeCompensation(compensationRequest);
    }

    /**
     * Endpoint for retrieving employee compensation data.
     *
     * @param employeeId the employee to retrieve data for.
     * @return the compensation data for the given employee.
     */
    @GetMapping("/compensation/{employeeId}")
    public ResponseEntity<Compensation> retrieveEmployeeCompensation(@PathVariable String employeeId) {
        LOGGER.debug("Received retrieve compensation request for id [{}]", employeeId);

        return this.compensationService.retrieveEmployeeCompensation(employeeId);
    }
}
