package com.epam.react;

import com.epam.react.client.EmployeeClient;
import com.epam.react.repository.EmployeesRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class ReactApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(ReactApplication.class, args);
		EmployeeClient client = context.getBean(EmployeeClient.class);
		EmployeesRepository repository = context.getBean(EmployeesRepository.class);

		client.getEmployees(10, 1)
				.doOnNext(repository::insert);

		System.out.println(">> results:");
		repository.findAll().doOnNext(System.out::println).blockLast();
	}
}
