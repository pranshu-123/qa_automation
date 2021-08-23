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

    @FindBy(xpath = "//div[contains(@class,'auto-action-head')]/div")
    public WebElement closeNewAutoAction;

    @FindBy(xpath = "(//div/div/label)[1]/following-sibling::input")
    public WebElement policyName;

    //@FindBy(xpath = "//div[contains(@class, 'rule-row')]/div[2]/input")
    @FindBy(xpath = "//div/div[2]/input")
    public WebElement policyDescription;

    //@FindBy (xpath = "(//div[contains(@class,'rule-row')])[2]/div/div/b/span[1]")
    @FindBy (xpath = "//button[contains(@id,'trigger-dropdown')]//span[2]")
    public WebElement triggerConditionsBtn;

    @FindBy(xpath="//div[@class='container-fluid']/div[5]/div/div/b/span[1]")
    public WebElement triggerConditionsBtn2;

    @FindBy(xpath="//div[contains(@class,'join-row')]/div/div/b/span[1]")
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

    //@FindBy (xpath = "(//div[contains(@class,'auto-footer')]/div/div/span/a)[1]")
    @FindBy (xpath = "//button[contains(text(),'Save')]")
    public WebElement saveBtn;

    @FindBy(xpath = "//div[@class='container-fluid']/section/span")
    public WebElement messageBanner;

    @FindBy(xpath = "//div[@class='component-activity-feed']/div/div[2]")
    public WebElement iconX_RefineScope;

    @FindBy(xpath = "(//div[contains(@class,'autoaction-icon-drop')])[2]/b/span[1]")
    public WebElement refineScopeBtn;

    @FindBy(xpath = "(//div[contains(@class,'dropdown-content')])[2]/a")
    public List<WebElement> selectRefineScopeList;

    @FindBy(xpath = "//div[contains(@class,'mode-div')]/div/div/span[text()='all']/parent::div/preceding-sibling::div/label/span")
    public WebElement allUserChkBox;

    @FindBy(xpath = "//div[contains(@class,'mode-div')]/div/div/span[text()='only']/parent::div/preceding-sibling::div/label/span")
    public  WebElement onlyUserChkBox;

    @FindBy(xpath = "//div[contains(@class,'mode-div')]/div/div/span[text()='except']/parent::div/preceding-sibling::div/label/span")
    public WebElement exceptUserChkBox;

    @FindBy(xpath = "//div[contains(@class, 'mode-div')]/div/div/span[text()='always']/parent::div/preceding-sibling::div/label/span")
    public WebElement alwaysChkBox;

    @FindBy(xpath = "//div[contains(@class, 'mode-div')]/div/div/span[text()='daily']/parent::div/preceding-sibling::div/label/span")
    public WebElement dailyChkBox;

    @FindBy(xpath = "(//select[contains(@class, 'component-multi-select')])[1]")
    public WebElement selectAppType;

    @FindBy(xpath = "(//select[contains(@class, 'component-multi-select')])[2]")
    public WebElement selectMetric;

    @FindBy(xpath = "(//div[contains(@class,'autoaction-action-div')])/div/div/b/span[2]")
    public WebElement actionButton;

    @FindBy(xpath = "(//div[contains(@class,'autoaction-action-div')])/div/div/div/a")
    public List<WebElement> listOfActions;

    @FindBy(xpath = "(//span[contains(@class, 'select2-selection')])[4]/span[2]")
    public WebElement metricCombo;

    @FindBy(xpath = "//span[contains(@class, 'select2-dropdown')]/span[1]/input")
    public WebElement metricComboSearch;

    @FindBy(xpath = "//span[contains(@class, 'select2-dropdown')]/span[2]/ul/li")
    public List<WebElement> listOfMetric;

    @FindBy(xpath="//input[contains(@class, 'input-sm')]")
    public WebElement actionEle;

    @FindBy(xpath="//input[contains(@class, 'input-sm') and @placeholder='Add Webhook Url']")
    public WebElement actionElePostToSlack;

    @FindBy(xpath="//a/span[text()='Add Email']")
    public WebElement addEmailEleBtn;

    @FindBy(xpath="//a/span[text()='Add Url']")
    public WebElement addUrlBtn;

    @FindBy(xpath="//a/span[text()='Add Webhook Url']")
    public WebElement addWebhookUrlBtn;

    @FindBy(xpath="//div[@class='token-div']/input[@placeholder='Token']")
    public WebElement webHookTokenEle;

    @FindBy(xpath = "//input[@placeholder='Queue Name']")
    public WebElement queueNameEle;

    @FindBy(xpath="//div[@id='killapp-div']/label[@class='checkbox']")
    public WebElement killAppChkBoxEle;

    @FindBy(xpath="//section[contains(@class,'bg-fatal')]/span")
    public WebElement fatalMsgTextEle;
    /**
     * @param driver The driver that will be used to look up the elements
     */
    public NewAutoActionPolicyPageObject(WebDriver driver){
        PageFactory.initElements(driver, this);
    }
}