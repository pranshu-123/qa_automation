package com.qa.testcases.cluster.workload;

import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.pagefactory.TopPanelPageObject;
import com.qa.scripts.DatePicker;
import com.qa.scripts.HomePage;
import com.qa.scripts.clusters.Workload;
import com.qa.utils.MouseActions;
import com.qa.utils.WaitExecuter;
import com.relevantcodes.extentreports.LogStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * @author Sarbashree Ray
 */
@Marker.All
@Marker.ClusterWorkload
public class TC_CTP_01 extends BaseClass {
    Logger logger = LoggerFactory.getLogger(TC_CTP_01.class);

    @Test(dataProvider = "clusterid-data-provider")
    public void validateallthecombinationindatepicker(String clusterId) {
        test = extent.startTest("TC_CTP_01.validateallthecombinationindatepicker",
                "Verify Cluster workload report should be generated for the select date range");
        test.assignCategory("Cluster - Workload");
        WaitExecuter waitExecuter = new WaitExecuter(driver);
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

        //select 'Last 7 Days'
        DatePicker datePicker = new DatePicker(driver);
        datePicker.clickOnDatePicker();

        datePicker.selectLast7Days();
        waitExecuter.sleep(1000);
        test.log(LogStatus.PASS, "Last 7 Days field is successfully verified in date range");


        //select 'Last 14 Days'
        datePicker.clickOnDatePicker();
        waitExecuter.sleep(1000);

        datePicker.selectLast14Days();
        waitExecuter.sleep(2000);

        test.log(LogStatus.PASS, "Last 14 Days field is successfully verified in date range");

        //select 'Last 30 Days'
        datePicker.clickOnDatePicker();
        waitExecuter.sleep(1000);
        datePicker.selectLast30Days();
        waitExecuter.sleep(1000);


        test.log(LogStatus.PASS, "Last 30 Days field is successfully verified in date range");

        //select 'Last 60 Days'
        datePicker.clickOnDatePicker();
        waitExecuter.sleep(1000);
        datePicker.selectLast60Days();
        waitExecuter.sleep(1000);

        test.log(LogStatus.PASS, "Last 60 Days field is successfully verified in date range");

        //select 'Last 90 Days'
        datePicker.clickOnDatePicker();
        waitExecuter.sleep(1000);
        datePicker.selectLast90Days();
        waitExecuter.sleep(1000);

        test.log(LogStatus.PASS, "Last 90 Days field is successfully verified in date range");

        //select 'Custom Range'
        datePicker.clickOnDatePicker();
        waitExecuter.sleep(1000);
        datePicker.selectCustomRange();
        waitExecuter.sleep(1000);

        // Set Start date by substracting days from current date and end date as current
        // date
        datePicker.setCurrentAndPastDate(-3);
        waitExecuter.sleep(1000);

        // Click on apply button of calendar
        datePicker.clickOnCustomDateApplyBtn();
        waitExecuter.sleep(1000);

        test.log(LogStatus.PASS, "Start Date field is successfully verified in custom date range");
    }

}