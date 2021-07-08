package com.qa.testcases.cluster.workload;

import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.pagefactory.TopPanelPageObject;
import com.qa.pagefactory.clusters.WorkloadPageObject;
import com.qa.scripts.DatePicker;
import com.qa.scripts.HomePage;
import com.qa.scripts.clusters.Workload;
import com.qa.utils.MouseActions;
import com.qa.utils.WaitExecuter;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * @author Sarbashree Ray
 */
@Marker.All
@Marker.ClusterWorkload
public class TC_CTP_04 extends BaseClass {
    Logger logger = LoggerFactory.getLogger(TC_CTP_04.class);

    @Test(dataProvider = "clusterid-data-provider")
    public void VerifyAllViewByFiltersInByJobCountpage(String clusterId) {
        test = extent.startTest("TC_CTP_04.VerifyAllViewByFiltersInByJobCountpage",
                "Verify Cluster workload report should be generated as per selected view by filter");
        test.assignCategory("Cluster - Workload");
        WaitExecuter waitExecuter = new WaitExecuter(driver);
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
        datePicker.selectLast7Days();
        waitExecuter.sleep(1000);
        workload.selectViewBy("Month");
        test.log(LogStatus.PASS, "Verify Workload in selected time range :"
                + workloadPageObject.timerangeMessageElement.getText().trim());

        waitExecuter.sleep(1000);
        test.log(LogStatus.PASS, "Verify View By Month");

        datePicker.clickOnDatePicker();

        datePicker.selectLast14Days();
        waitExecuter.sleep(1000);
        test.log(LogStatus.PASS, "Verify Workload in selected time range :"
                + workloadPageObject.timerangeMessageElement.getText().trim());
        waitExecuter.waitUntilPageFullyLoaded();
        workload.selectViewBy("Day");
        waitExecuter.sleep(1000);
        test.log(LogStatus.PASS, "Verify View By Day");

        waitExecuter.waitUntilPageFullyLoaded();
        datePicker.clickOnDatePicker();
        datePicker.selectLast30Days();
        waitExecuter.sleep(1000);
        test.log(LogStatus.PASS, "Verify Workload in selected time range :"
                + workloadPageObject.timerangeMessageElement.getText().trim());
        waitExecuter.waitUntilPageFullyLoaded();
        workload.selectViewBy("Hour");
        test.log(LogStatus.PASS, "Verify View By Hour");


        datePicker.clickOnDatePicker();
        datePicker.selectLast60Days();
        waitExecuter.sleep(1000);

        test.log(LogStatus.PASS, "Verify Workload in selected time range :"
                + workloadPageObject.timerangeMessageElement.getText().trim());
        waitExecuter.sleep(1000);

        workload.selectViewBy("Hour/Day");
        test.log(LogStatus.PASS, "Verify View By Hour/Day");
        waitExecuter.sleep(1000);

    }
}
