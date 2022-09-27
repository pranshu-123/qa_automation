package com.qa.pagefactory.emr.Cost;

import com.qa.enums.UserAction;
import com.qa.utils.actions.UserActions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class ChargebackPageObject {

    @FindBy(xpath = "(//ul[contains(@class,'sub-menu')]//span[text()='Chargeback'])")
    public WebElement costChargeBackTab;
    @FindBy(xpath = "(//ul[contains(@class,'sub-menu')]//span[text()='Trends'])")
    public WebElement costTrendsTab;

    @FindBy(xpath = "(//ul[contains(@class,'sub-menu')]//span[text()='Budget'])")
    public WebElement costBudgetTab;
    @FindBy(xpath = "//label//h5[contains(@class,'card-title')]")
    public List<WebElement> graphsHeader;
    @FindBy(xpath = "//div[@class='chart']")
    public List<WebElement> pieGraph;

    @FindBy(xpath = "//label//h5[contains(text(),'Cost')]")
    public WebElement cost;
    @FindBy(xpath = "//label//h5[contains(text(),'EC2')]")
    public WebElement ec2;
    @FindBy(xpath = "//label//h5[contains(text(),'EMR')]")
    public WebElement emr;
    @FindBy(xpath = "//label//h5[contains(text(),'EBS')]")
    public WebElement ebs;
    @FindBy(xpath = "//label//h5[contains(text(),'Cluster count')]")
    public WebElement clustercount ;

    @FindBy(xpath = "//div[@class='multiselect__select']")
    public WebElement ScopeDropdownButton ;

    @FindBy(xpath = "//div[@class=\"unravel-select select-chargeback-scope\"]/div/div[3]//li[@class=\"multiselect__element\"]")
    public List<WebElement> ScopeDropdownvalues ;

    @FindBy(xpath = "//div[@class='multiselect multiselect--active multiselect--above']//div[3]//span//span")
    public List<WebElement> CBbyTagKeyDdn ;
    @FindBy(xpath = "//div[@class='unravel-select select-chargeback-scope']/div/div[3]//li[2]")
    public WebElement selectTags ;

    @FindBy(xpath = "//div[@class='unravel-select select-chargeback-scope']/div/div[3]//li[1]")
    public WebElement selectGlobal ;
    @FindBy(xpath = "//div[@class='vue-treeselect__multi-value']")
    public WebElement MultiselectTags ;

    @FindBy(xpath = "//span[@class='multiselect__single']")
    public WebElement ScopeSelected ;
    @FindBy(xpath = "//label[@class='vue-treeselect__label']")
    public List<WebElement> MultiselectTagsValues ;

    @FindBy(xpath = "//div[@class='vue-treeselect__option' and @data-id='Tenure']//parent::div//div[@class='vue-treeselect__list']//div[@class='vue-treeselect__checkbox-container'])[2]")
    public WebElement MultiselectTagTenure ;
    @FindBy(xpath = "//div[@class='vue-treeselect__option' and @data-id='Purpose']//parent::div//div[@class='vue-treeselect__list']//div[@class='vue-treeselect__checkbox-container'])[2]")
    public WebElement MultiselectTagPurpose ;
    @FindBy(xpath = "//div[@class='vue-treeselect__option' and @data-id='Name']//parent::div//div[@class='vue-treeselect__list']//div[@class='vue-treeselect__checkbox-container'])[2]")
    public WebElement MultiselectTagName ;

    @FindBy(xpath = "(//div[@class='multiselect__select'])[2]")
    public WebElement selectCBbyTagKey ;

    @FindBy(xpath = "//span[text()='Tenure']")
    public WebElement selectCBbyTenure ;
    @FindBy(xpath = "//span[text()='Purpose']")
    public WebElement selectCBbyPurpose ;

    @FindBy(xpath = "//span[text()='Name']")
    public WebElement selectCBbyName ;

    @FindBy(xpath ="//div[@class='perma-links']//a")
    public WebElement viewTrends ;

    @FindBy(xpath ="(//div[@class='perma-links']//a)[2]")
    public WebElement viewClusters ;

    @FindBy(xpath ="//div[@id='count']")
    public WebElement viewTrendsCount ;

    @FindBy(xpath ="//div[@id='cost']")
    public WebElement viewTrendsCost ;

    @FindBy(xpath ="//div[@class='table-wrapper']")
    public WebElement viewClusterValidation ;
    @FindBy(xpath ="//span[@class='mutated']")
    public WebElement viewClusterHeader ;

    @FindBy(xpath ="//span[@class='menu-icon']")
    public WebElement ActionsButton ;

    @FindBy(xpath ="//a[@class='menu-item-text']")
    public WebElement CreateBudget ;

    @FindBy(xpath ="//h2[text()='Create budget']")
    public WebElement CreateBudgetHeader1 ;

    @FindBy(xpath ="//p[text()='Monitor your costs when your budget limit is met.']")
    public WebElement CreateBudgetHeader2 ;

    @FindBy(xpath ="//h5[@class='title']")
    public List<WebElement> TitleList ;
    public ChargebackPageObject(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }
}
