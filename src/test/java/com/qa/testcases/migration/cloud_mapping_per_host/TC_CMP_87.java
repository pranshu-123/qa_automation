package com.qa.testcases.migration.cloud_mapping_per_host;

import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.enums.migration.InstanceSummaryTableColumn;
import com.qa.scripts.migration.CloudMigrationPerHostPage;
import com.qa.utils.LoggingUtils;
import com.qa.utils.WaitExecuter;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

/**
 * @author Ankur Jaiswal
 */
@Marker.CloudMappingPerHost
@Marker.All
public class TC_CMP_87 extends BaseClass {

    private final static LoggingUtils LOGGER = new LoggingUtils(TC_CMP_87.class);

    /**
     * Verify the summary report displays number of hosts and should be > 0
     */

    @Test
    public void verifyHostSummaryReport() {
        test = extent.startTest("TC_CMP_87.verifySummaryReport", "Verify the summary report displays number of hosts and should be > 0");
        test.assignCategory("Migration/Cloud Mapping Per Host");
        CloudMigrationPerHostPage cloudMigrationPerHostPage = new CloudMigrationPerHostPage(driver);
        LOGGER.info("Navigate to migration host page", test);
        cloudMigrationPerHostPage.navigateToCloudMappingPerHost();
        WaitExecuter waitExecuter = new WaitExecuter(driver);
        waitExecuter.sleep(3000);
        List<String> hostsInSummary =
                cloudMigrationPerHostPage.getSummaryReportDetails(InstanceSummaryTableColumn.HOSTS);
        hostsInSummary.stream().forEach(data -> Assert.assertTrue(Integer.parseInt(data) > 0, "Host values are less " +
                "than 1"));
        LOGGER.pass("Hosts values are more than 0", test);
    }
}
