package com.qa.testcases.cluster.topx;

import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.pagefactory.TopPanelPageObject;
import com.qa.scripts.DatePicker;
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
public class TC_CTX_21 extends BaseClass {

  @Test(dataProvider = "clusterid-data-provider")
  public void verifyDatePicker(String clusterId) {
    test = extent.startTest("TC_CTX_21.verifyDatePicker", "Verify date picker in new report page");
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
