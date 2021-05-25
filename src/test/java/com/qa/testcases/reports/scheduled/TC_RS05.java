package com.qa.testcases.reports.scheduled;

import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.enums.UserAction;
import com.qa.pagefactory.SubTopPanelModulePageObject;
import com.qa.pagefactory.reports.ReportsArchiveScheduledPageObject;
import com.qa.scripts.reports.ReportsArchiveSchedulePage;
import com.qa.utils.Log;
import com.qa.utils.MouseActions;
import com.qa.utils.WaitExecuter;
import com.qa.utils.actions.UserActions;
import com.relevantcodes.extentreports.LogStatus;
import org.testng.annotations.Test;

import java.util.logging.Logger;

@Marker.ReportsScheduled
@Marker.All
public class TC_RS05 extends BaseClass {
  /**
   * Verify Edit option from Action tab
   */

  private static final Logger LOGGER = Logger.getLogger(TC_RS05.class.getName());

  @Test(dataProvider = "clusterid-data-provider",description = "Verify Edit option from Action tab, this will open " +
          "schedule Report page can edit the parameters of the report")
  public void TC_RS05_verifyScheduledReportsEditAction(String clusterId) {
    test = extent.startTest("TC_RS05_verifyScheduledReportsEditAction: " + clusterId,
        "Verify Edit option from Action tab, this will open Schedule Report page from where we " +
            "can edit the parameters of the report");
    test.assignCategory(" Schedule ");
    Log.startTestCase("TC_RS05_verifyScheduledReportsEditAction");

    // Initialize all classes objects
    test.log(LogStatus.INFO, "Initialize all class objects");
    LOGGER.info("Initialize all class objects");
    SubTopPanelModulePageObject topPanelComponentPageObject = new SubTopPanelModulePageObject(driver);
    ReportsArchiveSchedulePage reportsPage = new ReportsArchiveSchedulePage(driver);
    ReportsArchiveScheduledPageObject reportPageObj = new ReportsArchiveScheduledPageObject(driver);
    WaitExecuter waitExecuter = new WaitExecuter(driver);
    UserActions userActions = new UserActions(driver);

    // Navigate to Reports tab from header
    test.log(LogStatus.INFO, "Verify Scheduled Reports page");
    userActions.performActionWithPolling(topPanelComponentPageObject.reports, UserAction.CLICK);
    waitExecuter.waitUntilElementClickable(reportPageObj.scheduledPage);
    waitExecuter.sleep(1000);
    userActions.performActionWithPolling(reportPageObj.scheduledPage, UserAction.CLICK);
    waitExecuter.sleep(1000);
    reportsPage.validateScheduledReportEditAction(reportPageObj);
    test.log(LogStatus.PASS, "Verified Edit option from Action tab successfully");
  }
}
