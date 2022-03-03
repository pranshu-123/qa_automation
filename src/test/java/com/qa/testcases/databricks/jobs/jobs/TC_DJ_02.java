package com.qa.testcases.databricks.jobs.jobs;

import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.pagefactory.databricks.DbxSubTopPanelModulePageObject;
import com.qa.pagefactory.databricks.jobs.DbxApplicationsPageObject;
import com.qa.scripts.DatePicker;
import com.qa.scripts.databricks.jobs.DbAllApps;
import com.qa.testcases.databricks.jobs.runs.TC_DR_01;
import com.qa.testcases.databricks.jobs.runs.TC_DR_11;
import com.qa.utils.LoggingUtils;
import com.qa.utils.WaitExecuter;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
@Marker.DbxJobs
@Marker.All
public class TC_DJ_02 extends BaseClass {
    private final LoggingUtils loggingUtils = new LoggingUtils(TC_DJ_02.class);

    @Test()
    public void VerifyDatepickerInUI() {
        test = extent.startTest("TC_DR_01.validateFilterByClusterName",
                "Verify datepicker filter in UI");
        test.log(LogStatus.INFO, "Login to the application");
        // Initialize all classes objects
        test.log(LogStatus.INFO, "Initialize all class objects");
        loggingUtils.info("Initialize all class objects",test);
        WaitExecuter waitExecuter = new WaitExecuter(driver);
        DbxSubTopPanelModulePageObject dbpageObject = new DbxSubTopPanelModulePageObject(driver);
        DbAllApps allApps = new DbAllApps(driver);
        // Navigate to Jobs tab from header
        test.log(LogStatus.INFO, "Navigate to jobs tab from header");
        allApps.navigateToJobsTab("Jobs");
        waitExecuter.waitUntilElementPresent(dbpageObject.jobsTabs);

        // Navigate to Jobs tab from header
        test.log(LogStatus.INFO, "Navigate to jobs tab from header");
        test.log(LogStatus.INFO, "Select last 7 days");
        allApps.inJobsSelectClusterAndLast7Days();

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
        loggingUtils.info("Verified Date Picker successfully.",test);
    }
}

