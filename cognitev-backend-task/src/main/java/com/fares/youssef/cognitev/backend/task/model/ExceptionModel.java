package com.fares.youssef.cognitev.backend.task.model;

import java.util.List;
import java.util.Map;

public class ExceptionModel {

	Map<String, List<String>> errors;

	public Map<String, List<String>> getErrors() {
		return errors;
	}

	public void setErrors(Map<String, List<String>> errors) {
		this.errors = errors;
	}

}
