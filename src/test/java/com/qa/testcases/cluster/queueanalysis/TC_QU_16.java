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

import java.util.List;
import java.util.logging.Logger;

@Marker.QueueAnalysis
@Marker.All
public class TC_QU_16 extends BaseClass {
    private static final Logger LOGGER = Logger.getLogger(TC_QU_16.class.getName());

    @Test(description = "P0-Validate Memory graph is loaded for selected queue")
    public void validateMemoryQueueGraph() {
        test = extent.startTest("TC_QU_16.validateMemoryQueueGraph", "Validate Memory graphs for a queue");
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
        try {
            waitExecuter.waitUntilTextToBeInWebElement(qaPageObject.successBanner,
                    "SUCCESS");
            queueAnalysis.navigateToQueueTable();
            // Verify if the queues are present
            test.log(LogStatus.INFO, "Verify if the queues are present in table");
            LOGGER.info("Verify if the queues are present in table");
            if (qaPageObject.getQueueList.size() > 0) {
                queueAnalysis.select1stQueueFromTable();

                // Get expected list of tool tips
                test.log(LogStatus.INFO, "Get expected list of tool tips");
                LOGGER.info("Get expected list of tool tips");
                List<String> expectedMemoryToolTip = queueAnalysis.expectedMemToolTips();
                test.log(LogStatus.INFO, "Expected tool tip values- " + expectedMemoryToolTip);
                // Get actual list of tool tips
                test.log(LogStatus.INFO, "Get actual list of tool tips");
                LOGGER.info("Get actual list of tool tips");
                List<String> actualToolTipValues = queueAnalysis.actualMemToolTips();
                test.log(LogStatus.INFO, "Actual tool tip values- " + actualToolTipValues);

                Assert.assertTrue(actualToolTipValues.containsAll(expectedMemoryToolTip),
                        "Tool tip of memory graph did not match set of values");
                test.log(LogStatus.PASS, "Tool tips verified of memory graph ");
            } else {
                Assert.assertTrue(qaPageObject.whenNoQueuePresent.isDisplayed(), "No data available is not displayed");
                test.log(LogStatus.SKIP, "There are no queue present in table");
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