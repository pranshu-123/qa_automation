package com.qa.testcases.cloud.databricks.data.tables;

import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.pagefactory.cloud.databricks.DataPageObject;
import com.qa.scripts.cloud.databricks.DataTablesHelper;
import com.qa.scripts.jobs.applications.AllApps;
import com.qa.utils.LoggingUtils;
import com.qa.utils.WaitExecuter;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * @author Ankur Jaiswal
 */

@Marker.DbxDataTables
@Marker.GCPDataTables
public class TC_DBX_DT_8 extends BaseClass {
    private final LoggingUtils loggingUtils = new LoggingUtils(TC_DBX_DT_8.class);

    @Test(dataProvider = "clusterid-data-provider",description = "Verify whether search textbox is present to filter data from data table")
    public void TC_DBX_DT_8_verifySearchBox(String clusterId) {
        test = extent.startTest("verifySearchBox", "Verify whether search textbox is present to filter data from data table");
        test.assignCategory("Databricks - Data");
        DataTablesHelper dataTablesHelper = new DataTablesHelper(driver, test);
        WaitExecuter waitExecuter = new WaitExecuter(driver);
        dataTablesHelper.clickOnDataTab();
        dataTablesHelper.clickOnDataTablesTab();
        AllApps allApps = new AllApps(driver);
        allApps.selectWorkSpaceId(clusterId);
        /*dataTablesHelper.selectWorkspaceForConfiguredMetastore(clusterId);*/
        DataPageObject dataPageObject = new DataPageObject(driver);
        try {
            waitExecuter.waitUntilElementPresent(dataPageObject.searchBoxForTableData);
            waitExecuter.waitUntilElementPresent(dataPageObject.searchBoxButton);
        } catch (Exception e) {
            Assert.assertTrue(false, "Either search box or button is missing");
        }
        loggingUtils.pass("Search box is present for filter table data", test);
    }
}
