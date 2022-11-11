package com.qa.pagefactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AddOrderPageObject {

	@FindBy(xpath="//span[@class = 'dx-tab-text' and contains(text(),'Order')]")
	public WebElement orderTab;

	@FindBy(xpath="//span[@class = 'dx-tab-text' and contains(text(),'Addresses')]")
	public WebElement AddressTab;

	@FindBy(xpath="(//dx-drop-down-box[@valueexpr='orderNo']//input[@class = 'dx-texteditor-input'])[1]")
	public WebElement orderList;

	@FindBy(xpath="(//tbody[@role='presentation']//tr)[7]")
	public WebElement orders;

	@FindBy(xpath="//div[@class='header-toolbar']//i[@class='dx-icon dx-icon-add']")
	public WebElement createNewOrder;
	
	@FindBy(xpath="//div[@class='header-toolbar']//i[@class='dx-icon dx-icon-save']")
	public WebElement saveOrder;
	
	@FindBy(xpath="//div[@class='header-toolbar']//i[@class='dx-icon dx-icon-trash']")
	public WebElement deleteOrder;
	
	

	/**
	 * @param driver The driver that will be used to look up the elements
	 */
	public AddOrderPageObject(WebDriver driver) {
		PageFactory.initElements(driver,this);
	}
}
