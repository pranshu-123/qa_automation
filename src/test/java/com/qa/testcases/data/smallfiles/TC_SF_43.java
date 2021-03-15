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
public class TC_SF_43 extends BaseClass {

    Logger logger = Logger.getLogger(TC_SF_43.class.getName());

    @Test(dataProvider = "clusterid-data-provider")
    public void validateReportsArchivedDownload(String clusterId) {
        test = extent.startTest("TC_SF_43.validateReportsArchivedDownload: " + clusterId,
                "Verify the Download action.");
        test.assignCategory("Data- Small Files and File reports");
        Log.startTestCase("TC_SF_43.validateReportsArchivedDownload");

        SubTopPanelModulePageObject topPanelComponentPageObject = new SubTopPanelModulePageObject(driver);
        ReportsArchiveScheduledPageObject reportPageObj = new ReportsArchiveScheduledPageObject(driver);
        SmallfilesPageObject smallfilesPageObject = new SmallfilesPageObject(driver);
        Smallfiles smallfiles = new Smallfiles(driver);
        WaitExecuter waitExecuter = new WaitExecuter(driver);
        try {
            // Navigate to Reports tab from header
            test.log(LogStatus.INFO, "Navigate to reports tab from header ");
            MouseActions.clickOnElement(driver, topPanelComponentPageObject.reports);
            waitExecuter.waitUntilPageFullyLoaded();
            String reportName = "Small File Report";
            smallfiles.verifyReportsArchived(reportPageObj, reportName, "downloadReport");
            logger.info("Clicked on Services and Versions Compatibility counts and downloaded.");
            test.log(LogStatus.PASS, "Verified Reports Archived for Services and Versions is downloadable.");
        } catch (TimeoutException | NoSuchElementException te) {
            throw new AssertionError("Small File Report not completed successfully.");
        }
    }
}