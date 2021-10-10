/**
 * 
 */
package com.backend.test.utility;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validate {
	public static Boolean validateMobile(String mobileNumber) {
		Pattern pattern = Pattern.compile("^0+\\d{9}$");
		Matcher matcher = pattern.matcher(mobileNumber);
		return matcher.matches();
	}
}
