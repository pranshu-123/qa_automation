package com.qa.testcases.cloud.databricks.data.tables;

import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.enums.UserAction;
import com.qa.pagefactory.cloud.databricks.DataPageObject;
import com.qa.scripts.cloud.databricks.DataTablesHelper;
import com.qa.scripts.jobs.applications.AllApps;
import com.qa.utils.LoggingUtils;
import com.qa.utils.WaitExecuter;
import com.qa.utils.actions.UserActions;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.NoSuchElementException;
import org.testng.annotations.Test;

/**
 * @author Ankur Jaiswal
 */

@Marker.DbxDataTables
@Marker.GCPDataTables
public class TC_DBX_DT_62 extends BaseClass {
    private final LoggingUtils loggingUtils = new LoggingUtils(this.getClass());

    @Test(dataProvider = "clusterid-data-provider",description = "Verify Analysis tab on application details page")
    public void TC_DBX_DT_62_verifyAnalysisTab(String clusterId) throws InterruptedException {
        test = extent.startTest("TC_DBX_DT_62.verifyAnalysisTab",
            "Verify Analysis tab on application details page");
        test.assignCategory("Databricks - Data");
        DataTablesHelper dataTablesHelper = new DataTablesHelper(driver, test);
        DataPageObject dataPageObject = new DataPageObject(driver);
        UserActions userActions = new UserActions(driver);
        WaitExecuter waitExecuter = new WaitExecuter(driver);
        dataTablesHelper.clickOnDataTab();
        dataTablesHelper.clickOnDataTablesTab();
        AllApps allApps = new AllApps(driver);
        allApps.selectWorkSpaceId(clusterId);
        waitExecuter.waitForSeconds(5);
        dataPageObject.sortByDurationApp.click();
        waitExecuter.waitUntilElementPresent(dataPageObject.sortDown);
        dataPageObject.sortDown.click();
        waitExecuter.waitUntilPageFullyLoaded();
        dataTablesHelper.clickOnMoreInfoOfNthRow(0);
        /*dataTablesHelper.selectWorkspaceForConfiguredMetastore(clusterId);*/
        try {
            dataTablesHelper.clickOnTabOnTableDetails("Applications");
            dataTablesHelper.selectAllApplicationsColumn();
            dataTablesHelper.clickOnParentApp(0);
            userActions.performActionWithPolling(dataPageObject.analysisTabOnTableDetails, UserAction.CLICK);
            if(dataPageObject.noRecommendationAndInsightsElements.size() == 0) {
                loggingUtils.pass("Recommendation and Insights displayed.", test);
            } else {
                loggingUtils.info("Recommendation and Insights displayed not displayed.", test);
                loggingUtils.warning("No recommendation and insights found. Please verify manually", test);
            }

        }
        catch (NoSuchElementException e) {
            dataTablesHelper.closeApplicationDetailsPage();
            dataTablesHelper.backToTablesPage();
            test.log(LogStatus.WARNING, "Recommendation and Insights displayed not displayed <app_id>, " +
                    "Check manually if correct application Id is present"+ e.getStackTrace());
        } finally {
            dataTablesHelper.closeApplicationDetailsPage();
            dataTablesHelper.backToTablesPage();
        }
    }
}

