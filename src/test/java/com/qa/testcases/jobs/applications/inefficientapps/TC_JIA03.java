package com.qa.testcases.jobs.applications.inefficientapps;

import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.pagefactory.SubTopPanelModulePageObject;
import com.qa.pagefactory.jobs.ApplicationsPageObject;
import com.qa.scripts.DatePicker;
import com.qa.utils.WaitExecuter;
import com.relevantcodes.extentreports.LogStatus;
import org.testng.annotations.Test;

@Marker.InefficientApps
@Marker.All
public class TC_JIA03 extends BaseClass {

    /**
     * Verify applications with Inefficient events are listed for different date ranges
     */
    @Test(dataProvider = "clusterid-data-provider")
    public void TC_JIA03_verifyAllDatePicker(String clusterId) {
        test = extent.startTest("TC_JIA03_verifyAllDatePicker" + clusterId, "Verify applications with Inefficient events are listed for different date ranges");
        test.assignCategory(" Jobs / InEfficient Apps");

        WaitExecuter waitExecuter = new WaitExecuter(driver);
        test.log(LogStatus.PASS, "Passed Parameter Is : " + clusterId);

        //Click on Jobs tab
        SubTopPanelModulePageObject subTopPanelModulePageObject = new SubTopPanelModulePageObject(driver);
        waitExecuter.sleep(2000);
        subTopPanelModulePageObject.jobs.click();

        //Click on inefficientApps tab
        ApplicationsPageObject applicationsPageObject = new ApplicationsPageObject(driver);
        waitExecuter.sleep(2000);
        applicationsPageObject.inefficientApps.click();

        //Click on date picker
        DatePicker datePicker = new DatePicker(driver);
        datePicker.clickOnDatePicker();

        // Click on combination of daterange
        datePicker.selectLastOneHour();
        waitExecuter.sleep(1000);
        test.log(LogStatus.PASS, "Last 1 Hour field is successfully verified in date range");


        //select 'Last 2 Hours'
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
        test.log(LogStatus.PASS, "Custom Range date is successfully verified in date range");
        test.log(LogStatus.PASS, "Verified applications with Inefficient events are listed for different date ranges.");

    }

}
