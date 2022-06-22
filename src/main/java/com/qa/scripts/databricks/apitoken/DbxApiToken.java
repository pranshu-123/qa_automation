package com.qa.scripts.databricks.apitoken;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import com.qa.pagefactory.apitokens.ApiTokensPageObjects;
import com.qa.utils.WaitExecuter;

public class DbxApiToken {

	private WebDriver driver;
	private WaitExecuter waitExecuter;
	private ApiTokensPageObjects apiTokensPageObjects;

	private static final java.util.logging.Logger LOGGER = java.util.logging.Logger.getLogger(DbxApiToken.class.getName());

	/**
	 * Constructor to initialize wait, driver and necessary objects
	 *
	 * @param driver - WebDriver instance
	 */
	public DbxApiToken(WebDriver driver) {
		this.driver = driver;
		waitExecuter = new WaitExecuter(driver);
		apiTokensPageObjects = new ApiTokensPageObjects(driver);
	}

	public void navigateToApiToken() {
		apiTokensPageObjects.apiTokenMenuManager.click();
		apiTokensPageObjects.apiTokenLink.click();
		LOGGER.info("Api Token option selected");
	}

	public void selectCreateNewApiToken() {
		apiTokensPageObjects.newApiTokenButton.click();
		LOGGER.info("Create New Api Token option selected");
	}

	public String createNewToken(String token) {
		apiTokensPageObjects.clientIdText.sendKeys(token);
		apiTokensPageObjects.createButton.click();
		LOGGER.info("New token created");
		return apiTokensPageObjects.tokenNameList.get(0).getText();
	}

	public String createEmptyToken() {
		apiTokensPageObjects.createButton.click();
		LOGGER.info("No New token created");
		return apiTokensPageObjects.errorTextMessage.getText();
	}

	public String retrieveSuccessMessage() {
		return apiTokensPageObjects.successTextMessage.getText();
	}

	public void deleteToken(String token) {
		driver.findElement(By.xpath(String.format(apiTokensPageObjects.deleteOption, token))).click();
		LOGGER.info("Api Token deleted");
	}

	public String retrieveErrorMessage() {
		return apiTokensPageObjects.errorTextMessage.getText();
	}

	public String validateCopyTokenFunctionality() {
		waitExecuter.sleep(1500);
		apiTokensPageObjects.copyButton.get(0).click();
		LOGGER.info("Token copied");
		return apiTokensPageObjects.successTextMessage.getText();
	}

	public void validateSorting(String sortingType) {
		TreeSet<String> resultant = new TreeSet<String>();
		TreeSet<String> initial = new TreeSet<String>();

		if(sortingType.equalsIgnoreCase("Client Id")) {
			initial = storeTokenList(sortingType);
			driver.findElement(By.xpath(String.format(apiTokensPageObjects.sortOption, sortingType))).click();
			waitExecuter.sleep(1000);
			resultant = storeTokenList(sortingType);
		}
		else {
			initial = storeTokenList(sortingType);
			driver.findElement(By.xpath(String.format(apiTokensPageObjects.sortOption, sortingType))).click();
			waitExecuter.sleep(1000);
			resultant = storeTokenList(sortingType);
		}
		Assert.assertEquals(resultant, initial);
	}

	public TreeSet<String> storeTokenList(String sortingType){
		Set<String> initialSet = new HashSet<String>();
		TreeSet<String> up = null;
		if(sortingType.equalsIgnoreCase("Client Id")) {
			for(int i = 0;i < apiTokensPageObjects.tokenNameList.size();i++) {
				initialSet.add(apiTokensPageObjects.tokenNameList.get(i).getText());
				up = new TreeSet<String>(initialSet);
			}
		}
		else {
			for(int i = 0;i < apiTokensPageObjects.apiKeyList.size();i++) {
				initialSet.add(apiTokensPageObjects.apiKeyList.get(i).getText());
				up = new TreeSet<String>(initialSet);
			}
		}

		return up;
	}

}
