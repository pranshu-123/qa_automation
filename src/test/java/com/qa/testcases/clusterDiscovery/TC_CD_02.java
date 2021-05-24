package com.qa.testcases.clusterDiscovery;

import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.enums.UserAction;
import com.qa.pagefactory.DatePickerPageObject;
import com.qa.pagefactory.migration.ClusterDiscoveryPageObject;
import com.qa.scripts.migration.ClusterDiscovery;
import com.qa.testcases.cluster.queueanalysis.TC_QU_02;
import com.qa.utils.WaitExecuter;
import com.qa.utils.actions.UserActions;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.TimeoutException;
import org.testng.annotations.Test;

import java.util.logging.Logger;

@Marker.All
@Marker.MigrationClusterDiscovery
public class TC_CD_02 extends BaseClass {
    private static final Logger LOGGER = Logger.getLogger(TC_CD_02.class.getName());

    @Test
    public void validateReportWithDatePickerOptions() {
        test = extent.startTest("TC_CD_02.validateReportWithDatePickerOptions",
                "Verify that Unravel UI should generate a Cluster Discovery report successfully "
                        + "for all date picker option.");
        test.assignCategory("Migration - Cluster Discovery");
        test.log(LogStatus.INFO, "Login to the application");
        // Initialize all classes objects
        test.log(LogStatus.INFO, "Initialize all class objects");
        LOGGER.info("Initialize all class objects");
        WaitExecuter waitExecuter = new WaitExecuter(driver);
        DatePickerPageObject datePickerPageObject = new DatePickerPageObject(driver);
        ClusterDiscoveryPageObject discoveryPageObject = new ClusterDiscoveryPageObject(driver);
        ClusterDiscovery discovery = new ClusterDiscovery(driver);
        UserActions userAction = new UserActions(driver);

        // Navigate to Cluster Discovery tab from header
        test.log(LogStatus.INFO, "Navigate to Cluster Discovery tab from header");
        test.log(LogStatus.INFO, "Clicked on Cluster Discovery tab");
        test.log(LogStatus.INFO, "Validate Cluster Discovery tab loaded successfully");
        discovery.navigateToClusterDiscovery();
        // Close confirmation box
        test.log(LogStatus.INFO, "Closed the confirmation box");
        LOGGER.info("Closed the confirmation box");
        discovery.closeConfirmationMessageNotification();

        for (int i = 0; i < 4; i++) {
            // Click on Run button to open report page
            test.log(LogStatus.INFO, "Click on Run button to open report page");
            LOGGER.info("Click on Run button to open report page");
            userAction.performActionWithPolling(discoveryPageObject.runButton, UserAction.CLICK);
            waitExecuter.waitUntilPageFullyLoaded();

            //Click on date range
            test.log(LogStatus.INFO, "Click on date range");
            LOGGER.info("Click on date range");
            userAction.performActionWithPolling(datePickerPageObject.dateRange, UserAction.CLICK);
            waitExecuter.waitUntilPageFullyLoaded();

            String dateRangeValue = datePickerPageObject.dateRangeOptions.get(i).getText();
            userAction.performActionWithPolling(datePickerPageObject.dateRangeOptions.get(i), UserAction.CLICK);
            waitExecuter.waitUntilPageFullyLoaded();

            // Click on Run button of modal window
            test.log(LogStatus.INFO, "Click on Run button of modal window for date range- " + dateRangeValue);
            LOGGER.info("Click on Run button of modal window for date range- " + dateRangeValue);
            userAction.performActionWithPolling(discoveryPageObject.modalRunButton, UserAction.CLICK);
            waitExecuter.waitUntilPageFullyLoaded();

            try {
                waitExecuter.waitUntilTextToBeInWebElement(discoveryPageObject.confirmationMessageElement,
                        "Cluster Discovery completed successfully.");
                test.log(LogStatus.PASS, "Verified Cluster Discovery report is loaded properly for date range- " + dateRangeValue);
                waitExecuter.waitUntilElementPresent(discoveryPageObject.runButton);
                waitExecuter.waitUntilElementClickable(discoveryPageObject.runButton);
            } catch (TimeoutException te) {
                throw new AssertionError("Cluster Discovery Report not completed successfully for date range- " + dateRangeValue);
            }
            discovery.closeConfirmationMessageNotification();
        }
    }
}
