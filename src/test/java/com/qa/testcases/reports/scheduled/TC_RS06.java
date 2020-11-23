package com.qa.testcases.reports.scheduled;

import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.pagefactory.SubTopPanelModulePageObject;
import com.qa.pagefactory.reports.ReportsArchiveScheduledPageObject;
import com.qa.scripts.reports.ReportsArchiveSchedulePage;
import com.qa.utils.Log;
import com.qa.utils.MouseActions;
import com.qa.utils.WaitExecuter;
import com.relevantcodes.extentreports.LogStatus;
import org.testng.annotations.Test;

import java.util.logging.Logger;

@Marker.ReportsScheduled
@Marker.All
public class TC_RS06 extends BaseClass {
  /**
   * Verify Delete option from Action tab
   */

  private static final Logger LOGGER = Logger.getLogger(TC_RS06.class.getName());

  @Test(dataProvider = "clusterid-data-provider")
  public void TC_RS06_verifyScheduledReportsDeleteAction(String clusterId) {
    test = extent.startTest("TC_RS06_verifyScheduledReportsDeleteAction: " + clusterId,
        "Verify Delete option from Action tab, this should remove scheduled report successfully. " +
            " This should not delete reports already generated in Report Archives.");
    test.assignCategory(" Schedule ");
    Log.startTestCase("TC_RS06_verifyScheduledReportsDeleteAction");

    // Initialize all classes objects
    test.log(LogStatus.INFO, "Initialize all class objects");
    LOGGER.info("Initialize all class objects");
    SubTopPanelModulePageObject topPanelComponentPageObject = new SubTopPanelModulePageObject(driver);
    ReportsArchiveSchedulePage reportsPage = new ReportsArchiveSchedulePage(driver);
    ReportsArchiveScheduledPageObject reportPageObj = new ReportsArchiveScheduledPageObject(driver);
    WaitExecuter waitExecuter = new WaitExecuter(driver);

    // Navigate to Reports tab from header
    test.log(LogStatus.INFO, "Verify Scheduled Reports page");
    MouseActions.clickOnElement(driver, topPanelComponentPageObject.reports);
    waitExecuter.waitUntilElementClickable(reportPageObj.scheduledPage);
    MouseActions.clickOnElement(driver, reportPageObj.scheduledPage);
    waitExecuter.sleep(1000);
    reportsPage.validateScheduledReportDeleteAction(reportPageObj);
    test.log(LogStatus.PASS, "Verified Delete option from Action tab successfully");
  }
}
