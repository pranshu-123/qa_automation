package com.qa.testcases.reports;

import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.pagefactory.SubTopPanelModulePageObject;
import com.qa.pagefactory.reports.ReportsArchiveScheduledPageObject;
import com.qa.scripts.reports.ReportsArchiveSchedulePage;
import com.qa.utils.Log;
import com.qa.utils.MouseActions;
import com.qa.utils.WaitExecuter;
import com.relevantcodes.extentreports.LogStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

@Marker.ReportArchive
@Marker.All
public class TC_RRA013 extends BaseClass {
  /**
   * Verify latest report on selected date range option from actions tab
   */

  Logger logger = LoggerFactory.getLogger(com.qa.testcases.reports.TC_RRA013.class);

  @Test(dataProvider = "clusterid-data-provider")
  public void TC_RRA013_verifyLatestReportOption(String clusterId) {
    test = extent.startTest("TC_RRA013_verifyLatestReportOption: " + clusterId,
        "Verify latest report on selected date range option from actions tab");
    test.assignCategory(" Report Archive");
    Log.startTestCase("TC_RRA013_verifyLatestReportOption");

    // Initialize all classes objects
    test.log(LogStatus.INFO, "Initialize all class objects");
    logger.info("Initialize all class objects");
    SubTopPanelModulePageObject topPanelComponentPageObject = new SubTopPanelModulePageObject(driver);
    ReportsArchiveSchedulePage reportsPage = new ReportsArchiveSchedulePage(driver);
    ReportsArchiveScheduledPageObject reportPageObj = new ReportsArchiveScheduledPageObject(driver);
    WaitExecuter waitExecuter = new WaitExecuter(driver);

    // Navigate to Reports tab from header
    test.log(LogStatus.INFO, "Navigate to reports tab from header and Verify latest report");
    MouseActions.clickOnElement(driver, topPanelComponentPageObject.reports);
    waitExecuter.sleep(2000);
    reportsPage.validateLatestReportAction(reportPageObj);
    test.log(LogStatus.PASS, "Verified latest report successfully");
  }
}
