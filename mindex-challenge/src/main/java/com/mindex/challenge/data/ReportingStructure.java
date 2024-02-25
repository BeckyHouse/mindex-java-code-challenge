package com.mindex.challenge.data;

import lombok.Builder;
import lombok.Data;

/**
 * Response object containing information for Employees and their number of reports.
 *
 * @author Becky House
 */
@Builder
@Data
public class ReportingStructure {

    private Employee employee;

    private int numberOfReports;

    /**
     * Constructor.
     *
     * @param employee the employee
     * @param numberOfReports the number of reports
     */
    public ReportingStructure(Employee employee, int numberOfReports) {
        this.numberOfReports = numberOfReports;
        this.employee = employee;
    }
}
