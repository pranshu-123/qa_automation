package com.qa.testcases.reports.scheduled;

import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.pagefactory.SubTopPanelModulePageObject;
import com.qa.pagefactory.reports.ReportsArchiveScheduledPageObject;
import com.qa.scripts.reports.ReportsArchiveSchedulePage;
import com.qa.utils.Log;
import com.qa.utils.MouseActions;
import com.qa.utils.WaitExecuter;
import com.relevantcodes.extentreports.LogStatus;
import org.testng.annotations.Test;

import java.util.logging.Logger;

@Marker.ReportsScheduled
@Marker.All
public class TC_RS01 extends BaseClass {
  /**
   * Verify Scheduled Reports page
   */

  private static final Logger LOGGER = Logger.getLogger(com.qa.testcases.reports.scheduled.TC_RS01.class.getName());

  @Test(dataProvider = "clusterid-data-provider")
  public void TC_RS01_verifyScheduledReportsPage(String clusterId) {
    test = extent.startTest("TC_RS01_verifyScheduledReportsPage: " + clusterId,
        "Verify Scheduled Reports page");
    test.assignCategory(" Schedule ");
    Log.startTestCase("TC_RS01_verifyScheduledReportsPage");

    // Initialize all classes objects
    test.log(LogStatus.INFO, "Initialize all class objects");
    LOGGER.info("Initialize all class objects");
    SubTopPanelModulePageObject topPanelComponentPageObject = new SubTopPanelModulePageObject(driver);
    ReportsArchiveSchedulePage reportsPage = new ReportsArchiveSchedulePage(driver);
    ReportsArchiveScheduledPageObject reportPageObj = new ReportsArchiveScheduledPageObject(driver);
    WaitExecuter waitExecuter = new WaitExecuter(driver);

    // Navigate to Reports tab from header
    test.log(LogStatus.INFO, "Verify Scheduled Reports page");
    MouseActions.clickOnElement(driver, topPanelComponentPageObject.reports);
    waitExecuter.waitUntilElementClickable(reportPageObj.scheduledPage);
    MouseActions.clickOnElement(driver, reportPageObj.scheduledPage);
    reportsPage.validateScheduleReportPage(reportPageObj);
    test.log(LogStatus.PASS, "Validated Scheduled Reports page successfully");
  }
}