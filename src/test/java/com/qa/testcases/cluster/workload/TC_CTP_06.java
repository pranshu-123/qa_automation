package com.qa.testcases.cluster.workload;

import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.pagefactory.clusters.WorkloadPageObject;
import com.qa.scripts.DatePicker;
import com.qa.scripts.HomePage;
import com.qa.scripts.clusters.Workload;
import com.qa.utils.DateUtils;
import com.qa.utils.WaitExecuter;
import com.relevantcodes.extentreports.LogStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

@Marker.All
@Marker.ClusterWorkload
public class TC_CTP_06 extends BaseClass {
    Logger logger = LoggerFactory.getLogger(TC_CTP_06.class);

    @Test(dataProvider = "clusterid-data-provider")
    public void VerifyallviewbyfiltersinByMemoryHourspage(String clusterId) {
        test = extent.startTest("TC_CTP_06.VerifyallviewbyfiltersinByMemoryHourspage",
                "Verify Cluster workload report should be generated as per selected view by filter");
        test.assignCategory("Cluster - Workload");
        WaitExecuter waitExecuter = new WaitExecuter(driver);
        WorkloadPageObject workloadPageObject = new WorkloadPageObject(driver);
        Workload workload = new Workload(driver);
        workload.selectByMemoryHours();
        waitExecuter.sleep(3000);

        test.log(LogStatus.PASS, "verify Clusterid : " + clusterId);

        HomePage homePage = new HomePage(driver);
        homePage.selectMultiClusterId(clusterId);

        DatePicker datePicker = new DatePicker(driver);
        datePicker.clickOnDatePicker();
        datePicker.selectLast7Days();

        test.log(LogStatus.PASS, "Verify Workload in selected time range :"
                + workloadPageObject.timerangeMessageElement.getText());

        workload.clickOnMonth();
        test.log(LogStatus.PASS, "Verify View By Month :-"
                + workloadPageObject.viewByMonth.isDisplayed());

        test.log(LogStatus.PASS, "Verify current month selected :"
                + workloadPageObject.currentmonthHeader.getText());

        datePicker.clickOnDatePicker();
        datePicker.selectLast14Days();

        test.log(LogStatus.PASS, "Verify Workload in selected time range :"
                + workloadPageObject.timerangeMessageElement.getText());

        workload.clickOnDay();
        test.log(LogStatus.PASS, "Verify View By Day :-"
                + workloadPageObject.viewByDay.isDisplayed());


        datePicker.clickOnDatePicker();
        datePicker.selectLast30Days();

        test.log(LogStatus.PASS, "Verify Workload in selected time range :"
                + workloadPageObject.timerangeMessageElement.getText());

        workload.clickOnHour();
        test.log(LogStatus.PASS, "Verify View By Hour :-"
                + workloadPageObject.viewByHour.isDisplayed());


        datePicker.clickOnDatePicker();
        datePicker.selectLast60Days();

        test.log(LogStatus.PASS, "Verify Workload in selected time range :"
                + workloadPageObject.timerangeMessageElement.getText());

        workload.clickOnHourDay();
        test.log(LogStatus.PASS, "Verify View By Hour/Day :-"
                + workloadPageObject.viewByHourDay.isDisplayed());


    }
}

