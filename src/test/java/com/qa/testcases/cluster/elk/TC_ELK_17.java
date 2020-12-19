package com.qa.testcases.cluster.elk;

import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.pagefactory.clusters.ELKPageObject;
import com.qa.scripts.clusters.elk.ELKPage;
import com.qa.utils.Log;
import com.relevantcodes.extentreports.LogStatus;
import org.testng.annotations.Test;

import java.util.logging.Logger;

@Marker.All
@Marker.ClusterELK
public class TC_ELK_17 extends BaseClass {

  private static final java.util.logging.Logger LOGGER = Logger.getLogger(TC_ELK_17.class.getName());

  @Test(dataProvider = "clusterid-data-provider")
  public void verifyPipelineMetricsGraphs(String clusterId) {
    test = extent.startTest("verifyPipelineMetricGraphs: " + clusterId,
        "Validate pipeline metrics graph");
    test.assignCategory(" ELK ");
    Log.startTestCase("verifyPipelineMetricGraphs");

    // Initialize all classes objects
    test.log(LogStatus.INFO, "Initialize all class objects");
    LOGGER.info("Initialize all class objects");
    ELKPage elkPage = new ELKPage(driver);
    ELKPageObject elkPageObj = new ELKPageObject(driver);

    // Navigate to pipeline tab from Logstash tab
    elkPage.navigateToPipilineTab(elkPageObj);
    elkPage.verifyPipelineMetricGraphs(elkPageObj);

    test.log(LogStatus.PASS, "Verified pipeline metrics graph successfully ");
  }
}
