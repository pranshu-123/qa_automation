package com.qa.pagefactory.appsDetailsPage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class TezAppsDetailsPageObject {

    @FindBy(xpath = "(//tbody[@id = 'allApps-body']/tr/td)[4]/div")
    public WebElement getAppId;

    @FindBy(xpath = "(//thead[@id='sparkStageNavigation-head'])/tr/th")
    public List<WebElement> stageHeaders;

    @FindBy(xpath = "/html/body/div[1]/div/div/div/div[3]/div/div[2]/div[2]/div[2]/div/div/table/tbody/tr[1]/td[1]")
    public List<WebElement> tezTableRecords;

    @FindBy(xpath = "/html/body/div[1]/div/div/div/div[3]/div/div[2]/div[2]/div[2]")
    public WebElement tezTableRec;

    @FindBy(xpath = "//tbody[@id=\"allApps-body\"]")
    public WebElement tblTezApps;

    @FindBy(xpath = "//tbody[@id='allApps-body']/tr")
    public WebElement tblTezAppsRowsList;

    @FindBy(xpath="//*[@id=\"allApps-headerspan-0\"]")
    public List<WebElement> resultsGroupedByTableHeaderNames;

    @FindBy(xpath="//*[@id=\"allApps-body\"]/tr[1]/td[1]")
    public WebElement getType;

    @FindBy(xpath="//*[@id=\"allApps-body\"]/tr[1]/td[2]")
    public  WebElement getStatus;

    @FindBy(xpath="//*[@id=\"allApps-body\"]/tr[1]/td[3]")
    public  WebElement getUsername;

    @FindBy(xpath="//*[@id=\"allApps-body\"]/tr[1]/td[4]/span[1]")
    public  WebElement getAppname;

    @FindBy(xpath="//*[@id=\"allApps-body\"]/tr[1]/td[4]/div")
    public  WebElement getAppid;


    @FindBy(xpath = "//*[@id='topContainer-appConfiguration']/form/div[1]/p")
    public WebElement configPropNum;

    @FindBy(xpath = "(//div[@id=\"app\"])/div/div/div[1]/h2/span[2]/span/span")
    public WebElement getHeaderAppId;

    @FindBy(xpath = "(//div[@id=\"app\"])/div/div/div[2]/div[1]/div/div[2]/div/ul/li")
    public List<WebElement> component_element;

    @FindBy(xpath = "(//tbody[@id='appNavigation-body'])/tr")
    public List<WebElement> navigationTableRows;

    @FindBy(xpath = "(//thead[@id='appNavigation-head'])/tr/th")
    public List<WebElement> navigationHeaders;

    @FindBy(xpath = "(//div[@class='timeline gantt-timeline pipeline row no-gutters'])")
    public List<WebElement> ganttChartTable;

    @FindBy(xpath = "(//div[@id='app'])/div/div/div[2]/div[1]/div/div[2]/div/div/div/div[1]/div/p/b")
    public List<WebElement> ganttChartHeaders;

    // (//div[contains(@class, 'gantt-timeline')])//div/p
    @FindBy(xpath = "(//div[@class='timeline gantt-timeline pipeline row no-gutters'])//div/p")
    public List<WebElement> ganttChartDuration;

    @FindBy(xpath = "(//div[@class='timeline gantt-timeline pipeline row no-gutters'])//span[1]")
    public List<WebElement> ganttChartJobId;

    @FindBy(xpath = "(//div[@class='timeline gantt-timeline pipeline row no-gutters'])//span[2]")
    public List<WebElement> ganttChartStartTime;

    @FindBy(xpath = "//*[@id='app']/div/div/div[1]/div[2]")
    public WebElement closeAppsPageTab;

    //Executor Tab xpath
    @FindBy(xpath = "//*[@id=\"dagContainer\"]/*[name()='g']/*[name()='g']/*[name()='g']/*[name()='g']/*[name()='g']/*[name()='g']")
    public List<WebElement> rddBlockList;

    @FindBy(xpath = "//*[@id=\"spark_exec_plan\"]/*[name()='svg']")
    public WebElement DAGData;


    /**
     * @param driver The driver that will be used to look up the elements
     */
    public TezAppsDetailsPageObject(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }
}

