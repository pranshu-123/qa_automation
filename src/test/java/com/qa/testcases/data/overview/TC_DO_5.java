package com.qa.testcases.data.overview;

import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.scripts.cloud.databricks.DataOverviewHelper;
import com.qa.scripts.cloud.databricks.DataTablesHelper;
import com.qa.scripts.data.DataOverview;
import com.qa.scripts.jobs.applications.AllApps;
import com.qa.utils.LoggingUtils;
import com.qa.utils.WaitExecuter;
import org.testng.Assert;
import org.testng.annotations.Test;
@Marker.DataOverview
public class TC_DO_5 extends BaseClass {
    private final LoggingUtils loggingUtils = new LoggingUtils(this.getClass());

    @Test(dataProvider = "clusterid-data-provider",description = "Verify \"Total Number of Tables\" chart under Trends of Tables KPIs")
    public void TC_DO_5_verifyTotalNumberOfTablesGraph(String clusterId) {
        test = extent.startTest("TC_DO_5.verifyTotalNumberOfTablesGraph", "Verify \"Total Number of Tables\" chart under Trends of Tables KPIs");
        test.assignCategory("Data/Overview");
        DataOverview dataOverview = new DataOverview(driver, test);
        AllApps allApps = new AllApps(driver);
        WaitExecuter waitExecuter = new WaitExecuter(driver);
        dataOverview.clickOnDataTab();
        allApps.selectWorkSpaceId(clusterId);
        waitExecuter.sleep(3000);
        DataOverviewHelper dataOverviewHelper = new DataOverviewHelper(driver, test);
        String tooltipValue = dataOverviewHelper.getTooltipValuesOfLoadedGraph("Total Number of Tables");
        Assert.assertNotEquals(tooltipValue.trim(), "", "Tooltip value is blank, Graph seems to be empty");
        loggingUtils.pass("Graph loaded for \"Total Number of Tables\"", test);
    }
}
