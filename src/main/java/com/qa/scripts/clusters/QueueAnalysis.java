package com.qa.scripts.clusters;

import com.qa.pagefactory.CommonPageObject;
import com.qa.pagefactory.TopPanelPageObject;
import com.qa.pagefactory.clusters.QueueAnalysisPageObject;
import com.qa.utils.ActionPerformer;
import com.qa.utils.JavaScriptExecuter;
import com.qa.utils.MouseActions;
import com.qa.utils.WaitExecuter;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.NoSuchWindowException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

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
    private final QueueAnalysisPageObject qaPageObject;
    private final List<String> memoryTooltipValues;
    private final List<String> jobsTooltipValues;
    private final List<String> vcoresTooltipValues;
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
        qaPageObject = new QueueAnalysisPageObject(driver);
        memoryTooltipValues = new ArrayList<String>();
        jobsTooltipValues = new ArrayList<String>();
        vcoresTooltipValues = new ArrayList<String>();
    }

    /*
     * This method is to close the confirmation message that appears on landing of
     * page
     */
    public void closeConfirmationMessageNotification() {
        if (queueAnalysisPageObject.confirmationMessageElementClose.size() > 0) {
            waitExecuter.waitUntilElementClickable(queueAnalysisPageObject.confirmationMessageElementClose.get(0));
            JavaScriptExecuter.clickOnElement(driver, queueAnalysisPageObject.confirmationMessageElementClose.get(0));
        }
    }

    /* Method to select Cluster in Modal box */
    public void selectMultiClusterId(String clusterId) {
        waitExecuter.sleep(2000);
        commonPageObject = new CommonPageObject(driver);
        MouseActions.clickOnElement(driver, commonPageObject.clusterDropdown);
        waitExecuter.sleep(2000);
        System.out.println("Size of cluster in dropdown: " + commonPageObject.clustersList.size());
        waitExecuter.waitUntilElementPresent(commonPageObject.clusterSearchBox);
        commonPageObject.clusterSearchBox.sendKeys(clusterId);
        commonPageObject.clusterSearchFirstField.click();
        waitExecuter.sleep(2000);
    }

    /* Navigate to Queue Analysis Tab */
    public void navigateToQueueAnalysis() {
        LOGGER.info("Navigate to Queue Analysis tab from header");
        waitExecuter.waitUntilElementClickable(topPanelPageObject.queueAnalysisTab);
        waitExecuter.sleep(4000);
        // Click on Queue Analysis tab
        LOGGER.info("Clicked on Queue Analysis tab");
        topPanelPageObject.queueAnalysisTab.click();
        waitExecuter.sleep(3000);
        // Validate Queue Analysis tab loaded successfully
        LOGGER.info("Validate Queue Analysis tab loaded successfully");
        waitExecuter.waitUntilElementPresent(qaPageObject.queueAnalysisHeading);
        waitExecuter.waitUntilPageFullyLoaded();
    }

    /**
     * This methods returns the query tooltip values displayed at different
     * coordinates of memory/query graph
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
        qaPageObject.getQueueNameFromTable.get(0).click();
        waitExecuter.waitUntilElementPresent(qaPageObject.loading);
        waitExecuter.sleep(3000);
    }

    /* Get expected MemToolTips */
    public List<String> expectedMemToolTips() {
        List<String> expectedMemoryToolTip = Arrays.asList("pendingb", "steadyfairshareb", "availablegb", "allocatedb",
                "reservedb", "fairshareb");
        waitExecuter.sleep(1000);
        navigateDifferentPointOnGraph(driver, qaPageObject.memoryGraph);
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
            validToolTipValues.add(value.replaceAll("[^a-zA-Z_]", "").trim().toLowerCase());
        }
        LOGGER.info("Actual tool tip values- " + validToolTipValues);
        return validToolTipValues;
    }

    /* Get vcores expected tool tips */
    public List<String> expectedVcoresToolTips() {
        List<String> expectedVcoresToolTip = Arrays.asList("reserved", "fairshare", "pending", "steadyfairshare",
                "available", "allocated");
        waitExecuter.sleep(1000);
        navigateDifferentPointOnGraph(driver, qaPageObject.vcoresGraph);
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
        navigateDifferentPointOnGraph(driver, qaPageObject.jobsGraph);
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
            waitExecuter.sleep(3000);
        } catch (NoSuchWindowException e) {
            driver.close();
        }
    }
}
