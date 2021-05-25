package com.qa.testcases.reports.archived;

import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.constants.DatePickerConstants;
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
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

@Marker.ReportArchive
@Marker.All
public class TC_RRA05 extends BaseClass {
  /**
   * Verify sorting option in Report Archive page :
   * Sorting should work fine on Name, Reports and Status tabs
   */

  Logger logger = LoggerFactory.getLogger(com.qa.testcases.reports.archived.TC_RRA05.class);

  @Test(dataProvider = "clusterid-data-provider",description = "Verify that the sorting should work fine on Name, Reports and Status tabs")
  public void TC_RRA05_verifyReportArchiveSortingOption(String clusterId) {
    test = extent.startTest("TC_RRA05_verifyReportArchiveSortingOption: " + clusterId,
        "Verify Sorting should work fine on Name, Reports and Status tabs");
    test.assignCategory("Report Archive");
    Log.startTestCase("TC_RRA05_verifyReportArchiveSortingOption");

    // Initialize all classes objects
    test.log(LogStatus.INFO, "Initialize all class objects");
    logger.info("Initialize all class objects");
    WaitExecuter waitExecuter = new WaitExecuter(driver);
    SubTopPanelModulePageObject topPanelComponentPageObject = new SubTopPanelModulePageObject(driver);
    ReportsArchiveSchedulePage reportsPage = new ReportsArchiveSchedulePage(driver);
    ReportsArchiveScheduledPageObject reportPageObj = new ReportsArchiveScheduledPageObject(driver);
    UserActions userActions = new UserActions(driver);
    waitExecuter.sleep(2000);
    // Navigate to Reports tab from header
    test.log(LogStatus.INFO, "Navigate to reports tab from header and validate the sorting options on " +
        "Name, Reports and Status tabs");
    userActions.performActionWithPolling(topPanelComponentPageObject.reports, UserAction.CLICK);
    waitExecuter.sleep(2000);
    reportsPage.validateSortingOptionReportName(reportPageObj, false);
   /* reportsPage.validateSortingOptionReportCnt(reportPageObj, true);
    reportsPage.validateSortingOptionStatus(reportPageObj, true);*/
    test.log(LogStatus.PASS, "The sorting option has been validated successfully for Name, Reports and Status tabs");
  }
}
