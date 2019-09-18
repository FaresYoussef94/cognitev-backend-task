package com.fares.youssef.cognitev.backend.task.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;

@RestController
@RequestMapping("/authentication")
public class AuthenticationController {

	private final AuthenticationManager authenticationManager;

	public AuthenticationController(AuthenticationManager authenticationManager) {
		this.authenticationManager = authenticationManager;
	}

	@PostMapping("/login")
	public ResponseEntity login(HttpServletRequest request, HttpServletResponse response,
			HttpEntity<String> httpEntity) {

		JSONObject json = new JSONObject(httpEntity.getBody());

		try {
			Authentication authenticate = authenticationManager
					.authenticate(new UsernamePasswordAuthenticationToken(json.get("username"), json.get("password")));
			if (authenticate.isAuthenticated()) {
				SecurityContextHolder.getContext().setAuthentication(authenticate);
				HttpSession session = request.getSession(false);
				if (session == null)
					session = request.getSession(true);
				session.setAttribute("SPRING_SECURITY_CONTEXT", SecurityContextHolder.getContext());
			}

		} catch (Exception e) {
			throw e;
		}
		return ResponseEntity.status(HttpStatus.OK.value())
				.body(RequestContextHolder.getRequestAttributes().getSessionId());
	}

}
