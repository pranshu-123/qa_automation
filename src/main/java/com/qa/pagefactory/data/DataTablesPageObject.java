package com.qa.pagefactory.data;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class DataTablesPageObject {

    @FindBy(xpath = "//a[contains(@href,'data/overview')]/span[contains(text(),\"Data\")]")
    public WebElement dataTab;

    @FindBy(xpath = "//a[contains(@href,'data/tables')]/span[contains(text(),\"Tables\")]")
    public WebElement dataTablesTab;

    @FindBy(xpath = "(//label[contains(text(),'Workspace')]/following-sibling::span)[1]//span[contains(@class,'select2-selection__arrow')]")
    public WebElement workspaceDropdown;

    @FindBy(xpath = "(//label[contains(text(),'Metastore')]/following-sibling::span)[1]")
    public WebElement metastoreParentElement;

    @FindBy(xpath = "//li[contains(@class,'select2-results__option')]")
    public List<WebElement> dropdownValues;
    /**
     * @param driver The driver that will be used to look up the elements
     */

    public DataTablesPageObject(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }
}

