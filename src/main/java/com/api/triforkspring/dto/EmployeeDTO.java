package com.api.triforkspring.dto;

import lombok.Data;

@Data
public class EmployeeDTO {
    private long id;
    private String firstName;
    private String lastName;
    private String email;
}
