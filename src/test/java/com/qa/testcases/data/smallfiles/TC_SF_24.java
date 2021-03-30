package com.qa.testcases.data.smallfiles;

import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.pagefactory.data.SmallfilesPageObject;
import com.qa.scripts.data.Smallfiles;
import com.qa.testcases.appdetails.mapreduce.MR_050;
import com.qa.testcases.appdetails.spark.TC_spark_239;
import com.qa.utils.Log;
import com.qa.utils.WaitExecuter;
import com.relevantcodes.extentreports.LogStatus;

import java.util.logging.Logger;

import org.testng.Assert;
import org.testng.annotations.Test;

@Marker.DataSmallFiles
@Marker.All
public class TC_SF_24 extends BaseClass {

    Logger logger = Logger.getLogger(TC_SF_24.class.getName());

    @Test(dataProvider = "clusterid-data-provider")
    public void VerifySortByPath(String clusterId) {
        test = extent.startTest("TC_SF_24.VerifySortByPath: " + clusterId,
                "Verify the user is able sort the UI based on Path column for Small File Reports");
        test.assignCategory("Data- Small Files");
        Log.startTestCase("TC_SF_24.VerifySortByPath");

        WaitExecuter waitExecuter = new WaitExecuter(driver);
        SmallfilesPageObject smallfilesPageObject = new SmallfilesPageObject(driver);
        Smallfiles smallfiles = new Smallfiles(driver);
        smallfiles.commonPanelTabValidation(test, logger);
        smallfiles.closeConfirmationMessageNotification();

        // Sort Up by Path
        test.log(LogStatus.INFO, "Ascending order by Path");
        logger.info("Ascending order by Path");
        smallfilesPageObject.sortPath.click();
        waitExecuter.sleep(2000);
        Assert.assertTrue(smallfilesPageObject.sortUp.isDisplayed(), "Ascending order is not working");
        // Sort down by Path
        test.log(LogStatus.INFO, "Descending order by Path");
        logger.info("Descending order by Path");
        smallfilesPageObject.sortPath.click();
        waitExecuter.sleep(2000);
        Assert.assertTrue(smallfilesPageObject.sortDown.isDisplayed(), "Descending order is not working");
        test.log(LogStatus.PASS, "Verified sorting on Path.");

    }
}