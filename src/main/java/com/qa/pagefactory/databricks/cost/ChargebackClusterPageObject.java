package com.qa.pagefactory.databricks.cost;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ChargebackClusterPageObject {

    @FindBy(xpath = "")
    public WebElement costChargebackTab;


    /**
     * @param driver The driver that will be used to look up the elements
     */
    public ChargebackClusterPageObject(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }
}

