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

    @FindBy(xpath = "(//div[contains(@class,'rule-row')][1]/div/label)[1]/following-sibling::input")
    public WebElement policyName;

    @FindBy(xpath = "//div[contains(@class, 'rule-row')]/div[2]/input")
    public WebElement policyDescription;

    @FindBy (xpath = "(//div[contains(@class,'rule-row')])[2]/div/div/b/span[1]")
    public WebElement triggerConditionsBtn;

    @FindBy (xpath = "(//div[contains(@class,'dropdown-content')]/a)[1]")
    public WebElement usertriggerConditions;

    @FindBy (xpath = "(//div[contains(@class,'dropdown-content')])[1]/a")
    public List<WebElement> selectTriggerConditions;

    @FindBy (xpath="//li[contains(@class,'rule-li')]/div/div/h4")
    public WebElement selectedTriggerConditions;

    @FindBy (xpath = "//li[contains(@class,'rule-li')]/div/div/h4/parent::div/following-sibling::div")
    public WebElement cancelSelectedTriggerConditions;

    @FindBy (xpath = "(//li[contains(@class,'rule-li')]/div)[3]/div[4]/input")
    public WebElement triggerConditionValue;

    @FindBy (xpath = "(//div[contains(@class,'auto-footer')]/div/div/span/a)[1]")
    public WebElement saveBtn;

    @FindBy(xpath = "//div[@class='container-fluid']/section/span")
    public WebElement messageBanner;

    /**
     * @param driver The driver that will be used to look up the elements
     */
    public NewAutoActionPolicyPageObject(WebDriver driver){
        PageFactory.initElements(driver, this);
    }
}
