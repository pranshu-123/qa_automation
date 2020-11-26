package com.qa.testcases.data.smallfiles;

import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.pagefactory.SubTopPanelModulePageObject;
import com.qa.pagefactory.TopPanelPageObject;
import com.qa.pagefactory.data.SmallfilesPageObject;
import com.qa.scripts.HomePage;
import com.qa.scripts.data.Smallfiles;
import com.qa.utils.Log;
import com.qa.utils.MouseActions;
import com.qa.utils.WaitExecuter;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.TimeoutException;

import org.testng.annotations.Test;

import java.util.logging.Logger;

@Marker.DataSmallFiles
@Marker.All
public class TC_SF_05 extends BaseClass {
    private static final Logger LOGGER = Logger.getLogger(TC_SF_05.class.getName());

    @Test(dataProvider = "clusterid-data-provider")
    public void validateAbsolutesmallfilesizereport(String clusterId) {
        test = extent.startTest("TC_SF_05.validateAbsolutesmallfilesizereport: " + clusterId, "Verify User is able " +
                "Verify Unravel should successfully generate Absolute \"Small File Size Report\"");
        test.assignCategory("Data- Small Files and File reports");
        Log.startTestCase("TC_SF_05.validateAbsolutesmallfilesizereport");

        WaitExecuter waitExecuter = new WaitExecuter(driver);
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

        //click on cluster search field
        smallfilesPageObject.clusterIdsearchfield.click();
        System.out.println("All clusterId size: " + smallfilesPageObject.clusterIdsList.size());
        test.log(LogStatus.INFO, "All clusterId count: " + smallfilesPageObject.clusterIdsList.size());
        test.log(LogStatus.PASS, "Validated cluster filter in UI");

        String minimumFileSize = "6";
        smallfiles.minimumFileSize(minimumFileSize);
        LOGGER.info("Set minimum FileSize as: " + minimumFileSize);
        test.log(LogStatus.INFO, "Set minimum FileSize as: " + minimumFileSize);

        String maximumFileSize = "6";
        smallfiles.maximumFileSize(maximumFileSize);
        LOGGER.info("Set maximum FileSize as: " + maximumFileSize);
        test.log(LogStatus.INFO, "Set maximum FileSize as: " + maximumFileSize);

        String minimumSmallFile = "6";
        smallfiles.minimumSmallFile(minimumSmallFile);
        LOGGER.info("Set minimum SmallFile as: " + minimumSmallFile);
        test.log(LogStatus.INFO, "Set minimum Small File as: " + minimumSmallFile);

        String directoriesToShow = "6";
        smallfiles.directoriesToShow(directoriesToShow);
        LOGGER.info("Set minimum SmallFile as: " + directoriesToShow);
        test.log(LogStatus.INFO, "Set minimum Small File as: " + directoriesToShow);

        smallfiles.clickOnModalRunButton();
        waitExecuter.sleep(3000);
        LOGGER.info("Clicked on Modal Run Button");
        test.log(LogStatus.INFO, "Clicked on Modal Run Button");

        try {
            waitExecuter.waitUntilTextToBeInWebElement(smallfilesPageObject.confirmationMessageElement,
                    "Small file Report completed successfully.");
            waitExecuter.sleep(3000);
            test.log(LogStatus.PASS, "Verified smallfiles report is loaded properly.");
            LOGGER.info("Verified smallfiles report is loaded properly");
        } catch (TimeoutException te) {
            throw new AssertionError("smallfiles Report not completed successfully.");
        }
    }
}
