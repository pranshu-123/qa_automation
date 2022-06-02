package com.qa.testcases.emr.jobs.applications;

import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.pagefactory.emr.jobs.EmrApplicationsPageObject;
import com.qa.scripts.DatePicker;
import com.qa.scripts.emr.jobs.EMRAllApps;
import com.qa.utils.LoggingUtils;
import com.qa.utils.WaitExecuter;
import com.relevantcodes.extentreports.LogStatus;
import org.testng.Assert;
import org.testng.annotations.Test;

@Marker.EMRAllApps
public class TC_EMR_JAL_02 extends BaseClass {
    private final LoggingUtils LOGGER = new LoggingUtils(TC_EMR_JAL_02.class);

    @Test
    public void validateCustomRangeCalendar() {
        test = extent.startTest("TC_EMR_JAL_02.validateCustomRangeCalendar",
                "Verify custom range click opens date-time calendar");
        test.assignCategory("Jobs - Applications");
        test.log(LogStatus.INFO, "Login to the application");
        // Initialize all classes objects
        test.log(LogStatus.INFO, "Initialize all class objects");
        LOGGER.info("Initialize all class objects",test);
        WaitExecuter waitExecuter = new WaitExecuter(driver);
        EmrApplicationsPageObject applicationsPageObject = new EmrApplicationsPageObject(driver);
        DatePicker datePicker = new DatePicker(driver);
        EMRAllApps allApps = new EMRAllApps(driver);
        // Navigate to Jobs tab from header
        test.log(LogStatus.INFO, "Navigate to jobs tab from header");
        allApps.navigateToJobsTab();
        // Click on date picker and select custom range
        test.log(LogStatus.INFO, "Click on date picker and select custom range");
        LOGGER.info("Click on date picker and select custom range",test);
        datePicker.clickOnDatePicker();
        waitExecuter.sleep(1000);
        datePicker.selectCustomRange();
        waitExecuter.sleep(1000);
        Assert.assertTrue(applicationsPageObject.datepickerCalendar.isDisplayed());
        test.log(LogStatus.PASS, "Verified custom range opens date-time calendar.");

    }
}
