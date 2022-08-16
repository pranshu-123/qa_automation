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

import java.util.Iterator;
import java.util.Map;
import java.util.Set;
@Marker.DataOverview
public class TC_DO_3 extends BaseClass {
    private final LoggingUtils loggingUtils = new LoggingUtils(this.getClass());

    @Test(dataProvider = "clusterid-data-provider",description = "Verify Last Day of Table KPIs")
    public void TC_DO_3_verifyLastDayOfTableKPIs(String clusterId) {
        test = extent.startTest("TC_DO_3.verifyLastDayOfTableKPIs", "Verify Last Day of Table KPIs");
        test.assignCategory("Data/Overview");
        AllApps allApps = new AllApps(driver);
        WaitExecuter waitExecuter = new WaitExecuter(driver);
        DataOverview dataOverview = new DataOverview(driver, test);
        dataOverview.clickOnDataTab();
        allApps.selectWorkSpaceId(clusterId);
        waitExecuter.sleep(3000);
        DataOverviewHelper dataOverviewHelper = new DataOverviewHelper(driver, test);
        Set<Map.Entry<String, String>> kpisKV = dataOverviewHelper.getLastDayTablesKPIs().entrySet();
        Iterator itr = kpisKV.iterator();
        int counter = 0;
        while (itr.hasNext()) {
            counter++;
            Map.Entry<String, String> kpiKV = (Map.Entry<String, String>) itr.next();
            Assert.assertNotEquals(kpiKV.getKey().trim(), "", "KPI title is missing.");
            Assert.assertNotEquals(kpiKV.getValue().trim(), "", "KPI value is missing.");
        }
        Assert.assertEquals(counter, 5, "");
        loggingUtils.pass("Table KPIs titles are present", test);
    }
}

