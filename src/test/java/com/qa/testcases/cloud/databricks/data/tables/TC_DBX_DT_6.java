package com.qa.testcases.cloud.databricks.data.tables;

import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.scripts.cloud.databricks.DataTablesHelper;
import com.qa.scripts.jobs.applications.AllApps;
import com.qa.utils.LoggingUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * @author Ankur Jaiswal
 */

@Marker.DbxDataTables
@Marker.GCPDataTables
public class TC_DBX_DT_6 extends BaseClass {

    private final LoggingUtils loggingUtils = new LoggingUtils(TC_DBX_DT_6.class);

    @Test(description = "Verify the Size graph")
    public void TC_DBX_DT_6_verifySizeTab(String clusterId) {
        test = extent.startTest("verifySizeTab", "Verify the Size graph");
        test.assignCategory("Databricks - Data");
        DataTablesHelper dataTablesHelper = new DataTablesHelper(driver, test);
        dataTablesHelper.clickOnDataTab();
        loggingUtils.info("Click on Data Tab on top panel", test);
        dataTablesHelper.clickOnDataTablesTab();
        AllApps allApps = new AllApps(driver);
        allApps.selectWorkSpaceId(clusterId);
        /*dataTablesHelper.selectWorkspaceForConfiguredMetastore(clusterId);*/
        String isSizeGraphLoaded = dataTablesHelper.getTooltipValuesOfLoadedGraph("size");
        Assert.assertTrue(!isSizeGraphLoaded.equals(""), "Size graph is not loaded");
        loggingUtils.pass("Size graph loaded successfully.", test);
    }
}
