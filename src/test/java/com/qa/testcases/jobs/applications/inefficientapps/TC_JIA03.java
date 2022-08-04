package com.qa.testcases.jobs.applications.inefficientapps;

import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.enums.UserAction;
import com.qa.pagefactory.SubTopPanelModulePageObject;
import com.qa.pagefactory.jobs.ApplicationsPageObject;
import com.qa.scripts.DatePicker;
import com.qa.utils.WaitExecuter;
import com.qa.utils.actions.UserActions;
import com.relevantcodes.extentreports.LogStatus;
import org.testng.annotations.Test;

@Marker.InefficientApps
@Marker.GCPInefficientApps
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
        ApplicationsPageObject applicationsPageObject = new ApplicationsPageObject(driver);
        test.log(LogStatus.INFO, "Passed Parameter Is : " + clusterId);

        //Click on Jobs tab
        SubTopPanelModulePageObject subTopPanelModulePageObject = new SubTopPanelModulePageObject(driver);
        UserActions userAction = new UserActions(driver);
        waitExecuter.waitUntilElementClickable(subTopPanelModulePageObject.jobs);
        userAction.performActionWithPolling(subTopPanelModulePageObject.jobs, UserAction.CLICK);

        //Click on inefficientApps tab
        waitExecuter.waitUntilElementClickable(applicationsPageObject.inefficientApps);
        userAction.performActionWithPolling(applicationsPageObject.inefficientApps, UserAction.CLICK);

        //Click on date picker
        DatePicker datePicker = new DatePicker(driver);
        datePicker.clickOnDatePicker();
        waitExecuter.waitUntilElementClickable(applicationsPageObject.resetButton);

        // Click on combination of daterange
        datePicker.selectLastOneHour();
        waitExecuter.waitUntilElementClickable(applicationsPageObject.resetButton);
        test.log(LogStatus.PASS, "Last 1 Hour field is successfully verified in date range");


        //select 'Last 2 Hours'
        datePicker.clickOnDatePicker();
        waitExecuter.waitUntilElementClickable(applicationsPageObject.resetButton);
        datePicker.selectLastTwoHour();
        waitExecuter.waitUntilElementClickable(applicationsPageObject.resetButton);
        test.log(LogStatus.PASS, "Last 2 Hours field is successfully verified in date range");

        datePicker.clickOnDatePicker();
        waitExecuter.waitUntilElementClickable(applicationsPageObject.resetButton);
        datePicker.selectLastSixHour();
        waitExecuter.waitUntilElementClickable(applicationsPageObject.resetButton);


        test.log(LogStatus.PASS, "lastSixHour field is successfully verified in date range");

        datePicker.clickOnDatePicker();
        waitExecuter.waitUntilElementClickable(applicationsPageObject.resetButton);
        datePicker.selectLast12Hour();
        waitExecuter.waitUntilElementClickable(applicationsPageObject.resetButton);
        test.log(LogStatus.PASS, "last12Hours field is successfully verified in date range");

        datePicker.clickOnDatePicker();
        waitExecuter.waitUntilElementClickable(applicationsPageObject.resetButton);
        datePicker.selectToday();
        waitExecuter.waitUntilElementClickable(applicationsPageObject.resetButton);
        test.log(LogStatus.PASS, "today field is successfully verified in date range");

        datePicker.clickOnDatePicker();
        waitExecuter.waitUntilElementClickable(applicationsPageObject.resetButton);
        datePicker.selectYesterday();
        waitExecuter.waitUntilElementClickable(applicationsPageObject.resetButton);
        test.log(LogStatus.PASS, "yesterday field is successfully verified in date range");

        datePicker.clickOnDatePicker();
        waitExecuter.waitUntilElementClickable(applicationsPageObject.resetButton);
        datePicker.selectLast7Days();
        waitExecuter.waitUntilElementClickable(applicationsPageObject.resetButton);
        test.log(LogStatus.PASS, "Last 7 Days field is successfully verified in date range");

        datePicker.clickOnDatePicker();
        waitExecuter.waitUntilElementClickable(applicationsPageObject.resetButton);
        datePicker.selectLast30Days();
        waitExecuter.waitUntilElementClickable(applicationsPageObject.resetButton);


        test.log(LogStatus.PASS, "Last 30 Days field is successfully verified in date range");

        datePicker.clickOnDatePicker();
        waitExecuter.waitUntilElementClickable(applicationsPageObject.resetButton);
        datePicker.selectLast90Days();
        waitExecuter.waitUntilElementClickable(applicationsPageObject.resetButton);

        test.log(LogStatus.PASS, "Last 90 Days field is successfully verified in date range");

        datePicker.clickOnDatePicker();
        waitExecuter.waitUntilElementClickable(applicationsPageObject.resetButton);
        datePicker.selectThisMonth();
        waitExecuter.waitUntilElementClickable(applicationsPageObject.resetButton);
        test.log(LogStatus.PASS, "This Month field is successfully verified in date range");

        datePicker.clickOnDatePicker();
        waitExecuter.waitUntilElementClickable(applicationsPageObject.resetButton);
        datePicker.selectLastMonth();
        waitExecuter.waitUntilElementClickable(applicationsPageObject.resetButton);

        test.log(LogStatus.PASS, "Last Month field is successfully verified in date range");
        datePicker.clickOnDatePicker();
        waitExecuter.waitUntilElementClickable(applicationsPageObject.resetButton);
        datePicker.selectCustomRange();
        waitExecuter.waitUntilElementClickable(applicationsPageObject.resetButton);
        test.log(LogStatus.PASS, "Custom Range date is successfully verified in date range");
        test.log(LogStatus.PASS, "Verified applications with Inefficient events are listed for different date ranges.");

    }

}
