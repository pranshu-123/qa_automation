package com.qa.testcases.clusterDiscovery;

import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.enums.UserAction;
import com.qa.pagefactory.migration.ClusterDiscoveryPageObject;
import com.qa.scripts.migration.ClusterDiscovery;
import com.qa.utils.WaitExecuter;
import com.qa.utils.actions.UserActions;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TimeoutException;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.logging.Logger;

@Marker.MigrationClusterDiscovery
@Marker.All
public class TC_CD_13 extends BaseClass {
    private static final Logger LOGGER = Logger.getLogger(TC_CD_13.class.getName());

    @Test
    public void validateHeatMapForMemory() {
        test = extent.startTest("TC_CD_13.validateHeatMapForMemory",
                "Verify that Unravel UI should generate a Cluster Discovery heat map for memory.");
        test.assignCategory("Migration - Cluster Discovery");
        test.log(LogStatus.INFO, "Login to the application");
        // Initialize all classes objects
        test.log(LogStatus.INFO, "Initialize all class objects");
        LOGGER.info("Initialize all class objects");
        WaitExecuter waitExecuter = new WaitExecuter(driver);
        ClusterDiscoveryPageObject cdPageObject = new ClusterDiscoveryPageObject(driver);
        ClusterDiscovery discovery = new ClusterDiscovery(driver);
        JavascriptExecutor executor = (JavascriptExecutor) driver;
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
        waitExecuter.sleep(1000);
        // Click on Run button to open report page
        test.log(LogStatus.INFO, "Click on Run button to open report page");
        discovery.clickRunForNewReport();

        // Click on Run button of modal window
        test.log(LogStatus.INFO, "Click on Run button of modal window");
        discovery.clickRunModalButton();
        try {
            waitExecuter.waitUntilTextToBeInWebElement(cdPageObject.confirmationMessageElement,
                    "Cluster Discovery completed successfully.");
            test.log(LogStatus.PASS, "Verified Cluster Discovery report is loaded properly.");

        } catch (TimeoutException te) {
            throw new AssertionError("Cluster Discovery Report not completed successfully.");
        }
        executor.executeScript("arguments[0].scrollIntoView();", cdPageObject.cpu_memoryHeatMap);
        userAction.performActionWithPolling(cdPageObject.cpuDropButton, UserAction.CLICK);
        userAction.performActionWithPolling(cdPageObject.selectMemoryinDropdown, UserAction.CLICK);
        Assert.assertTrue(cdPageObject.heatMapContainer.isDisplayed(), "Heat map for cpu not displayed");
        test.log(LogStatus.PASS, "Heat map for Memory verified");

    }
}
