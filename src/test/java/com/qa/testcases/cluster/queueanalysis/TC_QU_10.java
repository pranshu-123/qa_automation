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

public class TC_QU_10 extends BaseClass {
    private static final Logger LOGGER = Logger.getLogger(TC_QU_10.class.getName());

    @Test(dataProvider = "clusterid-data-provider")
    public void validateSortByVcoresAllocated() {
        test = extent.startTest("TC_QU_10.validateSortByVcoresAllocated",
                "Verify sorting up and down in Vcores Allocated");
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
        // Sort down by Vcores Allocated
        test.log(LogStatus.INFO, "Sort down by Vcores Allocated");
        LOGGER.info("Sort down by Vcores Allocated");
        qaPageObject.sortByVcore.click();
        waitExecuter.sleep(1000);
        Assert.assertTrue(qaPageObject.sortDown.isDisplayed(), "Sort down is not working");
        // Sort up by Vcores Allocated
        test.log(LogStatus.INFO, "Sort up by Vcores Allocated");
        LOGGER.info("Sort up by Vcores Allocated");
        qaPageObject.sortByVcore.click();
        waitExecuter.sleep(1000);
        Assert.assertTrue(qaPageObject.sortUp.isDisplayed(), "Sort up is not working");
        test.log(LogStatus.PASS, "Verified sorting on Vcore Allocated.");
        //Refresh the page and reload to original state
        test.log(LogStatus.INFO, "Refresh the page and reload to original state");
        LOGGER.info("Refresh the page and reload to original state");
        waitExecuter.sleep(1000);
        driver.navigate().refresh();
        waitExecuter.sleep(3000);
    }
}
