package com.qa.pagefactory.databricks.reports;

import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ReportsTopXPageObject {

	@FindBy(xpath = "//span[contains(text(),'Reports')]")
	public WebElement reports;
	
	@FindBy(xpath = "//button/span[contains(text(),'Run')]")
	public WebElement topXRunBtn;

	@FindBy(xpath = "//button/span[contains(text(),'Schedule')]")
	public WebElement scheduleBtn;

	@FindBy(xpath = "//button[@title='Copy report URL']")
	public WebElement copyUrl;

	@FindBy(xpath = "//div[@class='toolbox']")
	public WebElement threeDots;

	@FindBy(xpath = "//span[contains(text(),'Download JSON')]")
	public WebElement downloadJson;

	@FindBy(xpath = "//table[@class='component-data-tables row-hover topx-param-tbl']/thead/tr/th")
	public List<WebElement> inputParameterHeaders;

	@FindBy(xpath = "//th[contains(text(),'Parameter')]/../../../tbody//tr/td")
	public List<WebElement> inputParameterValues;

	@FindBy(xpath = "//label[contains(text(),'Top X')]/following-sibling::input")
	public WebElement topxTextArea;

	@FindBy(xpath = "//label[contains(text(),'Real Users')]/..//span//input")
	public WebElement realUserTextArea;

	@FindBy(xpath = "//label[contains(text(),'Workspaces')]/..//span//input")
	public WebElement workspaceTextArea;
	
	@FindBy(xpath = "//div[@class='clusters']")
	public WebElement cluster;

	@FindBy(xpath = "//input[@placeholder='Search...']")
	public WebElement clusterTextArea;
	
	@FindBy(xpath = "//a[contains(text(),'Run')]")
	public WebElement newReportRunBtn;
	
	@FindBy(xpath = "//div[contains(text(),'Top X Report completed successfully.')]")
	public WebElement reportGenerationMsg;
	
	@FindBy(xpath = "//div[contains(text(),'Please make sure all inputs are valid.')]")
	public WebElement invalidInputErrorMessage;
	
	@FindBy(xpath = "//div[contains(text(),'Top X is required.')]")
	public WebElement requiredFieldErrorMessage;
	
	@FindBy(xpath = "//div[contains(text(),'Top X should be a number')]")
	public WebElement invalidTopXNumberErrorMessage;

	@FindBy(xpath = "//span[contains(text(),'Archived')]")
	public WebElement archivedTab;
	
	@FindBy(xpath = "//span[contains(text(),'Scheduled')]")
	public WebElement scheduledTab;
	
	@FindBy(xpath = "//span[contains(text(),'Top X')]")
	public WebElement topXTab;

	@FindBy(xpath = "//table[@class='component-data-tables row-hover']/thead/tr/th/span")
	public List<WebElement>  applicationHeaders;
	
	@FindBy(xpath = "//table[@class='component-data-tables row-hover']/tbody/tr/td/span")
	public List<WebElement>  applicationHeadersValue;
	
	@FindBy(xpath = "//label[contains(text(),'Workspaces')]/..//table//tr/td/span")
	public List<WebElement>  appCount;
	
	@FindBy(xpath = "//span[contains(text(),'Total Apps')]/../../following-sibling::div//h2")
	public WebElement totalAppCount;
	
	@FindBy(xpath = "//span[contains(text(),'Name')]/..//a[@class='icon-sort']")
	public WebElement sortIcon;
	
	@FindBy(xpath = "//span[contains(text(),'App Count')]/..//a[contains(@class,'icon-sort')]")
	public WebElement sortAppCount;
	
	public String tagsType = "//label[contains(text(),'%s')]/preceding-sibling::input";
	
	public String tagsName = "//label[contains(text(),'%s')]/preceding-sibling::input/../../following-sibling::footer/ul//input";
	
	public String applicationStatusStates = "//a[contains(text(),'%s')]";

	/**
	 * @param driver The driver that will be used to look up the elements
	 */
	public ReportsTopXPageObject(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

}
