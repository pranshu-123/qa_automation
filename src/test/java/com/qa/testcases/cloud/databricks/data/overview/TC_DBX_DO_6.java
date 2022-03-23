package com.qa.testcases.cloud.databricks.data.overview;

import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.scripts.cloud.databricks.DataOverviewHelper;
import com.qa.scripts.cloud.databricks.DataTablesHelper;
import com.qa.utils.LoggingUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * @author Ankur Jaiswal
 */
@Marker.DBX.Data
public class TC_DBX_DO_6 extends BaseClass {
    private final LoggingUtils loggingUtils = new LoggingUtils(this.getClass());

    @Test(description = "Verify \"Total Number of Tables\" chart under Trends of Tables KPIs")
    public void verifyTotalNumberOfTablesGraph() {
        test = extent.startTest("TC_DBX_DO_6.verifyTotalNumberOfTablesGraph", "Verify \"Total Number of Tables\" chart under Trends of Tables KPIs");
        test.assignCategory("Databricks - Data");
        DataTablesHelper dataTablesHelper = new DataTablesHelper(driver, test);
        dataTablesHelper.clickOnDataTab();
        dataTablesHelper.selectWorkspaceForConfiguredMetastore();
        DataOverviewHelper dataOverviewHelper = new DataOverviewHelper(driver, test);
        String tooltipValue = dataOverviewHelper.getTooltipValuesOfLoadedGraph("Total Number of Tables");
        Assert.assertNotEquals(tooltipValue.trim(), "", "Tooltip value is blank, Graph seems to be empty");
        loggingUtils.pass("Graph loaded for \"Total Number of Tables\"", test);
    }
}
