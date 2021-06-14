package com.qa.testcases.cluster.elk;

import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.pagefactory.DatePickerPageObject;
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
public class TC_ELK_19 extends BaseClass {

  private static final java.util.logging.Logger LOGGER = Logger.getLogger(TC_ELK_17.class.getName());

  @Test(dataProvider = "clusterid-data-provider",description="P0-Verify that the Kibana KPIs must be loaded into the Unravel UI, and the value should match the configuration file in the Kibana UI.")
  public void TC_ELK_19_verifyKibanaKPIs(String clusterId) {
    test = extent.startTest("verifyKibanaKPIs: " + clusterId,
        "Validate Kibana KPI in UI.");
    test.assignCategory(" ELK ");
    Log.startTestCase("verifyKibanaKPIs");

    // Initialize all classes objects
    test.log(LogStatus.INFO, "Initialize all class objects");
    LOGGER.info("Initialize all class objects");
    ELKPage elkPage = new ELKPage(driver);
    ELKPageObject elkPageObj = new ELKPageObject(driver);
    WaitExecuter waitExecuter = new WaitExecuter(driver);
    DatePicker datePicker = new DatePicker(driver);
    DatePickerPageObject datePickerPageObject = new DatePickerPageObject(driver);

    // Navigate to pipeline tab from Logstash tab
    MouseActions.clickOnElement(driver, elkPageObj.kibanaTab);
    // waitExecuter.waitUntilElementClickable(elkPageObj.clusterDropDown);
    waitExecuter.waitUntilPageFullyLoaded();
    datePicker.clickOnDatePicker();
    waitExecuter.waitUntilPageFullyLoaded();
    datePicker.selectLast30Days();
    waitExecuter.waitUntilPageFullyLoaded();
    //waitExecuter.waitUntilElementClickable(datePickerPageObject.dateRange);
    elkPage.verifyKibanaKPIs(elkPageObj);

    test.log(LogStatus.PASS, "Verified Kibana KPI in UI successfully ");
  }
}
