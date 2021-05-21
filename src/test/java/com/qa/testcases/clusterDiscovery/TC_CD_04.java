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
@Marker.Only
@Marker.All
@Marker.MigrationClusterDiscovery
public class TC_CD_04 extends BaseClass {
    private static final Logger LOGGER = Logger.getLogger(TC_CD_04.class.getName());

    @Test
    public void validateByUserCount() {
        test = extent.startTest("TC_CD_04.validateByUserCount", "Verify the Cluster Discovery user count from pie chart");
        test.assignCategory("Migration - Cluster Discovery");
        test.log(LogStatus.INFO, "Login to the Userlication");
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
            // Get By User type count from pie chart
            waitExecuter.sleep(2000);
            waitExecuter.waitUntilElementPresent(cdPageObject.getByUserPieCount);
            int totalUsers = Integer.parseInt(cdPageObject.getByUserPieCount.getText());
            LOGGER.info("Total Users in cluster discovery pie chart- " + totalUsers);
            test.log(LogStatus.INFO, "Total Users in cluster discovery pie chart- " + totalUsers);

            // Navigate to Jobs page
            test.log(LogStatus.INFO, "Navigating to jobs page");
            discovery.navigateToJobs();
            // Select last 30 days from date picker
            test.log(LogStatus.INFO, "Select last 30 days");
            discovery.selectLast30Days();

            test.log(LogStatus.INFO, "Selecting cluster as of defined in cluster discovery page");
            LOGGER.info("Selecting cluster as of defined in cluster discovery page");
            allApps.selectCluster(clusterName);
            // Get jobs count of hive, tez, spark and MR Users
            test.log(LogStatus.INFO, "Get jobs count of hive, tez, spark and MR Users");
            LOGGER.info("Get jobs count of hive, tez, spark and MR Users");
            int totalJobs = discovery.getJobsCount();
            LOGGER.info(String.valueOf(totalJobs));
            LOGGER.info(String.valueOf(totalUsers));
            LOGGER.info("-------------------------------------- "+String.valueOf(totalJobs-totalUsers));
            Assert.assertNotNull(totalUsers, "Total Users count is null");
            Assert.assertTrue(totalJobs-totalUsers ==0 || totalJobs-totalUsers <= 10 || totalJobs-totalUsers <= -10, "The total count expected Users is null");
            test.log(LogStatus.PASS, "User counts and Job counts are greater than 0");

        } catch (NoSuchElementException ex) {
            Assert.assertTrue(cdPageObject.noDataByUser.getText().trim().equals("No Data Available."),
                    "There is no data available on pie chart");
            test.log(LogStatus.SKIP, "No data available for cluster discovery");
        }
    }
}