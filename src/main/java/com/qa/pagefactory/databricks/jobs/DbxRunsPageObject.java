package com.qa.pagefactory.databricks.jobs;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class DbxRunsPageObject {

    @FindBy(css = "tbody#RunsListAll-body")
    public WebElement whenApplicationPresent;

    @FindBy(xpath = "//a[contains(.,'Status')]//following::div[contains(@class,'check-items-container')]//span[@class='checkmark']")
    public List<WebElement> selectSingleStatusType;

    @FindBy(xpath = "(//div[contains(@class,'check-items-container')])[1]//span[1]")
    public List<WebElement> getApplicationTypes;

    @FindBy(css = ".col-md-12.no-data-msg")
    public WebElement whenNoApplicationPresent;

    @FindBy(xpath = "//div[contains(@class,'ranges')]//li")
    public List<WebElement> dateRanges;

    @FindBy(css = "span#reset")
    public WebElement resetButton;

    @FindBy(xpath = "//tbody[@id = 'allApps-body']/tr/td[6]")
    public List<WebElement> getApplicationClusterId;

    /**
     * @param driver The driver that will be used to look up the elements
     */
    public DbxRunsPageObject(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }
}

