package com.qa.pagefactory.clusters;

import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ChargebackEmrPageObject {
	
	@FindBy(xpath = "(//ul[contains(@class,'sub-menu')]//span[text()='Chargeback'])")
	public WebElement clusterChargeBackTab;

	@FindBy(css=".select-chargback-type~.select2 .selection .select2-selection__arrow")
	public WebElement chargebackTypeDropdownOption;

	@FindBy(xpath="//span[@class='select2-results']//li")
	public List<WebElement> selectType;

	@FindBy(xpath = "//li[contains(@class,'select2-results__option')]")
	public List<WebElement> clustersList;

	@FindBy(xpath = "//label[normalize-space()='Chargeback Type']")
	public WebElement chargeBackPageHeading;

	@FindBy(xpath = "(//div[contains(@class,'row')])[7]//th/span")
	public WebElement chargebackResultHeader;
	
	@FindBy(xpath = "(//table[@class='component-data-tables row-hover'])[1]/tbody/tr")
	public List<WebElement> resultsGroupedByTableRecordsRows;
	
	@FindBy(xpath = "(//table[@class='component-data-tables row-hover'])[1]/tbody")
	public WebElement resultsGroupedByTableRecordsValues;
	
	@FindBy(xpath = "//table[@class='component-data-tables row-hover']/tbody/tr/td//span[contains(text(),'Insights')]")
	public List<WebElement> resultsGroupedByTableRecordsInsights;

	@FindBy(xpath = "//span[contains(@class, 'select2-selection--multiple')]//input")
	public WebElement groupBySearchBox;
	
	@FindBy(xpath = "//ul[@class='select2-results__options']/li")
	public List<WebElement> groupByList;

	@FindBy(xpath = "(//div[contains(@class,'highcharts-container')])[3]")
	public WebElement parentElementOfMemoryPieChart;

	@FindBy(xpath = "(.//*[local-name() = 'g'])[last()]/*[local-name() = 'text']/*[local-name() = 'tspan']")
	public List<WebElement> childElementsOfMemoryPieChart;

	@FindBy(xpath = "//span[contains(@class,'select2-selection__choice__remove')]")
	public List<WebElement> closeGroupByOptionButton;

	@FindBy(xpath = "(//td/p)[1]")
	public WebElement jobsNoDataAvailableText;

	@FindBy(xpath = "(//a[contains(.,'Download CSV')])")
	public List<WebElement> downloadCSVFile;

	@FindBy(xpath = "//h4/b[contains(text(),'Clusters')]/../..//a[@class='menu']")
	public WebElement clusterDownloadMenu;
	
	@FindBy(xpath = "//h4/b[contains(text(),'AVG')]/../..//a[@class='menu']")
	public WebElement avgCostDownloadMenu;
	
	@FindBy(xpath = "//h4/b[contains(text(),'Total')]/../..//a[@class='menu']")
	public WebElement totalCostDownloadMenu;

    public String downloadTotalCostFile = "//h4/b[contains(text(),'Total Cost')]/../following-sibling::div//a[contains(text(),'%s')]";
    
    public String downloadTotalAvgCostFile = "//h4/b[contains(text(),'AVG. Cost')]/../following-sibling::div//a[contains(text(),'%s')]";
    
    public String downloadClusterFile = "//h4/b[contains(text(),'Clusters')]/../following-sibling::div//a[contains(text(),'%s')]";

	@FindBy(xpath = "//div[contains(@class,'highcharts-container')]")
	public List<WebElement> emrChargebackPieCharts;

	@FindBy(xpath = "(//div[@id='searchBox'])[1]/child::div")
	public List<WebElement> pieChartGroupBySearchBoxs;

	@FindBy(xpath = "//span[contains(@class,'select2-results')]//li[contains(text(),'You can only select 1 items')]")
	public WebElement selectOnly1Message;

	@FindBy(xpath = "//span[@class='select2-selection__choice__remove']")
	public List<WebElement> remove1stGroupBy;

	@FindBy(xpath = "(((//div[contains(@class,'highcharts-container ')]//*[local-name() = 'svg'])[1]/*[local-name() = 'g']/*[local-name() = 'g'])[1]/*[local-name() = 'path'])[1]")
	public WebElement jobPie;

	@FindBy(xpath = "(((//div[contains(@class,'highcharts-container ')]//*[local-name() = 'svg'])[2]/*[local-name() = 'g']/*[local-name() = 'g'])[1]/*[local-name() = 'path'])[1]")
	public WebElement cpuHourPie;

	@FindBy(xpath = "(((//div[contains(@class,'highcharts-container ')]//*[local-name() = 'svg'])[3]/*[local-name() = 'g']/*[local-name() = 'g'])[1]/*[local-name() = 'path'])[1]")
	public WebElement memoryHourPie;

	@FindBy(xpath = "(//div[@id='searchBox'])[1]/child::div")
	public List<WebElement> pieChartGroupByOptions;

	@FindBy(xpath= "//table[@class='component-data-tables row-hover']/tbody/tr[1]/td//span")
	public List<WebElement> firstRowOfTable;

	@FindBy(xpath = "//a[contains(@class,'icon-sort-sorted-up')]")
	public WebElement userSortUp;

	@FindBy(xpath = "//h4[contains(text(),'Chargeback Report By')]/../../../../..//input[@class='search']")
	public WebElement chargebackSearchTextBox;
	
	@FindBy(xpath = "//h4[contains(text(),'Cluster By')]/../../../../..//input[@class='search']")
	public WebElement clusterSearchTextBox;
	
	@FindBy(xpath = "//a[contains(@class,'icon-sort-sorted-down')]")
	public WebElement userSortDown;
	
	@FindBy(xpath = "(//table[@class='component-data-tables row-hover'])[1]/tbody/tr")
	public List<WebElement> resultsGroupedByTableClusterRows;

	@FindBy(xpath = "//span[contains(text(),'Jobs')]")
	public List<WebElement> clusterJobs;
	
	@FindBy(xpath = "//span[contains(text(),'Jobs')]/../../../../../td[1]//span")
	public List<WebElement> clusterName;
	
	@FindBy(xpath = "//li[@class='select2-selection__choice']")
	public WebElement selectedCluster;
	
	public ChargebackEmrPageObject(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

}


