package com.qa.pagefactory.databricks.reports;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ReportSparkPageObject {

	@FindBy(xpath = "//span[contains(text(),'Spark')]/../following-sibling::div//span")
	public List<WebElement> appsDetails;

	@FindBy(xpath = "//h5[contains(text(),'Duration')]/..//h3")
	public WebElement duration;

	@FindBy(xpath = "//h5[contains(text(),'STAGES COUNT')]/..//h3")
	public WebElement stagesCount;

	@FindBy(xpath = "//div[contains(@class,'scroll-tab-container')]//li/a")
	public List<WebElement> summaryTabs;

	@FindBy(xpath = "//span[@title='Action menu']")
	public WebElement actionMenu;

	@FindBy(xpath = "//h4[contains(text(),'Load Diagnostics')]")
	public WebElement loadDiagnostics;
	
	@FindBy(xpath = "//a[contains(text(),'Cancel')]")
	public WebElement cancelDiagnostics;

	@FindBy(xpath = "//thead[@id='sparkDiagnosis-head']//span")
	public List<WebElement> diagnosticsHead;

	@FindBy(xpath = "//tbody[@id='sparkDiagnosis-body']//span")
	public List<WebElement> diagnosticValues;

	@FindBy(xpath = "//span[@class='text-ellipsis app-id']/following-sibling::span[1]")
	public WebElement getHeaderAppId;
	
	@FindBy(xpath = "//a[contains(text(),'Navigation')]")
	public WebElement navigationTab;
	
	@FindBy(xpath = "//a[contains(text(),'Gantt Chart')]")
	public WebElement ganttChartTab;

	@FindBy(xpath = "//thead[@class='app-navigation-head']/tr/th")
	public List<WebElement> appNavigationHeader;
	
	@FindBy(xpath = "//tbody[@class='app-navigation-body']/tr/td")
	public List<WebElement> appNavigationValue;
	
	@FindBy(xpath = "//div[@class='row no-gutters']/div")
	public List<WebElement> ganttChartHeader;
	
	@FindBy(xpath = "//div[contains(@id,'gantt-container')]")
	public List<WebElement> ganttChartValue;

	@FindBy(xpath = "//p[contains(text(),'Stage-0')]")
	public WebElement ganttChartStage;
	
	@FindBy(xpath = "//a[contains(text(),'Taskattempt')]")
	public WebElement taskAttempt;

	@FindBy(xpath = "//a[contains(text(),'Program')]")
	public WebElement program;

	@FindBy(xpath = "//a[contains(text(),'Timeline')]")
	public WebElement timeline;
	
	@FindBy(xpath = "//a[contains(text(),'Timings')]")
	public WebElement timings;

	/**
	 * @param driver The driver that will be used to look up the elements
	 */
	public ReportSparkPageObject(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}


}
