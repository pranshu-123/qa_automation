package com.qa.testcases.migration.cloud_mapping_per_host;

import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.enums.UserAction;
import com.qa.enums.migration.CloudProduct;
import com.qa.scripts.migration.CloudMigrationPerHostPage;
import com.qa.utils.LoggingUtils;
import com.qa.utils.WaitExecuter;
import com.qa.utils.actions.UserActions;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

/**
 * @author Ankur Jaiswal
 */
@Marker.CloudMappingPerHost
@Marker.All
public class TC_CMP_94 extends BaseClass {

    private final static LoggingUtils LOGGER = new LoggingUtils(TC_CMP_94.class);

    /**
     * Verify the report generated for Cloud provider - "X" displays the name of the cloud provider
     */
    @Test
    public void verifyCloudProvider() {
        test = extent.startTest("TC_CMP_94.verifyCloudProvider", "Verify the report generated for Cloud provider - \"X\" displays the name of the cloud provider");
        test.assignCategory("Migration/Cloud Mapping Per Host");

        CloudMigrationPerHostPage cloudMigrationPerHostPage = new CloudMigrationPerHostPage(driver);
        WaitExecuter waitExecuter = new WaitExecuter(driver);
        UserActions actions = new UserActions(driver);
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
        cloudMigrationPerHostPage.checkUncheckColumn(true);

        List<WebElement> checkboxListForTable = cloudMigrationPerHostPage.getCheckboxListForTable();
        try {
            actions.performActionWithPolling(checkboxListForTable.get(0), UserAction.CLICK);
        } catch (IndexOutOfBoundsException outOfBoundsException) {
            Assert.assertTrue(false, "No data displayed in table");
        }
        cloudMigrationPerHostPage.clickOnRunButton();
        cloudMigrationPerHostPage.waitTillLoaderPresent();
        waitExecuter.sleep(5000);
        Assert.assertEquals(cloudMigrationPerHostPage.getCloudProductAndServicesOnReportPage(),
                CloudProduct.AMAZON_EMR.getValue());
        LOGGER.pass("Correct cloud provider values are displayed.", test);
    }
}
