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
public class TC_DBX_DO_4 extends BaseClass {
    private final LoggingUtils loggingUtils = new LoggingUtils(this.getClass());

    @Test(description = "Verify Last Day of Table KPIs")
    public void verifyLastDayOfTableKPIs() {
        test = extent.startTest("TC_DBX_DO_4.verifyLastDayOfTableKPIs", "Verify Last Day of Table KPIs");
        test.assignCategory("Databricks - Data");
        DataTablesHelper dataTablesHelper = new DataTablesHelper(driver, test);
        dataTablesHelper.clickOnDataTab();
        DataOverviewHelper dataOverviewHelper = new DataOverviewHelper(driver, test);
        Set<Map.Entry<String, String>> kpisKV = dataOverviewHelper.getLastDayTablesKPIs().entrySet();
        Iterator itr = kpisKV.iterator();
        while (itr.hasNext()) {
            Map.Entry<String, String> kpiKV = (Map.Entry<String, String>) itr.next();
            Assert.assertNotEquals(kpiKV.getKey(), "KPI title is not missing.");
            Assert.assertNotEquals(kpiKV.getValue(), "KPI value is not missing.");
        }
        loggingUtils.pass("Table KPIs titles are present", test);
        loggingUtils.pass("Table KPIs values are present", test);
    }
}
