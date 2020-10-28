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

public class TC_RRA06 extends BaseClass {
  /**
   * Verify reports tab in Report Archive page :
   * This will open new page with  all the reports list, count and list should match here
   */

  Logger logger = LoggerFactory.getLogger(com.qa.testcases.reports.TC_RRA06.class);

  @Test(dataProvider = "clusterid-data-provider")
  public void TC_RRA05_verifyReportArchiveReportTab(String clusterId) {
    test = extent.startTest("TC_RRA05_verifyReportArchiveReportTab: " + clusterId,
        "Verify all the spark apps are listed in the UI");
    test.assignCategory(" Apps Details-Spark");
    Log.startTestCase("TC_RRA05_verifyReportArchiveReportTab");

    // Initialize all classes objects
    test.log(LogStatus.INFO, "Initialize all class objects");
    logger.info("Initialize all class objects");
    TopPanelComponentPageObject topPanelComponentPageObject = new TopPanelComponentPageObject(driver);
    ReportsArchiveSchedulePage reportsPage = new ReportsArchiveSchedulePage(driver);
    ReportsArchiveScheduledPageObject reportPageObj = new ReportsArchiveScheduledPageObject(driver);

    // Navigate to Reports tab from header
    test.log(LogStatus.INFO, "Navigate to reports tab from header and validate the Report tab");
    MouseActions.clickOnElement(driver, topPanelComponentPageObject.reports);

    test.log(LogStatus.PASS, "Validated the report tab successfully");
  }
}
