package com.qa.testcases.cloud.databricks.data.tables;

import com.qa.base.BaseClass;
import com.qa.scripts.cloud.databricks.DataTablesHelper;
import com.qa.utils.LoggingUtils;
import org.testng.annotations.Test;

/**
 * @author Ankur Jaiswal
 */


public class TC_DBX_DT_28 extends BaseClass {
    private final LoggingUtils loggingUtils = new LoggingUtils(TC_DBX_DT_28.class);

    @Test(description = "Validate whether user is able to filter data with \"Users\" column value.")
    public void verifyTableDataFilterByUsersColumn() {
        test = extent.startTest("verifyTableDataFilterByUsersColumn", "Validate whether user is able to filter data" +
            " with \"Users\" column value.");
        test.assignCategory("Databricks - Data");
        DataTablesHelper dataTablesHelper = new DataTablesHelper(driver, test);
        dataTablesHelper.clickOnDataTab();
        dataTablesHelper.clickOnDataTablesTab();
        dataTablesHelper.selectWorkspaceForConfiguredMetastore();
        dataTablesHelper.verifyTableFilterByColumn("Users");
        loggingUtils.pass("Data is displayed based on applied search for Users", test);
    }
}
