package com.qa.testcases.cloud.databricks.data.tables;

import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.enums.UserAction;
import com.qa.pagefactory.cloud.databricks.DataPageObject;
import com.qa.scripts.cloud.databricks.DataTablesHelper;
import com.qa.utils.LoggingUtils;
import com.qa.utils.actions.UserActions;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * @author Ankur Jaiswal
 */

@Marker.DbxDataTables
@Marker.GCPDataTables
public class TC_DBX_DT_64 extends BaseClass {
    private final LoggingUtils loggingUtils = new LoggingUtils(this.getClass());

    @Test(description = "Verify \"Dag graph\" tab on application details page")
    public void verifyDagGraphTab() {
        test = extent.startTest("TC_DBX_DT_64.verifyDagGraphTab",
            "Verify \"Dag graph\" tab on application details page");
        test.assignCategory("Databricks - Data");
        DataTablesHelper dataTablesHelper = new DataTablesHelper(driver, test);
        dataTablesHelper.clickOnDataTab();
        dataTablesHelper.clickOnDataTablesTab();
        dataTablesHelper.selectWorkspaceForConfiguredMetastore();
        dataTablesHelper.clickOnMoreInfoOfNthRow(0);
        DataPageObject dataPageObject = new DataPageObject(driver);
        UserActions userActions = new UserActions(driver);
        try {
            dataTablesHelper.clickOnTabOnTableDetails("Applications");
            dataTablesHelper.clickOnParentAppOfNthRow(0);
            userActions.performActionWithPolling(dataPageObject.dagGraphTabApplicationDetails, UserAction.CLICK);
            loggingUtils.warning("Daggraph not loaded. Please verify manually", test);
        } finally {
            dataTablesHelper.closeApplicationDetailsPage();
            dataTablesHelper.backToTablesPage();
        }
    }
}
