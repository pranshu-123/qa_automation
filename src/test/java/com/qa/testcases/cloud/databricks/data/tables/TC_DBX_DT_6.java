package com.qa.testcases.cloud.databricks.data.tables;

import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.scripts.cloud.dbx.DataTablesHelper;
import com.qa.utils.LoggingUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * @author Ankur Jaiswal
 */

@Marker.DBX.Data
public class TC_DBX_DT_6 extends BaseClass {

    private final LoggingUtils loggingUtils = new LoggingUtils(TC_DBX_DT_6.class);

    @Test(description = "Verify the Size graph")
    public void verifySizeTab() {
        test = extent.startTest("verifySizeTab", "Verify the Size graph");
        test.assignCategory("Databricks - Data");
        DataTablesHelper dataTablesHelper = new DataTablesHelper(driver, test);
        dataTablesHelper.clickOnDataTab();
        loggingUtils.info("Click on Data Tab on top panel", test);
        dataTablesHelper.clickOnDataTablesTab();
        dataTablesHelper.selectWorkspaceForConfiguredMetastore();
        String isSizeGraphLoaded = dataTablesHelper.getTooltipValuesOfLoadedGraph("size");
        Assert.assertTrue(!isSizeGraphLoaded.equals(""), "Size graph is not loaded");
        loggingUtils.pass("Size graph loaded successfully.", test);
    }
}
