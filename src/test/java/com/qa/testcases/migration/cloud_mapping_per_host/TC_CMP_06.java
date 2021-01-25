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
public class TC_CMP_06 extends BaseClass {
    private static final LoggingUtils LOGGER = new LoggingUtils(TC_CMP_06.class);
    /**
     * Verify Unravel UI displays the right VM types for HDI.
     */
    @Test
    public void verifyVMTypeForHDI() {
        test = extent.startTest("TC_CMP_06.verifyVMTypeForHDI", "Verify Unravel UI displays the right VM types for HDI.");
        test.assignCategory("Migration/Cloud Mapping Per Host");
        CloudMigrationPerHostPage cloudMigrationPerHostPage = new CloudMigrationPerHostPage(driver);
        LOGGER.info("Navigate to migration host page", test);
        cloudMigrationPerHostPage.navigateToCloudMappingPerHost();
        LOGGER.info("Click on Run button", test);
        cloudMigrationPerHostPage.clickOnRunButton();
        cloudMigrationPerHostPage.waitTillLoaderPresent();
        LOGGER.info("Select HDI as cloud product", test);
        cloudMigrationPerHostPage.selectCloudProduct(CloudProduct.AZURE_HD_INSIGHT);
        cloudMigrationPerHostPage.waitTillLoaderPresent();
        cloudMigrationPerHostPage.verifyCloudMappingHostTableColumn(MigrationCloudMappingModalTable.VM_TYPE);
        LOGGER.pass("Verified VM types", test);
    }
}
