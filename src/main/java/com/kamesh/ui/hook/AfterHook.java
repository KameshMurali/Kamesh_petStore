package com.kamesh.ui.hook;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.kamesh.ui.driver.SharedDriver;

import cucumber.api.Scenario;

/**
 * Hello world!
 *
 */
public class AfterHook 
{
    public static void embedScreenshot(Scenario scenario) {
    	WebDriver driver = SharedDriver.getDriver();
    	if (scenario.isFailed()&& driver!=null) {
    		TakesScreenshot screenshot = (TakesScreenshot) driver;
    		byte[] image = screenshot.getScreenshotAs(OutputType.BYTES);
    		scenario.embed(image,"image/png");
    	}
    }
}
