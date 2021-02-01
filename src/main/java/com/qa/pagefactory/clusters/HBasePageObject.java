package com.qa.pagefactory.clusters;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class HBasePageObject {

    public HBasePageObject(WebDriver driver){ PageFactory.initElements(driver, this);}

    @FindBy(xpath = "//li[contains(@class,'active')]/ul[contains(@class,'sub-menu')]//li/span[contains(text(),'HBase')]")
    public WebElement hbaseTab;

    @FindBy(xpath = "//h1[contains(text(), 'HBase')]")
    public WebElement hbaseHeader;

    @FindBy(xpath = "(//span[contains(@class,'select2-selection__rendered')])")
    public WebElement hBaseClusterDropDown;

    @FindBy(xpath = "//*[@class='select2-results__options']//li")
    public List<WebElement> hBaseClusters;

    @FindBy(xpath="//div[contains(@class, 'bg-success')]")
    public List<WebElement> hBaseKPIs;

    @FindBy(xpath = "//*[@class='row no-gutters']//div[@class='col']/span")
    public List<WebElement> hBaseClusterKPIs;

    @FindBy(xpath = "//*[@class='row no-gutters']//div[@class='col-4']/h2")
    public List<WebElement> hBaseClusterKPIValues;

    @FindBy(xpath = "//div[contains(@class,'kpi-container')]/div/ul/li/span/a")
    public List<WebElement> hBaseKpiContainers;

    @FindBy(xpath= "//table[@id='hbaseRegionsDataTble']/tbody/tr")
    public List<WebElement> regionServerTblRows;

    @FindBy(xpath="//table[@id='hbaseRegionsDataTble']")
    public WebElement hbaseRegionsDataTble;

    @FindBy(xpath="//table[@id='hbaseRegionsDataTble']/tbody/tr/td[6]/span")
    public List<WebElement> hBaseRegionSvrHealth;

    @FindBy(xpath="//div[@class='container-fluid']/h3/a")
    public WebElement hBaseSvrHealthHeader;

    @FindBy(xpath="//div[@class='col kpi-box']/h5")
    public List<WebElement> regionSvrKpis;

    @FindBy(xpath="//div[contains(@class,'kpi-content')]/div")
    public List<WebElement> regionSvrKpiContent;

    @FindBy(xpath = "//div[@class='container-fluid']//div[contains(@class,'dashboard-module')]")
    public List<WebElement> hBaseMetrics;

    @FindBy(xpath = "//div[@class='container-fluid']//div[contains(@class,'dashboard-module')]//div[@class='header']/h4")
    public List<WebElement> hBaseMetricsHeader;

    @FindBy(xpath = "//div[@class='container-fluid']//div[contains(@class,'dashboard-module')]" +
            "//div[@class='content']//*[name()='g' and @class='highcharts-series-group']")
    public List<WebElement> hBaseMetricsGraph;

    @FindBy(xpath = "//div[@class='container-fluid']//div[contains(@class,'dashboard-module')]//div[@class='footer']//span")
    public List<WebElement> hBaseMetricsFooter;

    @FindBy(xpath="//*[@class='g-two-container']/div/div/h2")
    public WebElement hBaseMetricsTitle;

    @FindBy(xpath="//table[@id='hbaseRegionsDataTble']/tbody/tr/td[1]")
    public List<WebElement> hBaseRegionSvrName;

    @FindBy(xpath="//table[@id='hbaseRegionsDataTble']/tbody/tr[1]/td[1]")
    public  WebElement hBaseFirstRegionSvr;

    @FindBy(xpath="//table[@id='null-region']")
    public WebElement hBaseRegionSvrTable;

    @FindBy(xpath="//table[@id='null-region']/tbody/tr/td[1]")
    public List<WebElement> hBaseRegionSvrTableNames;

    @FindBy(xpath="//div[@class='g-col-one']/h3")
    public WebElement hBaseRegionSvrTableHeaderName;

    @FindBy(xpath = "//table[@id='null-region']/tbody/tr[1]/td[1]")
    public WebElement hBaseFirstRegionSvrTable;

    @FindBy(xpath = "//a[@class='active']")
    public WebElement hBaseTableTab;

    @FindBy(xpath = "//table[@id='hbaseTableHostDataTAble']")
    public WebElement hBaseTableHostTbl;

    @FindBy(xpath ="//table[@id='hbaseTableHostDataTAble']/tbody/tr")
    public List<WebElement> hBaseTableHostTblRows;

    @FindBy(xpath="//table[@id='hbaseTableHostDataTAble']/tbody/tr[1]/td[1]")
    public WebElement getHbaseTableHostFirstRowRegionName;

    @FindBy(xpath="//table[@id='hbaseTableHostDataTAble']/tbody/tr[1]/td[2]")
    public WebElement getHbaseTableHostFirstRowRegionSvrName;

    @FindBy(xpath="//a[contains(text(), 'Tables')]")
    public WebElement tablesTab;

    @FindBy(xpath="//table[@id='null-table']")
    public WebElement tablesTabTbl;



}
