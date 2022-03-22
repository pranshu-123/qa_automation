package com.qa.testcases.cloud.dbx.data.tables;

import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.scripts.cloud.dbx.DataTablesHelper;
import com.qa.utils.LoggingUtils;
import org.testng.annotations.Test;

/**
 * @author Ankur Jaiswal
 */


public class TC_DBX_DT_27 extends BaseClass {
    private final LoggingUtils loggingUtils = new LoggingUtils(TC_DBX_DT_27.class);

    @Test(description = "Validate whether user is able to filter data with \"Partitions\" column value.")
    public void verifyTableDataFilterByPartitionsColumn() {
        test = extent.startTest("verifyTableDataFilterByPartitionsColumn", "Validate whether user is able to filter data" +
            " with \"Partitions\" column value.");
        test.assignCategory("Databricks - Data");
        DataTablesHelper dataTablesHelper = new DataTablesHelper(driver, test);
        dataTablesHelper.clickOnDataTab();
        dataTablesHelper.clickOnDataTablesTab();
        dataTablesHelper.selectWorkspaceForConfiguredMetastore();
        dataTablesHelper.verifyTableFilterByColumn("Partitions");
        loggingUtils.pass("Data is displayed based on applied search for Partitions", test);
    }
}
