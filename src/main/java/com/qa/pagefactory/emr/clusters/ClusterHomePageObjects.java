package com.qa.pagefactory.emr.clusters;

import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ClusterHomePageObjects {
	
	@FindBy(xpath = "//span[contains(text(),'Clusters')]")
	public WebElement cluster;
	
	@FindBy(xpath = "//span[contains(text(),'Select')]")
	public WebElement idListBox;
	
	@FindBy(xpath = "//div[@class='custom-option']/div")
	public List<WebElement> listOfIds;
	
	@FindBy(xpath = "//h6[contains(text(),'Name')]/../../following-sibling::div//input")
	public WebElement nameTextBox;
	
	@FindBy(xpath = "//span/span[contains(text(),'All')]")
	public WebElement statusListBox;
	
	@FindBy(xpath = "//span[contains(@class,'status status-indicator')]")
	public List<WebElement> listOfStatuses;
	
	@FindBy(xpath = "//div[contains(text(),'Select')]")
	public WebElement clusterTagsTextBox;
	
	@FindBy(xpath = "//div[contains(text(),'Clusters with Unravel')]/../input")
	public WebElement clusterWithUnravelChkBox;
	
	@FindBy(xpath = "//div[contains(text(),'Clusters without Unravel')]/../input")
	public WebElement clusterWithoutUnravelChkBox;
	
	@FindBy(xpath = "//thead/tr/th")
	public List<WebElement> resultTableHeader;
	
	@FindBy(xpath = "//tbody/tr/td/span")
	public List<WebElement> resultTableValues;
	
	@FindBy(xpath = "//tbody/tr/td/a")
	public List<WebElement> listOfClusterId;
	
	@FindBy(xpath = "//td[@class='text-left text-ellipsis']")
	public List<WebElement> listOfClusterNames;
	
	@FindBy(xpath = "//tbody/tr/td/a[contains(text(),'View jobs')]")
	public List<WebElement> viewJobs;
	
	@FindBy(xpath = "//span[@class='installation-status v2-un-installation']")
	public List<WebElement> unravelClusters;
	
	@FindBy(xpath = "//td[contains(text(),'No data present')]")
	public WebElement noDataPresent;
	
	@FindBy(xpath = "//div[contains(text(),'Node')]")
	public WebElement eventCount;
	
	@FindBy(xpath = "//h5[contains(text(),'Displaying')]")
	public WebElement clusterCount;
	
	public String clusterTabs = "//a/span[contains(text(),'%s')]";  //All, Active, Inefficient

	/**
	 * @param driver The driver that will be used to look up the elements
	 */
	public ClusterHomePageObjects(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

}
