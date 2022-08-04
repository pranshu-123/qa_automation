package com.qa.testcases.cloud.databricks.data.tables;

import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.scripts.cloud.databricks.DataTablesHelper;
import com.qa.utils.LoggingUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

/**
 * @author Ankur Jaiswal
 */

@Marker.DbxDataTables
@Marker.GCPDataTables
public class TC_DBX_DT_44 extends BaseClass {
    private final LoggingUtils loggingUtils = new LoggingUtils(this.getClass());

    @Test(description = "Verify the User column of \"Applications\" table.")
    public void verifyUserColumnApplicationsTable() {
        test = extent.startTest("TC_DBX_DT_44.verifyUserColumnApplicationsTable",
            "Verify the User column of \"Applications\" table.");
        test.assignCategory("Databricks - Data");
        DataTablesHelper dataTablesHelper = new DataTablesHelper(driver, test);
        dataTablesHelper.clickOnDataTab();
        dataTablesHelper.clickOnDataTablesTab();
        dataTablesHelper.selectWorkspaceForConfiguredMetastore();
        dataTablesHelper.clickOnMoreInfoOfNthRow(0);
        try {
            dataTablesHelper.clickOnTabOnTableDetails("Applications");
            dataTablesHelper.selectAllApplicationsColumn();
            List<String> userValues = dataTablesHelper.getColumnValuesFromApplicationsTable("User");
            userValues.stream().forEach(typeValue -> Assert.assertNotEquals(typeValue, ""));
            loggingUtils.pass("Verify all applications contains user informations.", test);
        } finally {
            dataTablesHelper.backToTablesPage();
        }
    }
}
