package com.qa.pagefactory.databricks.autoaction;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class DBXAutoActionPolicyPageObject {


	@FindBy(xpath = "//p//span[@class='checkmark']")
	public WebElement isEnabledCheckbox;
	
	@FindBy(xpath = "(//div/div/label)[1]/following-sibling::input")
	public WebElement policyName;

	@FindBy(xpath = "//div/div[2]/input")
	public WebElement policyDescription;

	@FindBy(xpath="//button[contains(@id,'0')]")
	public WebElement joinTxtBtn;

	@FindBy (xpath="//li[contains(@class,'rule-li')]/div/div/h4")
	public List<WebElement> selectedTriggerConditions;

	@FindBy (xpath = "//li[contains(@class,'rule-li')]/div/div/h4/parent::div/following-sibling::div")
	public WebElement cancelSelectedTriggerConditions;

	@FindBy (xpath = "//button[contains(text(),'Save')]")
	public WebElement saveBtn;

	@FindBy(xpath = "//section[contains(@class,'component-message-banner')]//div")
	public WebElement messageBanner;

	@FindBy(xpath = "//button[contains(@id,'scope-dropdown')]")
	public WebElement refineScopeBtn;

	@FindBy(xpath = "//button[@id='scope-dropdown']//div//a")
	public List<WebElement> selectRefineScopeList;
	
	@FindBy(xpath = "//button[@id='action-dropdown']//div//a")
	public List<WebElement> selectActionList;

	public String allUserChkBox =  "//h4[contains(text(),'%s')]/../following-sibling::div//label[contains(text(),'all')]";

	public String onlyUserChkBox =  "//h4[contains(text(),'%s')]/../following-sibling::div//label[contains(text(),'only')]";

	public String exceptUserChkBox =  "//h4[contains(text(),'%s')]/../following-sibling::div//label[contains(text(),'except')]";

	public String alwaysChkBox =  "//h4[contains(text(),'%s')]/../following-sibling::div//label[contains(text(),'always')]";

	public String dailyChkBox =  "//h4[contains(text(),'%s')]/../following-sibling::div//label[contains(text(),'daily')]";
	
	public String addedJobName =  "//a[contains(text(),'%s')]";
	
	@FindBy(xpath = "//div[contains(text(),'Start')]/span")
	public List<WebElement> startEndList;
	
	@FindBy(xpath = "//select[@class='hours']")
	public List<WebElement> hoursList;

	@FindBy(xpath = "(//select[contains(@class, 'component-multi-select')])[1]")
	public WebElement selectAppType;

	@FindBy(xpath = "(//select[contains(@class, 'component-multi-select')])[2]")
	public WebElement selectMetric;

	@FindBy(xpath = "//button[contains(@id,'action-dropdown')]")
	public WebElement actionButton;

	@FindBy(xpath = "//button[contains(@id,'action-dropdown')]//div//a")
	public List<WebElement> listOfActions;
	
	@FindBy(xpath = "//a[@class='close  icon-x-badge float-right']")
	public List<WebElement> listOfCrosses;

	@FindBy(xpath = "(//span[contains(@class, 'select2-selection')])[4]/span[2]")
	public WebElement metricCombo;
	
	@FindBy(xpath = "//input[@placeholder='Insert value...']")
	public WebElement totalValue;

	@FindBy(xpath="//input[contains(@placeholder, 'Email')]")
	public WebElement actionEle;

	@FindBy(xpath="//input[contains(@placeholder, 'Add Url')]")
	public WebElement actionEleToHttpPost;

	@FindBy(xpath="//input[contains(@class, 'input-sm') and @placeholder='Add Webhook Url']")
	public WebElement actionElePostToSlack;

	@FindBy(xpath="//button[contains(text(),'Add Email')]")
	public WebElement addEmailEleBtn;

	@FindBy(xpath="//button[contains(text(),'Add Url')]")
	public WebElement addUrlBtn;

	@FindBy(xpath="//button[contains(text(),'Add Webhook Url')]")
	public WebElement addWebhookUrlBtn;

	@FindBy(xpath="//div[contains(@class,'token-div')]/input[@placeholder='Token']")
	public WebElement webHookTokenEle;

	@FindBy(xpath = "//input[@placeholder='Queue Name']")
	public WebElement queueNameEle;

	@FindBy(xpath="//div[contains(@class,'kill-app-component')]//span[@class='checkmark']")
	public WebElement killAppChkBoxEle;

	@FindBy(xpath="//section[contains(@class,'component-message-banner')]//div")
	public WebElement fatalMsgTextEle;

	@FindBy(xpath="//span[contains(@id,'join-dropdown')]")
	public WebElement actionCondition;
	
	@FindBy(xpath="//div[contains(@class,'dropdown-content d-block')]/a")
	public List<WebElement> actionConditionList;
	
	@FindBy(xpath="//h1[contains(text(),'History of Runs of auto action :')]")
	public WebElement historyPageHeader;
	
	@FindBy(xpath = "//table[@class='component-data-tables row-hover']/tbody/tr")
	public List<WebElement> jobHistoryList;
	
	@FindBy(xpath = "//table[@class='component-data-tables row-hover']/tbody/tr/td/span")
	public List<WebElement> jobHistoryListData;
	
	@FindBy(xpath="//a[@class='close']")
	public WebElement close;
	
	/**
	 * @param driver The driver that will be used to look up the elements
	 */
	public DBXAutoActionPolicyPageObject(WebDriver driver){
		PageFactory.initElements(driver, this);
	}
}