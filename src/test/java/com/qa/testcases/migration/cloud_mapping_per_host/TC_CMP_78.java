package com.qa.testcases.migration.cloud_mapping_per_host;

import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.enums.migration.CloudStorageType;
import com.qa.scripts.migration.CloudMigrationPerHostPage;
import com.qa.utils.LoggingUtils;
import com.qa.utils.WaitExecuter;
import com.qa.utils.actions.UserActions;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

/**
 * @author Ankur Jaiswal
 */
@Marker.CloudMappingPerHost
@Marker.All
public class TC_CMP_78 extends BaseClass {
    private static final LoggingUtils LOGGER = new LoggingUtils(TC_CMP_78.class);
    /**
     * Verify storage type should be Object Storage or Local Attached Storage
     */
    @Test
    public void verifyStorageObject() {
        test = extent.startTest("TC_CMP_78.verifyStorageObject", "Verify storage type should be Object Storage or Local Attached Storage");
        test.assignCategory("Migration/Cloud Mapping Per Host");

        CloudMigrationPerHostPage cloudMigrationPerHostPage = new CloudMigrationPerHostPage(driver);
        UserActions actions = new UserActions(driver);
        WaitExecuter waitExecuter = new WaitExecuter(driver);
        LOGGER.info("Navigate to migration host page", test);
        cloudMigrationPerHostPage.navigateToCloudMappingPerHost();

        LOGGER.info("Click on Run button", test);
        cloudMigrationPerHostPage.clickOnRunButton();
        cloudMigrationPerHostPage.waitTillLoaderPresent();
        List<String> expectedStorageObject = Arrays.asList(CloudStorageType.OBJECT_STORAGE.getValue(),
                CloudStorageType.LOCAL_ATTACHED_STORAGE.getValue());
        Assert.assertEquals(cloudMigrationPerHostPage.getStorageList(), expectedStorageObject, "Incorrect correct cloud storage " +
                "types are displayed");
        LOGGER.pass("Verified correct cloud storage types are displayed", test);
    }
}
