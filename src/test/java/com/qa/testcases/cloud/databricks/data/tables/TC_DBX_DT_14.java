package com.qa.testcases.cloud.databricks.data.tables;

import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.pagefactory.cloud.databricks.DataPageObject;
import com.qa.scripts.cloud.databricks.DataTablesHelper;
import com.qa.scripts.jobs.applications.AllApps;
import com.qa.utils.LoggingUtils;
import com.qa.utils.WaitExecuter;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * @author Ankur Jaiswal
 */

@Marker.DbxDataTables
@Marker.GCPDataTables
public class TC_DBX_DT_14 extends BaseClass {
    private final LoggingUtils loggingUtils = new LoggingUtils(TC_DBX_DT_14.class);

    @Test(description = "Verify the table events filter present.")
    public void verifyTableEventFilter(String clusterId) {
        test = extent.startTest("TC_DBX_DT_14.verifyTableEventFilter", "Verify the table events filter present.");
        test.assignCategory("Databricks - Data");
        DataTablesHelper dataTablesHelper = new DataTablesHelper(driver, test);
        dataTablesHelper.clickOnDataTab();
        dataTablesHelper.clickOnDataTablesTab();
        AllApps allApps = new AllApps(driver);
        allApps.selectWorkSpaceId(clusterId);
        /*dataTablesHelper.selectWorkspaceForConfiguredMetastore(clusterId);*/
        DataPageObject dataPageObject = new DataPageObject(driver);
        WaitExecuter executor = new WaitExecuter(driver);
        executor.waitUntilElementPresent(dataPageObject.tableEventsFilterLabel);
        Boolean isTableEventsPresent = false;
        for (WebElement tableEvent : dataPageObject.tableEventsOptionsLabel) {
            isTableEventsPresent = true;
            Assert.assertNotEquals(tableEvent.getText().trim(), "", "Table event is empty");
        }
        Assert.assertTrue(isTableEventsPresent, "No table Events are present");
        loggingUtils.pass("Table Events are present", test);
    }
}
