package com.fares.youssef.cognitev.backend.task.security;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import com.fares.youssef.cognitev.backend.task.exception.CustomException;
import com.fares.youssef.cognitev.backend.task.model.Users;
import com.fares.youssef.cognitev.backend.task.repository.UserModelRepository;

@Component
public class AuthenticationProviderImpl implements AuthenticationProvider {

	private static final Logger LOG = LoggerFactory.getLogger(AuthenticationProviderImpl.class);

	@Autowired
	private UserModelRepository userModelRepository;

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		LOG.info("authentication - authenticating the credentials");

		Users loggedInUser = userModelRepository.findAllByPhoneNumberAndPassword(
				authentication.getPrincipal().toString(), authentication.getCredentials().toString());

		if (loggedInUser == null) {
			List<String> errors = new ArrayList<>();
			errors.add("Username/password is incorrect");
			Map<String, List<String>> errorMap = new HashMap<>();
			errorMap.put("Log In", errors);
			throw new CustomException(HttpStatus.UNAUTHORIZED.value(), errorMap);
		}

		return new UsernamePasswordAuthenticationToken(loggedInUser.getPhoneNumber(), loggedInUser.getPassword());
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}

}
