package com.epam.react.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Employee {
    @Id private int id;
    private String firstName;
    private String lastName;
    private int age;
}