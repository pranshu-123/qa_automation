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

/*
 * @author- Ojasvi Pandey
 */
@Marker.QueueAnalysis
@Marker.All
public class TC_QU_47 extends BaseClass {
    private static final Logger LOGGER = Logger.getLogger(TC_QU_47.class.getName());

    @Test(description = "P2-Validate when invalid queue is searched then No Result found is displayed")
    public void validateInvalidQueueSearch() {
        test = extent.startTest("TC_QU_47.validateInvalidQueueSearch",
                "Validate when invalid queue is searched then No Result found is displayed");
        test.assignCategory("Jobs - Queue Analysis");
        test.log(LogStatus.INFO, "Login to the application");
        // Initialize all classes objects
        test.log(LogStatus.INFO, "Initialize all class objects");
        LOGGER.info("Initialize all class objects");
        QueueAnalysisPageObject qaPageObject = new QueueAnalysisPageObject(driver);
        WaitExecuter wait = new WaitExecuter(driver);
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
                queueAnalysis.searchQueueName("invalid_queue_unravel_data");
                String message = "no results found";
                LOGGER.info("Expected message- " + message);
                LOGGER.info("Actual message- " + qaPageObject.invalidQueueMessage.getText().trim().toLowerCase());
                Assert.assertEquals(qaPageObject.invalidQueueMessage.getText().trim().toLowerCase(), message,
                        "On providing invalid queue name, expected message is not displayed");
                test.log(LogStatus.PASS, "Invalid queue message dispalyed.");
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
