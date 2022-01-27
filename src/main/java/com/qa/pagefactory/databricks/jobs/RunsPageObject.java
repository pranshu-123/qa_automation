package com.qa.pagefactory.databricks.jobs;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class RunsPageObject {

    
    /**
     * @param driver The driver that will be used to look up the elements
     */
    public RunsPageObject(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }
}

