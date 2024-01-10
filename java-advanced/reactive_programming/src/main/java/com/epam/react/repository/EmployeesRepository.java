package com.epam.react.repository;

import com.epam.react.model.Employee;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface EmployeesRepository extends ReactiveMongoRepository<Employee, String> {

    default Flux<Employee> findAllByNameLike(String name) {
        if (name != null) {
            return this.findAll().filter(employee -> employee.nameContains(name));
        }
        return Flux.empty();
    }
}
