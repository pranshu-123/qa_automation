package com.qa.testcases.cluster.elk;

import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.pagefactory.clusters.ELKPageObject;
import com.qa.scripts.clusters.elk.ELKPage;
import com.qa.utils.Log;
import com.qa.utils.MouseActions;
import com.qa.utils.WaitExecuter;
import com.relevantcodes.extentreports.LogStatus;
import org.testng.annotations.Test;

import java.util.logging.Logger;

@Marker.All
@Marker.ClusterELK
public class TC_ELK_31 extends BaseClass {

  private static final java.util.logging.Logger LOGGER = Logger.getLogger(com.qa.testcases.cluster.elk.TC_ELK_31.class.getName());

  @Test(dataProvider = "clusterid-data-provider",description="P0-Validate the ES indices specific metrics graph should be loaded and match with Kibana graph.")
  public void TC_ELK_31_verifyPerIndicesMetricsGraph(String clusterId) {
    test = extent.startTest("TC_ELK_31_verifyPerIndicesMetricsGraph: " + clusterId,
        "Validate ES indices specific metrics graph.");
    test.assignCategory(" ELK ");
    Log.startTestCase("TC_ELK_31_verifyPerIndicesMetricsGraph");

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

    elkPage.verifyClusterDropDown(elkPageObj);
    elkPage.navigateToIndicesTab(elkPageObj);
    elkPage.verifyPerIndicesMetricsGraph(elkPageObj);
    test.log(LogStatus.PASS, "Verified ES Indices specific metrics graph in UI successfully ");
  }
}
