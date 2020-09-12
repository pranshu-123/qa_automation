package com.qa.testcases.cluster.overview;

import com.qa.base.BaseClass;
import com.qa.constants.PageConstants;
import com.qa.pagefactory.HomePageObject;
import com.qa.pagefactory.TopPanelPageObject;
import com.qa.scripts.ComponentHelper;
import com.qa.scripts.DatePicker;
import com.qa.scripts.HomePage;
import com.qa.utils.JavaScriptExecuter;
import com.qa.utils.WaitExecuter;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.NoSuchElementException;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * @author Ankur Jaiswal
 */
public class TC_CO_19 extends BaseClass {

  /**
   * Validate alert row of home page is redirecting to yarn page on clicking.
   */

  @Test(dataProvider = "clusterid-data-provider")
  public void TC_CO_19_ValidateAutoAlertOfHomePage(String clusterId) {
    test = extent.startTest("TC_CO_19_ValidateAutoAlertOfHomePage: "+clusterId, "Validate alert row of home page is redirecting to yarn page on clicking.");
    test.assignCategory("4620 - Cluster Overview");

    HomePage homePage = new HomePage(driver);
    homePage.selectMultiClusterId(clusterId);

    HomePageObject homePageObject = new HomePageObject(driver);
    WaitExecuter executer = new WaitExecuter(driver);
    executer.sleep(3000);

    // Select this month
    DatePicker datePicker = new DatePicker(driver);
    datePicker.clickOnDatePicker();
    datePicker.selectThisMonth();

    //Click on first alert displayed at homepage
    try {
      JavaScriptExecuter.clickOnElement(driver,homePageObject.firstAlertRow);
    } catch (NoSuchElementException e) {
      Assert.assertTrue(false, "No Alert present.");
    }

    boolean isUrlContainResource = ComponentHelper.isUrlContain(driver,"/clusters/resources");

    Assert.assertTrue(isUrlContainResource,"Url does not contain resources path");
    test.log(LogStatus.PASS, "Url contains the resources path");

    TopPanelPageObject topPanelPageObject = new TopPanelPageObject(driver);
    Assert.assertEquals(topPanelPageObject.pageHeading.getText(), PageConstants.YARN_RESOURCE_HEADER,
      "Yarn Resource Usage page is not displayed");
    test.log(LogStatus.PASS, "Successfully verified Yarn Resource Usage page is displayed.");
  }
}
