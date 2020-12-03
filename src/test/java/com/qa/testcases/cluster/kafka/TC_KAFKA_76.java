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
public class TC_KAFKA_76 extends BaseClass {

  private static final Logger LOGGER = Logger.getLogger(com.qa.testcases.cluster.kafka.TC_KAFKA_76.class.getName());

  @Test(dataProvider = "clusterid-data-provider")
  public void TC_KAFKA_76_verifyTopBytesOutperSecond(String clusterId) {
    test = extent.startTest("TC_KAFKA_76_verifyTopBytesOutperSecond: " + clusterId,
        "Verify 'Bytes Out per Second' details under topic tab for selected topic.");
    test.assignCategory("Kafka External");
    Log.startTestCase("TC_KAFKA_76_verifyTopBytesOutperSecond");

    // Initialize all classes objects
    LOGGER.info("Initialize all class objects");
    KafkaPage kafkaPage = new KafkaPage(driver);
    KafkaPageObject kafkaPageObject = new KafkaPageObject(driver);

    kafkaPage.navigateToTopicTab(kafkaPageObject);
    kafkaPage.verifyKafkaKPIGraphs(kafkaPageObject, "Bytes Out per Second", "kafkaGraph2");
    test.log(LogStatus.PASS, "Verified 'Bytes Out per Second' details under topic tab for selected topic successfully ");
  }
}
