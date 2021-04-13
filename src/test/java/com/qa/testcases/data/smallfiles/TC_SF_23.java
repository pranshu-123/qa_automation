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
public class TC_SF_23 extends BaseClass {

    Logger logger = Logger.getLogger(TC_SF_23.class.getName());

    @Test(dataProvider = "clusterid-data-provider")
    public void verifyMaxParameters(String clusterId) {
        test = extent.startTest("TC_SF_23.verifyMaxParameters: " + clusterId, "Verify the user successfully run Small Files report and display the report in the UI.");
        test.assignCategory("Data- Small Files");
        Log.startTestCase("TC_SF_23.verifyMaxParameters");

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

        smallfiles.navigateToSmallFileReport(smallfilesPageObject, test, "999999999999999999999999999", "999999999999999999999999999"
                , "100000000000000", "10000");
        test.log(LogStatus.PASS, "Verify the user to enter all the parameters for small files");

        smallfiles.clickOnadvancedOptions();
        smallfiles.navigateToAdvancedOptions(smallfilesPageObject, test, "50", "50");
        waitExecuter.waitUntilPageFullyLoaded();
        userActions.performActionWithPolling(smallfilesPageObject.modalRunButton, UserAction.CLICK);
        logger.info("Clicked on Modal Run Button");
        test.log(LogStatus.INFO, "Clicked on Modal Run Button");

        String heading = smallfilesPageObject.verifyAbsoluteSize.getText();
        test.log(LogStatus.PASS, "Verified the absolute size  poulated :" + heading);

        try {
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
        }
    }
}
