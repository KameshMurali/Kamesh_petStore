package com.kamesh.ui.pageobjects;

import java.io.IOException;
import java.nio.file.Files;
import java.util.Random;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.kamesh.ui.basewrapper.BaseWrapper;
import com.kamesh.ui.utils.PageFactoryManager;
import com.kamesh.utils.Common;
import com.kamesh.utils.Reusables;

import cucumber.api.Scenario;
import groovyjarjarantlr.CommonAST;

public class PetStoreSwagger {
	private WebDriver driver;
	Random rand = new Random();
	private Scenario scenario;
	public BaseWrapper baseWrapper;
	private PageFactoryManager pageFactoryManager;

	public PetStoreSwagger(WebDriver driver) {
		this.driver = driver;
		pageFactoryManager = new PageFactoryManager(driver);
		PageFactory.initElements(driver, this);
	}

	// *[@id="operations-pet-addPet"]/div[1]/button[1]/span[1]
	@FindBy(xpath = "//div[contains(text(),'Add a new pet to the store')]/preceding-sibling::span[contains(text(),'POST')]")
	private WebElement postNewPetToStore;

	@FindBy(xpath = "//button[contains(text(),'Try it out ')]")
	private WebElement tryItOutButton;

	@FindBy(xpath = "//button[contains(text(),'Execute')]")
	private WebElement executeButton;

	@FindBy(xpath = "//table[@class='responses-table live-responses-table']/tbody/tr/td[@class='response-col_description']//span[@class=\"hljs-attr\" and text()='\"id\"']/following-sibling::span[2]")
	private WebElement newPetId;

	@FindBy(xpath = "//textarea[@class='body-param__text']")
	private WebElement jsonbody;

	@FindBy(xpath = "//table[@class='responses-table live-responses-table']/tbody/tr/td[@class='response-col_status']")
	private WebElement responseStatus;

	public void addNewPet(String jsonfilename) throws IOException {
		pageFactoryManager.getBaseWrapper().verifyAndClick(postNewPetToStore);
		pageFactoryManager.getBaseWrapper().verifyAndClick(tryItOutButton);
		String jsonvalue = Reusables.readPayloadFromJsonFile(Common.PAYLOAD_PATH + jsonfilename + ".json");
		pageFactoryManager.getBaseWrapper().verifyAndType(jsonbody, jsonvalue);
		pageFactoryManager.getBaseWrapper().verifyAndClick(executeButton);
	}

	public Boolean vaidateResponseStatus(String status) throws InterruptedException {
		boolean responsesuccess = false;
		pageFactoryManager.getBaseWrapper().verifyAndClick(responseStatus);
		System.out.println(pageFactoryManager.getBaseWrapper().verifyAndGetText(responseStatus));
		if (pageFactoryManager.getBaseWrapper().verifyAndGetText(responseStatus).substring(0, 3)
				.equalsIgnoreCase(status))
			responsesuccess = true;
		return responsesuccess;
	}

	public String retrievePetId() {
		String responsePetId = "";
		pageFactoryManager.getBaseWrapper().verifyAndClick(newPetId);
		responsePetId=pageFactoryManager.getBaseWrapper().verifyAndGetText(newPetId);
		return responsePetId;

	}

}
