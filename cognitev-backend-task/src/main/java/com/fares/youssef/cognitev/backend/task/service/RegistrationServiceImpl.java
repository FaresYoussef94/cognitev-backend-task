package com.fares.youssef.cognitev.backend.task.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.fares.youssef.cognitev.backend.task.exception.CustomException;
import com.fares.youssef.cognitev.backend.task.model.RegistrationModel;
import com.fares.youssef.cognitev.backend.task.repository.RegistrationModelRepository;
import com.fares.youssef.cognitev.backend.task.utils.StringValidatorUtils;

@Service
public class RegistrationServiceImpl implements RegistrationService {

	private static final Logger LOG = LoggerFactory.getLogger(RegistrationServiceImpl.class);
	private static final String PHONE_NUMBER_REGEX = "^\\+?[1-9]\\d{1,14}$";
	private static final String EMAI_REGEX = "^[a-zA-Z0-9_!#$%&’*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";
	private final Pattern phoneNumberPattern;
	private final Pattern emailPattern;
	private final RegistrationModelRepository registrationModelRepository;
	private SimpleDateFormat simpleDateFormat;

	public RegistrationServiceImpl(RegistrationModelRepository registrationModelRepository) {
		this.registrationModelRepository = registrationModelRepository;
		phoneNumberPattern = Pattern.compile(PHONE_NUMBER_REGEX);
		emailPattern = Pattern.compile(EMAI_REGEX, Pattern.CASE_INSENSITIVE);
		simpleDateFormat = new SimpleDateFormat("yyyy-MM-DD");
	}

	@Override
	public RegistrationModel registerData(RegistrationModel registrationModel) {
		LOG.info("Registering the submitted data");
		LOG.debug("The submitted registeration data: {}", registrationModel);

		validateRegistrationModel(registrationModel);
		return registrationModelRepository.save(registrationModel);
	}

	private void validateRegistrationModel(RegistrationModel registrationModel) {
		LOG.info("Validating the submitted registration model");

		Map<String, List<String>> validationsErrorMap = new HashMap<>();
		List<String> errorsList = new ArrayList<String>();

		if (StringValidatorUtils.isBlankOrNull(registrationModel.getFirstName())) {
			errorsList.add("blank");
			validationsErrorMap.put("first_name", errorsList);
		}

		if (StringValidatorUtils.isBlankOrNull(registrationModel.getLastName())) {
			errorsList = new ArrayList<>();
			errorsList.add("blank");
			validationsErrorMap.put("last_name", errorsList);
		}

		if (StringValidatorUtils.isBlankOrNull(registrationModel.getCountryCode())) {
			errorsList = new ArrayList<>();
			errorsList.add("blank");
			validationsErrorMap.put("country_code", errorsList);
		}

		if (StringValidatorUtils.isBlankOrNull(registrationModel.getPhoneNumber())) {
			errorsList = new ArrayList<>();
			errorsList.add("blank");
			validationsErrorMap.put("phone_number", errorsList);
		} else if (registrationModel.getPhoneNumber().length() <= 10) {
			errorsList = new ArrayList<>();
			errorsList.add("too_short");
			validationsErrorMap.put("phone_number", errorsList);
		} else if (registrationModel.getPhoneNumber().length() >= 15) {
			errorsList = new ArrayList<>();
			errorsList.add("too_long");
			validationsErrorMap.put("phone_number", errorsList);
		} else if (!phoneNumberPattern.matcher(registrationModel.getPhoneNumber()).matches()) {
			errorsList = new ArrayList<>();
			errorsList.add("invalid");
			validationsErrorMap.put("phone_number", errorsList);
		} else if (registrationModelRepository.existsByPhoneNumber(registrationModel.getPhoneNumber())) {
			errorsList = new ArrayList<>();
			errorsList.add("taken");
			validationsErrorMap.put("phone_number", errorsList);
		}

		if (StringValidatorUtils.isBlankOrNull(registrationModel.getGender())) {
			errorsList = new ArrayList<>();
			errorsList.add("blank");
			validationsErrorMap.put("gender", errorsList);
		} else if (!registrationModel.getGender().trim().equalsIgnoreCase("male")
				&& !registrationModel.getGender().trim().equalsIgnoreCase("female")) {
			errorsList = new ArrayList<>();
			errorsList.add("inclusion");
			validationsErrorMap.put("gender", errorsList);
		}

		if (StringValidatorUtils.isBlankOrNull(registrationModel.getBirthDate())) {
			errorsList = new ArrayList<>();
			errorsList.add("blank");
			validationsErrorMap.put("birthdate", errorsList);
		} else {
			Date date = null;
			try {
				date = simpleDateFormat.parse(registrationModel.getBirthDate());
			} catch (ParseException e) {
				errorsList = new ArrayList<>();
				errorsList.add("invalid");
				validationsErrorMap.put("birthdate", errorsList);
			}
			if (date != null && date.compareTo(new Date()) > 0) {
				errorsList = new ArrayList<>();
				errorsList.add("in_the_future");
				validationsErrorMap.put("birthdate", errorsList);
			}
		}

		if (!StringValidatorUtils.isBlankOrNull(registrationModel.getEmail())
				&& !emailPattern.matcher(registrationModel.getEmail()).matches()) {
			errorsList = new ArrayList<>();
			errorsList.add("invalid");
			validationsErrorMap.put("email", errorsList);
		} else if (registrationModelRepository.existsByEmail(registrationModel.getEmail())) {
			errorsList = new ArrayList<>();
			errorsList.add("taken");
			validationsErrorMap.put("email", errorsList);
		}

		if (registrationModel.getAvatar() == null) {
			errorsList = new ArrayList<>();
			errorsList.add("blank");
			validationsErrorMap.put("avatar", errorsList);
		} else if (!(FilenameUtils.getExtension(registrationModel.getAvatar().getName()).equalsIgnoreCase("jpg")
				|| FilenameUtils.getExtension(registrationModel.getAvatar().getName()).equalsIgnoreCase("jpeg")
				|| FilenameUtils.getExtension(registrationModel.getAvatar().getName()).equalsIgnoreCase("png"))) {
			errorsList = new ArrayList<>();
			errorsList.add("invalid_content_type");
			validationsErrorMap.put("avatar", errorsList);
		}

		if (!validationsErrorMap.isEmpty()) {
			throw new CustomException(HttpStatus.BAD_REQUEST.value(), validationsErrorMap);
		}

	}

	@Override
	public RegistrationModel statusUpdate(String phoneNumber, Object status) {
		LOG.info("statusUpdate - upding the status");

		RegistrationModel registrationModel = registrationModelRepository.findAllByPhoneNumber(phoneNumber);

		if (registrationModel == null) {
			Map<String, List<String>> validationsErrorMap = new HashMap<>();
			List<String> errorsList = new ArrayList<String>();
			errorsList = new ArrayList<>();
			errorsList.add("invalid_content_type");
			validationsErrorMap.put("avatar", errorsList);
			throw new CustomException(HttpStatus.NOT_FOUND.value(), validationsErrorMap);
		}

		registrationModel.setStatus(status);

		return registrationModel;
	}

}
