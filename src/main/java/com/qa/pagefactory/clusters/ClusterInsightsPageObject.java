package com.qa.pagefactory.clusters;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ClusterInsightsPageObject {

	@FindBy(xpath = "//input[@class='vue-treeselect__input']")
	public WebElement clusterTagsField;
	
	@FindBy(xpath = "//input[@class='search']")
	public WebElement search;
	
	@FindBy(xpath = "//span[contains(text(),'Cost Type')]/.././..//th/span")
	public List<WebElement> resultSetHeaders;
	
	@FindBy(xpath = "//span[contains(text(),'$')]/.././../../tr[1]/td/span")
	public List<WebElement> resultSetInitialRowValues;
	
	@FindBy(xpath = "//span[contains(text(),'$')]/.././../../tr")
	public List<WebElement> totalRowsCount;
	
	@FindBy(xpath = "//a[contains(text(),'Apply')]")
	public WebElement applyBtn;
	
	@FindBy(xpath = "//td//span[contains(text(),'Jobs')]")
	public WebElement jobsBtn;
	
	public ClusterInsightsPageObject(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

}
