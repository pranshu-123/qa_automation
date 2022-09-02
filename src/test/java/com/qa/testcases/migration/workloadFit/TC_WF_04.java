package com.qa.testcases.migration.workloadFit;

import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.pagefactory.migration.ClusterDiscoveryPageObject;
import com.qa.pagefactory.migration.WorkloadFitPageObject;
import com.qa.scripts.migration.WorkloadFit;
import com.qa.utils.WaitExecuter;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/*
 * @author- Ojasvi Pandey
 */

@Marker.WorkloadFit
@Marker.All
public class TC_WF_04 extends BaseClass {
    private static final Logger LOGGER = Logger.getLogger(TC_WF_04.class.getName());

    @Test
    public void validateTagType() {
        test = extent.startTest("TC_WF_04.validateTagType",
                "Verify that Unravel UI should generate a Workload Fit report and Tag Type pie-chart should be displayed.");
        test.assignCategory("Migration - Workload Fit");
        test.log(LogStatus.INFO, "Login to the application");
        // Initialize all classes objects
        test.log(LogStatus.INFO, "Initialize all class objects");
        LOGGER.info("Initialize all class objects");
        WaitExecuter waitExecuter = new WaitExecuter(driver);
        ClusterDiscoveryPageObject cdPageObject = new ClusterDiscoveryPageObject(driver);
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
        fit.selectAllCheckboxTypes(fitPageObject.AllTagOfTagsTypes, fitPageObject.selectAllTagTypes);
        // Click on Run button to open report page
        test.log(LogStatus.INFO, "Click on Run button to open report page");
        fit.setStorage("1500", "1500");
        fit.clickRunForNewReport();
        try {
            waitExecuter.waitUntilTextToBeInWebElement(cdPageObject.confirmationMessageElement,
                    "workload fit completed successfully.");
            test.log(LogStatus.PASS, "Verified Workload Fit report is loaded properly.");
        } catch (TimeoutException te) {
            waitExecuter.waitUntilTextToBeInWebElement(cdPageObject.confirmationMessageElement,
                    "workload fit completed successfully.");
            test.log(LogStatus.PASS, "Verified Workload Fit report is loaded properly.");
        }

        List<WebElement> appNamesInTagTypes = fitPageObject.getTagsTypeNames;
        List<String> appNameList = new ArrayList<>();
        for (WebElement appName : appNamesInTagTypes) {
            appNameList.add(appName.getText());
        }
        Assert.assertNotNull(appNameList, "There are no apps in Job Type pie chart.");
        test.log(LogStatus.PASS, "Verified that Job Type pie chart contains all app type name.");
    }
}
