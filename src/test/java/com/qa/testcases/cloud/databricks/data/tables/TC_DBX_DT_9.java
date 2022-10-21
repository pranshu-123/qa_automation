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
public class TC_DBX_DT_9 extends BaseClass {
    private final LoggingUtils loggingUtils = new LoggingUtils(TC_DBX_DT_9.class);

    @Test(dataProvider = "clusterid-data-provider",description = "Validate the search textbox is able to filter for tables data.")
    public void verifySearchBoxIsAbleToFilter(String clusterId) throws InterruptedException {
        test = extent.startTest("TC_DBX_DT_9.verifySearchBoxIsAbleToFilter", "Validate the search textbox is able to filter for tables data.");
        test.assignCategory("Databricks - Data");
        DataTablesHelper dataTablesHelper = new DataTablesHelper(driver, test);
        dataTablesHelper.clickOnDataTab();
        dataTablesHelper.clickOnDataTablesTab();
        AllApps allApps = new AllApps(driver);
        allApps.selectWorkSpaceId(clusterId);
        /*dataTablesHelper.selectWorkspaceForConfiguredMetastore(clusterId);*/
        dataTablesHelper.verifyTableFilterByColumn("Table");
        loggingUtils.pass("User is able to filter data using search box", test);
    }
}
