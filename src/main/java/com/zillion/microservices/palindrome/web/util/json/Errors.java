package com.zillion.microservices.palindrome.web.util.json;

import java.util.ArrayList;
import java.util.List;

/*
 * @author Rajesh Iyer
 */

public class Errors {

	private List<Error> fieldErrors = new ArrayList<Error>();

	public void addFieldError(String errorField, String errorMessage) {
		Error error = new Error(errorField, errorMessage);
		fieldErrors.add(error);
	}

	public List<Error> getFieldErrors() {
		return fieldErrors;
	}

	public void setFieldErrors(List<Error> fieldErrors) {
		this.fieldErrors = fieldErrors;
	}

}