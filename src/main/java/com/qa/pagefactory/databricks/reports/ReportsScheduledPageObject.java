package com.qa.pagefactory.databricks.reports;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ReportsScheduledPageObject {
	
	@FindBy(xpath = "//button/span[contains(text(),'Schedule')]")
	public WebElement scheduleBtn;
	
	@FindBy(xpath = "//label[contains(text(),'Schedule Name')]/following-sibling::div/input")
	public WebElement scheduleName;
	
	@FindBy(xpath = "//select[@class='form-control input-sm schedule-days']")
	public WebElement scheduleTime;
	
	@FindBy(xpath = "//label[contains(text(),'Notification')]/following-sibling::div/div/input")
	public WebElement notificationTextBox;
	
	@FindBy(xpath = "//span[@class='icon-add pointer']")
	public WebElement addMoreEmails;
	
	@FindBy(xpath = "//a[contains(text(),'Schedule')]")
	public WebElement scheduleReport;
	
	@FindBy(xpath = "//input[@class='search']")
	public WebElement searchBox;
	
	@FindBy(xpath = "//span[@id='select2-ds3f-container']")
	public WebElement reportFilter;
	
	@FindBy(xpath = "//ul[@id='select2-ds3f-results']/li")
	public List<WebElement> reportFilterValues;
	
	@FindBy(xpath = "//div[@id='custom-tbl']//thead/tr/th/span")
	public List<WebElement> reportTableHeader;
	
	@FindBy(xpath = "//div[@id='custom-tbl']//tbody/tr/td/span")
	public List<WebElement> reportTableValues;
	
	@FindBy(xpath = "(//span[@class='pointer icon-edit is-enabled undefined'])[1]")
	public WebElement editScheduleReport;
	
	@FindBy(xpath = "(//span[@class='pointer icon-delete is-enabled undefined'])[1]")
	public WebElement deleteScheduleReport;
	
	@FindBy(xpath = "(//span[@class='pointer icon-expand is-enabled undefined'])[1]")
	public WebElement moreInfo;
	
	@FindBy(xpath = "//h2[contains(text(),'Scheduled Info')]")
	public WebElement scheduledInfo;
	
	@FindBy(xpath = "//h2[contains(text(),'Schedule Report')]/button")
	public WebElement closeButton;
	
	@FindBy(xpath = "//a[contains(text(),'Update')]")
	public WebElement updateBtn;
	
	@FindBy(xpath = "//h2[contains(text(),'Scheduled Info')]/../following-sibling::div//table/thead/tr/th/span")
	public List<WebElement> inputParameterHeader;
	
	@FindBy(xpath = "//h2[contains(text(),'Scheduled Info')]/../following-sibling::div//table/tbody/tr/td/span")
	public List<WebElement> inputParameterValue;
	
	/**
	 * @param driver The driver that will be used to look up the elements
	 */
	public ReportsScheduledPageObject(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

}
