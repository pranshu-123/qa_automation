package com.qa.testcases.cluster.queueanalysis;

import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.pagefactory.clusters.QueueAnalysisPageObject;
import com.qa.scripts.clusters.QueueAnalysis;
import com.qa.utils.WaitExecuter;
import com.relevantcodes.extentreports.LogStatus;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.logging.Logger;

@Marker.QueueAnalysis
@Marker.All
public class TC_QU_09 extends BaseClass {
    private static final Logger LOGGER = Logger.getLogger(TC_QU_09.class.getName());

    @Test
    public void validateSortByJobsRunning() {
        test = extent.startTest("TC_QU_09.validateSortByJobsRunning",
                "Verify sorting up and down in Jobs Running");
        test.assignCategory("Jobs - Queue Analysis");
        test.log(LogStatus.INFO, "Login to the application");
        // Initialize all classes objects
        test.log(LogStatus.INFO, "Initialize all class objects");
        LOGGER.info("Initialize all class objects");
        WaitExecuter waitExecuter = new WaitExecuter(driver);
        QueueAnalysisPageObject qaPageObject = new QueueAnalysisPageObject(driver);
        QueueAnalysis queueAnalysis = new QueueAnalysis(driver);
        // Navigate to Queue Analysis tab from header
        test.log(LogStatus.INFO, "Navigate to Queue Analysis tab from header");
        test.log(LogStatus.INFO, "Clicked on Queue Analysis tab");
        test.log(LogStatus.INFO, "Validate Queue Analysis tab loaded successfully");
        queueAnalysis.navigateToQueueAnalysis();
        // Close confirmation message box and search the queue
        test.log(LogStatus.INFO, "Close confirmation message box and search the queue");
        LOGGER.info("Close confirmation message box and search the queue");
        queueAnalysis.closeConfirmationMessageNotification();
        waitExecuter.sleep(1000);
        // Sort down by Jobs running
        test.log(LogStatus.INFO, "Sort down by Jobs running");
        LOGGER.info("Sort down by Jobs running");
        qaPageObject.sortByJobs.click();
        waitExecuter.sleep(1000);
        Assert.assertTrue(qaPageObject.sortDown.isDisplayed(), "Sort down is not working");
        // Sort up by Jobs running
        test.log(LogStatus.INFO, "Sort up by Jobs running");
        LOGGER.info("Sort up by Jobs running");
        qaPageObject.sortByJobs.click();
        waitExecuter.sleep(1000);
        Assert.assertTrue(qaPageObject.sortUp.isDisplayed(), "Sort up is not working");
        test.log(LogStatus.PASS, "Verified sorting on Jobs running.");
        //Refresh the page and reload to original state
        test.log(LogStatus.INFO, "Refresh the page and reload to original state");
        LOGGER.info("Refresh the page and reload to original state");
        waitExecuter.sleep(1000);
        driver.navigate().refresh();
        waitExecuter.sleep(3000);
    }

}
