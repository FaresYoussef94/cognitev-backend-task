package com.fares.youssef.cognitev.backend.task.advice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.fares.youssef.cognitev.backend.task.exception.CustomException;
import com.fares.youssef.cognitev.backend.task.model.ExceptionModel;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

	private static final Logger LOG = LoggerFactory.getLogger(RestExceptionHandler.class);

	@ExceptionHandler(CustomException.class)
	protected Object handleCustomExceptions(CustomException e, WebRequest request) {
		LOG.error("Custom exception occured", e);

		return ResponseEntity.status(e.getStatus()).contentType(MediaType.APPLICATION_JSON).body(e.getErrors());
	}

	@ExceptionHandler({ Exception.class })
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	protected Object handleGeneralExceptions(Exception e, WebRequest request) {
		LOG.error("General exception occurred", e);

		ExceptionModel exceptionModel = new ExceptionModel();
		Map<String, List<String>> excpetionModelMap = new HashMap<>();
		List<String> exceptionModelList = new ArrayList<>();

		exceptionModelList.add(e.getMessage());
		excpetionModelMap.put("General Exception", exceptionModelList);
		exceptionModel.setErrors(excpetionModelMap);

		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).contentType(MediaType.APPLICATION_JSON)
				.body(exceptionModel);
	}

}
