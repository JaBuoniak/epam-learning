package com.epam.react.client;

import com.epam.react.model.Employee;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

@Slf4j
@Component
public class EmployeeClient {

    private final WebClient webClient;

    public EmployeeClient(WebClient.Builder builder) {
        webClient = builder.baseUrl("https://hub.dummyapis.com").build();
    }

    public Flux<Employee> getEmployees(int numberOfRecords, int startFrom) {
        return webClient.get()
                .uri(uri -> uri.path("/employee")
                                .queryParam("noofRecords", numberOfRecords)
                                .queryParam("idStarts", startFrom).build())
                .exchangeToFlux(response -> response.bodyToFlux(EmployeeJson.class))
                .map(e -> new Employee(e.getFirstName(), e.getLastName(), e.getAge()))
                .onErrorResume(e -> {
                    log.error("Failed to pull employees for input: (" + numberOfRecords + "," + startFrom, e);
                    return Flux.empty();
                });
    }
}
