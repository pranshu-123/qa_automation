package com.qa.testcases.reports;

import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.constants.DatePickerConstants;
import com.qa.pagefactory.TopPanelComponentPageObject;
import com.qa.pagefactory.reports.ReportsArchiveScheduledPageObject;
import com.qa.scripts.DatePicker;
import com.qa.scripts.jobs.applications.AllApps;
import com.qa.scripts.reports.ReportsArchiveSchedulePage;
import com.qa.utils.Log;
import com.qa.utils.MouseActions;
import com.qa.utils.WaitExecuter;
import com.relevantcodes.extentreports.LogStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class TC_RRA02 extends BaseClass {
  /**
   * Verify that after navigating to the reports page:
   * Verify datepicker list and Popup should list all the combination of daterange
   */

  Logger logger = LoggerFactory.getLogger(com.qa.testcases.reports.TC_RRA02.class);

  @Test(dataProvider = "clusterid-data-provider")
  public void TC_RRA02_verifyReportArchiveDatePickerList(String clusterId) {
    test = extent.startTest("TC_RRA02_verifyReportArchiveDatePickerList: " + clusterId,
        "validate the datepicker list");
    test.assignCategory(" Report Archive");
    Log.startTestCase("TC_RRA02_verifyReportArchiveDatePickerList");

    // Initialize all classes objects
    test.log(LogStatus.INFO, "Initialize all class objects");
    logger.info("Initialize all class objects");
    TopPanelComponentPageObject topPanelComponentPageObject = new TopPanelComponentPageObject(driver);
    ReportsArchiveSchedulePage reportsPage = new ReportsArchiveSchedulePage(driver);
    ReportsArchiveScheduledPageObject reportPageObj = new ReportsArchiveScheduledPageObject(driver);
    DatePicker datePicker = new DatePicker(driver);
    WaitExecuter waitExecuter = new WaitExecuter(driver);
    AllApps allApps = new AllApps(driver);

    // Navigate to Reports tab from header
    test.log(LogStatus.INFO, "Navigate to reports tab from header and validate the datepicker list");
    MouseActions.clickOnElement(driver, topPanelComponentPageObject.reports);

    // Click on date picker and get list of calendar ranges
    test.log(LogStatus.INFO, "Click on date picker and list of calendar ranges");
    logger.info("Click on date picker and list of calendar ranges");
    datePicker.clickOnDatePicker();
    waitExecuter.sleep(1000);
    List<String> calendarRanges = allApps.getCalendarRanges();
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
  }
}
