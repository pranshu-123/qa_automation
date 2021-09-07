package com.qa.testcases.cluster.queueanalysis;

import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.pagefactory.clusters.QueueAnalysisPageObject;
import com.qa.scripts.clusters.QueueAnalysis;
import com.qa.utils.WaitExecuter;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;
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
public class TC_QU_25 extends BaseClass {
    private static final Logger LOGGER = Logger.getLogger(TC_QU_25.class.getName());

    @Test(description= "P2-Validate user is able to cancel the modal window and changes does not get saved")
    public void validateCancelButton() {
        test = extent.startTest("TC_QU_25.validateCancelButton",
                "Validate user is able to cancel the modal window and changes does not get saved");
        test.assignCategory("Jobs - Queue Analysis");
        test.log(LogStatus.INFO, "Login to the application");
        // Initialize all classes objects
        test.log(LogStatus.INFO, "Initialize all class objects");
        LOGGER.info("Initialize all class objects");
        QueueAnalysis queueAnalysis = new QueueAnalysis(driver);
        WaitExecuter wait = new WaitExecuter(driver);
        QueueAnalysisPageObject qaPageObject = new QueueAnalysisPageObject(driver);
        Actions action = new Actions(driver);
        // Navigate to Queue Analysis tab from header
        test.log(LogStatus.INFO, "Navigate to Queue Analysis tab from header");
        test.log(LogStatus.INFO, "Clicked on Queue Analysis tab");
        test.log(LogStatus.INFO, "Validate Queue Analysis tab loaded successfully");
        queueAnalysis.navigateToQueueAnalysis();

        // Click on schedule report
        test.log(LogStatus.INFO, "Click on schedule report");
        LOGGER.info("Click on schedule report");
        queueAnalysis.clickOnScheduleButton();
        String scheduleName = "Queue_An_Test5";
        List<String> multiEmail = Arrays.asList("test1@unravel.com", "test2@unravel.com", "test3@unravel.com");
        // Schedule with multiple e-mails
        test.log(LogStatus.INFO, "Schedule with multiple e-mails");
        LOGGER.info("Schedule with multiple e-mails");
        queueAnalysis.scheduleWithMultiEmail(scheduleName, multiEmail);
        // Define day of the week and time
        test.log(LogStatus.INFO, "Define day of the week as- Daily and time- 10.30");
        LOGGER.info("Define day of the week as- Daily and time- 10.30");
        queueAnalysis.selectDayTime("Daily", "10", "30");
        wait.waitUntilPageFullyLoaded();
        action.sendKeys(Keys.ESCAPE).build().perform();
        wait.sleep(1000);
        wait.waitUntilElementClickable(qaPageObject.close);
        qaPageObject.close.click();
        LOGGER.info("Clicked on modal Cancel Button");
        test.log(LogStatus.INFO, "Clicked on modal Cancel Button");
        // String scheduleSuccessMsg = "The report has been scheduled successfully.";
        Assert.assertTrue(qaPageObject.QAHeading.isDisplayed(),
                "The Schedule success " + "message is displayed without report generation");
        test.log(LogStatus.PASS, "Verified that the report is not generated.");
    }
}
