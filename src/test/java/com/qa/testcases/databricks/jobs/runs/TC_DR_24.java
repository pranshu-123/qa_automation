package com.qa.testcases.databricks.jobs.runs;

import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.constants.DatePickerConstants;
import com.qa.pagefactory.databricks.DbxSubTopPanelModulePageObject;
import com.qa.pagefactory.databricks.jobs.DbxApplicationsPageObject;
import com.qa.scripts.DatePicker;
import com.qa.scripts.databricks.jobs.DbAllApps;
import com.qa.utils.LoggingUtils;
import com.qa.utils.WaitExecuter;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.NoSuchElementException;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

@Marker.DbxJobsRuns
@Marker.All
public class TC_DR_24 extends BaseClass {
    private final LoggingUtils loggingUtils = new LoggingUtils(TC_DR_24.class);

    @Test()
    public void validateDatePickerFilters() {
        test = extent.startTest("TC_DR_24.validateDatePickerFilters",
                "Verify the options available in date picker filter");
        test.log(LogStatus.INFO, "Login to the application");
        // Initialize all classes objects
        test.log(LogStatus.INFO, "Initialize all class objects");
        loggingUtils.info("Initialize all class objects", test);
        WaitExecuter waitExecuter = new WaitExecuter(driver);
        DatePicker datePicker = new DatePicker(driver);
        DbAllApps dballApps = new DbAllApps(driver);
        // Navigate to Runs tab from header
        test.log(LogStatus.INFO, "Navigate to jobs tab from header");
        dballApps.navigateToJobsTab("Runs");
        waitExecuter.waitUntilPageFullyLoaded();
        try {
            // Click on date picker and get list of calendar ranges
            test.log(LogStatus.INFO, "Click on date picker and list of calendar ranges");
            loggingUtils.info("Click on date picker and list of calendar ranges",test);
            datePicker.clickOnDatePicker();
            waitExecuter.sleep(1000);
            List<String> calendarRanges = dballApps.getCalendarRanges();
            waitExecuter.sleep(1000);

            Assert.assertTrue(calendarRanges.contains(DatePickerConstants.DatePicker.LAST_1_HOUR),
                    "Last 1 Hour is not presemt in datepicker filter ");
            test.log(LogStatus.PASS, "Verified 'Last 1 hour' option in date picker filter.");
            Assert.assertTrue(calendarRanges.contains(DatePickerConstants.DatePicker.LAST_2_HOUR),
                    "Last 2 Hour is not presemt in datepicker filter");
            test.log(LogStatus.PASS, "Verified 'Last 2 hour' option in date picker filter.");
            Assert.assertTrue(calendarRanges.contains(DatePickerConstants.DatePicker.LAST_6_HOUR),
                    "Last 6 Hour is not presemt in datepicker filter");
            test.log(LogStatus.PASS, "Verified 'Last 6 hour' option in date picker filter.");
            Assert.assertTrue(calendarRanges.contains(DatePickerConstants.DatePicker.LAST_12_HOUR),
                    "Last 12 Hour is not presemt in datepicker filter");
            test.log(LogStatus.PASS, "Verified 'Last 12 hour' option in date picker filter.");
            Assert.assertTrue(calendarRanges.contains(DatePickerConstants.DatePicker.TODAY),
                    "Today is not presemt in datepicker filter");
            test.log(LogStatus.PASS, "Verified 'Today' option in date picker filter.");
            Assert.assertTrue(calendarRanges.contains(DatePickerConstants.DatePicker.YESTERDAY),
                    "Yesterday is not presemt in datepicker filter");
            test.log(LogStatus.PASS, "Verified 'Yesterday' option in date picker filter.");
            Assert.assertTrue(calendarRanges.contains(DatePickerConstants.DatePicker.LAST_7_DAYS),
                    "Last 7 Day is not presemt in datepicker filter");
            test.log(LogStatus.PASS, "Verified 'Last 7 days' option in date picker filter.");
            Assert.assertTrue(calendarRanges.contains(DatePickerConstants.DatePicker.LAST_30_DAYS),
                    "Last 30 Days is not presemt in datepicker filter");
            test.log(LogStatus.PASS, "Verified 'Last 30 days' option in date picker filter.");
            Assert.assertTrue(calendarRanges.contains(DatePickerConstants.DatePicker.LAST_90_DAYS),
                    "Last 90 days is not presemt in datepicker filter");
            test.log(LogStatus.PASS, "Verified 'Last 90 days' option in date picker filter.");
            Assert.assertTrue(calendarRanges.contains(DatePickerConstants.DatePicker.LAST_MONTH),
                    "Last Month is not presemt in datepicker filter");
            test.log(LogStatus.PASS, "Verified 'Last Month' option in date picker filter.");
            Assert.assertTrue(calendarRanges.contains(DatePickerConstants.DatePicker.THIS_MONTH),
                    "This Month is not presemt in datepicker filter");
            test.log(LogStatus.PASS, "Verified 'This Month' option in date picker filter.");
            Assert.assertTrue(calendarRanges.contains(DatePickerConstants.DatePicker.CUSTOM_RANGE),
                    "Custom Range is not presemt in datepicker filter");
            test.log(LogStatus.PASS, "Verified 'Custom Range' option in date picker filter.");
        } catch (NoSuchElementException ex) {
            loggingUtils.error("No Data picker present by this name", test);
            loggingUtils.error("Error- " + ex, test);
        }
    }
}