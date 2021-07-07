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
public class TC_KAFKA_74 extends BaseClass {

  private static final Logger LOGGER = Logger.getLogger(com.qa.testcases.cluster.kafka.TC_KAFKA_74.class.getName());

  @Test(dataProvider = "clusterid-data-provider")
  public void TC_KAFKA_74_verifyTopBytesInperSecond(String clusterId) {
    test = extent.startTest("TC_KAFKA_74_verifyTopBytesInperSecond: " + clusterId,
        "Verify 'Bytes In Per Second' details under topic tab for selected topic.");
    test.assignCategory("Kafka External");
    Log.startTestCase("TC_KAFKA_74_verifyTopBytesInperSecond");

    // Initialize all classes objects
    LOGGER.info("Initialize all class objects");
    KafkaPage kafkaPage = new KafkaPage(driver);
    KafkaPageObject kafkaPageObject = new KafkaPageObject(driver);

    kafkaPage.navigateToTopicTab(kafkaPageObject, clusterId);
    kafkaPage.verifyKafkaKPIGraphs(kafkaPageObject, "Bytes In per Second", "kafkaGraph0");
    test.log(LogStatus.PASS, "Verified Bytes In per Second' details under topic tab for selected topic successfully ");
  }
}
