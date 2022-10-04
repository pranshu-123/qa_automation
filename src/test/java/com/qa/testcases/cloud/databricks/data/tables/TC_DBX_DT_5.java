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
public class TC_DBX_DT_5 extends BaseClass {

    private final LoggingUtils loggingUtils = new LoggingUtils(TC_DBX_DT_5.class);

    @Test(dataProvider = "clusterid-data-provider",description = "Verify the Apps graph")
    public void TC_DBX_DT_5_verifyAppsTab(String clusterId) {
        test = extent.startTest("verifyAppsTab", "Verify the Apps graph");
        test.assignCategory("Databricks - Data");
        DataTablesHelper dataTablesHelper = new DataTablesHelper(driver, test);
        dataTablesHelper.clickOnDataTab();
        loggingUtils.info("Click on Data Tab on top panel", test);
        dataTablesHelper.clickOnDataTablesTab();
        AllApps allApps = new AllApps(driver);
        allApps.selectWorkSpaceId(clusterId);
        /*dataTablesHelper.selectWorkspaceForConfiguredMetastore(clusterId);*/
        String isAppsGraphLoaded = dataTablesHelper.getTooltipValuesOfLoadedGraph("apps");
        Assert.assertTrue(!isAppsGraphLoaded.equals(""), "Apps graph is not loaded");
        loggingUtils.pass("Apps graph loaded successfully.", test);
    }
}
