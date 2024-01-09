package com.epam.react.client;

import com.epam.react.model.Employee;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

@Component
public class EmployeeClient {

    private final WebClient webClient;

    public EmployeeClient(WebClient.Builder builder) {
        webClient = builder.baseUrl("https://hub.dummyapis.com").build();
    }

    public Flux<Employee> getEmployees(int numberOfRecords, int startFrom) {
        return webClient.get()
                .uri(uri -> uri.path("/employee").queryParam("noofRecords", 10).queryParam("idStarts", 1).build())
                .exchangeToFlux(response -> response.bodyToFlux(EmployeeJson.class))
                .map(e -> new Employee(e.getId(), e.getFirstName(), e.getLastName(), e.getAge()))
                .onErrorResume(e -> Flux.empty());
    }
}
