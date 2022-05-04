package com.qa.testcases.cloud.databricks.data.tables;

import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.scripts.cloud.databricks.DataTablesHelper;
import com.qa.utils.LoggingUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

/**
 * @author Ankur Jaiswal
 */
@Marker.DBX.Data
@Marker.DbxDataTables
public class TC_DBX_DT_43 extends BaseClass {
    private final LoggingUtils loggingUtils = new LoggingUtils(this.getClass());

    @Test(description = "Verify the \"Status\" column of \"Applications\" table.")
    public void verifyStatusColumnApplicationsTable() {
        test = extent.startTest("TC_DBX_DT_43.verifyStatusColumnApplicationsTable",
            "Verify the \"Status\" column of \"Applications\" table.");
        test.assignCategory("Databricks - Data");
        DataTablesHelper dataTablesHelper = new DataTablesHelper(driver, test);
        dataTablesHelper.clickOnDataTab();
        dataTablesHelper.clickOnDataTablesTab();
        dataTablesHelper.selectWorkspaceForConfiguredMetastore();
        dataTablesHelper.clickOnMoreInfoOfNthRow(0);
        try {
            dataTablesHelper.clickOnTabOnTableDetails("Applications");
            dataTablesHelper.selectAllApplicationsColumn();
            List<String> statusValues = dataTablesHelper.getColumnValuesFromApplicationsTable("Status");
            List<String> expectedStatus = Arrays.asList("success","failed","killed");
            statusValues.stream().forEach(typeValue -> Assert.assertTrue(expectedStatus.
                    contains(typeValue.toLowerCase().trim()), "Incorrect status value displayed. " + typeValue.trim()));
            loggingUtils.pass("Correct status value displayed.", test);
        } finally {
            dataTablesHelper.backToTablesPage();
        }
    }
}
