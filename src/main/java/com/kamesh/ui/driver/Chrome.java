package com.kamesh.ui.driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;

public class Chrome extends ChromeDriver {

	public static final String DRIVER_PATH = System.getProperty("driver.path", "src/test/resources/drivers/");
	public static final boolean headless = Boolean.getBoolean("headless");

	public static WebDriver buildChromeBrowser() {
		ChromeOptions options = new ChromeOptions();
		System.setProperty("webdriver.chrome.driver", "src/test/resources/driver/chromedriver.exe");
		options.addArguments("disable-infobars");
		options.addArguments("--disable-notifications");
		options.addArguments("start-maximized");
		options.setExperimentalOption("useAutomationExtension", false);
		options.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);

		if (headless) {
			options.addArguments("--headless");
		}
		return new ChromeDriver(options);

	}



}
