package com.qa.testcases.migration.cloud_mapping_per_host;

import com.qa.base.BaseClass;
import com.qa.enums.migration.CloudProduct;
import com.qa.enums.migration.MigrationCloudMappingTable;
import com.qa.scripts.migration.CloudMigrationPerHostPage;
import com.qa.utils.LoggingUtils;
import org.testng.annotations.Test;

/**
 * @author Ankur jaiswal
 */
public class TC_CMP_04 extends BaseClass {
    private static final LoggingUtils LOGGER = new LoggingUtils(TC_CMP_04.class);
    /**
     * Verify Unravel UI displays the right VM types for AZURE.
     */
    @Test
    public void verifyVMTypeForAzure() {
        test = extent.startTest("TC_CMP_04.verifyVMTypeForAzure", "Verify Unravel UI displays the right VM types for " +
            "AZURE.");
        test.assignCategory("Migration/Cloud Mapping Per Host");
        CloudMigrationPerHostPage cloudMigrationPerHostPage = new CloudMigrationPerHostPage(driver);
        LOGGER.info("Navigate to migration host page", test);
        cloudMigrationPerHostPage.navigateToCloudMappingPerHost();
        LOGGER.info("Click on Run button", test);
        cloudMigrationPerHostPage.clickOnRunButton();
        cloudMigrationPerHostPage.waitTillLoaderPresent();
        LOGGER.info("Select azure as cloud product", test);
        cloudMigrationPerHostPage.selectCloudProduct(CloudProduct.AZURE);
        cloudMigrationPerHostPage.waitTillLoaderPresent();
        cloudMigrationPerHostPage.verifyCloudMappingHostTableColumn(MigrationCloudMappingTable.VM_TYPE);
        LOGGER.pass("Verified VM types", test);
    }
}
