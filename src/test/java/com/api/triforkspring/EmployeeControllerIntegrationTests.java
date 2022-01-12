package com.api.triforkspring;

import com.api.triforkspring.model.Employee;

import org.junit.jupiter.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.junit.jupiter.api.Test;

//TODO: These tests should be improved. This is just a quick pass.
@SpringBootTest(classes = TriforkSpringApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class EmployeeControllerIntegrationTests {

    @Autowired
    private TestRestTemplate restTemplate;

    @LocalServerPort
    private int port;

    private String getRootUrl() {
        return "http://localhost:" + port;
    }

    @Test
    public void contextLoads() {

    }

    @Test
    public void testGetAllEmployees() {
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> entity = new HttpEntity<>(null, headers);
        ResponseEntity<String> response = restTemplate.exchange(getRootUrl() + "/api/v1/employees",
                HttpMethod.GET, entity, String.class);
        Assertions.assertNotNull(response.getBody());
    }

    @Test
    public void testGetEmployeeById() {
        Employee employee = restTemplate.getForObject(getRootUrl() + "/api/v1/employees/1", Employee.class);
        System.out.println(employee.getFirstName());
        Assertions.assertNotNull(employee);
    }

    @Test
    public void testCreateEmployee() {
        Employee employee = new Employee();
        employee.setEmail("admin@gmail.com");
        employee.setFirstName("user1FirstName");
        employee.setLastName("user1LastName");

        ResponseEntity<Employee> postResponse = restTemplate.postForEntity(getRootUrl() + "/api/v1/employees", employee, Employee.class);
        Assertions.assertNotNull(postResponse);
        Assertions.assertNotNull(postResponse.getBody());
    }

    @Test
    public void testUpdateEmployee() {
        int id = 1;
        Employee employee = restTemplate.getForObject(getRootUrl() + "/api/v1/employees/" + id, Employee.class);
        employee.setFirstName("admin1");
        employee.setLastName("admin2");
        restTemplate.put(getRootUrl() + "/api/v1/employees/" + id, employee);
        Employee updatedEmployee = restTemplate.getForObject(getRootUrl() + "/api/v1/employees/" + id, Employee.class);
        Assertions.assertNotNull(updatedEmployee);
    }

    @Test
    public void testDeleteEmployee() {
        int id = 2;
        Employee employee = restTemplate.getForObject(getRootUrl() + "/api/v1/employees/" + id, Employee.class);
        Assertions.assertNotNull(employee);
        restTemplate.delete(getRootUrl() + "/api/v1/employees/" + id);

        try {
            employee = restTemplate.getForObject(getRootUrl() + "/api/v1/employees/" + id, Employee.class);
        } catch (final HttpClientErrorException e) {
            Assertions.assertEquals(e.getStatusCode(), HttpStatus.NOT_FOUND);
        }
    }
}
