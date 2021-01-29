package com.qa.testcases.migration.cloud_mapping_per_host;

import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.constants.CloudMappingHostConstants;
import com.qa.enums.UserAction;
import com.qa.enums.migration.CloudProduct;
import com.qa.enums.migration.MigrationCloudMappingHostDetailsTable;
import com.qa.enums.migration.MigrationCloudMappingModalTable;
import com.qa.scripts.migration.CloudMigrationPerHostPage;
import com.qa.utils.LoggingUtils;
import com.qa.utils.WaitExecuter;
import com.qa.utils.actions.UserActions;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author Ankur Jaiswal
 */
@Marker.CloudMappingPerHost
@Marker.All
public class TC_CMP_09 extends BaseClass {
    private final static LoggingUtils LOGGER = new LoggingUtils(TC_CMP_09.class);

    /**
     * Verify Unravel displays a single EMR instance for all the hosts in the cluster.
     */
    @Test
    public void verifySingleEMRInstance() {
        test = extent.startTest("TC_CMP_09.verifySingleEMRInstance", "Verify Unravel displays a single EMR instance for all the hosts in the cluster.");
        test.assignCategory("Migration/Cloud Mapping Per Host");

        CloudMigrationPerHostPage cloudMigrationPerHostPage = new CloudMigrationPerHostPage(driver);
        UserActions actions = new UserActions(driver);
        WaitExecuter waitExecuter = new WaitExecuter(driver);
        LOGGER.info("Navigate to migration host page", test);
        cloudMigrationPerHostPage.navigateToCloudMappingPerHost();
        LOGGER.info("Click on Run button", test);
        cloudMigrationPerHostPage.clickOnRunButton();
        cloudMigrationPerHostPage.waitTillLoaderPresent();

        LOGGER.info("Select amazone EMR as cloud product.", test);
        cloudMigrationPerHostPage.selectCloudProduct(CloudProduct.AMAZON_EMR);
        cloudMigrationPerHostPage.waitTillLoaderPresent();

        cloudMigrationPerHostPage.selectRegion("South America (Sao Paulo)");
        cloudMigrationPerHostPage.waitTillLoaderPresent();

        LOGGER.info("Select first storage.", test);
        cloudMigrationPerHostPage.selectStorage("Object storage");
        cloudMigrationPerHostPage.waitTillLoaderPresent();
        List<WebElement> checkboxListForTable = cloudMigrationPerHostPage.getCheckboxListForTable();
        cloudMigrationPerHostPage.checkUncheckColumn(true);
        try {
            actions.performActionWithPolling(checkboxListForTable.get(0), UserAction.CLICK);
        } catch (IndexOutOfBoundsException outOfBoundsException) {
            Assert.assertTrue(false, "No data displayed in table");
        }
        String expectedVMType = cloudMigrationPerHostPage.getColumnValuesFromModalTable(MigrationCloudMappingModalTable.VM_TYPE).get(0);
        cloudMigrationPerHostPage.clickOnRunButton();

        try {
            waitExecuter.waitUntilTextToBeInWebElement(cloudMigrationPerHostPage.getConfirmationMessage(),
                "Cloud Mapping Per Host completed successfully.");
        } catch (TimeoutException te) {
            Assert.assertTrue(false, "Cloud Mapping Per Host is not completed");
        }
        cloudMigrationPerHostPage.waitTillLoaderPresent();
        waitExecuter.sleep(10000);
        List actualUsages = cloudMigrationPerHostPage.getDataFromCloudMappingTable(
            MigrationCloudMappingHostDetailsTable.ACTUAL_USAGE);

        actualUsages.stream().filter(data -> data instanceof Map).map(data -> ((Map) data)
            .get(CloudMappingHostConstants.HostDetails.ActualUsages.CLUSTER))
            .forEach(data -> Assert.assertTrue(data != null && !data.toString().trim().equals("")));
        LOGGER.pass("Clusters Data displayed properly", test);

        actualUsages.stream().filter(data -> data instanceof Map).map(data -> ((Map) data)
            .get(CloudMappingHostConstants.HostDetails.ActualUsages.CORES))
            .forEach(data -> Assert.assertTrue(data != null && !data.toString().trim().equals("")));
        LOGGER.pass("Cores Data displayed properly", test);

        actualUsages.stream().filter(data -> data instanceof Map).map(data -> ((Map) data)
            .get(CloudMappingHostConstants.HostDetails.ActualUsages.MEMORY))
            .forEach(data -> Assert.assertTrue(data != null && !data.toString().trim().equals("")));
        LOGGER.pass("Memory Data displayed properly", test);

        actualUsages.stream().filter(data -> data instanceof Map).map(data -> ((Map) data)
            .get(CloudMappingHostConstants.HostDetails.ActualUsages.DISK))
            .forEach(data -> Assert.assertTrue(data != null && !data.toString().trim().equals("")));
        LOGGER.pass("Disk Data displayed properly", test);

        List capacityUsages = cloudMigrationPerHostPage.getDataFromCloudMappingTable(
            MigrationCloudMappingHostDetailsTable.CAPACITY);

        capacityUsages.stream().filter(data -> data instanceof Map).map(data -> ((Map) data)
            .get(CloudMappingHostConstants.HostDetails.CapacityUsages.CLUSTER))
            .forEach(data -> Assert.assertTrue(data != null && !data.toString().trim().equals("")));
        LOGGER.pass("Clusters Data displayed properly for capacity", test);

        capacityUsages.stream().filter(data -> data instanceof Map).map(data -> ((Map) data)
            .get(CloudMappingHostConstants.HostDetails.CapacityUsages.CORES))
            .forEach(data -> Assert.assertTrue(data != null && !data.toString().trim().equals("")));
        LOGGER.pass("Cores Data displayed properly for capacity", test);

        capacityUsages.stream().filter(data -> data instanceof Map).map(data -> ((Map) data)
            .get(CloudMappingHostConstants.HostDetails.CapacityUsages.MEMORY))
            .forEach(data -> Assert.assertTrue(data != null && !data.toString().trim().equals("")));
        LOGGER.pass("Memory Data displayed properly for capacity", test);

        capacityUsages.stream().filter(data -> data instanceof Map).map(data -> ((Map) data)
            .get(CloudMappingHostConstants.HostDetails.CapacityUsages.DISK))
            .forEach(data -> Assert.assertTrue(data != null && !data.toString().trim().equals("")));
        LOGGER.pass("Disk Data displayed properly for capacity", test);

        List recommendedUsages = cloudMigrationPerHostPage.getDataFromCloudMappingTable(
            MigrationCloudMappingHostDetailsTable.RECOMMENDATION);
        recommendedUsages.stream().filter(data -> data instanceof Map).map(data -> ((Map) data)
            .get(CloudMappingHostConstants.HostDetails.RecommendedUsages.TYPE))
            .filter(data -> data != null && data != "").forEach(data -> Assert.assertEquals(data, expectedVMType));
        LOGGER.pass("Instance are displayed as per user selected", test);

        Double vmCostPerHourSumTotal =
            recommendedUsages.stream().filter(data -> data instanceof Map).map(data -> ((Map) data)
            .get(CloudMappingHostConstants.HostDetails.RecommendedUsages.VM_COST_PER_HOUR))
            .mapToDouble(cost -> Double.parseDouble(cost.toString().replace("$",""))).sum();

        Double detailsCostPerHourSumTotal =
            recommendedUsages.stream().filter(data -> data instanceof Map).map(data -> ((Map) data)
            .get(CloudMappingHostConstants.HostDetails.RecommendedUsages.DETAILS))
            .filter(data -> data instanceof Map).mapToDouble(data -> Double.parseDouble(((Map) data)
            .get(CloudMappingHostConstants.HostDetails.RecommendedUsages.COST_PER_HOUR).toString().replace("$",""))).sum();

        Double actualCostPerHourInRecommended = vmCostPerHourSumTotal + detailsCostPerHourSumTotal;

        List totalCostUsages = cloudMigrationPerHostPage.getDataFromCloudMappingTable(
            MigrationCloudMappingHostDetailsTable.TOTAL_COST);
        Double totalCostPerHour =
            totalCostUsages.stream().mapToDouble(data -> Double.parseDouble(data.toString().replace("$", ""))).sum();

        Assert.assertEquals(totalCostPerHour, actualCostPerHourInRecommended, "Total cost per hour is not matching");
        LOGGER.pass("Total cost per hour is not matching", test);

        Double liftAndShiftTotalHourlyCost = cloudMigrationPerHostPage.getTotoalHourlyCostValue();
        cloudMigrationPerHostPage.clickOnCostReductionTab();
        Double costReductionTotalHourlyCost = cloudMigrationPerHostPage.getTotoalHourlyCostValue();
        Assert.assertTrue(costReductionTotalHourlyCost <= liftAndShiftTotalHourlyCost,
            "The Total Hourly Cost not equal to or less than what is displayed for Lift and Shift.");
        LOGGER.pass("The Total Hourly Cost is equal to or less than what is displayed for Lift and Shift.", test);
    }
}