package com.qa.testcases.cluster.overview;

import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.constants.PageConstants;
import com.qa.pagefactory.HomePageObject;
import com.qa.pagefactory.TopPanelPageObject;
import com.qa.scripts.DatePicker;
import com.qa.scripts.HomePage;
import com.qa.utils.MouseActions;
import com.qa.utils.WaitExecuter;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * @author Ankur Jaiswal
 */
@Marker.ClusterOverview
@Marker.All
public class TC_CO_19 extends BaseClass {

  /**
   * Validate alert row of home page is redirecting to yarn page on clicking.
   */

  @Test(dataProvider = "clusterid-data-provider",description="P0-Validate that the alert row of the home page is redirecting to the yarn page on clicking.")
  public void TC_CO_19_ValidateAutoAlertOfHomePage(String clusterId) {
    test = extent.startTest("TC_CO_19_ValidateAutoAlertOfHomePage: "+clusterId, "Validate alert row of home page is redirecting to yarn page on clicking.");
    test.assignCategory(" Cluster Overview");
    TopPanelPageObject topPanelPageObject = new TopPanelPageObject(driver);
    MouseActions.clickOnElement(driver, topPanelPageObject.overviewTab);

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
      MouseActions.clickOnElement(driver, homePageObject.firstAlertRow);
      executer.waitUntilPageFullyLoaded();
    } catch (NoSuchElementException e) {
      Assert.assertTrue(false, "No Alert present.");
    }

    try {
      executer.waitUntilUrlContains("/clusters/resources");
      executer.waitUntilPageFullyLoaded();
      test.log(LogStatus.PASS, "Url contains the resources path");
    } catch (TimeoutException te) {
      Assert.assertTrue(false,"Url does not contain resources path");
    }

    try {
      executer.waitUntilTextToBeInWebElement(topPanelPageObject.pageHeading, PageConstants.YARN_RESOURCE_HEADER);
      test.log(LogStatus.PASS, "Successfully verified Yarn Resource Usage page is displayed.");
    } catch (TimeoutException te) {
      Assert.assertTrue(false, "Yarn Resource Usage page is not displayed");
    }
  }
}
