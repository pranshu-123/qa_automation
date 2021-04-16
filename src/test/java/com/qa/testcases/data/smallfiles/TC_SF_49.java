package com.qa.testcases.data.smallfiles;

import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
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
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import java.util.logging.Logger;

@Marker.DataSmallFiles
@Marker.All
public class TC_SF_49 extends BaseClass {

    Logger logger = Logger.getLogger(TC_SF_49.class.getName());

    @Test(dataProvider = "clusterid-data-provider")
    public void validateErrorUnsupportedValues(String clusterId) {
        test = extent.startTest("TC_SF_49.validateErrorUnsupportedValues: " + clusterId,
                " Verify Small File report UI indicates unsupported values.");
        test.assignCategory("Data- Small Files");
        Log.startTestCase("TC_SF_49.validateErrorUnsupportedValues");

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

        smallfiles.navigateToSmallFileErrorReport(smallfilesPageObject, test, "999999999999999999999999999", "999999999999999999999999999"
                , "100000000000000", "10000");
        test.log(LogStatus.PASS, "Verify the user to enter all the parameters for small files");

        smallfiles.clickOnadvancedOptions();

        smallfiles.navigateToAdvancedOptions(smallfilesPageObject, test, "50", "50");
        smallfiles.clickOnModalRunButton();
        test.log(LogStatus.INFO, "Clicked on Modal Run Button");
        try {
            if (smallfilesPageObject.errorMessageElement.size() > 0) {
                String selectedMessage = smallfilesPageObject.errorMessageElement.stream().findFirst()
                        .filter(WebElement::isDisplayed).get().getText();
                test.log(LogStatus.PASS, "Path display in the table- " + selectedMessage);
                logger.info("Clicked on Modal Run Button");
                userActions.performActionWithPolling(smallfilesPageObject.modalRunButton, UserAction.CLICK);
                waitExecuter.sleep(5000);
            } else {
                String scheduleSuccessMsg = "Small file Report completed successfully.";
                smallfiles.verifyScheduleSuccessMsg(scheduleSuccessMsg);
                MouseActions.clickOnElement(driver, smallfilesPageObject.closeButton);
            }
        } catch (TimeoutException te) {
            throw new AssertionError("Verified the Error Unsupported Values not been scheduled successfully.");
        }
    }
}
