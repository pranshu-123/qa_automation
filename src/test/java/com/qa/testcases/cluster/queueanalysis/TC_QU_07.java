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
public class TC_QU_07 extends BaseClass {
    private static final Logger LOGGER = Logger.getLogger(TC_QU_07.class.getName());

    @Test
    public void validateSendingInvalidQueueName() {
        test = extent.startTest("TC_QU_07.validateSendingInvalidQueueName",
                "Verify on sending invalid queue name a proper message is displayed");
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
        waitExecuter.waitUntilElementClickable(qaPageObject.addIcon);
        qaPageObject.addIcon.click();
        waitExecuter.waitUntilElementClickable(qaPageObject.modalRunButton);
        waitExecuter.sleep(1000);
        //Click on Run button of modal window
        test.log(LogStatus.INFO, "Click on Run button of modal window");
        LOGGER.info("Click on Run button of modal window");
        waitExecuter.waitUntilElementClickable(qaPageObject.modalRunButton);
        qaPageObject.modalRunButton.click();
        waitExecuter.waitUntilTextNotToBeInWebElement(qaPageObject.footerWaitCycle, "Please Wait");
        waitExecuter.waitUntilElementClickable(qaPageObject.addIcon);
        try {
            waitExecuter.waitUntilTextToBeInWebElement(qaPageObject.successBanner,
                    "SUCCESS");
            waitExecuter.waitUntilElementClickable(qaPageObject.clickOnQAReports);
            qaPageObject.clickOnQAReports.click();
            waitExecuter.waitUntilElementClickable(qaPageObject.select1stQAReport);
            qaPageObject.select1stQAReport.click();
            waitExecuter.waitUntilElementClickable(qaPageObject.queueSearchBox);
            qaPageObject.queueSearchBox.click();
            // Click on queue search box and search for queue name
            test.log(LogStatus.INFO, "Click on queue search box and search for queue name");
            LOGGER.info("Click on queue search box and search for queue name");
            qaPageObject.queueSearchBox.sendKeys("Invalid Queue Name");
            String noResult = qaPageObject.queueOptions.get(0).getText();
            Assert.assertTrue(noResult.equals("No results found"),
                    "On giving invalid queue name, expected message is not displayed");
            test.log(LogStatus.PASS, "Verified the message on assigning invalid queue name.");

        } catch (TimeoutException te) {
            throw new AssertionError("Queue Analysis Report not completed successfully.");
        }

    }
}
