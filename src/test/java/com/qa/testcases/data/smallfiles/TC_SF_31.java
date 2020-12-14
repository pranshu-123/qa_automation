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
import org.openqa.selenium.WebElement;
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
        test.assignCategory("Data- Small Files and File reports");
        Log.startTestCase("TC_SF_31.VerifyNumberOfFiles");

        WaitExecuter waitExecuter = new WaitExecuter(driver);
        SmallfilesPageObject smallfilesPageObject = new SmallfilesPageObject(driver);
        Smallfiles smallfiles = new Smallfiles(driver);
        smallfiles.commonPanelTabValidation(test,logger);
        smallfiles.closeConfirmationMessageNotification();

        // Click on small file search
        test.log(LogStatus.INFO, "Click on queue search box and search for path");
        logger.info("Click on queue search box and search for path");
        smallfilesPageObject.reportSearchBox.click();
        smallfilesPageObject.reportSearchBox.sendKeys("/tmp");

        if (smallfilesPageObject.getFileNameFromTable.size() > 0) {
            String selectedFileName = smallfilesPageObject.getFileNameFromTable.stream().findFirst()
                    .filter(WebElement::isDisplayed).get().getText();
            test.log(LogStatus.PASS, "File display in the table- " + selectedFileName);
        } else {
            Assert.assertTrue(smallfilesPageObject.whenNoDataDisplay.isDisplayed());
            test.log(LogStatus.FAIL, "There is no data display in the table");
        }


    }
}
