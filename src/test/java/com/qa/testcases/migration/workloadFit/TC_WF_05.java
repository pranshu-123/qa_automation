package com.qa.testcases.migration.workloadFit;

import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.pagefactory.migration.WorkloadFitPageObject;
import com.qa.scripts.migration.WorkloadFit;
import com.qa.utils.WaitExecuter;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.logging.Logger;

/*
 * @author- Ojasvi Pandey
 */

@Marker.WorkloadFit
@Marker.All
public class TC_WF_05 extends BaseClass {
    private static final Logger LOGGER = Logger.getLogger(TC_WF_05.class.getName());

    @Test
    public void validateHeatMapJobCount() {
        test = extent.startTest("TC_WF_05.validateHeatMapJobCount",
                "Verify that Unravel UI should generate Heat Map for Job Count.");
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
        fit.selectAllOptionsOfPieCharts();
        // Click on Generate Heap Map button
        test.log(LogStatus.INFO, "Click on Generate Heat Map button");
        fit.clickGenerateHeatMap();
        fit.selectHeatMapTypeFromDropdown("count");
        waitExecuter.waitUntilElementPresent(fitPageObject.heatMapHighchart);
        try {
            Assert.assertTrue(fitPageObject.heatMapHighchart.isDisplayed(),
                    "The heat map didnt get genrated for Job Count");
            test.log(LogStatus.PASS, "Heat map generated for Job count.");
        } catch (TimeoutException | NoSuchElementException te) {
            throw new AssertionError("Workload Fit Report not completed successfully.");
        }

    }
}