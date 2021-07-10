package com.qa.testcases.migration.cloud_mapping_per_host;

import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.enums.migration.CloudProduct;
import com.qa.scripts.migration.CloudMigrationPerHostPage;
import com.qa.utils.LoggingUtils;
import com.qa.utils.WaitExecuter;
import com.qa.utils.actions.UserActions;
import org.testng.annotations.Test;

@Marker.CloudMappingPerHost
@Marker.All
public class TC_CMP_26 extends BaseClass {
    private static final LoggingUtils LOGGER = new LoggingUtils(TC_CMP_26.class);

    /**
     * Verify Unravel recommends the best HDI instance based on max capacity for "Lift and Shift" and cluster node
     * usage for "cost reduction" when all the instances in Run/Schedule are selected. Make sure that Unravel
     * recommends the most cost effective instance.
     */

    @Test
    public void verifyBestHDIInstance() {
        test = extent.startTest("TC_CMP_26.verifyBestHDIInstance", "Verify Unravel recommends the best HDI " +
            "instance based on max capacity.");
        test.assignCategory("Migration/Cloud Mapping Per Host");

        CloudMigrationPerHostPage cloudMigrationPerHostPage = new CloudMigrationPerHostPage(driver);
        UserActions actions = new UserActions(driver);
        WaitExecuter waitExecuter = new WaitExecuter(driver);
        LOGGER.info("Navigate to migration host page", test);
        cloudMigrationPerHostPage.navigateToCloudMappingPerHost();

        LOGGER.info("Click on Run button", test);
        cloudMigrationPerHostPage.clickOnModalRunButton();
        cloudMigrationPerHostPage.waitTillLoaderPresent();

        LOGGER.info("Select HDI as cloud product.", test);
        cloudMigrationPerHostPage.selectCloudProduct(CloudProduct.AZURE_HD_INSIGHT);
        cloudMigrationPerHostPage.waitTillLoaderPresent();

        cloudMigrationPerHostPage.selectRegion("Southeast Asia");
        cloudMigrationPerHostPage.waitTillLoaderPresent();

        cloudMigrationPerHostPage.validateCheapestIsDisplayedInRecommendation(test);
        cloudMigrationPerHostPage.verifyTotalHoulyCost(test);
    }
}
