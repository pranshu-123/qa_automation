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

public class TC_CMP_101 extends BaseClass {

    private final static LoggingUtils LOGGER = new LoggingUtils(TC_CMP_101.class);

    /**
     * Validate the Recommendations column displays the VM Instance, Cores, Memory,
     * storage - Name, type, size, cost and VM Cost ($/Hour), and they all contain valid values.
     */
    @Test
    public void verifyRecommendations() {
        test = extent.startTest("TC_CMP_101.verifyUsagesLessThanCapacity", "Validate the Recommendations column displays" +
                " the VM Instance, Cores, Memory, storage - Name, type, size, cost and VM Cost ($/Hour), and they all contain valid values.");
        test.assignCategory("Migration/Cloud Mapping Per Host");
        CloudMigrationPerHostPage cloudMigrationPerHostPage = new CloudMigrationPerHostPage(driver);
        LOGGER.info("Navigate to migration host page", test);
        cloudMigrationPerHostPage.navigateToCloudMappingPerHost();
        WaitExecuter waitExecuter = new WaitExecuter(driver);
        waitExecuter.sleep(3000);
        List recommendationDetails = cloudMigrationPerHostPage.getDataFromCloudMappingTable(
                MigrationCloudMappingHostDetailsTable.RECOMMENDATION);
        recommendationDetails.stream().forEach(recommendationDetail -> {
            Assert.assertTrue(((Map)recommendationDetail).get(CloudMappingHostConstants.HostDetails.RecommendedUsages.TYPE) != "");
            Assert.assertTrue(((Map)recommendationDetail).get(CloudMappingHostConstants.HostDetails.RecommendedUsages.STORAGE_INCLUDED_WITH_VM) != "");
            Assert.assertTrue(((Map)recommendationDetail).get(CloudMappingHostConstants.HostDetails.RecommendedUsages.CORES) != "");
            Assert.assertTrue(((Map)recommendationDetail).get(CloudMappingHostConstants.HostDetails.RecommendedUsages.VM_COST_PER_HOUR) != "");
            Assert.assertTrue(((Map)recommendationDetail).get(CloudMappingHostConstants.HostDetails.RecommendedUsages.MEMORY) != "");
            Assert.assertTrue(((Map) ((Map)recommendationDetail).get(CloudMappingHostConstants.HostDetails.RecommendedUsages.DETAILS))
                    .get(CloudMappingHostConstants.HostDetails.RecommendedUsages.DESC) != "");
            Assert.assertTrue(((Map) ((Map)recommendationDetail).get(CloudMappingHostConstants.HostDetails.RecommendedUsages.DETAILS))
                    .get(CloudMappingHostConstants.HostDetails.RecommendedUsages.NAME) != "");
            Assert.assertTrue(((Map) ((Map)recommendationDetail).get(CloudMappingHostConstants.HostDetails.RecommendedUsages.DETAILS))
                    .get(CloudMappingHostConstants.HostDetails.RecommendedUsages.TYPE) != "");
            Assert.assertTrue(((Map) ((Map)recommendationDetail).get(CloudMappingHostConstants.HostDetails.RecommendedUsages.DETAILS))
                    .get(CloudMappingHostConstants.HostDetails.RecommendedUsages.SIZE) != "");
            Assert.assertTrue(((Map) ((Map)recommendationDetail).get(CloudMappingHostConstants.HostDetails.RecommendedUsages.DETAILS))
                    .get(CloudMappingHostConstants.HostDetails.RecommendedUsages.COST_PER_HOUR) != "");
        });
        LOGGER.pass("Validated whether proper values are displayed for recommended details", test);
    }
}
