package com.qa.testcases.cluster.topx;

import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.constants.PageConstants;
import com.qa.pagefactory.SubTopPanelModulePageObject;
import com.qa.pagefactory.TopPanelPageObject;
import com.qa.pagefactory.clusters.TopXPageObject;
import com.qa.pagefactory.reports.ReportsArchiveScheduledPageObject;
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
public class TC_CTX_23 extends BaseClass {

  private final LoggingUtils LOGGER = new LoggingUtils(TC_CTX_23.class);

  @Test(dataProvider = "clusterid-data-provider")
  public void TC_CTX_23_verifyTopXWithDifferentCount(String clusterId) {
    test = extent.startTest("TC_CTX_23.verifyTopXWithDifferentCount",
      "Verify TopX report is generation for different application counts.");
    test.assignCategory(" Cluster - Top X");
    WaitExecuter waitExecuter = new WaitExecuter(driver);
    SubTopPanelModulePageObject topPanelComponentPageObject = new SubTopPanelModulePageObject(driver);
    TopX topX = new TopX(driver);

    MouseActions.clickOnElement(driver, topPanelComponentPageObject.reports);
    waitExecuter.sleep(3000);
    ReportsArchiveSchedulePage reportsPage = new ReportsArchiveSchedulePage(driver);
    ReportsArchiveScheduledPageObject reportPageObj = new ReportsArchiveScheduledPageObject(driver);
    LOGGER.info("Click on + button", test);

    waitExecuter.waitUntilPageFullyLoaded();
    TopXPageObject topXPageObject = new TopXPageObject(driver);
    waitExecuter.sleep(2000);
    String statusXpath = reportsPage.clickOnReportName(reportPageObj, PageConstants.ReportsArchiveNames.TopX);

    String topXValue = "15";
    topX.setTopXNumber(topXValue);
    topX.clickOnModalRunButton();
    waitExecuter.waitUntilElementPresent(topXPageObject.archivesHeader);
    waitExecuter.waitUntilPageFullyLoaded();
    waitExecuter.waitUntilTextNotToBeInWebElement(topXPageObject.modalAfterRunButton, "Please Wait");

    Boolean isSuccessFullyStarted = topX.checkIfReportSuccessfullyStarted();
    if (!isSuccessFullyStarted) {
      Assert.fail("Report is not started successfully.");
    }
    LOGGER.info("Report is started successfully.", test);

    WebElement statusElement = driver.findElement(By.xpath(statusXpath));
    try {
      waitExecuter.waitUntilElementPresent(statusElement);
      waitExecuter.waitUntilTextToBeInWebElement(statusElement,
          "SUCCESS");
      LOGGER.pass("Verified TopX report is completed successfully", test);
    } catch (TimeoutException te) {
      throw new AssertionError("TopX report not completed successfully.");
    }

    topX.validateLatestReport("Top X", topXValue);
    LOGGER.pass("Correct value is displayed on report.", test);
    test.log(LogStatus.PASS, "Data is loaded for different top number.");
  }
}
