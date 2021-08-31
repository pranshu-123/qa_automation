package com.qa.testcases.data.smallfiles;

import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.enums.UserAction;
import com.qa.pagefactory.SubTopPanelModulePageObject;
import com.qa.pagefactory.TopPanelPageObject;
import com.qa.pagefactory.data.SmallfilesPageObject;
import com.qa.scripts.HomePage;
import com.qa.scripts.data.Smallfiles;
import com.qa.utils.MouseActions;
import com.qa.utils.WaitExecuter;
import com.qa.utils.actions.UserActions;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.TimeoutException;
import org.testng.annotations.Test;

import java.util.logging.Logger;

@Marker.DataSmallFiles
@Marker.All
public class TC_SF_01 extends BaseClass {
    private static final Logger LOGGER = Logger.getLogger(TC_SF_01.class.getName());

    @Test(dataProvider = "clusterid-data-provider")
    public void validatesmallFilereports(String clusterId) {
        test = extent.startTest("TC_SF_01.validatesmallFilereports: " + clusterId, "Verify User is able " +
                "Verify UI should open a mini Winodw for the user to enter all the parameters for small files");
        test.assignCategory("Data- Small Files");
        LOGGER.info("Passed Parameter Is : " + clusterId);

        WaitExecuter waitExecuter = new WaitExecuter(driver);
        UserActions userActions = new UserActions(driver);
        SubTopPanelModulePageObject topPanelComponentPageObject = new SubTopPanelModulePageObject(driver);
        waitExecuter.waitUntilElementPresent(topPanelComponentPageObject.data);
        waitExecuter.waitUntilPageFullyLoaded();
        MouseActions.clickOnElement(driver, topPanelComponentPageObject.data);
        LOGGER.info("Clicked on Data Tab");
        test.log(LogStatus.INFO, "Clicked on Data Tab");

        TopPanelPageObject topPanelPageObject = new TopPanelPageObject(driver);
        waitExecuter.waitUntilElementPresent(topPanelPageObject.smallFilesTab);
        waitExecuter.waitUntilPageFullyLoaded();
        waitExecuter.waitUntilElementClickable(topPanelPageObject.smallFilesTab);
        waitExecuter.sleep(3000);
        MouseActions.clickOnElement(driver, topPanelPageObject.smallFilesTab);
        LOGGER.info("Clicked on small FilesTab Tab");
        test.log(LogStatus.INFO, "Clicked on small FilesTab Tab");

        SmallfilesPageObject smallfilesPageObject = new SmallfilesPageObject(driver);

        Smallfiles smallfiles = new Smallfiles(driver);
        smallfiles.closeConfirmationMessageNotification();
        smallfiles.clickOnRunButton();
        LOGGER.info("Clicked on Run Button");
        test.log(LogStatus.INFO, "Clicked on Run Button");

        HomePage homePage = new HomePage(driver);
        homePage.selectMultiClusterId(clusterId);

        smallfiles.navigateToSmallFileReport(smallfilesPageObject,test,"256","512"
                ,"1","10");
        userActions.performActionWithPolling(smallfilesPageObject.modalRunButton, UserAction.CLICK);
        waitExecuter.sleep(8000);
        LOGGER.info("Clicked on Modal Run Button");
        test.log(LogStatus.INFO, "Clicked on Modal Run Button");

        try {
            waitExecuter.waitUntilElementPresent(smallfilesPageObject.confirmationMessageElement);
            waitExecuter.waitUntilTextToBeInWebElement(smallfilesPageObject.confirmationMessageElement,
                    "Smallfile Report completed successfully.");
            waitExecuter.sleep(2000);
            test.log(LogStatus.PASS, "Verified smallfiles report is loaded properly.");
            LOGGER.info("Verified smallfiles report is loaded properly");
        } catch (TimeoutException te) {
            waitExecuter.waitUntilTextToBeInWebElement(smallfilesPageObject.confirmationMessageElement,
                    "Smallfile Report completed successfully.");
        }
        catch (VerifyError te) {
            throw new AssertionError("smallfiles Report not completed successfully."+te);
        }
    }
}