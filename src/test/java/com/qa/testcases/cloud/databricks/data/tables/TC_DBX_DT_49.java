package com.qa.testcases.cloud.databricks.data.tables;

import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.pagefactory.cloud.databricks.DataPageObject;
import com.qa.scripts.cloud.databricks.DataTablesHelper;
import com.qa.scripts.jobs.applications.AllApps;
import com.qa.utils.LoggingUtils;
import com.qa.utils.WaitExecuter;
import com.qa.utils.actions.UserActions;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

/**
 * @author Ankur Jaiswal
 */

@Marker.DbxDataTables
@Marker.GCPDataTables
public class TC_DBX_DT_49 extends BaseClass {
    private final LoggingUtils loggingUtils = new LoggingUtils(this.getClass());

    @Test(dataProvider = "clusterid-data-provider",description = "Verify the \"Queue\" column of \"Applications\" table.")
    public void verifyQueueColumnApplicationsTable(String clusterId) throws InterruptedException {
        test = extent.startTest("TC_DBX_DT_49.verifyQueueColumnApplicationsTable",
            "Verify the \"Queue\" column of \"Applications\" table.");
        test.assignCategory("Databricks - Data");
        DataTablesHelper dataTablesHelper = new DataTablesHelper(driver, test);
        DataPageObject dataPageObject = new DataPageObject(driver);
        WaitExecuter waitExecuter = new WaitExecuter(driver);
        dataTablesHelper.clickOnDataTab();
        dataTablesHelper.clickOnDataTablesTab();
        AllApps allApps = new AllApps(driver);
        allApps.selectWorkSpaceId(clusterId);
        waitExecuter.waitForSeconds(2);
        dataPageObject.sortByDurationApp.click();
        waitExecuter.waitUntilElementPresent(dataPageObject.sortDown);
        dataPageObject.sortDown.click();
        waitExecuter.waitUntilPageFullyLoaded();
        /*dataTablesHelper.selectWorkspaceForConfiguredMetastore(clusterId);*/
        dataTablesHelper.clickOnMoreInfoOfNthRow(0);
        try {
            dataTablesHelper.clickOnTabOnTableDetails("Applications");
            dataTablesHelper.selectAllApplicationsColumn();
            List<String> queueValues = dataTablesHelper.getColumnValuesFromApplicationsTable("Queue");
            queueValues.stream().forEach(queueValue ->
                Assert.assertEquals(queueValue, ""));
            loggingUtils.pass("Verify all applications contains correct queue value.", test);
        } finally {
            dataTablesHelper.backToTablesPage();
        }
    }
}
