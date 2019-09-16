package com.fares.youssef.cognitev.backend.task.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fares.youssef.cognitev.backend.task.model.RegistrationModel;
import com.fares.youssef.cognitev.backend.task.service.RegistrationService;

@RestController
@RequestMapping("/registration")
public class RegistrationController {

	private static final Logger LOG = LoggerFactory.getLogger(RegistrationController.class);
	private final RegistrationService registrationService;

	public RegistrationController(RegistrationService registrationService) {
		this.registrationService = registrationService;
	}

	@PostMapping
	public ResponseEntity registerData(@RequestPart(required = false) MultipartFile avatar,
			@RequestPart(value = "first_name", required = false) String firstName,
			@RequestPart(value = "last_name", required = false) String lastName,
			@RequestPart(value = "country_code", required = false) String countryCode,
			@RequestPart(value = "phone_number", required = false) String phoneNumber,
			@RequestPart(value = "gender", required = false) String gender,
			@RequestPart(value = "birthdate", required = false) String birthdate,
			@RequestPart(value = "email", required = false) String email) throws Exception {
		LOG.info("POST - registerData endpoint");

		RegistrationModel registrationModel = new RegistrationModel(firstName, lastName, countryCode, phoneNumber,
				gender, birthdate, avatar, email);

		RegistrationModel registeredData = registrationService.registerData(registrationModel);

		return ResponseEntity.status(HttpStatus.CREATED.value()).body(registeredData);
	}

}
