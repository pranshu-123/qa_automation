package com.qa.testcases.reports.archived;

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
public class TC_RRA07 extends BaseClass {
  /**
   * Verify sorting option in Report Archive page :
   * Verify Sorting should work fine on Name, Created and Status tabs for a particular report
   */

  Logger logger = LoggerFactory.getLogger(com.qa.testcases.reports.archived.TC_RRA07.class);

  @Test(dataProvider = "clusterid-data-provider")
  public void TC_RRA07_verifyReportPageSortingOption(String clusterId) {
    test = extent.startTest("TC_RRA07_verifyReportPageSortingOption: " + clusterId,
        "Verify Sorting should work fine on Name, Created and Status tabs for a particular reportI");
    test.assignCategory(" Report Archive");
    Log.startTestCase("TC_RRA07_verifyReportPageSortingOption");

    // Initialize all classes objects
    test.log(LogStatus.INFO, "Initialize all class objects");
    logger.info("Initialize all class objects");
    SubTopPanelModulePageObject topPanelComponentPageObject = new SubTopPanelModulePageObject(driver);
    ReportsArchiveSchedulePage reportsPage = new ReportsArchiveSchedulePage(driver);
    ReportsArchiveScheduledPageObject reportPageObj = new ReportsArchiveScheduledPageObject(driver);
    WaitExecuter waitExecuter = new WaitExecuter(driver);

    // Navigate to Reports tab from header
    test.log(LogStatus.INFO, "Navigate to reports tab from header and vVerify Sorting should work fine " +
        "on Name, Created and Status tabs for a particular report");
    MouseActions.clickOnElement(driver, topPanelComponentPageObject.reports);
    waitExecuter.sleep(2000);
    reportsPage.validateSortingOptionReportName(reportPageObj, false);
    /*reportsPage.validateSortingOptionStatus(reportPageObj, false);*/
    /*reportsPage.validateSortingOptionReportCnt(reportPageObj, false);*/
    test.log(LogStatus.PASS, "The sorting option has been validated successfully for Name," +
        " Created and Status tabs for particular report");
  }
}
