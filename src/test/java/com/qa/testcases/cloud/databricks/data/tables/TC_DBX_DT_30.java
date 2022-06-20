package com.qa.testcases.cloud.databricks.data.tables;

import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.scripts.cloud.databricks.DataTablesHelper;
import com.qa.utils.LoggingUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;

/**
 * @author Ankur Jaiswal
 */


@Marker.DbxDataTables
public class TC_DBX_DT_30 extends BaseClass {
    private final LoggingUtils loggingUtils = new LoggingUtils(TC_DBX_DT_30.class);

    @Test(description = "Verify the \"Download CSV\" button")
    public void verifyDownloadCSV() {
        test = extent.startTest("verifyDownloadCSV", "Verify the \"Download CSV\" button");
        test.assignCategory("Databricks - Data");
        DataTablesHelper dataTablesHelper = new DataTablesHelper(driver, test);
        dataTablesHelper.clickOnDataTab();
        dataTablesHelper.clickOnDataTablesTab();
        dataTablesHelper.selectWorkspaceForConfiguredMetastore();
        File downloadedFile = dataTablesHelper.downloadDataTable();
        Assert.assertTrue(downloadedFile.exists(), "CSV file not downloaded");
        loggingUtils.pass("CSV file downloaded successfully", test);
    }
}
