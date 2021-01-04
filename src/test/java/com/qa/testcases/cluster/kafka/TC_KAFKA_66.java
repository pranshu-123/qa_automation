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
public class TC_KAFKA_66 extends BaseClass {

  private static final Logger LOGGER = Logger.getLogger(com.qa.testcases.cluster.kafka.TC_KAFKA_63.class.getName());

  @Test(dataProvider = "clusterid-data-provider")
  public void verifyTopicRecPerBroker(String clusterId) {
    test = extent.startTest("verifyTopicRecPerBroker: " + clusterId,
        "Verify topic records with all details for selected broker under broker tab.");
    test.assignCategory("Kafka External");
    Log.startTestCase("verifyTopicRecPerBroker");

    // Initialize all classes objects
    LOGGER.info("Initialize all class objects");
    KafkaPage kafkaPage = new KafkaPage(driver);
    KafkaPageObject kafkaPageObject = new KafkaPageObject(driver);

    kafkaPage.navigateToBrokerTab(kafkaPageObject);
    kafkaPage.verifyTopicRecordPerBroker(kafkaPageObject);
    test.log(LogStatus.PASS, "Verified topic records with all details for selected broker under broker" +
        " tab successfully ");
  }
}
