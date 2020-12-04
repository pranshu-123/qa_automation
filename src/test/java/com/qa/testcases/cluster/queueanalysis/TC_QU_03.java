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

/*
 * @author- Ojasvi Pandey
 */
@Marker.QueueAnalysis
@Marker.All
public class TC_QU_03 extends BaseClass {
    private static final Logger LOGGER = Logger.getLogger(TC_QU_03.class.getName());

    @Test
    public void validate() {
        test = extent.startTest("TC_QU_03.validateMultiClusterInRunReports",
                "Validate user is able to generate schedule report for multiple clusters for Run reports.");
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
        // Close confirmation box
        test.log(LogStatus.INFO, "Closed the confirmation box");
        LOGGER.info("Closed the confirmation box");
        queueAnalysis.closeConfirmationMessageNotification();
        waitExecuter.sleep(1000);
        // Open Run report
        test.log(LogStatus.INFO, "Click on Run button to open report page");
        queueAnalysis.openReportRunButton();
        // Set custom date
        test.log(LogStatus.INFO, "Set custom date for 1 year duration");
        queueAnalysis.assignCustomDate("01/01/2018", "01/01/2019");

        // Click on Run button of modal window
        test.log(LogStatus.INFO, "Click on Run button of modal window");
        queueAnalysis.modalRunButton();

        String message = "please make sure date range less than 30 days";
        Assert.assertEquals(qaPageObject.invalidDateRangeMessage.getText().trim().toLowerCase(), message,
                "The message displayed for invalid date range does not match the expected message");

        qaPageObject.close.click();
        waitExecuter.sleep(1000);

    }
}