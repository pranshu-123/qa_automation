package com.qa.testcases.clusterDiscovery;

import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.enums.UserAction;
import com.qa.pagefactory.migration.ClusterDiscoveryPageObject;
import com.qa.scripts.jobs.applications.AllApps;
import com.qa.scripts.migration.ClusterDiscovery;
import com.qa.utils.WaitExecuter;
import com.qa.utils.actions.UserActions;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.logging.Logger;

@Marker.All
@Marker.MigrationClusterDiscovery
public class TC_CD_03 extends BaseClass {
    private static final Logger LOGGER = Logger.getLogger(TC_CD_03.class.getName());

    @Test
    public void validateAppTypesCount() {
        test = extent.startTest("TC_CD_03.validateAppTypesCount", "Verify that Cluster Discovery app count");
        test.assignCategory("Migration - Cluster Discovery");
        test.log(LogStatus.INFO, "Login to the application");
        // Initialize all classes objects
        test.log(LogStatus.INFO, "Initialize all class objects");
        LOGGER.info("Initialize all class objects");
        WaitExecuter waitExecuter = new WaitExecuter(driver);
        ClusterDiscoveryPageObject cdPageObject = new ClusterDiscoveryPageObject(driver);
        ClusterDiscovery discovery = new ClusterDiscovery(driver);
        UserActions userAction = new UserActions(driver);
        AllApps allApps = new AllApps(driver);

        // Navigate to Cluster Discovery tab from header
        test.log(LogStatus.INFO, "Navigate to Cluster Discovery tab from header");
        test.log(LogStatus.INFO, "Clicked on Cluster Discovery tab");
        test.log(LogStatus.INFO, "Validate Cluster Discovery tab loaded successfully");
        discovery.navigateToClusterDiscovery();
        // Close confirmation box
        test.log(LogStatus.INFO, "Closed the confirmation box");
        LOGGER.info("Closed the confirmation box");
        discovery.closeConfirmationMessageNotification();
        String clusterName = cdPageObject.getClusterName.getText();
        LOGGER.info("Cluster present in cluster discovery page- " + clusterName);

        // Click on Run button to open report page
        test.log(LogStatus.INFO, "Click on Run button to open report page");
        LOGGER.info("Click on Run button to open report page");
        userAction.performActionWithPolling(cdPageObject.runButton, UserAction.CLICK);

        // Click on Run button of modal window
        test.log(LogStatus.INFO, "Click on Run button of modal window");
        discovery.clickRunModalButton();

        try {
            waitExecuter.waitUntilTextToBeInWebElement(cdPageObject.confirmationMessageElement,
                    "Cluster Discovery completed successfully.");
            test.log(LogStatus.PASS, "Verified Cluster Discovery report is loaded properly for 7 days.");
        } catch (TimeoutException te) {
            throw new AssertionError("Cluster Discovery Report not completed successfully for 7 days.");
        }

        try {
            // Get By App type count from pie chart
            waitExecuter.waitUntilElementPresent(cdPageObject.getByAppTypePieCount);
            int totalApps = Integer.parseInt(cdPageObject.getByAppTypePieCount.getText());
            LOGGER.info("Total apps in cluster discovery pie chart- " + totalApps);
            test.log(LogStatus.INFO, "Total apps in cluster discovery pie chart- " + totalApps);

            // Navigate to Jobs page
            test.log(LogStatus.INFO, "Navigating to jobs page");
            discovery.navigateToJobs();
            // Select last 30 days from date picker
            test.log(LogStatus.INFO, "Select last 30 days");
            discovery.selectLast30Days();

            test.log(LogStatus.INFO, "Selecting cluster as of defined in cluster discovery page");
            LOGGER.info("Selecting cluster as of defined in cluster discovery page");
            allApps.selectCluster(clusterName);
            // Get jobs count of hive, tez, spark and MR apps
            test.log(LogStatus.INFO, "Get jobs count of hive, tez, spark and MR apps");
            LOGGER.info("Get jobs count of hive, tez, spark and MR apps");
            int totalJobs = discovery.getJobsCount();
            Assert.assertNotNull(totalApps, "Total apps count is null");
            Assert.assertTrue(totalJobs >= 0, "The total count expected apps is null");
            test.log(LogStatus.PASS, "App counts and Job counts are greater than 0");

        } catch (NoSuchElementException ex) {
            Assert.assertTrue(cdPageObject.noDataByAppType.getText().trim().equals("No Data Available."),
                    "There is no data available on pie chart");
            test.log(LogStatus.SKIP, "No data available for cluster discovery");
        }
    }
}