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

@Marker.DBX.Data
public class TC_DBX_DT_5 extends BaseClass {

    private final LoggingUtils loggingUtils = new LoggingUtils(TC_DBX_DT_5.class);

    @Test(description = "Verify the Apps graph")
    public void verifyAppsTab() {
        test = extent.startTest("verifyAppsTab", "Verify the Apps graph");
        test.assignCategory("Databricks - Data");
        DataTablesHelper dataTablesHelper = new DataTablesHelper(driver, test);
        dataTablesHelper.clickOnDataTab();
        loggingUtils.info("Click on Data Tab on top panel", test);
        dataTablesHelper.clickOnDataTablesTab();
        dataTablesHelper.selectWorkspaceForConfiguredMetastore();
        String isAppsGraphLoaded = dataTablesHelper.getTooltipValuesOfLoadedGraph("apps");
        Assert.assertTrue(!isAppsGraphLoaded.equals(""), "Apps graph is not loaded");
        loggingUtils.pass("Apps graph loaded successfully.", test);
    }
}
