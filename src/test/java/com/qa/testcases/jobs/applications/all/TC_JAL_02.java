package com.qa.testcases.jobs.applications.all;

import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.pagefactory.jobs.ApplicationsPageObject;
import com.qa.scripts.DatePicker;
import com.qa.scripts.jobs.applications.AllApps;
import com.qa.utils.WaitExecuter;
import com.relevantcodes.extentreports.LogStatus;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.logging.Logger;

@Marker.AllApps
@Marker.All
public class TC_JAL_02 extends BaseClass {
    private static final Logger LOGGER = Logger.getLogger(TC_JAL_02.class.getName());

    @Test(dataProvider = "clusterid-data-provider")
    public void validateCustomRangeCalendar() {
        test = extent.startTest("TC_JAL_02.validateCustomRangeCalendar",
                "Verify custom range click opens date-time calendar");
        test.assignCategory("Jobs - Applications");
        test.log(LogStatus.INFO, "Login to the application");
        // Initialize all classes objects
        test.log(LogStatus.INFO, "Initialize all class objects");
        LOGGER.info("Initialize all class objects");
        WaitExecuter waitExecuter = new WaitExecuter(driver);
        ApplicationsPageObject applicationsPageObject = new ApplicationsPageObject(driver);
        DatePicker datePicker = new DatePicker(driver);
        AllApps allApps = new AllApps(driver);
        // Navigate to Jobs tab from header
        test.log(LogStatus.INFO, "Navigate to jobs tab from header");
        allApps.navigateToJobsTab();
        // Click on date picker and select custom range
        test.log(LogStatus.INFO, "Click on date picker and select custom range");
        LOGGER.info("Click on date picker and select custom range");
        datePicker.clickOnDatePicker();
        waitExecuter.sleep(1000);
        datePicker.selectCustomRange();
        waitExecuter.sleep(1000);
        Assert.assertTrue(applicationsPageObject.datepickerCalendar.isDisplayed());
        test.log(LogStatus.PASS, "Verified custom range opens date-time calendar.");

    }
}
