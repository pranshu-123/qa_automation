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
public class TC_DBX_DT_8 extends BaseClass {
    private final LoggingUtils loggingUtils = new LoggingUtils(TC_DBX_DT_1.class);

    @Test(description = "Verify whether search textbox is present to filter data from data table")
    public void verifySearchBox() {
        test = extent.startTest("verifySearchBox", "Verify whether search textbox is present to filter data from data table");
        test.assignCategory("Databricks - Data");
        DataTablesHelper dataTablesHelper = new DataTablesHelper(driver, test);
        WaitExecuter waitExecuter = new WaitExecuter(driver);
        dataTablesHelper.clickOnDataTab();
        dataTablesHelper.clickOnDataTablesTab();
        dataTablesHelper.selectWorkspaceForConfiguredMetastore();
        DataPageObject dataPageObject = new DataPageObject(driver);
        try {
            waitExecuter.waitUntilElementPresent(dataPageObject.searchBoxForTableData);
            waitExecuter.waitUntilElementPresent(dataPageObject.searchBoxButton);
        } catch (Exception e) {
            Assert.assertTrue(false, "Either search box or button is missing");
        }
        loggingUtils.pass("Search box is present for filter table data", test);
    }
}
