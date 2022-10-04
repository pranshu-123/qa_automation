package com.qa.testcases.cloud.databricks.data.tables;

import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.scripts.cloud.databricks.DataTablesHelper;
import com.qa.scripts.jobs.applications.AllApps;
import com.qa.utils.LoggingUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

/**
 * @author Ankur Jaiswal
 */

@Marker.DbxDataTables
@Marker.GCPDataTables
public class TC_DBX_DT_50 extends BaseClass {
    private final LoggingUtils loggingUtils = new LoggingUtils(this.getClass());

    @Test(description = "Verify the \"Read\" column of \"Applications\" table.")
    public void verifyReadColumnApplicationsTable(String clusterId) {
        test = extent.startTest("TC_DBX_DT_50.verifyReadColumnApplicationsTable",
            "Verify the \"Read\" column of \"Applications\" table.");
        test.assignCategory("Databricks - Data");
        DataTablesHelper dataTablesHelper = new DataTablesHelper(driver, test);
        dataTablesHelper.clickOnDataTab();
        dataTablesHelper.clickOnDataTablesTab();
        AllApps allApps = new AllApps(driver);
        allApps.selectWorkSpaceId(clusterId);
        /*dataTablesHelper.selectWorkspaceForConfiguredMetastore(clusterId);*/
        dataTablesHelper.clickOnMoreInfoOfNthRow(0);
        try {
            dataTablesHelper.clickOnTabOnTableDetails("Applications");
            dataTablesHelper.selectAllApplicationsColumn();
            List<String> readValues = dataTablesHelper.getColumnValuesFromApplicationsTable("Read");
            readValues.stream().forEach(readValue -> {
                Assert.assertNotEquals(readValue, "");
                Assert.assertTrue(readValue.matches(".*[(GB)(MB)(KB)(B)].*"),"Read column does not contains value in " +
                    "GB, MB, KB or B");
            });
            loggingUtils.pass("Verify all applications contains correct Read value.", test);
        } finally {
            dataTablesHelper.backToTablesPage();
        }
    }
}
