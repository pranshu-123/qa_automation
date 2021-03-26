package com.qa.testcases.data.smallfiles;

import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.pagefactory.SubTopPanelModulePageObject;
import com.qa.pagefactory.data.SmallfilesPageObject;
import com.qa.pagefactory.reports.ReportsArchiveScheduledPageObject;
import com.qa.scripts.data.Smallfiles;
import com.qa.utils.Log;
import com.qa.utils.MouseActions;
import com.qa.utils.WaitExecuter;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.testng.annotations.Test;

import java.util.logging.Logger;
@Marker.DataSmallFiles
@Marker.All
public class TC_SF_45 extends BaseClass {

    Logger logger = Logger.getLogger(TC_SF_45.class.getName());

    @Test(dataProvider = "clusterid-data-provider")
    public void validateReportsArchivedView(String clusterId) {
        test = extent.startTest("TC_SF_45.validateReportsArchivedView: " + clusterId,
                "Verify the UI should display the selected File report.");
        test.assignCategory("Data- Small Files");
        Log.startTestCase("TC_SF_45.validateReportsArchivedView");

        SubTopPanelModulePageObject topPanelComponentPageObject = new SubTopPanelModulePageObject(driver);
        ReportsArchiveScheduledPageObject reportPageObj = new ReportsArchiveScheduledPageObject(driver);
        SmallfilesPageObject smallfilesPageObject = new SmallfilesPageObject(driver);
        Smallfiles smallfiles = new Smallfiles(driver);
        WaitExecuter waitExecuter = new WaitExecuter(driver);

        // Navigate to Reports tab from header
        try {
            test.log(LogStatus.INFO, "Navigate to reports tab from header ");
            MouseActions.clickOnElement(driver, topPanelComponentPageObject.reports);
            waitExecuter.waitUntilPageFullyLoaded();
            String reportName = "Small File Report";
            smallfiles.verifyReportsArchived(reportName, "viewReport");
            logger.info("Clicked on Small File Report counts and View.");
            test.log(LogStatus.PASS, "Verified Reports Archived for Small File Report is viewable.");
        } catch (TimeoutException | NoSuchElementException te) {
            throw new AssertionError("Verified the File report not been scheduled successfully.");
        }
    }
}
