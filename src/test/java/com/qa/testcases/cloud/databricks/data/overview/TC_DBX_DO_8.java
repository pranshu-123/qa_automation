package com.qa.testcases.cloud.databricks.data.overview;

import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.scripts.cloud.databricks.DataOverviewHelper;
import com.qa.scripts.cloud.databricks.DataTablesHelper;
import com.qa.scripts.jobs.applications.AllApps;
import com.qa.utils.LoggingUtils;
import com.qa.utils.WaitExecuter;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * @author Ankur Jaiswal
 */
@Marker.DbxDataOverview
@Marker.EmrDataOverview
@Marker.GCPDataOverview
public class TC_DBX_DO_8 extends BaseClass {
    private final LoggingUtils loggingUtils = new LoggingUtils(this.getClass());

    @Test(dataProvider = "clusterid-data-provider",description = "Verify \"Number of Queries\" chart under Trends of Tables KPIs")
    public void verifyNumberOfQueriesGraph(String clusterId) {
        test = extent.startTest("TC_DBX_DO_8.verifyNumberOfQueriesGraph", "Verify \"Number of Queries\" chart under Trends of Tables KPIs");
        test.assignCategory("Databricks - Data");
        DataTablesHelper dataTablesHelper = new DataTablesHelper(driver, test);
        AllApps allApps = new AllApps(driver);
        WaitExecuter waitExecuter = new WaitExecuter(driver);
        dataTablesHelper.clickOnDataTab();
        allApps.selectWorkSpaceId(clusterId);
        waitExecuter.sleep(3000);
        DataOverviewHelper dataOverviewHelper = new DataOverviewHelper(driver, test);
        String tooltipValue = dataOverviewHelper.getTooltipValuesOfLoadedGraph("Number of Queries");
        Assert.assertNotEquals(tooltipValue.trim(), "", "Tooltip value is blank, Graph seems to be empty");
        loggingUtils.pass("Graph loaded for \"Number of Queries\"", test);
    }
}
