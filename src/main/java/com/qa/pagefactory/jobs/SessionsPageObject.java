package com.qa.pagefactory.jobs;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SessionsPageObject {

    @FindBy(xpath = "//span[@id='reset']")
    public WebElement resetButton;

    @FindBy(css = "input.global-app-search")
    public WebElement globalSearch;

    @FindBy(xpath = "//h3[starts-with(normalize-space(text()),'No Data Available')]")
    public WebElement noDataForWorkflows;
    /**
     * @param driver The driver that will be used to look up the elements
     */
    public SessionsPageObject(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

}

