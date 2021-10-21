package com.kamesh.ui.utils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import cucumber.api.Scenario;

public class TestContext {
public ScenarioContext scenarioContext;
public static Scenario scenario;
public String serviceId;

public static Scenario getStep() {
	return scenario;
}

public static void setStep(Scenario step) {
	TestContext.scenario = step;
}

public ScenarioContext getScenarioContext() {
	return scenarioContext;
}

public void TestContext() {
	this.scenarioContext = new ScenarioContext();
}
}
	