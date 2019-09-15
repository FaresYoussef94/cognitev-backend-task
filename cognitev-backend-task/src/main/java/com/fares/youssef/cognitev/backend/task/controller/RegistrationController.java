package com.fares.youssef.cognitev.backend.task.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fares.youssef.cognitev.backend.task.model.RegistrationModel;
import com.fares.youssef.cognitev.backend.task.model.ResponseEntity;

@RestController
@RequestMapping("/registration")
public class RegistrationController {

	private static final Logger LOG = LoggerFactory.getLogger(RegistrationController.class);

	public RegistrationController() {

	}

	@PostMapping()
	public ResponseEntity registerData(@RequestBody RegistrationModel registrationModel) {
		return null;
	}

}
