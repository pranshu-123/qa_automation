package com.qa.testcases.migration.cloud_mapping_per_host;

import com.qa.base.BaseClass;
import com.qa.enums.migration.CloudProduct;
import com.qa.enums.migration.MigrationCloudMappingModalTable;
import com.qa.scripts.migration.CloudMigrationPerHostPage;
import com.qa.utils.LoggingUtils;
import org.testng.annotations.Test;

/**
 * @author Ankur jaiswal
 */
public class TC_CMP_05 extends BaseClass {
    private static final LoggingUtils LOGGER = new LoggingUtils(TC_CMP_05.class);
    /**
     * Verify Unravel UI displays the right VM types for GCP .
     */
    @Test
    public void verifyVMTypeForGCP() {
        test = extent.startTest("TC_CMP_05.verifyVMTypeForGCP", "Verify Unravel UI displays the right VM types for GCP.");
        test.assignCategory("Migration/Cloud Mapping Per Host");
        CloudMigrationPerHostPage cloudMigrationPerHostPage = new CloudMigrationPerHostPage(driver);
        LOGGER.info("Navigate to migration host page", test);
        cloudMigrationPerHostPage.navigateToCloudMappingPerHost();
        LOGGER.info("Click on Run button", test);
        cloudMigrationPerHostPage.clickOnRunButton();
        cloudMigrationPerHostPage.waitTillLoaderPresent();
        LOGGER.info("Select gcp as cloud product", test);
        cloudMigrationPerHostPage.selectCloudProduct(CloudProduct.GOOGLE_COMPUTE_ENGINE);
        cloudMigrationPerHostPage.waitTillLoaderPresent();
        cloudMigrationPerHostPage.verifyCloudMappingHostTableColumn(MigrationCloudMappingModalTable.VM_TYPE);
        LOGGER.pass("Verified VM types", test);
    }
}
