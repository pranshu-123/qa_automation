package com.qa.pagefactory.clusters;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class HBasePageObject {

    public HBasePageObject(WebDriver driver){ PageFactory.initElements(driver, this);}

    @FindBy(xpath = "//li[contains(@class,'active')]/ul[contains(@class,'sub-menu')]//li/span[contains(text(),'HBase')]")
    public WebElement hbaseTab;

    @FindBy(xpath = "//h1[contains(text(), 'HBase')]")
    public WebElement hbaseHeader;

    @FindBy(xpath = "(//span[contains(@class,'select2-selection__rendered')])")
    public WebElement hBaseClusterDropDown;

    @FindBy(xpath = "//*[@class='select2-results__options']//li")
    public List<WebElement> hBaseClusters;

    @FindBy(xpath = "//*[@class='row no-gutters']//div[@class='col']/span")
    public List<WebElement> hBaseClusterKPIs;

    @FindBy(xpath = "//*[@class='row no-gutters']//div[@class='col-4']/h2")
    public List<WebElement> hBaseClusterKPIValues;

}
