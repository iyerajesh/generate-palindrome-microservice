package com.zillion.microservices.palindrome.web.util.json;

import java.util.List;

import org.springframework.validation.FieldError;

public class Errors {

	private List<FieldError> fieldErrors;

	public List<FieldError> getFieldErrors() {
		return fieldErrors;
	}

	public void setFieldErrors(final List<FieldError> fieldErrors) {
		this.fieldErrors = fieldErrors;
	}

}
