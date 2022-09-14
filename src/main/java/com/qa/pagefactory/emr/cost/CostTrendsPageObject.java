package com.qa.pagefactory.emr.cost;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CostTrendsPageObject {

    @FindBy(xpath = "//button[contains(text(), 'Add')]")
    public WebElement add;
    
    @FindBy(xpath = "//a[contains(text(), 'View cluster')]")
    public WebElement viewCluster;
    
    @FindBy(xpath = "//a[contains(text(), 'View chargeback')]")
    public WebElement viewChargeback;
    
    @FindBy(xpath = "//a[contains(text(), 'View existing budget')]")
    public WebElement viewExistingBudget;
    
    @FindBy(xpath = "//span[@class='multiselect__single']/span[contains(text(), 'Global')]")
    public WebElement scopeList;
    
    @FindBy(xpath = "//span[@class='multiselect__option']/span[contains(text(), 'Global')]")
    public WebElement globalList;
    
    @FindBy(xpath = "//span[@class='multiselect__single']/span[contains(text(), 'Tags')]")
    public WebElement tagsList;
    
    @FindBy(xpath = "//span[@class='multiselect__single']/span[contains(text(), 'cluster')]")
    public WebElement clusterList;
    
    @FindBy(xpath = "//div[contains(text(), 'Select up to 5 tags.')]")
    public WebElement tagSelection;
    
    
	/**
     * @param driver The driver that will be used to look up the elements
     */
    public CostTrendsPageObject(WebDriver driver) {
        PageFactory.initElements(driver, this);

    }
}
