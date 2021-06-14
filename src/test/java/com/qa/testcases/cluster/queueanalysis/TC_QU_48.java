package com.qa.testcases.cluster.queueanalysis;

import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.pagefactory.clusters.QueueAnalysisPageObject;
import com.qa.scripts.clusters.QueueAnalysis;
import com.qa.utils.WaitExecuter;
import com.relevantcodes.extentreports.LogStatus;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

/*
 * @author- Ojasvi Pandey
 */
@Marker.QueueAnalysis
@Marker.All
public class TC_QU_48 extends BaseClass {
    private static final Logger LOGGER = Logger.getLogger(TC_QU_48.class.getName());

    @Test(description = "P1-Validate user is able to generate report with special chars in schedule report name")
    public void validateSpecialCharsInName() {
        test = extent.startTest("TC_QU_48.validateSpecialCharsInName",
                "Validate user is able to generate report with special chars in report name");
        test.assignCategory("Jobs - Queue Analysis");
        test.log(LogStatus.INFO, "Login to the application");
        // Initialize all classes objects
        test.log(LogStatus.INFO, "Initialize all class objects");
        LOGGER.info("Initialize all class objects");
        QueueAnalysis queueAnalysis = new QueueAnalysis(driver);
        QueueAnalysisPageObject qaPageObject = new QueueAnalysisPageObject(driver);
        WaitExecuter waitExecuter = new WaitExecuter(driver);
        // Navigate to Queue Analysis tab from header
        test.log(LogStatus.INFO, "Navigate to Queue Analysis tab from header");
        test.log(LogStatus.INFO, "Clicked on Queue Analysis tab");
        test.log(LogStatus.INFO, "Validate Queue Analysis tab loaded successfully");
        queueAnalysis.navigateToQueueAnalysis();

        // Click on schedule report
        test.log(LogStatus.INFO, "Click on schedule report");
        LOGGER.info("Click on schedule report");
        queueAnalysis.clickOnScheduleButton();
        String scheduleName = "@@@###!!!";
        List<String> multiEmail = Arrays.asList("test1@unravel.com", "test2@unravel.com", "test3@unravel.com");
        // Schedule with multiple e-mails
        test.log(LogStatus.INFO, "Schedule with multiple e-mails");
        LOGGER.info("Schedule with multiple e-mails");
        queueAnalysis.scheduleWithMultiEmail(scheduleName, multiEmail);
        // Define day of the week and time
        test.log(LogStatus.INFO, "Define day of the week as- Daily and time- 10.30");
        LOGGER.info("Define day of the week as- Daily and time- 10.30");
        queueAnalysis.selectDayTime("Daily", "10", "30");
        queueAnalysis.clickOnModalScheduleButton();
        LOGGER.info("Clicked on modal Schedule Button");
        test.log(LogStatus.INFO, "Clicked on modal Schedule Button");
        String scheduleSuccessMsg = "the report has been scheduled successfully.";
        queueAnalysis.verifyScheduleSuccessMsg(scheduleSuccessMsg);
        waitExecuter.waitUntilElementClickable(qaPageObject.close);
        test.log(LogStatus.PASS, "Verified schedule with multi email for daily.");
    }
}
