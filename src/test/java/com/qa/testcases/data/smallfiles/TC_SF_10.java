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
public class TC_SF_10 extends BaseClass {

    Logger logger = Logger.getLogger(TC_SF_10.class.getName());

    @Test(dataProvider = "clusterid-data-provider")
    public void verifyAbsoluteFileSize(String clusterId) {
        test = extent.startTest("TC_SF_10.verifyAbsoluteFileSize: " + clusterId, "Verify User is able " +
                "selected and the directory listed as having 2 files.");
        test.assignCategory("Data- Small Files and File reports");
        Log.startTestCase("TC_SF_10.verifyAbsoluteFileSize");

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

        smallfiles.navigateToSmallFileReport(smallfilesPageObject, test, "256", "512"
                , "1", "10");
        test.log(LogStatus.PASS, "Verify the user to enter all the parameters for small files");

        smallfiles.clickOnadvancedOptions();

        String onminParentDirectory = "3";
        smallfiles.clickonminParent(onminParentDirectory);
        logger.info("Set Min Parent Directory Depth as: " + onminParentDirectory);
        test.log(LogStatus.INFO, "Set minimum Small File as: " + onminParentDirectory);

        String onmaxParentDirectory = "5";
        smallfiles.clickonmaxParent(onmaxParentDirectory);
        logger.info("Set Max Parent Directory Depth as: " + onmaxParentDirectory);
        test.log(LogStatus.INFO, "Set minimum Small File as: " + onmaxParentDirectory);


        smallfiles.clickOnModalRunButton();
        logger.info("Clicked on Modal Run Button");
        test.log(LogStatus.INFO, "Clicked on Modal Run Button");

        String heading = smallfilesPageObject.verifyAbsoluteSize.getText();
        test.log(LogStatus.PASS, "Verified the absolute size  poulated :" + heading);

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
