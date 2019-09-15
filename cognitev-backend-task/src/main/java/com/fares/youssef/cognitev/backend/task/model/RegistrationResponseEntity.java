package com.fares.youssef.cognitev.backend.task.model;

import java.io.Serializable;

public class RegistrationResponseEntity<T> implements Serializable {

	private static final long serialVersionUID = -1112267409919864216L;

	private Integer totalCount;
	private transient T data;

	public Integer getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
	}

	public static ResponseBuilder builder() {
		return new RegistrationResponseEntity.ResponseBuilder<>();
	}

	public static class ResponseBuilder<T> {

		private RegistrationResponseEntity<T> instance = new RegistrationResponseEntity<>();

		private ResponseBuilder() {
		}

		public ResponseBuilder withTotalCount(Integer totalCount) {
			this.instance.totalCount = totalCount;
			return this;
		}

		public ResponseBuilder withData(T data) {
			this.instance.data = data;
			return this;
		}

		public RegistrationResponseEntity build() {
			return instance;
		}
	}
}
