package com.qa.testcases.migration.cloud_mapping_per_host;

import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.scripts.migration.CloudMigrationPerHostPage;
import com.qa.utils.LoggingUtils;
import com.qa.utils.WaitExecuter;
import com.qa.utils.actions.UserActions;
import org.testng.Assert;
import org.testng.annotations.Test;

@Marker.All
@Marker.CloudMappingPerHost
public class TC_CMP_81 extends BaseClass {
    private final static LoggingUtils LOGGER = new LoggingUtils(TC_CMP_81.class);
    /**
     * Verify the number of VM instances is equal to or
     * greater than "N" for all cloud providers/region
     */
    @Test
    public void verifyNumOfVMInstances() {
        test = extent.startTest("TC_CMP_81.verifyNumOfVMInstances", "Verify the number of VM instances is equal to or" +
            " greater than 'N' for all cloud providers/region");
        test.assignCategory("Migration/Cloud Mapping Per Host");

        CloudMigrationPerHostPage cloudMigrationPerHostPage = new CloudMigrationPerHostPage(driver);
        UserActions actions = new UserActions(driver);
        WaitExecuter waitExecuter = new WaitExecuter(driver);
        LOGGER.info("Navigate to migration host page", test);
        cloudMigrationPerHostPage.navigateToCloudMappingPerHost();

        LOGGER.info("Click on Run button", test);
        cloudMigrationPerHostPage.clickOnRunButton();
        cloudMigrationPerHostPage.waitTillLoaderPresent();

        Assert.assertNotEquals(cloudMigrationPerHostPage.getInstanceValuesFromModalTable(false).size(), 0,
            "Incorrect count is displayed for cloud.");
    }
}
