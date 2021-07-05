package com.qa.testcases.cluster.elk;

import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.pagefactory.clusters.ELKPageObject;
import com.qa.scripts.DatePicker;
import com.qa.scripts.clusters.elk.ELKPage;
import com.qa.scripts.jobs.applications.AllApps;
import com.qa.utils.Log;
import com.qa.utils.WaitExecuter;
import com.relevantcodes.extentreports.LogStatus;
import org.testng.annotations.Test;

import java.util.logging.Logger;

@Marker.All
@Marker.ClusterELK
public class TC_ELK_14 extends BaseClass {

  private static final java.util.logging.Logger LOGGER = Logger.getLogger(TC_ELK_13.class.getName());

  @Test(dataProvider = "clusterid-data-provider",description="Verify that the Events received,events emitted,events filtered value should be associated with the nodes")
  public void TC_ELK_14_verifyLogstashKPIsWithAssociatedNodes(String clusterId) {
    test = extent.startTest("TC_ELK_14_verifyLogstashKPIsWithAssociatedNodes: " + clusterId,
        "Validate Events received , events emitted, events filtered value with associated nodes.");
    test.assignCategory(" ELK ");
    Log.startTestCase("verifyLogstashKPIsWithAssociatedNodes");

    // Initialize all classes objects
    test.log(LogStatus.INFO, "Initialize all class objects");
    LOGGER.info("Initialize all class objects");
    ELKPage elkPage = new ELKPage(driver);
    ELKPageObject elkPageObj = new ELKPageObject(driver);

    // Navigate to pipeline tab from Logstash tab
    elkPage.navigateToPipilineTab(elkPageObj);
    elkPage.verifyPipelineTable(elkPageObj);

    test.log(LogStatus.PASS, "Verified Events received , events emitted, events filtered value with " +
        "associated nodes successfully ");
  }
}
