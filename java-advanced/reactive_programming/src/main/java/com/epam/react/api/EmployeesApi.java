package com.epam.react.api;

import com.epam.react.model.Employee;
import com.epam.react.repository.EmployeesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequiredArgsConstructor
public class EmployeesApi {
    private final EmployeesRepository repository;

    @GetMapping("/employees")
    public Flux<Employee> employees(@RequestParam String name) {
        return repository.findAllByNameLike(name);
    }

}
