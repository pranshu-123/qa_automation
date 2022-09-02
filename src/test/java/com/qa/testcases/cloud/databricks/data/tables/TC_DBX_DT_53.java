package com.qa.testcases.cloud.databricks.data.tables;

import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.pagefactory.cloud.databricks.DataPageObject;
import com.qa.scripts.cloud.databricks.DataTablesHelper;
import com.qa.utils.LoggingUtils;
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
    public void validateParentAppColumnApplicationsTable() {
        test = extent.startTest("TC_DBX_DT_53.validateParentAppColumnApplicationsTable",
            "Validate the Parent App column of the table detail.");
        test.assignCategory("Databricks - Data");
        DataTablesHelper dataTablesHelper = new DataTablesHelper(driver, test);
        dataTablesHelper.clickOnDataTab();
        dataTablesHelper.clickOnDataTablesTab();
        dataTablesHelper.selectWorkspaceForConfiguredMetastore();
        dataTablesHelper.clickOnMoreInfoOfNthRow(0);
        DataPageObject dataPageObject = new DataPageObject(driver);
        try {
            dataTablesHelper.clickOnTabOnTableDetails("Applications");
            dataTablesHelper.selectAllApplicationsColumn();
            String expectedClusterId = dataPageObject.tableRows.get(0).findElements(By.tagName("td")).get(6).getText();
            String expectedApplicationId = dataPageObject.applicationId.getText();
            dataTablesHelper.clickOnParentAppOfNthRow(0);
            String applicationDetailsHeading = dataPageObject.applicationDetailsHeading.getText();
            Assert.assertTrue(applicationDetailsHeading.contains(expectedClusterId) &&
                applicationDetailsHeading.contains(expectedApplicationId), "Incorrect applicationId or clusterId are" +
                "displayed");
            loggingUtils.pass("Correct applicationId or clusterId are displayed", test);
        } catch (Exception e) {
            loggingUtils.error("Exception occured " +e.getStackTrace(),test);
            dataTablesHelper.closeApplicationDetailsPage();
            dataTablesHelper.backToTablesPage();
        }
    }
}