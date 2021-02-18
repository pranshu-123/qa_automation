package com.qa.testcases.migration.cloud_mapping_per_host;

import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.enums.migration.CloudProduct;
import com.qa.enums.migration.CloudStorageType;
import com.qa.scripts.migration.CloudMigrationPerHostPage;
import com.qa.utils.LoggingUtils;
import com.qa.utils.WaitExecuter;
import com.qa.utils.actions.UserActions;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * @author Ankur Jaiswal
 */
@Marker.CloudMappingPerHost
@Marker.All
public class TC_CMP_80 extends BaseClass {
    private final static LoggingUtils LOGGER = new LoggingUtils(TC_CMP_80.class);

    @Test
    public void verifyStorageNameBasedOnLocalStorage() {
        test = extent.startTest("TC_CMP_80.verifyStorageNameBasedOnLocalStorage", "Verify storage name based on Cloud Provider/ Local Attached Storage");
        test.assignCategory("Migration/Cloud Mapping Per Host");

        CloudMigrationPerHostPage cloudMigrationPerHostPage = new CloudMigrationPerHostPage(driver);
        UserActions actions = new UserActions(driver);
        WaitExecuter waitExecuter = new WaitExecuter(driver);
        LOGGER.info("Navigate to migration host page", test);
        cloudMigrationPerHostPage.navigateToCloudMappingPerHost();

        LOGGER.info("Click on Run button", test);
        cloudMigrationPerHostPage.clickOnRunButton();
        cloudMigrationPerHostPage.waitTillLoaderPresent();

        cloudMigrationPerHostPage.selectCloudProduct(CloudProduct.AMAZON_EMR);
        cloudMigrationPerHostPage.waitTillLoaderPresent();
        cloudMigrationPerHostPage.selectStorage(CloudStorageType.LOCAL_ATTACHED_STORAGE.getValue());
        String storage1 = cloudMigrationPerHostPage.getStorageName();
        cloudMigrationPerHostPage.selectCloudProduct(CloudProduct.GOOGLE_COMPUTE_ENGINE);
        cloudMigrationPerHostPage.waitTillLoaderPresent();
        String storage2 = cloudMigrationPerHostPage.getStorageName();
        Assert.assertNotEquals(storage1, storage2, "Incorrect storage name are displayed");
        LOGGER.pass("Verified correct storage name are displayed", test);
    }
}
