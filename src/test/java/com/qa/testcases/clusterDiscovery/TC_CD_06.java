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
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

@Marker.All
@Marker.MigrationClusterDiscovery
public class TC_CD_06 extends BaseClass {
    private static final Logger LOGGER = Logger.getLogger(TC_CD_06.class.getName());

    @Test
    public void validateAppNamesInPie() {
        test = extent.startTest("TC_CD_06.validateAppNamesInPie",
                "Verify that Unravel UI should not contain app names of impala, pig, cascading");
        test.assignCategory("Jobs - Cluster Discovery");
        test.log(LogStatus.INFO, "Login to the application");
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
        test.log(LogStatus.INFO, "Verify if By app pie-chart is present.");
        LOGGER.info("Verify if By app pie-chart is present.");
        Boolean isPieChartPresent = discovery.checkPieIsPresent(discoveryPageObject.getByAppTypesName);
        List<String> namesOfApps = new ArrayList<String>();
        List<String> notExpectedInList = new ArrayList<>(Arrays.asList("impala", "pig", "cascading"));
        if (isPieChartPresent) {
            // Get all the names of application from By App Type chart
            test.log(LogStatus.INFO, "Get all the names of application from By App Type chart.");
            LOGGER.info("Get all the names of application from By App Type chart.");
            namesOfApps = discovery.getPieChartOptions(discoveryPageObject.getByAppTypesName);
            test.log(LogStatus.INFO, "Names of app in pie chart- " + namesOfApps);
            LOGGER.info("Names of app in pie chart- " + namesOfApps);
            Assert.assertNotEquals(namesOfApps, notExpectedInList,
                    "Apps in pie chart contains either imapal, pig prcascading");
            test.log(LogStatus.PASS, "Options do not contain impala, pig, cascading.");
        } else {
            Assert.assertEquals(discoveryPageObject.noDataByAppType.getText().trim().toLowerCase(),
                    "no data available.", "Apps are not present nor No data is displayed on UI");
            test.log(LogStatus.SKIP, "No data is available in By App Type");
        }
    }
}
