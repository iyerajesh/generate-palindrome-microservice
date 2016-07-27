package com.zillion.microservices.palindrome.web.controller;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import javax.annotation.Resource;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.zillion.microservices.palindrome.config.AppConfig;
import com.zillion.microservices.palindrome.config.SecurityConfig;

/**
 * @author Rajesh Iyer
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { AppConfig.class, SecurityConfig.class })
@WebAppConfiguration
public class PalindromeControllerTest {

	private static final Logger logger = LoggerFactory.getLogger(PalindromeControllerTest.class);

	@Resource
	private FilterChainProxy springSecurityFilterChain;

	@Resource
	private WebApplicationContext webApplicationContext;

	private MockMvc mockMvc;

	@Before
	public void setUp() {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).addFilter(springSecurityFilterChain)
				.build();
	}

	@Test
	public void verifyPalindromeAPICallWithId() throws Exception {
		mockMvc.perform(get("/palindrome/v1/notes/3496")).andExpect(status().isNotFound());
	}

	@Test
	public void submitPostRequest_ShouldReturnMethodNotSupported() throws Exception {
		mockMvc.perform(post("/palindromes?search=electricity&limit=2")).andExpect(status().isMethodNotAllowed());
	}

	@Test
	public void verifySuccessfulTransaction() throws Exception {

		mockMvc.perform(get("/palindromes?search=electricity&limit=2"))
				.andExpect(jsonPath("$.metadata.success", is(true))).andExpect(status().isOk());

	}

	@Test
	public void verifyFailedTransaction() throws Exception {

		mockMvc.perform(get("/palindromes?search=&limit=2")).andExpect(jsonPath("$.metadata.success", is(false)))
				.andExpect(status().isOk());

	}

	@Test
	public void checkLimitValidationForLessThan1() throws Exception {

		mockMvc.perform(get("/palindromes?search=electricity&limit=0"))
				.andExpect(jsonPath("$.data.payload.fieldErrors[0].errorField", is("limit")))
				.andExpect(status().isOk());

	}

	@Test
	public void checkLimitValidationForMoreThan6() throws Exception {

		mockMvc.perform(get("/palindromes?search=electricity&limit=6"))
				.andExpect(jsonPath("$.data.payload.fieldErrors[0].errorField", is("limit")))
				.andExpect(status().isOk());

	}

}
