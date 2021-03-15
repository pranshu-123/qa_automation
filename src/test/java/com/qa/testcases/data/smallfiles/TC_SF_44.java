package com.qa.testcases.data.smallfiles;

import com.qa.base.BaseClass;
import com.qa.pagefactory.SubTopPanelModulePageObject;
import com.qa.pagefactory.data.SmallfilesPageObject;
import com.qa.pagefactory.reports.ReportsArchiveScheduledPageObject;
import com.qa.scripts.data.Smallfiles;
import com.qa.utils.Log;
import com.qa.utils.MouseActions;
import com.qa.utils.WaitExecuter;
import com.relevantcodes.extentreports.LogStatus;
import org.testng.annotations.Test;

import java.util.logging.Logger;

public class TC_SF_44 extends BaseClass {

    Logger logger = Logger.getLogger(TC_SF_43.class.getName());

    @Test(dataProvider = "clusterid-data-provider")
    public void validateReportsArchivedDelete(String clusterId) {
        test = extent.startTest("TC_SF_44.validateReportsArchivedDelete: " + clusterId,
                "Verify the Download action.");
        test.assignCategory("Data- Small Files and File reports");
        Log.startTestCase("TC_SF_44.validateReportsArchivedDelete");

        SubTopPanelModulePageObject topPanelComponentPageObject = new SubTopPanelModulePageObject(driver);
        ReportsArchiveScheduledPageObject reportPageObj = new ReportsArchiveScheduledPageObject(driver);
        SmallfilesPageObject smallfilesPageObject = new SmallfilesPageObject(driver);
        Smallfiles smallfiles = new Smallfiles(driver);
        WaitExecuter waitExecuter = new WaitExecuter(driver);

        // Navigate to Reports tab from header
        test.log(LogStatus.INFO, "Navigate to reports tab from header ");
        MouseActions.clickOnElement(driver, topPanelComponentPageObject.reports);
        waitExecuter.waitUntilPageFullyLoaded();
        String reportName = "Small File Report";
        smallfiles.verifyReportsArchived(reportPageObj, reportName, "deleteReport");
        logger.info("Clicked on Services and Versions Compatibility counts and delete.");
        test.log(LogStatus.PASS, "Verified Reports Archived for Services and Versions is deletable.");
    }
}