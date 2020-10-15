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

public class TC_QU_08 extends BaseClass {
    private static final Logger LOGGER = Logger.getLogger(TC_QU_08.class.getName());

    @Test(dataProvider = "clusterid-data-provider")
    public void validateSortByQueueName(String clusterId) {
        test = extent.startTest("TC_QU_08.validateSortByQueueName",
                "Verify sorting up and down in queue name");
        test.assignCategory("Jobs - Queue Analysis");
        test.log(LogStatus.INFO, "Login to the application");
        // Initialize all classes objects
        test.log(LogStatus.INFO, "Initialize all class objects");
        LOGGER.info("Initialize all class objects");
        WaitExecuter waitExecuter = new WaitExecuter(driver);
        TopPanelPageObject topPanelPageObject = new TopPanelPageObject(driver);
        QueueAnalysisPageObject qaPageObject = new QueueAnalysisPageObject(driver);
        QueueAnalysis queueAnalysis = new QueueAnalysis(driver);
        // Navigate to  queue analysis  tab from header
        test.log(LogStatus.INFO, "Navigate to queue analysis tab from header");
        LOGGER.info("Navigate to  queue analysis  tab from header");
        waitExecuter.waitUntilElementClickable(topPanelPageObject.queueAnalysisTab);
        waitExecuter.sleep(1000);
        //Click on Queue Analysis tab
        test.log(LogStatus.INFO, "Clicked on Queue Analysis tab");
        LOGGER.info("Clicked on Queue Analysis tab");
        topPanelPageObject.queueAnalysisTab.click();
        waitExecuter.sleep(3000);
        //Validate Queue Analysis tab loaded successfully
        test.log(LogStatus.INFO, "Validate Queue Analysis tab loaded successfully");
        LOGGER.info("Validate Queue Analysis tab loaded successfully");
        waitExecuter.waitUntilElementPresent(qaPageObject.queueAnalysisHeading);
        waitExecuter.waitUntilPageFullyLoaded();
        // Close confirmation message box and search the queue
        test.log(LogStatus.INFO, "Close confirmation message box and search the queue");
        LOGGER.info("Close confirmation message box and search the queue");
        queueAnalysis.closeConfirmationMessageNotification();
        waitExecuter.sleep(1000);
        // Sort down by queue name
        test.log(LogStatus.INFO, "Sort down by queue name");
        LOGGER.info("Sort down by queue name");
        qaPageObject.sortByQueueName.click();
        waitExecuter.sleep(1000);
        Assert.assertTrue(qaPageObject.sortDown.isDisplayed(), "Sort down is not working");
        // Sort up by queue name
        test.log(LogStatus.INFO, "Sort down up queue name");
        LOGGER.info("Sort down up queue name");
        qaPageObject.sortByQueueName.click();
        waitExecuter.sleep(1000);
        Assert.assertTrue(qaPageObject.sortUp.isDisplayed(), "Sort up is not working");
        test.log(LogStatus.PASS, "Verified sorting on queue name.");
        //Refresh the page and reload to original state
        test.log(LogStatus.INFO, "Refresh the page and reload to original state");
        LOGGER.info("Refresh the page and reload to original state");
        waitExecuter.sleep(1000);
        driver.navigate().refresh();
        waitExecuter.sleep(3000);
    }
}