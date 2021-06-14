package com.qa.testcases.cluster.elk;

import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.pagefactory.clusters.ELKPageObject;
import com.qa.scripts.DatePicker;
import com.qa.scripts.clusters.elk.ELKPage;
import com.qa.scripts.jobs.applications.AllApps;
import com.qa.utils.Log;
import com.qa.utils.MouseActions;
import com.qa.utils.WaitExecuter;
import com.relevantcodes.extentreports.LogStatus;
import org.testng.annotations.Test;

import java.util.logging.Logger;

@Marker.All
@Marker.ClusterELK
public class TC_ELK_08 extends BaseClass {

  private static final java.util.logging.Logger LOGGER = Logger.getLogger(TC_ELK_08.class.getName());

  @Test(dataProvider = "clusterid-data-provider",description="P0-Verify that the log stash metrics all value should be loaded in unravel UI and matching with the endpoint data.")
  public void TC_ELK_08_verifyLogstashMetrics(String clusterId) {
    test = extent.startTest("verifyLogstashMetrics: " + clusterId,
        "Validate logstash metrics JVM heap used, events received, events emited and events filtered. ");
    test.assignCategory(" ELK ");
    Log.startTestCase("verifyLogstashMetrics");

    // Initialize all classes objects
    test.log(LogStatus.INFO, "Initialize all class objects");
    LOGGER.info("Initialize all class objects");
    ELKPage elkPage = new ELKPage(driver);
    ELKPageObject elkPageObj = new ELKPageObject(driver);
    DatePicker datePicker = new DatePicker(driver);
    WaitExecuter waitExecuter = new WaitExecuter(driver);

    // Navigate to ES tab from header
    MouseActions.clickOnElement(driver, elkPageObj.logstashTab);
    waitExecuter.waitUntilPageFullyLoaded();
    waitExecuter.sleep(2000);

    datePicker.clickOnDatePicker();
    waitExecuter.sleep(1000);
    datePicker.selectLast30Days();
    waitExecuter.sleep(3000);
    waitExecuter.waitUntilPageFullyLoaded();
    elkPage.verifyLogstashNodesTableData(elkPageObj);

    test.log(LogStatus.PASS, "Verified logstash metrics JVM heap used, events received, events emited" +
        " and events filtered in UI successfully ");
  }
}
