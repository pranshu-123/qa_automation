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
public class TC_RRA09 extends BaseClass {
  /**
   * Verify sorting option in Report Archive page :
   * Verify Select download, view report and remove options from actions for a particular report
   */

  Logger logger = LoggerFactory.getLogger(com.qa.testcases.reports.TC_RRA09.class);

  @Test(dataProvider = "clusterid-data-provider")
  public void TC_RRA09_verifyReportAction(String clusterId) {
    test = extent.startTest("TC_RRA09_verifyReportAction: " + clusterId,
        "Verify Select download, view report and remove options from actions for a particular report");
    test.assignCategory(" Report Archive");
    Log.startTestCase("TC_RRA09_verifyReportAction");

    // Initialize all classes objects
    test.log(LogStatus.INFO, "Initialize all class objects");
    logger.info("Initialize all class objects");
    SubTopPanelModulePageObject topPanelComponentPageObject = new SubTopPanelModulePageObject(driver);
    ReportsArchiveSchedulePage reportsPage = new ReportsArchiveSchedulePage(driver);
    ReportsArchiveScheduledPageObject reportPageObj = new ReportsArchiveScheduledPageObject(driver);
    WaitExecuter waitExecuter = new WaitExecuter(driver);

    // Navigate to Reports tab from header
    test.log(LogStatus.INFO, "Navigate to reports tab from header and  download, view report and remove options from actions");
    MouseActions.clickOnElement(driver, topPanelComponentPageObject.reports);
    waitExecuter.sleep(2000);
    reportsPage.validateReportActions(reportPageObj);
    test.log(LogStatus.PASS, "Verified  download, view report and remove options from actions successfully");
  }
}
