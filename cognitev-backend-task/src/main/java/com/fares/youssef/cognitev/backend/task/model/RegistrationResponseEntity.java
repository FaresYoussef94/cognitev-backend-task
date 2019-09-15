package com.fares.youssef.cognitev.backend.task.model;

import java.io.Serializable;

public class ResponseEntity<T> implements Serializable {

	private static final long serialVersionUID = -1112267409919864216L;

	private Integer totalCount;
	private transient T data;

	public Integer getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
	}

	public static class ResponseBuilder<T> {

		private ResponseEntity<T> instance = new ResponseEntity<>();

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

		public ResponseEntity build() {
			return instance;
		}
	}
}
