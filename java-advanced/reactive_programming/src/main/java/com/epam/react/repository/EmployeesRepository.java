package com.epam.react.repository;

import com.epam.react.model.Employee;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeesRepository extends ReactiveMongoRepository<Employee, String> {

}
