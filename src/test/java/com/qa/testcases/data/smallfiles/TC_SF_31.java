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
public class TC_SF_31 extends BaseClass {
    Logger logger = Logger.getLogger(TC_SF_31.class.getName());

    @Test(dataProvider = "clusterid-data-provider")
    public void VerifyNumberOfFiles(String clusterId) {
        test = extent.startTest("TC_SF_31.VerifyNumberOfFiles: " + clusterId,
                "Verify user is able search a particular number of files");
        test.assignCategory("Data- Small Files");
        Log.startTestCase("TC_SF_31.VerifyNumberOfFiles");

        WaitExecuter waitExecuter = new WaitExecuter(driver);
        UserActions userActions = new UserActions(driver);
        SmallfilesPageObject smallfilesPageObject = new SmallfilesPageObject(driver);
        Smallfiles smallfiles = new Smallfiles(driver);
        smallfiles.commonPanelTabValidation(test, logger);
        smallfiles.closeConfirmationMessageNotification();
        waitExecuter.waitUntilPageFullyLoaded();
        smallfiles.verifyAllFileSizePathSearchOption(clusterId, 2, 2);
        test.log(LogStatus.PASS, "Verify the user is able to search the Path in the small file table");
    }
}