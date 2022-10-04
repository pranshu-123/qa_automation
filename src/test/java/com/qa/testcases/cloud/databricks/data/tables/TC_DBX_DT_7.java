package com.qa.testcases.cloud.databricks.data.tables;

import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.scripts.cloud.databricks.DataTablesHelper;
import com.qa.scripts.jobs.applications.AllApps;
import com.qa.utils.LoggingUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

@Marker.DbxDataTables
@Marker.GCPDataTables
public class TC_DBX_DT_7 extends BaseClass {
    private final LoggingUtils loggingUtils = new LoggingUtils(TC_DBX_DT_7.class);

    @Test(description = "Verify tables data is displayed in table")
    public void TC_DBX_DT_7_verifyTablesData(String clusterId) {
        test = extent.startTest("verifyTablesData", "Verify tables data is displayed in table");
        test.assignCategory("Databricks - Data");
        DataTablesHelper dataTablesHelper = new DataTablesHelper(driver, test);
        dataTablesHelper.clickOnDataTab();
        dataTablesHelper.clickOnDataTablesTab();
        AllApps allApps = new AllApps(driver);
        allApps.selectWorkSpaceId(clusterId);
        /*dataTablesHelper.selectWorkspaceForConfiguredMetastore(clusterId);*/
        Boolean isDataLoaded = dataTablesHelper.isTableLoadedWithData();
        Assert.assertTrue(isDataLoaded, "Data is not loaded.");
        loggingUtils.pass("Data is loaded in data tables", test);
    }
}
