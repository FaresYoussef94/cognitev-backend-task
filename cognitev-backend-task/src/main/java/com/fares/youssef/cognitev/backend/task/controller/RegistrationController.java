package com.fares.youssef.cognitev.backend.task.controller;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FilenameUtils;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
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

	@PostMapping("/register-data")
	public ResponseEntity registerData(@RequestPart(required = false) MultipartFile avatar,
			@RequestPart(value = "first_name", required = false) String firstName,
			@RequestPart(value = "last_name", required = false) String lastName,
			@RequestPart(value = "country_code", required = false) String countryCode,
			@RequestPart(value = "phone_number", required = false) String phoneNumber,
			@RequestPart(value = "gender", required = false) String gender,
			@RequestPart(value = "birthdate", required = false) String birthdate,
			@RequestPart(value = "email", required = false) String email) throws IOException {
		LOG.info("POST - registerData endpoint");

		File avatarFile = null;

		if (avatar != null && avatar.getOriginalFilename() != null && !avatar.getOriginalFilename().trim().equals("")
				&& avatar.getOriginalFilename().contains("."))
			avatarFile = File.createTempFile(avatar.getOriginalFilename().split("\\.")[0],
					"." + FilenameUtils.getExtension(avatar.getOriginalFilename()));

		RegistrationModel registrationModel = new RegistrationModel(firstName, lastName, countryCode, phoneNumber,
				gender, birthdate, avatarFile, email);

		RegistrationModel registeredData = registrationService.registerData(registrationModel);

		return ResponseEntity.status(HttpStatus.CREATED.value()).body(registeredData);
	}
	
	@PostMapping("/update-status")
	public ResponseEntity updateStatus(HttpEntity<String> httpEntity) throws JSONException {
		JSONObject json = new JSONObject(httpEntity.getBody());
		String phoneNumber = json.getString("phone_number");
		Object status = json.get("status");
		
		RegistrationModel registrationModel = registrationService.statusUpdate(phoneNumber, status);
		return ResponseEntity.status(HttpStatus.OK.value()).body(registrationModel);
	}

}
