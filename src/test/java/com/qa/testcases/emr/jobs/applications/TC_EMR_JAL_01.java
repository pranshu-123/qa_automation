package com.qa.testcases.emr.jobs.applications;

import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.constants.DatePickerConstants;
import com.qa.scripts.DatePicker;
import com.qa.scripts.emr.jobs.EMRAllApps;
import com.qa.utils.LoggingUtils;
import com.qa.utils.WaitExecuter;
import com.relevantcodes.extentreports.LogStatus;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

@Marker.EMRAllApps
public class TC_EMR_JAL_01 extends BaseClass {
    private final LoggingUtils LOGGER = new LoggingUtils(TC_EMR_JAL_01.class);

    @Test()
    public void validateDatePickerFilters() {
        test = extent.startTest("TC_EMR_JAL_01.validateDatePickerFilters",
                "Verify the options available in date picker filter");
        test.assignCategory("Jobs - Applications");
        test.log(LogStatus.INFO, "Login to the application");
        // Initialize all classes objects
        test.log(LogStatus.INFO, "Initialize all class objects");
        LOGGER.info("Initialize all class objects",test);
        WaitExecuter waitExecuter = new WaitExecuter(driver);
        DatePicker datePicker = new DatePicker(driver);
        EMRAllApps allApps = new EMRAllApps(driver);
        // Navigate to Jobs tab from header
        test.log(LogStatus.INFO, "Navigate to jobs tab from header");
        allApps.navigateToJobsTab();
        waitExecuter.sleep(3000);
        // Click on date picker and get list of calendar ranges
        test.log(LogStatus.INFO, "Click on date picker and list of calendar ranges");
        LOGGER.info("Click on date picker and list of calendar ranges",test);
        datePicker.clickOnDatePicker();
        waitExecuter.sleep(1000);
        List<String> calendarRanges = allApps.getCalendarRanges();
        waitExecuter.sleep(1000);

        Assert.assertTrue(calendarRanges.contains(DatePickerConstants.DatePicker.LAST_1_HOUR),
                "Last 1 Hour is not present in datepicker filter ");
        test.log(LogStatus.PASS, "Verified 'Last 1 hour' option in date picker filter.");
        Assert.assertTrue(calendarRanges.contains(DatePickerConstants.DatePicker.LAST_2_HOUR),
                "Last 2 Hour is not present in datepicker filter");
        test.log(LogStatus.PASS, "Verified 'Last 2 hour' option in date picker filter.");
        Assert.assertTrue(calendarRanges.contains(DatePickerConstants.DatePicker.LAST_6_HOUR),
                "Last 6 Hour is not present in datepicker filter");
        test.log(LogStatus.PASS, "Verified 'Last 6 hour' option in date picker filter.");
        Assert.assertTrue(calendarRanges.contains(DatePickerConstants.DatePicker.LAST_12_HOUR),
                "Last 12 Hour is not present in datepicker filter");
        test.log(LogStatus.PASS, "Verified 'Last 12 hour' option in date picker filter.");
        Assert.assertTrue(calendarRanges.contains(DatePickerConstants.DatePicker.TODAY),
                "Today is not present in datepicker filter");
        test.log(LogStatus.PASS, "Verified 'Today' option in date picker filter.");
        Assert.assertTrue(calendarRanges.contains(DatePickerConstants.DatePicker.YESTERDAY),
                "Yesterday is not present in datepicker filter");
        test.log(LogStatus.PASS, "Verified 'Yesterday' option in date picker filter.");
        Assert.assertTrue(calendarRanges.contains(DatePickerConstants.DatePicker.LAST_7_DAYS),
                "Last 7 Day is not present in datepicker filter");
        test.log(LogStatus.PASS, "Verified 'Last 7 days' option in date picker filter.");
        Assert.assertTrue(calendarRanges.contains(DatePickerConstants.DatePicker.LAST_30_DAYS),
                "Last 30 Days is not present in datepicker filter");
        test.log(LogStatus.PASS, "Verified 'Last 30 days' option in date picker filter.");
        Assert.assertTrue(calendarRanges.contains(DatePickerConstants.DatePicker.LAST_90_DAYS),
                "Last 90 days is not present in datepicker filter");
        test.log(LogStatus.PASS, "Verified 'Last 90 days' option in date picker filter.");
        Assert.assertTrue(calendarRanges.contains(DatePickerConstants.DatePicker.LAST_MONTH),
                "Last Month is not present in datepicker filter");
        test.log(LogStatus.PASS, "Verified 'Last Month' option in date picker filter.");
        Assert.assertTrue(calendarRanges.contains(DatePickerConstants.DatePicker.THIS_MONTH),
                "This Month is not present in datepicker filter");
        test.log(LogStatus.PASS, "Verified 'This Month' option in date picker filter.");
        Assert.assertTrue(calendarRanges.contains(DatePickerConstants.DatePicker.CUSTOM_RANGE),
                "Custom Range is not present in datepicker filter");
        test.log(LogStatus.PASS, "Verified 'Custom Range' option in date picker filter.");
    }

}

