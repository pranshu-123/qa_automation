package com.qa.testcases.migration.cloud_mapping_per_host;

import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.enums.migration.CloudProduct;
import com.qa.scripts.migration.CloudMigrationPerHostPage;
import com.qa.utils.LoggingUtils;
import com.qa.utils.WaitExecuter;
import com.qa.utils.actions.UserActions;
import org.openqa.selenium.TimeoutException;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * @author Ankur Jaiswal
 */
@Marker.All
@Marker.CloudMappingPerHost
public class TC_CMP_83 extends BaseClass {

    private final static LoggingUtils LOGGER = new LoggingUtils(TC_CMP_83.class);
    /**
     * Verify the report generated for Cloud provider - Google Compute Engine (IaaS)
     * displays the name of the cloud provider
     */
    @Test
    public void verifyCPNameForGCP() {
        test = extent.startTest("TC_CMP_83.verifyCPNameForGCP", "Verify the report generated for Cloud provider - " +
            "Google Compute Engine (IaaS) displays the name of the cloud provider");
        test.assignCategory("Migration/Cloud Mapping Per Host");

        CloudMigrationPerHostPage cloudMigrationPerHostPage = new CloudMigrationPerHostPage(driver);
        UserActions actions = new UserActions(driver);
        WaitExecuter waitExecuter = new WaitExecuter(driver);
        LOGGER.info("Navigate to migration host page", test);
        cloudMigrationPerHostPage.navigateToCloudMappingPerHost();

        LOGGER.info("Click on Run button", test);
        cloudMigrationPerHostPage.clickOnRunButton();
        cloudMigrationPerHostPage.waitTillLoaderPresent();

        cloudMigrationPerHostPage.selectCloudProduct(CloudProduct.GOOGLE_COMPUTE_ENGINE);
        cloudMigrationPerHostPage.waitTillLoaderPresent();
        cloudMigrationPerHostPage.checkUncheckColumn(false);
        cloudMigrationPerHostPage.clickOnRunReportButton();
        try {
            waitExecuter.waitUntilTextToBeInWebElement(cloudMigrationPerHostPage.getConfirmationMessage(),
                "Cloud Mapping Per Host completed successfully.");
        } catch (TimeoutException te) {
            Assert.assertTrue(false, "Cloud Mapping Per Host is not completed");
        }
        cloudMigrationPerHostPage.waitTillLoaderPresent();
        waitExecuter.sleep(10000);

        Assert.assertEquals(cloudMigrationPerHostPage.getCloudProductAndServicesOnReportPage(),
            CloudProduct.GOOGLE_COMPUTE_ENGINE.getValue());
        LOGGER.pass("Correct cloud product and services displayed.", test);
    }
}
