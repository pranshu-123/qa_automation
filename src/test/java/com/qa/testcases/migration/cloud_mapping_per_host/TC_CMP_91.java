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
public class TC_CMP_91 extends BaseClass {

    private final static LoggingUtils LOGGER = new LoggingUtils(TC_CMP_90.class);

    /**
     * Validate the capcity column displays the Cluster Display name (Not Cluster ID), cores, memory, disk.
     */
    @Test
    public void verifyCapacityUsage() {
        test = extent.startTest("TC_CMP_90.verifyCapacityUsage", "Validate the capcity column displays the Cluster Display name" +
                " (Not Cluster ID), cores, memory, disk.");
        test.assignCategory("Migration/Cloud Mapping Per Host");
        CloudMigrationPerHostPage cloudMigrationPerHostPage = new CloudMigrationPerHostPage(driver);
        LOGGER.info("Navigate to migration host page", test);
        cloudMigrationPerHostPage.navigateToCloudMappingPerHost();
        WaitExecuter waitExecuter = new WaitExecuter(driver);
        waitExecuter.sleep(3000);
        List actualUsage = cloudMigrationPerHostPage.getDataFromCloudMappingTable(
                MigrationCloudMappingHostDetailsTable.CAPACITY);
        actualUsage.stream().forEach(data -> {
            Assert.assertNotEquals(((Map)data).get(CloudMappingHostConstants.HostDetails.CapacityUsages.CLUSTER).toString(), "");
            Assert.assertNotEquals(((Map)data).get(CloudMappingHostConstants.HostDetails.CapacityUsages.MEMORY).toString(), "");
            Assert.assertNotEquals(((Map)data).get(CloudMappingHostConstants.HostDetails.CapacityUsages.DISK).toString(), "");
            Assert.assertNotEquals(((Map)data).get(CloudMappingHostConstants.HostDetails.CapacityUsages.CORES).toString(), "");
        });
        LOGGER.pass("Capacity usages displayed with proper cluster, memory, disk and core values", test);
    }
}
