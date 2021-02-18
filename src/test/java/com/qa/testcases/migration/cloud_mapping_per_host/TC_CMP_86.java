package com.qa.testcases.migration.cloud_mapping_per_host;

import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.constants.CloudMappingHostConstants;
import com.qa.enums.migration.InstanceSummaryTableColumn;
import com.qa.enums.migration.MigrationCloudMappingHostDetailsTable;
import com.qa.scripts.migration.CloudMigrationPerHostPage;
import com.qa.utils.LoggingUtils;
import com.qa.utils.WaitExecuter;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author Ankur Jaiswal
 */
@Marker.CloudMappingPerHost
@Marker.All
public class TC_CMP_86 extends BaseClass {

    private final static LoggingUtils LOGGER = new LoggingUtils(TC_CMP_86.class);

    /**
     * Verify the summary report displays a valid instanse type, Cores, Memory
     */

    @Test
    public void verifySummaryReport() {
        test = extent.startTest("TC_CMP_86.verifySummaryReport", "Verify the summary report displays a valid instanse type, Cores, Memory");
        test.assignCategory("Migration/Cloud Mapping Per Host");
        CloudMigrationPerHostPage cloudMigrationPerHostPage = new CloudMigrationPerHostPage(driver);
        LOGGER.info("Navigate to migration host page", test);
        cloudMigrationPerHostPage.navigateToCloudMappingPerHost();
        WaitExecuter waitExecuter = new WaitExecuter(driver);
        waitExecuter.sleep(3000);
        List<String> instanceInSummary =
                cloudMigrationPerHostPage.getSummaryReportDetails(InstanceSummaryTableColumn.INSTANCE);
        List recommendedUsages = cloudMigrationPerHostPage.getDataFromCloudMappingTable(
                MigrationCloudMappingHostDetailsTable.RECOMMENDATION);
        Set instanceTypesInRecommended =
                (Set) recommendedUsages.stream().filter(data -> data instanceof Map).map(data -> ((Map) data)
                .get(CloudMappingHostConstants.HostDetails.RecommendedUsages.TYPE)).collect(Collectors.toSet());
        List instanceTypesInRecommendedList = (List) instanceTypesInRecommended.stream().collect(Collectors.toList());
        Collections.sort(instanceInSummary);
        Collections.sort(instanceTypesInRecommendedList);
        Assert.assertEquals(instanceInSummary, instanceTypesInRecommendedList, "Instance Details is not matching");
        LOGGER.pass("Instance Details is matching", test);

        List<Integer> coresInSummary =
                cloudMigrationPerHostPage.getSummaryReportDetails(InstanceSummaryTableColumn.CORES).stream().map(data -> Integer.parseInt(data)).sorted().collect(Collectors.toList());
        Collections.sort(coresInSummary);
        Set coresInRecommended =
                (Set) recommendedUsages.stream().filter(data -> data instanceof Map).map(data -> (int) Double.parseDouble(((Map) data)
                        .get(CloudMappingHostConstants.HostDetails.RecommendedUsages.CORES).toString())).collect(Collectors.toSet());
        List coresInRecommendedList =
                (List) coresInRecommended.stream().collect(Collectors.toList());
        Collections.sort(coresInRecommendedList);
        Assert.assertEquals(coresInSummary, coresInRecommendedList, "Cores is not matching");
        LOGGER.pass("Cores Details is matching", test);

        List<String> memoryInSummary =
                cloudMigrationPerHostPage.getSummaryReportDetails(InstanceSummaryTableColumn.MEMORY);
        Collections.sort(memoryInSummary);
        Set memoryInRecommended =
                (Set) recommendedUsages.stream().filter(data -> data instanceof Map).map(data -> ((Map) data)
                        .get(CloudMappingHostConstants.HostDetails.RecommendedUsages.MEMORY)).collect(Collectors.toSet());
        List memoryInRecommendedList = (List) memoryInRecommended.stream().collect(Collectors.toList());
        Collections.sort(memoryInRecommendedList);
        Assert.assertEquals(memoryInSummary, memoryInRecommendedList, "Memory is not matching");
        LOGGER.pass("Memory Details is matching", test);
    }
}
