package com.qa.testcases.cloud.databricks.data.tables;

import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.enums.UserAction;
import com.qa.pagefactory.cloud.databricks.DataPageObject;
import com.qa.scripts.cloud.databricks.DataTablesHelper;
import com.qa.scripts.jobs.applications.AllApps;
import com.qa.utils.LoggingUtils;
import com.qa.utils.RandomGenerator;
import com.qa.utils.WaitExecuter;
import com.qa.utils.actions.UserActions;
import org.openqa.selenium.Keys;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * @author Ankur Jaiswal
 */

@Marker.DbxDataTables
@Marker.GCPDataTables
public class TC_DBX_DT_60 extends BaseClass {
    private final LoggingUtils loggingUtils = new LoggingUtils(this.getClass());

    @Test(dataProvider = "clusterid-data-provider",description = "Validate the search box for application search")
    public void TC_DBX_DT_60_verifySearchBoxNavigationTab(String clusterId) {
        test = extent.startTest("TC_DBX_DT_60.verifySearchBoxNavigationTab",
            "Validate the search box for application search");
        test.assignCategory("Databricks - Data");
        DataTablesHelper dataTablesHelper = new DataTablesHelper(driver, test);
        dataTablesHelper.clickOnDataTab();
        dataTablesHelper.clickOnDataTablesTab();
        AllApps allApps = new AllApps(driver);
        allApps.selectWorkSpaceId(clusterId);
        /*dataTablesHelper.selectWorkspaceForConfiguredMetastore(clusterId);*/
        dataTablesHelper.clickOnMoreInfoOfNthRow(0);
        DataPageObject dataPageObject = new DataPageObject(driver);
        WaitExecuter waitExecuter = new WaitExecuter(driver);
        UserActions actions = new UserActions(driver);
        try {
            dataTablesHelper.clickOnTabOnTableDetails("Applications");
            dataTablesHelper.selectAllApplicationsColumn();
            String applicationId = dataPageObject.applicationId.getText();
            dataTablesHelper.clickOnParentApp(0);
            actions.performActionWithPolling(dataPageObject.navigationTabApplicationDetailsPage, UserAction.CLICK);
            waitExecuter.sleep(2000);
            actions.performActionWithPolling(dataPageObject.searchBoxForTableData, UserAction.SEND_KEYS, applicationId);
            Assert.assertTrue(dataPageObject.tableRows.size() > 0, "Data is not displayed when searched");
            dataPageObject.searchBoxForTableData.clear();
            actions.performActionWithPolling(dataPageObject.searchBoxForTableData, UserAction.SEND_KEYS,
                RandomGenerator.generateRandomName());
            Assert.assertEquals(dataPageObject.tableRows.size(), 0, "Data is displayed");
            loggingUtils.pass("Verified search box with application id", test);
            dataTablesHelper.closeApplicationDetailsPage();
        } finally {
            dataTablesHelper.backToTablesPage();
        }
    }
}
