package com.qa.testcases.cluster.topx;

import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.pagefactory.TopPanelPageObject;
import com.qa.scripts.HomePage;
import com.qa.scripts.clusters.TopX;
import com.qa.utils.MouseActions;
import com.qa.utils.WaitExecuter;
import com.relevantcodes.extentreports.LogStatus;
import org.testng.annotations.Test;

/**
 * @author Ankur Jaiswal
 */

@Marker.All
@Marker.TopX
public class TC_CTX_23 extends BaseClass {

  @Test(dataProvider = "clusterid-data-provider")
  public void verifyTopXWithDifferentCount(String clusterId) {
    test = extent.startTest("TC_CTX_23.verifyTopXWithDifferentCount",
      "Verify TopX report is generation for different application counts.");
    test.assignCategory(" Cluster - Top X");
    WaitExecuter waitExecuter = new WaitExecuter(driver);
    TopPanelPageObject topPanelPageObject = new TopPanelPageObject(driver);

    waitExecuter.waitUntilElementPresent(topPanelPageObject.topXTab);
    waitExecuter.waitUntilPageFullyLoaded();
    waitExecuter.waitUntilElementClickable(topPanelPageObject.topXTab);
    waitExecuter.sleep(3000);
    MouseActions.clickOnElement(driver, topPanelPageObject.topXTab);

    TopX topX = new TopX(driver);
    topX.closeConfirmationMessageNotification();
    topX.clickOnRunButton();

    HomePage homePage = new HomePage(driver);
    homePage.selectMultiClusterId(clusterId);

    topX.setTopXNumber("15");

    topX.clickOnRunButton();
    test.log(LogStatus.PASS, "Data is loaded for different top number.");
  }
}
