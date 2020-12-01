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
public class TC_KAFKA_68 extends BaseClass {

  private static final Logger LOGGER = Logger.getLogger(com.qa.testcases.cluster.kafka.TC_KAFKA_68.class.getName());

  @Test(dataProvider = "clusterid-data-provider")
  public void TC_KAFKA_68_verifyTopicLatestMetricInfo(String clusterId) {
    test = extent.startTest("TC_KAFKA_68_verifyTopicLatestMetricInfo: " + clusterId,
        "Verify message like 'Latest metrics between 11/23/2020 22:15:13 and 11/30/2020 22:15:13' " +
            "before topic metrics.");
    test.assignCategory("Kafka External");
    Log.startTestCase("TC_KAFKA_68_verifyTopicLatestMetricInfo");

    // Initialize all classes objects
    LOGGER.info("Initialize all class objects");
    KafkaPage kafkaPage = new KafkaPage(driver);
    KafkaPageObject kafkaPageObject = new KafkaPageObject(driver);

    kafkaPage.navigateToTopicTab(kafkaPageObject);
    Assert.assertTrue(kafkaPageObject.latestMetricsInfo.isDisplayed(), "The latest metrics info msg is not displayed");
    test.log(LogStatus.PASS, "Verified the Topic metric info msg successfully ");
  }
}
