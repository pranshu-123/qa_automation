package com.qa.testcases.data.smallfiles;

import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.enums.UserAction;
import com.qa.pagefactory.SubTopPanelModulePageObject;
import com.qa.pagefactory.TopPanelPageObject;
import com.qa.pagefactory.data.SmallfilesPageObject;
import com.qa.scripts.HomePage;
import com.qa.scripts.data.Smallfiles;
import com.qa.utils.Log;
import com.qa.utils.MouseActions;
import com.qa.utils.WaitExecuter;
import com.qa.utils.actions.UserActions;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.TimeoutException;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.logging.Logger;
@Marker.DataSmallFiles
@Marker.All
public class TC_SF_07 extends BaseClass {
    private static final Logger LOGGER = Logger.getLogger(TC_SF_07.class.getName());

    @Test(dataProvider = "clusterid-data-provider")
    public void validateAbsolutesmallfilesizereport(String clusterId) {
        test = extent.startTest("TC_SF_07.validateAbsolutesmallfilesizereport: " + clusterId, "Verify User is able " +
                "Verify Unravel should successfully generate Absolute Small File Size Report");
        test.assignCategory("Data- Small Files");
        Log.startTestCase("TC_SF_07.validateAbsolutesmallfilesizereport");

        WaitExecuter waitExecuter = new WaitExecuter(driver);
        UserActions userActions = new UserActions(driver);
        SubTopPanelModulePageObject topPanelComponentPageObject = new SubTopPanelModulePageObject(driver);
        waitExecuter.waitUntilElementPresent(topPanelComponentPageObject.data);
        waitExecuter.sleep(2000);
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
        waitExecuter.sleep(3000);
        LOGGER.info("Clicked on Modal Run Button");
        test.log(LogStatus.INFO, "Clicked on Modal Run Button");

        waitExecuter.waitUntilElementClickable(smallfilesPageObject.verifyReport);
        waitExecuter.waitUntilTextToBeInWebElement(smallfilesPageObject.verifyReport,
                "Currently, the Smallfile Report is being generated");
        waitExecuter.sleep(2000);
        Assert.assertEquals(smallfilesPageObject.verifyReport.getText(), "Currently, the Smallfile Report is being generated, " +
                        "so no other action can be performed at this time. Please wait for the running task to complete.",
                " Currently, the Small file Report report is not being generated..");
        waitExecuter.sleep(3000);

        try {
            waitExecuter.waitUntilElementPresent(smallfilesPageObject.confirmationMessageElement);
            waitExecuter.waitUntilTextToBeInWebElement(smallfilesPageObject.confirmationMessageElement,
                    "Smallfile Report completed successfully.");
            Assert.assertEquals(smallfilesPageObject.confirmationMessageElement.getText(), "Smallfile Report completed successfully.",
                    " Small file Report not completed successfully..");
            waitExecuter.sleep(3000);
            test.log(LogStatus.PASS, "Verified smallfiles report is loaded properly.");
            LOGGER.info("Verified smallfiles report is loaded properly");

            waitExecuter.waitUntilElementPresent(smallfilesPageObject.verifyAbsoluteSize);
            String heading = smallfilesPageObject.verifyAbsoluteSize.getText();
            test.log(LogStatus.PASS, "Verified the absolute size  poulated :"+heading);
        }
        catch (TimeoutException | VerifyError te) {
            throw new AssertionError("smallfiles Report not completed successfully."+te);
        }
    }
}

