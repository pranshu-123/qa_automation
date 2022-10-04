package com.qa.testcases.cloud.databricks.data.tables;

import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.pagefactory.cloud.databricks.DataPageObject;
import com.qa.scripts.cloud.databricks.DataTablesHelper;
import com.qa.scripts.jobs.applications.AllApps;
import com.qa.utils.LoggingUtils;
import com.qa.utils.WaitExecuter;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;


/**
 * @author Ankur Jaiswal
 */
@Marker.DbxDataTables
@Marker.GCPDataTables
public class TC_DBX_DT_53 extends BaseClass {
    private final LoggingUtils loggingUtils = new LoggingUtils(this.getClass());

    @Test(description = "Validate the Parent App column of the table detail.")
    public void validateParentAppColumnApplicationsTable(String clusterId) throws InterruptedException {
        test = extent.startTest("TC_DBX_DT_53.validateParentAppColumnApplicationsTable",
                "Validate the Parent App column of the table detail.");
        test.assignCategory("Databricks - Data");
        DataTablesHelper dataTablesHelper = new DataTablesHelper(driver, test);
        WaitExecuter executor = new WaitExecuter(driver);
        dataTablesHelper.clickOnDataTab();
        dataTablesHelper.clickOnDataTablesTab();
        AllApps allApps = new AllApps(driver);
        allApps.selectWorkSpaceId(clusterId);
        /*dataTablesHelper.selectWorkspaceForConfiguredMetastore(clusterId);*/
        dataTablesHelper.clickOnMoreInfoOfNthRow(0);
        DataPageObject dataPageObject = new DataPageObject(driver);
        try {
            dataTablesHelper.clickOnTabOnTableDetails("Applications");
            dataTablesHelper.selectAllApplicationsColumn();
            executor.waitForSeconds(5);
            String expectedClusterId = dataPageObject.tableRows.get(0).findElements(By.tagName("td")).get(5).getText();
            loggingUtils.pass("Correct applicationId or clusterId are displayed" + expectedClusterId, test);
            String expectedApplicationId = dataPageObject.applicationId.getText();
            loggingUtils.pass("Correct applicationId or clusterId are displayed" + expectedApplicationId, test);
            dataTablesHelper.clickOnParentApp(0);
            String applicationDetailsHeading = dataPageObject.applicationDetailsHeading.getText();
            loggingUtils.pass("Correct applicationId or clusterId are displayed" + applicationDetailsHeading, test);
            executor.waitForSeconds(5);
            Assert.assertTrue(applicationDetailsHeading.contains(expectedClusterId) &&
                    applicationDetailsHeading.contains(expectedApplicationId), "Incorrect applicationId or clusterId are" +
                    "displayed");
            loggingUtils.pass("Correct applicationId or clusterId are displayed", test);
            dataTablesHelper.closeApplicationDetailsPage();
            dataTablesHelper.backToTablesPage();
        } catch (Exception e) {
            executor.waitForSeconds(5);
            dataTablesHelper.closeApplicationDetailsPage();
            dataTablesHelper.backToTablesPage();
            loggingUtils.error("Exception occured " + e.getStackTrace(), test);
        }
    }
}