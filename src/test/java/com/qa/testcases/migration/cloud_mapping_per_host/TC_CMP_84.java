package com.qa.testcases.migration.cloud_mapping_per_host;

import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.enums.migration.MigrationCloudMappingHostDetailsTable;
import com.qa.scripts.migration.CloudMigrationPerHostPage;
import com.qa.utils.LoggingUtils;
import com.qa.utils.TextUtils;
import com.qa.utils.WaitExecuter;
import com.qa.utils.actions.UserActions;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

/**
 * @author Ankur Jaiswal
 */
@Marker.CloudMappingPerHost
@Marker.All
public class TC_CMP_84 extends BaseClass {
    private final static LoggingUtils LOGGER = new LoggingUtils(TC_CMP_84.class);
    /**
     * Verify the Total Hourly Cost - This should be sum of all "Total Cost($/Hour)" per host
     */
    @Test
    public void verifyTotalHourlyCost() {
        test = extent.startTest("TC_CMP_84.verifyTotalHourlyCost", "erify the Total Hourly Cost - This should be sum of all \"Total Cost($/Hour)\" per host.");
        test.assignCategory("Migration/Cloud Mapping Per Host");
        CloudMigrationPerHostPage cloudMigrationPerHostPage = new CloudMigrationPerHostPage(driver);
        UserActions actions = new UserActions(driver);
        WaitExecuter waitExecuter = new WaitExecuter(driver);
        LOGGER.info("Navigate to migration host page", test);
        cloudMigrationPerHostPage.navigateToCloudMappingPerHost();
        cloudMigrationPerHostPage.waitTillLoaderPresent();
        List totalCostUsages = cloudMigrationPerHostPage.getDataFromCloudMappingTable(
                MigrationCloudMappingHostDetailsTable.TOTAL_COST);
        Double totalCostPerHour =
                totalCostUsages.stream().mapToDouble(data -> Double.parseDouble(data.toString().replace("$", ""))).sum();
        Double totalHourlyCostValue = cloudMigrationPerHostPage.getTotoalHourlyCostValue();

        Assert.assertEquals(TextUtils.roundNumber(totalCostPerHour, 0), TextUtils.roundNumber(totalHourlyCostValue, 0),
                "Incorrect hourly is displayed.");
        LOGGER.pass("Proper value is displayed for total hourly cost", test);
    }
}
