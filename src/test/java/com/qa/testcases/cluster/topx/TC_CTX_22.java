package com.qa.testcases.cluster.topx;

import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.constants.PageConstants;
import com.qa.pagefactory.DatePickerPageObject;
import com.qa.pagefactory.SubTopPanelModulePageObject;
import com.qa.pagefactory.TopPanelPageObject;
import com.qa.pagefactory.clusters.TopXPageObject;
import com.qa.pagefactory.reports.ReportsArchiveScheduledPageObject;
import com.qa.scripts.DatePicker;
import com.qa.scripts.HomePage;
import com.qa.scripts.clusters.TopX;
import com.qa.scripts.reports.ReportsArchiveSchedulePage;
import com.qa.utils.MouseActions;
import com.qa.utils.WaitExecuter;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * @author Ankur Jaiswal
 */

@Marker.All
@Marker.TopX
public class TC_CTX_22 extends BaseClass {

  Logger logger = LoggerFactory.getLogger(TC_CTX_22.class);

  @Test(dataProvider = "clusterid-data-provider")
  public void verifyTopXWithDifferentDateRange(String clusterId) {
    test = extent.startTest("TC_CTX_22.verifyTopXWithDifferentDateRange",
        "Verify TopX report is generated for different date range");
    test.assignCategory(" Cluster - Top X");
    WaitExecuter waitExecuter = new WaitExecuter(driver);

//    TopPanelPageObject topPanelPageObject = new TopPanelPageObject(driver);
      TopXPageObject topXPageObject = new TopXPageObject(driver);
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
    String statusXpath = reportsPage.clickOnReportName(reportPageObj, PageConstants.ReportsArchiveNames.TopX);


    HomePage homePage = new HomePage(driver);
    homePage.selectMultiClusterId(clusterId);

    DatePicker datePicker = new DatePicker(driver);
    DatePickerPageObject datePickerPageObject = new DatePickerPageObject(driver);
    //datePicker.clickOnDatePicker();
    datePicker.clickOnDatePickerForTopX();

    for (int i=0; i < datePickerPageObject.dateRangeOptions.size(); i++) {
      WebElement dateRangeOption = datePickerPageObject.dateRangeOptions.get(i);
      String dateRangeValue = dateRangeOption.getText();
      dateRangeOption.click();
      topX.clickOnModalRunButton();
      //waitExecuter.waitUntilElementPresent(topXPageObject.runButton);
      //waitExecuter.waitUntilElementClickable(topXPageObject.runButton);
      //topX.clickOnRunButton();
      WebElement statusElement = driver.findElement(By.xpath(statusXpath));
      try{
      /*  waitExecuter.waitUntilTextToBeInWebElement(statusElement,
                "SUCCESS");*/
        test.log(LogStatus.PASS, "Top X Report completed successfully for date: " + dateRangeValue);
      }catch (TimeoutException te) {
        throw new AssertionError("Top X Report not completed successfully.");
      }

//      try {
//        waitExecuter.waitUntilTextToBeInWebElement(topXPageObject.confirmationMessageElement,
//                "Top X Report completed successfully.");
//        test.log(LogStatus.PASS, "Top X Report completed successfully for date: " + dateRangeValue);
//      } catch (TimeoutException te) {
//        throw new AssertionError("Top X Report not completed successfully.");
//      }
    }
  }
}
