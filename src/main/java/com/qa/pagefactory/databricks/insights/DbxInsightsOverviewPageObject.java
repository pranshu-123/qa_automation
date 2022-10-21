package com.qa.pagefactory.databricks.insights;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class DbxInsightsOverviewPageObject {

    @FindBy(xpath = "//a[@href='#/value_dashboard']//span")
    public WebElement insightsTab;

    @FindBy(xpath = "//span[text()='Cost']")
    public WebElement cost;

    @FindBy(xpath = "//span[text()='Resource Efficiency']")
    public WebElement resourceEfficiency;

    @FindBy(xpath = "//span[text()='App Acceleration']")
    public WebElement appAcceleration;

    @FindBy(xpath = "//div[@class='vd-category-container cost-container vd-card']//div[@class='vd-category-content']")
    public WebElement costCategory;

    @FindBy(xpath = "//div[contains(@class,'vd-category-container resource-efficiency-container')]")
    public WebElement resourceEfficiencyCategory;

    @FindBy(xpath = "//div[@class='vd-category-container app-speed-up-container vd-card']//div[@class='vd-category-content']")
    public WebElement appAccelerationCategory;

    /**
     * @param driver The driver that will be used to look up the elements
     */
    public DbxInsightsOverviewPageObject(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }
}
