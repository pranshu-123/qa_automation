package com.qa.testcases.data.smallfiles;

import com.qa.base.BaseClass;
import com.qa.pagefactory.SubTopPanelModulePageObject;
import com.qa.pagefactory.TopPanelPageObject;
import com.qa.pagefactory.data.SmallfilesPageObject;
import com.qa.scripts.data.Smallfiles;
import com.qa.utils.Log;
import com.qa.utils.MouseActions;
import com.qa.utils.WaitExecuter;
import com.relevantcodes.extentreports.LogStatus;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.logging.Logger;

public class TC_SF_27 extends BaseClass {
    private static final Logger LOGGER = Logger.getLogger(TC_SF_27.class.getName());

    @Test(dataProvider = "clusterid-data-provider")
    public void VerifySortByTotalFileSize(String clusterId) {
        test = extent.startTest("TC_SF_27.VerifySortByTotalFileSize: " + clusterId, "Verify User is able " +
                "Verify The user is able sort based on Total File Size column for Small File Reports");
        test.assignCategory("Data- Small Files and File reports");
        Log.startTestCase("TC_SF_27.VerifySortByTotalFileSize");

        WaitExecuter waitExecuter = new WaitExecuter(driver);
        SubTopPanelModulePageObject topPanelComponentPageObject = new SubTopPanelModulePageObject(driver);
        waitExecuter.waitUntilElementPresent(topPanelComponentPageObject.data);
        waitExecuter.sleep(2000);
        MouseActions.clickOnElement(driver, topPanelComponentPageObject.data);
        LOGGER.info("Clicked on Data Tab");
        test.log(LogStatus.INFO, "Clicked on Data Tab");

        TopPanelPageObject topPanelPageObject = new TopPanelPageObject(driver);
        waitExecuter.waitUntilElementPresent(topPanelPageObject.fileReportsTab);
        waitExecuter.waitUntilPageFullyLoaded();
        waitExecuter.waitUntilElementClickable(topPanelPageObject.fileReportsTab);
        waitExecuter.sleep(3000);
        MouseActions.clickOnElement(driver, topPanelPageObject.fileReportsTab);
        LOGGER.info("Clicked on file Reports Tab");
        test.log(LogStatus.INFO, "Clicked on file ReportsTab Tab");

        SmallfilesPageObject smallfilesPageObject = new SmallfilesPageObject(driver);

        Smallfiles smallfiles = new Smallfiles(driver);
        smallfiles.closeConfirmationMessageNotification();

        // Sort Up by Total File Size
        test.log(LogStatus.INFO, "Ascending order by Total File Size");
        LOGGER.info("Ascending order by Total File Size");
        smallfilesPageObject.sortTotalFileSize.click();
        waitExecuter.sleep(2000);
        Assert.assertTrue(smallfilesPageObject.sortUp.isDisplayed(), "Ascending order is not working");
        // Sort down by Total File Size
        test.log(LogStatus.INFO, "Descending order by Total File Size");
        LOGGER.info("Descending order by fTotal File Size");
        smallfilesPageObject.sortTotalFileSize.click();
        waitExecuter.sleep(2000);
        Assert.assertTrue(smallfilesPageObject.sortDown.isDisplayed(), "Descending order is not working");
        test.log(LogStatus.PASS, "Verified sorting on Total File Size.");

    }
}
