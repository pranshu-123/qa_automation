package com.qa.scripts.clusters;


import com.qa.pagefactory.clusters.WorkloadPageObject;
import com.qa.utils.ActionPerformer;
import com.qa.utils.Log;
import com.qa.utils.MouseActions;
import com.qa.utils.WaitExecuter;
import com.qa.utils.actions.UserActions;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

/**
 * @author Sarbashree Ray
 */
public class Workload {

    private static final Logger LOGGER = Logger.getLogger(Workload.class.getName());
    private static Calendar cal;
    private static SimpleDateFormat dateFormatter;
    private static final Boolean isDignosticWin = false;
    UserActions userActions;
    private WebDriver driver;
    private WaitExecuter waitExecuter;
    private WorkloadPageObject workloadPageObject;
    private List<String> memoryTooltipValues;
    private List<String> queriesTooltipValues;

    /**
     * Constructor to initialize wait, driver and necessary objects
     *
     * @param driver - WebDriver instance
     */
    public Workload(WebDriver driver) {
        this.driver = driver;
        waitExecuter = new WaitExecuter(driver);
        workloadPageObject = new WorkloadPageObject(driver);
    }

    /**
     * Constuctor to initialize members
     */
    public Workload() {
        memoryTooltipValues = new ArrayList<String>();
        queriesTooltipValues = new ArrayList<String>();
    }

    public List<WebElement> getClustersList() {
        return workloadPageObject.clusterList;
    }

    /* Get the list of clusters from UI */
    public List<String> getListOfClusters(List<WebElement> clusterList) {
        int clusterSize = clusterList.size();
        List<String> listOfClusters = new ArrayList<>();

        if (clusterSize > 0) {
            for (int i = 0; i < clusterList.size(); i++) {
                String clusterNames = clusterList.get(i).getText();
                listOfClusters.add(clusterNames);
            }
        }
        return listOfClusters;
    }

    /**
     * This method is use to return all yarn jobs table rows.
     *
     * @return - workload jobs table rows
     */
    public List<WebElement> getworkloadJobsTableRecord() {
        return workloadPageObject.workloadJobsTableRecords;
    }

    public List<WebElement> getworkloadJobsTableHeaderNames() {
        return workloadPageObject.workloadJobsTableHeaderNames;
    }

    //click on cluster drop down
    public void selectWorkloadType(String workloadType) {
        // Click on Impala chargeback dropdown
        MouseActions.clickOnElement(driver, workloadPageObject.workloadDropdownOption);
        List<WebElement> userList = workloadPageObject.selectDropdown;
        String selectWorkload = null;
        for (int i = 0; i < userList.size(); i++) {
            if (userList.get(i).getText().equals(workloadType)) {
                selectWorkload = userList.get(i).getText();
                LOGGER.info("Selected Impala from dropdown " + selectWorkload);
                waitExecuter.waitUntilElementClickable(userList.get(i));
                MouseActions.clickOnElement(driver, userList.get(i));
                waitExecuter.sleep(2000);
            }
        }
    }

    //click on workload View By
    public void selectViewBy(String viewBy) {
        MouseActions.clickOnElement(driver, workloadPageObject.selectViewBy);
        List<WebElement> userList = workloadPageObject.selectDropdown;
        String selectWorkload = null;
        for (int i = 0; i < userList.size(); i++) {
            if (userList.get(i).getText().equals(viewBy)) {
                selectWorkload = userList.get(i).getText();
                LOGGER.info("Selected Impala from dropdown " + selectWorkload);
                waitExecuter.waitUntilElementClickable(userList.get(i));
                MouseActions.clickOnElement(driver, userList.get(i));
                waitExecuter.sleep(2000);
            }
        }
    }

    //click on workload Aggregate By
    public void selectAggregateBy(String aggregateBy) {
        MouseActions.clickOnElement(driver, workloadPageObject.aggregateBy);
        List<WebElement> userList = workloadPageObject.selectDropdown;
        String selectWorkload = null;
        for (int i = 0; i < userList.size(); i++) {
            if (userList.get(i).getText().equals(aggregateBy)) {
                selectWorkload = userList.get(i).getText();
                LOGGER.info("Selected Impala from dropdown " + selectWorkload);
                waitExecuter.waitUntilElementClickable(userList.get(i));
                MouseActions.clickOnElement(driver, userList.get(i));
                waitExecuter.sleep(2000);
            }
        }
    }

    public void selectGroupBy(String groupBy) {
        try {
            MouseActions.clickOnElement(driver, workloadPageObject.selectGroupBy);
            List<WebElement> userList = workloadPageObject.selectDropdown;
            String selectWorkload = null;
            for (int i = 0; i < userList.size(); i++) {
                if (userList.get(i).getText().equals(groupBy)) {
                    selectWorkload = userList.get(i).getText();
                    LOGGER.info("Selected Impala from dropdown " + selectWorkload);
                    waitExecuter.waitUntilElementClickable(userList.get(i));
                    MouseActions.clickOnElement(driver, userList.get(i));
                    waitExecuter.sleep(2000);
                }
            }
        } catch (NoSuchElementException e) {
            LOGGER.severe("Class Workload | Method selectGroupBy | Exception desc" + e.getMessage());
            throw (e);
        }
    }

    public void validateStagesTabs(WorkloadPageObject workloadPageObject) {
        // click the stageId to sort it
        /*driver.findElement(By.xpath("//*[@id='sparkStageNavigation-head']/tr/th[1]")).click();*/
        List<WebElement> stageRowList = workloadPageObject.tspanCal;
        verifyAssertFalse(stageRowList.isEmpty(), workloadPageObject, " No stages displayed for JobId");
        for (int i = 0; i < stageRowList.size(); i++) {
            Actions builder = new Actions(driver);
            builder.click((WebElement) stageRowList).build().perform();
            waitExecuter.waitUntilPageFullyLoaded();
        }
    }


    public void verifyAssertFalse(Boolean condition, WorkloadPageObject workloadPageObject, String msg) {
        String appDuration = "0";
        try {
            // appDuration = verifyRightPaneKpis(sparkAppPageObj);
            Assert.assertFalse(condition, msg);
        } catch (Throwable e) {
            // Close apps details page
            if (isDignosticWin)
                MouseActions.clickOnElement(driver, workloadPageObject.loadWinClose);
            else
                MouseActions.clickOnElement(driver, workloadPageObject.closeAppsPageTab);
            throw new AssertionError(msg + e.getMessage());
        }
    }

    /*Method to click on Jobs table Header */
    public String getJobstableHeader() {
        String gettableValue = workloadPageObject.jobtableHeader.getText().trim();
        LOGGER.info("The header value of Cpu graph " + gettableValue);
        return gettableValue;
    }

    /*Method to click on Jobs table Header */
    public String getselectedTimeRange() {
        String selectedTimeRange = workloadPageObject.timeRange.getText();
        LOGGER.info("The header value of Cpu graph " + selectedTimeRange);
        return selectedTimeRange;
    }

    public String clickOnDateRange(WorkloadPageObject workloadPageObject) {
        String pattern = "MM-dd-yyyy";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        String date = simpleDateFormat.format(new Date());
        List<WebElement> dateRange = workloadPageObject.tspanCal;
        waitExecuter.waitUntilPageFullyLoaded();
        Actions actions = new Actions(driver);
        Assert.assertFalse(dateRange.isEmpty(), "There are no date range listed , expected date range");
        String iconXpath = null;
        int index = 0;
        for (int i = 0; i < dateRange.size(); i++) {
            String reportName = dateRange.get(i).getText().trim();
            LOGGER.info("reportName: " + reportName);
            if (reportName.equals(date)) {
                LOGGER.info("The report name is " + reportName);
                index = i + 1;
                iconXpath = "(//*[name()='svg'])[4][" + index + "]//*[local-name()='g']//*[local-name()='text']//*[local-name()='tspan']";
                WebElement iconElement = driver.findElement(By.xpath(iconXpath));
                waitExecuter.waitUntilElementPresent(iconElement);
                actions.moveToElement(iconElement)
                        .contextClick()
                        .doubleClick(iconElement)
                        .perform();
                waitExecuter.waitUntilPageFullyLoaded();
                break;
            }
        }

        String statusXpath = null;
        if (index > 0) {
            statusXpath = "(//*[name()='svg'])[4][" + index + "]//*[local-name()='g']";
            //statusText = getStatusText(statusXpath);
        }
        return statusXpath;
    }

    public void navigateTextClickCheckworkloadTbl(WebDriver driver, WebElement graphElement) {
        WaitExecuter waitExecuter = new WaitExecuter(driver);

        int width = graphElement.getSize().getWidth();
        int height = graphElement.getSize().getHeight();

        int incrementalWidth = width / 100;
        int incrementalHeight = height / 100;

        int runningWidth = incrementalWidth;
        int runningHeight = incrementalHeight;

        ActionPerformer actionPerformer = new ActionPerformer(driver);
        // When we use moveToElement, offsets are from the center of element
        actionPerformer.moveToTheElementByOffset(graphElement, 0, 0);
        waitExecuter.sleep(2000);
        WorkloadPageObject workloadPageObject = new WorkloadPageObject(driver);

        for (int i = 0; i < 4; i++) {
            /** Moving the cursor to the left of the graph since offsets are from
             the center of element **/
            actionPerformer.moveToTheElementByOffset(graphElement, -runningWidth, 0);
            memoryTooltipValues.add(workloadPageObject.memoryTooltip.getText());
            //Move and click on the graph
            actionPerformer.moveToTheElementByOffsetAndClick(graphElement, -runningWidth, 0);

            waitExecuter.sleep(2000);
            if (workloadPageObject.workloadHeader.getText().contains("No workload queries")) {
                LOGGER.info("workload query header :" + workloadPageObject.workloadHeader.getText());
            } else {
                LOGGER.info("workload query header :" + workloadPageObject.workloadHeader.getText());
                //If Impala queries table populated then verufy for other details.

            }

            actionPerformer.moveToTheElementByOffset(graphElement, runningWidth, 0);
            memoryTooltipValues.add(workloadPageObject.memoryTooltip.getText());
            //Move and click on the graph
            actionPerformer.moveToTheElementByOffsetAndClick(graphElement, runningWidth, 0);

            waitExecuter.sleep(2000);
            if (!workloadPageObject.workloadHeader.getText().contains("No workload queries")) {
                LOGGER.info("workload query header :" + workloadPageObject.workloadHeader.getText());
                Log.info("workload query header :" + workloadPageObject.workloadHeader.getText());
            } else {
                LOGGER.info("workload query header :" + workloadPageObject.workloadHeader.getText());
                Log.info("workload query header :" + workloadPageObject.workloadHeader.getText());
            }
            runningWidth += incrementalWidth;
        }
    }

    /* Get list of Users from chargeback table */
    public List<String> getUsersFromTable() {

        List<WebElement> getAllUsers = workloadPageObject.getUsersFromworkloadTable;
        List<String> listOfUsers = new ArrayList<String>();

        for (int i = 0; i < getAllUsers.size(); i++) {
            String indivualUser = getAllUsers.get(i).getText();
            LOGGER.info("getUsersFromTable: " + indivualUser);
            listOfUsers.add(indivualUser);
        }
        LOGGER.info("List of users from chargeback table" + listOfUsers);
        return listOfUsers;
    }


    /**
     * Method to Click on workload tab
     */
    public void selectWorkloadTab() {
        WaitExecuter waitExecuter = new WaitExecuter(driver);
        waitExecuter.waitUntilElementClickable(workloadPageObject.workloadTab);
        MouseActions.clickOnElement(driver, workloadPageObject.workloadTab);
        waitExecuter.waitUntilPageFullyLoaded();
    }


    public static class ByStatusGraph {
        public static final String COLOR = "#007fad";
    }
}
