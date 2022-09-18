package com.coronareport.coronareportapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories
@SpringBootApplication
public class CoronareportApplication {

	public static void main(String[] args) {
		SpringApplication.run(CoronareportApplication.class, args);
	}

}
