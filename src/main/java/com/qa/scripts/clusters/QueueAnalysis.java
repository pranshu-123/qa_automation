package com.qa.scripts.clusters;

import com.qa.enums.UserAction;
import com.qa.pagefactory.CommonPageObject;
import com.qa.pagefactory.TopPanelPageObject;
import com.qa.pagefactory.clusters.QueueAnalysisPageObject;
import com.qa.utils.ActionPerformer;
import com.qa.utils.MouseActions;
import com.qa.utils.WaitExecuter;
import com.qa.utils.actions.UserActions;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
@author - Ojasvi Pandey
This class contains all the action methods related to Queue Analysis
 */
public class QueueAnalysis {
    private static final Logger LOGGER = Logger.getLogger(QueueAnalysis.class.getName());
    private final WebDriver driver;
    private final WaitExecuter waitExecuter;
    private final QueueAnalysisPageObject queueAnalysisPageObject;
    private final TopPanelPageObject topPanelPageObject;
    private final List<String> memoryTooltipValues;
    private final List<String> jobsTooltipValues;
    private final List<String> vcoresTooltipValues;
    private final UserActions userAction;
    private CommonPageObject commonPageObject;

    /**
     * Constructor to initialize wait, driver and necessary objects
     *
     * @param driver - WebDriver instance
     */
    public QueueAnalysis(WebDriver driver) {
        this.driver = driver;
        waitExecuter = new WaitExecuter(driver);
        queueAnalysisPageObject = new QueueAnalysisPageObject(driver);
        topPanelPageObject = new TopPanelPageObject(driver);
        memoryTooltipValues = new ArrayList<String>();
        jobsTooltipValues = new ArrayList<String>();
        vcoresTooltipValues = new ArrayList<String>();
        userAction = new UserActions(driver);
    }

    /*
     * This method is to close the confirmation message that appears on landing of
     * page
     */
    public void closeConfirmationMessageNotification() {
        if (queueAnalysisPageObject.confirmationMessageElementClose.size() > 0) {
            waitExecuter.waitUntilElementClickable(queueAnalysisPageObject.confirmationMessageElementClose.get(0));
            userAction.performActionWithPolling(queueAnalysisPageObject.confirmationMessageElementClose.get(0),
                    UserAction.CLICK);
        }
    }

    /* Method to select Cluster in Modal box */
    public void selectMultiClusterId(String clusterId) {
        waitExecuter.sleep(2000);
        commonPageObject = new CommonPageObject(driver);
        MouseActions.clickOnElement(driver, commonPageObject.clusterDropdown);
        waitExecuter.sleep(3000);
        System.out.println("Size of cluster in dropdown: " + commonPageObject.clustersList.size());
        waitExecuter.waitUntilElementPresent(commonPageObject.clusterSearchBox);
        commonPageObject.clusterSearchBox.sendKeys(clusterId);
        commonPageObject.clusterSearchFirstField.click();
        waitExecuter.sleep(2000);
    }

    /* Navigate to Queue Analysis Tab */
    public void navigateToQueueAnalysis() {
        LOGGER.info("Navigate to Queue Analysis tab from header");
        waitExecuter.waitUntilElementClickable(topPanelPageObject.reportsHeader);
        waitExecuter.sleep(4000);
        // Click on Queue Analysis tab
        LOGGER.info("Clicked on Queue Analysis tab");
        userAction.performActionWithPolling(topPanelPageObject.reportsHeader, UserAction.CLICK);
        waitExecuter.waitUntilElementClickable(queueAnalysisPageObject.addIcon);
    }

    /**
     * This methods returns the query tooltip values displayed at different
     * coordinates of memory/Vcores/Job graph
     *
     * @return List of tooltip values
     */
    public List<String> getMemoryTooltipValues() {
        return memoryTooltipValues;
    }

    public List<String> getJobsTooltipValues() {
        return jobsTooltipValues;
    }

    public List<String> getVcoresTooltipValues() {
        return vcoresTooltipValues;
    }

    /*This method is to navigate on different coordinates of  Queue graphs */
    public void navigateDifferentPointOnGraph(WebDriver driver, WebElement graphElement) {
        WaitExecuter waitExecuter = new WaitExecuter(driver);
        int width = graphElement.getSize().getWidth();
        int incremental = width / 10;
        int runningWidth = incremental;
        ActionPerformer actionPerformer = new ActionPerformer(driver);
        // When we use moveToElement, offsets are from the center of element
        actionPerformer.moveToTheElementByOffset(graphElement, 0, 0);
        waitExecuter.sleep(2000);

        for (int i = 0; i < 1; i++) {
            /**
             * Moving the cursor to the right of the graph since offsets are from the center
             * of element
             **/
            actionPerformer.moveToTheElementByOffset(graphElement, runningWidth, 0);
            waitExecuter.sleep(1000);
            try {
                memoryTooltipValues.add(queueAnalysisPageObject.memoryToolTip.getText());
                jobsTooltipValues.add(queueAnalysisPageObject.jobsToolTip.getText());
                vcoresTooltipValues.add(queueAnalysisPageObject.vcoresToolTip.getText());
            } catch (NoSuchElementException noSuchElementException) {

                LOGGER.log(Level.WARNING, "Unable to locate element on graph");
            }
            /**
             * Moving the cursor to the left of the graph since offsets are from the center
             * of element
             **/
            actionPerformer.moveToTheElementByOffset(graphElement, -runningWidth, 0);
            waitExecuter.sleep(1000);
            try {
                memoryTooltipValues.add(queueAnalysisPageObject.memoryToolTip.getText());
                jobsTooltipValues.add(queueAnalysisPageObject.jobsToolTip.getText());
                vcoresTooltipValues.add(queueAnalysisPageObject.vcoresToolTip.getText());
            } catch (NoSuchElementException noSuchElementException) {

                LOGGER.log(Level.WARNING, "Unable to locate element on graph");
            }
            runningWidth += incremental;
        }
    }

    public void closeConfirmationBoxAndClickQueue() {
        closeConfirmationMessageNotification();
        waitExecuter.sleep(2000);
        queueAnalysisPageObject.getQueueNameFromTable.get(0).click();
        waitExecuter.waitUntilElementPresent(queueAnalysisPageObject.loading);
        waitExecuter.sleep(3000);
    }

    /* Get expected MemToolTips */
    public List<String> expectedMemToolTips() {
        List<String> expectedMemoryToolTip = Arrays.asList("pendingb", "steadyfairshareb", "availablegb", "allocatedb",
                "reservedb", "fairshareb");
        waitExecuter.sleep(1000);
        navigateDifferentPointOnGraph(driver, queueAnalysisPageObject.memoryGraph);
        LOGGER.info("Expected tool tip values- " + expectedMemoryToolTip);
        return expectedMemoryToolTip;
    }

    /* Get Actual Mem tool tips */
    public List<String> actualMemToolTips() {
        List<String> memoryTooltipValues = getMemoryTooltipValues();
        List<String> validToolTipValues = new ArrayList<>();
        LOGGER.info("Memory tooltip values : " + memoryTooltipValues);
        String[] memoryToolTipSplit = memoryTooltipValues.get(0).split("●");
        LOGGER.info("After splitting values are- " + Arrays.toString(memoryToolTipSplit));
        for (String value : memoryToolTipSplit) {
            if (!value.trim().isEmpty()) {
                validToolTipValues.add(value.replaceAll("[^a-zA-Z_]", "").trim().toLowerCase());
            }
        }
        LOGGER.info("Actual tool tip values- " + validToolTipValues);
        return validToolTipValues;
    }

    /* Get vcores expected tool tips */
    public List<String> expectedVcoresToolTips() {
        List<String> expectedVcoresToolTip = Arrays.asList("reserved", "fairshare", "pending", "steadyfairshare",
                "available", "allocated");
        waitExecuter.sleep(1000);
        navigateDifferentPointOnGraph(driver, queueAnalysisPageObject.vcoresGraph);
        LOGGER.info("Expected tool tip values- " + expectedVcoresToolTip);
        return expectedVcoresToolTip;
    }

    /* Get actual vcores tool tips */
    public List<String> actualVcoresToolTips() {
        List<String> vcoresTooltipValues = getVcoresTooltipValues();
        List<String> validToolTipValues = new ArrayList<>();
        LOGGER.info("Vcores tooltip values : " + vcoresTooltipValues);
        String[] vcoresToolTipSplit = vcoresTooltipValues.get(0).split("●");
        LOGGER.info("After splitting values are- " + Arrays.toString(vcoresToolTipSplit));
        for (String value : vcoresToolTipSplit) {
            validToolTipValues.add(value.replaceAll("[^a-zA-Z_]", "").trim().toLowerCase());
        }
        LOGGER.info("Actual tool tip values- " + validToolTipValues);
        return validToolTipValues;
    }

    /* Get jobs expected tool tips */
    public List<String> expectedJobsToolTips() {
        List<String> expectedJobsToolTip = Arrays.asList("failed", "killed", "running", "completed", "pending",
                "submitted");
        waitExecuter.sleep(1000);
        navigateDifferentPointOnGraph(driver, queueAnalysisPageObject.jobsGraph);
        LOGGER.info("Expected tool tip values- " + expectedJobsToolTip);
        return expectedJobsToolTip;
    }

    /* Get actual jobs tool tips */
    public List<String> actualJobsToolTips() {
        List<String> jobsTooltipValues = getJobsTooltipValues();
        List<String> validToolTipValues = new ArrayList<>();
        LOGGER.info("Jobs tooltip values : " + jobsTooltipValues);
        String[] jobsToolTipSplit = jobsTooltipValues.get(0).split("●");
        LOGGER.info("After splitting values are- " + Arrays.toString(jobsToolTipSplit));
        for (String value : jobsToolTipSplit) {
            validToolTipValues.add(value.replaceAll("[^a-zA-Z_]", "").trim().toLowerCase());
        }
        LOGGER.info("Actual tool tip values- " + validToolTipValues);
        return validToolTipValues;
    }

    /* Click on a data point of graph */
    public void clickOnDatapointOfGraph(WebDriver driver, WebElement graphElement) {
        WaitExecuter waitExecuter = new WaitExecuter(driver);
        int width = graphElement.getSize().getWidth();
        int incremental = width / 10;
        int runningWidth = incremental;
        ActionPerformer actionPerformer = new ActionPerformer(driver);
        // When we use moveToElement, offsets are from the center of element
        actionPerformer.moveToTheElementByOffset(graphElement, 0, 0);
        waitExecuter.sleep(2000);

        for (int i = 0; i < 1; i++) {
            /**
             * Moving the cursor to the right of the graph since offsets are from the center
             * of element
             **/
            actionPerformer.moveToTheElementByOffsetAndClick(graphElement, runningWidth, 0);
            waitExecuter.sleep(1000);
        }
    }

    /* Get multiple windows handles */
    public void getWindowHandles() {
        waitExecuter.sleep(2000);
        try {
            for (String winHandle : driver.getWindowHandles()) {
                driver.switchTo().window(winHandle);
            }
            //waitExecuter.sleep(3000);
            waitExecuter.waitUntilElementClickable(queueAnalysisPageObject.resourceUsagePointer);
            userAction.performActionWithPolling(queueAnalysisPageObject.resourceUsagePointer, UserAction.CLICK);
        } catch (NoSuchWindowException e) {
            driver.close();
        }
    }

    /* Click on schedule button */
    public void clickOnScheduleButton() {
        waitExecuter.waitUntilElementClickable(queueAnalysisPageObject.scheduleButton);
        userAction.performActionWithPolling(queueAnalysisPageObject.scheduleButton, UserAction.CLICK);
    }

    /* Click on schedule button and assign multiple e-mails */
    public void scheduleWithMultiEmail(String name, List<String> multiEmail) {
        waitExecuter.waitUntilElementPresent(queueAnalysisPageObject.scheduleName);
        userAction.performActionWithPolling(queueAnalysisPageObject.scheduleName, UserAction.SEND_KEYS, name);
        for (String email : multiEmail) {
            userAction.performActionWithPolling(queueAnalysisPageObject.email, UserAction.SEND_KEYS, email);
            waitExecuter.waitUntilElementPresent(queueAnalysisPageObject.addEmail);
            MouseActions.clickOnElement(driver, queueAnalysisPageObject.addEmail);
        }
    }

    /* Click on schedule button of Modal window */
    public void clickOnModalScheduleButton() {
        try {
            MouseActions.clickOnElement(driver, queueAnalysisPageObject.modalScheduleButton);
        } catch (TimeoutException te) {
            MouseActions.clickOnElement(driver, queueAnalysisPageObject.modalScheduleButton);
        }
    }

    /* Validate success message on report creation */
    public void verifyScheduleSuccessMsg(String successMsg) {
        waitExecuter.waitUntilElementPresent(queueAnalysisPageObject.scheduleSuccessMsg);
        Assert.assertEquals(queueAnalysisPageObject.scheduleSuccessMsg.getText().toLowerCase(), successMsg,
                "The Schedule success " + "message mismatch");
    }

    /* Select day from drop-down */
    public void selectByDays(String dayToRun) {
        waitExecuter.waitUntilElementClickable(queueAnalysisPageObject.scheduleDays);
        Select scheduleTorunDropDown = new Select(queueAnalysisPageObject.scheduleDays);
        scheduleTorunDropDown.selectByVisibleText(dayToRun);

    }

    /* Select hour from drop-down */
    public void selectByHour(String hour) {
        waitExecuter.waitUntilElementClickable(queueAnalysisPageObject.hoursDropdown);
        Select scheduleTorunDropDown = new Select(queueAnalysisPageObject.hoursDropdown);
        scheduleTorunDropDown.selectByVisibleText(hour);

    }

    /* Select minute from drop-down */
    public void selectByMin(String min) {
        waitExecuter.waitUntilElementClickable(queueAnalysisPageObject.minutesDropdown);
        Select scheduleTorunDropDown = new Select(queueAnalysisPageObject.minutesDropdown);
        scheduleTorunDropDown.selectByVisibleText(min);

    }

    /* Define day and Time to select from drop-down */
    public void selectDayTime(String day, String hour, String min) {
        selectByDays(day);
        queueAnalysisPageObject.displayTime.click();
        selectByHour(hour);
        selectByMin(min);
    }

    /* Search by queue name in queue table */
    public void searchQueueName(String queueName) {
        userAction.performActionWithPolling(queueAnalysisPageObject.queueSearchBox, UserAction.CLICK);
        userAction.performActionWithPolling(queueAnalysisPageObject.queueSearchBox, UserAction.SEND_KEYS, queueName);

    }

    /* This method is to Select cluster for schedule and run reports */
    public void selectCluster(String clusterId) {
        waitExecuter.waitUntilElementClickable(queueAnalysisPageObject.clusterDropdown);
        userAction.performActionWithPolling(queueAnalysisPageObject.clusterDropdown, UserAction.CLICK);
        userAction.performActionWithPolling(queueAnalysisPageObject.clusterSearchbox, UserAction.CLICK);
        userAction.performActionWithPolling(queueAnalysisPageObject.clusterSearchbox, UserAction.SEND_KEYS, clusterId);
        userAction.performActionWithPolling(queueAnalysisPageObject.select1stClusterOption, UserAction.CLICK);
        waitExecuter.sleep(1000);
    }

    /* This method is to Click on run button to open Run Report modal window */
    public void openReportRunButton() {
        // Click on Run button to open report page
        LOGGER.info("Click on Run button to open report page");
        userAction.performActionWithPolling(queueAnalysisPageObject.runButton, UserAction.CLICK);
        waitExecuter.sleep(1000);
    }

    /*This method is to click on Run button of Modal window */
    public void modalRunButton() {
        LOGGER.info("Click on Run button of modal window");
        userAction.performActionWithPolling(queueAnalysisPageObject.modalRunButton, UserAction.CLICK);
        waitExecuter.sleep(1000);
        waitExecuter.waitUntilElementPresent(queueAnalysisPageObject.runButton);
        waitExecuter.waitUntilElementClickable(queueAnalysisPageObject.runButton);
    }

    /* This method is used to assign start date and end date for custom range */
    public void assignCustomDate(String startDate, String endDate) {
        LOGGER.info("Click on custom range");
        waitExecuter.waitUntilElementClickable(queueAnalysisPageObject.dateRange);
        userAction.performActionWithPolling(queueAnalysisPageObject.dateRange, UserAction.CLICK);
        LOGGER.info("Set start and end date");
        userAction.performActionWithPolling(queueAnalysisPageObject.customRange, UserAction.CLICK);
        waitExecuter.waitUntilElementClickable(queueAnalysisPageObject.startDate);
        userAction.performActionWithPolling(queueAnalysisPageObject.startDate, UserAction.CLICK);
        queueAnalysisPageObject.startDate.clear();
        waitExecuter.sleep(1000);
        userAction.performActionWithPolling(queueAnalysisPageObject.startDate, UserAction.SEND_KEYS, startDate);
        waitExecuter.waitUntilElementClickable(queueAnalysisPageObject.applyButton);
        LOGGER.info("Click on apply button");
        userAction.performActionWithPolling(queueAnalysisPageObject.applyButton, UserAction.CLICK);
    }

    public String getPageNumber() {
        String[] numOfPages = queueAnalysisPageObject.getNumberOfPagesOfReports.getText().split(" ");
        LOGGER.info("Total number of pages ------------ " + numOfPages[2]);
        queueAnalysisPageObject.enterPageNumberToNavigation.click();
        queueAnalysisPageObject.enterPageNumberToNavigation.clear();
        queueAnalysisPageObject.enterPageNumberToNavigation.sendKeys(numOfPages[2]);
        queueAnalysisPageObject.enterPageNumberToNavigation.sendKeys(Keys.ENTER);
        waitExecuter.sleep(2000);
        return numOfPages[2];
    }

    /* From different queues of table, select 1st queue of table*/
    public void select1stQueueFromTable() {
        waitExecuter.waitUntilElementClickable(queueAnalysisPageObject.getQueueList.get(0));
        queueAnalysisPageObject.getQueueList.get(0).click();
        waitExecuter.waitUntilElementPresent(queueAnalysisPageObject.loading);
        //waitExecuter.sleep(2000);
        try {
            waitExecuter.waitUntilElementClickable(queueAnalysisPageObject.queueGraphSearchBox.get(0));
        } catch (StaleElementReferenceException ex) {
            waitExecuter.waitUntilElementClickable(queueAnalysisPageObject.queueGraphSearchBox.get(0));
        }
    }

    /* From Reports page run a queue analysis report*/
    public void runAQueueAnalysisReport() {
        waitExecuter.waitUntilElementClickable(queueAnalysisPageObject.addIcon);
        queueAnalysisPageObject.addIcon.click();
        waitExecuter.waitUntilElementClickable(queueAnalysisPageObject.modalRunButton);
        waitExecuter.sleep(3000);
        //Click on Run button of modal window
        LOGGER.info("Click on Run button of modal window");
        waitExecuter.waitUntilElementClickable(queueAnalysisPageObject.modalRunButton);
        userAction.performActionWithPolling(queueAnalysisPageObject.modalRunButton, UserAction.CLICK);
        waitExecuter.waitUntilElementClickable(queueAnalysisPageObject.addIcon);
    }

    /* Navigate from Reports page to Queue analysis Queue table*/
    public void navigateToQueueTable() {
        waitExecuter.waitUntilElementClickable(queueAnalysisPageObject.clickOnQAReports);
        queueAnalysisPageObject.clickOnQAReports.click();
        waitExecuter.waitUntilElementClickable(queueAnalysisPageObject.select1stQAReport);
        queueAnalysisPageObject.select1stQAReport.click();
        waitExecuter.waitUntilElementClickable(queueAnalysisPageObject.getQueueList.get(0));
    }
}
