package com.epam.react;

import com.epam.react.client.EmployeeClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class ReactApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(ReactApplication.class, args);
		EmployeeClient employeeClient = context.getBean(EmployeeClient.class);

		System.out.println(">> results:");
		employeeClient.getEmployees(10, 1).doOnNext(System.out::println).blockLast();
	}
}
