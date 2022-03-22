package com.qa.pagefactory.clusters;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;
/**
 * @author Sarbashree Ray
 * All WebElement which is related with clusters Jobs
 * is present in this class. Wherever you need to access these page object
 * create an instance of this class and access the members with that object.
 */
public class JobsPageObject {

    @FindBy(css = "div#highcharts-bpp6qdh-101.highcharts-container svg.highcharts-root g.highcharts-label.highcharts-no-data text tspan")
    public WebElement jobsNoDataAvailableText;

    @FindBy(xpath = "(//*[@id=\"highcharts-v3ten9y-542\"]/svg/g[5]/g[2]/path[2]")
    public WebElement jobsHighChartContainer;

    @FindBy(xpath = "(//div[contains(@class,'highcharts-container')])[1]")
    public WebElement JobHighChartContainer;


    @FindBy(xpath = "//div/table[@id='chargeback-table']/tbody/tr/td//span")
    public WebElement selectgroup;

    @FindBy(xpath = "//div/table[@id='chargeback-table']/tbody/tr/td//span")
    public WebElement selectQueue;

    @FindBy(xpath = "//div/table[@id='chargeback-table']/tbody/tr/td//span")
    public WebElement selectdept;

    @FindBy(xpath = "//li[contains(@class,'active')]/ul[contains(@class,'sub-menu')]//li/span[contains(text(),'Jobs')]")
    public WebElement jobsTab;

    @FindBy(xpath = "//div[contains(@class,'component-section-header')]/h1[text()='Jobs']")
    public WebElement getjobsPageHeader;

    @FindBy(xpath = "//label[contains(text(), 'Group By')]/following-sibling::span/span/span/span[contains(text(),'User')]")
    public WebElement groupByDropdownButton;

    @FindBy(xpath = "(//ul[contains(@class,'sub-menu')]//span[text()='Jobs Trends'])")
    public WebElement clusterResourcesTab;

    @FindBy(css = "svg > g.highcharts-axis-labels.highcharts-xaxis-labels")
    public List<WebElement> graphXAxisDateLabels;

    @FindBy(xpath = "//li[contains(text(),'State')]")
    public WebElement groupByState;

    @FindBy(xpath = "//span[contains(@class, 'select2-selection--multiple')]")
    public WebElement groupBySearchBox;

    @FindBy(xpath = "//li[contains(text(),'User')]")
    public WebElement groupByUser;

    @FindBy(xpath = "//li[contains(text(),'Application Type')]")
    public WebElement groupByAppType;

    @FindBy(xpath = "//li[contains(text(),'Queue')]")
    public WebElement groupByQueue;
    
    @FindBy(xpath = "//li[contains(text(),'Tag Key')]")
    public WebElement groupByTagKey;

    @FindBy(xpath = "//li[contains(text(),'Cluster')]")
    public WebElement groupByCluster;

    @FindBy(xpath = "//li[contains(text(),'Workspace')]")
    public WebElement groupByWorkspace;


    @FindBy(xpath = "//span[contains(@class,'select2-results')]/ul/li")
    public WebElement filterElements;

    @FindBy(xpath = "//input[contains(@class,'select2-search__field')]")
    public WebElement filterInput;

    @FindBy(xpath="//ul[contains(@class,'select2-selection__rendered')]/li[@class='select2-selection__choice']")
    public List<WebElement> defaultSelectedFilterElements;

    @FindBy(xpath = "(//*[@id=\"highcharts-xb65rrs-581\"]/svg/rect[1]")
    public WebElement jobsgraphContainer;

    @FindBy(xpath= "//div[@class='chargebackdrill']")
    public List<WebElement> listChargeBackDrillFromGroupByFilters;


    public JobsPageObject(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public List<WebElement> getChildElement(WebElement parentElement, By childElement) {
        return parentElement.findElements(childElement);

    }
}
