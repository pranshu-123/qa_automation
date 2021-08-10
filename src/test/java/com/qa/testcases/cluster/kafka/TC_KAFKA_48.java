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
public class TC_KAFKA_48 extends BaseClass {

  private static final Logger LOGGER = Logger.getLogger(com.qa.testcases.cluster.kafka.TC_KAFKA_48.class.getName());

  @Test(dataProvider = "clusterid-data-provider")
  public void TC_KAFKA_48_verifyBrokerBytesOutPerSecond(String clusterId) {
    test = extent.startTest("TC_KAFKA_48_verifyBrokerBytesOutPerSecond: " + clusterId,
        "Verify 'Bytes Out Per Second' details under brooker tab for selected broker.");
    test.assignCategory("Kafka External");
    Log.startTestCase("TC_KAFKA_48_verifyBrokerBytesOutPerSecond");

    // Initialize all classes objects
    LOGGER.info("Initialize all class objects");
    KafkaPage kafkaPage = new KafkaPage(driver);
    KafkaPageObject kafkaPageObject = new KafkaPageObject(driver);

    kafkaPage.navigateToBrokerTab(kafkaPageObject, clusterId);
    kafkaPage.verifyKafkaKPIGraphs(kafkaPageObject,"Bytes Out per Second","kafkaGraph1");
    test.log(LogStatus.PASS, "Verified 'Bytes Out Per Second' details under broker tab for selected broker successfully ");
  }
}
