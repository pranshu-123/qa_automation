package com.qa.pagefactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * @author Ankur Jaiswal
 * All WebElement which is present at top level of unravel ui
 * is present in this class. Wherever you need to access these page object
 * create an instance of this class and access the members with that object.
 */

public class TopPanelPageObject {

    @FindBy(xpath = "(//a[contains(@href,'#/clusters/overview')])[1]")
    public WebElement unravelLogo;

    @FindBy(css = ".head-logo")
    public WebElement headLogo;

    @FindBy(xpath = "//label[normalize-space()='Resource Type']")
    public WebElement pageHeading;

    @FindBy(xpath = "//*[@id=\"dashboard_tabs\"]/button[1]")
    public WebElement dashboardTab;

    @FindBy(css = ".section-title")
    public WebElement sectionTitle;

    @FindBy(css = ".dropdown.login")
    public WebElement dropdownTitle;

    @FindBy(xpath = "//div[@id='dLabel']/a")
    public WebElement loggedInUser;

    @FindBy(xpath = "//a[contains(text(),'Logout')]")
    public WebElement logout;

    @FindBy(xpath = "//*[@class='dropdown-menu']//a[@href='#/app/manage/daemons']")
    public WebElement manage;

    //search text and button
    @FindBy(name = "q[exec_id_or_hadoop_user_name_or_name_cont]")
    public WebElement searchTopButton;

    @FindBy(css = "#submit")
    public WebElement searchSubmit;

    //link for all tabs
    @FindBy(xpath = "//a[@href='#/app/operations/dashboard']")
    public WebElement opeationTabButton;

    @FindBy(xpath = "//a[@href='#/app/applications/home']")
    public WebElement applicationHome;

    @FindBy(xpath = "//a[@href='#/app/reports/operational/chargeback']")
    public WebElement reportsTab;

    @FindBy(xpath = "//a[@href='#/app/api']")
    public WebElement apiTab;

    @FindBy(xpath = "//li[contains(@class,'active')]/" +
            "ul[contains(@class,'sub-menu')]//li/span[contains(text(),'Impala')]")
    public WebElement impalaTab;

    @FindBy(xpath = "(//ul[contains(@class,'sub-menu')]//span[text()='Overview'])")
    public WebElement overviewTab;

    @FindBy(xpath = "(//ul[contains(@class,'sub-menu')]//span[text()='Workload'])")
    public WebElement workloadTab;

    @FindBy(xpath = "//li[contains(@class,'active')]/ul[contains(@class,'sub-menu')]/li/span[contains(text(),'Tuning')]")
    public WebElement tuningTab;

    @FindBy(xpath = "(//ul[contains(@class,'sub-menu')]//span[text()='Forecasting'])")
    public WebElement dataForecastingTab;

    @FindBy(xpath = "(//ul[contains(@class,'sub-menu')]//span[text()='Small Files'])")
    public WebElement smallFilesTab;

    @FindBy(xpath = "(//ul[contains(@class,'sub-menu')]//span[text()='File Reports'])")
    public WebElement fileReportsTab;

    @FindBy(xpath = "//span[contains(@class,'icon-jobs')]")
    public WebElement jobsTab;

    @FindBy(xpath = "//span[contains(@class,'icon-cloud')]")
    public WebElement migrationTab;

    @FindBy(xpath = "//span[contains(@class,'icon-cluster')]")
    public WebElement clusterTab;

    @FindBy(xpath = "//nav/ul//span[contains(text(),'Reports')]")
    public WebElement reportsHeader;

    /**
     * @param driver The driver that will be used to look up the elements
     */
    public TopPanelPageObject(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }
}
