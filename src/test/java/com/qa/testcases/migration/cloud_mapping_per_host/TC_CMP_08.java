package com.qa.testcases.migration.cloud_mapping_per_host;

import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.enums.migration.MigrationCloudMappingModalTable;
import com.qa.scripts.migration.CloudMigrationPerHostPage;
import com.qa.utils.LoggingUtils;
import com.relevantcodes.extentreports.LogStatus;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.util.List;

/**
 * @author Ankur Jaiswal
 */
@Marker.CloudMappingPerHost
@Marker.All
public class TC_CMP_08 extends BaseClass {
    private static final LoggingUtils LOGGER = new LoggingUtils(TC_CMP_08.class);

    /**
     * Verify the cost/hour changes according to different storage types.
     */
    @Test
    public void verifyCostChangeForDiffStorage() {
        test = extent.startTest("TC_CMP_08.verifyCostChangeForDiffStorage", "Verify the cost/hour changes according " +
            "to different storage types.");
        test.assignCategory("Migration/Cloud Mapping Per Host");
        CloudMigrationPerHostPage cloudMigrationPerHostPage = new CloudMigrationPerHostPage(driver);
        LOGGER.info("Navigate to migration host page", test);
        cloudMigrationPerHostPage.navigateToCloudMappingPerHost();
        LOGGER.info("Click on Run button", test);
        cloudMigrationPerHostPage.clickOnRunButton();
        cloudMigrationPerHostPage.waitTillLoaderPresent();
        LOGGER.info("Select first storage.", test);
        cloudMigrationPerHostPage.selectStorage("Object storage");
        cloudMigrationPerHostPage.waitTillLoaderPresent();
        List<String> value1 = cloudMigrationPerHostPage.getColumnValuesFromModalTable(MigrationCloudMappingModalTable.COST);
        LOGGER.info("Select second storage.", test);
        cloudMigrationPerHostPage.selectStorage("Local attached storage");
        cloudMigrationPerHostPage.waitTillLoaderPresent();
        List<String> value2 = cloudMigrationPerHostPage.getColumnValuesFromModalTable(MigrationCloudMappingModalTable.COST);
        Assert.assertNotEquals(value1, value2, "Cost/Hour is same for different storage.");
        test.log(LogStatus.PASS, "Validated the cost/hour changes according to different storage successfully.");
    }
}
