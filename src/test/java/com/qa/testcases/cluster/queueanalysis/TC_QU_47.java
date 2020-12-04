package com.qa.testcases.cluster.queueanalysis;

import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.pagefactory.clusters.QueueAnalysisPageObject;
import com.qa.scripts.clusters.QueueAnalysis;
import com.relevantcodes.extentreports.LogStatus;
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

    @Test
    public void validateInvalidQueueSearch() {
        test = extent.startTest("TC_QU_47.validateInvalidQueueSearch",
                "Validate when invalid queue is searched then No Result found is displayed");
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

        // Close confirmation box and search the queue
        test.log(LogStatus.INFO, "Close confirmation message box and search the queue");
        LOGGER.info("Close confirmation message box and search the queue");
        queueAnalysis.closeConfirmationMessageNotification();
        queueAnalysis.searchQueueName("invalid_queue_unravel_data");

        String message = "no results found";
        LOGGER.info("Expected message- " + message);
        LOGGER.info("Actual message- " + qaPageObject.invalidQueueMessage.getText().trim().toLowerCase());
        Assert.assertEquals(qaPageObject.invalidQueueMessage.getText().trim().toLowerCase(), message,
                "On providing invalid queue name, expected message is not displayed");
        test.log(LogStatus.PASS, "Invalid queue message dispalyed.");

    }
}
