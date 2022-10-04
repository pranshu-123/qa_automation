package com.qa.testcases.cloud.databricks.data.tables;

import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.scripts.cloud.databricks.DataTablesHelper;
import com.qa.scripts.jobs.applications.AllApps;
import com.qa.utils.LoggingUtils;
import org.testng.annotations.Test;

/**
 * @author Ankur Jaiswal
 */

@Marker.DbxDataTables
@Marker.GCPDataTables
public class TC_DBX_DT_24 extends BaseClass {
    private final LoggingUtils loggingUtils = new LoggingUtils(TC_DBX_DT_24.class);

    @Test(dataProvider = "clusterid-data-provider",description = "Validate whether user is able to filter data with \"Latest Access\" column value.")
    public void verifyTableDataFilterByLatestAccessColumn(String clusterId) throws InterruptedException {
        test = extent.startTest("TC_DBX_DT_24.verifyTableDataFilterByLatestAccessColumn", "Validate whether user is able to filter data " +
            "with \"Latest Access\" column value.");
        test.assignCategory("Databricks - Data");
        DataTablesHelper dataTablesHelper = new DataTablesHelper(driver, test);
        dataTablesHelper.clickOnDataTab();
        dataTablesHelper.clickOnDataTablesTab();
        AllApps allApps = new AllApps(driver);
        allApps.selectWorkSpaceId(clusterId);
        /*dataTablesHelper.selectWorkspaceForConfiguredMetastore(clusterId);*/
        dataTablesHelper.verifyTableFilterByColumn("Latest Access");
        loggingUtils.pass("Data is displayed based on applied search for Latest Access", test);
    }
}

