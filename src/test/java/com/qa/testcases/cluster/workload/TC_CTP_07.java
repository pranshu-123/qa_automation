package com.qa.testcases.cluster.workload;

import com.qa.base.BaseClass;
import com.qa.pagefactory.TopPanelPageObject;
import com.qa.pagefactory.clusters.WorkloadPageObject;
import com.qa.scripts.DatePicker;
import com.qa.scripts.HomePage;
import com.qa.scripts.clusters.Workload;
import com.qa.utils.GraphUtils;
import com.qa.utils.MouseActions;
import com.qa.utils.WaitExecuter;
import com.relevantcodes.extentreports.LogStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class TC_CTP_07 extends BaseClass {
    Logger logger = LoggerFactory.getLogger(TC_CTP_07.class);

    @Test(dataProvider = "clusterid-data-provider")
    public void VerifySumAverageoption(String clusterId) {
        test = extent.startTest("TC_CTP_07.VerifySumAverageoption",
                "Verify Cluster workload report data will be generated as sum of application count/ average as per requirement");
        test.assignCategory("Cluster - Workload");

        TopPanelPageObject topPanelPageObject = new TopPanelPageObject(driver);
        WorkloadPageObject workloadPageObject = new WorkloadPageObject(driver);
        WaitExecuter waitExecuter = new WaitExecuter(driver);
        GraphUtils graphUtils = new GraphUtils();
        waitExecuter.waitUntilElementPresent(topPanelPageObject.workloadTab);
        waitExecuter.waitUntilPageFullyLoaded();
        waitExecuter.waitUntilElementClickable(topPanelPageObject.workloadTab);
        waitExecuter.sleep(3000);
        MouseActions.clickOnElement(driver, topPanelPageObject.workloadTab);

        Workload workload = new Workload(driver);

        test.log(LogStatus.PASS, "verify Clusterid : " + clusterId);

        HomePage homePage = new HomePage(driver);
        homePage.selectMultiClusterId(clusterId);


        DatePicker datePicker = new DatePicker(driver);
        datePicker.clickOnDatePicker();
        datePicker.selectLast30Days();
        waitExecuter.sleep(1000);

        test.log(LogStatus.PASS, "Verify Workload in selected time range :"
                + workloadPageObject.timerangeMessageElement.getText());

        workload.clickOnHour();
        waitExecuter.sleep(1000);


        workload.clickOnSum();
        waitExecuter.sleep(1000);
        test.log(LogStatus.PASS, "Verify Workload in selected Sum Hour :"
                + workloadPageObject.viewBySum.getText());

        graphUtils.navigateDifferentPointOnGraph(driver, workloadPageObject.HourHighChartContainer);
        List<String> SumTooltipValues = graphUtils.getMemoryTooltipValues();


        Assert.assertTrue(SumTooltipValues.size() > 0, "Hour Sum tooltips are not displayed");
        for (int i = 0; i < SumTooltipValues.size(); i++) {
            Assert.assertNotNull(SumTooltipValues.get(i), "Tooltip value displayed null value for Hour graph");
        }


        test.log(LogStatus.PASS, "Validate When the user hovers the mouse over the Average Hour graph"
                + " it should simultaneously display the tool tip for Average Hour graph at the same data point");

        workload.clickOnAverage();
        waitExecuter.sleep(1000);
        test.log(LogStatus.PASS, "Verify Workload in selected Average Hour :"
                +workloadPageObject.viewByAverage.getText());


        graphUtils.navigateDifferentPointOnGraph(driver,  workloadPageObject.HourHighChartContainer);
        List<String> AverageTooltipValues = graphUtils.getMemoryTooltipValues();

        Assert.assertTrue(AverageTooltipValues.size() > 0, "Hour Average tooltips are not displayed");
        for (int i=0; i<AverageTooltipValues.size(); i++) {
            Assert.assertNotNull(AverageTooltipValues.get(i), "Tooltip value displayed null value for Hour graph");
        }


        test.log(LogStatus.PASS, "Validate When the user hovers the mouse over the Average Hour graph"
                + " it should simultaneously display the tool tip for Average Hour graph at the same data point");


    }
    }
