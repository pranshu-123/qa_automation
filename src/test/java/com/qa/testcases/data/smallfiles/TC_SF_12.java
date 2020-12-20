package com.qa.testcases.data.smallfiles;

import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.pagefactory.data.SmallfilesPageObject;
import com.qa.scripts.HomePage;
import com.qa.scripts.data.Smallfiles;
import com.qa.utils.Log;
import com.qa.utils.WaitExecuter;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.TimeoutException;
import org.testng.annotations.Test;

import java.util.logging.Logger;
@Marker.DataSmallFiles
@Marker.All
public class TC_SF_12 extends BaseClass {

    Logger logger = Logger.getLogger(TC_SF_12.class.getName());

    @Test(dataProvider = "clusterid-data-provider")
    public void verifySmallFile(String clusterId) {
        test = extent.startTest("TC_SF_12.verifySmallFileSize: " + clusterId, "Verify User is able " +
                "minmum of 10 number of files with Average file size between 512 - 1024 bytes.");
        test.assignCategory("Data- Small Files and File reports");
        Log.startTestCase("TC_SF_12.verifySmallFileSize");

        WaitExecuter waitExecuter = new WaitExecuter(driver);
        SmallfilesPageObject smallfilesPageObject = new SmallfilesPageObject(driver);
        Smallfiles smallfiles = new Smallfiles(driver);
        smallfiles.commonPanelTabValidation(test, logger);
        smallfiles.closeConfirmationMessageNotification();
        smallfiles.clickOnRunButton();
        logger.info("Clicked on Run Button");
        test.log(LogStatus.INFO, "Clicked on Run Button");

        HomePage homePage = new HomePage(driver);
        homePage.selectMultiClusterId(clusterId);

        smallfiles.navigateToSmallFileReport(smallfilesPageObject, test, "512",
                "1024", "10", "10");
        test.log(LogStatus.PASS, "Verify the user to enter all the parameters for small files");

        smallfiles.clickOnadvancedOptions();
        smallfiles.navigateToAdvancedOptions(smallfilesPageObject, test, "3", "5");
        smallfiles.clickOnModalRunButton();
        logger.info("Clicked on Modal Run Button");
        test.log(LogStatus.INFO, "Clicked on Modal Run Button");

        try {
            waitExecuter.waitUntilTextToBeInWebElement(smallfilesPageObject.confirmationMessageElement,
                    "Small file Report completed successfully.");
            test.log(LogStatus.PASS, "Verified smallfiles report is loaded properly.");
            logger.info("Verified smallfiles report is loaded properly");
        } catch (TimeoutException te) {
            throw new AssertionError("smallfiles Report not completed successfully.");
        }

    }
}

