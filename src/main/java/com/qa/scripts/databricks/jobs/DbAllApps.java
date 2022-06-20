package com.qa.scripts.databricks.jobs;

import com.qa.enums.UserAction;
import com.qa.pagefactory.databricks.DbxSubTopPanelModulePageObject;
import com.qa.pagefactory.databricks.jobs.DbxApplicationsPageObject;
import com.qa.pagefactory.reports.ReportsArchiveScheduledPageObject;
import com.qa.scripts.DatePicker;
import com.qa.scripts.appdetails.AppDetailsPage;
import com.qa.utils.LoggingUtils;
import com.qa.utils.MouseActions;
import com.qa.utils.WaitExecuter;
import com.qa.utils.actions.UserActions;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DbAllApps {
    private final Logger logger = LoggerFactory.getLogger(AppDetailsPage.class);
    private final LoggingUtils loggingUtils = new LoggingUtils(DbAllApps.class);
    private final WaitExecuter waitExecuter;
    private final WebDriver driver;
    private final DbxApplicationsPageObject applicationsPageObject;
    private final Actions action;
    private final UserActions userActions;
    private final DbxSubTopPanelModulePageObject dbSubTopPanelModulePageObject;
    private final DatePicker datePicker;
    private final UserActions userAction;

    /**
     * Constructer to initialize wait, driver and necessary objects
     *
     * @param driver - WebDriver instance
     */

    public DbAllApps(WebDriver driver) {
        waitExecuter = new WaitExecuter(driver);
        applicationsPageObject = new DbxApplicationsPageObject(driver);
        action = new Actions(driver);
        userActions = new UserActions(driver);
        dbSubTopPanelModulePageObject = new DbxSubTopPanelModulePageObject(driver);
        datePicker = new DatePicker(driver);
        userAction = new UserActions(driver);
        this.driver = driver;
    }


    /*Search for the app Id*/
    public void searchByAppID(String app) {
        waitExecuter.waitUntilElementClickable(dbSubTopPanelModulePageObject.searchBox);
        userActions.performActionWithPolling(dbSubTopPanelModulePageObject.searchBox, UserAction.CLICK);
        userActions.performActionWithPolling(dbSubTopPanelModulePageObject.searchBox, UserAction.SEND_KEYS, app);
        dbSubTopPanelModulePageObject.searchBox.sendKeys(Keys.RETURN);
        waitExecuter.waitUntilElementClickable(dbSubTopPanelModulePageObject.searchBox);
        if (dbSubTopPanelModulePageObject.noDataPresent.size() > 0) {
            Boolean noData = dbSubTopPanelModulePageObject.noDataPresent.contains("No Data Available.");
            logger.info("There are no application by Jobid- " + noData);
            waitExecuter.waitUntilPageFullyLoaded();
        } else
            logger.info("There are  application by name- " + app);
    }

    /* Select cluster from list */
    public void selectCluster(String clusterId) {
        logger.info("Select Cluster: " + clusterId);
        removeClusterIfPresent();
        userAction.performActionWithPolling(applicationsPageObject.clusterSearchBox, UserAction.CLICK);
        waitExecuter.waitUntilElementClickable(applicationsPageObject.globalSearchBox);
        logger.info("Search for cluster: " + clusterId);
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
            logger.info("No cluster to remove");
    }

    /**
     * Method to click on 'Only' and select a specific checkbox from job pages left pane
     *
     * @param types Types can be appType | status Type
     */
    public int clickOnlyLink(String types) {
        Actions action = new Actions(driver);
        WebElement we = driver
                .findElement(By.xpath("(//label[contains(@class,'checkbox')])//span[contains(text(),'" + types + "')]"));
        action.moveToElement(we)
                .moveToElement(driver.findElement(By.xpath("//label[@title='" + types + "']/following-sibling::a[1]")))
                .click().build().perform();
        waitExecuter.waitUntilElementClickable(dbSubTopPanelModulePageObject.resetButton);
        WebElement ele = driver.findElement(By.xpath("(//label[contains(@class,'checkbox')])"
                + "//span[contains(text(),'" + types + "')]/following-sibling::span[1]"));
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
            waitExecuter.waitUntilElementPresent(applicationsPageObject.selectApplicationPresent);
        } catch (NoSuchElementException te) {
            throw new AssertionError("After de-selecting all status 'No Data Available' is not displayed.");
        }
    }

    /*Get all range from calendar */
    public List<String> getCalendarRanges() {
        List<WebElement> getCalendarRangeElements = applicationsPageObject.dateRanges;
        waitExecuter.sleep(1000);
        List<String> listOfCaledarRanges = new ArrayList<>();
        logger.info("Total number of ranges in datepicker: " + getCalendarRangeElements.size());
        for (int i = 0; i < getCalendarRangeElements.size(); i++) {
            logger.info("The range in the calendar " + getCalendarRangeElements.get(i).getText());
            listOfCaledarRanges.add(getCalendarRangeElements.get(i).getText());
        }
        return listOfCaledarRanges;
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
    public void navigateToRunsTab() {
        logger.info("Navigate to jobs tab from header");
        waitExecuter.waitUntilElementClickable(dbSubTopPanelModulePageObject.jobs);
        userAction.performActionWithPolling(dbSubTopPanelModulePageObject.jobs, UserAction.CLICK);
        waitExecuter.waitUntilElementClickable(applicationsPageObject.resetButton);
        userAction.performActionWithPolling(dbSubTopPanelModulePageObject.runsTab, UserAction.CLICK);
        waitExecuter.waitUntilPageFullyLoaded();
    }

    public void navigateToJobsTab(String tab) {
        waitExecuter.waitUntilElementPresent(dbSubTopPanelModulePageObject.jobs);
        userAction.performActionWithPolling(dbSubTopPanelModulePageObject.jobs, UserAction.CLICK);
        waitExecuter.sleep(4000);
        try {
            if (tab.equalsIgnoreCase("Runs")) {
                userAction.performActionWithPolling(dbSubTopPanelModulePageObject.runsTab, UserAction.CLICK);
                waitExecuter.waitUntilElementPresent(dbSubTopPanelModulePageObject.runsTab);
            } else if (tab.equalsIgnoreCase("Jobs")) {
                userAction.performActionWithPolling(dbSubTopPanelModulePageObject.jobsTabs, UserAction.CLICK);
                waitExecuter.waitUntilElementPresent(dbSubTopPanelModulePageObject.jobsTabs);
            }
        } catch (ElementClickInterceptedException e) {
            e.printStackTrace();
        }
    }

    public void selectTab(String tab) {
        userAction.performActionWithPolling(dbSubTopPanelModulePageObject.runsTab, UserAction.CLICK);
        try {
            if (tab.equalsIgnoreCase("Finished")) {
                userAction.performActionWithPolling(dbSubTopPanelModulePageObject.finishedTab, UserAction.CLICK);
            } else if (tab.equalsIgnoreCase("Running")) {
                userAction.performActionWithPolling(dbSubTopPanelModulePageObject.runningTab, UserAction.CLICK);
                waitExecuter.waitUntilElementPresent(dbSubTopPanelModulePageObject.runningTab);
            }
        } catch (ElementClickInterceptedException e) {
            e.printStackTrace();
        }
    }


    /* Select last 7 days from date range */
    public void select7Days() {
        logger.info("Select last 7 days");
        datePicker.clickOnDatePicker();
        waitExecuter.sleep(1000);
        datePicker.selectLast7Days();
        waitExecuter.waitUntilPageFullyLoaded();
    }

    /* Select cluster and Last 7 days */
    public void inJobsSelectClusterAndLast7Days() {
        // Select last 7 days from date picker
        select7Days();
        // Select cluster
        logger.info("Select last 7 days from date picker");
    }

    /* Select cluster and Last 7 days */
    public void inJobsSelectClusterAndLast30Days() {
        // Select last 30 days from date picker
        select30Days();
        // Select cluster
        logger.info("Select last 30 days from date picker");
    }

    public List<WebElement> getClusterListFromDropdown() {
        return dbSubTopPanelModulePageObject.clusterList;
    }

    /* Get all Status types that have run in unravel UI */
    public List<String> getAllApplicationTypes() {
        List<WebElement> appTypes = applicationsPageObject.getApplicationTypes;
        List<String> nameOfAppTypes = new ArrayList<>();
        for (WebElement appType : appTypes) {
            nameOfAppTypes.add(appType.getText().trim());
        }
        return nameOfAppTypes;
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

    public void clickOnClusterDropDown() {
        userAction.performActionWithPolling(dbSubTopPanelModulePageObject.clusterDropdown, UserAction.CLICK);
        waitExecuter.waitUntilElementPresent(dbSubTopPanelModulePageObject.resetButton);
    }


    /**
     * Method to click the first app in jobs table , navigate to the details page.
     * and verify Status App details Page .
     */
    public String verifyStatus(DbxSubTopPanelModulePageObject dballApps) {
        String statusTable = dballApps.Status.getText().trim().toLowerCase();
        logger.info("Application Id is " + statusTable);
        waitExecuter.waitUntilElementClickable(dbSubTopPanelModulePageObject.clickOnAppId);
        dbSubTopPanelModulePageObject.clickOnAppId.click();
        waitExecuter.waitUntilElementClickable(dbSubTopPanelModulePageObject.closeIcon);
        waitExecuter.waitUntilPageFullyLoaded();
        String status = dballApps.appStatus.getText().trim().toLowerCase();
        Assert.assertEquals(statusTable, status, "Runs Status is not displayed in the Header");
        return status;
    }

    /**
     * Method to click the first app in jobs table , navigate to the details page.
     * and verify Cost App details Page .
     */
    public String verifyCost(DbxSubTopPanelModulePageObject dballApps, ExtentTest test) {
        double sum =0.00;
        String cost = dballApps.costValue.getText().trim();
        String costValue = cost.replaceAll("[^\\d-]", "");
        String costDbu = dballApps.costDbu.getText().trim();
        String costDbuValue = costDbu.replaceAll("[^\\d-]", "");
        logger.info("cost is " + costValue);
        logger.info("cost DBU is " + costDbu);
        test.log(LogStatus.PASS, "cost is " + costValue);
        test.log(LogStatus.PASS, "cost DBU is " + costDbu);
        waitExecuter.waitUntilElementClickable(dbSubTopPanelModulePageObject.clickOnAppId);
        dbSubTopPanelModulePageObject.clickOnAppId.click();
        waitExecuter.waitUntilElementClickable(dbSubTopPanelModulePageObject.closeIcon);
        waitExecuter.waitUntilPageFullyLoaded();
        String appCost = dballApps.appCost.getText().trim();
        String appCostValue = appCost.replaceAll("[^\\d.]+", "");
        String appCostDbu = dballApps.appCostDbu.getText().trim();
        String appCostDbuValue = appCostDbu.replaceAll("\"\\\\.\",\"\"", "");
        test.log(LogStatus.PASS, "cost value in summary page is " + costValue);
        test.log(LogStatus.PASS, "cost DBU value in  summary page is " + costDbu);
        Assert.assertEquals(costValue, appCostValue, "Runs Cost is not displayed in the Header");
        Assert.assertEquals(costDbuValue, appCostDbuValue, "Runs Cost DBU is not displayed in the Header");
        return ("$ "+String.format("%.2f",sum));
    }


    /**
     * Method to click the first app in Finished table , navigate to the details page.
     * and verify Status App details Page .
     */
    public String verifyStatusInFinishedTab(DbxSubTopPanelModulePageObject dballApps) {
        String statusTable = dballApps.statusFinishedTab.getText().trim().toLowerCase();
        logger.info("Application Status is " + statusTable);
        waitExecuter.waitUntilElementClickable(dbSubTopPanelModulePageObject.finishedAppId);
        dbSubTopPanelModulePageObject.finishedAppId.click();
        waitExecuter.waitUntilElementClickable(dbSubTopPanelModulePageObject.closeIcon);
        waitExecuter.waitUntilPageFullyLoaded();
        String status = dballApps.appStatus.getText().trim().toLowerCase();
        Assert.assertEquals(statusTable, status, "Runs Status is not displayed in the Header");
        return status;
    }


    /* To reset the settings made in apps page */
    public void reset() {
        logger.info("Reset username filter");
        waitExecuter.waitUntilElementClickable(applicationsPageObject.resetButton);
        userAction.performActionWithPolling(applicationsPageObject.resetButton, UserAction.CLICK);
        waitExecuter.waitUntilElementClickable(applicationsPageObject.resetButton);
    }

    public int clickOnStatusSort() {
        int countOfIconSort = dbSubTopPanelModulePageObject.iconSort.size();
        logger.info("Number of total icon sort :" + countOfIconSort);
        if (countOfIconSort > 0) {
            for (int i = 0; i < countOfIconSort - 1; i++) {
                waitExecuter.sleep(2000);
                dbSubTopPanelModulePageObject.iconSort.get(i).click();
                waitExecuter.sleep(3000);
                int tableCount = dbSubTopPanelModulePageObject.listStaus.size();
                logger.info("Count of Table :" + tableCount);

            }
        }
        return countOfIconSort;
    }

    public int clickOnIdsSort() {
        int countOfIconSort = dbSubTopPanelModulePageObject.iconSort.size();
        logger.info("Number of total icon sort :" + countOfIconSort);
        if (countOfIconSort > 0) {
            for (int i = 0; i < countOfIconSort - 1; i++) {
                waitExecuter.sleep(2000);
                dbSubTopPanelModulePageObject.iconSort.get(i).click();
                waitExecuter.sleep(3000);
                int tableCount = dbSubTopPanelModulePageObject.listIds.size();
                logger.info("Count of Table :" + tableCount);

            }
        }
        return countOfIconSort;
    }

    /* Select last 30 days from date range */
    public void select30Days() {
        logger.info("Select last 30 days");
        datePicker.clickOnDatePicker();
        waitExecuter.waitUntilElementClickable(applicationsPageObject.resetButton);
        datePicker.selectLast30Days();
        waitExecuter.waitUntilElementClickable(applicationsPageObject.resetButton);
    }
}
