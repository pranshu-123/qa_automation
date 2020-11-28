package com.qa.testcases.cluster.kafka;

import com.qa.base.BaseClass;
import com.qa.pagefactory.clusters.KafkaPageObject;
import com.qa.scripts.clusters.kafka.KafkaPage;
import com.qa.utils.Log;
import com.qa.utils.MouseActions;
import com.qa.utils.WaitExecuter;
import com.relevantcodes.extentreports.LogStatus;
import org.testng.annotations.Test;

import java.util.logging.Logger;

public class TC_KAFKA_36 extends BaseClass {

  private static final Logger LOGGER = Logger.getLogger(com.qa.testcases.cluster.kafka.TC_KAFKA_36.class.getName());

  @Test(dataProvider = "clusterid-data-provider")
  public void TC_KAFKA_36_verifyMetricsInfoMsg(String clusterId) {
    test = extent.startTest("TC_KAFKA_36_verifyMetricsTitle: " + clusterId,
        "Verify info message like this 'Average metrics between 11/28/2020 00:00:00 and 11/28/2020 10:49:54'" +
            " before topics metrics.");
    test.assignCategory("Kafka External");
    Log.startTestCase("TC_KAFKA_36_verifyMetricsTitle");

    // Initialize all classes objects
    test.log(LogStatus.INFO, "Initialize all class objects");
    LOGGER.info("Initialize all class objects");
    KafkaPage kafkaPage = new KafkaPage(driver);
    KafkaPageObject kafkaPageObject = new KafkaPageObject(driver);
    WaitExecuter waitExecuter = new WaitExecuter(driver);

    // Navigate to Kafka tab
    MouseActions.clickOnElement(driver, kafkaPageObject.kafkaTab);
    waitExecuter.waitUntilPageFullyLoaded();
    waitExecuter.sleep(2000);
    kafkaPage.verifyClusterDropDown(kafkaPageObject);
    kafkaPage.verifyMetricsInfoMsg(kafkaPageObject);
    test.log(LogStatus.PASS, "Verified the metrics info message successfully ");
  }
}
