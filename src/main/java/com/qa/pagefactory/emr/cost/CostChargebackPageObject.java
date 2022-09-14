package com.qa.pagefactory.emr.cost;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CostChargebackPageObject {

	@FindBy(xpath = "//span[contains(text(),'Chargeback')]")
	public WebElement chargebackTab;


	@FindBy(xpath = "//span[contains(@class,'multiselect__option')]/span[contains(text(), 'Global')]")
	public WebElement globalList;

	@FindBy(xpath = "//span[contains(@class,'multiselect')]/span[contains(text(), 'Tags')]")
	public WebElement tagsList;


	/**
	 * @param driver The driver that will be used to look up the elements
	 */
	public CostChargebackPageObject(WebDriver driver) {
		PageFactory.initElements(driver, this);

	}

}
