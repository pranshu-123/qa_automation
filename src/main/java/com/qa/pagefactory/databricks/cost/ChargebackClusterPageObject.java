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
	
    @FindBy(xpath = "//span[@id='select2-mjwl-container']")
    public WebElement chargeBackType;
    
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
    
    @FindBy(xpath = "//button[contains(text(),'Copy URL')]/../../..//td/span")
    public List<WebElement> resultSetValues;
    /**
     * @param driver The driver that will be used to look up the elements
     */
    public ChargebackClusterPageObject(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }
}

