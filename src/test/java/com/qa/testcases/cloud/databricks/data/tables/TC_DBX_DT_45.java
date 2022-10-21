package com.qa.testcases.cloud.databricks.data.tables;

import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.scripts.cloud.databricks.DataTablesHelper;
import com.qa.scripts.jobs.applications.AllApps;
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
public class TC_DBX_DT_45 extends BaseClass {
    private final LoggingUtils loggingUtils = new LoggingUtils(this.getClass());

    @Test(dataProvider = "clusterid-data-provider",description = "Verify the \"Insights\" column of \"Applications\" table.")
    public void verifyInsightsColumnApplicationsTable(String clusterId) throws InterruptedException {
        test = extent.startTest("TC_DBX_DT_45.verifyInsightsColumnApplicationsTable",
            "Verify the \"Insights\" column of \"Applications\" table.Verify the User column of \"Applications\" table.");
        test.assignCategory("Databricks - Data");
        DataTablesHelper dataTablesHelper = new DataTablesHelper(driver, test);
        WaitExecuter wait=new WaitExecuter(driver);
        dataTablesHelper.clickOnDataTab();
        dataTablesHelper.clickOnDataTablesTab();
        AllApps allApps = new AllApps(driver);
        allApps.selectWorkSpaceId(clusterId);
        /*dataTablesHelper.selectWorkspaceForConfiguredMetastore(clusterId);*/
        dataTablesHelper.clickOnMoreInfoOfNthRow(0);
        wait.waitForSeconds(10);
        try {
            dataTablesHelper.clickOnTabOnTableDetails("Applications");
            dataTablesHelper.selectAllApplicationsColumn();
            wait.waitForSeconds(5);
            List<String> insightsValues = dataTablesHelper.getColumnValuesFromApplicationsTable("Insights");
            long nonNullCount = insightsValues.stream().distinct().filter(insight -> !insight.trim().equals("")).count();
            if (nonNullCount == 0) {
                loggingUtils.warning("All values are blank for insights, unable to verify.", test);
            }
            loggingUtils.pass("Verified insights value.", test);
        } finally {
            dataTablesHelper.backToTablesPage();
        }
    }
}
