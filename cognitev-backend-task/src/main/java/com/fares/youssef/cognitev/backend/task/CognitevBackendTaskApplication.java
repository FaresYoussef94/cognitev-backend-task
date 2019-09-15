package com.fares.youssef.cognitev.backend.task;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.fares.youssef.cognitev.backend.service.RegistrationService;
import com.fares.youssef.cognitev.backend.service.RegistrationServiceImpl;

@SpringBootApplication
public class CognitevBackendTaskApplication {

	public static void main(String[] args) {
		SpringApplication.run(CognitevBackendTaskApplication.class, args);
	}

	@Bean
	public RegistrationService getRegistrationService() {
		return new RegistrationServiceImpl();
	}

}
