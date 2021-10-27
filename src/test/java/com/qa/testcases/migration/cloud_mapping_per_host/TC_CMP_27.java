package com.qa.testcases.migration.cloud_mapping_per_host;

import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.enums.UserAction;
import com.qa.enums.migration.CloudProduct;
import com.qa.enums.migration.MigrationCloudMappingModalTable;
import com.qa.scripts.migration.CloudMigrationPerHostPage;
import com.qa.utils.LoggingUtils;
import com.qa.utils.actions.UserActions;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import com.qa.pagefactory.migration.CloudMappingPerHostPageObject;
import org.testng.annotations.Test;
import com.qa.utils.WaitExecuter;
import java.util.Arrays;
import java.util.List;


@Marker.CloudMappingPerHost
@Marker.All
public class TC_CMP_27 extends BaseClass {

    private static final LoggingUtils LOGGER = new LoggingUtils(TC_CMP_27.class);

    @Test
    public void verifyHDICostEffectivenessWithObjectStorage() {
        test = extent.startTest("TC_CMP_27.verifyEHDICostEffectivenessWithObjectStorage", "Verify Unravel recommends the best HDI instance based on max capacity for \"Lift and Shift\" and cluster node usage for \"cost reduction\" when 5 of the instances in Run/Schedule are selected. Make sure that Unravel recommends the most cost effective instance. " +
                "for Object Storage");

        test.assignCategory("Migration/Cloud Mapping Per Host");

        CloudMigrationPerHostPage cloudMigrationPerHostPage = new CloudMigrationPerHostPage(driver);
        CloudMappingPerHostPageObject cloudMappingPerHostPageObject = new CloudMappingPerHostPageObject(driver);
        WaitExecuter waitExecuter = new WaitExecuter(driver);
        LOGGER.info("Navigate to migration host page", test);
        cloudMigrationPerHostPage.navigateToCloudMappingPerHost();
        LOGGER.info("Click on Run button", test);
        cloudMigrationPerHostPage.clickOnRunButton();
        cloudMigrationPerHostPage.waitTillLoaderPresent();

        LOGGER.info("Select GCP as cloud product.", test);
        cloudMigrationPerHostPage.selectCloudProduct(CloudProduct.AZURE_HD_INSIGHT);
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
        cloudMigrationPerHostPage.selectCloudProduct(CloudProduct.AZURE_HD_INSIGHT);
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
