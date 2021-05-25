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
public class TC_RS07 extends BaseClass {
  /**
   * Verify More info option from Action tab
   */

  private static final Logger LOGGER = Logger.getLogger(TC_RS07.class.getName());

  @Test(dataProvider = "clusterid-data-provider",description = "P0-Verify that the  More info option from Action tab,this should pop up an window with all the details (parameters) of the report")
  public void TC_RS07_verifyScheduledReportsMoreInfoAction(String clusterId) {
    test = extent.startTest("TC_RS07_verifyScheduledReportsMoreInfoAction: " + clusterId,
        "Verify More info option from Action tab,this should pop an window with all the details " +
            "(parameters) of the report");
    test.assignCategory(" Schedule ");
    Log.startTestCase("TC_RS07_verifyScheduledReportsMoreInfoAction");

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
    userActions.performActionWithPolling(reportPageObj.scheduledPage, UserAction.CLICK);
    waitExecuter.sleep(1000);
    reportsPage.validateScheduledReportMoreInfoAction(reportPageObj);
    test.log(LogStatus.PASS, "Verified More info option from Action tab successfully");
  }
}

