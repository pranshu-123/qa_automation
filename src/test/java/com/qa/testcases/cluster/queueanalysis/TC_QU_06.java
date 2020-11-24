package com.qa.testcases.cluster.queueanalysis;

import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.pagefactory.clusters.QueueAnalysisPageObject;
import com.qa.scripts.clusters.QueueAnalysis;
import com.qa.utils.WaitExecuter;
import com.relevantcodes.extentreports.LogStatus;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Marker.QueueAnalysis
@Marker.All
public class TC_QU_06 extends BaseClass {
    private static final Logger LOGGER = Logger.getLogger(TC_QU_06.class.getName());

    @Test
    public void validateSixthQueueAdditionInSearchBox() {
        test = extent.startTest("TC_QU_06.validateSixthQueueAdditionInSearchBox",
                "The UI should not allow the user to enter sixth queue and should display the message - \"You can only select 5 queues\"");
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
        List<String> selectedQueueList = new ArrayList<>();
        if (qaPageObject.queueOptions.size() > 0) {
            int numberOfQueue = qaPageObject.queueOptions.size();
            if (numberOfQueue >= 5) {
                for (int i = 0; i < 5; i++) {
                    String selectedQueueName = qaPageObject.queueOptions.get(i).getText();
                    LOGGER.info("Selected Queue: " + selectedQueueName);
                    selectedQueueList.add(selectedQueueName.trim().toLowerCase());
                    qaPageObject.queueOptions.get(i).click();
                    waitExecuter.sleep(1000);
                    qaPageObject.queueSearchBox.click();
                }
                selectedQueueList.add(qaPageObject.queueOptions.get(0).getText().trim());
                LOGGER.info("Selected queue names: " + selectedQueueList);
                test.log(LogStatus.INFO, "Selected queue names: " + selectedQueueList);
                Assert.assertTrue(selectedQueueList.contains("You can only select 5 Queues"),
                        "On selecting 6th queue in search box expected message is not displayed");
                test.log(LogStatus.PASS, "Verified the message on selecting 6th queue.");
            }
            if(numberOfQueue < 5 && numberOfQueue > 0){
                Assert.assertTrue(numberOfQueue < 5, "Number of queue is less than 5");
                LOGGER.info("Queue names in list is less than 5 thus cannot validate test case");
                test.log(LogStatus.SKIP, "Queue names in list is less than 5 thus cannot validate test case");
            }
        } else
            Assert.assertNull(qaPageObject.getQueueNameFromTable.get(0).getText(),
                    "The dropdown does not show any queue, but table contains rows");
        //Refresh the page and reload to original state
        test.log(LogStatus.INFO, "Refresh the page and reload to original state");
        LOGGER.info("Refresh the page and reload to original state");
        waitExecuter.sleep(1000);
        driver.navigate().refresh();
        waitExecuter.sleep(3000);
    }
}