package com.qa.pagefactory.clusters;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class QueueAnalysisPageObject {

	@FindBy(xpath = "//h3/b[contains(text(),'Queue Analysis')]")
	public WebElement queueAnalysisHeading;

	@FindBy(xpath = "//div[contains(@class,'component-cta')]//span[contains(text(),'Run')]")
	public WebElement runButton;

	@FindBy(xpath = "//section[contains(@class,'component-message-banner')]/a")
	public List<WebElement> confirmationMessageElementClose;

	// @FindBy(xpath = "//span[contains(text(),'Run')]/parent::a")
	// public WebElement runButton;

	@FindBy(xpath = "(//span[contains(text(),'Run')])[2]/parent::a")
	public WebElement modalRunButton;

	@FindBy(xpath = "//section[contains(@class,'component-message-banner')]/div")
	public WebElement confirmationMessageElement;
	
	@FindBy (xpath = "//div[contains(@class,'daterangepicker') and contains(@class, 'open')]//li")
	public WebElement dateRanges;
	
	@FindBy (xpath = "//span[contains(@class,'text-fatal')]")
	public WebElement messageOnSelectingBeyond30days;
	
	@FindBy (xpath = "//section[contains(@class,'component-message-banner')]/span")
	public WebElement invalidInputMessage;
	
	@FindBy(className = "select2-search__field")
	public WebElement queueSearchBox;
	
	@FindBy(xpath = "//ul[(@class='select2-results__options')]/li")
	public List<WebElement> queueOptions;
	
	@FindBy(xpath = "//table/tbody/tr/td[1]")
	public List<WebElement> getQueueNameFromTable;
	
	@FindBy(xpath = "(//a[contains(@class,'icon-sort')])[1]")
	public WebElement sortByQueueName;
	
	@FindBy(xpath = "(//a[contains(@class,'icon-sort')])[2]")
	public WebElement sortByJobs;
	
	@FindBy(xpath = "(//a[contains(@class,'icon-sort')])[3]")
	public WebElement sortByVcore;
	
	@FindBy(xpath = "(//a[contains(@class,'icon-sort')])[4]")
	public WebElement sortByMemory;
	
	@FindBy (xpath = "//a[contains(@class,'icon-sort-sorted-down')]")
	public WebElement sortDown;
	
	@FindBy (xpath = "//a[contains(@class,'icon-sort-sorted-up')]")
	public WebElement sortUp;
	

	public QueueAnalysisPageObject(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
}
