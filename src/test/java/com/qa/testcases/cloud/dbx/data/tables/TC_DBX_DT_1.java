package com.qa.testcases.cloud.dbx.data.tables;

import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.pagefactory.cloud.dbx.DataPageObject;
import com.qa.scripts.cloud.dbx.DataTablesHelper;
import com.qa.utils.LoggingUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * @author Ankur Jaiswal
 */

@Marker.DBX.Data
public class TC_DBX_DT_1 extends BaseClass {

    private final LoggingUtils loggingUtils = new LoggingUtils(TC_DBX_DT_1.class);

    @Test(description = "Verify whether table tab is present")
    public void verifyTablesTab() {
        test = extent.startTest("TC_DBX_DT_1.verifyTablesTab", "Verify whether table tab is present");
        test.assignCategory("Databricks - Data");
        DataPageObject dataPageObject = new DataPageObject(driver);
        DataTablesHelper dataTablesHelper = new DataTablesHelper(driver, test);
        dataTablesHelper.clickOnDataTab();
        loggingUtils.info("Click on Data Tab on top panel", test);
        Assert.assertTrue(dataTablesHelper.checkIfElementPresent(dataPageObject.dataTablesTab));
        loggingUtils.pass("Data Tables tab is present", test);
    }
}
