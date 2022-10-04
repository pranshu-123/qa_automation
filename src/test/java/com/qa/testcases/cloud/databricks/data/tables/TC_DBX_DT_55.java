package com.qa.testcases.cloud.databricks.data.tables;

import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.pagefactory.cloud.databricks.DataPageObject;
import com.qa.scripts.cloud.databricks.DataTablesHelper;
import com.qa.scripts.jobs.applications.AllApps;
import com.qa.utils.LoggingUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Ankur Jaiswal
 */

@Marker.DbxDataTables
@Marker.GCPDataTables
public class TC_DBX_DT_55 extends BaseClass {
    private final LoggingUtils loggingUtils = new LoggingUtils(this.getClass());

    @Test(description = "Verify the Instance Summary section on Application Details page")
    public void TC_DBX_DT_55_verifyInstanceSummaryPage(String clusterId) {
        test = extent.startTest("TC_DBX_DT_55.verifyInstanceSummaryPage",
            "Verify the Instance Summary section on Application Details page");
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
            dataTablesHelper.clickOnParentApp(0);
            List<String> instanceSummaryValues =
                dataPageObject.instanceSummaryValues.stream().map(instanceSummaryValue -> instanceSummaryValue.getText()).collect(Collectors.toList());
            if (instanceSummaryValues.size() < 3) {
                Assert.assertTrue(false, "Missing instance summary values");
            } else {
                Assert.assertNotEquals(instanceSummaryValues.get(0).trim(), "", "Cost value is blank");
                loggingUtils.pass("Cost value is loaded", test);
                Assert.assertNotEquals(instanceSummaryValues.get(1).trim(), "", "Duration value is blank");
                loggingUtils.pass("Duration value is loaded", test);
                Assert.assertNotEquals(instanceSummaryValues.get(2).trim(), "", "Data I/O value is blank");
                loggingUtils.pass("Data I/O value is loaded", test);
                dataTablesHelper.closeApplicationDetailsPage();
            }
        } finally {
            dataTablesHelper.backToTablesPage();
        }
    }
}
