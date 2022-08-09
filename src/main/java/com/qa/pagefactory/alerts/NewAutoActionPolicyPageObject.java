package com.qa.pagefactory.alerts;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

/**
 * @author Birender Kumar All New Auto Action Policy related webelements of unravel ui is present in
 *          this class. Wherever you need to access these page object create an
 *          instance of this class and access the members with that object.
 */
public class NewAutoActionPolicyPageObject {

	@FindBy(xpath="//div[contains(@class,'auto-action-head')]/h2")
	public WebElement newAutoActionPolicyHeader;

	@FindBy(xpath = "//button[contains(@class,'close')]")
	public WebElement closeNewAutoAction;

	@FindBy(xpath = "(//div/div/label)[1]/following-sibling::input")
	public WebElement policyName;

	@FindBy(xpath = "//div/div[2]/input")
	public WebElement policyDescription;

	@FindBy (xpath = "//button[contains(@id,'trigger-dropdown')]//span[2]")
	public WebElement triggerConditionsBtn;

	@FindBy (xpath = "//button[contains(@id,'trigger-dropdown')]//span[2]")
	public WebElement triggerConditionsBtn2;

	@FindBy(xpath="//button[contains(@id,'0')]")
	public WebElement joinTxtBtn;

	@FindBy (xpath = "(//div[contains(@class,'dropdown-content')]/a)[1]")
	public WebElement usertriggerConditions;

	@FindBy (xpath = "(//div[contains(@class,'dropdown-content')])[1]/a")
	public List<WebElement> selectTriggerConditions;

	@FindBy(xpath = "//div[contains(@class,'join-row')]/div/div/div/a")
	public List<WebElement> getSelectORANDOptions;

	@FindBy (xpath="//li[contains(@class,'rule-li')]/div/div/h4")
	public WebElement selectedTriggerConditions;

	@FindBy (xpath = "//li[contains(@class,'rule-li')]/div/div/h4/parent::div/following-sibling::div")
	public WebElement cancelSelectedTriggerConditions;

	@FindBy (xpath = "(//li[contains(@class,'rule-li')]/div)[3]/div[4]/input")
	public WebElement triggerConditionValue;

	@FindBy(xpath="(//li[contains(@class,'rule-li')])[2]/div[3]/div[4]/input")
	public WebElement triggerConditionValue2;

	@FindBy (xpath = "//button[contains(text(),'Save')]")
	public WebElement saveBtn;

	@FindBy(xpath = "//section[contains(@class,'component-message-banner')]//div")
	public WebElement messageBanner;

	@FindBy(xpath = "//div[@class='component-activity-feed']/div/div[2]")
	public WebElement iconX_RefineScope;

	@FindBy(xpath = "//button[contains(@id,'scope-dropdown')]")
	public WebElement refineScopeBtn;

	@FindBy(xpath = "//button[@id='scope-dropdown']//div//a")
	public List<WebElement> selectRefineScopeList;

	public String allUserChkBox =  "//h4[contains(text(),'%s')]/../following-sibling::div//label[contains(text(),'all')]";

	public String onlyUserChkBox =  "//h4[contains(text(),'%s')]/../following-sibling::div//label[contains(text(),'only')]";

	public String exceptUserChkBox =  "//h4[contains(text(),'%s')]/../following-sibling::div//label[contains(text(),'except')]";

	public String alwaysChkBox =  "//h4[contains(text(),'%s')]/../following-sibling::div//label[contains(text(),'always')]";

	public String dailyChkBox =  "//h4[contains(text(),'%s')]/../following-sibling::div//label[contains(text(),'daily')]";

	@FindBy(xpath = "(//select[contains(@class, 'component-multi-select')])[1]")
	public WebElement selectAppType;

	@FindBy(xpath = "(//select[contains(@class, 'component-multi-select')])[2]")
	public WebElement selectMetric;

	@FindBy(xpath = "//button[contains(@id,'action-dropdown')]")
	public WebElement actionButton;

	@FindBy(xpath = "//button[contains(@id,'action-dropdown')]")
	public List<WebElement> listOfScopeActions;

	@FindBy(xpath = "//button[contains(@id,'action-dropdown')]//div//a")
	public List<WebElement> listOfActions;

	@FindBy(xpath = "(//span[contains(@class, 'select2-selection')])[4]/span[2]")
	public WebElement metricCombo;

	@FindBy(xpath = "//span[contains(@class, 'select2-dropdown')]/span[1]/input")
	public WebElement metricComboSearch;

	@FindBy(xpath = "//span[contains(@class, 'select2-dropdown')]/span[2]/ul/li")
	public List<WebElement> listOfMetric;

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
	/**
	 * @param driver The driver that will be used to look up the elements
	 */
	public NewAutoActionPolicyPageObject(WebDriver driver){
		PageFactory.initElements(driver, this);
	}
}