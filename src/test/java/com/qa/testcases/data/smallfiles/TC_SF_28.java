package com.qa.testcases.data.smallfiles;

import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.pagefactory.data.SmallfilesPageObject;
import com.qa.scripts.data.Smallfiles;
import com.qa.utils.Log;
import com.qa.utils.WaitExecuter;
import com.qa.utils.actions.UserActions;
import com.relevantcodes.extentreports.LogStatus;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.logging.Logger;

@Marker.DataSmallFiles
@Marker.All
public class TC_SF_28 extends BaseClass {
    Logger logger = Logger.getLogger(TC_SF_28.class.getName());

    @Test(dataProvider = "clusterid-data-provider")
    public void VerifySortByMinFileSize(String clusterId) {
        test = extent.startTest("TC_SF_28.VerifySortByMinFileSize: " + clusterId,
                "Verify The user is able sort based on Min File Size column for Small File Reports");
        test.assignCategory("Data- Small Files");
        Log.startTestCase("TC_SF_28.VerifySortByMinFileSize");

        WaitExecuter waitExecuter = new WaitExecuter(driver);
        UserActions userActions = new UserActions(driver);
        SmallfilesPageObject smallfilesPageObject = new SmallfilesPageObject(driver);
        Smallfiles smallfiles = new Smallfiles(driver);
        smallfiles.commonPanelTabValidation(test, logger);
        smallfiles.closeConfirmationMessageNotification();
        try {
            // Sort Up by  Min File Size
            test.log(LogStatus.INFO, "Ascending order by Min File Size");
            logger.info("Ascending order by Min File Size");
            smallfilesPageObject.sortMinFileSize.click();
            waitExecuter.sleep(2000);
            Assert.assertTrue(smallfilesPageObject.sortUp.isDisplayed(), "Ascending order is not working");
            // Sort down by  Min File Size
            test.log(LogStatus.INFO, "Descending order by Min File Size");
            logger.info("Descending order by Min File Size");
            smallfilesPageObject.sortMinFileSize.click();
            waitExecuter.sleep(2000);
            Assert.assertTrue(smallfilesPageObject.sortDown.isDisplayed(), "Descending order is not working");
            test.log(LogStatus.PASS, "Verified sorting on Min File Size.");

        } catch (Exception te) {
            throw new AssertionError("Verified Min File Size column for Small File Reports not completed successfully." + te.getMessage());
        }

    }
}
