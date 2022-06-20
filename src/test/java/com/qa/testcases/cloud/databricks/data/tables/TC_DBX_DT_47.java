package com.qa.testcases.cloud.databricks.data.tables;

import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.scripts.cloud.databricks.DataTablesHelper;
import com.qa.utils.DateUtils;
import com.qa.utils.LoggingUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

/**
 * @author Ankur Jaiswal
 */

@Marker.DbxDataTables
public class TC_DBX_DT_47 extends BaseClass {
    private final LoggingUtils loggingUtils = new LoggingUtils(this.getClass());

    @Test(description = "Verify the \"Start Time\" column of \"Applications\" table.")
    public void verifyStartTimeColumnApplicationsTable() {
        test = extent.startTest("TC_DBX_DT_47.verifyStartTimeColumnApplicationsTable",
            "Verify the \"Start Time\" column of \"Applications\" table.");
        test.assignCategory("Databricks - Data");
        DataTablesHelper dataTablesHelper = new DataTablesHelper(driver, test);
        dataTablesHelper.clickOnDataTab();
        dataTablesHelper.clickOnDataTablesTab();
        dataTablesHelper.selectWorkspaceForConfiguredMetastore();
        dataTablesHelper.clickOnMoreInfoOfNthRow(0);
        try {
            dataTablesHelper.clickOnTabOnTableDetails("Applications");
            dataTablesHelper.selectAllApplicationsColumn();
            List<String> startTimeValues = dataTablesHelper.getColumnValuesFromApplicationsTable("Start Time");
            startTimeValues.stream().forEach(startTimeValue -> {
                Assert.assertNotEquals(startTimeValue, "");
                Assert.assertTrue(DateUtils.getDateTimeAsFormat(startTimeValue.replaceAll("\n"," "),"HH:mm:ss " +
                    "MM/dd/yy") != null, "Date is not in correct format");
            });
            loggingUtils.pass("Verify all applications contains correct start time value.", test);
        } finally {
            dataTablesHelper.backToTablesPage();
        }
    }
}
