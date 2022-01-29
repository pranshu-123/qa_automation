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

    @FindBy(xpath = "(//a[@class='menu'])[1]")
    public WebElement gear;

    @FindBy(xpath = "//ul[@class='dropdown-content']//a")
    public WebElement daemeons;

    @FindBy(xpath = "//a[@href='#/manage/stats']")
    public WebElement stats;

    @FindBy(css = "a.about")
    public WebElement aboutInfo;

    @FindBy(xpath = "//div[@class='modal-body scrollbar-s']//div[2]")
    public WebElement versionInfo;

    @FindBy(css = "button.close")
    public WebElement closeAboutWindow;

    @FindBy(xpath = "//li[contains(@class,'active')]//span[contains(text(),'Pipelines')]")
    public WebElement pipelinesTab;

    @FindBy(xpath = "//i[@class='icn-help']/following-sibling::i[1]")
    public WebElement helpCenterMenu;

    @FindBy(xpath = "(//ul[contains(@class,'sub-menu')]//span[text()='Services And Versions Compatibility'])")
    public WebElement servicesVersionMigrationTab;

    @FindBy(xpath = "(//ul[contains(@class,'sub-menu')]//span[text()='Cluster Discovery'])")
    public WebElement clusterDiscoveryTab;

    @FindBy(xpath = "(//ul[contains(@class,'sub-menu')]//span[text()='Workload Fit'])")
    public WebElement WorkloadFitTab;

    @FindBy(xpath = "//ul[@class='sub-menu']//span[text()='Pipelines']")
    public WebElement jobsPipelinesTab;

    @FindBy(xpath = "(//ul[contains(@class,'sub-menu')]//span[text()='Applications'])")
    public WebElement applicationTab;

    @FindBy(css = "input.global-app-search")
    public WebElement globalSearch;

    @FindBy(xpath = "(//ul[@class='sub-menu']//span[text()='Chargeback'])")
    public WebElement chargeback;

    /**
     * @param driver The driver that will be used to look up the elements
     */
    public SubTopPanelModulePageObject(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

}
