package com.qa.testcases.cluster.queueanalysis;

import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.pagefactory.clusters.QueueAnalysisPageObject;
import com.qa.scripts.clusters.QueueAnalysis;
import com.qa.utils.WaitExecuter;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.TimeoutException;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.logging.Logger;

@Marker.QueueAnalysis
@Marker.All
public class TC_QU_11 extends BaseClass {
    private static final Logger LOGGER = Logger.getLogger(TC_QU_11.class.getName());

    @Test
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
        queueAnalysis.runAQueueAnalysisReport();
        try {
            waitExecuter.waitUntilTextToBeInWebElement(qaPageObject.successBanner,
                    "SUCCESS");
            waitExecuter.waitUntilElementClickable(qaPageObject.clickOnQAReports);
            qaPageObject.clickOnQAReports.click();
            waitExecuter.waitUntilElementClickable(qaPageObject.select1stQAReport);
            qaPageObject.select1stQAReport.click();
            waitExecuter.waitUntilElementClickable(qaPageObject.queueSearchBox);
            // Sort down by Jobs running
            test.log(LogStatus.INFO, "Sort down by Jobs running");
            LOGGER.info("Sort down by Jobs running");
            waitExecuter.waitUntilElementClickable(qaPageObject.sortByMemory);
            // Sort down by Memory Allocated
            test.log(LogStatus.INFO, "Sort down by Memory Allocated");
            LOGGER.info("Sort down by Memory Allocated");
            qaPageObject.sortByMemory.click();
            waitExecuter.sleep(2000);
            Assert.assertTrue(qaPageObject.sortDown.isDisplayed(), "Sort down is not working");
            // Sort up by Memory Allocated
            test.log(LogStatus.INFO, "Sort up by Memory Allocated");
            LOGGER.info("Sort up by Memory Allocated");
            waitExecuter.waitUntilElementClickable(qaPageObject.sortByMemory);
            qaPageObject.sortByMemory.click();
            waitExecuter.sleep(2000);
            Assert.assertTrue(qaPageObject.sortUp.isDisplayed(), "Sort up is not working");
            test.log(LogStatus.PASS, "Verified sorting on Memory Allocated.");
        } catch (TimeoutException te) {
            throw new AssertionError("Queue Analysis Report not completed successfully.");
        }
        //Refresh the page and reload to original state
        test.log(LogStatus.INFO, "Refresh the page and reload to original state");
        LOGGER.info("Refresh the page and reload to original state");
        waitExecuter.sleep(1000);
        driver.navigate().refresh();
        waitExecuter.waitUntilElementClickable(qaPageObject.addIcon);
    }
}
