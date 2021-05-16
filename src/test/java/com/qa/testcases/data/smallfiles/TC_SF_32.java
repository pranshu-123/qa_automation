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
public class TC_SF_32 extends BaseClass {
    Logger logger = Logger.getLogger(TC_SF_32.class.getName());

    @Test(dataProvider = "clusterid-data-provider")
    public void verifyAvgFileSize(String clusterId) {
        test = extent.startTest("TC_SF_32.verifyAvgFileSize: " + clusterId,
                "Verify the UI should display the only the rows that match the search pattern for Avg File size");
        test.assignCategory("Data- Small Files");
        Log.startTestCase("TC_SF_32.verifyAvgFileSize");

        SmallfilesPageObject smallfilesPageObject = new SmallfilesPageObject(driver);
        UserActions userActions = new UserActions(driver);
        WaitExecuter waitExecuter = new WaitExecuter(driver);
        Smallfiles smallfiles = new Smallfiles(driver);
        smallfiles.commonPanelTabValidation(test, logger);
        smallfiles.closeConfirmationMessageNotification();
        try {
            // Click on small file search
            if (smallfilesPageObject.avgFileSize.size() > 0) {
                test.log(LogStatus.INFO, "Click on queue search box and search for path");
                logger.info("Click on queue search box and search for path");
                smallfiles.verifyAllFileSizePathSearchOption(clusterId, 3, 3);
                waitExecuter.waitUntilPageFullyLoaded();
                test.log(LogStatus.PASS, "Verify the user is able to search the Avg File Size in the small file table");

            } else {
                Assert.assertTrue(smallfilesPageObject.whenNoDataDisplay.isDisplayed());
                test.log(LogStatus.FAIL, "Verified rows that match the search pattern for Avg File size not completed successfully");
            }
        } catch (Exception te) {
            throw new AssertionError("Verified rows that match the search pattern for Avg File size not completed successfully." + te.getMessage());
        }

    }
}