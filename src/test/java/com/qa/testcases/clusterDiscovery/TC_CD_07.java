package com.qa.testcases.clusterDiscovery;

import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.pagefactory.migration.ClusterDiscoveryPageObject;
import com.qa.scripts.migration.ClusterDiscovery;
import com.qa.utils.WaitExecuter;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.TimeoutException;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Marker.All
@Marker.MigrationClusterDiscovery
public class TC_CD_07 extends BaseClass {
    private static final Logger LOGGER = Logger.getLogger(TC_CD_07.class.getName());

    @Test
    public void validateByUserInPie() {
        test = extent.startTest("TC_CD_07.validateByUserInPie",
                "Verify that Unravel UI pie chart By user should contain user name");
        test.assignCategory("Migration - Cluster Discovery");
        test.log(LogStatus.INFO, "Login to the userlication");
        // Initialize all classes objects
        test.log(LogStatus.INFO, "Initialize all class objects");
        LOGGER.info("Initialize all class objects");
        WaitExecuter waitExecuter = new WaitExecuter(driver);
        ClusterDiscoveryPageObject discoveryPageObject = new ClusterDiscoveryPageObject(driver);
        ClusterDiscovery discovery = new ClusterDiscovery(driver);

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
            waitExecuter.waitUntilTextToBeInWebElement(discoveryPageObject.confirmationMessageElement,
                    "Cluster Discovery completed successfully.");
            test.log(LogStatus.PASS, "Verified Cluster Discovery report is loaded properly.");
        } catch (TimeoutException te) {
            throw new AssertionError("Cluster Discovery Report not completed successfully.");
        }

        // Check whether the pie chart is present
        test.log(LogStatus.INFO, "Verify if By User pie-chart is present.");
        LOGGER.info("Verify if By User pie-chart is present.");
        Boolean isPieChartPresent = discovery.checkPieIsPresent(discoveryPageObject.getByUserTypesName);
        List<String> namesOfUsers = new ArrayList<String>();
        if (isPieChartPresent) {
            // Get all the names of user from By user Type chart
            test.log(LogStatus.INFO, "Get all the names of user from By user Type chart.");
            LOGGER.info("Get all the names of user from By user Type chart.");
            namesOfUsers = discovery.getPieChartOptions(discoveryPageObject.getByUserTypesName);
            test.log(LogStatus.INFO, "Names of user in pie chart- " + namesOfUsers);
            LOGGER.info("Names of user in pie chart- " + namesOfUsers);
            Assert.assertNotNull(namesOfUsers, "User in pie chart is null");
            test.log(LogStatus.PASS, "Users in pie chart is not empty.");
        } else {
            Assert.assertEquals(discoveryPageObject.noDataByUser.getText().trim().toLowerCase(), "no data available.",
                    "User are not present in pie chart nor No data is displayed on UI");
            test.log(LogStatus.SKIP, "No data is available in By USER Type pie chart");
        }

    }
}
