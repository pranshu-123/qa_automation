package com.qa.testcases.cloud.databricks.data.tables;

import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.pagefactory.cloud.databricks.DataPageObject;
import com.qa.scripts.cloud.databricks.DataTablesHelper;
import com.qa.utils.LoggingUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * @author Ankur Jaiswal
 */
@Marker.DBX.Data
public class TC_DBX_DT_38 extends BaseClass {
    private final LoggingUtils loggingUtils = new LoggingUtils(this.getClass());

    @Test(description = "Verify the Applications section of Table Details page.")
    public void verifyApplicationsSection() {
        test = extent.startTest("TC_DBX_DT_38.verifyApplicationsSection", "Verify the Applications section of Table Details page.");
        test.assignCategory("Databricks - Data");
        DataTablesHelper dataTablesHelper = new DataTablesHelper(driver, test);
        DataPageObject dataPageObject = new DataPageObject(driver);
        dataTablesHelper.clickOnDataTab();
        dataTablesHelper.clickOnDataTablesTab();
        dataTablesHelper.selectWorkspaceForConfiguredMetastore();
        dataTablesHelper.clickOnMoreInfoOfNthRow(0);
        try {
            dataTablesHelper.clickOnTabOnTableDetails("Applications");
            Assert.assertTrue(dataPageObject.dataTable.size() > 0, "Applications tab is not loaded with data");
            loggingUtils.pass("Applications tab is not loaded with data", test);
        } finally {
            dataTablesHelper.backToTablesPage();
        }
    }
}
