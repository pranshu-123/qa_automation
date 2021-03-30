package com.qa.testcases.data.smallfiles;

import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.pagefactory.data.SmallfilesPageObject;
import com.qa.scripts.HomePage;
import com.qa.scripts.data.Smallfiles;
import com.qa.utils.Log;
import com.qa.utils.MouseActions;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.testng.annotations.Test;

import java.util.logging.Logger;

@Marker.DataSmallFiles
@Marker.All
public class TC_SF_41 extends BaseClass {

    Logger logger = Logger.getLogger(TC_SF_41.class.getName());

    @Test(dataProvider = "clusterid-data-provider")
    public void verifyScheduleSpecialCharecters(String clusterId) {
        test = extent.startTest("TC_SF_41.verifyScheduleSpecialCharacters: " + clusterId,
                "Verify user clicks on Cancel button the Mini Window to schedule report should close and the UI should display the previously generated report.");
        test.assignCategory("Data- Small Files");
        Log.startTestCase("TC_SF_41.verifyScheduleSpecialCharacters");

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

        try {
            smallfiles.navigateToSmallFileReport(smallfilesPageObject, test, "256",
                    "512", "10", "100");
            test.log(LogStatus.PASS, "Verify the user to enter all the parameters for small files");

            smallfiles.scheduleAdvancedOptions(smallfilesPageObject, test, "Queue_An_Test",
                    "sray@unraveldata.com");
            // Define day of the week and time
            test.log(LogStatus.INFO, "Define day of the week as- Daily and time as- 10:30");
            logger.info("Define day of the week as- Daily and time as- 10:30");
            smallfiles.selectDayTime("Daily", "10", "30");
            //Close apps details page
            MouseActions.clickOnElement(driver, smallfilesPageObject.homeTab);
        } catch (TimeoutException | NoSuchElementException | VerifyError te) {
            throw new AssertionError("Verified the report not been scheduled successfully.");

        }
    }
}



