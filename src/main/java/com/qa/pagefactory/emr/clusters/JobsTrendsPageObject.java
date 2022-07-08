package com.qa.pagefactory.emr.clusters;

import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class JobsTrendsPageObject {

	@FindBy(xpath="//a/span[contains(text(),'Jobs Trends')]")
	public WebElement jobTrends;
	
	@FindBy(xpath = "//label[contains(text(),'Group By')]/../span")
	public WebElement groupByDropdownButton;

	@FindBy(xpath = "//li[contains(text(),'Queue')]")
	public WebElement groupByQueueList;

	@FindBy(xpath = "//li[contains(text(),'User')]")
	public WebElement groupByUserList;

	@FindBy(xpath = "//li[contains(text(),'Application Type')]")
	public WebElement groupByAppTypeList;

	@FindBy(xpath = "//li[contains(text(),'State')]")
	public WebElement groupByStateList;

	@FindBy(xpath = "//span[contains(text(),'×')]")
	public List<WebElement> filterRemoveElements;
	
	@FindBy(xpath = "//li[@class='select2-selection__choice']")
	public List<WebElement> selectedFilterValues;

	@FindBy(css = ".select-cluster ~ .select2 .selection .select2-selection__arrow")
	public WebElement clusterDropdown;

	@FindBy(xpath = "//span[contains(@class,'select2-dropdown--below')]//li")
	public List<WebElement> listOfClusters;

	@FindBy(css = "svg > g.highcharts-axis-labels.highcharts-xaxis-labels")
	public WebElement graphXAxisDateLabels;
	
	@FindBy(css = "g.highcharts-grid.highcharts-yaxis-grid>path")
	public List<WebElement> graphXAxisDatePoints;
	
	@FindBy(xpath = "//h2[contains(text(),'Showing jobs running between')]")
	public WebElement runningJobs;

	@FindBy(xpath = "(//div[contains(@class,'footer')])[1]//label/span")
	public List<WebElement> memoryFooterLabels;

	@FindBy(css = "svg > g.highcharts-axis-labels.highcharts-yaxis-labels")
	public WebElement graphYAxisLabels;

	@FindBy(xpath = "//div[@graph-id='ClusterJobs']/a[@class='menu']")
	public WebElement downloadMenu;

	@FindBy(xpath = "//input[@class='select2-search__field']")
	public WebElement filter;

	@FindBy(xpath = "//li[contains(@class,'select2-results__option')]")
	public List<WebElement> filterOptionList;

	@FindBy(xpath = "(.//*[local-name() = 'svg']/*[local-name() = 'g']/*[local-name() = 'text']/*[local-name() = 'tspan'])[1]")
	public WebElement noDataHeader;
	
	public String downloadFormat = "//a[contains(text(),'%s')]";

	/**
	 * @param driver The driver that will be used to look up the elements
	 */
	public JobsTrendsPageObject(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
}
