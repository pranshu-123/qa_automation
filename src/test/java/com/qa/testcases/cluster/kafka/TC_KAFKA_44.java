package com.qa.testcases.cluster.kafka;

import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.pagefactory.clusters.KafkaPageObject;
import com.qa.scripts.clusters.kafka.KafkaPage;
import com.qa.utils.Log;
import com.relevantcodes.extentreports.LogStatus;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.logging.Logger;

@Marker.KafkaExternal
@Marker.All
public class TC_KAFKA_44 extends BaseClass {

  private static final Logger LOGGER = Logger.getLogger(com.qa.testcases.cluster.kafka.TC_KAFKA_44.class.getName());

  @Test(dataProvider = "clusterid-data-provider")
  public void TC_KAFKA_44_verifyBrokerMetrics(String clusterId) {
    test = extent.startTest("TC_KAFKA_44_verifyBrokerMetrics: " + clusterId,
        "Verify broker records with all details in broker metrics.");
    test.assignCategory("Kafka External");
    Log.startTestCase("TC_KAFKA_44_verifyBrokerMetrics");

    // Initialize all classes objects
    LOGGER.info("Initialize all class objects");
    KafkaPage kafkaPage = new KafkaPage(driver);
    KafkaPageObject kafkaPageObject = new KafkaPageObject(driver);

    kafkaPage.navigateToBrokerTab(kafkaPageObject);
    kafkaPage.verifyBrokerMetrics(kafkaPageObject);
    test.log(LogStatus.PASS, "Verified broker records with all details in broker metrics successfully ");
  }
}
