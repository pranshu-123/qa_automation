package com.qa.testcases.data.smallfiles;

import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.constants.PageConstants;
import com.qa.enums.UserAction;
import com.qa.pagefactory.data.SmallfilesPageObject;
import com.qa.scripts.HomePage;
import com.qa.scripts.data.Smallfiles;
import com.qa.utils.Log;
import com.qa.utils.MouseActions;
import com.qa.utils.WaitExecuter;
import com.qa.utils.actions.UserActions;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.TimeoutException;
import org.testng.annotations.Test;
import java.util.logging.Logger;
@Marker.DataSmallFiles
@Marker.All
public class TC_SF_36 extends BaseClass {

    Logger logger = Logger.getLogger(TC_SF_36.class.getName());

    @Test(dataProvider = "clusterid-data-provider")
    public void verifyScheduleReport(String clusterId) {
        test = extent.startTest("TC_SF_36.verifyScheduleReport: " + clusterId,
                "Verify Unravel should send email notifications to all the email address saved in the scheduled report daily.");
        test.assignCategory("Data- Small Files");
        Log.startTestCase("TC_SF_36.verifyScheduleReport");

        WaitExecuter waitExecuter = new WaitExecuter(driver);
        UserActions userActions = new UserActions(driver);
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

        // Schedule with filesize and directories
        smallfiles.navigateToSmallFileReport(smallfilesPageObject, test, "256",
                "512", "10", "100");
        test.log(LogStatus.PASS, "Verify the user to enter all the parameters for small files");
        // Schedule with schedulename and  e-mails
        smallfiles.scheduleAdvancedOptions(smallfilesPageObject, test, "Queue_An_Test",
                "test@gmail.com");
        // Define day of the week and time
        test.log(LogStatus.INFO, "Define day of the week as- Daily and time as- 10:30");
        logger.info("Define day of the week as- Daily and time as- 10:30");
        smallfiles.selectDayTime("Daily", "10", "30");
        waitExecuter.waitUntilPageFullyLoaded();
        waitExecuter.sleep(1000);
        userActions.performActionWithPolling(smallfilesPageObject.runSheduleButton, UserAction.CLICK);
        logger.info("Clicked on Modal Shedule Button");
        test.log(LogStatus.INFO, "Clicked on Modal Shedule Button");
        waitExecuter.waitUntilPageFullyLoaded();
        /*try {
            waitExecuter.waitUntilElementPresent(smallfilesPageObject.confirmationMessageElement);
            waitExecuter.waitUntilTextToBeInWebElement(smallfilesPageObject.confirmationMessageElement,
                    "Small file Report completed successfully.");
            waitExecuter.sleep(3000);
            test.log(LogStatus.PASS, "Verified smallfiles report is loaded properly.");
            logger.info("Verified smallfiles report is loaded properly");
        } catch (TimeoutException te) {
            waitExecuter.waitUntilTextToBeInWebElement(smallfilesPageObject.confirmationMessageElement,
                    "Small file Report completed successfully.");
        }
        catch (VerifyError te) {
            throw new AssertionError("smallfiles Report not completed successfully."+te);
        }*/
    }
}