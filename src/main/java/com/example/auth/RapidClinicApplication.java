package com.example.auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.example"})
public class RapidClinicApplication {
	public static void main(String[] args) {
		SpringApplication.run(RapidClinicApplication.class, args);
	}

}
