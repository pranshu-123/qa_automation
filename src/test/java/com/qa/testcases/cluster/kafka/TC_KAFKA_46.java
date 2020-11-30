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
public class TC_KAFKA_46 extends BaseClass {

  private static final Logger LOGGER = Logger.getLogger(com.qa.testcases.cluster.kafka.TC_KAFKA_46.class.getName());

  @Test(dataProvider = "clusterid-data-provider")
  public void TC_KAFKA_46_verifySelectedBrokerRecordColor(String clusterId) {
    test = extent.startTest("TC_KAFKA_46_verifySelectedBrokerRecordColor: " + clusterId,
        "Verify selected broker records colour.");
    test.assignCategory("Kafka External");
    Log.startTestCase("TC_KAFKA_46_verifySelectedBrokerRecordColor");

    // Initialize all classes objects
    LOGGER.info("Initialize all class objects");
    KafkaPage kafkaPage = new KafkaPage(driver);
    KafkaPageObject kafkaPageObject = new KafkaPageObject(driver);

    kafkaPage.navigateToBrokerTab(kafkaPageObject);
    kafkaPage.verifySelectedBrokerRecordColor(kafkaPageObject);
    test.log(LogStatus.PASS, "Verified selected broker records colour successfully ");
  }
}
