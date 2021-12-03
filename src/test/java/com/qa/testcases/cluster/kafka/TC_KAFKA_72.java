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
public class TC_KAFKA_72 extends BaseClass {

	
  private static final Logger LOGGER = Logger.getLogger(com.qa.testcases.cluster.kafka.TC_KAFKA_72.class.getName());

  @Test(dataProvider = "clusterid-data-provider",description="Verify sorting for all columns in topic metrics table")
  public void TC_KAFKA_72_verifySortingForTopicMerticColumns(String clusterId) {
    test = extent.startTest("TC_KAFKA_72_verifyTopicMetrics: " + clusterId,
            "Verify sorting for all columns in topic metrics table");
    test.assignCategory("Kafka External");
    Log.startTestCase("TC_KAFKA_72_verifySortingForTopicMerticColumns");

    // Initialize all classes objects
    LOGGER.info("Initialize all class objects");
    KafkaPage kafkaPage = new KafkaPage(driver);
    KafkaPageObject kafkaPageObject = new KafkaPageObject(driver);

    kafkaPage.navigateToTopicTab(kafkaPageObject, clusterId);
    kafkaPage.verifyTopicMetrics(kafkaPageObject);
    kafkaPage.verifyTopicMetricsColSort(kafkaPageObject);
    test.log(LogStatus.PASS, "Verified topic records with all details in topic metrics successfully ");
    }
}

