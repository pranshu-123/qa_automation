package com.qa.testcases.cloud.databricks.data.tables;

import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.scripts.cloud.databricks.DataTablesHelper;
import com.qa.utils.LoggingUtils;
import org.testng.annotations.Test;

/**
 * @author Ankur Jaiswal
 */

@Marker.DbxDataTables
@Marker.GCPDataTables
public class TC_DBX_DT_26 extends BaseClass {
    private final LoggingUtils loggingUtils = new LoggingUtils(TC_DBX_DT_26.class);

    @Test(description = "Validate whether user is able to filter data with Apps column value.")
    public void verifyTableDataFilterByAppsColumn() throws InterruptedException {
        test = extent.startTest("TC_DBX_DT_26.verifyTableDataFilterByAppsColumn", "Validate whether user is able to filter data " +
            "with Apps column value.");
        test.assignCategory("Databricks - Data");
        DataTablesHelper dataTablesHelper = new DataTablesHelper(driver, test);
        dataTablesHelper.clickOnDataTab();
        dataTablesHelper.clickOnDataTablesTab();
        dataTablesHelper.selectWorkspaceForConfiguredMetastore();
        dataTablesHelper.verifyTableFilterByColumn("Apps");
        loggingUtils.pass("Data is displayed based on applied search for Apps", test);
    }
}
