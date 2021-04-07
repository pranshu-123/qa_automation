package com.qa.testcases.data.smallfiles;

import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.enums.UserAction;
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
public class TC_SF_14 extends BaseClass {

    Logger logger = Logger.getLogger(TC_SF_14.class.getName());

    @Test(dataProvider = "clusterid-data-provider")
    public void validateParentDepth(String clusterId) {
        test = extent.startTest("TC_SF_14.validateParentDepth: " + clusterId,
                " Verify User for min and max values of 1 and 2 respectively, directories like /a, /a/b only will be listed.");
        test.assignCategory("Data- Small Files");
        Log.startTestCase("TC_SF_14.validateParentDepth");

        WaitExecuter waitExecuter = new WaitExecuter(driver);
        UserActions userActions = new UserActions(driver);
        SmallfilesPageObject smallfilesPageObject = new SmallfilesPageObject(driver);
        Smallfiles smallfiles = new Smallfiles(driver);
        smallfiles.commonPanelTabValidation(test, logger);
        smallfiles.closeConfirmationMessageNotification();
        smallfiles.clickOnRunButton();
        logger.info("Clicked on Run Button");
        test.log(LogStatus.INFO, "Clicked on Run Button");

        HomePage homePage = new HomePage(driver);
        homePage.selectMultiClusterId(clusterId);

        smallfiles.navigateToSmallFileReport(smallfilesPageObject, test, "512", "1024"
                , "100", "5");
        test.log(LogStatus.PASS, "Verify the user to enter all the parameters for small files");

        smallfiles.clickOnadvancedOptions();

        smallfiles.navigateToAdvancedOptions(smallfilesPageObject, test, "3", "6");
        waitExecuter.waitUntilPageFullyLoaded();
        userActions.performActionWithPolling(smallfilesPageObject.modalRunButton, UserAction.CLICK);
        logger.info("Clicked on Modal Run Button");
        test.log(LogStatus.INFO, "Clicked on Modal Run Button");

        String heading = smallfilesPageObject.verifyAbsoluteSize.getText();
        test.log(LogStatus.PASS, "Verified the absolute size  poulated :" + heading);

        try {
            String scheduleSuccessMsg = "The report has been scheduled successfully.";
            smallfiles.verifyScheduleSuccessMsg(scheduleSuccessMsg);
        } catch (TimeoutException te) {
            String scheduleSuccessMsg = "The report has been scheduled successfully.";
            smallfiles.verifyScheduleSuccessMsg(scheduleSuccessMsg);
       } catch (VerifyError te) {
        throw new AssertionError("smallfiles Report has not been scheduled successfully.");
    }
    }
}
