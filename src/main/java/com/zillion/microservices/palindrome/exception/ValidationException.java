package com.zillion.microservices.palindrome.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/*
 * @author Rajesh Iyer
 */

public final class ValidationException extends Exception {

	private String errorField;
	private String errorMessage;

	private static final long serialVersionUID = 1L;
	private static final Logger logger = LoggerFactory.getLogger(ValidationException.class);

	public ValidationException(String errorField, String errorMessage) {

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
