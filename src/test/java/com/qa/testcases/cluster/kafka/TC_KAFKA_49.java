package com.qa.testcases.cluster.kafka;

import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.pagefactory.clusters.KafkaPageObject;
import com.qa.scripts.clusters.kafka.KafkaPage;
import com.qa.utils.Log;
import com.relevantcodes.extentreports.LogStatus;
import org.testng.annotations.Test;

import java.util.logging.Logger;

@Marker.KafkaExternal
@Marker.All
public class TC_KAFKA_49 extends BaseClass {

  private static final Logger LOGGER = Logger.getLogger(com.qa.testcases.cluster.kafka.TC_KAFKA_49.class.getName());

  @Test(dataProvider = "clusterid-data-provider")
  public void TC_KAFKA_49_verifyBrokerMsgInPerSecond(String clusterId) {
    test = extent.startTest("TC_KAFKA_49_verifyBrokerMsgInPerSecond: " + clusterId,
        "Verify 'Messages In per Second' details under brooker tab for selected broker.");
    test.assignCategory("Kafka External");
    Log.startTestCase("TC_KAFKA_49_verifyBrokerMsgInPerSecond");

    // Initialize all classes objects
    LOGGER.info("Initialize all class objects");
    KafkaPage kafkaPage = new KafkaPage(driver);
    KafkaPageObject kafkaPageObject = new KafkaPageObject(driver);

    kafkaPage.navigateToBrokerTab(kafkaPageObject);
    kafkaPage.verifyKafkaKPIGraphs(kafkaPageObject,"Messages In per Second","kafkaGraph2");
    test.log(LogStatus.PASS, "Verified 'Messages In per Second' details under broker tab for selected broker successfully ");
  }
}
