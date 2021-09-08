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
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Marker.QueueAnalysis
@Marker.All
public class TC_QU_05 extends BaseClass {
    private static final Logger LOGGER = Logger.getLogger(TC_QU_05.class.getName());

    @Test(dataProvider = "clusterid-data-provider")
    public void validateMultipleQueueNameFilter(String clusterId) {
        test = extent.startTest("TC_QU_05.validateMultipleQueueNameFilter",
                "The queue anaysis table should display the data for the selected queue. ");
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
        waitExecuter.sleep(3000);
        //Click on Run button of modal window
        test.log(LogStatus.INFO, "Click on Run button of modal window");
        LOGGER.info("Click on Run button of modal window");
        actions.performActionWithPolling(qaPageObject.modalRunButton, UserAction.CLICK);
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
            List<String> selectedQueueList = new ArrayList<>();
            List<String> queueNameListFromTable = new ArrayList<>();
            if (qaPageObject.queueOptions.size() > 0) {
                int numberOfQueue = qaPageObject.queueOptions.size();
                if (numberOfQueue > 5) {
                    for (int i = 0; i < 5; i++) {
                        String selectedQueueName = qaPageObject.queueOptions.get(i).getText();
                        LOGGER.info("Selected Queue: " + selectedQueueName);
                        selectedQueueList.add(selectedQueueName.trim().toLowerCase());
                        qaPageObject.queueOptions.get(i).click();
                        waitExecuter.sleep(1000);
                        qaPageObject.queueSearchBox.click();
                        waitExecuter.sleep(1000);
                    }
                    LOGGER.info("Selected queue names: " + selectedQueueList);
                } else if (numberOfQueue < 5) {
                    for (int i = 0; i < qaPageObject.queueOptions.size(); i++) {
                        String selectedQueueName = qaPageObject.queueOptions.get(i).getText();
                        LOGGER.info("Selected Queue: " + selectedQueueName);
                        selectedQueueList.add(selectedQueueName.trim().toLowerCase());
                        qaPageObject.queueOptions.get(i).click();
                        waitExecuter.sleep(1000);
                        qaPageObject.queueSearchBox.click();
                        waitExecuter.sleep(1000);
                    }
                    LOGGER.info("Selected queue names: " + selectedQueueList);
                    test.log(LogStatus.INFO, "Selected queue names: " + selectedQueueList);
                }
                for (WebElement queueName : qaPageObject.getQueueNameFromTable) {
                    queueNameListFromTable.add(queueName.getText().trim().toLowerCase());
                    LOGGER.info("Queue Name from Table: " + queueName.getText());
                }
                LOGGER.info("Queue Name List from Table: " + queueNameListFromTable);
                test.log(LogStatus.INFO, "Queue Name List from Table: " + queueNameListFromTable);
                Assert.assertTrue(queueNameListFromTable.containsAll(selectedQueueList),
                        "The table contains data of queue name other than that of filtered queue ");
                test.log(LogStatus.PASS, "Verified Queue filtered.");
            } else
                Assert.assertNull(qaPageObject.getQueueNameFromTable.get(0).getText(),
                        "The dropdown does not show any queue, but table contains rows");
        }catch (TimeoutException te) {
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
