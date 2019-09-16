package com.fares.youssef.cognitev.backend.task.utils;

public class StringValidatorUtils {

	private StringValidatorUtils() {

	}

	public static boolean isBlankOrNull(String input) {

		if (input == null || input.trim().equals("")) {
			return true;
		} else {
			return false;
		}
	}

}
