package com.qa.pagefactory.clusters;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class JobsTrendsPageObject {

	@FindBy(xpath = "//select[contains(@class,'select-group-by')]")
	public WebElement groupByFilter;
	
	@FindBy(xpath = "//ul[contains(@id,'results')]/li")
	public List<WebElement> filterText;
	
	@FindBy(xpath = "//select[contains(@class,'select-cluster')]")
	public WebElement clusterFilter;
	
	@FindBy(xpath = "//label[@class='checkbox alt-error capitalize']/input")
	public List<WebElement> filterCheckboxes;
	
	@FindBy(xpath = "//label[@class='checkbox alt-error capitalize']/span")
	public List<WebElement> filterCheckboxText;
	
	@FindBy(id = "highcharts-dropdown")
	public WebElement threeDots;
	
	@FindBy(xpath = "//a[contains(text(),'Print')]/../../li/a")
	public List<WebElement> downloadList;
	
	String stateDeselector = "//li[contains(text(),'%s')]/span[contains(text(),'×')]";  //ACCEPTED, FAILED, FINISHED, RUNNING
	
	

	public JobsTrendsPageObject(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

}
