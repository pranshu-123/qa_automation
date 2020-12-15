package com.qa.testcases.data.smallfiles;

import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
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
public class TC_SF_25 extends BaseClass {
    Logger logger = Logger.getLogger(TC_SF_25.class.getName());

    @Test(dataProvider = "clusterid-data-provider")
    public void VerifySortByFiles(String clusterId) {
        test = extent.startTest("TC_SF_25.VerifySortByFiles: " + clusterId,
                "Verify The user is able sort based on number of Files column for Small File Reports");
        test.assignCategory("Data- Small Files and File reports");
        Log.startTestCase("TC_SF_25.VerifySortByFiles");

        WaitExecuter waitExecuter = new WaitExecuter(driver);
        SmallfilesPageObject smallfilesPageObject = new SmallfilesPageObject(driver);

        Smallfiles smallfiles = new Smallfiles(driver);
        smallfiles.commonPanelTabValidation(test, logger);
        smallfiles.closeConfirmationMessageNotification();

        // Sort Up by Files
        test.log(LogStatus.INFO, "Ascending order by files");
        logger.info("Ascending order by files");
        smallfilesPageObject.sortFiles.click();
        waitExecuter.sleep(2000);
        Assert.assertTrue(smallfilesPageObject.sortUp.isDisplayed(), "Ascending order is not working");
        // Sort down by Files
        test.log(LogStatus.INFO, "Descending order by files");
        logger.info("Descending order by files");
        smallfilesPageObject.sortFiles.click();
        waitExecuter.sleep(2000);
        Assert.assertTrue(smallfilesPageObject.sortDown.isDisplayed(), "Descending order is not working");
        test.log(LogStatus.PASS, "Verified sorting on files.");
    }
}
