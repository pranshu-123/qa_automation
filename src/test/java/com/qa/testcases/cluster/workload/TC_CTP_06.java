package com.qa.testcases.cluster.workload;

import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.pagefactory.TopPanelPageObject;
import com.qa.pagefactory.clusters.WorkloadPageObject;
import com.qa.scripts.DatePicker;
import com.qa.scripts.HomePage;
import com.qa.scripts.clusters.Workload;
import com.qa.utils.DateUtils;
import com.qa.utils.MouseActions;
import com.qa.utils.WaitExecuter;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

@Marker.All
@Marker.ClusterWorkload
public class TC_CTP_06 extends BaseClass {
    Logger logger = LoggerFactory.getLogger(TC_CTP_06.class);

    @Test(dataProvider = "clusterid-data-provider")
    public void VerifyAllViewByFiltersInByMemoryHoursPage(String clusterId) {
        test = extent.startTest("TC_CTP_06.VerifyAllViewByFiltersInByMemoryHoursPage",
                "Verify Cluster workload report should be generated as per selected view by filter");
        test.assignCategory("Cluster - Workload");
        WaitExecuter waitExecuter = new WaitExecuter(driver);
        WorkloadPageObject workloadPageObject = new WorkloadPageObject(driver);
        Workload workload = new Workload(driver);
        workload.selectWorkloadTab();
        waitExecuter.sleep(2000);

        test.log(LogStatus.PASS, "verify Clusterid : " + clusterId);

        HomePage homePage = new HomePage(driver);
        homePage.selectMultiClusterId(clusterId);
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
        test.log(LogStatus.PASS, "Verify View By Month");

        test.log(LogStatus.PASS, "Verify current month selected :"
                + workloadPageObject.currentmonthHeader.getText());

        datePicker.clickOnDatePicker();
        datePicker.selectLast14Days();
        waitExecuter.sleep(3000);

        test.log(LogStatus.PASS, "Verify Workload in selected time range :"
                + workloadPageObject.timerangeMessageElement.getText().trim());

        workload.clickOnDay();
        waitExecuter.sleep(3000);
        test.log(LogStatus.PASS, "Verify View By Day ");


        datePicker.clickOnDatePicker();
        datePicker.selectLast30Days();

        test.log(LogStatus.PASS, "Verify Workload in selected time range :"
                + workloadPageObject.timerangeMessageElement.getText().trim());

        workload.clickOnHour();
        test.log(LogStatus.PASS, "Verify View By Hour ");


        datePicker.clickOnDatePicker();
        datePicker.selectLast60Days();
        waitExecuter.sleep(3000);

        test.log(LogStatus.PASS, "Verify Workload in selected time range :"
                + workloadPageObject.timerangeMessageElement.getText().trim());

        if(workload.clickOnHourDay()){
        waitExecuter.sleep(3000);
        test.log(LogStatus.PASS, "Verify View By Hour/Day :-"
                + workloadPageObject.viewByHourDay.isDisplayed());
       }
        else{
        test.log(LogStatus.FAIL, "Test Failed Verify View By Hour/Day");
    }
        test.log(LogStatus.PASS, "Verify workload report should be generated as per selected view by filter");
    }
}

