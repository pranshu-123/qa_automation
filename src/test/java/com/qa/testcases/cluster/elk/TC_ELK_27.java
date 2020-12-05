package com.qa.testcases.cluster.elk;

import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.pagefactory.clusters.ELKPageObject;
import com.qa.pagefactory.clusters.KafkaPageObject;
import com.qa.scripts.clusters.elk.ELKPage;
import com.qa.scripts.clusters.kafka.KafkaPage;
import com.qa.utils.Log;
import com.qa.utils.MouseActions;
import com.qa.utils.WaitExecuter;
import com.relevantcodes.extentreports.LogStatus;
import org.testng.annotations.Test;

import java.util.logging.Logger;

@Marker.All
@Marker.ELK
public class TC_ELK_27 extends BaseClass {

  private static final java.util.logging.Logger LOGGER = Logger.getLogger(com.qa.testcases.cluster.elk.TC_ELK_27.class.getName());

  @Test(dataProvider = "clusterid-data-provider")
  public void TC_ELK_27_verifyESNodeSpecificGraphs(String clusterId) {
    test = extent.startTest("TC_ELK_27_verifyESNodeSpecificGraphs: " + clusterId,
        "Validate ES cluster metrics node specific graph");
    test.assignCategory(" ELK ");
    Log.startTestCase("TC_ELK_27_verifyESNodeSpecificGraphs");

    // Initialize all classes objects
    test.log(LogStatus.INFO, "Initialize all class objects");
    LOGGER.info("Initialize all class objects");
    ELKPage elkPage = new ELKPage(driver);
    ELKPageObject elkPageObj = new ELKPageObject(driver);
    KafkaPage kafkaPage = new KafkaPage(driver);
    KafkaPageObject kafkaPageObject = new KafkaPageObject(driver);
    WaitExecuter waitExecuter = new WaitExecuter(driver);

    // Navigate to ES tab from header
    MouseActions.clickOnElement(driver, elkPageObj.ESTab);
    waitExecuter.waitUntilPageFullyLoaded();
    waitExecuter.sleep(2000);

    elkPage.verifyClusterDropDown(elkPageObj);
    elkPage.verifyNodeSpecificGraphs(elkPageObj, kafkaPageObject);

    test.log(LogStatus.PASS, "Verified ES cluster metrics node specific graph in UI successfully ");
  }
}
