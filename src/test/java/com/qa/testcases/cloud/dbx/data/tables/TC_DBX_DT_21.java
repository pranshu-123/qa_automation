package com.qa.testcases.cloud.dbx.data.tables;

import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.scripts.cloud.dbx.DataTablesHelper;
import com.qa.utils.LoggingUtils;
import org.testng.annotations.Test;

/**
 * @author Ankur Jaiswal
 */

@Marker.DBX.Data
public class TC_DBX_DT_21 extends BaseClass {
    private final LoggingUtils loggingUtils = new LoggingUtils(TC_DBX_DT_21.class);

    @Test(description = "Validate whether user is able to filter data with File System column value.")
    public void verifyTableDataFilterByFileSystemColumn() {
        test = extent.startTest("verifyTableDataFilterByFileSystemColumn", "Validate whether user is able to filter data " +
            "with File System column value.");
        test.assignCategory("Databricks - Data");
        DataTablesHelper dataTablesHelper = new DataTablesHelper(driver, test);
        dataTablesHelper.clickOnDataTab();
        dataTablesHelper.clickOnDataTablesTab();
        dataTablesHelper.selectWorkspaceForConfiguredMetastore();
        dataTablesHelper.verifyTableFilterByColumn("File System");
        loggingUtils.pass("Data is displayed based on applied search for File System", test);
    }
}
