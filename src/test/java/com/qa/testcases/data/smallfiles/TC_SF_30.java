package com.qa.testcases.data.smallfiles;

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

public class TC_SF_30 extends BaseClass {
    private static final Logger LOGGER = Logger.getLogger(TC_SF_30.class.getName());

    @Test(dataProvider = "clusterid-data-provider")
    public void VerifyFilePath(String clusterId) {
        test = extent.startTest("TC_SF_30.VerifyFilePath: " + clusterId, "Verify User is able " +
                "Verify user is able search a particular file path");
        test.assignCategory("Data- Small Files and File reports");
        Log.startTestCase("TC_SF_30.VerifyFilePath");

        WaitExecuter waitExecuter = new WaitExecuter(driver);
        SubTopPanelModulePageObject topPanelComponentPageObject = new SubTopPanelModulePageObject(driver);
        waitExecuter.waitUntilElementPresent(topPanelComponentPageObject.data);
        waitExecuter.sleep(2000);
        MouseActions.clickOnElement(driver, topPanelComponentPageObject.data);
        LOGGER.info("Clicked on Data Tab");
        test.log(LogStatus.INFO, "Clicked on Data Tab");

        TopPanelPageObject topPanelPageObject = new TopPanelPageObject(driver);
        waitExecuter.waitUntilElementPresent(topPanelPageObject.smallFilesTab);
        waitExecuter.waitUntilPageFullyLoaded();
        waitExecuter.waitUntilElementClickable(topPanelPageObject.smallFilesTab);
        waitExecuter.sleep(3000);
        MouseActions.clickOnElement(driver, topPanelPageObject.smallFilesTab);
        LOGGER.info("Clicked on small FilesTab Tab");
        test.log(LogStatus.INFO, "Clicked on small FilesTab Tab");
        ;

        SmallfilesPageObject smallfilesPageObject = new SmallfilesPageObject(driver);

        Smallfiles smallfiles = new Smallfiles(driver);
        smallfiles.closeConfirmationMessageNotification();


        // Click on small file Reports search
        test.log(LogStatus.INFO, "Click on queue search box and search for path");
        LOGGER.info("Click on queue search box and search for path");
        smallfilesPageObject.reportSearchBox.click();
        smallfilesPageObject.reportSearchBox.sendKeys("/tmp/hive/unravel");

        if (smallfilesPageObject.getPathNameFromTable.size() > 0) {
            String selectedPathName = smallfilesPageObject.getPathNameFromTable.stream().findFirst()
                    .filter(WebElement::isDisplayed).get().getText();
            test.log(LogStatus.PASS, "Path display in the table- " + selectedPathName);
        } else {
            Assert.assertTrue(smallfilesPageObject.whenNoDataDisplay.isDisplayed());
            test.log(LogStatus.FAIL, "There is no data display in the table");
        }


    }
}
