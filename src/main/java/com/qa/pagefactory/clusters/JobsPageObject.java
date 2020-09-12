package com.qa.pagefactory.clusters;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class JobsPageObject {

    @FindBy(css = "div#highcharts-bpp6qdh-101.highcharts-container svg.highcharts-root g.highcharts-label.highcharts-no-data text tspan")
    public WebElement jobsNoDataAvailableText;

    @FindBy(xpath = "//div/table[@id='chargeback-table']/tbody/tr/td//span")
    public WebElement selectgroup;

    @FindBy(xpath = "//div/table[@id='chargeback-table']/tbody/tr/td//span")
    public WebElement selectQueue;

    @FindBy(xpath = "//div/table[@id='chargeback-table']/tbody/tr/td//span")
    public WebElement selectdept;

    @FindBy(xpath = "//li[contains(@class,'active')]/ul[contains(@class,'sub-menu')]//li/span[contains(text(),'Jobs')]")
    public WebElement jobsTab;

    @FindBy(xpath = "//div[contains(@class,'component-section-header')]/h1[text()='Jobs']")
    public WebElement getjobsPageHeader;

    @FindBy(xpath = "//label[contains(text(), 'Group By')]/following-sibling::span/span/span/span[contains(@class,'select2-selection__arrow')]")
    public WebElement groupByDropdownButton;

    @FindBy(xpath = "//li[contains(text(),'State')]")
    public WebElement groupByState;

    @FindBy(xpath = "//li[contains(text(),'User')]")
    public WebElement groupByUser;

    @FindBy(xpath = "//li[contains(text(),'Application Type')]")
    public WebElement groupByAppType;

    @FindBy(xpath = "//li[contains(text(),'Queue')]")
    public WebElement groupByQueueList;

    @FindBy(xpath = "//span[contains(@class,'select2-results')]/ul/li")
    public List<WebElement> filterElements;

    public JobsPageObject(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }
}
