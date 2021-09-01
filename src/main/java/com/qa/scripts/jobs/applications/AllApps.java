package com.qa.scripts.jobs.applications;

import com.qa.enums.UserAction;
import com.qa.pagefactory.SubTopPanelModulePageObject;
import com.qa.pagefactory.jobs.ApplicationsPageObject;
import com.qa.scripts.DatePicker;
import com.qa.utils.WaitExecuter;
import com.qa.utils.actions.UserActions;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class AllApps {
    private static final Logger LOGGER = Logger.getLogger(AllApps.class.getName());
    private final WaitExecuter waitExecuter;
    private final WebDriver driver;
    private final ApplicationsPageObject applicationsPageObject;
    private final Actions action;
    private final SubTopPanelModulePageObject subTopPanelModulePageObject;
    private final DatePicker datePicker;
    private final UserActions userAction;

    /**
     * Constructer to initialize wait, driver and necessary objects
     *
     * @param driver - WebDriver instance
     */

    public AllApps(WebDriver driver) {
        waitExecuter = new WaitExecuter(driver);
        applicationsPageObject = new ApplicationsPageObject(driver);
        action = new Actions(driver);
        subTopPanelModulePageObject = new SubTopPanelModulePageObject(driver);
        datePicker = new DatePicker(driver);
        userAction = new UserActions(driver);
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

    /* Select cluster from list */
    public void selectCluster(String clusterId) {
        LOGGER.info("Select Cluster: " + clusterId);
        removeClusterIfPresent();
        userAction.performActionWithPolling(applicationsPageObject.clusterSearchBox, UserAction.CLICK);
        waitExecuter.waitUntilElementClickable(applicationsPageObject.globalSearchBox);
        LOGGER.info("Search for cluster: " + clusterId);
        applicationsPageObject.clusterSearchBox.sendKeys(clusterId);
        waitExecuter.waitUntilElementClickable(applicationsPageObject.select1stCluster);
        waitExecuter.waitUntilElementClickable(applicationsPageObject.globalSearchBox);
        waitExecuter.sleep(1000);
        userAction.performActionWithPolling(applicationsPageObject.select1stCluster, UserAction.CLICK);
        waitExecuter.waitUntilElementClickable(applicationsPageObject.resetButton);
        waitExecuter.sleep(4000);

    }

    /* Check and remove cluster from searchbox */
    public void removeClusterIfPresent() {
        if (applicationsPageObject.removeCluster != null) {
            waitExecuter.waitUntilElementClickable(applicationsPageObject.clusterSearchBox);
            applicationsPageObject.clusterSearchBox.sendKeys(Keys.BACK_SPACE);
            waitExecuter.sleep(1000);
            waitExecuter.waitUntilElementClickable(applicationsPageObject.clusterSearchBox);
            waitExecuter.sleep(1000);
            applicationsPageObject.clusterSearchBox.clear();
            waitExecuter.sleep(1000);
            waitExecuter.waitUntilElementClickable(applicationsPageObject.clusterSearchBox);
        } else
            LOGGER.info("No cluster to remove");
    }

    /* Add all application count */
    public int addApplicationTypeCount() {
        List<WebElement> appJobCounts = applicationsPageObject.getEachApplicationTypeJobCounts;
        List<Integer> listOfJobCounts = new ArrayList<>();
        int totalCount = 0;
        for (int i = 0; i < appJobCounts.size(); i++) {
            listOfJobCounts.add(Integer.parseInt(appJobCounts.get(i).getText().replaceAll("[^\\dA-Za-z ]", "").trim()));
        }
        for (int jobCount : listOfJobCounts) {
            totalCount += jobCount;
        }
        return totalCount;
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
            waitExecuter.waitUntilElementClickable(applicationsPageObject.resetButton);
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

    /* Select cluster and Last 7 days */
    public void inJobsSelectClusterAndLast7Days(String clusterId) {
        // Navigate to Jobs tab from header
        navigateToJobsTab();
        // Select last 7 days from date picker
        select7Days();
        // Select cluster
        LOGGER.info("Select clusterId : " + clusterId);
        selectCluster(clusterId);
    }

    /* Get all app types that have run in unravel UI */
    public List<String> getAllApplicationTypes() {
        List<WebElement> appTypes = applicationsPageObject.getApplicationTypes;
        List<String> nameOfAppTypes = new ArrayList<>();
        for (WebElement appType : appTypes) {
            nameOfAppTypes.add(appType.getText().trim());
        }
        return nameOfAppTypes;
    }

    /* To reset the settings made in apps page */
    public void reset() {
        LOGGER.info("Reset username filter");
        waitExecuter.waitUntilElementClickable(applicationsPageObject.resetButton);
        userAction.performActionWithPolling(applicationsPageObject.resetButton, UserAction.CLICK);
        waitExecuter.waitUntilElementClickable(applicationsPageObject.resetButton);
    }

    /* Select cluster and Last 30 days */
    public void inJobsSelectClusterAndLast30Days(String clusterId) {
        // Navigate to Jobs tab from header
        navigateToJobsTab();
        // Select last 30 days from date picker
        select30Days();
        // Select cluster
        LOGGER.info("Select clusterId : " + clusterId);
        selectCluster(clusterId);
        waitExecuter.waitUntilElementClickable(applicationsPageObject.resetButton);
    }

    /* Select last 30 days from date range */
    public void select30Days() {
        LOGGER.info("Select last 30 days");
        datePicker.clickOnDatePicker();
        waitExecuter.waitUntilElementClickable(applicationsPageObject.resetButton);
        datePicker.selectLast30Days();
        waitExecuter.waitUntilElementClickable(applicationsPageObject.resetButton);
    }
}