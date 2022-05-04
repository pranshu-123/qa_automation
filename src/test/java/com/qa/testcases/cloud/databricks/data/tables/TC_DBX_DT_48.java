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
public class TC_DBX_DT_48 extends BaseClass {
    private final LoggingUtils loggingUtils = new LoggingUtils(this.getClass());

    @Test(description = "Verify the \"Duration\" column of \"Applications\" table.")
    public void verifyDurationColumnApplicationsTable() {
        test = extent.startTest("TC_DBX_DT_48.verifyDurationColumnApplicationsTable",
            "Verify the \"Duration\" column of \"Applications\" table.");
        test.assignCategory("Databricks - Data");
        DataTablesHelper dataTablesHelper = new DataTablesHelper(driver, test);
        dataTablesHelper.clickOnDataTab();
        dataTablesHelper.clickOnDataTablesTab();
        dataTablesHelper.selectWorkspaceForConfiguredMetastore();
        dataTablesHelper.clickOnMoreInfoOfNthRow(0);
        try {
            dataTablesHelper.clickOnTabOnTableDetails("Applications");
            dataTablesHelper.selectAllApplicationsColumn();
            List<String> durationValues = dataTablesHelper.getColumnValuesFromApplicationsTable("Duration");
            durationValues.stream().forEach(durationValue -> {
                Assert.assertNotEquals(durationValue, "");
                Assert.assertTrue(durationValue.matches(".*[dhms].*"), "Days, Hours, Minutes or Seconds does not " +
                    "exists in value");
            });
            loggingUtils.pass("Verify all applications contains correct duration value.", test);
        } finally {
            dataTablesHelper.backToTablesPage();
        }
    }
}
