package com.qa.testcases.migration.cloud_mapping_per_host;

import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.constants.CloudMappingHostConstants;
import com.qa.enums.migration.MigrationCloudMappingHostDetailsTable;
import com.qa.scripts.migration.CloudMigrationPerHostPage;
import com.qa.utils.LoggingUtils;
import com.qa.utils.WaitExecuter;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Map;

/**
 * @author Ankur Jaiswal
 */
@Marker.CloudMappingPerHost
@Marker.All
public class TC_CMP_92 extends BaseClass {
    private final static LoggingUtils LOGGER = new LoggingUtils(TC_CMP_92.class);

    /**
     * Validate the Recommendations column displays the VM Instanse,
     * Cores, Memory, storage - Name, type, size, cost and VM Cost ($/Hour).
     */
    @Test
    public void verifyRecommendation() {
        test = extent.startTest("TC_CMP_86.verifySummaryReport", "Validate the Recommendations column displays the VM Instanse, Cores, Memory, " +
                "storage - Name, type, size, cost and VM Cost ($/Hour).");
        test.assignCategory("Migration/Cloud Mapping Per Host");
        CloudMigrationPerHostPage cloudMigrationPerHostPage = new CloudMigrationPerHostPage(driver);
        LOGGER.info("Navigate to migration host page", test);
        cloudMigrationPerHostPage.navigateToCloudMappingPerHost();
        WaitExecuter waitExecuter = new WaitExecuter(driver);
        waitExecuter.sleep(3000);
        List recommendedUsages = cloudMigrationPerHostPage.getDataFromCloudMappingTable(
                MigrationCloudMappingHostDetailsTable.RECOMMENDATION);
        recommendedUsages.stream().forEach(data -> {
            Assert.assertNotEquals(((Map)data).get(CloudMappingHostConstants.HostDetails.RecommendedUsages.MEMORY), "");
            Assert.assertNotEquals(((Map)data).get(CloudMappingHostConstants.HostDetails.RecommendedUsages.CORES), "");
            Assert.assertNotEquals(((Map)data).get(CloudMappingHostConstants.HostDetails.RecommendedUsages.TYPE), "");
            Assert.assertNotEquals(((Map)data).get(CloudMappingHostConstants.HostDetails.RecommendedUsages.VM_COST_PER_HOUR), "");
            Assert.assertNotEquals(((Map)data).get(CloudMappingHostConstants.HostDetails.RecommendedUsages.STORAGE_INCLUDED_WITH_VM), "");
            Assert.assertNotEquals(((Map)((Map)data).get(CloudMappingHostConstants.HostDetails.RecommendedUsages.DETAILS))
                    .get(CloudMappingHostConstants.HostDetails.RecommendedUsages.DESC), "");
            Assert.assertNotEquals(((Map)((Map)data).get(CloudMappingHostConstants.HostDetails.RecommendedUsages.DETAILS))
                    .get(CloudMappingHostConstants.HostDetails.RecommendedUsages.NAME), "");
            Assert.assertNotEquals(((Map)((Map)data).get(CloudMappingHostConstants.HostDetails.RecommendedUsages.DETAILS))
                    .get(CloudMappingHostConstants.HostDetails.RecommendedUsages.TYPE), "");
            Assert.assertNotEquals(((Map)((Map)data).get(CloudMappingHostConstants.HostDetails.RecommendedUsages.DETAILS))
                    .get(CloudMappingHostConstants.HostDetails.RecommendedUsages.SIZE), "");
            Assert.assertNotEquals(((Map)((Map)data).get(CloudMappingHostConstants.HostDetails.RecommendedUsages.DETAILS))
                    .get(CloudMappingHostConstants.HostDetails.RecommendedUsages.COST_PER_HOUR), "");
        });
        LOGGER.pass("Verified whether proper values are displayed in recommended,", test);
    }
}
