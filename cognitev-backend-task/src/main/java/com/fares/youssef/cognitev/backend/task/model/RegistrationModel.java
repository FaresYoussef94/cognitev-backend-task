package com.fares.youssef.cognitev.backend.task.model;

import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.MoreObjects;

@JsonInclude(Include.NON_NULL)
@JsonIgnoreProperties("avatar")
public class RegistrationModel {

	@JsonProperty("first_name")
	private String firstName;
	@JsonProperty("last_name")
	private String lastName;
	@JsonProperty("country_code")
	private String countryCode;
	@JsonProperty("phone_number")
	private String phoneNumber;
	@JsonProperty("gender")
	private String gender;
	@JsonProperty("birthdate")
	private String birthDate;
	@JsonProperty("avatar")
	private MultipartFile avatar;
	@JsonProperty("email")
	private String email;

	public RegistrationModel(String firstName, String lastName, String countryCode, String phoneNumber, String gender,
			String birthDate, MultipartFile avatar, String email) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.countryCode = countryCode;
		this.phoneNumber = phoneNumber;
		this.gender = gender;
		this.birthDate = birthDate;
		this.avatar = avatar;
		this.email = email;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}

	public MultipartFile getAvatar() {
		return avatar;
	}

	public void setAvatar(MultipartFile avatar) {
		this.avatar = avatar;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return MoreObjects.toStringHelper(RegistrationModel.class).add("first_name", firstName)
				.add("last_name", lastName).add("country_code", countryCode).add("phone_number", phoneNumber)
				.add("gender", gender).add("birthdate", birthDate).add("email", email).toString();
	}

}
