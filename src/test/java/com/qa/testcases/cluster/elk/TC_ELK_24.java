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

import java.util.List;
import java.util.logging.Logger;

@Marker.All
@Marker.ClusterELK
public class TC_ELK_24 extends BaseClass {

  private static final java.util.logging.Logger LOGGER = Logger.getLogger(com.qa.testcases.cluster.elk.TC_ELK_24.class.getName());

  @Test(dataProvider = "clusterid-data-provider",description="P0-Verify that the ES nodes table should be loaded with CPU usage, disk total, Disk use, load average, and JVM memory in kibana UI.")
  public void TC_ELK_24_verifyESNodesTable(String clusterId) {
    test = extent.startTest("TC_ELK_24_verifyESNodesTable: " + clusterId,
        "Validate ES nodes table.");
    test.assignCategory(" ELK ");
    Log.startTestCase("TC_ELK_24_verifyESNodesTable");

    // Initialize all classes objects
    test.log(LogStatus.INFO, "Initialize all class objects");
    LOGGER.info("Initialize all class objects");
    ELKPage elkPage = new ELKPage(driver);
    ELKPageObject elkPageObj = new ELKPageObject(driver);
    WaitExecuter waitExecuter = new WaitExecuter(driver);

    // Navigate to ES tab from header
    MouseActions.clickOnElement(driver, elkPageObj.ESTab);
    waitExecuter.waitUntilPageFullyLoaded();
    waitExecuter.sleep(2000);
    DatePicker datePicker = new DatePicker(driver);
    datePicker.clickOnDatePicker();
    waitExecuter.sleep(1000);
    datePicker.selectLast30Days();
    waitExecuter.waitUntilPageFullyLoaded();
    elkPage.verifyClusterDropDown(elkPageObj);
    elkPage.verifyESNodeTable(elkPageObj);

    test.log(LogStatus.PASS, "Verified ES nodes table in UI successfully ");
  }
}
