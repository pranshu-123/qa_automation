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
public class TC_SF_38 extends BaseClass {

    Logger logger = Logger.getLogger(TC_SF_38.class.getName());

    @Test(dataProvider = "clusterid-data-provider")
    public void verifyScheduleReport(String clusterId) {
        test = extent.startTest("TC_SF_38.verifyScheduleReport: " + clusterId,
                "Verify Unravel should send email notifications to all the email address saved in the scheduled report once in two weeks");
        test.assignCategory("Data- Small Files and File reports");
        Log.startTestCase("TC_SF_38.verifyScheduleReport");

        WaitExecuter waitExecuter = new WaitExecuter(driver);
        SmallfilesPageObject smallfilesPageObject = new SmallfilesPageObject(driver);
        Smallfiles smallfiles = new Smallfiles(driver);
        smallfiles.commonPanelTabValidation(test, logger);
        smallfiles.closeConfirmationMessageNotification();
        smallfiles.clickOnScheduleButton();
        logger.info("Clicked on Shedule Button");
        test.log(LogStatus.INFO, "Clicked on Shedule Button");
        UserActions userActions = new UserActions(driver);

        HomePage homePage = new HomePage(driver);
        homePage.selectMultiClusterId(clusterId);


        smallfiles.navigateToSmallFileReport(smallfilesPageObject, test, "1024",
                "1024", "10", "100");
        test.log(LogStatus.PASS, "Verify the user to enter all the parameters for small files");

        smallfiles.scheduleAdvancedOptions(smallfilesPageObject,test,"Queue_An_Test","sray@unraveldata.com");
        test.log(LogStatus.PASS, "Verify the user to enter all the parameters for small files Advanced Options");
        smallfiles.clickOnModalScheduleButton();
        waitExecuter.waitUntilPageFullyLoaded();
        logger.info("Clicked on Modal Schedule Button");
        test.log(LogStatus.INFO, "Clicked on Modal Schedule Button");

        try {
            waitExecuter.waitUntilTextToBeInWebElement(smallfilesPageObject.confirmationMessageElement,
                    "Small file Report completed successfully.");
            test.log(LogStatus.PASS, "Verified smallfiles report is loaded properly.");
            logger.info("Verified smallfiles report is loaded properly");
        } catch (TimeoutException te) {
            throw new AssertionError("smallfiles Report not completed successfully.");
        }

        smallfiles.validateReportPage(smallfilesPageObject);
        test.log(LogStatus.PASS, "Validated Reports page successfully");
    }
}
