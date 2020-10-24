package com.qa.testcases.cluster.queueanalysis;

import com.qa.base.BaseClass;
import com.qa.pagefactory.DatePickerPageObject;
import com.qa.pagefactory.clusters.QueueAnalysisPageObject;
import com.qa.scripts.clusters.QueueAnalysis;
import com.qa.utils.WaitExecuter;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;
import java.util.logging.Logger;

/*
 * @author- Ojasvi Pandey
 */
public class TC_QU_02 extends BaseClass {
    private static final Logger LOGGER = Logger.getLogger(TC_QU_02.class.getName());

    @Test(dataProvider = "clusterid-data-provider")
    public void validateReportWithDatePickerOptions(String clusterId) {
        test = extent.startTest("TC_QU_02.validateReportWithDatePickerOptions",
                "Verify that Unravel UI should generate a Queue Analysis report successfully " +
                        "for all date picker option.");
        test.assignCategory("Jobs - Queue Analysis");
        test.log(LogStatus.INFO, "Login to the application");
        // Initialize all classes objects
        test.log(LogStatus.INFO, "Initialize all class objects");
        LOGGER.info("Initialize all class objects");
        WaitExecuter waitExecuter = new WaitExecuter(driver);
        DatePickerPageObject datePickerPageObject = new DatePickerPageObject(driver);
        QueueAnalysisPageObject qaPageObject = new QueueAnalysisPageObject(driver);
        QueueAnalysis queueAnalysis = new QueueAnalysis(driver);
        // Navigate to Queue Analysis tab from header
        test.log(LogStatus.INFO, "Navigate to Queue Analysis tab from header");
        test.log(LogStatus.INFO, "Clicked on Queue Analysis tab");
        test.log(LogStatus.INFO, "Validate Queue Analysis tab loaded successfully");
        queueAnalysis.navigateToQueueAnalysis();
        // Close confirmation box
        test.log(LogStatus.INFO, "Closed the confirmation box");
        LOGGER.info("Closed the confirmation box");
        queueAnalysis.closeConfirmationMessageNotification();
        waitExecuter.sleep(1000);
        //Click on Run button to open report page
        test.log(LogStatus.INFO, "Click on Run button to open report page");
        LOGGER.info("Click on Run button to open report page");
        qaPageObject.runButton.click();
        waitExecuter.sleep(1000);
        //Select ClusterId
        test.log(LogStatus.INFO, "Selecting ClusterId: " + clusterId);
        LOGGER.info("Selecting ClusterId: " + clusterId);
        queueAnalysis.selectMultiClusterId(clusterId);
        //Click on date range
        test.log(LogStatus.INFO, "Click on date range");
        LOGGER.info("Click on date range");
        datePickerPageObject.dateRange.click();
        List<WebElement> dateRange = datePickerPageObject.dateRangeOptions;
        for (int i = 0; i < datePickerPageObject.dateRangeOptions.size() - 1; i++) {
            WebElement dateRangeOption = datePickerPageObject.dateRangeOptions.get(i);
            String dateRangeValue = dateRangeOption.getText();
            dateRangeOption.click();
            //Click on Run button of modal window
            test.log(LogStatus.INFO, "Click on Run button of modal window");
            LOGGER.info("Click on Run button of modal window");
            qaPageObject.modalRunButton.click();
            if (dateRangeValue.equals("This Month") || dateRangeValue.equals("Last Month")
                    || dateRangeValue.equals("Last 7 Days") || dateRangeValue.equals("Last 30 Days")) {
                try {
                    waitExecuter.waitUntilTextToBeInWebElement(qaPageObject.confirmationMessageElement,
                            "Queue Analysis completed successfully.");
                    test.log(LogStatus.PASS, "Verified Queue Analysis report is loaded properly.");
                    waitExecuter.sleep(3000);
                    waitExecuter.waitUntilElementPresent(qaPageObject.runButton);
                    waitExecuter.waitUntilElementClickable(qaPageObject.runButton);
                } catch (TimeoutException te) {
                    throw new AssertionError("Queue Analysis Report not completed successfully.");
                }
            } else if (dateRangeValue.equals("This Year") || dateRangeValue.equals("Last 90 Days")
                    || dateRangeValue.equals("Last 60 Days")) {
                Assert.assertEquals(qaPageObject.invalidInputMessage.getText().trim(),
                        "Please, Make sure valid inputs.", "Banner message display an in-correct message as "
                                + qaPageObject.invalidInputMessage.getText());
                test.log(LogStatus.PASS, "Verified Queue Analysis report is loaded properly.");
            }
        }
    }
}
