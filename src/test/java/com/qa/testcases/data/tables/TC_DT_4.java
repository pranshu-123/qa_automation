package com.qa.testcases.data.tables;

import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.scripts.data.DataTables;
import com.qa.testcases.cloud.databricks.data.tables.TC_DBX_DT_4;
import com.qa.utils.LoggingUtils;
import org.testng.Assert;
import org.testng.annotations.Test;
@Marker.DataTables
public class TC_DT_4 extends BaseClass {

    private final LoggingUtils loggingUtils = new LoggingUtils(TC_DBX_DT_4.class);

    @Test(description = "Verify the Users graph")
    public void TC_DT_4_verifyUsersTab() {
        test = extent.startTest("TC_DT_4.verifyUsersTab", "Verify the Users graph");
        test.assignCategory("Data/Tables");
        DataTables dataTables = new DataTables(driver, test);
        dataTables.clickOnDataTab("Tables");
        loggingUtils.info("Click on Data Tab on top panel", test);
        dataTables.clickOnDataTablesTab();
        dataTables.selectWorkspaceForConfiguredMetastore();
        String isUserGraphLoaded = dataTables.getTooltipValuesOfLoadedGraph("users");
        Assert.assertTrue(!isUserGraphLoaded.equals(""), "Users graph is not loaded");
        loggingUtils.pass("Users graph loaded successfully.", test);
    }
}

