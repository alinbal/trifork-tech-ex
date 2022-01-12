package com.api.triforkspring.service;

import com.api.triforkspring.exception.ResourceNotFoundException;

import java.util.List;
import java.util.Map;

/**
 * Interface to implement an Employee Service.
 *
 * @param <T> DTO Type - ex. EmployeeDTO model type
 * @param <E> Entity Type - ex. Employee model type.
 */
public interface IEmployeeService<T, E> {

    /**
     * Return a list with all the employees.
     */
    List<T> getAllEmployees();

    /**
     * Get employee based on the DB id.
     */
    T getEmployeeById(Long employeeId) throws ResourceNotFoundException;

    /**
     * Add a new employee to the database.
     */
    T createEmployee(T employee);

    /**
     * Updates employees based on the given details.
     */
    T updateEmployee(T employeeDetails, Long employeeId) throws ResourceNotFoundException;

    /**
     * Deletes employee from the database given an employeeId
     * @param employeeId DB ID.
     * @return Map with some text and true or false if it has succeeded.
     * @throws ResourceNotFoundException In case we don't find the object with the specific ID
     */
    Map<String, Boolean> deleteEmployee(Long employeeId) throws ResourceNotFoundException;

    /**
     * Converts a JPA entity/POJO to a DTO object
     *
     * @param entity JPA entity.
     * @return DTO object of type T.
     */
    T convertEntityToDto(E entity);

    /**
     * Returns an POJO from a DTO object
     *
     * @param entityDTO //Fill in the text here.
     * @return // fill in the text here
     */
    E convertDtoToEntity(T entityDTO);
}
