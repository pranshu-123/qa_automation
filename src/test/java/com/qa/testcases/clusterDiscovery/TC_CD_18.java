package com.qa.testcases.clusterDiscovery;

import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.enums.UserAction;
import com.qa.pagefactory.SubTopPanelModulePageObject;
import com.qa.pagefactory.reports.ReportsArchiveScheduledPageObject;
import com.qa.scripts.reports.ReportsArchiveSchedulePage;
import com.qa.utils.Log;
import com.qa.utils.MouseActions;
import com.qa.utils.WaitExecuter;
import com.qa.utils.actions.UserActions;
import com.relevantcodes.extentreports.LogStatus;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

import java.util.logging.Logger;


@Marker.ReportArchive
@Marker.All
public class TC_CD_18 extends BaseClass {
    private static final java.util.logging.Logger LOGGER = Logger.getLogger(TC_CD_18.class.getName());
    public void verifyaAppropriateErrorMessage(String clusterId){

        test = extent.startTest("TC_CD18_verifyErrorMessage: " + clusterId,
                "Verify Unravel UI displays appropriate error message when data is not available for a perticular range");
        test.assignCategory("Report Archive");
        Log.startTestCase("TC_CD18_verifyErrorMessage");

        // Initialize all classes objects
        test.log(LogStatus.INFO, "Initialize all class objects");
        LOGGER.info("Initialize all class objects");
        SubTopPanelModulePageObject topPanelComponentPageObject = new SubTopPanelModulePageObject(driver);
        ReportsArchiveSchedulePage reportsPage = new ReportsArchiveSchedulePage(driver);
        ReportsArchiveScheduledPageObject reportPageObj = new ReportsArchiveScheduledPageObject(driver);
        UserActions userActions = new UserActions(driver);
        WaitExecuter waitExecuter = new WaitExecuter(driver);

        // Navigate to Reports tab from header
        test.log(LogStatus.INFO, "Navigate to reports tab from header and validate the reports with report status are present");
        userActions.performActionWithPolling(topPanelComponentPageObject.reports, UserAction.CLICK);
        waitExecuter.sleep(3000);

        // Validate Error Message displayed by UI
        reportsPage.validateErrorMessage(reportPageObj);
        test.log(LogStatus.PASS, "Verify Unravel UI displays appropriate error message when data is not available for a perticular range");
    }
}
