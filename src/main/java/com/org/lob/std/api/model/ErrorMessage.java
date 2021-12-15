package com.org.lob.std.api.model;

public class ErrorMessage {

    private String statusCode;
    private String message;

	public ErrorMessage(String statusCode, String errorMessage) {
		this.statusCode = statusCode;
		this.message = errorMessage;
	}
	public String getStatusCode() {
		return statusCode;
	}

	public String getErrorMessage() {
		return message;
	}
}