package com.qa.testcases.cluster.workload;

import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.pagefactory.clusters.WorkloadPageObject;
import com.qa.scripts.DatePicker;
import com.qa.scripts.HomePage;
import com.qa.scripts.clusters.Workload;
import com.qa.utils.GraphUtils;
import com.qa.utils.WaitExecuter;
import com.relevantcodes.extentreports.LogStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

@Marker.All
@Marker.ClusterWorkload
@Marker.GCPClusterWorkload
public class TC_CTP_06 extends BaseClass {
    Logger logger = LoggerFactory.getLogger(TC_CTP_06.class);

    @Test(dataProvider = "clusterid-data-provider")
    public void VerifyAllViewByFiltersInByMemoryHoursPage(String clusterId) {
        test = extent.startTest("TC_CTP_06.VerifyAllViewByFiltersInByMemoryHoursPage",
                "Verify Cluster workload report should be generated as per selected view by filter");
        test.assignCategory("Cluster - Workload");
        WaitExecuter waitExecuter = new WaitExecuter(driver);
        GraphUtils graphUtils = new GraphUtils();
        WorkloadPageObject workloadPageObject = new WorkloadPageObject(driver);
        Workload workload = new Workload(driver);
        workload.selectWorkloadTab();
        waitExecuter.sleep(2000);

        test.log(LogStatus.PASS, "verify Clusterid : " + clusterId);

        HomePage homePage = new HomePage(driver);
        homePage.selectMultiClusterIdClusterPage(clusterId);
        waitExecuter.sleep(2000);
        waitExecuter.waitUntilPageFullyLoaded();

        workload.selectWorkloadType("Impala");
        waitExecuter.sleep(2000);

        DatePicker datePicker = new DatePicker(driver);
        datePicker.clickOnDatePicker();
        datePicker.selectLast30Days();
        waitExecuter.sleep(3000);

        test.log(LogStatus.PASS, "Verify Workload in selected time range :"
                + workloadPageObject.timerangeMessageElement.getText().trim());
        waitExecuter.waitUntilPageFullyLoaded();
        workload.selectGroupBy("vCore Hour");
        test.log(LogStatus.PASS, "Verify Group By vCore Hour");
        waitExecuter.waitUntilPageFullyLoaded();
        workload.selectViewBy("Hour");
        test.log(LogStatus.PASS, "Verify View By Month");
        waitExecuter.sleep(2000);
        workload.selectAggregateBy("Sum");
        test.log(LogStatus.PASS, "Verify Aggregate By Sum");
        waitExecuter.sleep(1000);


        graphUtils.navigateDifferentPointOnGraph(driver, workloadPageObject.HourHighChartContainer);
        List<String> SumTooltipValues = graphUtils.getMemoryTooltipValues();
        waitExecuter.sleep(1000);
        logger.info("Sum tooltip values : " + SumTooltipValues);
        for (int i = 0; i < SumTooltipValues.size(); i++) {
            Assert.assertNotNull(SumTooltipValues.get(i), "Tooltip value displayed null value for Sum Hour graph");
            Assert.assertNotEquals(SumTooltipValues.get(i), "",
                    "Tooltip value displayed blank value for Sum Hour Graph");
        }
        test.log(LogStatus.PASS, "Validate When the user hovers the mouse over the Sum Hour graph"
                + " it should simultaneously display the tool tip for  Sum Hour graph at the same data point");


        test.log(LogStatus.PASS, "Verify report data will be generated as sum of application count/ average");

        workload.selectGroupBy("Memory Hour");
        test.log(LogStatus.PASS, "Verify Group By Memory Hour");
        waitExecuter.waitUntilPageFullyLoaded();
        workload.selectViewBy("Hour");
        test.log(LogStatus.PASS, "Verify View By Month");
        waitExecuter.sleep(2000);
        workload.selectAggregateBy("Average");
        test.log(LogStatus.PASS, "Verify Aggregate By Average");
        waitExecuter.sleep(2000);


        graphUtils.navigateDifferentPointOnGraph(driver, workloadPageObject.HourHighChartContainer);
        List<String> AverageTooltipValues = graphUtils.getMemoryTooltipValues();
        waitExecuter.sleep(1000);
        logger.info("Sum tooltip values : " + AverageTooltipValues);
        for (int i = 0; i < AverageTooltipValues.size(); i++) {
            Assert.assertNotNull(AverageTooltipValues.get(i), "Tooltip value displayed null value for Sum Hour graph");
            Assert.assertNotEquals(AverageTooltipValues.get(i), "",
                    "Tooltip value displayed blank value for Sum Hour Graph");
        }
        test.log(LogStatus.PASS, "Validate When the user hovers the mouse over the Sum Hour graph"
                + " it should simultaneously display the tool tip for  Sum Hour graph at the same data point");


        test.log(LogStatus.PASS, "Verify report data will be generated as sum of application count/ average");
    }
}

