package com.qa.pagefactory.appsDetailsPage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

/**
 * @author Ankur Jaiswal
 */
public class AppDetailsPageObject {

    @FindBy(xpath = "//h3[contains(@class,'expandable-header')]/following-sibling::div[contains(@class,'filter-items')]//label")
    public List<WebElement> applicationTypeLabels;

    @FindBy(xpath = "//h3[contains(@class,'expandable-header')]/following-sibling::div[contains(@class,'filter-items')]//label//span[contains(@class,'show-only')]")
    public List<WebElement> applicationTypeShowOnly;

    @FindBy(id="allApps")
    public WebElement allAppsTable;

    @FindBy(xpath = "//div[contains(@class,'close')]")
    public WebElement closeModalbutton;

    @FindBy(xpath = "//h2/span[2]/span/span")
    public WebElement jobUUID;

    @FindBy(xpath = "(//h2/span)[1]")
    public WebElement appTypeAppDetails;

    @FindBy(xpath = "//h4[contains(@class,'job-times')]/span[1]")
    public WebElement jobStartTime;

    @FindBy(xpath = "//h4[contains(@class,'job-times')]/span[2]")
    public WebElement jobEndTime;

    @FindBy(xpath = "//h4[contains(@class,'job-times')]/span[3]")
    public WebElement jobDuration;

    @FindBy(xpath = "//h5[contains(text(),'DATA I/O')]/following-sibling::div")
    public WebElement jobReadWriteData;

    @FindBy(xpath = "//div[@id=\"statusContainer\"]/span[1]")
    public WebElement jobStatus;

    @FindBy(id = "appNavigation")
    public WebElement impalaFragmentTable;

    @FindBy(xpath = "//table[@id='appNavigation']/thead/tr/th")
    public List<WebElement> impalaFragmentTableHeadings;

    @FindBy(xpath = "//table[@id='appNavigation']/tbody/tr")
    public List<WebElement> impalaFragmentRows;

    @FindBy(xpath = "//div[contains(@class,'opnode')]")
    public List<WebElement> impalaQueryPlanComponents;

    @FindBy(xpath = "//a[@content=\"Instance View\"]")
    public WebElement impalaIFragmentnstanceView;

    @FindBy(xpath = "//table[@id='impalaOpInstanceTable']/tbody/tr")
    public List<WebElement> impalaFragmentInstanceViewsRows;

    @FindBy(xpath = "//a[contains(text(),'Operators')]")
    public WebElement operatorsTabImpala;

    @FindBy(xpath = "//a[contains(text(),'Gantt Chart')]")
    public WebElement ganttChartTabImpala;

    @FindBy(xpath = "//div[contains(@class,'gantt-timeline')]/div[1]")
    public List<WebElement> ganttChartIds;

    /**
     * @param driver The driver that will be used to look up the elements
     */
    public AppDetailsPageObject(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }
}
