package com.qa.testcases.cluster.kafka;

import com.qa.annotations.Marker;
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

@Marker.KafkaExternal
@Marker.All
public class TC_KAFKA_40 extends BaseClass {

  private static final Logger LOGGER = Logger.getLogger(com.qa.testcases.cluster.kafka.TC_KAFKA_40.class.getName());

  @Test(dataProvider = "clusterid-data-provider")
  public void TC_KAFKA_40_verifyBrokerTab(String clusterId) {
    test = extent.startTest("TC_KAFKA_40_verifyBrokerTab: " + clusterId,
        "Verify broker tab.");
    test.assignCategory("Kafka External");
    Log.startTestCase("TC_KAFKA_40_verifyBrokerTab");

    // Initialize all classes objects
    LOGGER.info("Initialize all class objects");
    KafkaPage kafkaPage = new KafkaPage(driver);
    KafkaPageObject kafkaPageObject = new KafkaPageObject(driver);

    // Navigate to Kafka tab and then to broker tab
    kafkaPage.navigateToBrokerTab(kafkaPageObject, clusterId);
    Assert.assertTrue(kafkaPageObject.componentDashboard.isDisplayed(),"The components in broker tab are " +
        "not displayed on the UI");
    test.log(LogStatus.PASS, "Verified broker tab successfully ");
  }
}
