package com.zillion.microservices.palindrome.web.util;

import org.springframework.http.HttpStatus;
import com.zillion.microservices.palindrome.exception.ValidationException;

/**
 * Simple static methods to be called at the start of your own methods to verify
 * correct arguments and state. If the Precondition fails, an {@link HttpStatus}
 * code is thrown
 */
public final class RestPreconditions {

	private RestPreconditions() {
		throw new AssertionError();
	}

	// API

	/**
	 * Check if some value was found, otherwise throw exception.
	 *
	 * @param expression
	 *            has value true if found, otherwise false
	 * @throws ValidationException
	 *             if expression is false, means value not found.
	 */
	public static void checkLimit(final int limit) throws ValidationException {

		if (limit < 1)
			throw new ValidationException("limit", "limit cannot be less than 1!");

		if (limit > 5)
			throw new ValidationException("limit", "limit cannot be more than 5!");
	}

	/**
	 * Check if some value was found, otherwise throw exception.
	 *
	 * @param expression
	 *            has value true if found, otherwise false
	 * @throws ValidationException
	 *             if expression is false, means value not found.
	 */
	public static String checkField(final String field, final String value) throws ValidationException {

		if (value == null || value.equals("")) {
			throw new ValidationException(field, field + " cannot be empty!");
		}

		return field;
	}

}
