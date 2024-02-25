package com.mindex.challenge.controller;

import com.mindex.challenge.data.ReportingStructure;
import com.mindex.challenge.service.ReportingStructureService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST controller containing services for retrieving reporting structure data.
 *
 * @author Becky House
 */
@RestController
public class ReportingStructureController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ReportingStructureController.class);

    @Autowired
    private ReportingStructureService reportingStructureService;

    /**
     * Endpoint for retrieving Employee data and their number of reports.
     *
     * @param employeeId the employee to retrieve
     * @return the employee and their number of reports
     */
    @GetMapping("/reporting/{employeeId}")
    public ResponseEntity<ReportingStructure> retrieveReports(@PathVariable String employeeId) {
        LOGGER.debug("Received retrieve reporting request for id [{}]", employeeId);

        return this.reportingStructureService.numberOfEmployeeReports(employeeId);
    }
}
