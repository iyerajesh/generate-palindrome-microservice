
package com.zillion.microservices.palindrome.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
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
import com.zillion.microservices.palindrome.model.Palindrome;

/*
 * @author Rajesh Iyer
 */

@Service
@Transactional
@PropertySource({ "classpath:palindrome.properties" })

public class GeneratePalindromeService {

	private static final Logger logger = LoggerFactory.getLogger(GeneratePalindromeService.class);

	@Autowired
	private Environment env;

	public List<Palindrome> processPalindromes(List<String> fullnames) throws Exception {

		List<Palindrome> palindromeList = new ArrayList<Palindrome>();

		for (int x = 0; x < fullnames.size(); x++) {

			String original = fullnames.get(x).trim().toLowerCase().replaceAll("\\s+", "");
			Set<String> pSet = new HashSet<String>();

			int j = 0;
			for (int i = 0; i < original.length(); i++) {

				for (j = i + 2; j <= (int) Math.ceil((double) original.length()); j++) {

					logger.debug("palindrome source submitted:" + original.substring(i, j) + ":");
					String palindromeStr = generatePalindrome(original.substring(i, j).toCharArray(),
							original.length());

					pSet.add(palindromeStr);

					logger.debug("Generated palindrome string:" + palindromeStr);
					logger.debug("length of the generated palindrome string:" + palindromeStr.length());

				}
			}
			logger.debug("Total number of palindrome strings generated for:" + fullnames.get(x) + ":" + pSet.size());

			Palindrome palindrome = new Palindrome();
			palindrome.setName(fullnames.get(x));
			palindrome.setCount(pSet.size());

			palindromeList.add(palindrome);
		}

		/* sort the collection before returning */

		Collections.sort(palindromeList, new Comparator<Palindrome>() {
			public int compare(Palindrome p1, Palindrome p2) {
				return Long.valueOf(p2.getCount()).compareTo(p1.getCount());
			}
		});

		return palindromeList;
	}

	public static String generatePalindrome(char[] pChars, int size) {

		StringBuilder random = new StringBuilder(size);

		for (int k = 0, l = 0; k < (int) Math.ceil((double) size / 2); k++, l++) {

			char ch = 0;
			if (l < (pChars.length))
				ch = pChars[l];
			else {
				l = 0;
				ch = pChars[l];
			}
			logger.debug("Character generated for i and j:" + k + ":" + l + "--" + ch);
			random.append(ch);
		}

		logger.debug("palindrome formation before the reverse append:" + random.toString());

		for (int k = size / 2 - 1; k >= 0; k--) {

			logger.debug("Character at:" + random.charAt(k));
			random.append(random.charAt(k));
		}

		return random.toString();
	}
}