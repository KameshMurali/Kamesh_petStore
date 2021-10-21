package com.kamesh.ui.utils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyReader {
	Properties properties;

	public PropertyReader(String fileName){
		InputStream is = null;
		properties = new Properties();
		try {
		is = getClass().getClassLoader().getResourceAsStream(fileName);
		if (is != null) {
			
				properties.load(is);
			
		}
		 else {
				throw new FileNotFoundException("property file '" + fileName + "' not found in the classPath");
			}
	}catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	}
	
	public String get(String key) {
		return properties.getProperty(key);
	}
}
