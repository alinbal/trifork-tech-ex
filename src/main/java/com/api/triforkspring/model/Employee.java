package com.api.triforkspring.model;

import lombok.*;

import javax.persistence.*;


@Data
@AllArgsConstructor
@Entity
@Table(name = "employees")
@NoArgsConstructor
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "email")
    private String email;
}
