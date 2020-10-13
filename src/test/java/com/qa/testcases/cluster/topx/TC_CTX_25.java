package com.qa.testcases.cluster.topx;

import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.pagefactory.TopPanelPageObject;
import com.qa.scripts.HomePage;
import com.qa.scripts.clusters.TopX;
import com.qa.utils.MouseActions;
import com.qa.utils.WaitExecuter;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

/**
 * @author Ankur Jaiswal
 */
@Marker.Regression
@Marker.TopX
public class TC_CTX_25 extends BaseClass {

  @Test
  public void verifyForDifferentCluster() {
      test = extent.startTest("TC_CTX_25.verifyForDifferentCluster",
        "Verify TopX report is generation for different clusters");
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
      for (int i=0; i<topX.getClustersList().size(); i++) {
        MouseActions.clickOnElement(driver, topX.getClustersList().get(i));
        waitExecuter.sleep(1000);
        topX.clickOnRunButton();
        waitExecuter.waitUntilTextToBeInWebElement(topX.getConfirmationMessage(),
          "Top X Report completed successfully.");
        topX.closeConfirmationMessageNotification();
        topX.clickOnRunButton();
        homePage.clickOnClusterDropDown();
      }
  }
}
