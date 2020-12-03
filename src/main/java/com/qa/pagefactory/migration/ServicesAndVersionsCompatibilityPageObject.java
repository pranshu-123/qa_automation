package com.qa.pagefactory.migration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

/**
 * @author Birender Kumar All 'Services And Versions Compatibility' tab of Migration related webelements of unravel ui
 * is present in this class. Wherever you need to access these page object create an instance of this class and access
 * the members with that object.
 */

public class ServicesAndVersionsCompatibilityPageObject {

    @FindBy(xpath = "//a[@class='icon-x']")
    public WebElement closeMsgBanner;

    @FindBy(xpath = "//span[contains(text(), 'Run New')]//parent::a")
    public WebElement runNewBtn;

    @FindBy(xpath = "//span[@class='select2-selection__arrow']")
    public WebElement cloudProductDropDown;

    @FindBy(xpath ="//li[contains(@class,'select2-results__option')]")
    public List<WebElement> cloudProductList;

    @FindBy(xpath="//span[contains(@class, 'select2-search--dropdown')]/input")
    public WebElement cloudProductSearchBox;

    @FindBy(xpath="//span[@class='select2-results']//li")
    public WebElement cloudProductSearchFirstField;

    @FindBy(xpath = "//div[contains(@class,'component-section-header')]/span/a")
    public WebElement runModalBtn;


    /**
     * @param driver The driver that will be used to look up the elements
     */
    public ServicesAndVersionsCompatibilityPageObject(WebDriver driver) {
        PageFactory.initElements(driver,this);
    }
}
