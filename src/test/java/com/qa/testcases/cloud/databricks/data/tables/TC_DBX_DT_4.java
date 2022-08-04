package com.qa.testcases.cloud.databricks.data.tables;

import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.scripts.cloud.databricks.DataTablesHelper;
import com.qa.utils.LoggingUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * @author Ankur Jaiswal
 */

@Marker.DbxDataTables
@Marker.GCPDataTables
public class TC_DBX_DT_4 extends BaseClass {

    private final LoggingUtils loggingUtils = new LoggingUtils(TC_DBX_DT_4.class);

    @Test(description = "Verify the Users graph")
    public void verifyUsersTab() {
        test = extent.startTest("verifyUsersTab", "Verify the Users graph");
        test.assignCategory("Databricks - Data");
        DataTablesHelper dataTablesHelper = new DataTablesHelper(driver, test);
        dataTablesHelper.clickOnDataTab();
        loggingUtils.info("Click on Data Tab on top panel", test);
        dataTablesHelper.clickOnDataTablesTab();
        dataTablesHelper.selectWorkspaceForConfiguredMetastore();
        String isUserGraphLoaded = dataTablesHelper.getTooltipValuesOfLoadedGraph("users");
        Assert.assertTrue(!isUserGraphLoaded.equals(""), "Users graph is not loaded");
        loggingUtils.pass("Users graph loaded successfully.", test);
    }
}
