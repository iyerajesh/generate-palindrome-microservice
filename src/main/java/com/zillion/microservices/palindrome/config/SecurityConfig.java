package com.zillion.microservices.palindrome.config;

import org.slf4j.Logger;

import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.access.channel.ChannelProcessingFilter;

import com.zillion.microservices.security.filters.CORSFilter;

/*
 * @author Rajesh Iyer
 */

@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	private static final Logger logger = LoggerFactory.getLogger(SecurityConfig.class);

	@Override
	protected void configure(final HttpSecurity http) throws Exception {

		logger.debug("---- Registering the CORS security filter ----");
		http.addFilterBefore(new CORSFilter(), ChannelProcessingFilter.class).csrf().disable().authorizeRequests()
		.anyRequest().permitAll();

	}
}
