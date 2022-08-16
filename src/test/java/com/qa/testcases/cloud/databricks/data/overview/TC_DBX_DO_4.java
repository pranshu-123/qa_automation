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

import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * @author Ankur Jaiswal
 */
@Marker.DbxDataOverview
@Marker.EmrDataOverview
@Marker.GCPDataOverview
public class TC_DBX_DO_4 extends BaseClass {
    private final LoggingUtils loggingUtils = new LoggingUtils(this.getClass());

    @Test(dataProvider = "clusterid-data-provider",description = "Verify Last Day of Table KPIs")
    public void TC_DBX_DO_4_verifyLastDayOfTableKPIs(String clusterId) {
        test = extent.startTest("TC_DBX_DO_4.verifyLastDayOfTableKPIs", "Verify Last Day of Table KPIs");
        test.assignCategory("Databricks - Data");
        AllApps allApps = new AllApps(driver);
        WaitExecuter waitExecuter = new WaitExecuter(driver);
        DataTablesHelper dataTablesHelper = new DataTablesHelper(driver, test);
        dataTablesHelper.clickOnDataTab();
        allApps.selectWorkSpaceId(clusterId);
        waitExecuter.sleep(3000);
        DataOverviewHelper dataOverviewHelper = new DataOverviewHelper(driver, test);
        Set<Map.Entry<String, String>> kpisKV = dataOverviewHelper.getLastDayTablesKPIs().entrySet();
        Iterator itr = kpisKV.iterator();
        int counter = 0;
        while (itr.hasNext()) {
            counter++;
            Map.Entry<String, String> kpiKV = (Map.Entry<String, String>) itr.next();
            Assert.assertNotEquals(kpiKV.getKey().trim(), "", "KPI title is not missing.");
            Assert.assertNotEquals(kpiKV.getValue().trim(), "", "KPI value is not missing.");
        }
        Assert.assertEquals(counter, 5, "");
        loggingUtils.pass("Table KPIs titles are present", test);
        loggingUtils.pass("Table KPIs values are present", test);
    }
}
