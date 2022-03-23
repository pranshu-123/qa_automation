package com.qa.testcases.cloud.databricks.data.tables;

import com.qa.base.BaseClass;
import com.qa.scripts.cloud.databricks.DataTablesHelper;
import com.qa.utils.LoggingUtils;
import org.testng.annotations.Test;

/**
 * @author Ankur Jaiswal
 */


public class TC_DBX_DT_23 extends BaseClass {
    private final LoggingUtils loggingUtils = new LoggingUtils(TC_DBX_DT_23.class);

    @Test(description = "Validate whether user is able to filter data with Created column value.")
    public void verifyTableDataFilterByCreatedColumn() {
        test = extent.startTest("verifyTableDataFilterByCreatedColumn", "Validate whether user is able to filter data " +
            "with Created column value.");
        test.assignCategory("Databricks - Data");
        DataTablesHelper dataTablesHelper = new DataTablesHelper(driver, test);
        dataTablesHelper.clickOnDataTab();
        dataTablesHelper.clickOnDataTablesTab();
        dataTablesHelper.selectWorkspaceForConfiguredMetastore();
        dataTablesHelper.verifyTableFilterByColumn("Created");
        loggingUtils.pass("Data is displayed based on applied search for Created", test);
    }
}
