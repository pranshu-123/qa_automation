package com.qa.testcases.data.smallfiles;

import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.pagefactory.data.SmallfilesPageObject;
import com.qa.scripts.HomePage;
import com.qa.scripts.data.Smallfiles;
import com.qa.utils.Log;
import com.qa.utils.WaitExecuter;
import com.qa.utils.actions.UserActions;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.TimeoutException;
import org.testng.annotations.Test;

import java.util.logging.Logger;
@Marker.DataSmallFiles
@Marker.All
public class TC_SF_39 extends BaseClass {

    Logger logger = Logger.getLogger(TC_SF_39.class.getName());

    @Test(dataProvider = "clusterid-data-provider")
    public void verifySchedulingReport(String clusterId) {
        test = extent.startTest("TC_SF_39.verifySchedulingReport: " + clusterId,
                "Verify Unravel should send email notifications to all the email address saved in the scheduled report Once in a month.");
        test.assignCategory("Data- Small Files and File reports");
        Log.startTestCase("TC_SF_39.verifySchedulingReport");

        WaitExecuter waitExecuter = new WaitExecuter(driver);
        SmallfilesPageObject smallfilesPageObject = new SmallfilesPageObject(driver);
        Smallfiles smallfiles = new Smallfiles(driver);
        smallfiles.commonPanelTabValidation(test, logger);
        smallfiles.closeConfirmationMessageNotification();
        smallfiles.clickOnScheduleButton();
        logger.info("Clicked on Shedule Button");
        test.log(LogStatus.INFO, "Clicked on Shedule Button");
        UserActions userActions = new UserActions(driver);

        // Select cluster
        HomePage homePage = new HomePage(driver);
        homePage.selectMultiClusterId(clusterId);


        smallfiles.navigateToSmallFileReport(smallfilesPageObject, test, "256",
                "512", "10", "1000");
        test.log(LogStatus.PASS, "Verify the user to enter all the parameters for small files");

        smallfiles.scheduleAdvancedOptions(smallfilesPageObject, test, "Queue_An_Test4",
                "test@gmail.com");
        // Define day of the week and time
        test.log(LogStatus.INFO, "Define day of the week as- Every month and time as- 00:00");
        logger.info("Define day of the week as- Every month and time as- 00:00");
        smallfiles.selectDayTime("Every month", "00", "00");
        smallfiles.clickOnModalScheduleButton();
        waitExecuter.waitUntilPageFullyLoaded();
        logger.info("Clicked on Modal Schedule Button");
        test.log(LogStatus.INFO, "Clicked on Modal Schedule Button");

        logger.info("Clicked on modal Schedule Button");
        test.log(LogStatus.INFO, "Clicked on modal Schedule Button");
        String scheduleSuccessMsg = "The report has been scheduled successfully.";
        smallfiles.verifyScheduleSuccessMsg(scheduleSuccessMsg);
    }
}
