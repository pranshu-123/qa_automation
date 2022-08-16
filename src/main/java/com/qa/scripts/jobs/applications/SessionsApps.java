package com.qa.scripts.jobs.applications;

import com.qa.enums.UserAction;
import com.qa.pagefactory.SubTopPanelModulePageObject;
import com.qa.pagefactory.TopPanelPageObject;
import com.qa.pagefactory.jobs.ApplicationsPageObject;
import com.qa.pagefactory.jobs.SessionsPageObject;
import com.qa.scripts.DatePicker;
import com.qa.utils.WaitExecuter;
import com.qa.utils.actions.UserActions;
import com.relevantcodes.extentreports.ExtentTest;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;

import java.util.logging.Logger;

public class SessionsApps {

    private static final Logger LOGGER = Logger.getLogger(AllApps.class.getName());
    private final WaitExecuter waitExecuter;
    private final WebDriver driver;
    private final SessionsPageObject sessionsPageObject;
    private final Actions action;
    private final ExtentTest test;

    private final ApplicationsPageObject applicationsPageObject;
    private final SubTopPanelModulePageObject subTopPanelModulePageObject;
    private final DatePicker datePicker;
    private final UserActions userAction;
    private final TopPanelPageObject topPanelPageObject;

    /**
     * Constructer to initialize wait, driver and necessary objects
     *
     * @param driver - WebDriver instance
     */

    public SessionsApps(WebDriver driver, ExtentTest extentTest) {
        waitExecuter = new WaitExecuter(driver);
        topPanelPageObject = new TopPanelPageObject(driver);
        sessionsPageObject = new SessionsPageObject(driver);
        action = new Actions(driver);
        subTopPanelModulePageObject = new SubTopPanelModulePageObject(driver);
        applicationsPageObject = new ApplicationsPageObject(driver);
        datePicker = new DatePicker(driver);
        userAction = new UserActions(driver);
        this.driver = driver;
        this.test = extentTest;

    }

    /**
     * Click on  Jobs Sessions page Actions Click On Cluster > Jobs > Sessions
     */
    public void clickOnJobsSessionsTab() {
        waitExecuter.waitUntilElementClickable(topPanelPageObject.jobsTab);
        userAction.performActionWithPolling(topPanelPageObject.jobsTab, UserAction.CLICK);
        waitExecuter.waitUntilElementClickable(sessionsPageObject.resetButton);
        userAction.performActionWithPolling(subTopPanelModulePageObject.jobsSessionsTab, UserAction.CLICK);
        waitExecuter.waitUntilElementClickable(sessionsPageObject.globalSearch);
    }

    /* Select last 30 days data to display for workflow */
    public void selectLast30Days() {
        datePicker.clickOnDatePicker();
        waitExecuter.waitUntilElementClickable(sessionsPageObject.globalSearch);
        try {
            waitExecuter.sleep(3000);
            datePicker.selectLast30Days();
            waitExecuter.waitUntilElementClickable(sessionsPageObject.globalSearch);
        } catch (NoSuchElementException ex) {
            sessionsPageObject.noDataForWorkflows.isDisplayed();
        } catch (WebDriverException e) {
            LOGGER.info("unknown exception occured while finding locator value" + e);
        }
    }

    /**
     * Method to click on 'Only' and select a specific checkbox from job pages left pane
     *
     * @param types Types can be appType | status Type
     */
    public int clickOnlyLink(String types) {
        Actions action = new Actions(driver);
        WebElement we = driver.findElement(By.xpath("//label[contains(text(),'" + types + "')]"));
        action.moveToElement(we).moveToElement(driver.findElement(By.xpath("//label[contains(text(),'" + types + "')]//following-sibling::span[2]"))).click().build().perform();
        waitExecuter.waitUntilElementClickable(applicationsPageObject.resetButton);
        WebElement ele = driver.findElement(By.xpath("//label[contains(text(),'" + types + "')]//following-sibling::span[1]"));
        waitExecuter.sleep(3000);
        int appCount = Integer.parseInt(ele.getText().replaceAll("[^\\dA-Za-z ]", "").trim());
        waitExecuter.sleep(3000);
        return appCount;
    }

}
