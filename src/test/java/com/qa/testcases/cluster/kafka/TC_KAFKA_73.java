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
public class TC_KAFKA_73 extends BaseClass {

  private static final Logger LOGGER = Logger.getLogger(com.qa.testcases.cluster.kafka.TC_KAFKA_73.class.getName());

  @Test(dataProvider = "clusterid-data-provider")
  public void TC_KAFKA_73_verifySelectedRecordColour(String clusterId) {
    test = extent.startTest("TC_KAFKA_73_verifySelectedRecordColour: " + clusterId,
        "Verify selected topic records colour.");
    test.assignCategory("Kafka External");
    Log.startTestCase("TC_KAFKA_73_verifySelectedRecordColour");

    // Initialize all classes objects
    LOGGER.info("Initialize all class objects");
    KafkaPage kafkaPage = new KafkaPage(driver);
    KafkaPageObject kafkaPageObject = new KafkaPageObject(driver);

    kafkaPage.navigateToTopicTab(kafkaPageObject);
    kafkaPage.verifySelectedTopicRecordColor(kafkaPageObject);
    test.log(LogStatus.PASS, "Verified selected topic records colour successfully ");
  }
}
