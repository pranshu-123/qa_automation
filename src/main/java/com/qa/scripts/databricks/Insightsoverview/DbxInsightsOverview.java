package com.qa.scripts.databricks.Insightsoverview;

import com.qa.enums.UserAction;
import com.qa.pagefactory.databricks.DbxSubTopPanelModulePageObject;
import com.qa.pagefactory.databricks.insights.DbxInsightsOverviewPageObject;
import com.qa.scripts.DatePicker;
import com.qa.scripts.appdetails.AppDetailsPage;
import com.qa.scripts.databricks.jobs.DbAllApps;
import com.qa.utils.LoggingUtils;
import com.qa.utils.MouseActions;
import com.qa.utils.WaitExecuter;
import com.qa.utils.actions.UserActions;
import com.relevantcodes.extentreports.ExtentTest;
import org.openqa.selenium.InvalidElementStateException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.interactions.Actions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;

public class DbxInsightsOverview {

    private final Logger logger = LoggerFactory.getLogger(AppDetailsPage.class);
    private final LoggingUtils loggingUtils = new LoggingUtils(DbAllApps.class);
    private final WaitExecuter waitExecuter;
    private final WebDriver driver;
    private final ExtentTest test;
    private final DbxInsightsOverviewPageObject insightsOverview;
    private final Actions action;
    private final DbxSubTopPanelModulePageObject dbSubTopPanelModulePageObject;
    private final DatePicker datePicker;
    private final UserActions userAction;

    /**
     * Constructer to initialize wait, driver and necessary objects
     *
     * @param driver - WebDriver instance
     */

    public DbxInsightsOverview(WebDriver driver, ExtentTest extentTest) {
        waitExecuter = new WaitExecuter(driver);
        this.test = extentTest;
        insightsOverview = new DbxInsightsOverviewPageObject(driver);
        action = new Actions(driver);
        dbSubTopPanelModulePageObject = new DbxSubTopPanelModulePageObject(driver);
        datePicker = new DatePicker(driver);
        userAction = new UserActions(driver);
        this.driver = driver;
    }


    /**
     * Click on the Insights Overview tab present on top of the page
     */
    public void navigateToInsightsTab() {
        try {
            userAction.performActionWithPolling(dbSubTopPanelModulePageObject.jobs, UserAction.CLICK);
            waitExecuter.waitUntilPageFullyLoaded();
            waitExecuter.waitUntilElementClickable(insightsOverview.insightsTab);
            waitExecuter.sleep(3000);
            MouseActions.clickOnElement(driver, insightsOverview.insightsTab);
            loggingUtils.pass("Click on Insights tab", test);
        } catch (InvalidElementStateException e) {
            loggingUtils.error("Could not click on Insights tab" +e, test);
        } catch (WebDriverException e) {
            e.printStackTrace();
            loggingUtils.error("Unknown exception occured while click Insights tab" +e, test);
        }
    }

    public void validateInsightsTabPresence() {
        String cost = insightsOverview.cost.getText();
        loggingUtils.info("Cost displayed: " + cost,test);
        Assert.assertNotNull(cost);
        String resourceEfficiency = insightsOverview.resourceEfficiency.getText();
        loggingUtils.info("Resource Efficiency Tab is displayed: " + resourceEfficiency,test);
        Assert.assertNotNull(resourceEfficiency);
        String appAcceleration = insightsOverview.appAcceleration.getText();
        loggingUtils.info("App Acceleration Tab is displayed " + appAcceleration,test);
        Assert.assertNotNull(appAcceleration);
    }

    public void validateInsightsCategory() {
        Assert.assertTrue(insightsOverview.costCategory.isDisplayed(), "cost Category not displayed");
        Assert.assertTrue(insightsOverview.resourceEfficiencyCategory.isDisplayed(), "Resource Efficiency Category not displayed");
        Assert.assertTrue(insightsOverview.appAccelerationCategory.isDisplayed(), "App Acceleration Category not displayed");

    }

}
