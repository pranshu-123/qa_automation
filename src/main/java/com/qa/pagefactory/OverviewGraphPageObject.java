package com.qa.pagefactory;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * @author Ankur Jaiswal
 * All WebElement which is related with graphs
 * is present in this class. Wherever you need to access these page object
 * create an instance of this class and access the members with that object.
 */

public class OverviewGraphPageObject {

    @FindBy(xpath = "//*[@id=\"MemryTSOverview\"]/span/div/div")
    public WebElement memoryGraph;

    @FindBy(xpath = "//span[@id=\"MemryTSOverview\"]/following-sibling::div//span[contains(text(),'Total Available')]/following-sibling::input")
    public WebElement memoryTotalAvailableCheckbox;

    @FindBy(xpath = "//span[@id=\"MemryTSOverview\"]/following-sibling::div//span[contains(text(),'Total Allocated')]/following-sibling::input")
    public WebElement memoryTotalAllocatedCheckbox;

    @FindBy(xpath = "//*[@id=\"app\"]/div/div/div[1]/div[2]/div/div[1]/div/div/div[1]/div/div[2]")
    public WebElement nodeGraph;

    @FindBy(xpath = "//*[@id=\"app\"]/div/div/div[1]/div[2]/div/div[1]/div/div/div[1]/div/div[3]/label[1]")
    public WebElement nodeActiveChkBoxText;

    @FindBy(xpath = "//*[@id=\"app\"]/div/div/div[1]/div[2]/div/div[1]/div/div/div[1]/div/div[3]/label[2]")
    public WebElement nodebBadChkBoxText;

    @FindBy(xpath = "//*[@id='VCoreTSOverview']/span/div/div")
    public WebElement vcoreGraph;

    @FindBy(xpath = "//*[@id=\"VCoreTSOverview\"]/div[2]/div/label[1]")
    public WebElement VcoreTotalAvailableCheckbox;

    @FindBy(xpath = "//*[@id=\"VCoreTSOverview\"]/div[2]/div/label[2]")
    public WebElement VcoreTotalAllocatedCheckbox;

    @FindBy(xpath = "//div[@id='JobsFinishedByStatus']/div[2]")
    public WebElement statusGraph;

    @FindBy(xpath = "//*[@id='JobByStatus']/div[2]")
    public WebElement runningGraph;

    @FindBy(xpath = "//div[@id='JobByStatus']//label[1]/input")
    public WebElement runningChkBoxText;

    @FindBy(xpath = "//div[@id='JobByStatus']//label[2]/input")
    public WebElement acceptedChkBoxText;

    @FindBy(xpath = "//div[@class=\"header\"]/h4[contains(text(),'Inefficient Events')]/parent::div/following-sibling::div[1]/div")
    public WebElement inefficientEventsGraph;

    public By graphGContents = By.cssSelector("svg > g.highcharts-series-group");

    /**
     * @param driver The driver that will be used to look up the elements
     */
    public OverviewGraphPageObject(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

}
