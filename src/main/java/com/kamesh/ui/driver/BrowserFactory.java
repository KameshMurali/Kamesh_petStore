package com.kamesh.ui.driver;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.RemoteWebDriver;

/**
 * Hello world!
 *
 */
public class BrowserFactory 
{
   
   public static final String operatingSystem = System.getProperty("os.name").toUpperCase(); 	
   public static final String systemArchitecture = System.getProperty("os.arch"); 	
   public static final String gridUrl = System.getProperty("gridUrl"); 	
   public static final String browser = System.getProperty("browser"); 	
   public static final String INVALID_BROWSER_MESSAGE = "Browser: %s is not supported";
   public static final boolean useRemoteWebDriver = Boolean.getBoolean("remote");
   public static final boolean headless = Boolean.getBoolean("headless");
   
   private BrowserFactory() {
	   
   }
   
   public static WebDriver getRealDriver() throws MalformedURLException {
	   
	   WebDriver driver = null;
	   
	  if(useRemoteWebDriver) {
		  MutableCapabilities options = null;
		  if("CHROME".equalsIgnoreCase(browser)) {
				options = new ChromeOptions();
				System.setProperty("webdriver.chrome.driver", "src/test/resources/driver/chromedriver.exe");
				((ChromeOptions) options).addArguments("disable-infobars");
				((ChromeOptions) options).addArguments("--disable-notifications");
				((ChromeOptions) options).addArguments("start-maximized");
//				options.setExperimentalOption("useAutomationExtension", false);
//				options.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
				if(headless) {
					((ChromeOptions) options).addArguments("--headless");
				}
				else {
					throw new RuntimeException(String.format(INVALID_BROWSER_MESSAGE,browser));
				}
  
		  }
		  driver = new RemoteWebDriver(new URL(gridUrl),options);
	  }
	  else { 
	   
	  switch(browser) {
	  case "chrome":
		  driver = Chrome.buildChromeBrowser();
		  break;
	  default:
		  throw new RuntimeException(String.format(INVALID_BROWSER_MESSAGE, browser));
		  
	  }
	  }
	  return driver; 
	   
   }
   
    
}
