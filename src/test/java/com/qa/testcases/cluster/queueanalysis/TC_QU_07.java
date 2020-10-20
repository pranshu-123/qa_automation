package com.qa.testcases.cluster.queueanalysis;

import com.qa.base.BaseClass;
import com.qa.pagefactory.TopPanelPageObject;
import com.qa.pagefactory.clusters.QueueAnalysisPageObject;
import com.qa.scripts.clusters.QueueAnalysis;
import com.qa.utils.WaitExecuter;
import com.relevantcodes.extentreports.LogStatus;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.logging.Logger;

public class TC_QU_07 extends BaseClass {
    private static final Logger LOGGER = Logger.getLogger(TC_QU_07.class.getName());

    @Test(dataProvider = "clusterid-data-provider")
    public void validateSendingInvalidQueueName(String clusterId) {
        test = extent.startTest("TC_QU_07.validateSendingInvalidQueueName",
                "Verify on sending invalid queue name a proper message is displayed");
        test.assignCategory("Jobs - Queue Analysis");
        test.log(LogStatus.INFO, "Login to the application");
        // Initialize all classes objects
        test.log(LogStatus.INFO, "Initialize all class objects");
        LOGGER.info("Initialize all class objects");
        WaitExecuter waitExecuter = new WaitExecuter(driver);
        TopPanelPageObject topPanelPageObject = new TopPanelPageObject(driver);
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
        qaPageObject.queueSearchBox.sendKeys("InvalidQueue");
        String noResult = qaPageObject.queueOptions.get(0).getText();
        Assert.assertTrue(noResult.equals("No results found"),
                "On giving invalid queue name, expected message is not displayed");
        test.log(LogStatus.PASS, "Verified the message on assigning invalid queue name.");
    }
}
