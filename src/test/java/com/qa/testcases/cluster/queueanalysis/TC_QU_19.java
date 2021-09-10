package com.qa.testcases.cluster.queueanalysis;

import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.pagefactory.clusters.QueueAnalysisPageObject;
import com.qa.scripts.clusters.QueueAnalysis;
import com.qa.utils.WaitExecuter;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.TimeoutException;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.logging.Logger;

@Marker.QueueAnalysis
@Marker.All
public class TC_QU_19 extends BaseClass {
    private static final Logger LOGGER = Logger.getLogger(TC_QU_19.class.getName());

    @Test(description = "P0-Validate Memory graph data points for selected queue")
    public void validateMemoryDataPoint() {
        test = extent.startTest("TC_QU_19.validateMemoryDataPoint", "Validate Memory graphs data points for a queue");
        test.assignCategory("Jobs - Queue Analysis");
        test.log(LogStatus.INFO, "Login to the application");
        // Initialize all classes objects
        test.log(LogStatus.INFO, "Initialize all class objects");
        LOGGER.info("Initialize all class objects");
        QueueAnalysisPageObject qaPageObject = new QueueAnalysisPageObject(driver);
        QueueAnalysis queueAnalysis = new QueueAnalysis(driver);
        WaitExecuter waitExecuter = new WaitExecuter(driver);
        // Navigate to Queue Analysis tab from header
        test.log(LogStatus.INFO, "Navigate to Queue Analysis tab from header");
        test.log(LogStatus.INFO, "Clicked on Queue Analysis tab");
        test.log(LogStatus.INFO, "Validate Queue Analysis tab loaded successfully");
        queueAnalysis.navigateToQueueAnalysis();
        queueAnalysis.runAQueueAnalysisReport();
        waitExecuter.waitUntilTextToBeInWebElement(qaPageObject.successBanner,
                "SUCCESS");
        String winHandleBefore = driver.getWindowHandle();

        try {
            waitExecuter.waitUntilTextToBeInWebElement(qaPageObject.successBanner,
                    "SUCCESS");
            queueAnalysis.navigateToQueueTable();
            // Verify if the queues are present
            test.log(LogStatus.INFO, "Verify if the queues are present in table");
            LOGGER.info("Verify if the queues are present in table");
            if (qaPageObject.getQueueList.size() > 0) {
                queueAnalysis.select1stQueueFromTable();
                // Click on data point of graph and navigate to Resource tab
                test.log(LogStatus.INFO, "Click on data point of graph and navigate to Resource tab");
                LOGGER.info("Click on data point of graph and navigate to Resource tab");
                queueAnalysis.clickOnDatapointOfGraph(driver, qaPageObject.memoryGraph);
                // Switch to child browser
                test.log(LogStatus.INFO, "Switch to child browser");
                LOGGER.info("Switch to child browser");
                queueAnalysis.getWindowHandles();

                // Assert that Resource tabs is loaded
                test.log(LogStatus.INFO, "Assert that Resource tabs is loaded");
                LOGGER.info("Assert that Resource tabs is loaded");
                try {
                    Assert.assertEquals(qaPageObject.yarnResources.getText().trim(), "Yarn Resource Usage",
                            "Unable to load child browser");
                    test.log(LogStatus.PASS, "Tool tips verified of vcore graph ");
                    driver.close();
                    driver.switchTo().window(winHandleBefore);
                } catch (AssertionError ex) {
                    driver.close();
                    driver.switchTo().window(winHandleBefore);
                }

            } else {
                Assert.assertTrue(qaPageObject.whenNoQueuePresent.isDisplayed(), "No data available is not displayed");
                test.log(LogStatus.SKIP, "There are no queue present in table");
                driver.close();
                driver.switchTo().window(winHandleBefore);
            }
        } catch (TimeoutException te) {
            throw new AssertionError("Queue Analysis Report not completed successfully.");
        }
        //Refresh the page and reload to original state
        test.log(LogStatus.INFO, "Refresh the page and reload to original state");
        LOGGER.info("Refresh the page and reload to original state");
        waitExecuter.sleep(1000);
        driver.navigate().refresh();
        waitExecuter.waitUntilElementClickable(qaPageObject.addIcon);
    }
}