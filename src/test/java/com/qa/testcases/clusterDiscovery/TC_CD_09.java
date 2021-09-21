package com.qa.testcases.clusterDiscovery;

import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.pagefactory.DatePickerPageObject;
import com.qa.pagefactory.migration.ClusterDiscoveryPageObject;
import com.qa.scripts.DatePicker;
import com.qa.scripts.migration.ClusterDiscovery;
import com.qa.utils.WaitExecuter;
import com.qa.utils.actions.UserActions;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.TimeoutException;
import org.testng.annotations.Test;

import java.util.logging.Logger;

@Marker.All
@Marker.MigrationClusterDiscovery
public class TC_CD_09 extends BaseClass {
    private static final Logger LOGGER = Logger.getLogger(TC_CD_09.class.getName());

    @Test
    public void validateErrorMessage() {
        test = extent.startTest("TC_CD_09.validateReportExecution",
                "Verify that Unravel UI should generate a error message " +
                        "when report is generated for less than 2 days.");
        test.assignCategory("Migration - Cluster Discovery");
        test.log(LogStatus.INFO, "Login to the application");
        // Initialize all classes objects
        test.log(LogStatus.INFO, "Initialize all class objects");
        LOGGER.info("Initialize all class objects");
        WaitExecuter waitExecuter = new WaitExecuter(driver);
        ClusterDiscoveryPageObject cdPageObject = new ClusterDiscoveryPageObject(driver);
        ClusterDiscovery discovery = new ClusterDiscovery(driver);
        UserActions userAction = new UserActions(driver);
        DatePickerPageObject datePickerPageObject = new DatePickerPageObject(driver);
        DatePicker date = new DatePicker(driver);

        // Navigate to Cluster Discovery tab from header
        test.log(LogStatus.INFO, "Navigate to Cluster Discovery tab from header");
        test.log(LogStatus.INFO, "Clicked on Cluster Discovery tab");
        test.log(LogStatus.INFO, "Validate Cluster Discovery tab loaded successfully");
        discovery.navigateToClusterDiscovery();
        // Close confirmation box
        test.log(LogStatus.INFO, "Closed the confirmation box");
        LOGGER.info("Closed the confirmation box");
        discovery.closeConfirmationMessageNotification();
        waitExecuter.sleep(1000);
        // Click on Run button to open report page
        test.log(LogStatus.INFO, "Click on Run button to open report page");
        discovery.clickRunForNewReport();

        // Click on custom date range and set dates
        test.log(LogStatus.INFO, "Click on date range");
        discovery.setCustomRange("09/14/2021", "09/15/2021");
        waitExecuter.waitUntilPageFullyLoaded();

        // Click on Run button of modal window
        test.log(LogStatus.INFO, "Click on Run button of modal window");
        discovery.clickRunModalButton();
        try {
            waitExecuter.waitUntilTextToBeInWebElement(cdPageObject.fatalMessage,
                    "Date range should be greater than two days.");
            test.log(LogStatus.PASS, "Verified Cluster Discovery report throws error " +
                    "when report is generated for less than 2 days.");
        } catch (TimeoutException te) {
            throw new AssertionError("Cluster Discovery Report not completed successfully.");
        }
    }
}
