package com.qa.testcases.migration.cloud_mapping_per_host;

import com.qa.base.BaseClass;
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
public class TC_CMP_79 extends BaseClass {
    private static final LoggingUtils LOGGER = new LoggingUtils(TC_CMP_79.class);
    /**
     * Verify storage name based on Cloud Provider/ Object Storage
     */
    @Test
    public void verifyStorageNameBasedOnObjectStorage() {
        test = extent.startTest("TC_CMP_79.verifyStorageNameBasedOnObjectStorage", "Verify storage name based on Cloud Provider/ Object Storage");
        test.assignCategory("Migration/Cloud Mapping Per Host");

        CloudMigrationPerHostPage cloudMigrationPerHostPage = new CloudMigrationPerHostPage(driver);
        UserActions actions = new UserActions(driver);
        WaitExecuter waitExecuter = new WaitExecuter(driver);
        LOGGER.info("Navigate to migration host page", test);
        cloudMigrationPerHostPage.navigateToCloudMappingPerHost();

        LOGGER.info("Click on Run button", test);
        cloudMigrationPerHostPage.clickOnRunButton();
        cloudMigrationPerHostPage.waitTillLoaderPresent();

        cloudMigrationPerHostPage.selectStorage(CloudStorageType.OBJECT_STORAGE.getValue());
        String storage1 = cloudMigrationPerHostPage.getStorageName();
        cloudMigrationPerHostPage.selectStorage(CloudStorageType.LOCAL_ATTACHED_STORAGE.getValue());
        String storage2 = cloudMigrationPerHostPage.getStorageName();
        Assert.assertNotEquals(storage1, storage2, "Incorrect storage types are displayed");
        LOGGER.pass("Verified correct storage types are displayed", test);
    }
}
