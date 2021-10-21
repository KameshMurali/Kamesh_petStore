package com.kamesh.stepdefs;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.io.StringWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.output.WriterOutputStream;
import org.apache.commons.lang3.SystemUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.testng.Reporter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.jayway.jsonpath.JsonPath;
import com.kamesh.pojo.Pet;
import com.kamesh.pojo.serializeDeserialize.PetSerializeDeserialize;
import com.kamesh.ui.driver.BrowserFactory;
import com.kamesh.ui.pageobjects.PetStoreSwagger;
import com.kamesh.ui.utils.PageFactoryManager;
import com.kamesh.utils.Common;
import com.kamesh.utils.Reusables;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class CommonSteps {

	public static RequestSpecification requestSpecs;
	public static Response res;
	public static Properties prop = new Properties();
	public static FileInputStream fis;
	public static Scenario step;
	public static Logger log = LogManager.getLogger(CommonSteps.class.getName());
	public static long currentTimestamp = System.currentTimeMillis();
	public static ThreadLocal<String> testCaseName = new ThreadLocal<String>();
	public static String resourceURL;
	private static WebDriver driver;
	private static PageFactoryManager pagefactory;
	URL temp = getClass().getClassLoader().getResource("config.properties");
	private StringWriter requestWriter;
	private PrintStream requestCapture;
	private StringWriter responseWriter;
	private PrintStream responseCapture;
	private String bodyvalue;

	@SuppressWarnings("deprecation")
	@Before
	public void beforeEveryScenario(Scenario s) throws Exception {
		CommonSteps.log.info(Common.SCENARIO_BEGINS);
		step = s;
		step.write(Common.SCENARIO_BEGINS);
		requestWriter = new StringWriter();
		requestCapture = new PrintStream(new WriterOutputStream(requestWriter), true);
		responseWriter = new StringWriter();
		responseCapture = new PrintStream(new WriterOutputStream(responseWriter), true);
		RestAssured.useRelaxedHTTPSValidation();
		RestAssured.urlEncodingEnabled = true;
		requestSpecs = RestAssured.given().filter(new RequestLoggingFilter(requestCapture))
				.filter(new ResponseLoggingFilter(responseCapture));
	}

	public static String getTestCaseName() {
		String s = step.getName();
		if (s == null)
			return "";
		if (s.contains("_"))
			return s.substring(s.indexOf("_") + 1);
		else
			return s.trim();

	}

	// Get config file loaded
	@Given("I want to initiate the config file")
	public void initiatePropertyFile() throws IOException {
		fis = new FileInputStream(temp.getFile());
		prop.load(fis);
	}

	
	@And("I pass headers")
	public void setHeaders(Map<String,String> headers) {
		Iterator<Entry<String, String>> it = headers.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry<String, String> pair = it.next();
			requestSpecs = requestSpecs.header(pair.getKey(), pair.getValue());
		}
		log.info("Request Specs on setting path parameters:"+requestSpecs.toString());
	}
	
	@And("I perform GET operation {string}")
	public void invokeGETOperation(String resourceName) {
		resourceURL = prop.getProperty(resourceName);
		res = requestSpecs.when().get(resourceURL);
		log.info("Time taken to respond:" + res.getTimeIn(TimeUnit.MILLISECONDS));
		step.write("Time taken to respond:" + res.getTimeIn(TimeUnit.MILLISECONDS));

	}

	@And("I perform DELETE operation {string}")
	public void invokePUTOperation(String resourceName) {
		resourceURL = prop.getProperty(resourceName);
		res = requestSpecs.when().delete(resourceURL);
		log.info("Time taken to respond:" + res.getTimeIn(TimeUnit.MILLISECONDS));
		step.write("Time taken to respond:" + res.getTimeIn(TimeUnit.MILLISECONDS));

	}
	
	@And("I perform POST operation {string}")
	public void invokePOSTOperation(String resourceName) {
		resourceURL = prop.getProperty(resourceName);
//		RestAssured.baseURI= resourceURL;
		res = requestSpecs.when().post(resourceURL);
		log.info("Time taken to respond:" + res.getTimeIn(TimeUnit.MILLISECONDS));
		step.write("Time taken to respond:" + res.getTimeIn(TimeUnit.MILLISECONDS));

	}
	@And("I pass path parameters")
	public void passpathparameters(Map<String, String> pathparam) {
		Iterator<Entry<String, String>> it = pathparam.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry<String, String> pair = it.next();
			if(pair.getValue().contains("ENV-")) {
				requestSpecs = requestSpecs.pathParameter(pair.getKey(), System.getProperty(pair.getValue().split("ENV-")[1]));
			}
			else
			requestSpecs = requestSpecs.pathParameter(pair.getKey(), pair.getValue());
		}
		log.info("Request Specs on setting path parameters:"+requestSpecs.toString());
	}

	@And("I pass body as {string}")
	public void setBody(String jsonFileName, Map<String,String> headers) throws IOException {
		bodyvalue = "";
		Iterator<Entry<String,String>> it = headers.entrySet().iterator();
		bodyvalue= Reusables.readPayloadFromJsonFile(Common.PAYLOAD_PATH+jsonFileName+".json");
		while(it.hasNext()) {
			Map.Entry<String, String> pair = it.next();
			if(bodyvalue.contains("%"+pair.getKey())) {
				bodyvalue.replace("%"+pair.getKey(), String.valueOf(pair.getValue()));
			}
		}
		requestSpecs.body(bodyvalue);
	}
	
	@Then("I should get {string} response")
	public void validateHttpStatusCode(String arg) {
		if (res != null) {
			CommonSteps.log.info(Common.RESPONSE_STATUSCODE + res.getStatusCode());
			step.write(Common.RESPONSE_STATUSCODE + String.valueOf(res.getStatusCode()));
			Assert.assertTrue("Response http statusCode is not "+arg, String.valueOf(res.getStatusCode()).equals(arg));
		}
	}

	
	@And("I should get the response with the following values")
	public static void verifyJsonResponseValues(Map<String,String> table) {
	String valueToBeCompared = "";
	String key = "";
	String bodyStringValue = res.getBody().asString();
	Iterator<Entry<String,String>> it = table.entrySet().iterator();
	while(it.hasNext()) {
		Map.Entry<String, String> pair = it.next();
		key = pair.getKey();
		Object value = JsonPath.parse(bodyStringValue).read("$."+key);
		valueToBeCompared = value.toString();
		Assert.assertTrue("Actual value :"+valueToBeCompared+" doesn't match with the expected", pair.getValue().equalsIgnoreCase(valueToBeCompared));
	}
	}
	
	@And("I should validate Pet response with the pojo class")
	public static void validatePetResponseWithPojo() throws JsonMappingException, JsonProcessingException {
		String bodyStringValue = res.getBody().asString();
		PetSerializeDeserialize pet = new PetSerializeDeserialize();
		Pet petval = pet.petDeserializeToPojo(bodyStringValue);
			String[] statusValue = {"available", "pending", "sold"};
			Assert.assertEquals(Arrays.asList(statusValue).contains(petval.getStatus()),true);
		}
	
	@And("I should retrieve the reponse and store the values in Env variable")
	public static void retrieveValueFromJsonAndStoreInEnv(Map<String,String> table) {
		String valueToBeStored= "";
		String key = "";
		String bodyStringValue = res.getBody().asString();
		Iterator<Entry<String,String>> it = table.entrySet().iterator();
		while(it.hasNext()) {
			Map.Entry<String, String> pair = it.next();
			key = pair.getKey();
			Object value = JsonPath.parse(bodyStringValue).read("$."+key);
			valueToBeStored = value.toString();
			System.setProperty(pair.getValue(), valueToBeStored);
		}
		}
	
	
	@And("I should retrieve the petId from UI and store it in Env variable {string}")
	public static void retrievePetIdAndStoreInEnv(String petId) {
		step.write("Retrieved Id: "+new String(pagefactory.getPetStoreSwagger().retrievePetId()));
			System.setProperty(petId, new String(pagefactory.getPetStoreSwagger().retrievePetId()));
		
		}
		
		
	
	@Given("I launch the browser")
	public static void openbrowser() throws MalformedURLException {
		Reporter.log("UI Autoframework");
		if(null==System.getenv("browser")||System.getenv("browser").isBlank()) {
		System.setProperty("browser", "chrome");}
		else
			System.setProperty("browser", System.getenv("browser"));
		driver = BrowserFactory.getRealDriver();
	}
	
	@And("I open applicationURL and validate the title {string}")
	public static void openPetStorePage(String title) {
		if(null == System.getenv("applicationURL")|| System.getenv("applicationURL").isEmpty()) 
		driver.get(prop.getProperty("applicationURL"));
		else
			driver.get(System.getenv("applicationURL"));	
		driver.getTitle().equalsIgnoreCase(title);
		step.write("Landed successfully on Swagger petstore page");
		pagefactory = new PageFactoryManager(driver);
	}
	
	@And("I click on add new pet {string}")
	public static void addnewPetInPetStore(String jsonFileName) throws IOException {
		pagefactory.getPetStoreSwagger().addNewPet(jsonFileName);
	}
	
	@And("The response status should be {string}")
	public static void responseStatusValidation(String status) throws InterruptedException {
		Assert.assertEquals(true,pagefactory.getPetStoreSwagger().vaidateResponseStatus(status));
	}

	@After
	public void afterEveryScenario() {
		driver.quit();
		step.write(Common.SCENARIO_ENDS);
		Reusables.printRequestsToExtenReport(requestWriter, step);
		Reusables.printResponseToExtenReport(responseWriter, step);
	}

}
