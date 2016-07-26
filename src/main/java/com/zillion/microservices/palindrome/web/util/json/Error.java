package com.zillion.microservices.palindrome.web.util.json;

/*
 * @author Rajesh Iyer
 */

public class Error {

	private String errorField;
	private String errorMessage;

	public Error(String errorField, String errorMessage) {

		this.errorField = errorField;
		this.errorMessage = errorMessage;
	}

	public String getErrorField() {
		return errorField;
	}

	public void setErrorField(String errorField) {
		this.errorField = errorField;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

}
