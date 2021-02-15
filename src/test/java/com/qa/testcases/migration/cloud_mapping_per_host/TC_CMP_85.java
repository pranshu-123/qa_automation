package com.qa.testcases.migration.cloud_mapping_per_host;

import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.constants.CloudMappingHostConstants;
import com.qa.enums.migration.MigrationCloudMappingHostDetailsTable;
import com.qa.scripts.migration.CloudMigrationPerHostPage;
import com.qa.utils.LoggingUtils;
import com.qa.utils.TextUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Map;

/**
 * @author Ankur Jaiswal
 */
@Marker.CloudMappingPerHost
@Marker.All
public class TC_CMP_85 extends BaseClass {
    private final static LoggingUtils LOGGER = new LoggingUtils(TC_CMP_85.class);

    /**
     * Verify the Total Storage required - This should be sum of all "Recommendations,
     * storage size" per host
     */

    @Test
    public void verifyTotalStorageRequired() {
        test = extent.startTest("TC_CMP_85.verifyTotalStorageRequired", "Verify the Total Storage required - This should be sum of all" +
                " \"Recommendations, storage size\" per host");
        test.assignCategory("Migration/Cloud Mapping Per Host");
        CloudMigrationPerHostPage cloudMigrationPerHostPage = new CloudMigrationPerHostPage(driver);
        LOGGER.info("Navigate to migration host page", test);
        cloudMigrationPerHostPage.navigateToCloudMappingPerHost();

        List recommendedUsages = cloudMigrationPerHostPage.getDataFromCloudMappingTable(
                MigrationCloudMappingHostDetailsTable.RECOMMENDATION);

        Double detailsCostPerHourSumTotal =
                recommendedUsages.stream().filter(data -> data instanceof Map).map(data -> ((Map) data)
                        .get(CloudMappingHostConstants.HostDetails.RecommendedUsages.DETAILS))
                        .filter(data -> data instanceof Map).mapToDouble(data -> Double.parseDouble(((Map) data)
                        .get(CloudMappingHostConstants.HostDetails.RecommendedUsages.COST_PER_HOUR).toString().replace("$",""))).sum();
        Double totalAttachedStorageCost = cloudMigrationPerHostPage.getTotalLocalAttachedStorageValue();

        Assert.assertEquals(TextUtils.roundNumber(totalAttachedStorageCost, 2),
                TextUtils.roundNumber(detailsCostPerHourSumTotal, 2), "Total " +
                "Storage Cost is not matching with storage cost per VM.");
        LOGGER.pass("Storage Cost is matching with storage cost per VM", test);
    }
}
