package com.qa.testcases.jobs.applications.inefficientapps;

import com.qa.base.BaseClass;
import com.qa.pagefactory.TopPanelComponentPageObject;
import com.qa.pagefactory.jobs.ApplicationsPageObject;
import com.qa.scripts.DatePicker;
import com.qa.utils.WaitExecuter;
import com.relevantcodes.extentreports.LogStatus;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class TC_JIA01 extends BaseClass {

    /**
     * Verify User is able to access Jobs / InEfficient Apps and Verify datepicker filter in UI
     */
    @Test(dataProvider = "clusterid-data-provider")
    public void TC_JIA01_verifyDatePicker(String clusterId) {
        test = extent.startTest("TC_JIA01_verifyDatePicker" + clusterId, "Verify the calender time");
        test.assignCategory(" Jobs / InEfficient Apps");

        WaitExecuter waitExecuter = new WaitExecuter(driver);
        test.log(LogStatus.PASS, "Passed Parameter Is : " + clusterId);

        //Click on Jobs tab
        TopPanelComponentPageObject topPanelComponentPageObject = new TopPanelComponentPageObject(driver);
        waitExecuter.sleep(2000);
        topPanelComponentPageObject.jobs.click();

        //Click on inefficientApps tab
        ApplicationsPageObject applicationsPageObject = new ApplicationsPageObject(driver);
        waitExecuter.sleep(2000);
        applicationsPageObject.inefficientApps.click();

        //Click on date picker
        DatePicker datePicker = new DatePicker(driver);
        datePicker.clickOnDatePicker();

        String [] expectedDatePicker = {"Last 1 Hour","Last 2 Hours","Last 7 Days", "Last 30 Days", "Last 90 Days",
                "This Month","Last Month","Custom Range"};
        List<String> datePickerOptions =datePicker.getDatePickerOptions();
        System.out.println("Actual datepicker list: "+datePickerOptions);

        Boolean boolDate=true;
        for(String strDate: expectedDatePicker){
            if(!datePickerOptions.contains(strDate)){
                boolDate= false;
            }
        }

        Assert.assertTrue(boolDate,"Some of date picker not found.");
        test.log(LogStatus.PASS, "Verified Date Picker successfully.");



    }
}
