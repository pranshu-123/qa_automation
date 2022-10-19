package com.qa.testcases.cloud.databricks.data.tables;

import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.pagefactory.cloud.databricks.DataPageObject;
import com.qa.scripts.cloud.databricks.DataTablesHelper;
import com.qa.scripts.jobs.applications.AllApps;
import com.qa.utils.LoggingUtils;
import com.qa.utils.WaitExecuter;
import com.qa.utils.actions.UserActions;
import org.testng.annotations.Test;

/**
 * @author Ankur Jaiswal
 */

@Marker.DbxDataTables
@Marker.GCPDataTables
public class TC_DBX_DT_26 extends BaseClass {
    private final LoggingUtils loggingUtils = new LoggingUtils(TC_DBX_DT_26.class);

    @Test(dataProvider = "clusterid-data-provider",description = "Validate whether user is able to filter data with Apps column value.")
    public void verifyTableDataFilterByAppsColumn(String clusterId) throws InterruptedException {
        test = extent.startTest("TC_DBX_DT_26.verifyTableDataFilterByAppsColumn", "Validate whether user is able to filter data " +
            "with Apps column value.");
        test.assignCategory("Databricks - Data");
        DataTablesHelper dataTablesHelper = new DataTablesHelper(driver, test);
        DataPageObject dataPageObject = new DataPageObject(driver);
        WaitExecuter waitExecuter = new WaitExecuter(driver);
        dataTablesHelper.clickOnDataTab();
        dataTablesHelper.clickOnDataTablesTab();
        AllApps allApps = new AllApps(driver);
        allApps.selectWorkSpaceId(clusterId);
        waitExecuter.waitForSeconds(3);
        dataPageObject.sortByDurationApp.click();
        waitExecuter.waitUntilElementPresent(dataPageObject.sortDown);
        dataPageObject.sortDown.click();
        /*dataTablesHelper.selectWorkspaceForConfiguredMetastore(clusterId);*/
        dataTablesHelper.verifyTableFilterByColumn("Apps");
        loggingUtils.pass("Data is displayed based on applied search for Apps", test);
    }
}
