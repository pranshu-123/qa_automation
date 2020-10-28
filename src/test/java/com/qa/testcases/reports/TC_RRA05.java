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

public class TC_RRA05 extends BaseClass {
  /**
   * Verify sorting option in Report Archive page :
   * Sorting should work fine on Name, Reports and Status tabs
   */

  Logger logger = LoggerFactory.getLogger(com.qa.testcases.reports.TC_RRA05.class);

  @Test(dataProvider = "clusterid-data-provider")
  public void TC_RRA05_verifyReportArchiveSortingOption(String clusterId) {
    test = extent.startTest("TC_RRA05_verifyReportArchiveSortingOption: " + clusterId,
        "Verify all the spark apps are listed in the UI");
    test.assignCategory(" Apps Details-Spark");
    Log.startTestCase("TC_RRA05_verifyReportArchiveSortingOption");

    // Initialize all classes objects
    test.log(LogStatus.INFO, "Initialize all class objects");
    logger.info("Initialize all class objects");
    TopPanelComponentPageObject topPanelComponentPageObject = new TopPanelComponentPageObject(driver);
    ReportsArchiveSchedulePage reportsPage = new ReportsArchiveSchedulePage(driver);
    ReportsArchiveScheduledPageObject reportPageObj = new ReportsArchiveScheduledPageObject(driver);

    // Navigate to Reports tab from header
    test.log(LogStatus.INFO, "Navigate to reports tab from header and validate the sorting options on " +
        "Name, Reports and Status tabs");
    MouseActions.clickOnElement(driver, topPanelComponentPageObject.reports);
    reportsPage.validateSortingOptionReportName(reportPageObj);
    reportsPage.validateSortingOptionReportCnt(reportPageObj);
    reportsPage.validateSortingOptionStatus(reportPageObj);
    test.log(LogStatus.PASS, "The sorting option has been validated successfully for Name, Reports and Status tabs");
  }
}
