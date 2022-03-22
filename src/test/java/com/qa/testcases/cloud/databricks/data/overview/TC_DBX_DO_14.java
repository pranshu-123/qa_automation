package com.qa.testcases.cloud.databricks.data.overview;

import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.scripts.cloud.databricks.DataOverviewHelper;
import com.qa.scripts.cloud.databricks.DataTablesHelper;
import com.qa.utils.LoggingUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * @author Ankur Jaiswal
 */

@Marker.DBX.Data
public class TC_DBX_DO_14 extends BaseClass {
    private final LoggingUtils loggingUtils = new LoggingUtils(this.getClass());

    @Test(description = "Verify Last Day of Partitions KPIs")
    public void verifyLastDayOfPartitionsKPIs() {
        test = extent.startTest("TC_DBX_DO_4.verifyLastDayOfPartitionsKPIs", "Verify Last Day of Partitions KPIs");
        test.assignCategory("Databricks - Data");
        DataTablesHelper dataTablesHelper = new DataTablesHelper(driver, test);
        dataTablesHelper.clickOnDataTab();
        DataOverviewHelper dataOverviewHelper = new DataOverviewHelper(driver, test);
        Set<Map.Entry<String, String>> kpisKV = dataOverviewHelper.getLastDayPartitionKPIs().entrySet();
        Iterator itr = kpisKV.iterator();
        while (itr.hasNext()) {
            Map.Entry<String, String> kpiKV = (Map.Entry<String, String>) itr.next();
            Assert.assertNotEquals(kpiKV.getKey(), "KPI title is not missing.");
            Assert.assertNotEquals(kpiKV.getValue(), "KPI value is not missing.");
        }
        loggingUtils.pass("Partitions KPIs titles are present", test);
        loggingUtils.pass("Partitions KPIs values are present", test);
    }
}
