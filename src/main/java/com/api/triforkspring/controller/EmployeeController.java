package com.api.triforkspring.controller;

import com.api.triforkspring.dto.EmployeeDTO;
import com.api.triforkspring.exception.ResourceNotFoundException;
import com.api.triforkspring.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("employees")
    public List<EmployeeDTO> getAllEmployees() {
        return this.employeeService.getAllEmployees();
    }

    @GetMapping("employees/{id}")
    public ResponseEntity<EmployeeDTO> getEmployeeById(@PathVariable(value = "id") Long employeeId)
            throws ResourceNotFoundException {
        return ResponseEntity.ok().body(employeeService.getEmployeeById(employeeId));
    }

    @PostMapping("employees")
    public EmployeeDTO createEmployee(@RequestBody EmployeeDTO employee) {
        return this.employeeService.createEmployee(employee);
    }

    @PutMapping("employees/{id}")
    public ResponseEntity<EmployeeDTO> updateEmployee(
            @PathVariable(value = "id") Long employeeId,
            @Validated @RequestBody EmployeeDTO employeeDetails)
            throws ResourceNotFoundException {

        return ResponseEntity.ok(this.employeeService.updateEmployee(employeeDetails, employeeId));
    }

    @DeleteMapping("employees/{id}")
    public Map<String, Boolean> deleteEmployee(@PathVariable(value = "id") Long employeeId) throws ResourceNotFoundException {
        return this.employeeService.deleteEmployee(employeeId);
    }
}
