package com.qa.testcases.clusterDiscovery;

import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.enums.UserAction;
import com.qa.pagefactory.migration.ClusterDiscoveryPageObject;
import com.qa.scripts.migration.ClusterDiscovery;
import com.qa.utils.FileUtils;
import com.qa.utils.WaitExecuter;
import com.qa.utils.actions.UserActions;
import com.relevantcodes.extentreports.LogStatus;
import org.testng.annotations.Test;

import java.util.logging.Logger;

@Marker.All
@Marker.MigrationClusterDiscovery
public class TC_CD_11 extends BaseClass {
    private static final Logger LOGGER = Logger.getLogger(TC_CD_11.class.getName());

    @Test
    public void validateJSONDownload() {
        test = extent.startTest("TC_CD_11.validateJSONDownload",
                "Verify that Unravel UI is able to download JSON in cluster discovery.");
        test.assignCategory("Migration - Cluster Discovery");
        test.log(LogStatus.INFO, "Login to the queuelication");
        // Initialize all classes objects
        test.log(LogStatus.INFO, "Initialize all class objects");
        LOGGER.info("Initialize all class objects");
        // WaitExecuter waitExecuter = new WaitExecuter(driver);
        ClusterDiscoveryPageObject discoveryPageObject = new ClusterDiscoveryPageObject(driver);
        ClusterDiscovery discovery = new ClusterDiscovery(driver);
        UserActions actions = new UserActions(driver);
        WaitExecuter waitExecuter = new WaitExecuter(driver);

        // Navigate to Cluster Discovery tab from header
        test.log(LogStatus.INFO, "Navigate to Cluster Discovery tab from header");
        test.log(LogStatus.INFO, "Clicked on Cluster Discovery tab");
        test.log(LogStatus.INFO, "Validate Cluster Discovery tab loaded successfully");
        discovery.navigateToClusterDiscovery();
        // Close confirmation box
        test.log(LogStatus.INFO, "Closed the confirmation box");
        LOGGER.info("Closed the confirmation box");
        discovery.closeConfirmationMessageNotification();

        // Click on component dropdown and json download button
        test.log(LogStatus.INFO, "Click on component dropdown");
        LOGGER.info("Click on component dropdown");
        waitExecuter.waitUntilElementClickable(discoveryPageObject.clickOnComponentDropdown);
        actions.performActionWithPolling(discoveryPageObject.clickOnComponentDropdown, UserAction.CLICK);
        test.log(LogStatus.INFO, "Click on JSON download button");
        LOGGER.info("Click on JSON download button");
        waitExecuter.waitUntilElementClickable(discoveryPageObject.downloadJsonButton);
        actions.performActionWithPolling(discoveryPageObject.downloadJsonButton, UserAction.CLICK);
        test.log(LogStatus.PASS, "Clicked on Download JSON");
        //Assert if the downloaded file name is- clusterdiscovery.json.json
        FileUtils.checkForFileNameInDownloadsFolder("clusterdiscovery.json.json");
        test.log(LogStatus.PASS, "Verified Download JSON files present in directory.");
    }

}
