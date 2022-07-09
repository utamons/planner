package com.corn.planner.configuration;

@SuppressWarnings("unused")
public class Error {
	private final int status;
	private final String message;

	private Error(int status, String message) {
		this.status = status;
		this.message = message;
	}

	public int getStatus() {
		return status;
	}

	public String getMessage() {
		return message;
	}

	public static final class ErrorBuilder {
		private int status;
		private String message;

		private ErrorBuilder() {
		}

		public static ErrorBuilder anError() {
			return new ErrorBuilder();
		}

		public ErrorBuilder withStatus(int status) {
			this.status = status;
			return this;
		}

		public ErrorBuilder withMessage(String message) {
			this.message = message;
			return this;
		}

		public Error build() {
			return new Error(status, message);
		}
	}
}
