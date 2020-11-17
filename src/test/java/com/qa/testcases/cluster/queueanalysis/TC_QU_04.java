package com.qa.testcases.cluster.queueanalysis;

import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.pagefactory.clusters.QueueAnalysisPageObject;
import com.qa.scripts.clusters.QueueAnalysis;
import com.qa.utils.WaitExecuter;
import com.relevantcodes.extentreports.LogStatus;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.logging.Logger;

@Marker.QueueAnalysis
@Marker.All
public class TC_QU_04 extends BaseClass {
    private static final Logger LOGGER = Logger.getLogger(TC_QU_04.class.getName());

    @Test(dataProvider = "clusterid-data-provider")
    public void validateQueueNameFilter(String clusterId) {
        test = extent.startTest("TC_QU_04.validateQueueNameFilter",
                "The queue anaysis table should display the data for the selected queue. ");
        test.assignCategory("Jobs - Queue Analysis");
        test.log(LogStatus.INFO, "Login to the application");
        // Initialize all classes objects
        test.log(LogStatus.INFO, "Initialize all class objects");
        LOGGER.info("Initialize all class objects");
        WaitExecuter waitExecuter = new WaitExecuter(driver);
        QueueAnalysisPageObject qaPageObject = new QueueAnalysisPageObject(driver);
        QueueAnalysis queueAnalysis = new QueueAnalysis(driver);
        // Navigate to Queue Analysis tab from header
        test.log(LogStatus.INFO, "Navigate to Queue Analysis tab from header");
        test.log(LogStatus.INFO, "Clicked on Queue Analysis tab");
        test.log(LogStatus.INFO, "Validate Queue Analysis tab loaded successfully");
        queueAnalysis.navigateToQueueAnalysis();
        // Close confirmation message box and search the queue
        test.log(LogStatus.INFO, "Close confirmation message box and search the queue");
        LOGGER.info("Close confirmation message box and search the queue");
        queueAnalysis.closeConfirmationMessageNotification();
        waitExecuter.sleep(1000);
        // Click on queue search box and search for queue name
        test.log(LogStatus.INFO, "Click on queue search box and search for queue name");
        LOGGER.info("Click on queue search box and search for queue name");
        qaPageObject.queueSearchBox.click();
        if (qaPageObject.queueOptions.size() > 0) {
            String selectedQueueName = qaPageObject.queueOptions.get(0).getText();
            qaPageObject.queueOptions.get(0).click();
            waitExecuter.sleep(1000);
            Assert.assertTrue(selectedQueueName.equals(qaPageObject.getQueueNameFromTable.get(0).getText()),
                    "The table contains data of queue name other than that of filtered queue "
                            + qaPageObject.getQueueNameFromTable.get(0).getText());
            test.log(LogStatus.PASS, "Verified Queue filtered.");
        } else
            Assert.assertNull(qaPageObject.getQueueNameFromTable.get(0).getText(),
                    "The dropdown does not show any queue, but table contains rows");
        //Refresh the page and reload to original state
        test.log(LogStatus.INFO, "Refresh the page and reload to original state");
        LOGGER.info("Refresh the page and reload to original state");
        driver.navigate().refresh();
        waitExecuter.sleep(3000);
    }
}
