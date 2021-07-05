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
public class TC_ELK_16 extends BaseClass {

  private static final java.util.logging.Logger LOGGER = Logger.getLogger(TC_ELK_16.class.getName());

  @Test(dataProvider = "clusterid-data-provider",description="P0-Verify the metrics graphs should be loaded properly and match with Kibana..")
  public void TC_ELK_16_verifyPipelineSpecificKPI(String clusterId) {
    test = extent.startTest("TC_ELK_16_verifyPipelineSpecificKPI: " + clusterId,
        "Validate pipeline specific KPI");
    test.assignCategory(" ELK ");
    Log.startTestCase("verifyPipelineSpecificKPI");

    // Initialize all classes objects
    test.log(LogStatus.INFO, "Initialize all class objects");
    LOGGER.info("Initialize all class objects");
    ELKPage elkPage = new ELKPage(driver);
    ELKPageObject elkPageObj = new ELKPageObject(driver);

    // Navigate to pipeline tab from Logstash tab
    elkPage.navigateToPipilineTab(elkPageObj);
    elkPage.verifyPipelineSpecificKPIs(elkPageObj);

    test.log(LogStatus.PASS, "Verified pipeline specific KPI successfully ");
  }
}
