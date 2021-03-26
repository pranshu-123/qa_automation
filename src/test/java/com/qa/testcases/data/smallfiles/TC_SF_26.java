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
public class TC_SF_26 extends BaseClass {
    Logger logger = Logger.getLogger(TC_SF_26.class.getName());

    @Test(dataProvider = "clusterid-data-provider")
    public void VerifySortByAvgFileSize(String clusterId) {
        test = extent.startTest("TC_SF_26.VerifySortByAvgFileSize: " + clusterId,
                "Verify The user is able sort based on Avg File Size column for Small File Reports");
        test.assignCategory("Data- Small Files");
        Log.startTestCase("TC_SF_26.VerifySortByAvgFileSize");

        WaitExecuter waitExecuter = new WaitExecuter(driver);
        SmallfilesPageObject smallfilesPageObject = new SmallfilesPageObject(driver);
        Smallfiles smallfiles = new Smallfiles(driver);
        smallfiles.commonPanelTabValidation(test, logger);
        smallfiles.closeConfirmationMessageNotification();
       try{
        // Sort Up by Avg File Size
        test.log(LogStatus.INFO, "Ascending order by Avg File Size");
        logger.info("Ascending order by Avg File Size");
        smallfilesPageObject.sortAvgFileSize.click();
        waitExecuter.sleep(2000);
        Assert.assertTrue(smallfilesPageObject.sortUp.isDisplayed(), "Ascending order is not working");
        // Sort down by Avg File Size
        test.log(LogStatus.INFO, "Descending order by Avg File Size");
        logger.info("Descending order by Avg File Size");
        smallfilesPageObject.sortAvgFileSize.click();
        waitExecuter.sleep(2000);
        Assert.assertTrue(smallfilesPageObject.sortDown.isDisplayed(), "Descending order is not working");
        test.log(LogStatus.PASS, "Verified sorting on Avg File Size.");
    }
          catch (Exception te) {
            throw new AssertionError("Verified sorting on Avg File Size not completed successfully."+te.getMessage());
        }

    }
}
