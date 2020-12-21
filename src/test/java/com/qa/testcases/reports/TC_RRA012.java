package com.qa.testcases.reports;

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

public class TC_RRA012 extends BaseClass {
  Logger logger = LoggerFactory.getLogger(com.qa.testcases.reports.TC_RRA011.class);

  @Test(dataProvider = "clusterid-data-provider")
  public void verifyScheduleReportOption(String clusterId) {
    test = extent.startTest("verifyScheduleReportOption: " + clusterId,
        "Verify schedule report option from actions tab");
    test.assignCategory(" Report Archive");
    Log.startTestCase("verifyScheduleReportOption");

    // Initialize all classes objects
    test.log(LogStatus.INFO, "Initialize all class objects");
    logger.info("Initialize all class objects");
    SubTopPanelModulePageObject topPanelComponentPageObject = new SubTopPanelModulePageObject(driver);
    ReportsArchiveSchedulePage reportsPage = new ReportsArchiveSchedulePage(driver);
    ReportsArchiveScheduledPageObject reportPageObj = new ReportsArchiveScheduledPageObject(driver);
    WaitExecuter waitExecuter = new WaitExecuter(driver);

    // Navigate to Reports tab from header
    test.log(LogStatus.INFO, "Navigate to reports tab from header and Verify new report option from actions tab");
    MouseActions.clickOnElement(driver, topPanelComponentPageObject.reports);
    waitExecuter.waitUntilPageFullyLoaded();
    reportsPage.validateScheduleReportOption(reportPageObj);
    test.log(LogStatus.PASS, "Verified schedule report option from actions tab successfully");
  }
}