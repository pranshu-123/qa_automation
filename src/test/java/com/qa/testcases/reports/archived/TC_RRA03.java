package com.qa.testcases.reports.archived;

import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.enums.UserAction;
import com.qa.pagefactory.SubTopPanelModulePageObject;
import com.qa.pagefactory.reports.ReportsArchiveScheduledPageObject;
import com.qa.scripts.DatePicker;
import com.qa.scripts.jobs.applications.AllApps;
import com.qa.scripts.reports.ReportsArchiveSchedulePage;
import com.qa.utils.Log;
import com.qa.utils.MouseActions;
import com.qa.utils.WaitExecuter;
import com.qa.utils.actions.UserActions;
import com.relevantcodes.extentreports.LogStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

@Marker.ReportArchive
@Marker.All
public class TC_RRA03 extends BaseClass {
  /**
   * Verify report is generated for different date ranges
   * Verify datepicker list and Popup should list all the combination of daterange
   * This should list all the reports with latest report status (SUCCESS, FAILURE, NO REPORT)
   * between selected date range
   */

  Logger logger = LoggerFactory.getLogger(com.qa.testcases.reports.archived.TC_RRA03.class);

  @Test(dataProvider = "clusterid-data-provider",description = "P0-Verify that the date picker list all the reports with latest report status should show(SUCCESS, FAILURE, NO REPORT)")
  public void TC_RRA03_verifyReportsForDiffDateRange(String clusterId) {
    test = extent.startTest("TC_RRA03_verifyReportsForDiffDateRange: " + clusterId,
        "Verify datepicker list and Popup should list all the combination of daterange\n" +
            "This should list all the reports with latest report status (SUCCESS, FAILURE, NO REPORT)");
    test.assignCategory(" Report Archive");
    Log.startTestCase("TC_RRA03_verifyReportsForDiffDateRange");

    // Initialize all classes objects
    test.log(LogStatus.INFO, "Initialize all class objects");
    logger.info("Initialize all class objects");
    SubTopPanelModulePageObject topPanelComponentPageObject = new SubTopPanelModulePageObject(driver);
    ReportsArchiveSchedulePage reportsPage = new ReportsArchiveSchedulePage(driver);
    ReportsArchiveScheduledPageObject reportPageObj = new ReportsArchiveScheduledPageObject(driver);
    DatePicker datePicker = new DatePicker(driver);
    UserActions userActions = new UserActions(driver);
    WaitExecuter waitExecuter = new WaitExecuter(driver);

    // Navigate to Reports tab from header
    test.log(LogStatus.INFO, "Navigate to reports tab from header ");
    userActions.performActionWithPolling(topPanelComponentPageObject.reports, UserAction.CLICK);
    waitExecuter.waitUntilPageFullyLoaded();
    waitExecuter.sleep(2000);

    // Click on date picker and get list of calendar ranges
    test.log(LogStatus.INFO, "Click on date picker and list of calendar ranges");
    logger.info("Click on date picker and list of calendar ranges");


    // Click on combination of daterange
    test.log(LogStatus.INFO, "Verify reports with status between last one hour");
    datePicker.clickOnDatePicker();
    waitExecuter.waitUntilPageFullyLoaded();
    waitExecuter.sleep(2000);
    datePicker.selectLastOneHour();
    waitExecuter.sleep(4000);
    reportsPage.validateReportNames(reportPageObj);
    reportsPage.validateReportStatus(reportPageObj);
    test.log(LogStatus.PASS, "Verified  reports with status between last one hour successfully");

    //select 'Last 2 Hour'
    test.log(LogStatus.INFO, "Verify reports with status between last two hours");
    datePicker.clickOnDatePicker();
    waitExecuter.waitUntilPageFullyLoaded();
    waitExecuter.sleep(1000);
    datePicker.selectLastTwoHour();
    waitExecuter.waitUntilPageFullyLoaded();
    waitExecuter.sleep(2000);
    reportsPage.validateReportNames(reportPageObj);
    reportsPage.validateReportStatus(reportPageObj);
    test.log(LogStatus.PASS, "Verified  reports with status between last two hours successfully");

    test.log(LogStatus.INFO, "Verify reports with status between last 6 hours");
    datePicker.clickOnDatePicker();
    waitExecuter.waitUntilPageFullyLoaded();
    datePicker.selectLastSixHour();
    waitExecuter.waitUntilPageFullyLoaded();
    waitExecuter.sleep(2000);
    reportsPage.validateReportNames(reportPageObj);
    reportsPage.validateReportStatus(reportPageObj);
    waitExecuter.sleep(1000);
    test.log(LogStatus.PASS, "Verified  reports with status between last 6 hours successfully");

    test.log(LogStatus.INFO, "Verify reports with status between last 12 hours");
    datePicker.clickOnDatePicker();
    waitExecuter.waitUntilPageFullyLoaded();
    datePicker.selectLast12Hour();
    waitExecuter.waitUntilPageFullyLoaded();
    waitExecuter.sleep(3000);
    reportsPage.validateReportNames(reportPageObj);
    reportsPage.validateReportStatus(reportPageObj);
    test.log(LogStatus.PASS, "Verified  reports with status between last 12 hours successfully");

    test.log(LogStatus.INFO, "Verify reports with status between today");
    datePicker.clickOnDatePicker();
    waitExecuter.waitUntilPageFullyLoaded();
    waitExecuter.sleep(2000);
    datePicker.selectToday();
    waitExecuter.waitUntilPageFullyLoaded();
    waitExecuter.sleep(3000);
    reportsPage.validateReportNames(reportPageObj);
    reportsPage.validateReportStatus(reportPageObj);
    test.log(LogStatus.PASS, "Verified  reports with status between today successfully");

    test.log(LogStatus.INFO, "Verify reports with status between yesterday");
    datePicker.clickOnDatePicker();
    waitExecuter.waitUntilPageFullyLoaded();
    datePicker.selectYesterday();
    waitExecuter.sleep(2000);
    waitExecuter.waitUntilPageFullyLoaded();
    waitExecuter.sleep(2000);
    reportsPage.validateReportNames(reportPageObj);
    reportsPage.validateReportStatus(reportPageObj);
    test.log(LogStatus.PASS, "Verified  reports with status between yesterday successfully");

    test.log(LogStatus.INFO, "Verify reports with status between last 7 days");
    datePicker.clickOnDatePicker();
    waitExecuter.waitUntilPageFullyLoaded();
    waitExecuter.sleep(2000);
    datePicker.selectLast7Days();
    waitExecuter.waitUntilPageFullyLoaded();
    waitExecuter.sleep(2000);
    reportsPage.validateReportNames(reportPageObj);
    reportsPage.validateReportStatus(reportPageObj);
    test.log(LogStatus.PASS, "Verified  reports with status between last 7 days successfully");

    test.log(LogStatus.INFO, "Verify reports with status between last 30 days");
    datePicker.clickOnDatePicker();
    waitExecuter.waitUntilPageFullyLoaded();
    datePicker.selectLast30Days();
    waitExecuter.waitUntilPageFullyLoaded();
    waitExecuter.sleep(2000);
    reportsPage.validateReportNames(reportPageObj);
    reportsPage.validateReportStatus(reportPageObj);
    test.log(LogStatus.PASS, "Verified  reports with status between last 30 days successfully");

    test.log(LogStatus.INFO, "Verify reports with status between last 90 days");
    datePicker.clickOnDatePicker();
    waitExecuter.waitUntilPageFullyLoaded();
    waitExecuter.sleep(1000);
    datePicker.selectLast90Days();
    waitExecuter.waitUntilPageFullyLoaded();
    waitExecuter.sleep(2000);
    reportsPage.validateReportNames(reportPageObj);
    reportsPage.validateReportStatus(reportPageObj);
    test.log(LogStatus.PASS, "Verified  reports with status between last 90 days successfully");

    test.log(LogStatus.INFO, "Verify reports with status between this month ");
    datePicker.clickOnDatePicker();
    waitExecuter.waitUntilPageFullyLoaded();
    waitExecuter.sleep(1000);
    datePicker.selectThisMonth();
    waitExecuter.waitUntilPageFullyLoaded();
    waitExecuter.sleep(2000);
    reportsPage.validateReportNames(reportPageObj);
    reportsPage.validateReportStatus(reportPageObj);
    test.log(LogStatus.PASS, "Verified  reports with status between this month successfully");

    test.log(LogStatus.INFO, "Verify reports with status between last month ");
    datePicker.clickOnDatePicker();
    waitExecuter.waitUntilPageFullyLoaded();
    waitExecuter.sleep(1000);
    datePicker.selectLastMonth();
    waitExecuter.waitUntilPageFullyLoaded();
    waitExecuter.sleep(3000);
    reportsPage.validateReportNames(reportPageObj);
    reportsPage.validateReportStatus(reportPageObj);
    test.log(LogStatus.PASS, "Verified  reports with status between last month successfully");
  }
}
