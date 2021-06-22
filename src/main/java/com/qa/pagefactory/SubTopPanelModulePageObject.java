package com.qa.pagefactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * @author Birender Kumar
 * All WebElement which is present at top most level of unravel ui
 * is present in this class. Wherever you need to access these page object
 * create an instance of this class and access the members with that object.
 */

public class SubTopPanelModulePageObject {

    @FindBy(css = "a.logo")
    public WebElement unravelLogo;

    @FindBy(xpath = "//span[contains(@class,'icon-jobs')]")
    public WebElement jobs;

    @FindBy(xpath = "//span[contains(@class,'icon-data')]")
    public WebElement data;

    @FindBy(xpath = "//span[contains(@class,'icon-reports')]")
    public WebElement reports;

    @FindBy(xpath = "//span[contains(@class,'icon-alerts')]")
    public WebElement alerts;

    @FindBy(xpath = "//span[contains(@class,'icon-gear')]")
    public WebElement gear;

    @FindBy(xpath = "//li/a[text()='About']")
    public WebElement aboutInfo;

    @FindBy(xpath = "//div[@class='modal-body']//div[2]")
    public WebElement versionInfo;

    @FindBy(xpath = "//div[@class='close pointer']")
    public WebElement closeAboutWindow;

    @FindBy(xpath = "//li[contains(@class,'active')]//span[contains(text(),'Pipelines')]")
    public WebElement pipelinesTab;

    @FindBy(id = "support-li")
    public WebElement supportList;

    @FindBy(xpath = "//li//span[contains(text(),'Services And Versions Compatibility')]")
    public WebElement servicesVersionMigrationTab;

    @FindBy(xpath = "//li//span[text()='Cluster Discovery']")
    public WebElement clusterDiscoveryTab;

    @FindBy(xpath = "//li//span[contains(text(),'Workload Fit')]")
    public WebElement WorkloadFitTab;

    @FindBy(xpath = "(//ul[@class='sub-menu']/li/span[text()='Pipelines'])[2]")
    public WebElement jobsPipelinesTab;

    @FindBy(xpath = "(//span[contains(@class,'icon-jobs')]//following::li/span[text()='Applications'])[1]")
    public WebElement applicationTab;

    @FindBy(id = "apps-global-search-filter")
    public WebElement globalSearch;

    @FindBy(xpath = "(//ul[@class='sub-menu']//span[text()='Chargeback'])[1]")
    public WebElement chargeback;

    /**
     * @param driver The driver that will be used to look up the elements
     */
    public SubTopPanelModulePageObject(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

}
