package com.qa.testcases.cluster.queueanalysis;

import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.enums.UserAction;
import com.qa.pagefactory.clusters.QueueAnalysisPageObject;
import com.qa.scripts.clusters.QueueAnalysis;
import com.qa.utils.MouseActions;
import com.qa.utils.WaitExecuter;
import com.qa.utils.actions.UserActions;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.TimeoutException;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.logging.Logger;

@Marker.QueueAnalysis
@Marker.All
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
        UserActions actions = new UserActions(driver);
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
        actions.performActionWithPolling(qaPageObject.modalRunButton, UserAction.CLICK);
        waitExecuter.waitUntilElementClickable(qaPageObject.confirmationMessageElement);
        waitExecuter.waitUntilTextToBeInWebElement(qaPageObject.confirmationMessageElement,
                "Please wait while the report is being generated.");
        waitExecuter.sleep(4000);
        try {
            waitExecuter.waitUntilTextToBeInWebElement(qaPageObject.successBanner,
                    "SUCCESS");
            waitExecuter.waitUntilElementClickable(qaPageObject.clickOnQAReports);
            qaPageObject.clickOnQAReports.click();
            waitExecuter.waitUntilElementClickable(qaPageObject.select1stQAReport);
            qaPageObject.select1stQAReport.click();
            waitExecuter.waitUntilElementClickable(qaPageObject.queueSearchBox);
            // Sort down by queue name
            test.log(LogStatus.INFO, "Sort down by queue name");
            LOGGER.info("Sort down by queue name");
            waitExecuter.waitUntilElementClickable(qaPageObject.sortByQueueName);
            qaPageObject.sortByQueueName.click();
            waitExecuter.sleep(1000);
            Assert.assertTrue(qaPageObject.sortDown.isDisplayed(), "Sort down is not working");
            // Sort up by queue name
            test.log(LogStatus.INFO, "Sort down up queue name");
            LOGGER.info("Sort down up queue name");
            waitExecuter.waitUntilElementClickable(qaPageObject.sortByQueueName);
            qaPageObject.sortByQueueName.click();
            waitExecuter.sleep(1000);
            Assert.assertTrue(qaPageObject.sortUp.isDisplayed(), "Sort up is not working");
            test.log(LogStatus.PASS, "Verified sorting on queue name.");
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