package com.qa.testcases.cluster.queueanalysis;

import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.pagefactory.clusters.QueueAnalysisPageObject;
import com.qa.scripts.clusters.QueueAnalysis;
import com.relevantcodes.extentreports.LogStatus;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

/*
 * @author- Ojasvi Pandey
 */
@Marker.QueueAnalysis
@Marker.All
public class TC_QU_49 extends BaseClass {
    private static final Logger LOGGER = Logger.getLogger(TC_QU_49.class.getName());

    @Test(description = "P1-Validate message on providing invalid input in schedule report")
    public void validateInvalidInputMessage() {
        test = extent.startTest("TC_QU_49.validateInvalidInputMessage",
                "Validate message on providing invalid input in schedule report");
        test.assignCategory("Jobs - Queue Analysis");
        test.log(LogStatus.INFO, "Login to the application");
        // Initialize all classes objects
        test.log(LogStatus.INFO, "Initialize all class objects");
        LOGGER.info("Initialize all class objects");
        QueueAnalysisPageObject qaPageObject = new QueueAnalysisPageObject(driver);
        QueueAnalysis queueAnalysis = new QueueAnalysis(driver);
        // Navigate to Queue Analysis tab from header
        test.log(LogStatus.INFO, "Navigate to Queue Analysis tab from header");
        test.log(LogStatus.INFO, "Clicked on Queue Analysis tab");
        test.log(LogStatus.INFO, "Validate Queue Analysis tab loaded successfully");
        queueAnalysis.navigateToQueueAnalysis();

        // Click on schedule report
        test.log(LogStatus.INFO, "Click on schedule report");
        LOGGER.info("Click on schedule report");
        queueAnalysis.clickOnScheduleButton();
        String scheduleName = "Queue_Ana_Test6";
        List<String> multiEmail = Arrays.asList("test1@unravel");
        // Schedule with invalid e-mails
        test.log(LogStatus.INFO, "Schedule with invalid e-mails");
        LOGGER.info("Schedule with invalid e-mails");
        queueAnalysis.scheduleWithMultiEmail(scheduleName, multiEmail);
        queueAnalysis.clickOnModalScheduleButton();

        String message = "please, make sure valid inputs.";
        Assert.assertEquals(qaPageObject.invalidInputMessage.getText().trim().toLowerCase(), message,
                "On providing in invalid email, expected message was not displayed");
        test.log(LogStatus.PASS, "For Invalid input, message was displayed");
    }
}
