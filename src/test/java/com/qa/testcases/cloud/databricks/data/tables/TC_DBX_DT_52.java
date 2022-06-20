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
public class TC_DBX_DT_52 extends BaseClass {
    private final LoggingUtils loggingUtils = new LoggingUtils(this.getClass());

    @Test(description = "Verify the \"Events\" column of \"Applications\" table.")
    public void verifyEventsColumnApplicationsTable() {
        test = extent.startTest("TC_DBX_DT_52.verifyEventsColumnApplicationsTable",
            "Verify the \"Events\" column of \"Applications\" table.");
        test.assignCategory("Databricks - Data");
        DataTablesHelper dataTablesHelper = new DataTablesHelper(driver, test);
        dataTablesHelper.clickOnDataTab();
        dataTablesHelper.clickOnDataTablesTab();
        dataTablesHelper.selectWorkspaceForConfiguredMetastore();
        dataTablesHelper.clickOnMoreInfoOfNthRow(0);
        try {
            dataTablesHelper.clickOnTabOnTableDetails("Applications");
            dataTablesHelper.selectAllApplicationsColumn();
            List<String> eventsValues = dataTablesHelper.getColumnValuesFromApplicationsTable("Events");
            eventsValues.stream().forEach(eventValue ->
                Assert.assertNotEquals(eventValue, ""));
            loggingUtils.pass("Verify all applications contains correct Events value.", test);
        } finally {
            dataTablesHelper.backToTablesPage();
        }
    }
}
