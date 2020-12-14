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
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.logging.Logger;

@Marker.DataSmallFiles
@Marker.All
public class TC_SF_29 extends BaseClass {
    Logger logger = Logger.getLogger(TC_SF_29.class.getName());

    @Test(dataProvider = "clusterid-data-provider")
    public void VerifySortByMaxFileSize(String clusterId) {
        test = extent.startTest("TC_SF_29.VerifySortByMaxFileSize: " + clusterId,
                "Verify The user is able sort based on Max File Size column for Small File Reports");
        test.assignCategory("Data- Small Files and File reports");
        Log.startTestCase("TC_SF_29.VerifySortByMaxFileSize");

        WaitExecuter waitExecuter = new WaitExecuter(driver);
        SmallfilesPageObject smallfilesPageObject = new SmallfilesPageObject(driver);
        Smallfiles smallfiles = new Smallfiles(driver);
        smallfiles.commonPanelTabValidation(test,logger);
        smallfiles.closeConfirmationMessageNotification();


        // Sort Up by Max File Size
        test.log(LogStatus.INFO, "Ascending order by Max File Size");
        logger.info("Ascending order by Max File Size");
        smallfilesPageObject.sortMaxFileSize.click();
        waitExecuter.sleep(2000);
        Assert.assertTrue(smallfilesPageObject.sortUp.isDisplayed(), "Sort up is not working");
        // Sort down by Max File Size
        test.log(LogStatus.INFO, "Descending order by Max File Size");
        logger.info("Descending order by Max File Size");
        smallfilesPageObject.sortMaxFileSize.click();
        waitExecuter.sleep(2000);
        Assert.assertTrue(smallfilesPageObject.sortDown.isDisplayed(), "Sort down is not working");
        test.log(LogStatus.PASS, "Verified sorting on Max File Size.");

    }
}