package com.qa.testcases.cloud.databricks.data.tables;

import com.qa.base.BaseClass;
import com.qa.scripts.cloud.databricks.DataTablesHelper;
import com.qa.utils.LoggingUtils;
import org.testng.annotations.Test;

/**
 * @author Ankur Jaiswal
 */


public class TC_DBX_DT_25 extends BaseClass {
    private final LoggingUtils loggingUtils = new LoggingUtils(TC_DBX_DT_25.class);

    @Test(description = "Validate whether user is able to filter data with Size column value.")
    public void verifyTableDataFilterBySizeColumn() {
        test = extent.startTest("verifyTableDataFilterBySizeColumn", "Validate whether user is able to filter data " +
            "with Size column value.");
        test.assignCategory("Databricks - Data");
        DataTablesHelper dataTablesHelper = new DataTablesHelper(driver, test);
        dataTablesHelper.clickOnDataTab();
        dataTablesHelper.clickOnDataTablesTab();
        dataTablesHelper.selectWorkspaceForConfiguredMetastore();
        dataTablesHelper.verifyTableFilterByColumn("Size");
        loggingUtils.pass("Data is displayed based on applied search for Size", test);
    }
}
