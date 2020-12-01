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
public class TC_KAFKA_47 extends BaseClass {

  private static final Logger LOGGER = Logger.getLogger(com.qa.testcases.cluster.kafka.TC_KAFKA_47.class.getName());

  @Test(dataProvider = "clusterid-data-provider")
  public void TC_KAFKA_47_verifyBrokerBytesInPerSecond(String clusterId) {
    test = extent.startTest("TC_KAFKA_47_verifyBrokerBytesInPerSecond: " + clusterId,
        "Verify 'Bytes In Per Second' details under broker tab for selected broker.");
    test.assignCategory("Kafka External");
    Log.startTestCase("TC_KAFKA_47_verifyBrokerBytesInPerSecond");

    // Initialize all classes objects
    LOGGER.info("Initialize all class objects");
    KafkaPage kafkaPage = new KafkaPage(driver);
    KafkaPageObject kafkaPageObject = new KafkaPageObject(driver);

    kafkaPage.navigateToBrokerTab(kafkaPageObject);
    kafkaPage.verifyKafkaKPIGraphs(kafkaPageObject,"Bytes In per Second","kafkaGraph0");
    test.log(LogStatus.PASS, "Verified 'Bytes In Per Second' details under broker tab for selected broker successfully ");
  }
}
