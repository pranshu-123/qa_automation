package com.qa.testcases.migration.servicesversionscompatibility;

import com.qa.base.BaseClass;
import com.qa.pagefactory.SubTopPanelModulePageObject;
import com.qa.pagefactory.reports.ReportsArchiveScheduledPageObject;
import com.qa.scripts.migration.ServicesAndVersionsCompatibility;
import com.qa.utils.Log;
import com.qa.utils.MouseActions;
import com.qa.utils.WaitExecuter;
import com.relevantcodes.extentreports.LogStatus;
import org.testng.annotations.Test;

import java.util.logging.Logger;

public class TC_MP_SC_28 extends BaseClass {

    private static final Logger logger = Logger.getLogger(TC_MP_SC_28.class.getName());

    @Test(dataProvider = "clusterid-data-provider")
    public void validateReportsArchivedDelete(String clusterId) {

        test = extent.startTest("TC_MP_SC_28.validateReportsArchivedDelete: " + clusterId,
                "Reports Archived Validate the Delete action: Services and Versions are Compatible ");
        test.assignCategory(" Migration - Services And Versions Compatibility ");
        Log.startTestCase("TC_MP_SC_28.validateReportsArchivedDelete");

        // Initialize all classes objects
        test.log(LogStatus.INFO, "Initialize all class objects");
        logger.info("Initialize all class objects");
        SubTopPanelModulePageObject topPanelComponentPageObject = new SubTopPanelModulePageObject(driver);
        ReportsArchiveScheduledPageObject reportPageObj = new ReportsArchiveScheduledPageObject(driver);
        ServicesAndVersionsCompatibility servicesAndVersionsCompatibility = new ServicesAndVersionsCompatibility(driver);
        WaitExecuter waitExecuter = new WaitExecuter(driver);

        // Navigate to Reports tab from header
        test.log(LogStatus.INFO, "Navigate to reports tab from header ");
        MouseActions.clickOnElement(driver, topPanelComponentPageObject.reports);
        waitExecuter.waitUntilPageFullyLoaded();
        String reportName = "Services and Versions Compatibility";
        servicesAndVersionsCompatibility.verifyReportsArchived(reportPageObj, reportName, "deleteReport");
        logger.info("Clicked on Services and Versions Compatibility counts and delete.");
        test.log(LogStatus.PASS, "Verified Reports Archived for Services and Versions is deletable.");

    }
}
