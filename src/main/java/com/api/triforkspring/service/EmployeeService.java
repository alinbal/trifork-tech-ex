package com.api.triforkspring.service;

import com.api.triforkspring.dto.EmployeeDTO;
import com.api.triforkspring.exception.ResourceNotFoundException;
import com.api.triforkspring.model.Employee;
import com.api.triforkspring.repository.EmployeeRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class EmployeeService implements IEmployeeService<EmployeeDTO, Employee> {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<EmployeeDTO> getAllEmployees() {
        return this.employeeRepository.findAll()
                .stream()
                .map(this::convertEntityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public EmployeeDTO getEmployeeById(Long employeeId) throws ResourceNotFoundException {
        Employee employee = getEmployee(employeeId);
        return modelMapper.map(employee, EmployeeDTO.class);
    }

    @Override
    public EmployeeDTO createEmployee(EmployeeDTO employee) {
        return convertEntityToDto(this.employeeRepository.save(convertDtoToEntity(employee)));
    }

    @Override
    public EmployeeDTO updateEmployee(EmployeeDTO employeeDetails, Long employeeId) throws ResourceNotFoundException {
        Employee employee = getEmployee(employeeId);
        modelMapper.map(employeeDetails, employee);
        return convertEntityToDto(this.employeeRepository.save(employee));
    }

    @Override
    public Map<String, Boolean> deleteEmployee(Long employeeId) throws ResourceNotFoundException {
        Employee employee = getEmployee(employeeId);
        this.employeeRepository.delete(employee);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);

        return response;
    }

    private Employee getEmployee(Long employeeId) throws ResourceNotFoundException {
        return employeeRepository.
                findById(employeeId).
                orElseThrow(() -> new ResourceNotFoundException("Employee not found with id " + employeeId));
    }

    @Override
    public EmployeeDTO convertEntityToDto(Employee employee) {
        modelMapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.LOOSE);
        EmployeeDTO employeeDTO;
        employeeDTO = modelMapper.map(employee, EmployeeDTO.class);
        return employeeDTO;
    }

    @Override
    public Employee convertDtoToEntity(EmployeeDTO employeeDTO) {
        modelMapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.LOOSE);
        Employee employee;
        employee = modelMapper.map(employeeDTO, Employee.class);
        return employee;
    }
}
