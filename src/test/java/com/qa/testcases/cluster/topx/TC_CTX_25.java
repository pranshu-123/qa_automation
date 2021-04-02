package com.qa.testcases.cluster.topx;

import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.constants.PageConstants;
import com.qa.pagefactory.SubTopPanelModulePageObject;
import com.qa.pagefactory.TopPanelPageObject;
import com.qa.pagefactory.reports.ReportsArchiveScheduledPageObject;
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

import java.util.List;

/**
 * @author Ankur Jaiswal
 */
@Marker.All
@Marker.TopX
public class TC_CTX_25 extends BaseClass {

    Logger logger = LoggerFactory.getLogger(TC_CTX_24.class);
  @Test
  public void verifyForDifferentCluster() {
      test = extent.startTest("TC_CTX_25.verifyForDifferentCluster",
        "Verify TopX report is generation for different clusters");
      test.assignCategory(" Cluster - Top X");
      WaitExecuter waitExecuter = new WaitExecuter(driver);
//      TopPanelPageObject topPanelPageObject = new TopPanelPageObject(driver);
//      waitExecuter.waitUntilElementPresent(topPanelPageObject.topXTab);
//      waitExecuter.waitUntilPageFullyLoaded();
//      waitExecuter.waitUntilElementClickable(topPanelPageObject.topXTab);
//      waitExecuter.sleep(3000);
//      MouseActions.clickOnElement(driver, topPanelPageObject.topXTab);

      TopX topX = new TopX(driver);
//      topX.closeConfirmationMessageNotification();
//      topX.clickOnRunButton();
      SubTopPanelModulePageObject topPanelComponentPageObject = new SubTopPanelModulePageObject(driver);
      MouseActions.clickOnElement(driver, topPanelComponentPageObject.reports);
      waitExecuter.sleep(3000);
      ReportsArchiveSchedulePage reportsPage = new ReportsArchiveSchedulePage(driver);
      ReportsArchiveScheduledPageObject reportPageObj = new ReportsArchiveScheduledPageObject(driver);
      logger.info("Click on + button", test);
      String statusXpath = reportsPage.clickOnReportName(reportPageObj, PageConstants.ReportsArchiveNames.TopX);

      HomePage homePage = new HomePage(driver);
      homePage.clickOnClusterDropDown();
      waitExecuter.sleep(1000);
      for (int i=0; i<topX.getClustersList().size(); i++) {
        String clusterId  = topX.getClustersList().get(i).getText();
        MouseActions.clickOnElement(driver, topX.getClustersList().get(i));
        waitExecuter.sleep(1000);
        topX.clickOnModalRunButton();

//        try {
//            waitExecuter.waitUntilTextToBeInWebElement(topX.getConfirmationMessage(),
//                "Top X Report completed successfully.");
//        } catch (TimeoutException te) {
//            Assert.assertTrue(false, "TopX Report is not completed");
//        }
          WebElement statusElement = driver.findElement(By.xpath(statusXpath));
          try{
              waitExecuter.waitUntilTextToBeInWebElement(statusElement,
                      "SUCCESS");
          }catch (TimeoutException te) {
              throw new AssertionError("Top X Report not completed successfully.");
          }
//        topX.closeConfirmationMessageNotification();
//        topX.clickOnRunButton();
        test.log(LogStatus.PASS, "TopX report is generated for cluster id: " + clusterId);
        //homePage.clickOnClusterDropDown();
      }
  }
}
