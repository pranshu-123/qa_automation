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
public class TC_DBX_DT_48 extends BaseClass {
    private final LoggingUtils loggingUtils = new LoggingUtils(this.getClass());

    @Test(dataProvider = "clusterid-data-provider",description = "Verify the \"Duration\" column of \"Applications\" table.")
    public void verifyDurationColumnApplicationsTable(String clusterId) {
        test = extent.startTest("TC_DBX_DT_48.verifyDurationColumnApplicationsTable",
            "Verify the \"Duration\" column of \"Applications\" table.");
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
            List<String> durationValues = dataTablesHelper.getColumnValuesFromApplicationsTable("Duration");
            durationValues.stream().forEach(durationValue -> {
                Assert.assertNotEquals(durationValue, "");
                Assert.assertTrue(durationValue.matches(".*[dhms].*"), "Days, Hours, Minutes or Seconds does not " +
                    "exists in value");
            });
            loggingUtils.pass("Verify all applications contains correct duration value.", test);
        } finally {
            dataTablesHelper.backToTablesPage();
        }
    }
}
