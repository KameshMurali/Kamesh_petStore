package com.kamesh.ui.basewrapper;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.time.Duration;
import java.util.Base64;
import java.util.List;
import java.util.function.Function;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.NotFoundException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;



public class BaseWrapper 
{   
	private static final int TIMEOUT = 30;
	private WebDriver driver;
	private static final String PLACEHOLDER = "placeholder";
    public BaseWrapper(WebDriver driver) {
    	if(this.driver==null) {
		this.driver = driver;}
	}
	/**
	 * @param element
	 * @author Kamesh
	 * Verify and click the webElement
	 */
	public void verifyAndClick(WebElement element) {
		FluentWait<WebDriver> wait = new FluentWait<WebDriver>(driver);
		wait.pollingEvery(Duration.ofMillis(250));
		wait.withTimeout(Duration.ofSeconds(TIMEOUT));
		wait.ignoring(NoSuchElementException.class);
	wait.until(ExpectedConditions.elementToBeClickable(element));
		Function<WebDriver, Boolean> function = new Function<WebDriver,Boolean>(){
			public Boolean apply(WebDriver arg0) {
				boolean result = false;
				try {
				element.click();
				result = true;
				}
				catch(ElementNotVisibleException e) {
					return false;
				}
				catch(NoSuchElementException nse) {
					throw new NoSuchElementException("Unable to locate element with locator");
				}
				catch(StaleElementReferenceException se) {
					PageFactory.initElements(new AjaxElementLocatorFactory(driver, TIMEOUT), this);
					
				}
				catch(ElementClickInterceptedException ee) {
					if(ee.getMessage().contains("not clickable at point")) {
						return false;
					}
				}
				catch(WebDriverException we) {
					throw we;
				}
				return result;
			}
		};
		wait.until(function);
	}
		
	/**
	 * @param element
	 * @return element visible text
	 * Element Locator
	 */
	private String getElementLocator(WebElement element) {
		return element.getText();
	}
	
	/**
	 * @param element
	 * @param text
	 * Verify the element and type the text
	 */
	public void verifyAndType(final WebElement element, final CharSequence... text) {
		try {
		element.clear();
		element.sendKeys(text);
		}
		catch (NoSuchElementException nse) {
			throw nse;
		}
	}
	
	/**
	 * @param element
	 * @return
	 * Verify and the get the text of a webElement
	 */
	public String verifyAndGetText(WebElement element) {
		try{
			new WebDriverWait(this.driver,TIMEOUT).until((WebDriver d) -> !element.getText().isEmpty());
		}
		catch(TimeoutException toe) {
			throw new NoSuchElementException(String.format("Element did not appear after waited for %d seconds", TIMEOUT), toe);
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return element.getText().trim();
	}
	
	/**
	 * @param name
	 * @param elements
	 * Click by Rowname,webelements
	 */
	public void clickByRowName(String name, List<WebElement> elements) {
		clickByName(By.xpath("//tbody/tr"),name,elements);
	}
	
	
	/**
	 * Wait for table elements to load
	 */
	private void waitForTableElements() {
		System.out.println("Start - wait for table elements");
		waitForElements(By.xpath("//tbody/tr"));
		System.out.println("End - wait for table elements");
	}

	
	/**
	 * @param by
	 * Wait for elements by
	 */
	private void waitForElements(By by) {
		new WebDriverWait(this.driver,TIMEOUT).until((WebDriver d) -> !driver.findElements(by).isEmpty());
	}

	
	protected <T> void clickByName(T elementsToLookIn, String expectedRowName, List<WebElement> elemetntsToClickFrom) {
		boolean result = false;
		
		waitForTableElements();
		
		List<WebElement> actualElementsToLookIn = null;
		
		if(elementsToLookIn.getClass().getName().contains("By")) {
			actualElementsToLookIn = driver.findElements((By) elementsToLookIn);
		}
		else {
			actualElementsToLookIn=(List<WebElement>) elementsToLookIn;
		}
		
		List<WebElement> actualElementsToClickFrm = null;
		if(elemetntsToClickFrom.getClass().getName().contains("By")) {
			actualElementsToClickFrm = driver.findElements((By) elemetntsToClickFrom);
		}
		else {
			actualElementsToClickFrm=(List<WebElement>) elemetntsToClickFrom;
		}
		
		for(int i=0;i<actualElementsToLookIn.size();i++) {
			String actualName = actualElementsToLookIn.get(i).getText();
			if(actualName.contains(expectedRowName)) {
				verifyAndClick(actualElementsToClickFrm.get(i));
				result=true;
				break;
			}
		}
		if(!result) {
			throw new NotFoundException("***********ROW WITH VALUE: " +expectedRowName+ " NOT FOUND*********");
		}
	}
	
	/**
	 * @param elementsOrBy
	 * @param text
	 * @return the element by Text
	 * Get the Element by text from the List of elements or List of By
	 */
	protected <T> WebElement getElementByText(T elementsOrBy, String text) {
		List<WebElement> elements = null;
		if(elementsOrBy.getClass().getName().contains("By")) {
			waitForElements((By)elementsOrBy);
			elements = driver.findElements((By) elementsOrBy);
		}
		else {
		 elements = (List<WebElement>) elementsOrBy;
		}
		return getElementByText(elements, text);
	}
	
	/**
	 * @param elements
	 * @param text
	 * @return
	 * get Elements by text
	 */
	private WebElement getElementByText(List<WebElement> elements, String text) {
		return(elements.stream().filter(e -> e.getText().contains(text)).findFirst().
				orElseThrow(() -> new NoSuchElementException(String.format("Element with text %s can not be found", text))));
	}
	
	/**
	 * @param file
	 * Run an Executable file
	 */
	public void runExe(String file){
	try
	{System.out.println("Trying to run the file: "+file);
	Thread.sleep(2000);
	Runtime.getRuntime().exec(file);
	}catch(Exception e) {
		e.printStackTrace();
	}
	}
	
	/**
	 * @param xpathExpression
	 * @return Boolean value
	 * is Displayed based on xpath 
	 */
	public boolean isDisplayed(String xpathExpression) {
		return !driver.findElements(By.xpath(xpathExpression)).isEmpty();
	}
	
	/**
	 * @param element
	 * @return
	 * Is displayed based on element
	 */
	public boolean isDisplayed(WebElement element) {
		try {
			return element.isDisplayed();
		}catch(NoSuchElementException nse) {
			return false;
		}
	}
	
	/**
	 * @param screenshotName
	 * Save the screenshot with the passed name 
	 */
	public void takeScreenshot(String screenshotName) {
		try {
			String screenshotPath = ".\\screenshots\\"+screenshotName+".png";
			File srcFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(srcFile, new File(screenshotPath));
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * @param screenshotName
	 * Save the screenshot with the passed name and this method used Ashot library
	 */
	public void takeFullPageScreenshot(String screenshotName) {
		try {
			String screenshotPath = ".\\screenshots\\"+screenshotName+".png";
			Screenshot screenshot = new AShot().shootingStrategy(ShootingStrategies.viewportPasting(1000)).takeScreenshot(driver);
			ImageIO.write(screenshot.getImage(),"png", new File(screenshotPath));
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * @param file
	 * @return the Base64String value of the encoded value
	 * Encodes the file passed
	 */
	public static String encodeFileToBase64(File file) {
		try{
			byte[] fileContent = Files.readAllBytes(file.toPath());
			return Base64.getEncoder().encodeToString(fileContent);
		}
		catch(IOException e) {
			throw new IllegalStateException("could not read file "+file, e);
		}
	}
}

