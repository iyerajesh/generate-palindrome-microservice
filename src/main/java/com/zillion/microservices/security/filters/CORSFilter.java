package com.zillion.microservices.security.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/*
 * @author Rajesh Iyer
 */

@Component
public class CORSFilter implements Filter {

	private static final Logger logger = LoggerFactory.getLogger(CORSFilter.class);

	private static final String ALLOWED_ORIGINS = "*";
	private static final String ALLOWED_HTTP_HEADERS = "accept, x-requested-with, access-control-allow-origin,Content-Type,authorization";
	private static final String ALLOWED_HTTP_METHODS = "POST,GET,PUT,OPTIONS,DELETE";

	@Override
	public void doFilter(final ServletRequest req, final ServletResponse res, final FilterChain chain)
			throws IOException, ServletException {
		final HttpServletResponse response = (HttpServletResponse) res;

		response.setHeader("Access-Control-Allow-Origin", ALLOWED_ORIGINS);
		response.setHeader("Access-Control-Allow-Methods", ALLOWED_HTTP_METHODS);
		response.setHeader("Access-Control-Allow-Headers", ALLOWED_HTTP_HEADERS);
		response.setHeader("Access-Control-Max-Age", "3600");

		final HttpServletRequest request = (HttpServletRequest) req;

		if (request.getMethod().equals("OPTIONS")) {
			try {
				response.getWriter().print("OK");
				response.getWriter().flush();
			} catch (final IOException e) {
				e.printStackTrace();
			}
		} else {
			chain.doFilter(req, res);
		}
	}

	@Override
	public void init(final FilterConfig filterConfig) {
	}

	@Override
	public void destroy() {
	}
}