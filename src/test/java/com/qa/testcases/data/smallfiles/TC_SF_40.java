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
public class TC_SF_40 extends BaseClass {

    Logger logger = Logger.getLogger(TC_SF_40.class.getName());

    @Test(dataProvider = "clusterid-data-provider")
    public void verifyScheduleSpecialCharecters(String clusterId) {
        test = extent.startTest("TC_SF_40.verifyScheduleSpecialCharecters: ",
                "Verify Unravel should be able to schedule a Queue Analysis report with Scedule Name having special charecters.");
        test.assignCategory("Data- Small Files and File reports");
        Log.startTestCase("TC_SF_40.verifyScheduleSpecialCharecters");

        WaitExecuter waitExecuter = new WaitExecuter(driver);
        SmallfilesPageObject smallfilesPageObject = new SmallfilesPageObject(driver);
        Smallfiles smallfiles = new Smallfiles(driver);
        smallfiles.commonPanelTabValidation(test, logger);
        smallfiles.closeConfirmationMessageNotification();
        smallfiles.clickOnScheduleButton();
        logger.info("Clicked on Shedule Button");
        test.log(LogStatus.INFO, "Clicked on Shedule Button");

        // Select cluster
        HomePage homePage = new HomePage(driver);
        homePage.selectMultiClusterId(clusterId);


        smallfiles.navigateToSmallFileReport(smallfilesPageObject, test, "256",
                "512", "10", "100");
        test.log(LogStatus.PASS, "Verify the user to enter all the parameters for small files");

        smallfiles.scheduleAdvancedOptions(smallfilesPageObject, test, "!@$@$@#$@#%@%",
                "test@gmail.com");
        // Define day of the week and time
        test.log(LogStatus.INFO, "Define day of the week as- Daily and time as- 10:30");
        logger.info("Define day of the week as- Daily and time as- 10:30");
        smallfiles.selectDayTime("Daily", "10", "30");
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
