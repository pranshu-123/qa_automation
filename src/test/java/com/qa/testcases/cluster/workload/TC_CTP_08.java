package com.qa.testcases.cluster.workload;

import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.pagefactory.TopPanelPageObject;
import com.qa.pagefactory.clusters.WorkloadPageObject;
import com.qa.scripts.DatePicker;
import com.qa.scripts.HomePage;
import com.qa.scripts.clusters.Workload;
import com.qa.utils.*;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

import java.awt.*;

/**
 * @author Sarbashree Ray
 */
@Marker.All
@Marker.ClusterWorkload
public class TC_CTP_08 extends BaseClass {
    Logger logger = LoggerFactory.getLogger(TC_CTP_08.class);

    @Test(dataProvider = "clusterid-data-provider")
    public void VerifyDateRangeDisplayedInWorkloadReport(String clusterId) {
        test = extent.startTest("TC_CTP_08.VerifyDateRangeDisplayedInWorkloadReport",
                "Verify Date range displayed in workload report should be same as date range selected in date picker");
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

        workload.selectWorkloadType("Yarn");
        waitExecuter.sleep(2000);

        DatePicker datePicker = new DatePicker(driver);
        datePicker.clickOnDatePicker();
        datePicker.selectLast30Days();
        waitExecuter.sleep(3000);

        test.log(LogStatus.PASS, "Verify Workload in selected time range :"
                + workloadPageObject.timerangeMessageElement.getText().trim());

        waitExecuter.sleep(3000);
        workload.selectViewBy("Month");
        test.log(LogStatus.PASS, "Verify View By Month");

        test.log(LogStatus.PASS, "Verify current month selected :"
                + workloadPageObject.currentmonthHeader.getText());
        waitExecuter.sleep(3000);
        workload.selectGroupBy("Memory Hour");
        try {
            waitExecuter.waitUntilElementPresent(workloadPageObject.timeRange);
            test.log(LogStatus.PASS, "Verify Aggregated datewise Job Count in selected time range:"
                    + workloadPageObject.timeRange.getText().trim());
            waitExecuter.waitUntilPageFullyLoaded();
            workload.clickOnDateRange(workloadPageObject);
            waitExecuter.sleep(3000);
            test.log(LogStatus.PASS, "Verify selected time range");
        } catch (VerifyError te) {
            throw new AssertionError("workload selected time range not completed successfully." + te);
        }
        test.log(LogStatus.PASS, "Verify report should be same as date range selected in date picker");
    }
}
