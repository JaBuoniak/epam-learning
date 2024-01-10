package com.epam.react.api;

import com.epam.react.model.Employee;
import com.epam.react.repository.EmployeesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

import javax.management.InstanceAlreadyExistsException;

@RestController
@RequiredArgsConstructor
public class EmployeesApi {
    private final EmployeesRepository repository;

    @GetMapping("/employees")
    public Flux<Employee> employees(@RequestParam String name) {
        return repository.findAllByNameLike(name);
    }

    @PostMapping("/addemployee")
    @ResponseStatus(HttpStatus.CREATED)
    public void addemployee(@RequestParam String name, @RequestParam String surname, @RequestParam int age) {
        repository.save(new Employee(name, surname, age));
    }
}
