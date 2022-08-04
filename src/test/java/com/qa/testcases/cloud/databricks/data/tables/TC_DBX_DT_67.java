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
public class TC_DBX_DT_67 extends BaseClass {
    private final LoggingUtils loggingUtils = new LoggingUtils(this.getClass());

    @Test(description = "Validate the close button of application page")
    public void validateCloseButtonApplicationDetailsPage() {
        test = extent.startTest("TC_DBX_DT_66.validateCloseButtonApplicationDetailsPage",
            "Validate the close button of application page");
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
            userActions.performActionWithPolling(dataPageObject.closeApplicationsDetailsButton, UserAction.CLICK);
            Assert.assertTrue(driver.getCurrentUrl().contains("/data/table/"), "Close button did not close the " +
                "application details page");
            loggingUtils.pass("Close button closed the table details page",test);
        } finally {
            dataTablesHelper.backToTablesPage();
        }
    }
}
