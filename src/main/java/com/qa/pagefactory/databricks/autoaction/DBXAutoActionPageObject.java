package com.qa.pagefactory.databricks.autoaction;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class DBXAutoActionPageObject {

	@FindBy(xpath = "//span[contains(text(),'AutoActions')]")
	public WebElement autoActionTab;

	@FindBy(xpath = "//a[contains(text(),'New from Templates')]")
	public WebElement newTemplate;

	@FindBy(xpath = "//input[@placeholder='Created By']")
	public WebElement createdBy;

	@FindBy(xpath = "//i[@class='icon-edit']")
	public List<WebElement> edit;

	@FindBy(xpath = "//i[@class='icon-delete']")
	public  List<WebElement> delete;

	@FindBy(xpath = "//i[@class='icon-copy']")
	public  List<WebElement> copy;

	@FindBy(xpath = "//i[contains(@class,'icon-triangle-green')]")
	public  List<WebElement> activeAALists;

	@FindBy(xpath = "//i[@class='icon-triangle-red']")
	public WebElement inactive;

	@FindBy(xpath = "//a[contains(text(),'All')]")
	public WebElement allTab;

	@FindBy(xpath = "//a[contains(text(),'Active')]")
	public WebElement activeTab;

	@FindBy(xpath = "//a[contains(text(),'Inactive')]")
	public WebElement inactiveTab;

	@FindBy(xpath = "//th[contains(text(),'Name')]/../th")
	public  List<WebElement> resultSetHeaders;

	@FindBy(xpath = "//i[@class='icon-edit']/../../..")
	public  List<WebElement> resultSetRows;

	@FindBy(xpath = "//i[@class='icon-edit']/../../../../tr[1]/td")
	public  List<WebElement> resultSetValues;

	@FindBy(xpath = "//i[@class='icon-edit']/../../../../tr[2]/td/a")
	public WebElement JobName;

	@FindBy(xpath = "//p[contains(text(),'Auto action policy for Databricks Jobs')]")
	public WebElement longRunningJobs;

	@FindBy(xpath = "//p[contains(text(),'Auto action policy for Databricks Cluster')]")
	public WebElement allPurposeJobs;
	/**
	 * @param driver The driver that will be used to look up the elements
	 */
	public DBXAutoActionPageObject(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

}
