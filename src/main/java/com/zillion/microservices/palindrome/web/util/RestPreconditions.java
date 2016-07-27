package com.zillion.microservices.palindrome.web.util;

import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import com.zillion.microservices.palindrome.exception.ValidationException;

/**
 * Simple static methods to be called at the start of your own methods to verify
 * correct arguments and state. If the Precondition fails, an {@link HttpStatus}
 * code is thrown
 */
@Component
public final class RestPreconditions {

	@Autowired
	private MessageSource messageSource;

	private static final Logger logger = LoggerFactory.getLogger(RestPreconditions.class);

	/**
	 * Check if limit is within the boundaries, otherwise throw
	 * ValidationException.
	 *
	 * @param limit
	 *            has value true if found, otherwise false
	 * @throws ValidationException
	 *             if expression is false, means value not found.
	 */
	public void checkLimit(final int limit) throws ValidationException {

		if (limit < 1)
			throw new ValidationException("limit", messageSource.getMessage("limit.field.error", null, Locale.US));

		if (limit > 5)
			throw new ValidationException("limit", messageSource.getMessage("limit.field.error", null, Locale.US));
	}

	/**
	 * Check if some value was found, otherwise throw Validation exception.
	 *
	 * @param field
	 *            the field to be checked if the value is null or empty
	 * @throws ValidationException
	 *             if the field is null or empty, the ValidationException gets
	 *             thrown.
	 */
	public String checkField(final String field, final String value) throws ValidationException {

		if (value == null || value.equals(""))
			throw new ValidationException(field,
					messageSource.getMessage("generic.field.error", new Object[] { field }, Locale.US));

		return field;
	}

}
