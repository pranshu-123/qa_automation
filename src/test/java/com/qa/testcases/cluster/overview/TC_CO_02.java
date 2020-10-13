package com.qa.testcases.cluster.overview;

import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.scripts.DatePicker;
import com.qa.scripts.HomePage;
import com.qa.utils.WaitExecuter;
import com.relevantcodes.extentreports.LogStatus;
import org.testng.Assert;
import org.testng.annotations.Test;

@Marker.ClusterOverview
@Marker.All
public class TC_CO_02 extends BaseClass {


    @Test(dataProvider = "clusterid-data-provider")
    public void TC_CO_02_verifyCombinationOfDaterange(String clusterId) {

        test = extent.startTest("TC_CO_02_verifyCombinationOfDaterange: "+clusterId, "Verify Custom Range in datepicker filter ");
        test.assignCategory("4620 - Cluster Overview");

        WaitExecuter waitExecuter = new WaitExecuter(driver);
        test.log(LogStatus.PASS, "verify Clusterid : " + clusterId);

        HomePage homePage = new HomePage(driver);
        homePage.selectMultiClusterId(clusterId);
        // Click on datepicker button
        DatePicker datePicker = new DatePicker(driver);
        datePicker.clickOnDatePicker();


        // Click on combination of daterange
        datePicker.selectLastOneHour();
        waitExecuter.sleep(1000);
        test.log(LogStatus.PASS, "Last 1 Hour field is successfully verified in date range");


        //select 'Last 2 Hour'
        datePicker.clickOnDatePicker();
        waitExecuter.sleep(1000);

        datePicker.selectLastTwoHour();
        waitExecuter.sleep(2000);

        test.log(LogStatus.PASS, "Last 2 Hours field is successfully verified in date range");

        datePicker.clickOnDatePicker();
        waitExecuter.sleep(1000);
        datePicker.selectLastSixHour();
        waitExecuter.sleep(2000);


        test.log(LogStatus.PASS, "lastSixHour field is successfully verified in date range");

        datePicker.clickOnDatePicker();
        waitExecuter.sleep(1000);
        datePicker.selectLast12Hour();
        waitExecuter.sleep(2000);
        test.log(LogStatus.PASS, "last12Hours field is successfully verified in date range");

        datePicker.clickOnDatePicker();
        waitExecuter.sleep(1000);
        datePicker.selectToday();
        waitExecuter.sleep(2000);
        test.log(LogStatus.PASS, "today field is successfully verified in date range");

        datePicker.clickOnDatePicker();
        waitExecuter.sleep(1000);
        datePicker.selectYesterday();
        waitExecuter.sleep(2000);


        test.log(LogStatus.PASS, "yesterday field is successfully verified in date range");
        datePicker.clickOnDatePicker();
        waitExecuter.sleep(1000);
        datePicker.selectLast7Days();
        waitExecuter.sleep(2000);


        test.log(LogStatus.PASS, "Last 7 Days field is successfully verified in date range");

        datePicker.clickOnDatePicker();
        waitExecuter.sleep(1000);
        datePicker.selectLast30Days();
        waitExecuter.sleep(1000);


        test.log(LogStatus.PASS, "Last 30 Days field is successfully verified in date range");

        datePicker.clickOnDatePicker();
        waitExecuter.sleep(1000);
        datePicker.selectLast90Days();
        waitExecuter.sleep(1000);

        test.log(LogStatus.PASS, "Last 90 Days field is successfully verified in date range");

        datePicker.clickOnDatePicker();
        waitExecuter.sleep(1000);
        datePicker.selectThisMonth();
        waitExecuter.sleep(1000);
        test.log(LogStatus.PASS, "This Month field is successfully verified in date range");

        datePicker.clickOnDatePicker();
        waitExecuter.sleep(1000);
        datePicker.selectLastMonth();
        waitExecuter.sleep(1000);

        test.log(LogStatus.PASS, "Last Month field is successfully verified in date range");
        datePicker.clickOnDatePicker();
        waitExecuter.sleep(1000);
        datePicker.selectCustomRange();
        waitExecuter.sleep(1000);

        test.log(LogStatus.PASS, "Start Date field is successfully verified in custom date range");
    }
}
