package com.qa.testcases.cloud.databricks.data.tables;

import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.pagefactory.cloud.databricks.DataPageObject;
import com.qa.scripts.cloud.databricks.DataTablesHelper;
import com.qa.scripts.jobs.applications.AllApps;
import com.qa.utils.DateUtils;
import com.qa.utils.LoggingUtils;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * @author Ankur Jaiswal
 */

@Marker.DbxDataTables
@Marker.GCPDataTables
public class TC_DBX_DT_54 extends BaseClass {
    private final LoggingUtils loggingUtils = new LoggingUtils(this.getClass());

    @Test(description = "Verify the application details page.")
    public void verifyApplicationDetailsPage(String clusterId) {
        test = extent.startTest("TC_DBX_DT_54.verifyApplicationDetailsPage",
            "Verify the application details page.");
        test.assignCategory("Databricks - Data");
        DataTablesHelper dataTablesHelper = new DataTablesHelper(driver, test);
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
            String expectedStatus = dataPageObject.tableRows.get(0).findElements(By.tagName("td")).get(1).getText().trim();
            String expectedUser = dataPageObject.tableRows.get(0).findElements(By.tagName("td")).get(2)
                    .getText().replace("...","").trim();
            String expectedCluster = dataPageObject.tableRows.get(0).findElements(By.tagName("td")).get(5).getText().trim();
            String expectedDuration = dataPageObject.tableRows.get(0).findElements(By.tagName("td")).get(7).getText().trim();
            String expectedQueue = dataPageObject.tableRows.get(0).findElements(By.tagName("td")).get(8).getText().trim();

            dataTablesHelper.clickOnParentApp(0);
            String actualStatusValue = dataPageObject.statusValueApplicationPage.getText();
            String[] ownerClusterQueueDetailsValue =
                dataPageObject.ownerClusterQueueDetailsApplicationPage.getText().split("\\|");
            String actualOwnerValue = ownerClusterQueueDetailsValue[0].split(":")[1].trim();
            String actualClusterValue = ownerClusterQueueDetailsValue[1].split(":")[1].trim();
            String actualQueueValue = ownerClusterQueueDetailsValue[2].split(":")[1].trim();

            String[] startEndAndDurationValues =
                dataPageObject.startEndDurationDetailsApplicationPage.getText().split("\\|");
            String actualStartValue = startEndAndDurationValues[0].split(": ")[1].trim();
            String actualEndValue = startEndAndDurationValues[1].split(": ")[1].trim();
            String actualDurationValue = startEndAndDurationValues[2].split(": ")[1].trim();

            Assert.assertEquals(actualStatusValue.toLowerCase(), expectedStatus.toLowerCase(), "Incorrect status value displayed");
            loggingUtils.pass("Correct status value displayed", test);
            Assert.assertTrue(actualOwnerValue.contains(expectedUser), "Incorrect user value displayed");
            loggingUtils.pass("Correct user value displayed", test);
            Assert.assertTrue(actualClusterValue.contains(expectedCluster), "Incorrect cluster value displayed");
            loggingUtils.pass("Correct cluster value displayed", test);
            Assert.assertTrue(DateUtils.getDateTimeAsFormat(actualStartValue, "MM/dd/yyyy HH:mm:ss") != null
                , "Incorrect start time value displayed");
            loggingUtils.pass("Correct start time value displayed", test);
            Assert.assertTrue(DateUtils.getDateTimeAsFormat(actualEndValue, "MM/dd/yyyy HH:mm:ss") != null
                ,"End time value is blank");
            loggingUtils.pass("Correct end time value displayed", test);
            Assert.assertTrue(actualDurationValue.contains(expectedDuration), "Incorrect duration value displayed");
            loggingUtils.pass("Correct duration value displayed", test);
            Assert.assertTrue(actualQueueValue.contains(expectedQueue), "Incorrect queue value displayed");
            loggingUtils.pass("Correct queue value displayed", test);
            dataTablesHelper.closeApplicationDetailsPage();
        } finally {
            dataTablesHelper.backToTablesPage();
        }
    }
}
