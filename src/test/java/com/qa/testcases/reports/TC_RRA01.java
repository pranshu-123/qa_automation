package com.qa.testcases.reports;

import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.pagefactory.TopPanelComponentPageObject;
import com.qa.pagefactory.reports.ReportsArchiveScheduledPageObject;
import com.qa.scripts.reports.ReportsArchiveSchedulePage;
import com.qa.utils.Log;
import com.qa.utils.MouseActions;
import com.relevantcodes.extentreports.LogStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;


public class TC_RRA01 extends BaseClass {
  /**
   * Verify that after navigating to the reports page:
   * With default parameters, all the reports with latest report status should be displayed
   */

  Logger logger = LoggerFactory.getLogger(com.qa.testcases.reports.TC_RRA01.class);

  @Test(dataProvider = "clusterid-data-provider")
  public void TC_RRA01_verifyReportArchivePage(String clusterId) {
    test = extent.startTest("TC_RRA01_verifyReportArchivePage: " + clusterId,
        "Verify the reports with report status are present");
    test.assignCategory(" Report Archive");
    Log.startTestCase("TC_RRA01_verifyReportArchivePage");

    // Initialize all classes objects
    test.log(LogStatus.INFO, "Initialize all class objects");
    logger.info("Initialize all class objects");
    TopPanelComponentPageObject topPanelComponentPageObject = new TopPanelComponentPageObject(driver);
    ReportsArchiveSchedulePage reportsPage = new ReportsArchiveSchedulePage(driver);
    ReportsArchiveScheduledPageObject reportPageObj = new ReportsArchiveScheduledPageObject(driver);

    // Navigate to Reports tab from header
    test.log(LogStatus.INFO, "Navigate to reports tab from header and validate the reports with report status are present");
    MouseActions.clickOnElement(driver, topPanelComponentPageObject.reports);
    reportsPage.validateReportNames(reportPageObj);
    reportsPage.validateReportStatus(reportPageObj);
    test.log(LogStatus.PASS, "Validated that all the reports with report status are present ");
  }
}
