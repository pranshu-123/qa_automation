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
public class TC_KAFKA_45 extends BaseClass {

  private static final Logger LOGGER = Logger.getLogger(com.qa.testcases.cluster.kafka.TC_KAFKA_44.class.getName());

  @Test(dataProvider = "clusterid-data-provider")
  public void verifySortingForBrokerMerticColumns(String clusterId) {
    test = extent.startTest("verifySortingForBrokerMerticColumns: " + clusterId,
        "Verify sorting for all columns in broker metrics table.");
    test.assignCategory("Kafka External");
    Log.startTestCase("verifySortingForBrokerMerticColumns");

    // Initialize all classes objects
    LOGGER.info("Initialize all class objects");
    KafkaPage kafkaPage = new KafkaPage(driver);
    KafkaPageObject kafkaPageObject = new KafkaPageObject(driver);

    kafkaPage.navigateToBrokerTab(kafkaPageObject, clusterId);
    kafkaPage.verifyBrokerMetricsColSort(kafkaPageObject);
    test.log(LogStatus.PASS, "Verified sorting for all columns in broker metrics table successfully ");
  }
}
