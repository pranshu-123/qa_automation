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

    @FindBy(xpath = "//span[contains(text(), 'Run')]//parent::a")
    public WebElement runBtn;

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

    @FindBy(xpath = "//section[contains(@class, 'component-message-banner')]/div")
    public WebElement confirmationMessageElement;

    @FindBy(xpath = "//span[@class='select2-results']/ul/li[contains(text(), 'No results found')]")
    public WebElement noCloudProductResult;

    @FindBy(xpath = "//h3/b")
    public List<WebElement> latestReportList;

    @FindBy(xpath = "//div[@class='version-comp-inner']/div[2]/div/p[contains(@class, 'pd')]")
    public List<WebElement> legendList;

    @FindBy(xpath = "(//table[contains(@class, 'component-data-tables')])[2]")
    public WebElement reportTable;

    @FindBy(xpath = "//div[@class='ab']/div")
    public List<WebElement> platformList;

    @FindBy(xpath = "(//thead/tr)[2]/th")
    public List<WebElement> hdpHeaderList;

    @FindBy(xpath = "//tbody/tr")
    public List<WebElement> rowsList;

    @FindBy(xpath = "//tbody/tr[1]/td")
    public List<WebElement> colList;

    @FindBy(xpath = "//div[@id='breadcrumb']/span[2]/span")
    public WebElement archiveReportSVCHeader;

    /**
     * @param driver The driver that will be used to look up the elements
     */
    public ServicesAndVersionsCompatibilityPageObject(WebDriver driver) {
        PageFactory.initElements(driver,this);
    }
}
