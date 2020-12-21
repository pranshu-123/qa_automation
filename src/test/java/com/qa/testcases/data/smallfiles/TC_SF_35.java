package com.qa.testcases.data.smallfiles;

import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.pagefactory.data.SmallfilesPageObject;
import com.qa.scripts.data.Smallfiles;
import com.qa.utils.Log;
import com.qa.utils.WaitExecuter;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;
import java.util.logging.Logger;
@Marker.DataSmallFiles
@Marker.All
public class TC_SF_35 extends BaseClass {
    Logger logger = Logger.getLogger(TC_SF_34.class.getName());

    @Test(dataProvider = "clusterid-data-provider")
    public void verifyMaxFileSize(String clusterId) {
        test = extent.startTest("TC_SF_35.verifyMaxFileSize: " + clusterId,
                "Verify the UI should display the only the rows that match the search pattern for Max File size");
        test.assignCategory("Data- Small Files and File reports");
        Log.startTestCase("TC_SF_35.verifyMaxFileSize");

        WaitExecuter waitExecuter = new WaitExecuter(driver);
        SmallfilesPageObject smallfilesPageObject = new SmallfilesPageObject(driver);
        Smallfiles smallfiles = new Smallfiles(driver);
        smallfiles.commonPanelTabValidation(test, logger);
        smallfiles.closeConfirmationMessageNotification();

        // Click on small file search
        test.log(LogStatus.INFO, "Click on queue search box and search for path");
        logger.info("Click on queue search box and search for path");
        smallfilesPageObject.reportSearchBox.click();
        smallfilesPageObject.reportSearchBox.sendKeys("192.23");

        if (smallfilesPageObject.maxFileSize.size() > 0) {
            List<WebElement> maxFileSize = smallfilesPageObject.pathName;
            Assert.assertFalse(maxFileSize.isEmpty(), "There are no schedule report path");
            test.log(LogStatus.PASS, "File display in the table- " + maxFileSize);
        } else {
            Assert.assertTrue(smallfilesPageObject.whenNoDataDisplay.isDisplayed());
            test.log(LogStatus.FAIL, "There is no data display in the table");
        }

    }
}