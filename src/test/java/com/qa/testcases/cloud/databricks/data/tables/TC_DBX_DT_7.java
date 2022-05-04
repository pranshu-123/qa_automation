package com.qa.testcases.cloud.databricks.data.tables;

import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.scripts.cloud.databricks.DataTablesHelper;
import com.qa.utils.LoggingUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

@Marker.DbxDataTables
public class TC_DBX_DT_7 extends BaseClass {
    private final LoggingUtils loggingUtils = new LoggingUtils(TC_DBX_DT_1.class);

    @Test(description = "Verify tables data is displayed in table")
    public void verifyTablesData() {
        test = extent.startTest("verifyTablesData", "Verify tables data is displayed in table");
        test.assignCategory("Databricks - Data");
        DataTablesHelper dataTablesHelper = new DataTablesHelper(driver, test);
        dataTablesHelper.clickOnDataTab();
        dataTablesHelper.clickOnDataTablesTab();
        dataTablesHelper.selectWorkspaceForConfiguredMetastore();
        Boolean isDataLoaded = dataTablesHelper.isTableLoadedWithData();
        Assert.assertTrue(isDataLoaded, "Data is not loaded.");
        loggingUtils.pass("Data is loaded in data tables", test);
    }
}
