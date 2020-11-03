package com.qa.testcases.reports;

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

public class TC_RRA08 extends BaseClass {
  /**
   * Verify search option in Report Archive page :
   * should list all the reports which matches with search box
   */

  Logger logger = LoggerFactory.getLogger(com.qa.testcases.reports.TC_RRA08.class);

  @Test(dataProvider = "clusterid-data-provider")
  public void TC_RRA08_verifyReportArchiveSearchOption(String clusterId) {
    test = extent.startTest("TC_RRA08_verifyReportArchiveSearchOption: " + clusterId,
        "Verify search option perReport");
    test.assignCategory(" Report Archive");
    Log.startTestCase("TC_RRA08_verifyReportArchiveSearchOption");

    // Initialize all classes objects
    test.log(LogStatus.INFO, "Initialize all class objects");
    logger.info("Initialize all class objects");
    TopPanelComponentPageObject topPanelComponentPageObject = new TopPanelComponentPageObject(driver);
    ReportsArchiveSchedulePage reportsPage = new ReportsArchiveSchedulePage(driver);
    ReportsArchiveScheduledPageObject reportPageObj = new ReportsArchiveScheduledPageObject(driver);

    // Navigate to Reports tab from header
    test.log(LogStatus.INFO, "Navigate to reports tab from header and Verify search option perReport");
    MouseActions.clickOnElement(driver, topPanelComponentPageObject.reports);
    reportsPage.validateSearchOption(reportPageObj);
    test.log(LogStatus.PASS, "The search option has been validated successfully");
  }
}
