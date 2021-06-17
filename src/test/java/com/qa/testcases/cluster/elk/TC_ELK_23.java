package com.qa.testcases.cluster.elk;

import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.pagefactory.clusters.ELKPageObject;
import com.qa.scripts.DatePicker;
import com.qa.scripts.clusters.elk.ELKPage;
import com.qa.utils.Log;
import com.qa.utils.MouseActions;
import com.qa.utils.WaitExecuter;
import com.relevantcodes.extentreports.LogStatus;
import org.testng.annotations.Test;

import java.util.logging.Logger;

@Marker.All
@Marker.ClusterELK
public class TC_ELK_23 extends BaseClass {

  private static final java.util.logging.Logger LOGGER = Logger.getLogger(com.qa.testcases.cluster.elk.TC_ELK_23.class.getName());

  @Test(dataProvider = "clusterid-data-provider",description="P0-Verify that the ES KPI should be loaded in Unravel UI and value should match with configuration file in the Kibana UI.")
  public void TC_ELK_23_verifyESKPIs(String clusterId) {
    test = extent.startTest("TC_ELK_23_verifyESKPIs: " + clusterId,
        "Validate ES KPI in UI.");
    test.assignCategory(" ELK ");
    Log.startTestCase("TC_ELK_23_verifyESKPIs");

    // Initialize all classes objects
    test.log(LogStatus.INFO, "Initialize all class objects");
    LOGGER.info("Initialize all class objects");
    ELKPage elkPage = new ELKPage(driver);
    ELKPageObject elkPageObj = new ELKPageObject(driver);
    WaitExecuter waitExecuter = new WaitExecuter(driver);

    // Navigate to ES tab from header
    MouseActions.clickOnElement(driver, elkPageObj.ESTab);
    waitExecuter.waitUntilPageFullyLoaded();
    DatePicker datePicker = new DatePicker(driver);
    datePicker.clickOnDatePicker();
    waitExecuter.sleep(1000);
    datePicker.selectLast30Days();
    waitExecuter.waitUntilPageFullyLoaded();

    elkPage.verifyClusterDropDown(elkPageObj);
    elkPage.verifyESClusterKPIs(elkPageObj);

    test.log(LogStatus.PASS, "Verified ES KPIs in UI successfully ");
  }
}
