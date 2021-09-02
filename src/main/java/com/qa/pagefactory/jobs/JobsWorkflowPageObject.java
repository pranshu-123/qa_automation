package com.qa.pagefactory.jobs;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class JobsWorkflowPageObject {

    @FindBy(xpath = "//div[contains(@class,'component-section-header')]/h2[text()='Pipelines']")
    public WebElement pipelineHeader;

    @FindBy(css = "div#app>div>div>div:nth-of-type(2)>div:nth-of-type(3)>div>input")
    public WebElement searchBoxWorkflow;

    @FindBy(id = "pipelinesList-body")
    public List<WebElement> pipelineTablePresent;

    @FindBy(xpath = "((//tbody[@id='pipelinesList-body']/tr)[1]/td)")
    public List<WebElement> getPipelinesOfSearchedName;

    @FindBy(xpath = "//span[contains(@class,'icon-flipped')]")
    public WebElement flippedButton;

    @FindBy(xpath = "//div[contains(@class,'gantt-parent')]//span[contains(@class,'icon-caret')]")
    public WebElement oozienodeGanntChartDropdown;

    @FindBy(xpath = "//div[contains(@class,'gantt-parent')]//div[contains(@id,'gantt-container-job')]//span[contains(@content,'job')]")
    public List<WebElement> getJobId;

    @FindBy(xpath = "(//div[contains(@class,'gantt-parent')]//div[contains(@id,'gantt-container')]//span)[1]")
    public WebElement getParentJobId;

    @FindBy(className = "close")
    public WebElement close;

    @FindBy(xpath = "//span[contains(@class,'icon-jobs')]")
    public WebElement jobsTab;

    @FindBy(xpath = "//ul[@class='sub-menu']//span[text()='Applications']")
    public WebElement applicationTab;

    @FindBy(id = "apps-global-search-filter")
    public WebElement globalSearch;

    @FindBy(xpath = "//div[contains(@class,'component-section-header')]/h1[text()='Jobs']")
    public WebElement jobsHeading;

    @FindBy(xpath = "//tbody[@id='allApps-body']/tr/td[contains(text(),'MR')]//following::td[11]//span[text()='WFI']")
    public List<WebElement> WFILink;

    @FindBy(xpath = "//tbody[@id='allApps-body']/tr/td[contains(text(),'')]//following::td[11]//span[text()='WFI']")
    public List<WebElement> genericWFILink;

    @FindBy(xpath = "//tbody[@id='allApps-body']/tr/td[contains(text(),'')]//following::td[11]//span[text()='Hive']")
    public List<WebElement> genericHiveLink;

    @FindBy(xpath = "//tbody[@id='allApps-body']/tr/td[contains(text(),'MR')]//following::td[11]//span[text()='Hive']")
    public List<WebElement> HiveLink;

    @FindBy(xpath = "//tbody[@id='allApps-body']/tr/td[contains(text(),'MR')]//following::td[11]//span[text()='WFI']")
    public List<WebElement> HiveMRWFILink;

    @FindBy(xpath = "//tbody[@id='allApps-body']/tr/td[contains(text(),'impala')]//following::td[11]//span[text()='WFI']")
    public List<WebElement> ImpalaWFILink;

    @FindBy(xpath = "//tbody[@id='allApps-body']/tr/td[contains(text(),'spark')]//following::td[11]//span[text()='WFI']")
    public List<WebElement> SparkWFILink;

    @FindBy(xpath = "//div[@id='pipeline-instance-header']/h2/span")
    public WebElement getWorkflowHeaderID;

    @FindBy(xpath = "//h3[starts-with(normalize-space(text()),'No Data Available')]")
    public WebElement noDataForWorkflows;

    @FindBy(xpath = "//div[@id='statusContainer']/span[contains(@class,'success')]")
    public List<WebElement> successBadge;

    @FindBy(xpath = "//div[@id='statusContainer']/span[contains(@class,'unknown')]")
    public List<WebElement> unknownBadge;

    @FindBy(xpath = "//table[@id='pipelinesList']/tbody[1]/tr[1]/td[1]/a[1]")
    public WebElement getUserFromTable;

    @FindBy(xpath = "//h4[@class='job-times']/span")
    public List<WebElement> getJobDuration;

    @FindBy(xpath = "//div[contains(@class,'kpi-container')]//div/h3")
    public List<WebElement> getJobKPI;

    @FindBy(xpath = "//div[contains(@class,'component-tabs-secondary')]//a[text()='Gantt Chart']")
    public WebElement ganttChartTab;

    @FindBy(xpath = "//span[@id='reset']")
    public WebElement resetButton;

    /**
     * @param driver The driver that will be used to look up the elements
     */
    public JobsWorkflowPageObject(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

}
