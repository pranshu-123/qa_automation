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
public class TC_DBX_DT_27 extends BaseClass {
    private final LoggingUtils loggingUtils = new LoggingUtils(TC_DBX_DT_27.class);

    @Test(dataProvider = "clusterid-data-provider",description = "Validate whether user is able to filter data with \"Partitions\" column value.")
    public void verifyTableDataFilterByPartitionsColumn(String clusterId) throws InterruptedException {
        test = extent.startTest("TC_DBX_DT_27.verifyTableDataFilterByPartitionsColumn", "Validate whether user is able to filter data" +
            " with \"Partitions\" column value.");
        test.assignCategory("Databricks - Data");
        DataTablesHelper dataTablesHelper = new DataTablesHelper(driver, test);
        dataTablesHelper.clickOnDataTab();
        dataTablesHelper.clickOnDataTablesTab();
        AllApps allApps = new AllApps(driver);
        allApps.selectWorkSpaceId(clusterId);
        /*dataTablesHelper.selectWorkspaceForConfiguredMetastore(clusterId);*/
        dataTablesHelper.verifyTableFilterByColumn("Partitions");
        loggingUtils.pass("Data is displayed based on applied search for Partitions", test);
    }
}
