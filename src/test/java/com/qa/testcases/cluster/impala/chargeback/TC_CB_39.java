package com.qa.testcases.cluster.impala.chargeback;

import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.pagefactory.CommonPageObject;
import com.qa.scripts.DatePicker;
import com.qa.scripts.HomePage;
import com.qa.scripts.clusters.impala.ChargeBackImpala;
import com.qa.utils.DateUtils;
import com.qa.utils.JavaScriptExecuter;
import com.qa.utils.MouseActions;
import com.qa.utils.WaitExecuter;
import com.relevantcodes.extentreports.LogStatus;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * @author Ankur Jaiswal
 */
@Marker.All
@Marker.ImpalaChargeback
public class TC_CB_39 extends BaseClass {

  /**
   * Validate the user can view the app details by clicking on the application
   */
  @Test(dataProvider = "clusterid-data-provider",description ="P0-Verify the user can view the app details by clicking on the application")
  public void TC_CB_39_VerifyAppDetails(String clusterId) {
    test = extent.startTest("TC_CB_39_VerifyAppDetails: "+clusterId,"Validate the user can view the app details by clicking on the application");
    test.assignCategory(" Cluster - Impala Chargeback");

    WaitExecuter waitExecuter = new WaitExecuter(driver);
    ChargeBackImpala chargeBackImpala = new ChargeBackImpala(driver);
    chargeBackImpala.selectImpalaChargeback();

    // Select the cluster
    test.log(LogStatus.INFO, "Select clusterId : "+clusterId);
    HomePage homePage = new HomePage(driver);

    chargeBackImpala.selectImpalaType("Impala");
    waitExecuter.sleep(2000);
    homePage.selectMultiClusterIdClusterPage(clusterId);
    waitExecuter.sleep(1000);


    CommonPageObject commonPageObject = new CommonPageObject(driver);
    MouseActions.clickOnElement(driver, commonPageObject.clusterDropdown);
    waitExecuter.sleep(1000);
    MouseActions.clickOnElement(driver, commonPageObject.clustersList.get(0));
    chargeBackImpala.selectImpalaType("Impala");
    waitExecuter.sleep(3000);

    DatePicker datePicker = new DatePicker(driver);
    datePicker.clickOnDatePicker();
    datePicker.selectLast90Days();
    waitExecuter.sleep(2000);

    String parentWindow = driver.getWindowHandle();
    JavaScriptExecuter.scrollOnElement(driver, chargeBackImpala.getImpalaJobsTableRecord().get(0));
    chargeBackImpala.getImpalaJobsTableRecord().get(0).click();
    Assert.assertTrue(driver.getWindowHandles().size() > 1, "two windows is not present");
    test.log(LogStatus.PASS, "Validate window is opened by clicking at impala jobs.");
    System.out.println(driver.getCurrentUrl());
    for (String window : driver.getWindowHandles()) {
      if (!window.equals(parentWindow)) {
        driver.switchTo().window(window);
        Assert.assertTrue(driver.getCurrentUrl().contains("/app/application/impala"), "application is not redirected on clicking on impala jobs.");
        test.log(LogStatus.PASS, "Validate application is redirected by clicking at impala jobs.");
        driver.close();
      }
    }
    driver.switchTo().window(parentWindow);
  }
}
