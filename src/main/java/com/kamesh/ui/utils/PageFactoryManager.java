package com.kamesh.ui.utils;

import org.openqa.selenium.WebDriver;

import com.kamesh.ui.basewrapper.BaseWrapper;
import com.kamesh.ui.pageobjects.PetStoreSwagger;
import cucumber.api.Scenario;

public class PageFactoryManager {
	private WebDriver driver;
	private PetStoreSwagger petStorePage;
	public Scenario scenario;
	private BaseWrapper basewrapper;

	public PageFactoryManager(WebDriver driver) {
		if (this.driver == null) {
			setBaseweapper(new BaseWrapper(driver));
			this.driver = driver;
		}
	}
	
	public PetStoreSwagger getPetStoreSwagger() {
		return petStorePage == null ? new PetStoreSwagger(driver) : petStorePage;
	}

	public BaseWrapper getBaseWrapper() {
		return basewrapper == null ? new BaseWrapper(driver) : basewrapper;
	}

	private void setBaseweapper(BaseWrapper baseWrapper) {
		this.basewrapper = baseWrapper;
	}
}
