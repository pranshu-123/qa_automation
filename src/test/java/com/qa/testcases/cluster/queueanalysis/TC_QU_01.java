package com.qa.testcases.cluster.queueanalysis;

import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.enums.UserAction;
import com.qa.pagefactory.clusters.QueueAnalysisPageObject;
import com.qa.scripts.clusters.QueueAnalysis;
import com.qa.utils.WaitExecuter;
import com.qa.utils.actions.UserActions;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.TimeoutException;
import org.testng.annotations.Test;

import java.util.logging.Logger;

/*
 * @author- Ojasvi Pandey
 */

@Marker.QueueAnalysis
@Marker.All
public class TC_QU_01 extends BaseClass {
    private static final Logger LOGGER = Logger.getLogger(TC_QU_01.class.getName());

    @Test(dataProvider = "clusterid-data-provider", description = "Verify that Unravel UI should generate a Queue Analysis report successfully for 7 days.")
    public void validateReportExecutionOfSevenDays(String clusterId) {
        test = extent.startTest("TC_QU_01.validateReportExecutionOfSevenDays",
                "Verify that Unravel UI should generate a Queue Analysis report successfully for 7 days.");
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
        test.log(LogStatus.INFO, "Navigate to Report tab from header");
        test.log(LogStatus.INFO, "Clicked on Queue Analysis Add icon");
        queueAnalysis.navigateToQueueAnalysis();
        waitExecuter.sleep(1000);
        waitExecuter.waitUntilElementClickable(qaPageObject.addIcon);
        qaPageObject.addIcon.click();
        waitExecuter.waitUntilElementClickable(qaPageObject.modalRunButton);
        waitExecuter.sleep(1000);
        //Select ClusterId
        test.log(LogStatus.INFO, "Selecting ClusterId: " + clusterId);
        LOGGER.info("Selecting ClusterId: " + clusterId);
        queueAnalysis.selectMultiClusterId(clusterId);
        //Click on Run button of modal window
        test.log(LogStatus.INFO, "Click on Run button of modal window");
        LOGGER.info("Click on Run button of modal window");
        waitExecuter.waitUntilElementClickable(qaPageObject.modalRunButton);
        actions.performActionWithPolling(qaPageObject.modalRunButton, UserAction.CLICK);
        waitExecuter.sleep(2000);
        waitExecuter.waitUntilElementClickable(qaPageObject.confirmationMessageElement);
        waitExecuter.waitUntilTextToBeInWebElement(qaPageObject.confirmationMessageElement,
                "Please wait while the report is being generated.");
        waitExecuter.sleep(4000);
        try {
            waitExecuter.waitUntilTextToBeInWebElement(qaPageObject.successBanner,
                    "SUCCESS");
            test.log(LogStatus.PASS, "Verified Queue Analysis report is loaded properly.");
        } catch (TimeoutException te) {
            throw new AssertionError("Queue Analysis Report not completed successfully.");
        }
    }
}
