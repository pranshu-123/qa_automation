package com.qa.testcases.cloud.databricks.data.tables;

import com.qa.base.BaseClass;
import com.qa.pagefactory.cloud.dbx.DataPageObject;
import com.qa.scripts.cloud.dbx.DataTablesHelper;
import com.qa.utils.LoggingUtils;
import com.qa.utils.WaitExecuter;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC_DBX_DT_29 extends BaseClass {

    private final LoggingUtils loggingUtils = new LoggingUtils(TC_DBX_DT_29.class);

    @Test(description = "Verify Total Tables label left of tabular data")
    public void verifyTotalTablesLabel() {
        test = extent.startTest("verifyTotalTablesLabel", "Verify Total Tables label left of tabular data");
        test.assignCategory("Databricks - Data");
        DataTablesHelper dataTablesHelper = new DataTablesHelper(driver, test);
        dataTablesHelper.clickOnDataTab();
        dataTablesHelper.clickOnDataTablesTab();
        dataTablesHelper.selectWorkspaceForConfiguredMetastore();
        DataPageObject dataPageObject = new DataPageObject(driver);
        WaitExecuter executor = new WaitExecuter(driver);
        executor.waitUntilElementPresent(dataPageObject.totalTables);
        String totalTableCount = dataPageObject.totalTables.getText();
        try {
            Assert.assertNotEquals(totalTableCount.split(":")[1], "", "Total tables value is not present.");
        } catch (IndexOutOfBoundsException indexOutOfBoundsException) {
            Assert.assertTrue(false, "Total tables value is not present.");
        }
        loggingUtils.pass("Total tables value present", test);
    }
}
