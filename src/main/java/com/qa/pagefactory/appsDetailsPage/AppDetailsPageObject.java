package com.qa.pagefactory.appsDetailsPage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

/**
 * @author Ankur Jaiswal
 */
public class AppDetailsPageObject {

    @FindBy(xpath = "//h3[contains(@class,'expandable-header')]/following-sibling::div[contains(@class,'filter-items')]//label")
    public List<WebElement> applicationTypeLabels;

    @FindBy(xpath = "//h3[contains(@class,'expandable-header')]/following-sibling::div[contains(@class,'filter-items')]//label//span[contains(@class,'show-only')]")
    public List<WebElement> applicationTypeShowOnly;

    @FindBy(id="allApps")
    public WebElement allAppsTable;

    /**
     * @param driver The driver that will be used to look up the elements
     */
    public AppDetailsPageObject(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }
}
