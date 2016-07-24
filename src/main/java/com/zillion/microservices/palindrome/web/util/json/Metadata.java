package com.zillion.microservices.palindrome.web.util.json;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Metadata {

	/*
	 * @author Rajesh Iyer
	 */

	private boolean success;
	private String country;
	private String language;

	private static final Logger logger = LoggerFactory.getLogger(Metadata.class);

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(final boolean success) {
		this.success = success;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(final String country) {
		this.country = country;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(final String language) {
		this.language = language;
	}

}
