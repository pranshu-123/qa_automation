package com.qa.testcases.cluster.kafka;

import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.pagefactory.clusters.KafkaPageObject;
import com.qa.scripts.clusters.kafka.KafkaPage;
import com.qa.utils.Log;
import com.qa.utils.MouseActions;
import com.qa.utils.WaitExecuter;
import com.relevantcodes.extentreports.LogStatus;
import org.testng.annotations.Test;

import java.util.logging.Logger;
@Marker.KafkaExternal
@Marker.All
public class TC_KAFKA_39 extends BaseClass {

    private static final Logger LOGGER = Logger.getLogger(com.qa.testcases.cluster.kafka.TC_KAFKA_39.class.getName());

    @Test(dataProvider = "clusterid-data-provider",description="Verify pagination for topic metrics.")
    public void TC_KAFKA_39_verifyTopicPagination(String clusterId) {
        test = extent.startTest("TC_KAFKA_39_verifyTopicPagination: " + clusterId,
                "Verify pagination for topic metrics.");
        test.assignCategory("Kafka External");
        Log.startTestCase("TC_KAFKA_39_verifyTopicPagination");

        // Initialize all classes objects
        LOGGER.info("Initialize all class objects");
        KafkaPage kafkaPage = new KafkaPage(driver);
        KafkaPageObject kafkaPageObject = new KafkaPageObject(driver);
        WaitExecuter waitExecuter = new WaitExecuter(driver);

        // Navigate to Kafka tab
        MouseActions.clickOnElement(driver, kafkaPageObject.kafkaTab);
        waitExecuter.waitUntilPageFullyLoaded();
        waitExecuter.sleep(2000);
        kafkaPage.verifyClusterDropDown(kafkaPageObject, clusterId);
        kafkaPage.navigateToTopicTab(kafkaPageObject, clusterId);
        kafkaPage.verifyLatestMetricsTopicPagination(kafkaPageObject,test);
        test.log(LogStatus.PASS, "Verified topic records with all details successfully ");
    }
}
