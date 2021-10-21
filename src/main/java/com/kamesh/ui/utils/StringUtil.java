package com.kamesh.ui.utils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtil {

	public String extract(String text, String regex) {
		String result = null;
		Matcher matcher = Pattern.compile(regex, Pattern.CASE_INSENSITIVE).matcher(text);
		if (matcher.find()) {
			result = matcher.group(1);
		}
		return result;
	}
}
