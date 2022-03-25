package com.qa.pagefactory.databricks.reports;

import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ReportsArchivedPageObject {
	
	@FindBy(xpath = "//input[@class='search']")
	public WebElement searchBox;
	
	@FindBy(xpath = "//span[@class='pointer icon-add is-enabled undefined']")
	public WebElement addNewReport;
	
	@FindBy(xpath = "//span[@class='pointer icon-calendar is-enabled undefined']")
	public WebElement scheduledNewReport;
	
	@FindBy(xpath = "//span[@class='pointer icon-expand is-enabled undefined']")
	public WebElement viewReports;
	
	@FindBy(xpath = "//span[@class='pointer icon-copy undefined']")
	public WebElement copyLatestReportUrl;
	
	@FindBy(xpath = "//span[contains(text(),'SUCCESS')]")
	public WebElement success;
	
	@FindBy(xpath = "//span[contains(text(),'STARTED')]")
	public WebElement started;
	
	@FindBy(xpath = "//span[contains(text(),'Name')]")
	public WebElement sortName;
	
	@FindBy(xpath = "//span[contains(text(),'Created')]")
	public WebElement sortCreated;

	@FindBy(xpath = "//span[@class='pointer icon-download is-enabled undefined']")
	public List<WebElement> downloadList;
	
	@FindBy(xpath = "//span[@class='pointer icon-delete is-enabled undefined']")
	public List<WebElement> deleteList;
	
	@FindBy(xpath = "//span[@class='pointer icon-copy undefined']")
	public List<WebElement> copyUrlList;
	
	@FindBy(xpath = "//span[contains(text(),'Yes')]")
	public WebElement deleteYes;
	
	@FindBy(xpath = "//span[contains(text(),'No')]")
	public WebElement deleteNo;
	
	@FindBy(xpath = "//span[contains(text(),'Top X-')]")
	public List<WebElement> archivedTopXReportList;
	
	@FindBy(xpath = "//span[contains(text(),'/')]")
	public List<WebElement> archivedTopXReportCreatedDates;
	
	@FindBy(xpath = "//h2[contains(text(),'LATEST SUCCESSFUL TOP X REPORT')]")
	public WebElement latestSuccessfulReport;

	/**
	 * @param driver The driver that will be used to look up the elements
	 */
	public ReportsArchivedPageObject(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
}
