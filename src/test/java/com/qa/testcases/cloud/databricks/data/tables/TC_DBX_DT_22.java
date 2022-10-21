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
public class TC_DBX_DT_22 extends BaseClass {
    private final LoggingUtils loggingUtils = new LoggingUtils(TC_DBX_DT_22.class);

    @Test(dataProvider = "clusterid-data-provider",description = "Validate whether user is able to filter data with Storage Format column value.")
    public void verifyTableDataFilterByStorageFormatColumn(String clusterId) throws InterruptedException {
        test = extent.startTest("TC_DBX_DT_22.verifyTableDataFilterByStorageFormatColumn", "Validate whether user is able to filter data " +
            "with Storage Format column value.");
        test.assignCategory("Databricks - Data");
        DataTablesHelper dataTablesHelper = new DataTablesHelper(driver, test);
        dataTablesHelper.clickOnDataTab();
        dataTablesHelper.clickOnDataTablesTab();
        AllApps allApps = new AllApps(driver);
        allApps.selectWorkSpaceId(clusterId);
        /*dataTablesHelper.selectWorkspaceForConfiguredMetastore(clusterId);*/
        dataTablesHelper.verifyTableFilterByColumn("Storage Format");
        loggingUtils.pass("Data is displayed based on applied search for Storage Format", test);
    }
}
