package com.qa.testcases.migration.cloud_mapping_per_host;

import com.qa.base.BaseClass;
import com.qa.enums.migration.CloudProduct;
import com.qa.scripts.migration.CloudMigrationPerHostPage;
import com.qa.utils.LoggingUtils;
import com.qa.utils.WaitExecuter;
import com.qa.utils.actions.UserActions;
import org.testng.annotations.Test;

/**
 * @author Ankur Jaiswal
 */
public class TC_CMP_22 extends BaseClass {
    private static final LoggingUtils LOGGER = new LoggingUtils(TC_CMP_22.class);

    /**
     * Verify Unravel recommends the best GCP instance based on max capacity for "Lift and Shift" and cluster node
     * usage for "cost reduction" when all the instances in Run/Schedule are selected. Make sure that Unravel
     * recommends the most cost effective instance.
     */

    @Test
    public void verifyBestGCPInstance() {
        test = extent.startTest("TC_CMP_22.verifyBestGCPInstance", "Verify Unravel recommends the best GCP " +
            "instance based on max capacity.");
        test.assignCategory("Migration/Cloud Mapping Per Host");

        CloudMigrationPerHostPage cloudMigrationPerHostPage = new CloudMigrationPerHostPage(driver);
        UserActions actions = new UserActions(driver);
        WaitExecuter waitExecuter = new WaitExecuter(driver);
        LOGGER.info("Navigate to migration host page", test);
        cloudMigrationPerHostPage.navigateToCloudMappingPerHost();

        LOGGER.info("Click on Run button", test);
        cloudMigrationPerHostPage.clickOnRunButton();
        cloudMigrationPerHostPage.waitTillLoaderPresent();

        LOGGER.info("Select GCP as cloud product.", test);
        cloudMigrationPerHostPage.selectCloudProduct(CloudProduct.GOOGLE_COMPUTE_ENGINE);
        cloudMigrationPerHostPage.waitTillLoaderPresent();

        cloudMigrationPerHostPage.selectRegion("Sao Paulo (southamerica-east1)");
        cloudMigrationPerHostPage.waitTillLoaderPresent();

        cloudMigrationPerHostPage.validateCheapestIsDisplayedInRecommendation(test);
        cloudMigrationPerHostPage.verifyTotalHoulyCost(test);
    }
}
