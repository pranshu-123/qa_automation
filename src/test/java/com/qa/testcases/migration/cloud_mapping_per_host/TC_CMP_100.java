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
public class TC_CMP_100 extends BaseClass {

    private final static LoggingUtils LOGGER = new LoggingUtils(TC_CMP_100.class);

    /**
     * Validate that the Usage column specs are always <= the Capacity column specs,
     * for CPU, Mem, Disk. E.g., used CPU cores <= available CPU cores
     */
    @Test
    public void verifyUsagesLessThanCapacity() {
        test = extent.startTest("TC_CMP_100.verifyUsagesLessThanCapacity", "Validate that the Usage column" +
                " specs are always <= the Capacity column specs, for CPU, Mem, Disk. E.g., used CPU cores <= available CPU cores");
        test.assignCategory("Migration/Cloud Mapping Per Host");
        CloudMigrationPerHostPage cloudMigrationPerHostPage = new CloudMigrationPerHostPage(driver);
        LOGGER.info("Navigate to migration host page", test);
        cloudMigrationPerHostPage.navigateToCloudMappingPerHost();
        WaitExecuter waitExecuter = new WaitExecuter(driver);
        waitExecuter.sleep(3000);
        List actualUsages = cloudMigrationPerHostPage.getDataFromCloudMappingTable(
                MigrationCloudMappingHostDetailsTable.ACTUAL_USAGE);
        List capacityDetails = cloudMigrationPerHostPage.getDataFromCloudMappingTable(
                MigrationCloudMappingHostDetailsTable.CAPACITY);

        for (int i=0; i<actualUsages.size(); i++) {
            Map actualUsage = ((Map)actualUsages.get(i));
            Map capacity = ((Map)capacityDetails.get(i));
            Assert.assertTrue(Double.parseDouble(actualUsage.get(CloudMappingHostConstants.HostDetails.ActualUsages.CORES).toString()) <
                    Double.parseDouble(capacity.get(CloudMappingHostConstants.HostDetails.ActualUsages.CORES).toString()));
            Assert.assertTrue(cloudMigrationPerHostPage.convertMemoryToMB(actualUsage.get(CloudMappingHostConstants.HostDetails.ActualUsages.MEMORY).toString()) <
                    cloudMigrationPerHostPage.convertMemoryToMB(capacity.get(CloudMappingHostConstants.HostDetails.ActualUsages.MEMORY).toString()));
            Assert.assertTrue(cloudMigrationPerHostPage.convertMemoryToMB(actualUsage.get(CloudMappingHostConstants.HostDetails.ActualUsages.DISK).toString()) <
                    cloudMigrationPerHostPage.convertMemoryToMB(capacity.get(CloudMappingHostConstants.HostDetails.ActualUsages.DISK).toString()));
        }
        LOGGER.pass("Verified cores, memory and disk values for actual usages is less than capacity", test);
    }
}
