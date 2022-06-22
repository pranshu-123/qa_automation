package com.qa.pagefactory.apitokens;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ApiTokensPageObjects {
	
	@FindBy(xpath = "//div[@class='header-dropdown manage-dropdown']")
	public WebElement apiTokenMenuManager;
	
	@FindBy(xpath = "//a/span[contains(text(),'API Tokens')]")
	public WebElement apiTokenLink;
	
	@FindBy(xpath = "//a[contains(text(),'New API Token')]")
	public WebElement newApiTokenButton;
	
	@FindBy(xpath = "//a[contains(@class,'icon-copy')]")
	public List<WebElement> copyButton;
	
	@FindBy(xpath = "//span[contains(@class,'icon-delete')]")
	public List<WebElement> deleteButton;
	
	@FindBy(xpath = "//label[contains(text(),'Client Id')]/../input")
	public WebElement clientIdText;
	
	@FindBy(xpath = "//a[contains(text(),'Create')]")
	public WebElement createButton;
	
	@FindBy(xpath = "//div[contains(@class,'text-fatal')]")
	public WebElement errorTextMessage;
	
	@FindBy(xpath = "//section[contains(@class,'icon-success')]/div")
	public WebElement successTextMessage;
	
	@FindBy(xpath = "//td[contains(@class,'key')][1]/span")
	public List<WebElement> tokenNameList;
	
	@FindBy(xpath = "//td[contains(@class,'key token-txt')]")
	public List<WebElement> apiKeyList;
	
	public String sortOption = "//span[contains(text(),'%s')]/..//a[contains(@class,'icon-sort')]";  //Client Id, Token
	public String deleteOption = "//span[contains(text(),'%s')]/../..//span[contains(@class,'icon-delete')]";

	/**
	 * @param driver The driver that will be used to look up the elements
	 */
	public ApiTokensPageObjects(WebDriver driver){
		PageFactory.initElements(driver, this);
	}

}
