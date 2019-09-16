package com.fares.youssef.cognitev.backend.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.fares.youssef.cognitev.backend.task.model.RegistrationModel;

@Service
public class RegistrationServiceImpl implements RegistrationService {

	private static final Logger LOG = LoggerFactory.getLogger(RegistrationServiceImpl.class);

	public RegistrationServiceImpl() {

	}

	@Override
	public RegistrationModel registerData(RegistrationModel registrationModel) {
		LOG.info("Registering the submitted data");
		LOG.debug("The submitted registeration data: {}", registrationModel);

		return registrationModel;
	}

	private void validateRegistrationModel(RegistrationModel registrationModel) {
		LOG.info("Validating the submitted registration model");

		// TODO:Add the needed validation
	}

}
