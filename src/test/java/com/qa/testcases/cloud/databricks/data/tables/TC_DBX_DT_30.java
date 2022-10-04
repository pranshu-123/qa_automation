package com.qa.testcases.cloud.databricks.data.tables;

import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.scripts.cloud.databricks.DataTablesHelper;
import com.qa.scripts.jobs.applications.AllApps;
import com.qa.utils.LoggingUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;

/**
 * @author Ankur Jaiswal
 */


@Marker.DbxDataTables
@Marker.GCPDataTables
public class TC_DBX_DT_30 extends BaseClass {
    private final LoggingUtils loggingUtils = new LoggingUtils(TC_DBX_DT_30.class);

    @Test(dataProvider = "clusterid-data-provider",description = "Verify the \"Download CSV\" button")
    public void TC_DBX_DT_30_verifyDownloadCSV(String clusterId) {
        test = extent.startTest("TC_DBX_DT_30.verifyDownloadCSV", "Verify the \"Download CSV\" button");
        test.assignCategory("Databricks - Data");
        DataTablesHelper dataTablesHelper = new DataTablesHelper(driver, test);
        dataTablesHelper.clickOnDataTab();
        dataTablesHelper.clickOnDataTablesTab();
        AllApps allApps = new AllApps(driver);
        allApps.selectWorkSpaceId(clusterId);
        /*dataTablesHelper.selectWorkspaceForConfiguredMetastore(clusterId);*/
        File downloadedFile = dataTablesHelper.downloadDataTable();
        Assert.assertTrue(downloadedFile.exists(), "CSV file not downloaded");
        loggingUtils.pass("CSV file downloaded successfully", test);
    }
}
