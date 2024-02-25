package com.mindex.challenge.service.impl;

import com.mindex.challenge.data.Employee;
import com.mindex.challenge.data.ReportingStructure;
import com.mindex.challenge.service.ReportingStructureService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ReportingStructureServiceImplTest {

    private static final String REPORTING_STRUCTURE_URL = "http://localhost:{port}/reporting/{id}";

    @Autowired
    private ReportingStructureService reportingStructureService;

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void testRetrieveReports() {
        Employee testEmployee = new Employee();
        testEmployee.setFirstName("John");
        testEmployee.setLastName("Lennon");
        testEmployee.setDepartment("Engineering");
        testEmployee.setPosition("Development Manager");
        testEmployee.setEmployeeId("16a596ae-edd3-4847-99fe-c4518e82c86f");

        ReportingStructure testReportingStructure = new ReportingStructure(testEmployee, 4);

        ReportingStructure retrievedReportingStructure
            = this.restTemplate.getForEntity(REPORTING_STRUCTURE_URL,
                ReportingStructure.class,
                this.port,
                testReportingStructure.getEmployee().getEmployeeId()).getBody();

        assertEquals(retrievedReportingStructure.getNumberOfReports(), testReportingStructure.getNumberOfReports());
    }
}
