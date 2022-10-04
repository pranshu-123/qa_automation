package com.qa.testcases.cloud.databricks.data.tables;

import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.enums.UserAction;
import com.qa.pagefactory.cloud.databricks.DataPageObject;
import com.qa.scripts.cloud.databricks.DataTablesHelper;
import com.qa.scripts.jobs.applications.AllApps;
import com.qa.utils.LoggingUtils;
import com.qa.utils.actions.UserActions;
import org.testng.annotations.Test;

/**
 * @author Ankur Jaiswal
 */

@Marker.DbxDataTables
@Marker.GCPDataTables
public class TC_DBX_DT_66 extends BaseClass {
    private final LoggingUtils loggingUtils = new LoggingUtils(this.getClass());

    @Test(dataProvider = "clusterid-data-provider",description = "Verify \"Tag\" tab on application details page")
    public void TC_DBX_DT_66_verifyTagTab(String clusterId) {
        test = extent.startTest("TC_DBX_DT_66.verifyTagTab",
            "Verify \"Tag\" tab on application details page");
        test.assignCategory("Databricks - Data");
        DataTablesHelper dataTablesHelper = new DataTablesHelper(driver, test);
        dataTablesHelper.clickOnDataTab();
        dataTablesHelper.clickOnDataTablesTab();
        AllApps allApps = new AllApps(driver);
        allApps.selectWorkSpaceId(clusterId);
        /*dataTablesHelper.selectWorkspaceForConfiguredMetastore(clusterId);*/
        dataTablesHelper.clickOnMoreInfoOfNthRow(0);
        DataPageObject dataPageObject = new DataPageObject(driver);
        UserActions userActions = new UserActions(driver);
        try {
            dataTablesHelper.clickOnTabOnTableDetails("Applications");
            dataTablesHelper.clickOnParentApp(0);
            userActions.performActionWithPolling(dataPageObject.tagsTabApplicationDetails, UserAction.CLICK);
            if(dataPageObject.noTagsFoundElements.size() == 0) {
                loggingUtils.pass("Tags data displayed.", test);
            } else {
                loggingUtils.warning("Tags data are not displayed. Please verify manually", test);
            }
            dataTablesHelper.closeApplicationDetailsPage();
        } finally {

            dataTablesHelper.backToTablesPage();
        }
    }
}
