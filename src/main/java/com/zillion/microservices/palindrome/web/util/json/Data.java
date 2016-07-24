package com.zillion.microservices.palindrome.web.util.json;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Data {

	/*
	 * @author Rajesh Iyer
	 */

	private static final Logger logger = LoggerFactory.getLogger(Data.class);
	private Object payload;

	public Object getPayload() {
		return payload;
	}

	public void setPayload(final Object payload) {
		this.payload = payload;
	}

}
