package com.qa.testcases.cloud.databricks.data.tables;

import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.scripts.cloud.dbx.DataTablesHelper;
import com.qa.utils.LoggingUtils;
import org.testng.annotations.Test;

/**
 * @author Ankur Jaiswal
 */

@Marker.DBX.Data
public class TC_DBX_DT_9 extends BaseClass {
    private final LoggingUtils loggingUtils = new LoggingUtils(TC_DBX_DT_9.class);

    @Test(description = "Validate the search textbox is able to filter for tables data.")
    public void verifySearchBoxIsAbleToFilter() {
        test = extent.startTest("verifySearchBoxIsAbleToFilter", "Validate the search textbox is able to filter for tables data.");
        test.assignCategory("Databricks - Data");
        DataTablesHelper dataTablesHelper = new DataTablesHelper(driver, test);
        dataTablesHelper.clickOnDataTab();
        dataTablesHelper.clickOnDataTablesTab();
        dataTablesHelper.selectWorkspaceForConfiguredMetastore();
        dataTablesHelper.verifyTableFilterByColumn("Table");
        loggingUtils.pass("User is able to filter data using search box", test);
    }
}
