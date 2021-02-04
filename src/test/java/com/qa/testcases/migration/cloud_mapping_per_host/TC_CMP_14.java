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
public class TC_CMP_14 extends BaseClass {
    private static final LoggingUtils LOGGER = new LoggingUtils(TC_CMP_14.class);

    /**
     * Verify Unravel recommends the best AZURE instance based on max capacity for "Lift and Shift"
     * and cluster node usage for "cost reduction" when 5 of the instances in Run/Schedule are selected.
     * Make sure that Unravel recommends the most cost effective instance.
     */
    @Test
    public void verifyBestAzureInstance() {
        test = extent.startTest("TC_CMP_14.verifyBestAzureInstance", "Verify Unravel recommends the best AZURE " +
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

        LOGGER.info("Select Azure as cloud product.", test);
        cloudMigrationPerHostPage.selectCloudProduct(CloudProduct.AZURE);
        cloudMigrationPerHostPage.waitTillLoaderPresent();

        cloudMigrationPerHostPage.selectRegion("Southeast Asia");
        cloudMigrationPerHostPage.waitTillLoaderPresent();

        cloudMigrationPerHostPage.validateCheapestIsDisplayedInRecommendation(test);
        cloudMigrationPerHostPage.verifyTotalHoulyCost(test);
    }
}