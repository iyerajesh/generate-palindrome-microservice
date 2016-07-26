package com.zillion.microservices.palindrome.web.util.json;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/*
 * @author Rajesh Iyer
 */

public class StandardJsonResponseBuilder {

	private Metadata metadata = new Metadata();
	private Data data = new Data();
	private Errors errors = new Errors();

	private static final Logger logger = LoggerFactory.getLogger(StandardJsonResponseBuilder.class);

	public Metadata getMetadata() {
		return metadata;
	}

	public void setMedadata(final Metadata medadata) {
		metadata = medadata;
	}

	public Data getData() {
		return data;
	}

	public void setData(final Data data) {
		this.data = data;
	}

	public Errors Errors() {
		return errors;
	}

	public void setErrors(final Object errors) {
		this.errors = (Errors) errors;
	}

	public void setPayload(final Object responsePayload) {
		data.setPayload(responsePayload);

	}

	public void setMetadata() {
		metadata.setCountry("US");
		metadata.setLanguage("en");
		metadata.setSuccess(true);
	}

	public static StandardJsonResponseBuilder buildResponse(final Object payload) {

		final StandardJsonResponseBuilder standardJsonResponseBuilder = new StandardJsonResponseBuilder();

		standardJsonResponseBuilder.setMetadata();
		standardJsonResponseBuilder.setPayload(payload);

		return standardJsonResponseBuilder;

	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((metadata == null) ? 0 : metadata.hashCode());
		return result;
	}

	@Override
	public String toString() {
		final StringBuilder builder = new StringBuilder();
		builder.append("StandardJSONResponseBuilder [metadata=").append(metadata).append("]");
		return builder.toString();
	}

}
