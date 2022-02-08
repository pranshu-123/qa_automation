package com.qa.pagefactory.databricks.cost;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class TrendsPageObject {
	

    @FindBy(xpath = "//h4[contains(text(),'DBU')]")
    public WebElement dbuGraphHeader;

    @FindBy(xpath = "//h4[contains(text(),'Cost')]")
    public WebElement costGraphHeader;

    @FindBy(xpath = "//h4[contains(text(),'Number of Clusters')]")
    public WebElement clusterGraphHeader;
    
    @FindBy(xpath = "//h4[contains(text(),'DBU')]/../following-sibling::div/a/span[contains(text(),'Chargeback')]")
    public WebElement dbuChargebackButton;
    
    @FindBy(xpath = "//h4[contains(text(),'DBU')]/../following-sibling::div/a/span[contains(text(),'Optimize')]")
    public WebElement dbuOptimizeButton;
    
    @FindBy(xpath = "//h4[contains(text(),'Cost')]/../following-sibling::div/a/span[contains(text(),'Chargeback')]")
    public WebElement costChargebackButton;
    
    @FindBy(xpath = "//h4[contains(text(),'Cost	')]/../following-sibling::div/a/span[contains(text(),'Optimize')]")
    public WebElement costOptimizeButton;
    
    @FindBy(xpath = "//h4[contains(text(),'Number of Clusters')]/../following-sibling::div/a/span[contains(text(),'Chargeback')]")
    public WebElement noOfClusterChargebackButton;
    
    @FindBy(xpath = "//h4[contains(text(),'Number of Clusters')]/../following-sibling::div/a/span[contains(text(),'Optimize')]")
    public WebElement noOfClusterOptimizeButton;
    
    @FindBy(xpath = "//div[@class='footer']//span[1]")
    public List<WebElement> graphFooter;
    
    @FindBy(xpath = "//div[@class='highcharts-container ']")
    public List<WebElement> generatedGraph;
    
    /**
     * @param driver The driver that will be used to look up the elements
     */
    public TrendsPageObject(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

}
