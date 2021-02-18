package com.qa.testcases.migration.cloud_mapping_per_host;

import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.enums.migration.CloudProduct;
import com.qa.scripts.migration.CloudMigrationPerHostPage;
import com.qa.utils.LoggingUtils;
import com.qa.utils.WaitExecuter;
import com.qa.utils.actions.UserActions;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * @author Ankur Jaiswal
 */
@Marker.CloudMappingPerHost
@Marker.All
public class TC_CMP_93 extends BaseClass {
    private final static LoggingUtils LOGGER = new LoggingUtils(TC_CMP_93.class);
    /**
     * Verify settings are saved for the last run
     */
    @Test
    public void verifyDataIsSavedForLatestRun() {
        test = extent.startTest("TC_CMP_93.verifyDataIsSavedForLatestRun", "Verify settings are saved for the last " +
                "run.");
        test.assignCategory("Migration/Cloud Mapping Per Host");

        CloudMigrationPerHostPage cloudMigrationPerHostPage = new CloudMigrationPerHostPage(driver);
        UserActions actions = new UserActions(driver);
        WaitExecuter waitExecuter = new WaitExecuter(driver);
        LOGGER.info("Navigate to migration host page", test);
        cloudMigrationPerHostPage.navigateToCloudMappingPerHost();
        LOGGER.info("Click on Run button", test);
        cloudMigrationPerHostPage.clickOnRunButton();
        cloudMigrationPerHostPage.waitTillLoaderPresent();

        LOGGER.info("Select amazon EMR as cloud product.", test);
        cloudMigrationPerHostPage.selectCloudProduct(CloudProduct.AMAZON_EMR);
        cloudMigrationPerHostPage.waitTillLoaderPresent();

        cloudMigrationPerHostPage.selectRegion("US East (Ohio)");
        cloudMigrationPerHostPage.waitTillLoaderPresent();

        LOGGER.info("Select first storage.", test);
        cloudMigrationPerHostPage.selectStorage("Object storage");

        for (WebElement element : cloudMigrationPerHostPage.getCustomCosts()) {
            cloudMigrationPerHostPage.setCustomCost(element, "1");
            break;
        }

        cloudMigrationPerHostPage.clickOnRunButton();
        cloudMigrationPerHostPage.waitTillLoaderPresent();

        Assert.assertEquals(cloudMigrationPerHostPage.getCloudProvider(), CloudProduct.AMAZON_EMR.getValue());
        LOGGER.pass("Correct value displayed as cloud provider as per selected on previous run", test);

        Assert.assertEquals(cloudMigrationPerHostPage.getRegion(), "US East (Ohio)");
        LOGGER.pass("Correct value is displayed for region value as per selected on previous run", test);

        Assert.assertEquals(cloudMigrationPerHostPage.getCustomCosts().get(0).getText(), "1");
        LOGGER.pass("Custom cost is displayed as per selected on previous run", test);
    }
}
