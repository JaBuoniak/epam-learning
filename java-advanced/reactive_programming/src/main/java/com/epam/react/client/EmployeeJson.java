package com.epam.react.client;

import lombok.Data;

@Data
public class EmployeeJson {
        private int id;
        private String imageUrl;
        private String firstName;
        private String lastName;
        private String email;
        private String contactNumber;
        private int age;
        private String dob;
        private double salary;
        private String address;
}
