package com.qa.testcases.cloud.databricks.data.tables;

import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.pagefactory.cloud.databricks.DataPageObject;
import com.qa.scripts.cloud.databricks.DataTablesHelper;
import com.qa.utils.LoggingUtils;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

/**
 * @author Ankur Jaiswal
 */
@Marker.DBX.Data
@Marker.DbxDataTables
public class TC_DBX_DT_39 extends BaseClass {
    private final LoggingUtils loggingUtils = new LoggingUtils(this.getClass());

    @Test(description = "Verify the Applications table of Table Details Page")
    public void verifyApplicationsDetailsTable() {
        test = extent.startTest("TC_DBX_DT_39.verifyApplicationsDetailsTable", "Verify the Applications table of Table Details Page.");
        test.assignCategory("Databricks - Data");
        DataTablesHelper dataTablesHelper = new DataTablesHelper(driver, test);
        DataPageObject dataPageObject = new DataPageObject(driver);
        dataTablesHelper.clickOnDataTab();
        dataTablesHelper.clickOnDataTablesTab();
        dataTablesHelper.selectWorkspaceForConfiguredMetastore();
        dataTablesHelper.clickOnMoreInfoOfNthRow(0);
        try {
            dataTablesHelper.clickOnTabOnTableDetails("Applications");
            List<WebElement> tableHeadings = dataPageObject.tableHeadings;
            Assert.assertTrue(tableHeadings.size() > 0, "Applications details table is not loaded");
            loggingUtils.pass("Applications details table is loaded", test);
            Assert.assertEquals(tableHeadings.get(0).getText().trim(), "Type", "Type column does not exist in " +
                "table.");
            loggingUtils.info("Verified whether Type column exists in table.", test);
            Assert.assertEquals(tableHeadings.get(1).getText().trim(), "Status", "Status column does not exist in " +
                "table.");
            loggingUtils.info("Verified whether Status column exists in table.", test);
            Assert.assertEquals(tableHeadings.get(2).getText().trim(), "User", "User column does not exist in table.");
            loggingUtils.info("Verified whether User column exists in table.", test);
            Assert.assertEquals(tableHeadings.get(3).getText().trim(), "App Name / ID", "App Name / ID column does " +
                "not exist in table.");
            loggingUtils.info("Verified whether App Name / ID column exists in table.", test);
            Assert.assertEquals(tableHeadings.get(4).getText().trim(), "Insights", "Insights column does not exist in" +
                " table.");
            loggingUtils.info("Verified whether Insights column exists in table.", test);
            Assert.assertEquals(tableHeadings.get(5).getText().trim(), "Cluster Id", "Cluster Id column does not " +
                "exist in table.");
            loggingUtils.info("Verified whether Cluster Id column exists in table.", test);
            Assert.assertEquals(tableHeadings.get(6).getText().trim(), "Start Time", "Start Time column does not " +
                "exist in table.");
            loggingUtils.info("Verified whether Start Time column exists in table.", test);
            Assert.assertEquals(tableHeadings.get(7).getText().trim(), "Duration", "Duration column does not exist in" +
                " table.");
            loggingUtils.info("Verified whether type column exists in table.", test);
            Assert.assertEquals(tableHeadings.get(8).getText().trim(), "Queue", "Queue column does not exist in table" +
                ".");
            loggingUtils.info("Verified whether Queue column exists in table.", test);
            Assert.assertEquals(tableHeadings.get(9).getText().trim(), "Read", "Read column does not exist in table.");
            loggingUtils.info("Verified whether Read column exists in table.", test);
            Assert.assertEquals(tableHeadings.get(10).getText().trim(), "Write", "Write column does not exist in " +
                "table.");
            loggingUtils.info("Verified whether Write column exists in table.", test);
            Assert.assertEquals(tableHeadings.get(11).getText().trim(), "Parent App", "Parent App column does not " +
                "exist in table.");
            loggingUtils.info("Verified whether Parent App column exists in table.", test);
        } finally {
            dataTablesHelper.backToTablesPage();
        }
        loggingUtils.pass("Verified displayed columns of applications.", test);
    }
}
