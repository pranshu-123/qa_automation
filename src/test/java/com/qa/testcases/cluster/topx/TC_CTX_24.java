package com.qa.testcases.cluster.topx;

import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.constants.PageConstants;
import com.qa.pagefactory.SubTopPanelModulePageObject;
import com.qa.pagefactory.reports.ReportsArchiveScheduledPageObject;
import com.qa.scripts.HomePage;
import com.qa.scripts.clusters.TopX;
import com.qa.scripts.reports.ReportsArchiveSchedulePage;
import com.qa.utils.MouseActions;
import com.qa.utils.WaitExecuter;
import com.relevantcodes.extentreports.LogStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * @author Ankur Jaiswal
 */
@Marker.All
@Marker.TopX
public class TC_CTX_24 extends BaseClass {

  Logger logger = LoggerFactory.getLogger(TC_CTX_24.class);
  @Test()
  public void verifyClusterFilter() {
    test = extent.startTest("TC_CTX_24.verifyClusterFilter",
      "Verify cluster filter in new report page.");
    test.assignCategory(" Cluster - Top X");
    WaitExecuter waitExecuter = new WaitExecuter(driver);

    SubTopPanelModulePageObject topPanelComponentPageObject = new SubTopPanelModulePageObject(driver);
    MouseActions.clickOnElement(driver, topPanelComponentPageObject.reports);
    waitExecuter.sleep(3000);
    ReportsArchiveSchedulePage reportsPage = new ReportsArchiveSchedulePage(driver);
    ReportsArchiveScheduledPageObject reportPageObj = new ReportsArchiveScheduledPageObject(driver);
    reportsPage.clickOnReportName(reportPageObj, PageConstants.ReportsArchiveNames.TopX);
    logger.info("Click on + button", test);

    TopX topX = new TopX(driver);

    HomePage homePage = new HomePage(driver);
    homePage.clickOnClusterDropDown();

    Assert.assertTrue(topX.getClustersList().size() > 0, "No cluster is displayed.");
    test.log(LogStatus.PASS, "Cluster is displayed in dropdown.");
  }
}
