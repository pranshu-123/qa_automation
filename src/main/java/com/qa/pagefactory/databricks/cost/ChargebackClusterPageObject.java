	package com.qa.pagefactory.databricks.cost;

import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ChargebackClusterPageObject {

	@FindBy(xpath = "//span[text()='Cost']")
	public WebElement costTab;
	
	@FindBy(xpath = "(//ul[contains(@class,'sub-menu')]//span[text()='Chargeback'])")
	public WebElement costChargeBackTab;
	
	@FindBy(xpath = "(//ul[contains(@class,'sub-menu')]//span[text()='Trends'])")
	public WebElement costTrendsTab;
	
	@FindBy(xpath = "(//ul[contains(@class,'sub-menu')]//span[text()='Budget'])")
	public WebElement costBudgetTab;
	
    @FindBy(xpath = "//span[@title='Cluster']")
    public WebElement chargeBackType;
    
    @FindBy(xpath = "//li[contains(text(),'JobRun')]")
    public WebElement chargeBackTypeJobRun;
    
    @FindBy(xpath = "//ul[@class='select2-results__options']/li")
    public List<WebElement> chargeBackTypeValues;

    @FindBy(xpath = "//div[contains(@class,'dashboard')]//h4")
    public List<WebElement> graphsHeader;

    @FindBy(xpath = "//div[@class='col-md-6 pie-g']//div/div")
    public List<WebElement> pieGraph;

    @FindBy(xpath = "//div[@class='pie-legend']/div/label/div/span")
    public List<WebElement> pieChartValues;
    
    @FindBy(xpath = "//div[contains(@class,'dashboard')]//h4/following-sibling::div	")
    public List<WebElement> graphsThreeDots;
    
    @FindBy(xpath = "//label[contains(text(),'Group By')]/..//span[@class='select2-selection__rendered']")
    public WebElement groupBy;
    
    @FindBy(xpath = "//ul[contains(@id,'results')]/li")
    public List<WebElement> groupByValues;
    
    @FindBy(xpath = "//label[contains(text(),'Chargeback results group by')]/b")
    public WebElement lblResultSetHeader;
    
    @FindBy(xpath = "//th/span[contains(text(),'User')]")
    public WebElement user;
    
    @FindBy(xpath = "//th/span[contains(text(),'DBUs')]")
    public WebElement dbu;
    
    @FindBy(xpath = "//th/span[contains(text(),'Cluster Count')]")
    public WebElement clusterCount;
    
    @FindBy(xpath = "//th/span[contains(text(),'Cost')]")
    public WebElement cost;
    
    @FindBy(xpath = "//span[contains(text(),'Optimize')]")
    public WebElement optimize;
    
    @FindBy(xpath = "//button[contains(text(),'Copy URL')]")
    public List<WebElement> copyURL;
    
    @FindBy(xpath = "//button[contains(text(),'Copy URL')]/../a")
    public List<WebElement> urlLinks;
    
    @FindBy(xpath = "//button[contains(text(),'Copy URL')]/../../..//td/span")
    public List<WebElement> resultSetValues;
    
    @FindBy(xpath = "//div[@class='component-date-picker']/span")
    public WebElement selectedDates;
    
    @FindBy(xpath = "//h4[contains(text(),'Cluster')]/../h4/span")
    public WebElement clusterValue;
    
    @FindBy(xpath = "//h4[contains(text(),'JobRuns')]/../h4/span")
    public WebElement JobRunsValue;
    
    @FindBy(xpath = "//h4[contains(text(),'Cost')]/../h4/span")
    public WebElement costValue;
    
    @FindBy(xpath = "//h4[contains(text(),'DBU')]/../h4/span")
    public WebElement dbuValue;
    
    @FindBy(xpath = "(//span[@class='select2-selection__rendered'])[2]")
    public WebElement filterByDropDown;
    
    @FindBy(xpath = "//label[contains(text(),'Tag Key')]/..//span[contains(@class,'select2-selection__rendered')]")
    public WebElement tagKeyDropdown;
    
    @FindBy(xpath = "//span/input[@class='select2-search__field']")
    public WebElement tagKeySearchField;
    
    @FindBy(xpath = "//li[contains(@class,'select2-results__option')]")
    public List<WebElement> keyTeam;
    
    @FindBy(xpath = "//a[@class='icon-sort icon-sort-sorted-up']")
    public WebElement sortUp;
    
    @FindBy(xpath = "//a[@class='icon-sort icon-sort-sorted-down']")
    public WebElement sortDown;
    
    @FindBy(xpath = "//input[@class='select2-search__field']")
    public WebElement filterSearchTextField;
    
    @FindBy(xpath = "//ul[@class='select2-results__options']/li")
    public List<WebElement> tagKeyValues;
    
    @FindBy(xpath = "//ul[@class='select2-results__options']/li")
    public List<WebElement> filterSearchValueList;
   
    public String filterByValues = "//li[contains(text(),'%s')]";
    
    public String dbuDownloadFormat = "//h4[contains(text(),'DBU')]/..//a[contains(text(),'%s')]";
    
    public String costDownloadFormat = "//h4[contains(text(),'Cost')]/..//a[contains(text(),'%s')]";
    
    public String clusterDownloadFormat = "//h4[contains(text(),'Cluster')]/..//a[contains(text(),'%s')]";
   
    /**
     * @param driver The driver that will be used to look up the elements
     */
    public ChargebackClusterPageObject(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }
}

