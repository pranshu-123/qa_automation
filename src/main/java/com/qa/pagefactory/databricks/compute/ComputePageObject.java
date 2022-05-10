package com.qa.pagefactory.databricks.compute;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ComputePageObject {

	@FindBy(xpath = "//span[contains(text(),'Compute')]")
	public WebElement compute;
	
	@FindBy(xpath = "//input[@placeholder='Filter by Cluster Name']")
	public WebElement filterByClusterName;

	@FindBy(xpath = "//a[contains(text(),'All')]")
	public WebElement allTab;	

	@FindBy(xpath = "//a[contains(text(),'Finished')]")
	public WebElement finishedTab;	

	@FindBy(xpath = "//a[contains(text(),'Running')]")
	public WebElement runningTab;

	@FindBy(xpath = "//th[@class='no-sorting text-right table-settings options-dropdown']")
	public WebElement settings;

	@FindBy(xpath = "//h3[contains(text(),'No Data Available')]")
	public WebElement noDataAvailable;
	
	@FindBy(xpath = "//a[contains(text(),'Inefficient')]")
	public WebElement inefficientTab;	

	@FindBy(xpath = "//span[contains(@class,'event-name text-ellipsis')]/../..")
	public List<WebElement> statusCheckboxes;	
	
	@FindBy(xpath = "//p[contains(@class,'text-align-left')]/label")
	public List<WebElement> settingsCheckboxes;	

	@FindBy(xpath = "//a[contains(text(),'User')]/../..//input")
	public WebElement userSearchBox;	
	
	@FindBy(xpath = "//a[contains(text(),'Workspace')]/../..//input")
	public WebElement workspaceSearchBox;
	
	@FindBy(xpath = "//li[contains(@class,'select2-results__option')]")
	public WebElement selectWorkspace;
	
	@FindBy(xpath = "//a[contains(text(),'Cluster')]/../..//input")
	public WebElement clusterSearchBox;
	
	@FindBy(xpath = "//span[contains(text(),'Reset')]")
	public WebElement reset;
	
	@FindBy(xpath = "(//a[@class='primary-link text-ellipsis'])[1]")
	public WebElement clusterName;
	
	@FindBy(xpath = "//a[contains(text(),'Tags')]/../..//input")
	public List<WebElement> tagsSearchBox;
	
	@FindBy(xpath = "//td[@title='Success']/..//button[contains(text(),'Spark')]")
	public List<WebElement> sparkBtn;
	
	@FindBy(xpath = "//a[@class='icon-gear']/../..//th/span")
	public List<WebElement>  jobRunHeaders;
	
	@FindBy(xpath = "//tbody[@id='DBClustersListAll-body']/tr[1]/td")
	public List<WebElement>  jobRunValues;
	
	@FindBy(xpath = "//tbody[@id='DBClustersListAll-body']/tr[1]/td")
	public List<WebElement>  filteredResultRows;
	
	@FindBy(xpath = "//a[contains(text(),'Tags')]/../following-sibling::div//span")
	public List<WebElement>  tagKeyCheckboxes;
	
	@FindBy(xpath = "//a[contains(text(),'Tags')]/../following-sibling::div//span/..//input[@class='select2-search__field']")
	public WebElement tagKeySearchBox;
	
	@FindBy(xpath = "//li[contains(@class,'highlighted')]")
	public WebElement tagKeyValue;

	@FindBy(xpath = "//a[@class='pagination-atag']")
	public List<WebElement> pagination;

	@FindBy(xpath = "//input[@class='global-app-search']")
	public WebElement globalSearch;
	
	@FindBy(xpath = "//tbody[@id='allApps-body']/tr")
	public List<WebElement>  globalSearchResult;
	
	@FindBy(xpath = "//tbody[@id='DBClustersListAll-body']/tr/td")
	public List<WebElement>  jobRunResults;
	
	@FindBy(xpath = "//li[@class='select2-selection__choice']")
	public WebElement filteredChoice;
	
	public String sortType = "//span[contains(text(),'%s')]/../a[@class='sorting icon-sort']"; //Duration, Cost, Start time, User
	
	/**
	 * @param driver The driver that will be used to look up the elements
	 */
	public ComputePageObject(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
}
