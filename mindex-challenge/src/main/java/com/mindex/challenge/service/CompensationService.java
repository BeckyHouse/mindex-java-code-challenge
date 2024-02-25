package com.mindex.challenge.service;

import com.mindex.challenge.data.CompensationRequest;
import com.mindex.challenge.data.Compensation;
import org.springframework.http.ResponseEntity;

/**
 * Interface containing methods for creating and retrieving employee compensation data.
 *
 * @author Becky House
 */
public interface CompensationService {

    /**
     * Creates compensation data for a given employee.
     *
     * @param compensationRequest the compensation for an employee.
     * @return the compensation for the given employee.
     */
    ResponseEntity<Compensation> createEmployeeCompensation(CompensationRequest compensationRequest);

    /**
     * Retrieves employee compensation data.
     *
     * @param employeeId the employee to retrieve for.
     * @return the compensation data for the given employee.
     */
    ResponseEntity<Compensation> retrieveEmployeeCompensation(String employeeId);
}
