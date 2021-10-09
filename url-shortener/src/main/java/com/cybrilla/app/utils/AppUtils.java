package com.cybrilla.app.utils;

import java.security.SecureRandom;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class AppUtils {
	
	@Value("${SHORT.URL.LENGTH}")
	private String length;

	public String getAlphaNumericString() {
		int urlLength = Integer.parseInt(length);
		String upper = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		String lower = upper.toLowerCase(Locale.ROOT);
		String digits = "0123456789";
		String alphanum = upper + lower + digits;
		SecureRandom random = new SecureRandom();

		StringBuilder sb = new StringBuilder(urlLength);
		for (int i = 0; i < urlLength; i++) {
			sb.append(alphanum.charAt(random.nextInt(alphanum.length())));
		}
			
		return sb.toString();

	}

}
