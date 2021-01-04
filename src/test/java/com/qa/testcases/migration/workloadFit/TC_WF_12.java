package com.qa.testcases.migration.workloadFit;

import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.pagefactory.migration.WorkloadFitPageObject;
import com.qa.scripts.migration.WorkloadFit;
import com.qa.utils.WaitExecuter;
import com.relevantcodes.extentreports.LogStatus;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.logging.Logger;

/*
 * @author- Ojasvi Pandey
 */

@Marker.WorkloadFit
@Marker.All
public class TC_WF_12 extends BaseClass {
    private static final Logger LOGGER = Logger.getLogger(TC_WF_12.class.getName());

    @Test
    public void validateJobTypeErrorMessage() {
        test = extent.startTest("TC_WF_12.validateJobTypeErrorMessage",
                "Verify that Unravel UI should populate a warning message to select at least one Job Type.");
        test.assignCategory("Migration - Workload Fit");
        test.log(LogStatus.INFO, "Login to the application");
        // Initialize all classes objects
        test.log(LogStatus.INFO, "Initialize all class objects");
        LOGGER.info("Initialize all class objects");
        WaitExecuter waitExecuter = new WaitExecuter(driver);
        WorkloadFit fit = new WorkloadFit(driver);
        WorkloadFitPageObject fitPageObject = new WorkloadFitPageObject(driver);
        // Navigate to Workload Fit tab from header
        test.log(LogStatus.INFO, "Navigate to Workload Fit tab from header");
        test.log(LogStatus.INFO, "Clicked on Workload Fit tab");
        test.log(LogStatus.INFO, "Validate Workload Fit tab loaded successfully");
        fit.navigateToWorkloadFit();

        // Zoom out the page by 30%
        fit.zoomOut("30");
        // Close confirmation box
        test.log(LogStatus.INFO, "Closed the confirmation box");
        LOGGER.info("Closed the confirmation box");
        fit.closeConfirmationMessageNotification();
        waitExecuter.sleep(1000);

        // Select last 7 days from report date range
        test.log(LogStatus.INFO, "Select last 7 days from date range.");
        LOGGER.info("Select last 7 days from date range.");
        fit.selectLast7Days();
        // Select All job types run in last 7 days
        test.log(LogStatus.INFO, "Select All job types run in last 7 days.");
        LOGGER.info("Select All job types run in last 7 days.");
        fit.deselectAllCheckboxType(fitPageObject.AllTagOfJobTypes, fitPageObject.selectAllJobTypes);

        // Assert that error message is displayed on de-selecting Job Type
        Assert.assertTrue(fitPageObject.noJobSelectedErrorMessage.size() > 0,
                "On de-selecting all text box the warning message to select at least one app type is not displayed");
        test.log(LogStatus.PASS,
                "On de-selecting all Job Type, warning message to select at-least one app type was displayed");
    }
}