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
public class TC_ELK_11 extends BaseClass {

  private static final java.util.logging.Logger LOGGER = Logger.getLogger(TC_ELK_11.class.getName());

  @Test(dataProvider = "clusterid-data-provider")
  public void verifyLogstashPipelineInTable(String clusterId) {
    test = extent.startTest("verifyLogstashPipelineInTable: " + clusterId,
        "Validate pipelines in table.");
    test.assignCategory(" ELK ");
    Log.startTestCase("verifyLogstashPipelineInTable");

    // Initialize all classes objects
    test.log(LogStatus.INFO, "Initialize all class objects");
    LOGGER.info("Initialize all class objects");
    ELKPage elkPage = new ELKPage(driver);
    ELKPageObject elkPageObj = new ELKPageObject(driver);
    DatePicker datePicker = new DatePicker(driver);
    WaitExecuter waitExecuter = new WaitExecuter(driver);
    AllApps allApps = new AllApps(driver);

    // Navigate to pipeline tab from Logstash tab
    elkPage.navigateToPipilineTab(elkPageObj);
    elkPage.verifyPipelineTab(elkPageObj);

    test.log(LogStatus.PASS, "Verified pipelines in UI successfully ");
  }
}
