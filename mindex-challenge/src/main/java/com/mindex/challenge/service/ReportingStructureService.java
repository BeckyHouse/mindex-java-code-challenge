package com.mindex.challenge.service;

import com.mindex.challenge.data.ReportingStructure;
import org.springframework.http.ResponseEntity;

/**
 * Interface containing methods related to employee reporting structures.
 *
 * @author Becky House
 */
public interface ReportingStructureService {

    /**
     * Retrieves the number of total employees reporting to a given employee.
     *
     * @param employeeId the employee to retrieve reports for.
     * @return The total number of reports to given employee.
     */
    ResponseEntity<ReportingStructure> numberOfEmployeeReports(String employeeId);
}
