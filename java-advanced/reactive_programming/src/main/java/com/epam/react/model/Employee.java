package com.epam.react.model;

import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@RequiredArgsConstructor
@ToString
public class Employee {
    @Id private final int id;
    private final String firstName;
    private final String lastName;
    private final int age;

    public boolean nameContains(String name) {
        return firstName.contains(name) || lastName.contains(name);
    }
}