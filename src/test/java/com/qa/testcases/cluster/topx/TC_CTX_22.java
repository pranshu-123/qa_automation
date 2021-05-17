package com.qa.testcases.cluster.topx;

import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.constants.PageConstants;
import com.qa.enums.DatePickerOptions;
import com.qa.pagefactory.DatePickerPageObject;
import com.qa.pagefactory.SubTopPanelModulePageObject;
import com.qa.pagefactory.TopPanelPageObject;
import com.qa.pagefactory.clusters.TopXPageObject;
import com.qa.pagefactory.reports.ReportsArchiveScheduledPageObject;
import com.qa.scripts.DatePicker;
import com.qa.scripts.HomePage;
import com.qa.scripts.clusters.TopX;
import com.qa.scripts.reports.ReportsArchiveSchedulePage;
import com.qa.utils.LoggingUtils;
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

  LoggingUtils LOGGER = new LoggingUtils(TC_CTX_22.class);

  @Test(dataProvider = "clusterid-data-provider")
  public void verifyTopXWithDifferentDateRange(String clusterId) {
    test = extent.startTest("TC_CTX_22.verifyTopXWithDifferentDateRange",
        "Verify TopX report is generated for different date range");
    test.assignCategory(" Cluster - Top X");
    WaitExecuter waitExecuter = new WaitExecuter(driver);
    TopX topX = new TopX(driver);
    TopXPageObject topXPageObject = new TopXPageObject(driver);

    SubTopPanelModulePageObject topPanelComponentPageObject = new SubTopPanelModulePageObject(driver);
    MouseActions.clickOnElement(driver, topPanelComponentPageObject.reports);
    waitExecuter.sleep(3000);
    ReportsArchiveSchedulePage reportsPage = new ReportsArchiveSchedulePage(driver);
    ReportsArchiveScheduledPageObject reportPageObj = new ReportsArchiveScheduledPageObject(driver);
    String statusXpath = reportsPage.clickOnReportName(reportPageObj, PageConstants.ReportsArchiveNames.TopX);

    DatePicker datePicker = new DatePicker(driver);
    DatePickerOptions[] dateOptions = {DatePickerOptions.LAST_7_DAYS, DatePickerOptions.LAST_30_DAYS,
        DatePickerOptions.LAST_60_DAYS, DatePickerOptions.LAST_90_DAYS, DatePickerOptions.CUSTOM_RANGE};
    datePicker.clickOnDatePickerForTopX();

    for (DatePickerOptions dateRange : dateOptions) {
      LOGGER.info("Click on + button", test);
      reportsPage.clickOnReportName(reportPageObj, PageConstants.ReportsArchiveNames.TopX);
      String topXValue = "10";
      topX.setTopXNumber(topXValue);
      datePicker.clickOnDatePickerForTopX();
      datePicker.selectDateOptionFromDate(dateRange);
      topX.clickOnModalRunButton();
      waitExecuter.waitUntilElementPresent(topXPageObject.archivesHeader);
      waitExecuter.waitUntilPageFullyLoaded();
      waitExecuter.waitUntilTextNotToBeInWebElement(topXPageObject.modalAfterRunButton, "Please Wait");

      Boolean isSuccessFullyStarted = topX.checkIfReportSuccessfullyStarted();
      if (!isSuccessFullyStarted) {
        Assert.fail("Report is not started successfully.");
      }
      LOGGER.info("Report is started successfully for daterange: " + dateRange, test);

      WebElement statusElement = driver.findElement(By.xpath(statusXpath));
      try {
        waitExecuter.waitUntilElementPresent(statusElement);
        waitExecuter.waitUntilTextToBeInWebElement(statusElement,
            "SUCCESS");
        LOGGER.pass("Verified TopX report is completed successfully", test);
        waitExecuter.waitUntilElementClickable(topXPageObject.topXAddIcon);
      } catch (TimeoutException te) {
        throw new AssertionError("TopX report not completed successfully.");
      }
    }
  }
}
