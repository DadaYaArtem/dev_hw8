package com.goit.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@PropertySource("classpath:application.properties")
public class Hw8Application {

	public static void main(String[] args) {
		SpringApplication.run(Hw8Application.class, args);
	}

}
