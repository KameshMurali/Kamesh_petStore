package com.kamesh.ui.utils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ScenarioContext {

	private static Map<String, Object> scenarioContext;

	private static List<String> responseHeaders;
    
	public ScenarioContext() {
		scenarioContext = new HashMap<>();
	}
	
	public void setContext(String key, Object value) {
		scenarioContext.put(key, value);
	}
	
	
	public void setContext(Context key, Object value) {
		if(key.equals(Context.responseHeader)) {
			responseHeaders.add(value.toString());
		}
		scenarioContext.put(key.toString(), value);
	}

	public Object getContext(String key) {
		return scenarioContext.get(key);
	}
	
	public Object getContext(Context key) {
		return scenarioContext.get(key.toString());
	}
	
	public Boolean isContains(Context key) {
		return scenarioContext.containsKey(key.toString());
	}
	

}
