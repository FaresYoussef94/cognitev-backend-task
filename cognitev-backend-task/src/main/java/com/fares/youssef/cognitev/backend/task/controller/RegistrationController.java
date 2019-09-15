package com.fares.youssef.cognitev.backend.task.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fares.youssef.cognitev.backend.service.RegistrationService;
import com.fares.youssef.cognitev.backend.task.model.RegistrationModel;

@RestController
@RequestMapping("/registration")
public class RegistrationController {

	private static final Logger LOG = LoggerFactory.getLogger(RegistrationController.class);
	private final RegistrationService registrationService;

	public RegistrationController(RegistrationService registrationService) {
		this.registrationService = registrationService;
	}

	@PostMapping
	public ResponseEntity registerData(@RequestBody(required = false) RegistrationModel registrationModel) {
		LOG.info("POST - registerData endpoint");

		RegistrationModel registeredData = registrationService.registerData(registrationModel);

		return ResponseEntity.status(HttpStatus.CREATED.value()).body(registeredData);
	}

}
