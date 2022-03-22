package com.qa.testcases.cloud.databricks.data.tables;

import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.pagefactory.cloud.databricks.DataPageObject;
import com.qa.scripts.cloud.databricks.DataTablesHelper;
import com.qa.utils.LoggingUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Ankur Jaiswal
 */

@Marker.DBX.Data
public class TC_DBX_DT_15 extends BaseClass {
    private final LoggingUtils loggingUtils = new LoggingUtils(TC_DBX_DT_15.class);

    @Test(description = "Verify the table columns.")
    public void verifyTableColumns() {
        test = extent.startTest("verifyTableColumns", "Verify the table columns.");
        test.assignCategory("Databricks - Data");
        DataTablesHelper dataTablesHelper = new DataTablesHelper(driver, test);
        dataTablesHelper.clickOnDataTab();
        dataTablesHelper.clickOnDataTablesTab();
        dataTablesHelper.selectWorkspaceForConfiguredMetastore();
        DataPageObject dataPageObject = new DataPageObject(driver);
        List<String> tableColumns=
            dataPageObject.tableHeadings.stream().map(tableHeading -> tableHeading.getText().trim()).collect(Collectors.toList());
        Assert.assertEquals(tableColumns.get(1), "Database", "Database column is missing in data table");
        loggingUtils.pass("Database column is present in data table", test);
        Assert.assertEquals(tableColumns.get(2), "Table", "Table column is missing in data table");
        loggingUtils.pass("Table column is present in data table", test);
        Assert.assertEquals(tableColumns.get(3), "Owner", "Owner column is missing in data table");
        loggingUtils.pass("Owner column is present in data table", test);
        Assert.assertEquals(tableColumns.get(4), "Path", "Path column is missing in data table");
        loggingUtils.pass("Path column is present in data table", test);
        Assert.assertEquals(tableColumns.get(5), "Table Type", "Table Type column is missing in data table");
        loggingUtils.pass("Table Type column is present in data table", test);
        Assert.assertEquals(tableColumns.get(6), "File System", "File System column is missing in data table");
        loggingUtils.pass("File System column is present in data table", test);
        Assert.assertEquals(tableColumns.get(7), "Storage Format", "Storage Format column is missing in data table");
        loggingUtils.pass("Storage column is present in data table", test);
        Assert.assertEquals(tableColumns.get(8), "Created", "Created column is missing in data table");
        loggingUtils.pass("Created column is present in data table", test);
        Assert.assertEquals(tableColumns.get(9), "Latest Access", "Latest Access column is missing in data table");
        loggingUtils.pass("Latest Access column is present in data table", test);
        Assert.assertEquals(tableColumns.get(10), "Size", "Size column is missing in data table");
        loggingUtils.pass("Size column is present in data table", test);
        Assert.assertEquals(tableColumns.get(11), "Apps", "Apps column is missing in data table");
        loggingUtils.pass("Apps column is present in data table", test);
        Assert.assertEquals(tableColumns.get(12), "Partitions", "Partitions column is missing in data table");
        loggingUtils.pass("Partitions column is present in data table", test);
        Assert.assertEquals(tableColumns.get(13), "Users", "Users column is missing in data table");
        loggingUtils.pass("Users column is present in data table", test);
        Assert.assertEquals(tableColumns.get(14), "More Info", "More Info column is missing in data table");
        loggingUtils.pass("More Info column is present in data table", test);
    }
}
