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
import org.openqa.selenium.NoSuchElementException;
import org.testng.annotations.Test;

/**
 * @author Ankur Jaiswal
 */

@Marker.DbxDataTables
@Marker.GCPDataTables
public class TC_DBX_DT_65 extends BaseClass {
    private final LoggingUtils loggingUtils = new LoggingUtils(this.getClass());

    @Test(dataProvider = "clusterid-data-provider",description = "Verify \"Errors\" tab on application details page")
    public void TC_DBX_DT_65_verifyErrorsTab(String clusterId) {
        test = extent.startTest("TC_DBX_DT_65.verifyErrorsTab",
            "Verify \"Errors\" tab on application details page");
        test.assignCategory("Databricks - Data");
        DataTablesHelper dataTablesHelper = new DataTablesHelper(driver, test);
        DataPageObject dataPageObject = new DataPageObject(driver);
        WaitExecuter waitExecuter = new WaitExecuter(driver);
        UserActions userActions = new UserActions(driver);
        dataTablesHelper.clickOnDataTab();
        dataTablesHelper.clickOnDataTablesTab();
        AllApps allApps = new AllApps(driver);
        allApps.selectWorkSpaceId(clusterId);
        dataPageObject.sortByDurationApp.click();
        waitExecuter.waitUntilElementPresent(dataPageObject.sortDown);
        dataPageObject.sortDown.click();
        /*dataTablesHelper.selectWorkspaceForConfiguredMetastore(clusterId);*/
        dataTablesHelper.clickOnMoreInfoOfNthRow(0);

        try {
            dataTablesHelper.clickOnTabOnTableDetails("Applications");
            dataTablesHelper.clickOnParentApp(0);
            userActions.performActionWithPolling(dataPageObject.dagGraphTabApplicationDetails, UserAction.CLICK);
            if(dataPageObject.noErrorsFoundElements.size() == 0) {
                loggingUtils.pass("Errors data displayed.", test);
            } else {
                loggingUtils.info("Errors data are not displayed..", test);
                loggingUtils.warning("Errors data are not displayed. Please verify manually", test);
            }
        }
        catch (NoSuchElementException e) {
            dataTablesHelper.closeApplicationDetailsPage();
            dataTablesHelper.backToTablesPage();
            loggingUtils.error("Exception occured " + e.getStackTrace(), test);
        } finally {
            dataTablesHelper.closeApplicationDetailsPage();
            dataTablesHelper.backToTablesPage();
        }
    }
}