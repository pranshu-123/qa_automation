package com.qa.pagefactory.alerts;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

/**
 * @author Birender Kumar All Auto Actions related webelements of unravel ui is present in
 *          this class. Wherever you need to access these page object create an
 *          instance of this class and access the members with that object.
 */

public class AutoActionsPageObject {

    @FindBy(xpath = "//span[contains(@class,'icon-alerts')]/parent::h4/following-sibling::ul/li/span")
    public WebElement autoActionsHeaderLink;

    @FindBy(xpath = "//div[contains(@class,'component-section-header')]/h1")
    public  WebElement autoActionComponentHeader;

    @FindBy(xpath = "//div[contains(@class,'component-section-header')]/div/span[2]/a")
    public WebElement newAutoActionBtn;

    @FindBy (xpath = "//table[@id = 'aa-list-table']/tbody/tr/td[1]")
    public List<WebElement> firstColumnElementsAATable;

    @FindBy (xpath = "//div[@class='container-fluid']/section/span")
    public WebElement messageBanner;
    /**
     * @param driver The driver that will be used to look up the elements
     */
    public AutoActionsPageObject(WebDriver driver){
        PageFactory.initElements(driver, this);
    }
}
