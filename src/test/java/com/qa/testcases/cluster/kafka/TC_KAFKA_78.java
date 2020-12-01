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
public class TC_KAFKA_78 extends BaseClass {

  private static final Logger LOGGER = Logger.getLogger(com.qa.testcases.cluster.kafka.TC_KAFKA_78.class.getName());

  @Test(dataProvider = "clusterid-data-provider")
  public void TC_KAFKA_78_verifyTopicAvgInfoMsg(String clusterId) {
    test = extent.startTest("TC_KAFKA_78_verifyTopicAvgInfoMsg: " + clusterId,
        "Verify msg like 'Average metrics between 11/23/2020 23:44:08 and 11/30/2020 23:44:08' " +
            "is displayed before topic metrics.");
    test.assignCategory("Kafka External");
    Log.startTestCase("TC_KAFKA_78_verifyTopicAvgInfoMsg");

    // Initialize all classes objects
    LOGGER.info("Initialize all class objects");
    KafkaPage kafkaPage = new KafkaPage(driver);
    KafkaPageObject kafkaPageObject = new KafkaPageObject(driver);

    kafkaPage.navigateToTopicTab(kafkaPageObject);
    kafkaPage.verifyMetricsInfoMsg(kafkaPageObject);
    test.log(LogStatus.PASS, "Verified Average metrics info msg successfully ");
  }
}
