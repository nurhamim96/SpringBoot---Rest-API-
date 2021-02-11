package com.test.msemployeeservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;


@SpringBootApplication
@EnableJpaAuditing
public class MsEmployeeServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsEmployeeServiceApplication.class, args);
	}

}
