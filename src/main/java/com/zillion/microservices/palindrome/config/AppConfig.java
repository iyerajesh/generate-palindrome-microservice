package com.zillion.microservices.palindrome.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/*
 * @author Rajesh Iyer
 */

@Configuration
@ComponentScan("com.zillion")
@EnableWebMvc

public class AppConfig {

	private static final Logger logger = LoggerFactory.getLogger(AppConfig.class);

}
