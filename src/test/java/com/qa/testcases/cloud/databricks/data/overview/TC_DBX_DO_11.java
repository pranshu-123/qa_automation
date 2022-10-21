package com.qa.testcases.cloud.databricks.data.overview;

import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.scripts.cloud.databricks.DataOverviewHelper;
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
public class TC_DBX_DO_11 extends BaseClass {
    private final LoggingUtils loggingUtils = new LoggingUtils(this.getClass());

    @Test(dataProvider = "clusterid-data-provider",description = "Verify the graph chart is updated when filter is applied for table selection")
    public void TC_DBX_DO_11_verifyPieChartWhenAppStateSelected(String clusterId) throws InterruptedException {
        test = extent.startTest("TC_DBX_DO_11.verifyPieChartWhenAppStateSelected", "Verify the graph chart " +
            "is updated when filter is applied for table selection");
        test.assignCategory("Databricks - Data");
        DataTablesHelper dataTablesHelper = new DataTablesHelper(driver, test);
        DataOverviewHelper dataOverviewHelper = new DataOverviewHelper(driver, test);
        AllApps allApps = new AllApps(driver);
        WaitExecuter waitExecuter = new WaitExecuter(driver);
        dataTablesHelper.clickOnDataTab();
        allApps.selectWorkSpaceId(clusterId);
        waitExecuter.waitForSeconds(2);
        dataOverviewHelper.selectOnlyTableState("hot");
        loggingUtils.pass("Pie chart displayed as per selection", test);
    }
}
