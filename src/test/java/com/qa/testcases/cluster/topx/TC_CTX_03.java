package com.qa.testcases.cluster.topx;

import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.constants.DirectoryConstants;
import com.qa.constants.PageConstants;
import com.qa.enums.UserAction;
import com.qa.pagefactory.SubTopPanelModulePageObject;
import com.qa.pagefactory.TopPanelPageObject;
import com.qa.pagefactory.clusters.TopXPageObject;
import com.qa.pagefactory.reports.ReportsArchiveScheduledPageObject;
import com.qa.scripts.HomePage;
import com.qa.scripts.clusters.TopX;
import com.qa.scripts.reports.ReportsArchiveSchedulePage;
import com.qa.utils.FileUtils;
import com.qa.utils.LoggingUtils;
import com.qa.utils.MouseActions;
import com.qa.utils.WaitExecuter;
import com.qa.utils.actions.UserActions;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * @author Ankur Jaiswal
 */

@Marker.All
@Marker.TopX
public class TC_CTX_03 extends BaseClass {
    private final LoggingUtils LOGGER = new LoggingUtils(TC_CTX_03.class);

    @Test
    public void TC_CTX_03_verifyCreateReport() {
        test = extent.startTest("TC_CTX_03.verifyCreateReport", "Running reports from UI");
        test.assignCategory(" Cluster - Top X");
        LOGGER.info("Go to TopX page.", test);
        WaitExecuter waitExecuter = new WaitExecuter(driver);
        UserActions userActions = new UserActions(driver);
        SubTopPanelModulePageObject topPanelComponentPageObject = new SubTopPanelModulePageObject(driver);
        userActions.performActionWithPolling(topPanelComponentPageObject.reports, UserAction.CLICK);
        waitExecuter.sleep(3000);
        TopX topX = new TopX(driver);

        waitExecuter.sleep(3000);
        ReportsArchiveSchedulePage reportsPage = new ReportsArchiveSchedulePage(driver);
        ReportsArchiveScheduledPageObject reportPageObj = new ReportsArchiveScheduledPageObject(driver);
        LOGGER.info("Click on + button", test);
        reportsPage.clickOnLatestReport(reportPageObj, PageConstants.ReportsArchiveNames.TopX);
        waitExecuter.waitUntilPageFullyLoaded();
        TopXPageObject topXPageObject = new TopXPageObject(driver);
        waitExecuter.sleep(2000);
        String statusXpath = reportsPage.clickOnReportName(reportPageObj, PageConstants.ReportsArchiveNames.TopX);

        String topXValue = "10";
        topX.setTopXNumber(topXValue);
        topX.clickOnModalRunButton();
        waitExecuter.waitUntilElementPresent(topXPageObject.archivesHeader);
        waitExecuter.waitUntilPageFullyLoaded();
        waitExecuter.waitUntilTextNotToBeInWebElement(topXPageObject.modalAfterRunButton, "Please Wait");

        Boolean isSuccessFullyStarted = topX.checkIfReportSuccessfullyStarted();
        if (!isSuccessFullyStarted) {
            Assert.fail("Report is not started successfully.");
        }
        LOGGER.info("Report is started successfully.", test);

        WebElement statusElement = driver.findElement(By.xpath(statusXpath));
        try{
            waitExecuter.waitUntilElementPresent(statusElement);
            waitExecuter.waitUntilTextToBeInWebElement(statusElement,
                "SUCCESS");
            LOGGER.pass("Verified TopX report is completed successfully", test);
        }catch (TimeoutException te) {
            throw new AssertionError("TopX report not completed successfully.");
        }

        topX.validateLatestReport("Top X", topXValue);
        LOGGER.pass("Correct value is displayed on report.", test);
        userActions.performActionWithPolling(topXPageObject.downloadJsonButton, UserAction.CLICK);
        waitExecuter.sleep(2000);
        userActions.performActionWithPolling(topXPageObject.downloadJsonButton2,UserAction.CLICK);
        waitExecuter.sleep(4000);
        LOGGER.info("Clicked on Download JSON", test);
        FileUtils.isFileDownloadedInUUIDFolder();
        LOGGER.pass("Verified Download CSV files present in directory.", test);
        FileUtils.deleteFilesFromFolder(DirectoryConstants.getDownloadsDir());
    }
}
