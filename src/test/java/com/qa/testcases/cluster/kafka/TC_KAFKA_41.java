package com.qa.testcases.cluster.kafka;

import com.qa.base.BaseClass;
import com.qa.pagefactory.clusters.KafkaPageObject;
import com.qa.scripts.clusters.kafka.KafkaPage;
import com.qa.utils.Log;
import com.qa.utils.MouseActions;
import com.qa.utils.WaitExecuter;
import com.relevantcodes.extentreports.LogStatus;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.logging.Logger;

public class TC_KAFKA_41 extends BaseClass {

  private static final Logger LOGGER = Logger.getLogger(com.qa.testcases.cluster.kafka.TC_KAFKA_40.class.getName());

  @Test(dataProvider = "clusterid-data-provider")
  public void TC_KAFKA_40_verifyLatestInfoMsg(String clusterId) {
    test = extent.startTest("TC_KAFKA_40_verifyLatestInfoMsg: " + clusterId,
        "Verify this info 'Latest metrics between 11/28/2020 00:00:00 and 11/28/2020 22:12:54' info " +
            "before broker metrics.");
    test.assignCategory("Kafka External");
    Log.startTestCase("TC_KAFKA_40_verifyLatestInfoMsg");

    // Initialize all classes objects
    test.log(LogStatus.INFO, "Initialize all class objects");
    LOGGER.info("Initialize all class objects");
    KafkaPage kafkaPage = new KafkaPage(driver);
    KafkaPageObject kafkaPageObject = new KafkaPageObject(driver);

    kafkaPage.navigateToBrokerTab(kafkaPageObject);
    Assert.assertTrue(kafkaPageObject.latestMetricsInfo.isDisplayed(),"The latest metrics info message is " +
        "not displayed on the UI");
    test.log(LogStatus.PASS, "Verified latest metrics info message successfully ");
  }
}
