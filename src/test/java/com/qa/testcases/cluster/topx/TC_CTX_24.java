package com.qa.testcases.cluster.topx;

import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.pagefactory.TopPanelPageObject;
import com.qa.scripts.HomePage;
import com.qa.scripts.clusters.TopX;
import com.qa.utils.MouseActions;
import com.qa.utils.WaitExecuter;
import com.relevantcodes.extentreports.LogStatus;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * @author Ankur Jaiswal
 */
@Marker.All
@Marker.TopX
public class TC_CTX_24 extends BaseClass {

  @Test()
  public void verifyClusterFilter() {
    test = extent.startTest("TC_CTX_24.verifyClusterFilter",
      "Verify cluster filter in new report page.");
    test.assignCategory("4620 Cluster - Top X");
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
    homePage.clickOnClusterDropDown();

    Assert.assertTrue(topX.getClustersList().size() > 0, "No cluster is displayed.");
    test.log(LogStatus.PASS, "Cluster is displayed in dropdown.");
  }
}
