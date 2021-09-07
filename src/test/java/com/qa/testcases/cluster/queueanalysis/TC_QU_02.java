package com.qa.testcases.cluster.queueanalysis;

import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.enums.UserAction;
import com.qa.pagefactory.DatePickerPageObject;
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

import java.util.List;
import java.util.logging.Logger;

/*
 * @author- Ojasvi Pandey
 */
@Marker.QueueAnalysis
@Marker.All
public class TC_QU_02 extends BaseClass {
    private static final Logger LOGGER = Logger.getLogger(TC_QU_02.class.getName());

    @Test(dataProvider = "clusterid-data-provider", description = "Verify that Unravel UI should generate a Queue Analysis report successfully " +
            "for all date picker option.")
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
        UserActions actions = new UserActions(driver);
        DatePickerPageObject datePickerPageObject = new DatePickerPageObject(driver);
        QueueAnalysisPageObject qaPageObject = new QueueAnalysisPageObject(driver);
        QueueAnalysis queueAnalysis = new QueueAnalysis(driver);
        // Navigate to Queue Analysis tab from header
        test.log(LogStatus.INFO, "Navigate to Report tab from header");
        test.log(LogStatus.INFO, "Clicked on Queue Analysis Add icon");
        queueAnalysis.navigateToQueueAnalysis();
        waitExecuter.sleep(1000);
        waitExecuter.waitUntilElementClickable(qaPageObject.addIcon);
        for (int i = 0; i < 8; i++) {
            waitExecuter.sleep(2000);
            waitExecuter.waitUntilElementClickable(qaPageObject.addIcon);
            qaPageObject.addIcon.click();
            MouseActions.clickOnElement(driver,qaPageObject.modalRunButton);
            waitExecuter.sleep(1000);
            //Select ClusterId
            test.log(LogStatus.INFO, "Selecting ClusterId: " + clusterId);
            LOGGER.info("Selecting ClusterId: " + clusterId);
            queueAnalysis.selectMultiClusterId(clusterId);
            //Click on date range
            test.log(LogStatus.INFO, "Click on date range");
            LOGGER.info("Click on date range");
            qaPageObject.dateRange.click();
            List<WebElement> dateRange = datePickerPageObject.dateRangeOptions;
            WebElement dateRangeOption = datePickerPageObject.dateRangeOptions.get(i);
            String dateRangeValue = dateRangeOption.getText();
            dateRangeOption.click();
            //Click on Run button of modal window
            test.log(LogStatus.INFO, "Click on Run button of modal window");
            LOGGER.info("Click on Run button of modal window");
            actions.performActionWithPolling(qaPageObject.modalRunButton, UserAction.CLICK);

            if (dateRangeValue.equals("Last 7 Days") || dateRangeValue.equals("Last 30 Days")) {
                try {
                    waitExecuter.waitUntilTextNotToBeInWebElement(qaPageObject.footerWaitCycle, "Please Wait");
                    waitExecuter.waitUntilElementClickable(qaPageObject.addIcon);
                    waitExecuter.waitUntilTextToBeInWebElement(qaPageObject.successBanner,
                            "SUCCESS");
                    test.log(LogStatus.PASS, "Verified Queue Analysis report is loaded properly.");
                } catch (TimeoutException te) {
                    try{
                        waitExecuter.waitUntilTextToBeInWebElement(qaPageObject.successBanner,
                                "SUCCESS");
                    }
                    catch (TimeoutException ex){
                        throw new AssertionError("Queue Analysis Report not completed successfully.");
                    }

                }
            } else if (dateRangeValue.equals("This Year") || dateRangeValue.equals("Last 90 Days")
                    || dateRangeValue.equals("Last 60 Days")) {
                Assert.assertTrue(qaPageObject.invalidInputMessage.get(0).getText().trim().contains("Please Make Sure Date Range Less than 30 Days"),
                        "Banner message display an in-correct message as "
                                + qaPageObject.invalidInputMessage.get(0).getText());
                test.log(LogStatus.PASS, "Verified Queue Analysis report is loaded properly.");
                qaPageObject.close.click();
                waitExecuter.waitUntilElementClickable(qaPageObject.addIcon);
            } else if (dateRangeValue.equals("Last Month")
                    || dateRangeValue.equals("This Month")) {
                qaPageObject.close.click();
                waitExecuter.waitUntilElementClickable(qaPageObject.addIcon);
            }
        }
    }
}
