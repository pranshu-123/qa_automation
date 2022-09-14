package com.qa.testcases.cloud.databricks.data.tables;

import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.scripts.cloud.databricks.DataTablesHelper;
import com.qa.utils.LoggingUtils;
import com.qa.utils.WaitExecuter;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

/**
 * @author Ankur Jaiswal
 */

@Marker.DbxDataTables
@Marker.GCPDataTables
public class TC_DBX_DT_46 extends BaseClass {
    private final LoggingUtils loggingUtils = new LoggingUtils(this.getClass());

    @Test(description = "Verify the \"Cluster Id\" column of \"Applications\" table.")
    public void verifyClusterIdColumnApplicationsTable() throws InterruptedException {
        test = extent.startTest("TC_DBX_DT_46.verifyClusterIdColumnApplicationsTable",
            "Verify the \"Cluster Id\" column of \"Applications\" table.");
        test.assignCategory("Databricks - Data");
        DataTablesHelper dataTablesHelper = new DataTablesHelper(driver, test);
        WaitExecuter wait=new WaitExecuter(driver);
        dataTablesHelper.clickOnDataTab();
        dataTablesHelper.clickOnDataTablesTab();
        dataTablesHelper.selectWorkspaceForConfiguredMetastore();
        dataTablesHelper.clickOnMoreInfoOfNthRow(0);
        wait.waitForSeconds(10);
        try {
            dataTablesHelper.clickOnTabOnTableDetails("Applications");
            dataTablesHelper.selectAllApplicationsColumn();
            wait.waitForSeconds(10);
            List<String> clusterValues = dataTablesHelper.getColumnValuesFromApplicationsTable("Cluster Id");
            clusterValues.stream().forEach(clusterValue -> Assert.assertNotEquals(clusterValue, ""));
            loggingUtils.pass("Verify all applications contains cluster id value.", test);
        } finally {
            dataTablesHelper.backToTablesPage();
        }
    }
}
