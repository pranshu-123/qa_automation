package com.qa.pagefactory.emr.clusters;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ClusterDetailsPageObjects {
	
	@FindBy(xpath = "//a[contains(text(),'View jobs')]")
	public WebElement viewJobs;
	
	@FindBy(xpath = "//h6[contains(text(),'Cost')]/../h2")
	public WebElement costValue;
	
	@FindBy(xpath = "//h6[contains(text(),'EC2')]/../h4")
	public WebElement ec2Value;
	
	@FindBy(xpath = "//h6[contains(text(),'EMR')]/../h4")
	public WebElement emrValue;
	
	@FindBy(xpath = "//h6[contains(text(),'EBS')]/../h4")
	public WebElement ebsValue;
	
	//Cost Tab
	@FindBy(xpath = "//label/h6[contains(text(),'Cost')]")
	public WebElement costGraph;
	
	@FindBy(xpath = "//label/h6[contains(text(),'Nodes')]")
	public WebElement nodesGraph;
	
	@FindBy(xpath = "//label/h6[contains(text(),'CPU')]")
	public WebElement cpuGraph;
	
	@FindBy(xpath = "//label/h6[contains(text(),'Memory')]")
	public WebElement memoryGraph;
	
	@FindBy(xpath = "//h6[contains(text(),'Status')]/..//div")
	public WebElement status;
	
	@FindBy(css = "path.highcharts-point")
	public List<WebElement> chartsPoint;
	
	@FindBy(css = "path.highcharts-tracker-line")
	public List<WebElement> graphLines;
	
	public String nodeType = "//input[@value='%s']"; //master, core, task
	
	//Performance Tab
	@FindBy(xpath = "(//label/h6[contains(text(),'vCore')])[1]")
	public WebElement vCoreGraph;
	
	@FindBy(xpath = "(//label/h6[contains(text(),'Memory')])[1]")
	public WebElement performanceMemoryGraph;
	
	@FindBy(xpath = "//label/h6[contains(text(),'vCore - State')]")
	public WebElement vCoreStateGraph;
	
	@FindBy(xpath = "//label/h6[contains(text(),'Memory - State')]")
	public WebElement memoryStateGraph;
	
	@FindBy(xpath = "//label/h6[contains(text(),'Job count - State')]")
	public WebElement jobCountStateGraph;
	
	@FindBy(xpath = "//span[@class='multiselect__single']")
	public WebElement groupByDropdown;
	
	@FindBy(xpath = "//span[@class='multiselect__single']")
	public List<WebElement> groupByDropdownValues;
	
	@FindBy(xpath = "//span[@class='multiselect__placeholder']")
	public WebElement valuesDropdown;
	
	@FindBy(xpath = "//span[@class='multiselect__placeholder']/../..//div//li/span[@class='multiselect__option']")
	public List<WebElement> valuesDropdownList;
	
	//Resource Chargeback
	@FindBy(xpath = "//span[@class='multiselect__single']")
	public WebElement groupByResourceDropdown;
	
	@FindBy(xpath = "//li/span[@class='multiselect__option']/span")
	public List<WebElement> resourceValuesDropdownList;
	
	@FindBy(xpath = "//h5[contains(text(),'Jobs')]")
	public WebElement jobs;
	
	@FindBy(xpath = "//h5[contains(text(),'CPU')]")
	public WebElement cpuHours;
	
	@FindBy(xpath = "//h5[contains(text(),'Memory')]")
	public WebElement memoryHours;
	
	@FindBy(xpath = "//h4[contains(text(),'Chargeback')]")
	public WebElement chargebackByAppTypes;
	
	//Insights 
	@FindBy(xpath = "//h6[contains(text(),'Resource optimized instances')]")
	public List<WebElement> optimizationHeader;
	
	@FindBy(xpath = "//div[@class='cost-savings']")
	public List<WebElement> costSavings;
	
	@FindBy(xpath = "//button[@class='v2-close']")
	public WebElement close;
	
	public String clusterTabs = "//div/ul/li/a/span[contains(text(),'%s')]";  //Cost, Performance, Resource Chargeback
	
	
	/**
	 * @param driver The driver that will be used to look up the elements
	 */
	public ClusterDetailsPageObjects(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

}
