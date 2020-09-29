package com.qa.pagefactory.jobs;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import java.util.List;

public class ApplicationsPageObject {

    @FindBy(css="a#inefficientApps")
    public WebElement inefficientApps;

    @FindBy(css=".select2-search__field")
    public WebElement clusterIdsearchfield;

    @FindBy(xpath="//span[@class='select2-results']/ul/li")
    public List<WebElement> clusterIdsList;

    @FindBy(xpath="//tbody[@id='inefficientApps-body']")
    public WebElement tblInefficientApps;

    @FindBy(xpath = "//tbody[@id='inefficientApps-body']/tr")
    public List<WebElement> tblInefficientAppsRowsList;

    @FindBy(xpath= "//input[@name='daterangepicker_start' and contains(@class,'active')]")
    public WebElement startDatePicker;

    @FindBy(xpath = "//div[contains(@class,'opensleft')]/div[3]/div/button[1]")
    public WebElement applyBtn;
    
	@FindBy(xpath = "//div[contains(@class,'component-section-header')]/h1[text()='Jobs']")
	public WebElement jobsPageHeader;
	
	@FindBy(xpath = "//div[contains(@class,'ranges')]//li")
	public List<WebElement> dateRanges;

	@FindBy(xpath = "(//ul[contains(@class,'select2-selection__rendered')]//input)[1]")
	public WebElement clusterSearchBox;

	@FindBy(xpath = "//ul[contains(@class,'select2-results__options')]/li[1]")
	public WebElement select1stCluster;
	
	@FindBy(xpath = "//span[contains(@class,'select2-selection__choice__remove')]") 
	public WebElement removeCluster;
	
	@FindBy(xpath = "//div[contains(@class,'opensleft') and contains(@class,'show-calendar')]")
	public WebElement datepickerCalendar;
	
	@FindBy(xpath = "//span[contains(@class,'select2-dropdown--below')]//ul/li")
	public List<WebElement> getclusterListDropdown;
	
		@FindBy(xpath = "//tbody[@id = 'allApps-body']/tr/td[5]")
	public List<WebElement> getApplicationClusterId;
	
	@FindBy (xpath = "//h3[contains(text(),'No Data Available')]")
	public WebElement whenNoApplicationPresent;
	
	@FindBy(xpath = "(//div[contains(@class,'check-items-container')])[1]//span[1]")
	public WebElement getApplicationTypes;
	
	@FindBy (xpath = "(//ul[contains(@class,'select2-selection__rendered')])[2]")
	public WebElement userSearchBox;
	
	@FindBy (xpath = "(//ul[contains(@class,'select2-results')])/li")
	public List<WebElement> getUsername;
	
	@FindBy (xpath = "(//tbody[@id = 'allApps-body']/tr/td)[1]")
	public WebElement getTypeFromTable;
	
	@FindBy (xpath = "(//tbody[@id = 'allApps-body']/tr/td)[3]")
	public WebElement getUsernameFromTable;
	
	@FindBy (xpath = "(//tbody[@id = 'allApps-body']/tr/td)[8]")
	public WebElement getQueueNameTable;
	
	@FindBy (id = "apps-global-search-filter")
	public WebElement globalSearchBox;
	
	@FindBy (xpath = "//tbody/tr")
	public List<WebElement> getTableData;

    /**
     * @param driver The driver that will be used to look up the elements
     */
    public ApplicationsPageObject(WebDriver driver){
        PageFactory.initElements(driver, this);
    }
}
