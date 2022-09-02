package com.qa.testcases.cloud.databricks.data.tables;

import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.pagefactory.cloud.databricks.DataPageObject;
import com.qa.scripts.cloud.databricks.DataTablesHelper;
import com.qa.utils.LoggingUtils;
import com.qa.utils.WaitExecuter;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * @author Ankur Jaiswal
 */

@Marker.DbxDataTables
@Marker.GCPDataTables
public class TC_DBX_DT_10 extends BaseClass {
    private final LoggingUtils loggingUtils = new LoggingUtils(TC_DBX_DT_10.class);

    @Test(description = "Verify the table state filter present.")
    public void verifyTableStateFilter() {
        test = extent.startTest("TC_DBX_DT_10.verifyTableStateFilter", "Verify the table state filter present.");
        test.assignCategory("Databricks - Data");
        DataTablesHelper dataTablesHelper = new DataTablesHelper(driver, test);
        dataTablesHelper.clickOnDataTab();
        dataTablesHelper.clickOnDataTablesTab();
        dataTablesHelper.selectWorkspaceForConfiguredMetastore();
        DataPageObject dataPageObject = new DataPageObject(driver);
        WaitExecuter executor = new WaitExecuter(driver);
        executor.waitUntilElementPresent(dataPageObject.tableStateFilterLabel);
        Assert.assertTrue(dataPageObject.tableStateOptionsLabel.get(0).getText().contains("Hot"),
            "Hot option is not available");
        loggingUtils.pass("Hot option is present in table state", test);
        Assert.assertTrue(dataPageObject.tableStateOptionsLabel.get(1).getText().contains("Warm"),
            "Warm option is not available");
        loggingUtils.pass("Warm option is present in table state", test);
        Assert.assertTrue(dataPageObject.tableStateOptionsLabel.get(2).getText().contains("Cold"),
            "Cold option is not available");
        loggingUtils.pass("Cold option is present in table state", test);
        Assert.assertTrue(dataPageObject.tableStateOptionsLabel.get(3).getText().contains("Unknown"),
            "Unknown option is not available");
        loggingUtils.pass("Unknown option is present in table state", test);
    }
}
