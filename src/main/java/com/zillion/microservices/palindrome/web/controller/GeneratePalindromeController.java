package com.zillion.microservices.palindrome.web.controller;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.zillion.microservices.palindrome.exception.ValidationException;
import com.zillion.microservices.palindrome.model.Palindrome;
import com.zillion.microservices.palindrome.service.GeneratePalindromeService;
import com.zillion.microservices.palindrome.service.GetNasaPatentNamesService;
import com.zillion.microservices.palindrome.web.util.RestPreconditions;
import com.zillion.microservices.palindrome.web.util.json.Errors;
import com.zillion.microservices.palindrome.web.util.json.StandardJsonResponseBuilder;

/*
 * @author Rajesh Iyer
 */

@RestController
public class GeneratePalindromeController {

	private static final Logger logger = LoggerFactory.getLogger(GeneratePalindromeController.class);

	@Autowired
	private GeneratePalindromeService generatePalindromeService;

	@Autowired
	private GetNasaPatentNamesService getNasaPatentNamesService;

	@Autowired
	private RestPreconditions preconditions;

	@RequestMapping(value = "/palindromes", method = RequestMethod.GET)
	@ResponseStatus(value = HttpStatus.OK)
	public StandardJsonResponseBuilder generatePalindrome(@Valid @RequestParam(value = "search") final String search,
			@Valid @RequestParam(value = "limit") final int limit) throws Exception {

		try {

			preconditions.checkField("search", search);
			preconditions.checkLimit(limit);

			logger.debug(
					"Calling the generate palindrome service with searchString:" + search + ": with limit:" + limit);

			List<String> fullnames = getNasaPatentNamesService.getNASAPatentPortfolio(search, limit);
			List<Palindrome> palindromeList = generatePalindromeService.processPalindromes(fullnames);

			return StandardJsonResponseBuilder.buildResponse(true, palindromeList);

		} catch (ValidationException ex) {
			return StandardJsonResponseBuilder.buildResponse(false, handleBadRequest(ex));
		}
	}

	public Errors handleBadRequest(final ValidationException ex) {

		Errors errors = new Errors();
		errors.addFieldError(ex.getErrorField(), ex.getErrorMessage());

		return errors;
	}

}