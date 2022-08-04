package com.qa.testcases.cloud.databricks.data.overview;

import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.scripts.cloud.databricks.DataTablesHelper;
import com.qa.scripts.jobs.applications.AllApps;
import com.qa.utils.LoggingUtils;
import com.qa.utils.WaitExecuter;
import org.testng.annotations.Test;

/**
 * @author Ankur Jaiswal
 */
@Marker.DbxDataOverview
@Marker.EmrDataOverview
@Marker.GCPDataOverview
public class TC_DBX_DO_2 extends BaseClass {
    private final LoggingUtils loggingUtils = new LoggingUtils(this.getClass());

    @Test(dataProvider = "clusterid-data-provider",description = "Verify workspace on data page")
    public void verifyWorkspaceOnDataPage(String clusterId) {
        test = extent.startTest("TC_DBX_DO_2.verifyWorkspaceOnDataPage", "Verify workspace on data page");
        test.assignCategory("Databricks - Data");
        AllApps allApps = new AllApps(driver);
        WaitExecuter waitExecuter = new WaitExecuter(driver);
        DataTablesHelper dataTablesHelper = new DataTablesHelper(driver, test);
        dataTablesHelper.clickOnDataTab();
        allApps.selectWorkSpaceId(clusterId);
        waitExecuter.sleep(3000);
        /*dataTablesHelper.selectWorkspaceForConfiguredMetastore();*/
        loggingUtils.pass("Selected the workspace which has configured metastore", test);
    }
}
