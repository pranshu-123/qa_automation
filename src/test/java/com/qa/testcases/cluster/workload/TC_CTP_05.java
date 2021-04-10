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
public class TC_CTP_05 extends BaseClass {
    Logger logger = LoggerFactory.getLogger(TC_CTP_05.class);

    @Test(dataProvider = "clusterid-data-provider")
    public void VerifyAllViewByFiltersInByVcoresHoursPage(String clusterId) {
        test = extent.startTest("TC_CTP_05.VerifyAllViewByFiltersInByVcoresHoursPage",
                "Verify Cluster workload report should be generated as per selected view by filter");
        test.assignCategory("Cluster - Workload");
        WaitExecuter waitExecuter = new WaitExecuter(driver);
        WorkloadPageObject workloadPageObject = new WorkloadPageObject(driver);
        TopPanelPageObject topPanelPageObject = new TopPanelPageObject(driver);
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
        datePicker.selectLast7Days();
        waitExecuter.sleep(3000);

        test.log(LogStatus.PASS, "Verify Workload in selected time range :"
                + workloadPageObject.timerangeMessageElement.getText().trim());
        waitExecuter.sleep(3000);

        workload.clickOnMonth();
        test.log(LogStatus.PASS, "Verify View By Month");

        waitExecuter.sleep(3000);

        test.log(LogStatus.PASS, "Verify current month selected :"
                + workloadPageObject.currentmonthHeader.getText());
        waitExecuter.sleep(1000);

        datePicker.clickOnDatePicker();
        datePicker.selectLast14Days();
        waitExecuter.sleep(3000);

        test.log(LogStatus.PASS, "Verify Workload in selected time range :"
                + workloadPageObject.timerangeMessageElement.getText().trim());
        waitExecuter.sleep(3000);

        workload.clickOnDay();
        test.log(LogStatus.PASS, "Verify View By Day ");
        waitExecuter.sleep(3000);


        datePicker.clickOnDatePicker();
        datePicker.selectLast30Days();
        waitExecuter.sleep(3000);

        test.log(LogStatus.PASS, "Verify Workload in selected time range :"
                + workloadPageObject.timerangeMessageElement.getText().trim());
        waitExecuter.sleep(3000);

        workload.clickOnHour();
        test.log(LogStatus.PASS, "Verify View By Hour ");

        waitExecuter.sleep(3000);


        datePicker.clickOnDatePicker();
        datePicker.selectLast60Days();
        waitExecuter.sleep(3000);

        test.log(LogStatus.PASS, "Verify Workload in selected time range :"
                + workloadPageObject.timerangeMessageElement.getText().trim());
        waitExecuter.sleep(3000);

        if (workload.clickOnHourDay())
        {
            test.log(LogStatus.PASS, "verify the Group by dept table");

        waitExecuter.sleep(3000);
        test.log(LogStatus.PASS, "Verify View By Hour/Day");
        }
        else{
            test.log(LogStatus.FAIL, "Test Failed the Group by dept table");
        }

        test.log(LogStatus.PASS, "Verify report should be generated as per selected view by filter");
    }
}
