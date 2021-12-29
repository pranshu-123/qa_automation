package com.qa.pagefactory.clusters;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class HBasePageObject {

	public HBasePageObject(WebDriver driver){ PageFactory.initElements(driver, this);}

	@FindBy(xpath = "(//ul[contains(@class,'sub-menu')]//span[text()='HBase'])")
	public WebElement hbaseTab;

	@FindBy(xpath = "//span[text()='HBase']")
	public WebElement hbaseHeader;

	@FindBy(xpath = "(//span[contains(@class,'select2-selection__rendered')])")
	public WebElement hBaseClusterDropDown;

	@FindBy(xpath = "//*[@class='select2-results__options']//li")
	public List<WebElement> hBaseClusters;

	@FindBy(xpath="//div[contains(@class, 'bg-success')]")
	public List<WebElement> hBaseKPIs;

	@FindBy(xpath="//div[contains(@class, 'bg-error')]")
	public List<WebElement> hBaseDeadRegionKPIs;

	@FindBy(xpath = "//div[@class='component-score-improvement-module']//div//div//div")
	public List<WebElement> hBaseClusterKPIs;

	@FindBy(xpath = "//span[@class='select2-selection__rendered']")
	public WebElement clusterDropDown;

	@FindBy(xpath = "//ul[@class='select2-results__options']/li")
	public List<WebElement> clusterList;

	@FindBy(xpath = "//div[@class='component-score-improvement-module']//div//h2")
	public List<WebElement> hBaseClusterKPIValues;

	@FindBy(xpath = "//div[contains(@class,'component-sub-tabs')]//ul//li")
	public List<WebElement> hBaseKpiContainers;

	@FindBy(xpath= "//table[@id='hbaseRegionsDataTble']/tbody/tr")
	public List<WebElement> regionServerTblRows;

	@FindBy(xpath="//table[@id='hbaseRegionsDataTble']")
	public WebElement hbaseRegionsDataTble;

	@FindBy(xpath="//table[@id='hbaseRegionsDataTble']/tbody/tr")
	public List<WebElement> hBaseRegionSvrTableRecords;

	@FindBy(xpath="//table[@id='hbaseRegionsDataTble']/tbody/tr/td[6]/span")
	public List<WebElement> hBaseRegionSvrHealth;

	@FindBy(xpath = "//span[contains(text(),'Table Name')]/..//following-sibling::a[contains(@class,'sorting')]")
	public WebElement sortByTableName;

	@FindBy(xpath = "//a[contains(@class,'sorting icon-sort icon-sort-sorted-up')]")
	public WebElement sortUp;

	@FindBy(xpath = "//tbody[@id = 'null-table-body']/tr/td[1]")
	public WebElement getTableName;

	@FindBy(xpath="//table[1]/tbody[1]/tr/td[6]")
	public List<WebElement> hBaseRegionSvrInsight;

	@FindBy(xpath="//div[@class='col kpi-box']/h5")
	public List<WebElement> regionSvrKpis;

	@FindBy(xpath = "//div[contains(@class,'dashboard-module')]")
	public List<WebElement> hBaseMetrics;

	@FindBy(xpath = "//div[contains(@class,'dashboard-module')]//div[@class='header']")
	public List<WebElement> hBaseMetricsHeader;

	@FindBy(xpath="//div[contains(@class,'dashboard-module')]//div[@class='content']")
	public List<WebElement> hBaseMetricsGraph;

	@FindBy(xpath="//div[contains(@class,'dashboard-module')]//div[@class='footer']")
	public List<WebElement> hBaseMetricsFooter;

	@FindBy(xpath="//div[contains(@id,'hbaseGraph')]//h4")
	public WebElement hBaseMetricsTitle;


	@FindBy(xpath="//table[@id='hbaseRegionsDataTble']/tbody/tr/td[1]")
	public List<WebElement> hBaseRegionSvrName;

	@FindBy(xpath="//table[@id='hbaseRegionsDataTble']/tbody/tr[1]/td[1]")
	public  WebElement hBaseFirstRegionSvr;

	@FindBy(xpath="//table[@id='TableshbaseInfoTable']")
	public WebElement hBaseRegionSvrTable;

	@FindBy(xpath="//table[@id='TableshbaseInfoTable']/tbody/tr/td[1]")
	public List<WebElement> hBaseRegionSvrTableNames;

	@FindBy(xpath="//span[contains(@id,'hbaseRegionsDataTble-headerspan-0')]")
	public WebElement hBaseRegionSvrTableHeaderName;

	@FindBy(xpath = "//table[@id='TableshbaseInfoTable']/tbody/tr[1]/td[1]")
	public WebElement hBaseFirstRegionSvrTable;

	@FindBy(xpath= "//table[@id='null-table']")
	public WebElement hBaseTableHostTbl;

	@FindBy(xpath="//a[contains(text(), 'Tables')]")
	public WebElement tablesTab;

	@FindBy(xpath="//table[@id='null-table']")
	public WebElement tablesTabTbl;

	@FindBy(xpath="//table[@id='null-table']/tbody/tr")
	public List<WebElement> tablesTabTblRecords;

	@FindBy(xpath = "//table[@id='null-table']/tbody/tr[1]/td[1]")
	public WebElement hBaseFirstTableElement;

	@FindBy(xpath = "//tbody[@id='null-table-body']/tr[1]/td[1]")
	public WebElement regionTableName;

	@FindBy(xpath="//div[contains(@class, 'kpi-content')]/div")
	public List<WebElement> regionKpiContent;

}
