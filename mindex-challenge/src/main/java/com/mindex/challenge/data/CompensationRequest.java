package com.mindex.challenge.data;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

/**
 * Request object for compensation data.
 *
 * @author Becky House
 */
@Builder
@Data
@ToString
public class CompensationRequest {

    private Employee employee;

    private String salary;

    private String effectiveDate;

    /**
     * Default constructor.
     */
    public CompensationRequest() {

    }

    /**
     * Constructor.
     *
     * @param employee the employee
     * @param salary the salary
     * @param effectiveDate the effective date
     */
    public CompensationRequest(Employee employee, String salary, String effectiveDate) {
        this.employee = employee;
        this.salary = salary;
        this.effectiveDate = effectiveDate;
    }
}
