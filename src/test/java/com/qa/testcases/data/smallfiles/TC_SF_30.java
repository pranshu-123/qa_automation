package com.qa.testcases.data.smallfiles;

import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.pagefactory.data.SmallfilesPageObject;
import com.qa.scripts.data.Smallfiles;
import com.qa.utils.Log;
import com.qa.utils.WaitExecuter;
import com.qa.utils.actions.UserActions;
import com.relevantcodes.extentreports.LogStatus;
import org.testng.annotations.Test;

import java.util.logging.Logger;

@Marker.DataSmallFiles
@Marker.All
public class TC_SF_30 extends BaseClass {
    Logger logger = Logger.getLogger(TC_SF_30.class.getName());

    @Test(dataProvider = "clusterid-data-provider",description="P0-Validate that the user is able to search based on Path column for Small file table. ")
    public void VerifyFilePath(String clusterId) {
        test = extent.startTest("TC_SF_30.VerifyFilePath: " + clusterId);
        Log.startTestCase("TC_SF_30.VerifyFilePath");

        WaitExecuter waitExecuter = new WaitExecuter(driver);
        SmallfilesPageObject smallfilesPageObject = new SmallfilesPageObject(driver);
        Smallfiles smallfiles = new Smallfiles(driver);
        smallfiles.commonPanelTabValidation(test, logger);
        smallfiles.closeConfirmationMessageNotification();
        waitExecuter.waitUntilPageFullyLoaded();
        smallfiles.verifyAllFileSizePathSearchOption(clusterId, 1, 1);
        test.log(LogStatus.PASS, "Verify the user is able to search the Path in the small file table");
    }

    @Test(dataProvider = "clusterid-data-provider",description = "P0-Validate that the user is able to search based on Files column for Small file table.")
    public void VerifyNumberOfFiles(String clusterId) {
        Log.startTestCase("TC_SF_31.VerifyNumberOfFiles");

        WaitExecuter waitExecuter = new WaitExecuter(driver);
      SmallfilesPageObject smallfilesPageObject = new SmallfilesPageObject(driver);
        Smallfiles smallfiles = new Smallfiles(driver);
        smallfiles.commonPanelTabValidation(test, logger);
        smallfiles.closeConfirmationMessageNotification();
        waitExecuter.waitUntilPageFullyLoaded();
        smallfiles.verifyAllFileSizePathSearchOption(clusterId, 2, 2);
        test.log(LogStatus.PASS, "Verify the user is able to search the Files in the small file table");
    }

    @Test(dataProvider = "clusterid-data-provider",description="P0-Validate that the user is able to search based on Avg File Size column for Small file table. ")
    public void verifyAvgFileSize(String clusterId) {
        Log.startTestCase("TC_SF_32.verifyAvgFileSize");

        SmallfilesPageObject smallfilesPageObject = new SmallfilesPageObject(driver);
        WaitExecuter waitExecuter = new WaitExecuter(driver);
        Smallfiles smallfiles = new Smallfiles(driver);
        smallfiles.commonPanelTabValidation(test, logger);
        smallfiles.closeConfirmationMessageNotification();
        // Click on small file search
        test.log(LogStatus.INFO, "Click on queue search box and search for path");
        logger.info("Click on queue search box and search for path");
        smallfiles.verifyAllFileSizePathSearchOption(clusterId, 3, 3);
        waitExecuter.waitUntilPageFullyLoaded();
        test.log(LogStatus.PASS, "Verify the user is able to search the Avg File Size in the small file table");
    }

    @Test(dataProvider = "clusterid-data-provider",description="P0-Validate that the user is able to search based on Total File Siz column for Small file table.")
    public void verifyTotalFileSize(String clusterId) {
        Log.startTestCase("TC_SF_33.verifyTotalFileSize");

        WaitExecuter waitExecuter = new WaitExecuter(driver);
        SmallfilesPageObject smallfilesPageObject = new SmallfilesPageObject(driver);
        Smallfiles smallfiles = new Smallfiles(driver);
        smallfiles.commonPanelTabValidation(test, logger);
        smallfiles.closeConfirmationMessageNotification();

        // Click on small file search
        test.log(LogStatus.INFO, "Click on queue search box and search for path");
        logger.info("Click on queue search box and search for path");
        smallfiles.verifyAllFileSizePathSearchOption(clusterId, 4, 4);
        waitExecuter.waitUntilPageFullyLoaded();
        test.log(LogStatus.PASS, "Verify the user is able to search the Total File size in the small file table");

    }

    @Test(dataProvider = "clusterid-data-provider",description="Validate that the user is able to search based on Min File Size column for Small file table.")
    public void verifyMinFileSize(String clusterId) {
        Log.startTestCase("TC_SF_34.verifyMinFileSize");

        SmallfilesPageObject smallfilesPageObject = new SmallfilesPageObject(driver);
        WaitExecuter waitExecuter = new WaitExecuter(driver);
        Smallfiles smallfiles = new Smallfiles(driver);
        smallfiles.commonPanelTabValidation(test, logger);
        smallfiles.closeConfirmationMessageNotification();
        // Click on small file search
        test.log(LogStatus.INFO, "Click on queue search box and search for path");
        logger.info("Click on queue search box and search for path");
        smallfiles.verifyAllFileSizePathSearchOption(clusterId, 5, 5);
        waitExecuter.waitUntilPageFullyLoaded();
        test.log(LogStatus.PASS, "Verify the user is able to search the Min File size in the small file table");


    }

    @Test(dataProvider = "clusterid-data-provider",description = "Validate that the user is able to search based on Max File Size column for Small file table.")
    public void verifyMaxFileSize(String clusterId) {
        Log.startTestCase("TC_SF_35.verifyMaxFileSize");

        WaitExecuter waitExecuter = new WaitExecuter(driver);
        SmallfilesPageObject smallfilesPageObject = new SmallfilesPageObject(driver);
        Smallfiles smallfiles = new Smallfiles(driver);
        smallfiles.commonPanelTabValidation(test, logger);
        smallfiles.closeConfirmationMessageNotification();

        // Click on small file search
        test.log(LogStatus.INFO, "Click on queue search box and search for path");
        logger.info("Click on queue search box and search for path");
        smallfiles.verifyAllFileSizePathSearchOption(clusterId, 6, 6);
        waitExecuter.waitUntilPageFullyLoaded();
        test.log(LogStatus.PASS, "Verify the user is able to search the Max File size in the small file table");


    }


}



