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

@Marker.CloudMappingPerHost
@Marker.All
public class TC_CMP_23 extends BaseClass {
    private static final LoggingUtils LOGGER = new LoggingUtils(TC_CMP_23.class);


    /**
     Verify Unravel recommends the best GCP instance based on max capacity for "Lift and Shift" and cluster node
     *usage for "cost reduction" when 5 of the instances in Run/Schedule are selected. Make sure that Unravel
     *recommends the most cost effective instance.
     */

    @Test
    public void verifyBestGCPInstanceWith5InstanceSelected(){
        test = extent.startTest("TC_CMP_22.verifyBestGCPInstanceWith5InstanceSelected", "Verify Unravel recommends the best GCP " +
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

        LOGGER.info("Select Object storage.", test);
        cloudMigrationPerHostPage.selectStorage("Object storage");
        cloudMigrationPerHostPage.waitTillLoaderPresent();

        LOGGER.info("Select Local Attached storage.", test);
        cloudMigrationPerHostPage.selectStorage("Local attached storage");
        cloudMigrationPerHostPage.waitTillLoaderPresent();

        cloudMigrationPerHostPage.checkUncheckColumn(false);
        cloudMigrationPerHostPage.checkUncheckColumn(true);

        cloudMigrationPerHostPage.selectCheckboxList(5);
        cloudMigrationPerHostPage.offerCustomPriceInReverseOrder();

        cloudMigrationPerHostPage.clickOnModalRunButton();
        try {
            waitExecuter.waitUntilTextToBeInWebElement(cloudMigrationPerHostPage.getConfirmationMessage(),
                    "Cloud Mapping Per Host completed successfully.");
        } catch (TimeoutException te) {
            Assert.assertTrue(false, "Cloud Mapping Per Host is not completed");
        }
        cloudMigrationPerHostPage.waitTillLoaderPresent();
        waitExecuter.sleep(30000);

        LOGGER.info("Navigate to migration host page", test);
        cloudMigrationPerHostPage.navigateToCloudMappingPerHost();
        LOGGER.info("Click on Run button", test);
        cloudMigrationPerHostPage.clickOnRunButton();
        cloudMigrationPerHostPage.waitTillLoaderPresent();

        LOGGER.info("Select GCP as cloud product.", test);
        cloudMigrationPerHostPage.selectCloudProduct(CloudProduct.AMAZON_EMR);
        cloudMigrationPerHostPage.waitTillLoaderPresent();

        cloudMigrationPerHostPage.selectRegion("Sao Paulo (southamerica-east1)");
        cloudMigrationPerHostPage.waitTillLoaderPresent();

        LOGGER.info("Select Object storage.", test);
        cloudMigrationPerHostPage.selectStorage("Object storage");
        cloudMigrationPerHostPage.waitTillLoaderPresent();

        LOGGER.info("Select Local Attached storage.", test);
        cloudMigrationPerHostPage.selectStorage("Local attached storage");
        cloudMigrationPerHostPage.waitTillLoaderPresent();

        cloudMigrationPerHostPage.validateCheapestIsDisplayedInRecommendationWith5InstanceSelectedWithCustomPrice(test);




    }
}
