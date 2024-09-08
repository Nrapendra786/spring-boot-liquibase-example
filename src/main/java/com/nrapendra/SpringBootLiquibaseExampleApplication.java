package com.nrapendra;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.nrapendra.students")
public class SpringBootLiquibaseExampleApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootLiquibaseExampleApplication.class, args);
	}

}
