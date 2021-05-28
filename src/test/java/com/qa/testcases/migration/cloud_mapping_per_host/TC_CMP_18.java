package com.qa.testcases.migration.cloud_mapping_per_host;

import com.qa.annotations.Marker;
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
@Marker.CloudMappingPerHost
@Marker.All
public class TC_CMP_18 extends BaseClass {
    private static final LoggingUtils LOGGER = new LoggingUtils(TC_CMP_18.class);

    /**
     * Verify Unravel recommends the best EC2 instance based on max capacity for "Lift and Shift" and cluster node
     * usage for "cost reduction" when all the instances in Run/Schedule are selected. Make sure that Unravel
     * recommends the most cost effective instance.
     */

    @Test
    public void verifyBestEC2Instance() {
        test = extent.startTest("TC_CMP_18.verifyBestEC2Instance", "Verify Unravel recommends the best EC2 " +
            "instance based on max capacity.");
        test.assignCategory("Migration/Cloud Mapping Per Host");

        CloudMigrationPerHostPage cloudMigrationPerHostPage = new CloudMigrationPerHostPage(driver);
        UserActions actions = new UserActions(driver);
        WaitExecuter waitExecuter = new WaitExecuter(driver);
        LOGGER.info("Navigate to migration host page", test);
        cloudMigrationPerHostPage.navigateToCloudMappingPerHost();
        waitExecuter.waitUntilPageFullyLoaded();
        cloudMigrationPerHostPage.waitTillLoaderPresent();
        LOGGER.info("Click on Run button", test);
        cloudMigrationPerHostPage.clickOnRunButton();
        cloudMigrationPerHostPage.waitTillLoaderPresent();

        LOGGER.info("Select EC2 as cloud product.", test);
        cloudMigrationPerHostPage.selectCloudProduct(CloudProduct.AMAZON_EC2);
        cloudMigrationPerHostPage.waitTillLoaderPresent();

        cloudMigrationPerHostPage.selectRegion("South America (Sao Paulo)");
        cloudMigrationPerHostPage.waitTillLoaderPresent();

        cloudMigrationPerHostPage.validateCheapestIsDisplayedInRecommendation(test);
        cloudMigrationPerHostPage.verifyTotalHoulyCost(test);
    }
}
