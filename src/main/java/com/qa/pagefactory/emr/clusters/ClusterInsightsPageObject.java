package com.qa.pagefactory.emr.clusters;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ClusterInsightsPageObject {

	@FindBy(xpath="//div[contains(text(),'Select Cluster Tags')]/../div/input")
	public WebElement clusterTagsDropdown;

	@FindBy(xpath="//input[@class='search']")
	public WebElement search;

	@FindBy(xpath="//a/span[contains(text(),'Cluster Insights')]")
	public WebElement clusterInsights;

	@FindBy(xpath = "//table[@class='component-data-tables row-hover']/thead/tr/th/span")
	public List<WebElement> clusterResultTableHeader;

	@FindBy(xpath = "//table[@class='component-data-tables row-hover']/tbody/tr[1]/td/span")
	public List<WebElement> clusterResultTableValues;

	@FindBy(xpath = "//table[@class='component-data-tables row-hover']/thead/../tbody/tr")
	public List<WebElement> clusterResultSetRows;
	
	@FindBy(xpath = "//table[@class='component-data-tables row-hover']/thead/../tbody/tr/td")
	public List<WebElement> clusterResultSetRowsValues;

	@FindBy(xpath = "//a[contains(text(),'Download CSV')]")
	public WebElement downloadCSV;

	@FindBy(xpath = "//h4[contains(text(),'Clusters')]/../..//a[@class='menu']")
	public WebElement downloadMenu;

	@FindBy(xpath = "//a[@class='icon-sort icon-sort-sorted-up']")
	public WebElement iconSort;

	@FindBy(xpath = "//b/span")
	public WebElement insightsHeader;
	
	@FindBy(xpath = "//input[@class='vue-treeselect__input']")
	public WebElement clusterTags;
	
	@FindBy(xpath = "//p[contains(text(),'No data to display')]")
	public WebElement noDataMsg;
	
	@FindBy(xpath = "//div[@class='cluster-info-container component-insights-feed']")
	public WebElement insightsBody;
	
	@FindBy(xpath = "//li[@class='pb-2']")
	public List<WebElement> instanceName;
	
	@FindBy(xpath = "//div/span[contains(text(),'Cost')]/following-sibling::div/span")
	public List<WebElement> instancesCost;

	public String sort = "//span[contains(text(),'%s')]/../a[@class='icon-sort']";

	/**
	 * @param driver The driver that will be used to look up the elements
	 */
	public ClusterInsightsPageObject(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
}
