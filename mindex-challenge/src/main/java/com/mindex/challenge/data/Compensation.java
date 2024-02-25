package com.mindex.challenge.data;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.time.LocalDate;

/**
 * Response object containing information for employees compensation data.
 *
 * @author Becky House
 */
@Builder
@Data
@ToString
public class Compensation {

    private Employee employee;

    private String salary;

    private LocalDate effectiveDate;

    /**
     * Default constructor.
     */
    public Compensation() {

    }

    /**
     * Constructor.
     *
     * @param employee the employee
     * @param salary the salary
     * @param effectiveDate the effective date
     */
    public Compensation(Employee employee, String salary, LocalDate effectiveDate) {
        this.employee = employee;
        this.salary = salary;
        this.effectiveDate = effectiveDate;
    }
}
