package com.qa.testcases.cluster.kafka;

import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.pagefactory.SubTopPanelModulePageObject;
import com.qa.pagefactory.clusters.KafkaPageObject;
import com.qa.scripts.clusters.kafka.KafkaPage;
import com.qa.utils.Log;
import com.qa.utils.MouseActions;
import com.qa.utils.WaitExecuter;
import com.relevantcodes.extentreports.LogStatus;
import org.testng.annotations.Test;

import java.util.logging.Logger;

@Marker.KafkaExternal
@Marker.All
public class TC_KAFKA_18 extends BaseClass {

  private static final Logger LOGGER = Logger.getLogger(com.qa.testcases.cluster.kafka.TC_KAFKA_18.class.getName());

  @Test(dataProvider = "clusterid-data-provider")
  public void TC_KAFKA_18_verifyMessagesInPerSecond(String clusterId) {
    test = extent.startTest("TC_KAFKA_18_verifyMessagesInPerSecond: " + clusterId,
        "Verify 'Messages In per Second' KPI information name ,values and graphs");
    test.assignCategory("Kafka External");
    Log.startTestCase("TC_KAFKA_18_verifyMessagesInPerSecond");

    // Initialize all classes objects
    LOGGER.info("Initialize all class objects");
    KafkaPage kafkaPage = new KafkaPage(driver);
    KafkaPageObject kafkaPageObject = new KafkaPageObject(driver);
    WaitExecuter waitExecuter = new WaitExecuter(driver);

    // Navigate to Kafka tab
    MouseActions.clickOnElement(driver, kafkaPageObject.kafkaTab);
    waitExecuter.waitUntilPageFullyLoaded();
    waitExecuter.sleep(2000);
    kafkaPage.verifyClusterDropDown(kafkaPageObject);
    kafkaPage.verifyKafkaClusterKPIs(kafkaPageObject, "Messages In per Second");
    test.log(LogStatus.PASS, "Verified 'Messages In per Second' KPI information name ,values and graphs successfully ");
  }
}
