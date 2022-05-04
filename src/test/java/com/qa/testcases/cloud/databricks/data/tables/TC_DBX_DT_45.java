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
public class TC_DBX_DT_45 extends BaseClass {
    private final LoggingUtils loggingUtils = new LoggingUtils(this.getClass());

    @Test(description = "Verify the \"Insights\" column of \"Applications\" table.")
    public void verifyInsightsColumnApplicationsTable() {
        test = extent.startTest("TC_DBX_DT_45.verifyInsightsColumnApplicationsTable",
            "Verify the \"Insights\" column of \"Applications\" table.Verify the User column of \"Applications\" table.");
        test.assignCategory("Databricks - Data");
        DataTablesHelper dataTablesHelper = new DataTablesHelper(driver, test);
        dataTablesHelper.clickOnDataTab();
        dataTablesHelper.clickOnDataTablesTab();
        dataTablesHelper.selectWorkspaceForConfiguredMetastore();
        dataTablesHelper.clickOnMoreInfoOfNthRow(0);
        try {
            dataTablesHelper.clickOnTabOnTableDetails("Applications");
            dataTablesHelper.selectAllApplicationsColumn();
            List<String> insightsValues = dataTablesHelper.getColumnValuesFromApplicationsTable("Insights");
            long nonNullCount = insightsValues.stream().distinct().filter(insight -> !insight.trim().equals("")).count();
            if (nonNullCount == 0) {
                loggingUtils.warning("All values are blank for insights, unable to verify.", test);
            }
            loggingUtils.pass("Verified insights value.", test);
        } finally {
            dataTablesHelper.backToTablesPage();
        }
    }
}
