package com.fares.youssef.cognitev.backend.task.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import com.fares.youssef.cognitev.backend.task.exception.CustomException;
import com.fares.youssef.cognitev.backend.task.model.Users;
import com.fares.youssef.cognitev.backend.task.repository.UserModelRepository;
import com.fares.youssef.cognitev.backend.task.utils.StringValidatorUtils;

@Component
public class AuthenticationServiceImpl implements AuthenticationService {

	@Autowired
	private UserModelRepository userModelRpository;

	@Override
	public void signup(Users user) {

		validateSignupData(user);

		userModelRpository.save(user);

	}

	private void validateSignupData(Users user) {
		Map<String, List<String>> validationsErrorMap = new HashMap<>();
		List<String> errorsList = new ArrayList<String>();

		if (StringValidatorUtils.isBlankOrNull(user.getPhoneNumber())) {
			errorsList = new ArrayList<>();
			errorsList.add("blank");
			validationsErrorMap.put("phone_number", errorsList);
		} else if (userModelRpository.existsByPhoneNumber(user.getPhoneNumber())) {
			errorsList = new ArrayList<>();
			errorsList.add("taken");
			validationsErrorMap.put("phone_number", errorsList);
		}

		if (StringValidatorUtils.isBlankOrNull(user.getPassword())) {
			errorsList = new ArrayList<>();
			errorsList.add("password");
			validationsErrorMap.put("password", errorsList);
		}

		if (!validationsErrorMap.isEmpty()) {
			throw new CustomException(HttpStatus.BAD_REQUEST.value(), validationsErrorMap);
		}

	}

}
