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
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.logging.Logger;
@Marker.DataSmallFiles
@Marker.All
public class TC_SF_20 extends BaseClass {

    Logger logger = Logger.getLogger(TC_SF_20.class.getName());

    @Test(dataProvider = "clusterid-data-provider")
    public void verifySpecialCharacters(String clusterId) {
        test = extent.startTest("TC_SF_20.verifySpecialCharacters: " + clusterId,
                "Verify Unravel UI should display 10000 directories in the report.");
        test.assignCategory("Data- Small Files");
        Log.startTestCase("TC_SF_20.verifySpecialCharacters");

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
        waitExecuter.sleep(8000);
        waitExecuter.waitUntilElementClickable(smallfilesPageObject.smallFilesTab);
        logger.info("Clicked on Modal Run Button");
        test.log(LogStatus.INFO, "Clicked on Modal Run Button");

        waitExecuter.waitUntilElementClickable(smallfilesPageObject.verifyReport);
        waitExecuter.waitUntilTextToBeInWebElement(smallfilesPageObject.verifyReport,
                "Currently, the Smallfile Report is being generated");
        waitExecuter.sleep(2000);
        waitExecuter.waitUntilElementClickable(smallfilesPageObject.smallFilesTab);
        Assert.assertEquals(smallfilesPageObject.verifyReport.getText(), "Currently, the Smallfile Report is being generated, " +
                        "so no other action can be performed at this time. Please wait for the running task to complete.",
                " Currently, the Small file Report report is not being generated..");
        waitExecuter.sleep(2000);

        try {
            waitExecuter.waitUntilElementPresent(smallfilesPageObject.confirmationMessageElement);
            waitExecuter.waitUntilTextToBeInWebElement(smallfilesPageObject.confirmationMessageElement,
                    "Smallfile Report completed successfully.");
            waitExecuter.sleep(3000);
            test.log(LogStatus.PASS, "Verified smallfiles report is loaded properly.");
            logger.info("Verified smallfiles report is loaded properly");
            waitExecuter.waitUntilElementPresent(smallfilesPageObject.verifyAbsoluteSize);
            String heading = smallfilesPageObject.verifyAbsoluteSize.getText();
            waitExecuter.sleep(3000);
            test.log(LogStatus.PASS, "Verified the absolute size  poulated :" + heading);
        } catch (TimeoutException te) {
            waitExecuter.waitUntilTextToBeInWebElement(smallfilesPageObject.confirmationMessageElement,
                    "Smallfile Report completed successfully.");
        }
        catch (VerifyError te) {
            throw new AssertionError("smallfiles Report not completed successfully."+te);
        }
    }
}
