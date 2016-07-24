package com.zillion.microservices.palindrome.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.zillion.microservices.palindrome.service.GeneratePalindromeService;
import com.zillion.microservices.palindrome.web.util.json.StandardJsonResponseBuilder;

/*
 * @author Rajesh Iyer
 */

@RestController
@SpringBootApplication

public class GeneratePalindromeController {

	private static final Logger logger = LoggerFactory.getLogger(GeneratePalindromeController.class);

	@Autowired
	private GeneratePalindromeService generatePalindromeService;

	public static void main(String[] args) throws InterruptedException {
		SpringApplication.run(GeneratePalindromeController.class, args);
	}

	@RequestMapping(value = "/palindromes", method = RequestMethod.GET)
	@ResponseStatus(value = HttpStatus.OK)
	public StandardJsonResponseBuilder generatePalindrome(@RequestParam("search") final String searchString,
			@RequestParam("limit") final int limit) throws Exception {

		logger.debug(
				"Calling the generate palindrome service with searchString:" + searchString + ": with limit:" + limit);

		generatePalindromeService.processPalindromes();

		return StandardJsonResponseBuilder.buildResponse("Hello World");
	}
}