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
public class TC_QU_12 extends BaseClass {
    private static final Logger LOGGER = Logger.getLogger(TC_QU_12.class.getName());

    @Test
    public void validatePagination() {
        test = extent.startTest("TC_QU_12.validatePagination", "Validate pagination in Queue Analysis page.");
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

        // Verify if the queues are present
        test.log(LogStatus.INFO, "Verify if the queues are present in table");
        LOGGER.info("Verify if the queues are present in table");
        if (qaPageObject.getQueueNameFromTable.size() > 0) {

            // Close confirmation message box and search the queue
            test.log(LogStatus.INFO, "Close confirmation message box and search the queue");
            LOGGER.info("Close confirmation message box and search the queue");
            queueAnalysis.closeConfirmationBoxAndClickQueue();

            Assert.assertTrue(qaPageObject.pagination.size() > 0, "Pagination is not present");
            test.log(LogStatus.PASS, "Pagination is working");
        } else {
            Assert.assertTrue(qaPageObject.whenNoQueuePresent.isDisplayed(), "No data available is not displayed");
            LOGGER.info("There are no queue present in table.");
            test.log(LogStatus.SKIP, "There are no queue present in table");
        }

    }
}
