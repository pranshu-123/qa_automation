package com.qa.testcases.data.tables;

import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.scripts.data.DataTables;
import com.qa.utils.LoggingUtils;
import org.testng.Assert;
import org.testng.annotations.Test;
@Marker.DataTables
public class TC_DT_5 extends BaseClass {

    private final LoggingUtils loggingUtils = new LoggingUtils(TC_DT_5.class);

    @Test(description = "Verify the Apps graph")
    public void TC_DT_5_verifyAppsTab() {
        test = extent.startTest("TC_DT_5.verifyAppsTab", "Verify the Apps graph");
        test.assignCategory("Data/Tables");
        DataTables dataTables = new DataTables(driver, test);
        dataTables.clickOnDataTab("Tables");
        loggingUtils.info("Click on Data Tab on top panel", test);
        dataTables.clickOnDataTablesTab();
        dataTables.selectWorkspaceForConfiguredMetastore();
        String isAppsGraphLoaded = dataTables.getTooltipValuesOfLoadedGraph("apps");
        Assert.assertTrue(!isAppsGraphLoaded.equals(""), "Apps graph is not loaded");
        loggingUtils.pass("Apps graph loaded successfully.", test);
    }
}
