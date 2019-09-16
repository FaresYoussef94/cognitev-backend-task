package com.fares.youssef.cognitev.backend.task.exception;

import java.util.List;
import java.util.Map;

public class CustomException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private Map<String, List<String>> errors;
	private int status;

	public CustomException(int status, Map<String, List<String>> errors) {
		this.errors = errors;
		this.status = status;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Map<String, List<String>> getErrors() {
		return errors;
	}

	public void setErrors(Map<String, List<String>> errors) {
		this.errors = errors;
	}

}
