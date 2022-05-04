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
@Marker.DBX.Data
@Marker.DbxDataTables
public class TC_DBX_DT_49 extends BaseClass {
    private final LoggingUtils loggingUtils = new LoggingUtils(this.getClass());

    @Test(description = "Verify the \"Queue\" column of \"Applications\" table.")
    public void verifyQueueColumnApplicationsTable() {
        test = extent.startTest("TC_DBX_DT_49.verifyQueueColumnApplicationsTable",
            "Verify the \"Queue\" column of \"Applications\" table.");
        test.assignCategory("Databricks - Data");
        DataTablesHelper dataTablesHelper = new DataTablesHelper(driver, test);
        dataTablesHelper.clickOnDataTab();
        dataTablesHelper.clickOnDataTablesTab();
        dataTablesHelper.selectWorkspaceForConfiguredMetastore();
        dataTablesHelper.clickOnMoreInfoOfNthRow(0);
        try {
            dataTablesHelper.clickOnTabOnTableDetails("Applications");
            dataTablesHelper.selectAllApplicationsColumn();
            List<String> queueValues = dataTablesHelper.getColumnValuesFromApplicationsTable("Queue");
            queueValues.stream().forEach(queueValue ->
                Assert.assertNotEquals(queueValue, ""));
            loggingUtils.pass("Verify all applications contains correct queue value.", test);
        } finally {
            dataTablesHelper.backToTablesPage();
        }
    }
}
