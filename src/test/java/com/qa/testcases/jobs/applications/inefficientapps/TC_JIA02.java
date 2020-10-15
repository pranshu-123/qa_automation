package com.qa.testcases.jobs.applications.inefficientapps;

import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.pagefactory.TopPanelComponentPageObject;
import com.qa.pagefactory.jobs.ApplicationsPageObject;
import com.qa.scripts.DatePicker;
import com.qa.utils.WaitExecuter;
import com.relevantcodes.extentreports.LogStatus;
import org.testng.annotations.Test;

@Marker.InefficientApps
@Marker.All
public class TC_JIA02 extends BaseClass {

    /**
     * Verify Custom Range in datepicker
     */
    @Test(dataProvider = "clusterid-data-provider")
    public void verifyCustomRangeDatePicker(String clusterId) {
        test = extent.startTest("TC_JIA02.verifyCustomRangeDatePicker" + clusterId, "Verify the calender time");
        test.assignCategory(" Jobs / InEfficient Apps");

        WaitExecuter waitExecuter = new WaitExecuter(driver);
        test.log(LogStatus.PASS, "Passed Parameter Is : " + clusterId);

        //Click on Jobs tab
        TopPanelComponentPageObject topPanelComponentPageObject = new TopPanelComponentPageObject(driver);
        waitExecuter.sleep(2000);
        topPanelComponentPageObject.jobs.click();
        test.log(LogStatus.PASS, "Jobs tab is clicked successfully");

        //Click on inefficientApps tab
        ApplicationsPageObject applicationsPageObject = new ApplicationsPageObject(driver);
        waitExecuter.sleep(2000);
        applicationsPageObject.inefficientApps.click();
        test.log(LogStatus.PASS, "inefficientApps tab is clicked successfully");

        //Click on date picker
        DatePicker datePicker = new DatePicker(driver);
        datePicker.clickOnDatePicker();

        waitExecuter.sleep(1000);
        datePicker.selectCustomRange();
        waitExecuter.sleep(1000);

        test.log(LogStatus.PASS, "Custom Range date is successfully verified in date range");


    }
}
