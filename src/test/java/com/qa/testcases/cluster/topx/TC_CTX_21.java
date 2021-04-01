package com.qa.testcases.cluster.topx;

import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.constants.PageConstants;
import com.qa.pagefactory.SubTopPanelModulePageObject;
import com.qa.pagefactory.TopPanelPageObject;
import com.qa.pagefactory.reports.ReportsArchiveScheduledPageObject;
import com.qa.scripts.DatePicker;
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
public class TC_CTX_21 extends BaseClass {

  Logger logger = LoggerFactory.getLogger(TC_CTX_20.class);

  @Test(dataProvider = "clusterid-data-provider")
  public void verifyDatePicker(String clusterId) {
    test = extent.startTest("TC_CTX_21.verifyDatePicker", "Verify date picker in new report page");
    test.assignCategory(" Cluster - Top X");
    WaitExecuter waitExecuter = new WaitExecuter(driver);

//    TopPanelPageObject topPanelPageObject = new TopPanelPageObject(driver);
//    waitExecuter.waitUntilElementPresent(topPanelPageObject.topXTab);
//    waitExecuter.waitUntilPageFullyLoaded();
//    waitExecuter.waitUntilElementClickable(topPanelPageObject.topXTab);
//    waitExecuter.sleep(3000);
//    MouseActions.clickOnElement(driver, topPanelPageObject.topXTab);

    TopX topX = new TopX(driver);
//    topX.closeConfirmationMessageNotification();
//    topX.clickOnRunButton();

    SubTopPanelModulePageObject topPanelComponentPageObject = new SubTopPanelModulePageObject(driver);
    MouseActions.clickOnElement(driver, topPanelComponentPageObject.reports);
    waitExecuter.sleep(3000);
    ReportsArchiveSchedulePage reportsPage = new ReportsArchiveSchedulePage(driver);
    ReportsArchiveScheduledPageObject reportPageObj = new ReportsArchiveScheduledPageObject(driver);
    logger.info("Click on + button", test);
    reportsPage.clickOnReportName(reportPageObj, PageConstants.ReportsArchiveNames.TopX);

    DatePicker datePicker = new DatePicker(driver);
    datePicker.clickOnDatePicker();

    String[] expectedDateOptions = {"Current Day", "Last 7 Days", "Last 30 Days", "Last 60 Days",
      "Last 90 Days", "Custom Range"};

    for (String expectedDateOption : expectedDateOptions) {
      Assert.assertTrue(datePicker.getDatePickerOptions().contains(expectedDateOption),
        "Date list does not contain: " + expectedDateOption);
      test.log(LogStatus.PASS, "Date list contains option: " + expectedDateOption);
    }
    datePicker.clickOnDatePicker();
  }
}
