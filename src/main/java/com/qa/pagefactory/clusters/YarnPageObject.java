package com.qa.pagefactory.clusters;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

/**
 * @author Briender Kumar
 */
public class YarnPageObject {

    @FindBy(xpath = "//li[contains(@class,'active')]/ul[contains(@class,'sub-menu')]//li/span[contains(text(),'Resources')]")
    public WebElement clusterResourcesTab;

    @FindBy(xpath="//h1[contains(text(),'Yarn Resource Usage')]")
    public WebElement getResourcesPageHeader;

    @FindBy(xpath="//div[contains(@class,'ClusterResources_pblock_12Uze')]/div/span/span/span[contains(@class,'select2-selection--single')]")
    public WebElement groupByDropdownButton;

    @FindBy(xpath="//li[contains(text(),'Application Type')]")
    public WebElement groupByApplicationType;

    @FindBy(xpath = "//li[contains(text(),'User')]")
    public WebElement groupByUser;

    @FindBy(xpath = "//li[contains(text(),'Queue')]")
    public WebElement groupByQueue;

    @FindBy(xpath = "//li[contains(text(),'dept')]")
    public WebElement groupByDept;

    @FindBy(xpath = "//li[contains(text(),'project')]")
    public WebElement groupByProject;

    @FindBy(xpath = "//li[contains(text(),'realUser')]")
    public WebElement groupByRealUser;

    @FindBy(xpath = "//li[contains(text(),'dbs')]")
    public WebElement groupBydbs;

    @FindBy(xpath = "//li[contains(text(),'inputTables')]")
    public WebElement groupByInputTables;

    @FindBy(xpath = "//input[contains(@class,'select2-search__field')]")
    public WebElement filterInput;

    @FindBy(xpath = "//span[contains(@class,'select2-results')]/ul/li")
    public List<WebElement> filterElements;

    @FindBy(xpath="//ul[contains(@class,'select2-selection__rendered')]/li[@class='select2-selection__choice']")
    public List<WebElement> defaultSelectedFilterElements;

    @FindBy(xpath = "//span[contains(@class,'select2-selection__choice__remove')]")
    public List<WebElement> filterRemoveElements;

    public YarnPageObject(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

}
