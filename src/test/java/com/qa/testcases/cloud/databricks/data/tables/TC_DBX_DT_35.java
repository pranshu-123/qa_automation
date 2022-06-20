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


@Marker.DbxDataTables
public class TC_DBX_DT_35 extends BaseClass {
    private final LoggingUtils loggingUtils = new LoggingUtils(TC_DBX_DT_35.class);

    @Test(description = "Validate the more info of the table record.")
    public void validateMoreInfo() {
        test = extent.startTest("validateMoreInfo", "Validate the more info of the table record.");
        test.assignCategory("Databricks - Data");
        DataTablesHelper dataTablesHelper = new DataTablesHelper(driver, test);
        DataPageObject dataPageObject = new DataPageObject(driver);
        try {
            dataTablesHelper.clickOnDataTab();
            dataTablesHelper.clickOnDataTablesTab();
            dataTablesHelper.selectWorkspaceForConfiguredMetastore();
            dataTablesHelper.clickOnMoreInfoOfNthRow(0);
            Assert.assertEquals(dataPageObject.tableDetailsPageHeading.getText(), "Tables Table Details",
                "Table Details Page is not displayed.");
            loggingUtils.pass("Table Details page displayed.", test);
        } finally {
            dataTablesHelper.backToTablesPage();
        }
    }
}
