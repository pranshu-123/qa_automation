package com.qa.testcases.migration.cloud_mapping_per_host;

import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.enums.migration.CloudProduct;
import com.qa.enums.migration.MigrationCloudMappingModalTable;
import com.qa.scripts.migration.CloudMigrationPerHostPage;
import com.qa.utils.LoggingUtils;
import org.testng.annotations.Test;

@Marker.All
public class TC_CMP_03 extends BaseClass {
    private static final LoggingUtils LOGGER = new LoggingUtils(TC_CMP_03.class);

    @Test(dataProvider = "clusterid-data-provider")
    public void verifyVMTypesForEC2(String clusterId) {
        test = extent.startTest("TC_CMP_03.verifyVMTypesForEC2", "Verify Unravel UI displays the right VM types for " +
                "AMAZON_EC2.");
        test.assignCategory("Migration/Cloud Mapping Per Host");
        CloudMigrationPerHostPage cloudMigrationPerHostPage = new CloudMigrationPerHostPage(driver);
        LOGGER.info("Navigate to migration host page", test);
        cloudMigrationPerHostPage.navigateToCloudMappingPerHost();
        LOGGER.info("Click on Run button", test);
        cloudMigrationPerHostPage.clickOnRunButton();
        cloudMigrationPerHostPage.waitTillLoaderPresent();
        LOGGER.info("Select azure as cloud product", test);
        cloudMigrationPerHostPage.selectCloudProduct(CloudProduct.AMAZON_EC2);
        cloudMigrationPerHostPage.waitTillLoaderPresent();
        cloudMigrationPerHostPage.verifyCloudMappingHostTableColumn(MigrationCloudMappingModalTable.VM_TYPE);
        LOGGER.pass("Verified VM types", test);
    }
}
