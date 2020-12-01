package com.qa.testcases.cluster.queueanalysis;

import com.qa.base.BaseClass;
import com.qa.pagefactory.clusters.QueueAnalysisPageObject;
import com.qa.scripts.clusters.QueueAnalysis;
import com.relevantcodes.extentreports.LogStatus;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.logging.Logger;

public class TC_QU_18 extends BaseClass {
    private static final Logger LOGGER = Logger.getLogger(TC_QU_18.class.getName());

    @Test
    public void validateJobsDataPoint() {
        test = extent.startTest("TC_QU_18.validateJobsDataPoint", "Validate Jobs graphs data points for a queue");
        test.assignCategory("Jobs - Queue Analysis");
        test.log(LogStatus.INFO, "Login to the application");
        // Initialize all classes objects
        test.log(LogStatus.INFO, "Initialize all class objects");
        LOGGER.info("Initialize all class objects");
        QueueAnalysisPageObject qaPageObject = new QueueAnalysisPageObject(driver);
        QueueAnalysis queueAnalysis = new QueueAnalysis(driver);
        // Navigate to Queue Analysis tab from header
        test.log(LogStatus.INFO, "Navigate to Queue Analysis tab from header");
        test.log(LogStatus.INFO, "Clicked on Queue Analysis tab");
        test.log(LogStatus.INFO, "Validate Queue Analysis tab loaded successfully");
        queueAnalysis.navigateToQueueAnalysis();
        String winHandleBefore = driver.getWindowHandle();

        // Verify if the queues are present
        test.log(LogStatus.INFO, "Verify if the queues are present in table");
        LOGGER.info("Verify if the queues are present in table");
        if (qaPageObject.getQueueNameFromTable.size() > 0) {
            // Close confirmation message box and search the queue
            test.log(LogStatus.INFO, "Close confirmation message box and search the queue");
            LOGGER.info("Close confirmation message box and search the queue");
            queueAnalysis.closeConfirmationBoxAndClickQueue();

            // Click on data point of graph and navigate to Resource tab
            test.log(LogStatus.INFO, "Click on data point of graph and navigate to Resource tab");
            LOGGER.info("Click on data point of graph and navigate to Resource tab");
            queueAnalysis.clickOnDatapointOfGraph(driver, qaPageObject.jobsGraph);
            // Switch to child browser
            test.log(LogStatus.INFO, "Switch to child browser");
            LOGGER.info("Switch to child browser");
            queueAnalysis.getWindowHandles();

            // Assert that Resource tabs is loaded
            test.log(LogStatus.INFO, "Assert that Resource tabs is loaded");
            LOGGER.info("Assert that Resource tabs is loaded");
            Assert.assertEquals(qaPageObject.yarnResources.getText().trim(), "Yarn Resource Usage",
                    "Unable to load child browser");
            test.log(LogStatus.PASS, "Tool tips verified of vcore graph ");
            driver.close();
            driver.switchTo().window(winHandleBefore);

        } else {
            Assert.assertTrue(qaPageObject.whenNoQueuePresent.isDisplayed(), "No data available is not displayed");
            test.log(LogStatus.SKIP, "There are no queue present in table");
            driver.close();
            driver.switchTo().window(winHandleBefore);
        }

    }
}