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
import com.qa.scripts.clusters.TopX;
import com.qa.scripts.reports.ReportsArchiveSchedulePage;
import com.qa.utils.FileUtils;
import com.qa.utils.LoggingUtils;
import com.qa.utils.MouseActions;
import com.qa.utils.WaitExecuter;
import com.qa.utils.actions.UserActions;
import org.openqa.selenium.TimeoutException;
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
    public void verifyCreateReport() {
        test = extent.startTest("TC_CTX_03.verifyCreateReport", "Running reports from UI");
        test.assignCategory(" Cluster - Top X");
        LOGGER.info("Go to TopX page.", test);
        WaitExecuter waitExecuter = new WaitExecuter(driver);
        TopPanelPageObject topPanelPageObject = new TopPanelPageObject(driver);
        SubTopPanelModulePageObject topPanelComponentPageObject = new SubTopPanelModulePageObject(driver);
        UserActions userActions = new UserActions(driver);
        TopX topX = new TopX(driver);

        MouseActions.clickOnElement(driver, topPanelComponentPageObject.reports);
        waitExecuter.sleep(3000);
        ReportsArchiveSchedulePage reportsPage = new ReportsArchiveSchedulePage(driver);
        ReportsArchiveScheduledPageObject reportPageObj = new ReportsArchiveScheduledPageObject(driver);
        LOGGER.info("Click on + button", test);
        reportsPage.clickOnLatestReport(reportPageObj, PageConstants.ReportsArchiveNames.TopX);

//        userActions.performActionWithPolling(topPanelPageObject.topXTab, UserAction.CLICK);
//        TopX topX = new TopX(driver);
//        topX.closeConfirmationMessageNotification();
//        topX.clickOnRunButton();
        //LOGGER.info("Click on run button", test);
        //topX.clickOnModalRunButton();
//        try {
//            waitExecuter.waitUntilTextToBeInWebElement(topX.getConfirmationMessage(),
//                "Top X Report completed successfully.");
//        } catch (TimeoutException te) {
//            Assert.assertTrue(false, "TopX Report is not completed");
//        }

        TopXPageObject topXPageObject = new TopXPageObject(driver);
        userActions.performActionWithPolling(topXPageObject.downloadJsonButton, UserAction.CLICK);
        LOGGER.info("Clicked on Download JSON", test);
        FileUtils.isFileDownloadedInUUIDFolder();
        LOGGER.pass("Verified Download CSV files present in directory.", test);
        FileUtils.deleteFilesFromFolder(DirectoryConstants.getDownloadsDir());
    }
}
