package com.qa.testcases.migration.cloud_mapping_per_host;

import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.constants.CloudMappingHostConstants;
import com.qa.enums.migration.InstanceSummaryTableColumn;
import com.qa.enums.migration.MigrationCloudMappingHostDetailsTable;
import com.qa.scripts.migration.CloudMigrationPerHostPage;
import com.qa.utils.LoggingUtils;
import com.qa.utils.WaitExecuter;
import org.javatuples.Pair;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.*;
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
                cloudMigrationPerHostPage.getSummaryReportDetails(InstanceSummaryTableColumn.CORES).stream().map(data -> Integer.parseInt(data)).collect(Collectors.toList());
        instanceInSummary =
                cloudMigrationPerHostPage.getSummaryReportDetails(InstanceSummaryTableColumn.INSTANCE).stream().collect(Collectors.toList());
        Map<String, Integer> hostSummary = new HashMap<>();
        for (int i=0; i<coresInSummary.size(); i++) {
            hostSummary.put(instanceInSummary.get(i), coresInSummary.get(i));
        }

        List coresInRecommended =
                (List) recommendedUsages.stream().filter(data -> data instanceof Map).map(data -> Pair.with(
                        ((Map) data).get(CloudMappingHostConstants.HostDetails.RecommendedUsages.TYPE).toString(),
                        (int) Double.parseDouble(((Map) data)
                        .get(CloudMappingHostConstants.HostDetails.RecommendedUsages.CORES).toString()))).collect(Collectors.toList());

        for (Object typeCore : coresInRecommended) {
            Assert.assertEquals(hostSummary.get(((Pair) typeCore).getValue0()), ((Pair) typeCore).getValue1(),
                    "Incorrect value is displayed of cores value in summary vs recommendation for host: " + ((Pair) typeCore).getValue0());
        }
        LOGGER.pass("Cores Details is matching", test);

        List<Double> memoryInSummary =
            cloudMigrationPerHostPage.getSummaryReportDetails(InstanceSummaryTableColumn.MEMORY).stream()
            .map(memory -> cloudMigrationPerHostPage.convertMemoryToMB(memory)).collect(Collectors.toList());


        List<Pair> memoryInRecommended =
            (List) recommendedUsages.stream().filter(data -> data instanceof Map).map(data -> Pair.with(
                ((Map) data).get(CloudMappingHostConstants.HostDetails.RecommendedUsages.TYPE).toString(),
                (int) cloudMigrationPerHostPage.convertMemoryToMB(((Map) data)
                    .get(CloudMappingHostConstants.HostDetails.RecommendedUsages.MEMORY).toString()).doubleValue())).collect(Collectors.toList());

        hostSummary.clear();
        for (int i=0; i<coresInSummary.size(); i++) {
            hostSummary.put(instanceInSummary.get(i), (int) memoryInSummary.get(i).doubleValue());
        }

        for (Object typeCore : memoryInRecommended) {
            Assert.assertEquals(hostSummary.get(((Pair) typeCore).getValue0()), ((Pair) typeCore).getValue1(),
                "Incorrect value is displayed of cores value in summary vs recommendation for host: " + ((Pair) typeCore).getValue0());
        }

        LOGGER.pass("Memory Details is matching", test);
    }
}
