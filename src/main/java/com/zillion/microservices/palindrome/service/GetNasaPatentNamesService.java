
package com.zillion.microservices.palindrome.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import com.jayway.jsonpath.JsonPath;

/*
 * @author Rajesh Iyer
 */

@Service
@Transactional
@PropertySource({ "classpath:palindrome.properties" })
public class GetNasaPatentNamesService {

	private static final Logger logger = LoggerFactory.getLogger(GetNasaPatentNamesService.class);

	@Autowired
	private Environment env;

	/*
	 * Get the first names and the last names from the NASA patent REST API
	 * 
	 * @param searchString The search string to search the patent API with.
	 * 
	 * @param limit The limit of results to return
	 * 
	 * @return List<String> List containing all the full names of the patent
	 * creators.
	 */

	public List<String> getNASAPatentPortfolio(String searchString, int limit) {

		RestTemplate restTemplate = new RestTemplate();

		Map<String, String> params = new HashMap<String, String>();
		params.put("name", searchString);
		params.put("limit", Integer.toString(limit));

		String result = restTemplate.getForObject(env.getProperty("nasa.patent.url"), String.class, params);

		List<String> fnames = JsonPath.read(result, "$.results[*].innovator[*].fname");
		logger.debug("First names from the patent list:" + fnames);

		List<String> lnames = JsonPath.read(result, "$.results[*].innovator[*].lname");
		logger.debug("Last names from the patent list:" + lnames);

		List<String> fullnames = new ArrayList<String>();

		for (int i = 0; i < fnames.size(); i++) {

			StringBuilder fullname = new StringBuilder();

			fullname.append(fnames.get(i)).append(" ").append(lnames.get(i));
			fullnames.add(fullname.toString());

			logger.debug("Full name added to the list: " + fullname.toString());
		}

		return fullnames;
	}

}