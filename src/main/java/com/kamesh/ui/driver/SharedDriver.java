package com.kamesh.ui.driver;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;
import static com.kamesh.ui.driver.BrowserFactory.getRealDriver;
import org.openqa.selenium.WebDriver;

public class SharedDriver {
   private static ThreadLocal<WebDriver> drivers = new ThreadLocal<>();
   private static List<WebDriver> storedDrivers = new ArrayList<>();
   static {
	   Runtime.getRuntime().addShutdownHook(new Thread(() -> {
		   storedDrivers.stream().forEach(WebDriver::quit);
	   }));
   }
   public SharedDriver() throws MalformedURLException {
	   System.out.println("'Thread# "+Thread.currentThread().getId());
	   addDriver(getRealDriver());
	   
   }
   
	private static void addDriver(WebDriver driver) {
	System.out.println("Addition of the driver");
	storedDrivers.add(driver);
	drivers.set(driver);
	
}
	public static WebDriver getDriver() {
		return drivers.get();
	}

}
