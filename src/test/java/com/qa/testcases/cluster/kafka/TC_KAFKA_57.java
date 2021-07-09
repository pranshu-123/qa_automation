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
public class TC_KAFKA_57 extends BaseClass {

  private static final Logger LOGGER = Logger.getLogger(com.qa.testcases.cluster.kafka.TC_KAFKA_57.class.getName());

  @Test(dataProvider = "clusterid-data-provider")
  public void TC_KAFKA_57_verifyBrokerFetchTotalTime(String clusterId) {
    test = extent.startTest("TC_KAFKA_57_verifyBrokerFetchTotalTime: " + clusterId,
        "Verify 'Fetch Total Time, 99th Percentile' details under broker tab for selected broker.");
    test.assignCategory("Kafka External");
    Log.startTestCase("TC_KAFKA_57_verifyBrokerFetchTotalTime");

    // Initialize all classes objects
    LOGGER.info("Initialize all class objects");
    KafkaPage kafkaPage = new KafkaPage(driver);
    KafkaPageObject kafkaPageObject = new KafkaPageObject(driver);

    kafkaPage.navigateToBrokerTab(kafkaPageObject, clusterId);
    kafkaPage.verifyKafkaKPIGraphs(kafkaPageObject,"Fetch Total Time, 99th Percentile",
        "kafkaGraph10");
    test.log(LogStatus.PASS, "Verified 'Fetch Total Time, 99th Percentile' details under broker tab for" +
        " selected broker successfully ");
  }
}
