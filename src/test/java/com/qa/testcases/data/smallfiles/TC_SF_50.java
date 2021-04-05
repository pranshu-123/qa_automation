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
public class TC_SF_50 extends BaseClass {

    Logger logger = Logger.getLogger(TC_SF_50.class.getName());

    @Test(dataProvider = "clusterid-data-provider")
    public void validateErrorSmallFile(String clusterId) {
        test = extent.startTest("TC_SF_14.validateErrorSmallFile: " + clusterId,
                " Verify Error displayed by Small file report when fsimage is not available.");
        test.assignCategory("Data- Small Files");
        Log.startTestCase("TC_SF_14.validateErrorSmallFile");

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

        smallfiles.navigateToSmallFileReport(smallfilesPageObject, test, "0", "2048"
                , "100", "100");
        test.log(LogStatus.PASS, "Verify the user to enter all the parameters for small files");

        waitExecuter.waitUntilPageFullyLoaded();
        String heading = smallfilesPageObject.verifyAbsoluteSize.getText();
        test.log(LogStatus.PASS, "Verified the absolute size  poulated :" + heading);

        try {
            String scheduleSuccessMsg = "The report has been scheduled successfully.";
            smallfiles.verifyScheduleSuccessMsg(scheduleSuccessMsg);
        } catch (TimeoutException te) {
            throw new AssertionError("Verified the Error SmallFile not been scheduled successfully."+te);
        }
    }
}
