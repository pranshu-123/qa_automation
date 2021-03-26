package com.qa.testcases.data.smallfiles;

import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.pagefactory.data.SmallfilesPageObject;
import com.qa.scripts.data.Smallfiles;
import com.qa.utils.Log;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;
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
        Smallfiles smallfiles = new Smallfiles(driver);
        smallfiles.commonPanelTabValidation(test, logger);
        smallfiles.closeConfirmationMessageNotification();
        try {
            // Click on small file search
            test.log(LogStatus.INFO, "Click on queue search box and search for path");
            logger.info("Click on queue search box and search for path");
            smallfilesPageObject.reportSearchBox.click();
            smallfilesPageObject.reportSearchBox.sendKeys("/");

            if (smallfilesPageObject.avgFileSize.size() > 0) {
                List<WebElement> avgFileSize = smallfilesPageObject.fileName;
                Assert.assertFalse(avgFileSize.isEmpty(), "There are no schedule report path");
                test.log(LogStatus.PASS, "File display in the table- " + avgFileSize);
            } else {
                Assert.assertTrue(smallfilesPageObject.whenNoDataDisplay.isDisplayed());
                test.log(LogStatus.FAIL, "Verified rows that match the search pattern for Avg File size not completed successfully");
            }
        } catch (Exception te) {
            throw new AssertionError("Verified rows that match the search pattern for Avg File size not completed successfully." + te.getMessage());
        }

    }
}