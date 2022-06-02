package com.qa.scripts.emr.jobs;

import com.qa.enums.UserAction;
import com.qa.pagefactory.databricks.DbxSubTopPanelModulePageObject;
import com.qa.pagefactory.emr.jobs.EmrApplicationsPageObject;
import com.qa.pagefactory.emr.jobs.EmrSubTopPanelModulePageObject;
import com.qa.scripts.DatePicker;
import com.qa.scripts.jobs.applications.AllApps;
import com.qa.utils.WaitExecuter;
import com.qa.utils.actions.UserActions;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class EMRAllApps {
    private static final Logger LOGGER = Logger.getLogger(AllApps.class.getName());
    private final WaitExecuter waitExecuter;
    private final WebDriver driver;
    private final EmrApplicationsPageObject applicationsPageObject;
    private final Actions action;
    private final EmrSubTopPanelModulePageObject subTopPanelModulePageObject;
    private final DatePicker datePicker;
    private final JavascriptExecutor executor;
    private final UserActions userAction;

    /**
     * Constructer to initialize wait, driver and necessary objects
     *
     * @param driver - WebDriver instance
     */

    public EMRAllApps(WebDriver driver) {
        waitExecuter = new WaitExecuter(driver);
        applicationsPageObject = new EmrApplicationsPageObject(driver);
        action = new Actions(driver);
        subTopPanelModulePageObject = new EmrSubTopPanelModulePageObject(driver);
        datePicker = new DatePicker(driver);
        userAction = new UserActions(driver);
        executor = (JavascriptExecutor) driver;
        this.driver = driver;
    }

    /*Get all range from calendar */
    public List<String> getCalendarRanges() {
        List<WebElement> getCalendarRangeElements = applicationsPageObject.dateRanges;
        waitExecuter.sleep(1000);
        List<String> listOfCaledarRanges = new ArrayList<>();
        LOGGER.info("Total number of ranges in datepicker: " + getCalendarRangeElements.size());
        for (int i = 0; i < getCalendarRangeElements.size(); i++) {
            LOGGER.info("The range in the calendar " + getCalendarRangeElements.get(i).getText());
            listOfCaledarRanges.add(getCalendarRangeElements.get(i).getText());
        }
        return listOfCaledarRanges;
    }

    /* Add all application count */
    public int addApplicationTypeCount() {
        List<WebElement> appJobCounts = applicationsPageObject.getEachApplicationTypeJobCounts;
        waitExecuter.sleep(2000);
        List<Integer> listOfJobCounts = new ArrayList<>();
        int totalCount = 0;
        for (int i = 0; i < appJobCounts.size(); i++) {
            listOfJobCounts.add(Integer.parseInt(appJobCounts.get(i).getText().replaceAll("[^\\dA-Za-z ]", "").trim()));
            waitExecuter.waitUntilPageFullyLoaded();
        }
        for (int jobCount : listOfJobCounts) {
            totalCount += jobCount;
        }
        return totalCount;
    }
    /**
     * Method to click on 'Only' and select a specific checkbox from job pages left
     * pane
     *
     * @param types Types can be appType | status Type
     */
    public int clickOnlyLink(String types) {
        Actions action = new Actions(driver);
        WebElement we = driver
                .findElement(By.xpath("(//label[contains(@class,'checkbox')])/span[contains(text(),'" + types + "')]"));
        action.moveToElement(we)
                .moveToElement(driver.findElement(By.xpath("(//label[contains(@class,'checkbox')])"
                        + "/span[contains(text(),'" + types + "')]/following-sibling::span[2]")))
                .click().build().perform();
        waitExecuter.waitUntilPageFullyLoaded();
        WebElement ele = driver.findElement(By.xpath("(//label[contains(@class,'checkbox')])"
                + "/span[contains(text(),'" + types + "')]/following-sibling::span[1]"));
        waitExecuter.sleep(3000);
        int appCount = Integer.parseInt(ele.getText().replaceAll("[^\\dA-Za-z ]", "").trim());
        waitExecuter.sleep(3000);
        return appCount;
    }

    /* De-Select all app types */
    public void deselectAllAppTypes() {
        List<WebElement> appTypes = applicationsPageObject.selectOneApplicationType;
        for (int i = 0; i < appTypes.size(); i++) {
            userAction.performActionWithPolling(appTypes.get(i), UserAction.CLICK);
            waitExecuter.waitUntilElementClickable(appTypes.get(i));
        }
        // Assert if the application type is selected successfully.
        try {
            waitExecuter.waitUntilElementPresent(applicationsPageObject.whenApplicationPresent);
        } catch (NoSuchElementException te) {
            throw new AssertionError("After de-selecting all status 'No Data Available' is not displayed.");
        }
    }

    /* De-Select all status types */
    public void deselectAllStatusTypes() {
        List<WebElement> statusTypes = applicationsPageObject.selectSingleStatusType;
        for (int i = 0; i < statusTypes.size(); i++) {
            userAction.performActionWithPolling(statusTypes.get(i), UserAction.CLICK);
            waitExecuter.waitUntilElementClickable(statusTypes.get(i));
        }
        try {
            waitExecuter.waitUntilPageFullyLoaded();
        } catch (NoSuchElementException te) {
            throw new AssertionError("After de-selecting all status 'No Data Available' is not displayed.");
        }

    }

    /* Slide the slider */
    public void moveTheSlider(WebElement sliderElement, int width) {
        action.dragAndDropBy(sliderElement, width, 0).click();
        action.build().perform();
    }

    /* Navigate to Jobs Tab */
    public void navigateToJobsTab() {
        LOGGER.info("Navigate to jobs tab from header");
        waitExecuter.waitUntilElementClickable(subTopPanelModulePageObject.jobs);
        userAction.performActionWithPolling(subTopPanelModulePageObject.jobs, UserAction.CLICK);
        waitExecuter.waitUntilElementClickable(applicationsPageObject.resetButton);
        waitExecuter.waitUntilPageFullyLoaded();
    }

    /* Select last 7 days from date range */
    public void select7Days() {
        LOGGER.info("Select last 7 days");
        datePicker.clickOnDatePicker();
        waitExecuter.sleep(1000);
        datePicker.selectLast30Days();
        waitExecuter.waitUntilElementClickable(applicationsPageObject.resetButton);
    }

    /**
     * Method to click the first app in jobs table , navigate to the details page.
     * and verify Status App details Page .
     */
    public String verifyStatus(EmrSubTopPanelModulePageObject EmrallApps) {
        String statusTable = EmrallApps.Status.getText().trim().toLowerCase();
        LOGGER.info("Application Id is " + statusTable);
        waitExecuter.waitUntilElementClickable(EmrallApps.clickOnAppId);
        EmrallApps.clickOnAppId.click();
        waitExecuter.waitUntilElementClickable(EmrallApps.closeIcon);
        waitExecuter.waitUntilPageFullyLoaded();
        String status = EmrallApps.appStatus.getText().trim().toLowerCase();
        Assert.assertEquals(statusTable, status, "Job Status is not displayed in the Header");
        return status;
    }

    /* Select Last 7 days */
    public void inJobsSelectLast7Days() {
        // Navigate to Jobs tab from header
        navigateToJobsTab();
        // Select last 7 days from date picker
        select7Days();
    }


    /* To reset the settings made in apps page */
    public void reset() {
        LOGGER.info("Reset username filter");
        waitExecuter.waitUntilElementClickable(applicationsPageObject.resetButton);
        userAction.performActionWithPolling(applicationsPageObject.resetButton, UserAction.CLICK);
        waitExecuter.waitUntilElementClickable(applicationsPageObject.resetButton);
    }

}
