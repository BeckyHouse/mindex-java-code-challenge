package com.mindex.challenge.service.impl;

import com.mindex.challenge.dao.CompensationRepository;
import com.mindex.challenge.dao.EmployeeRepository;
import com.mindex.challenge.data.Compensation;
import com.mindex.challenge.data.CompensationRequest;
import com.mindex.challenge.data.Employee;
import com.mindex.challenge.service.EmployeeService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CompensationServiceImplTest {

    private static final String COMPENSATION_URL = "http://localhost:{port}/compensation";

    private static final String COMPENSATION_ID_URL = "http://localhost:{port}/compensation/{employeeId}";

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private CompensationRepository compensationRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void testCreateEmployeeCompensation() {
        Employee testEmployee = new Employee();
        testEmployee.setFirstName("John");
        testEmployee.setLastName("Lennon");
        testEmployee.setDepartment("Engineering");
        testEmployee.setPosition("Development Manager");
        testEmployee.setEmployeeId("16a596ae-edd3-4847-99fe-c4518e82c86f");

        CompensationRequest testCompensation = new CompensationRequest();
        testCompensation.setEmployee(testEmployee);
        testCompensation.setSalary("115000");
        testCompensation.setEffectiveDate("2024-02-25");

        // create compensation
        Compensation compensation
            = this.restTemplate.postForEntity(COMPENSATION_URL,
                testCompensation,
                Compensation.class,
                this.port).getBody();

        assertNotNull(compensation);
        assertCompensationEquivalence(testCompensation, compensation);

        // retrieve compensation
        Compensation retrievedCompensation
                = this.restTemplate.getForEntity(
                COMPENSATION_ID_URL,
                Compensation.class,
                this.port,
                testEmployee.getEmployeeId()
        ).getBody();

        assertNotNull(retrievedCompensation);
        assertCompensationEquivalence(testCompensation, retrievedCompensation);
    }

    private static void assertCompensationEquivalence(CompensationRequest compensationRequest, Compensation compensation) {
        assertEquals(compensationRequest.getSalary(), compensation.getSalary());
        assertEquals(compensationRequest.getEmployee().getEmployeeId(), compensation.getEmployee().getEmployeeId());
    }
}
