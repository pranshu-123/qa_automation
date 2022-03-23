package com.qa.testcases.cloud.databricks.data.overview;

import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.scripts.cloud.databricks.DataOverviewHelper;
import com.qa.scripts.cloud.databricks.DataTablesHelper;
import com.qa.utils.LoggingUtils;
import org.testng.annotations.Test;

/**
 * @author Ankur Jaiswal
 */
@Marker.DBX.Data
public class TC_DBX_DO_11 extends BaseClass {
    private final LoggingUtils loggingUtils = new LoggingUtils(this.getClass());

    @Test(description = "Verify the graph chart is updated when filter is applied for table selection")
    public void verifyPieChartWhenAppStateSelected() {
        test = extent.startTest("TC_DBX_DO_11.verifyPieChartWhenAppStateSelected", "Verify the graph chart " +
            "is updated when filter is applied for table selection");
        test.assignCategory("Databricks - Data");
        DataTablesHelper dataTablesHelper = new DataTablesHelper(driver, test);
        DataOverviewHelper dataOverviewHelper = new DataOverviewHelper(driver, test);
        dataTablesHelper.clickOnDataTab();
        dataTablesHelper.selectWorkspaceForConfiguredMetastore();
        dataOverviewHelper.selectOnlyTableState("hot");
        loggingUtils.pass("Pie chart displayed as per selection", test);
    }
}
