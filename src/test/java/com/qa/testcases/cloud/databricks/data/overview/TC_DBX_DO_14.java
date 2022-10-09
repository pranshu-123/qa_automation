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
public class TC_DBX_DO_14 extends BaseClass {
    private final LoggingUtils loggingUtils = new LoggingUtils(this.getClass());

    @Test(dataProvider = "clusterid-data-provider",description = "Verify Last Day of Partitions KPIs")
    public void TC_DBX_DO_14_verifyLastDayOfPartitionsKPIs(String clusterId) throws InterruptedException {
        test = extent.startTest("TC_DBX_DO_14.verifyLastDayOfPartitionsKPIs", "Verify Last Day of Partitions KPIs");
        test.assignCategory("Databricks - Data");
        DataTablesHelper dataTablesHelper = new DataTablesHelper(driver, test);
        AllApps allApps = new AllApps(driver);
        WaitExecuter waitExecuter = new WaitExecuter(driver);
        dataTablesHelper.clickOnDataTab();
        allApps.selectWorkSpaceId(clusterId);
        waitExecuter.waitForSeconds(3);
        DataOverviewHelper dataOverviewHelper = new DataOverviewHelper(driver, test);
        Set<Map.Entry<String, String>> kpisKV = dataOverviewHelper.getLastDayPartitionKPIs().entrySet();
        Iterator itr = kpisKV.iterator();
        int counter = 0;
        while (itr.hasNext()) {
            counter++;
            Map.Entry<String, String> kpiKV = (Map.Entry<String, String>) itr.next();
            Assert.assertNotEquals(kpiKV.getKey().trim(), "", "KPI title is not missing.");
            Assert.assertNotEquals(kpiKV.getValue().trim(), "", "KPI value is not missing.");
        }
        Assert.assertEquals(counter, 2, "");
        loggingUtils.pass("Partitions KPIs titles are present", test);
        loggingUtils.pass("Partitions KPIs values are present", test);
    }
}
