
package com.zillion.microservices.palindrome.service;

import java.util.HashSet;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/*
 * @author Rajesh Iyer
 */

@Service
@Transactional
public class GeneratePalindromeService {

	private static final Logger logger = LoggerFactory.getLogger(GeneratePalindromeService.class);

	public GeneratePalindromeService() {
		super();
	}

	public void processPalindromes() throws Exception {

		String original = "Thomas Edison";

		original = original.trim().toLowerCase().replaceAll("\\s+", "");

		logger.debug("Length of the original string:" + original.length());

		Set<String> pSet = new HashSet<String>();
		int j = 0;

		for (int i = 0; i < original.length(); i++) {

			for (j = i + 2; j <= (int) Math.ceil((double) original.length()); j++) {

				logger.debug("palindrome source submitted:" + original.substring(i, j) + ":");
				String palindromeStr = generatePalindrome(original.substring(i, j).toCharArray(), original.length());

				pSet.add(palindromeStr);

				logger.debug("Generated palindrome string:" + palindromeStr);
				logger.debug("length of the generated palindrome string:" + palindromeStr.length());

				logger.debug("value of j in the inner loop:" + j);

			}

			logger.debug("value of i in the outer loop:" + i);
			logger.debug("value of j in the outer loop:" + j);

		}

		logger.debug("Total number of palindrome strings generated for:" + original + ":" + pSet.size());
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