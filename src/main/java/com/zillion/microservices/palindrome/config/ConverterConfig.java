package com.zillion.microservices.palindrome.config;

import java.util.List;


import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.AbstractJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.ObjectMapper;

/*
 * @author Rajesh Iyer
 */

@Configuration
public class ConverterConfig extends WebMvcConfigurerAdapter {
	@Override
	public void extendMessageConverters(final List<HttpMessageConverter<?>> converters) {
		for (final HttpMessageConverter<?> converter : converters) {
			if (converter instanceof AbstractJackson2HttpMessageConverter) {
				final AbstractJackson2HttpMessageConverter c = (AbstractJackson2HttpMessageConverter) converter;
				final ObjectMapper objectMapper = c.getObjectMapper();
				objectMapper.setSerializationInclusion(Include.NON_NULL);
			}
		}

		super.extendMessageConverters(converters);
	}
}
