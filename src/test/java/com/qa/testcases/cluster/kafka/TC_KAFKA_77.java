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
public class TC_KAFKA_77 extends BaseClass {

  private static final Logger LOGGER = Logger.getLogger(com.qa.testcases.cluster.kafka.TC_KAFKA_77.class.getName());

  @Test(dataProvider = "clusterid-data-provider")
  public void TC_KAFKA_77_verifyTopTotalFetchRequests(String clusterId) {
    test = extent.startTest("TC_KAFKA_77_verifyTopTotalFetchRequests: " + clusterId,
        "Verify 'Total Fetch Requests per Second' details under topic tab for selected topic.");
    test.assignCategory("Kafka External");
    Log.startTestCase("TC_KAFKA_77_verifyTopTotalFetchRequests");

    // Initialize all classes objects
    LOGGER.info("Initialize all class objects");
    KafkaPage kafkaPage = new KafkaPage(driver);
    KafkaPageObject kafkaPageObject = new KafkaPageObject(driver);

    kafkaPage.navigateToTopicTab(kafkaPageObject);
    kafkaPage.verifyKafkaKPIGraphs(kafkaPageObject, "Total Fetch Requests per Second",
        "kafkaGraph3");
    test.log(LogStatus.PASS, "Verified 'Total Fetch Requests per Second' details under topic tab for" +
        " selected topic successfully ");
  }
}
