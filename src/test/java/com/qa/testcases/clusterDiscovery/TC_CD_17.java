package com.qa.testcases.clusterDiscovery;

import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.enums.UserAction;
import com.qa.pagefactory.SubTopPanelModulePageObject;
import com.qa.pagefactory.reports.ReportsArchiveScheduledPageObject;
import com.qa.scripts.migration.ClusterDiscovery;
import com.qa.scripts.reports.ReportsArchiveSchedulePage;
import com.qa.utils.Log;
import com.qa.utils.MouseActions;
import com.qa.utils.actions.UserActions;
import com.relevantcodes.extentreports.LogStatus;
import java.util.logging.Logger;
import org.testng.annotations.Test;
import com.qa.utils.WaitExecuter;

@Marker.ReportArchive
@Marker.All
public class TC_CD_17 extends BaseClass{
    private static final Logger LOGGER = Logger.getLogger(TC_CD_17.class.getName());

    @Test(dataProvider = "clusterid-data-provider",description = "Verify user is able to download json file.")
    public void verifyErrorMessage(String clusterId){
        test = extent.startTest("TC_CD17_verifyErrorMessage: " + clusterId,
                "Verify Unravel UI displays a error message when trying to generate a report with time range less than two days.");
        test.assignCategory("Report Archive");
        Log.startTestCase("TC_CD17_verifyErrorMessage");

        // Initialize all classes objects
        test.log(LogStatus.INFO, "Initialize all class objects");
        LOGGER.info("Initialize all class objects");
        SubTopPanelModulePageObject topPanelComponentPageObject = new SubTopPanelModulePageObject(driver);
        ReportsArchiveSchedulePage reportsPage = new ReportsArchiveSchedulePage(driver);
        ReportsArchiveScheduledPageObject reportPageObj = new ReportsArchiveScheduledPageObject(driver);
        UserActions userActions = new UserActions(driver);
        WaitExecuter waitExecuter = new WaitExecuter(driver);
        ClusterDiscovery discovery = new ClusterDiscovery(driver);

        // Navigate to Reports tab from header
        test.log(LogStatus.INFO, "Navigate to reports tab from header and validate the reports with report status are present");
        userActions.performActionWithPolling(topPanelComponentPageObject.reports, UserAction.CLICK);
        waitExecuter.sleep(3000);

        // Validate Error Message displayed by UI
        reportsPage.validateErrorMessage(reportPageObj);



        // Click on Run button of modal window
        test.log(LogStatus.INFO, "Click on Run button of modal window");
        discovery.clickRunModalButton();

        test.log(LogStatus.PASS, "Unravel UI displays a error message when trying to generate a report with time range less than two days.");
    }
}
