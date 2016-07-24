package com.zillion.microservices.test.util;

/**
 * @author Rajesh Iyer
 */
public class TestUtil {

	private static final String CHARACTER = "a";

	public static String createRedirectViewPath(final String path) {
		final StringBuilder redirectViewPath = new StringBuilder();
		redirectViewPath.append("redirect:");
		redirectViewPath.append(path);
		return redirectViewPath.toString();
	}

	public static String createStringWithLength(final int length) {
		final StringBuilder builder = new StringBuilder();

		for (int index = 0; index < length; index++) {
			builder.append(CHARACTER);
		}

		return builder.toString();
	}
}
