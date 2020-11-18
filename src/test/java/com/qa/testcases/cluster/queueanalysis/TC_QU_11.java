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
public class TC_QU_11 extends BaseClass {
    private static final Logger LOGGER = Logger.getLogger(TC_QU_11.class.getName());

    @Test(dataProvider = "clusterid-data-provider")
    public void validateSortByMemoryAllocated() {
        test = extent.startTest("TC_QU_11.validateSortByVcoresAllocated",
                "Verify sorting up and down in Memory Allocated");
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
        waitExecuter.sleep(2000);
        // Sort down by Memory Allocated
        test.log(LogStatus.INFO, "Sort down by Memory Allocated");
        LOGGER.info("Sort down by Memory Allocated");
        qaPageObject.sortByMemory.click();
        waitExecuter.sleep(2000);
        Assert.assertTrue(qaPageObject.sortDown.isDisplayed(), "Sort down is not working");
        // Sort up by Memory Allocated
        test.log(LogStatus.INFO, "Sort up by Memory Allocated");
        LOGGER.info("Sort up by Memory Allocated");
        qaPageObject.sortByMemory.click();
        waitExecuter.sleep(2000);
        Assert.assertTrue(qaPageObject.sortUp.isDisplayed(), "Sort up is not working");
        test.log(LogStatus.PASS, "Verified sorting on Memory Allocated.");
        //Refresh the page and reload to original state
        test.log(LogStatus.INFO, "Refresh the page and reload to original state");
        LOGGER.info("Refresh the page and reload to original state");
        waitExecuter.sleep(1000);
        driver.navigate().refresh();
        waitExecuter.sleep(3000);
    }
}
