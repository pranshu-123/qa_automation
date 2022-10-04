package com.qa.testcases.cloud.databricks.data.tables;

import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.pagefactory.cloud.databricks.DataPageObject;
import com.qa.scripts.cloud.databricks.DataTablesHelper;
import com.qa.scripts.jobs.applications.AllApps;
import com.qa.utils.ActionPerformer;
import com.qa.utils.LoggingUtils;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Ankur Jaiswal
 */

@Marker.DbxDataTables
@Marker.GCPDataTables
public class TC_DBX_DT_57 extends BaseClass {
    private final LoggingUtils loggingUtils = new LoggingUtils(this.getClass());

    @Test(description = "Verify the options for chart display on application details page")
    public void TC_DBX_DT_57_verifyOptionsOfLeftGraphApplicationDetailPage(String clusterId) {
        test = extent.startTest("TC_DBX_DT_57.verifyOptionsOfLeftGraphApplicationDetailPage",
            "Verify the options for chart display on application details page.");
        test.assignCategory("Databricks - Data");
        DataTablesHelper dataTablesHelper = new DataTablesHelper(driver, test);
        dataTablesHelper.clickOnDataTab();
        dataTablesHelper.clickOnDataTablesTab();
        AllApps allApps = new AllApps(driver);
        allApps.selectWorkSpaceId(clusterId);
        /*dataTablesHelper.selectWorkspaceForConfiguredMetastore(clusterId);*/
        dataTablesHelper.clickOnMoreInfoOfNthRow(0);
        DataPageObject dataPageObject = new DataPageObject(driver);
        ActionPerformer actionPerformer = new ActionPerformer(driver);
        try {
            dataTablesHelper.clickOnTabOnTableDetails("Applications");
            dataTablesHelper.selectAllApplicationsColumn();
            dataTablesHelper.clickOnParentApp(0);
            actionPerformer.moveToTheElement(dataPageObject.dropdownIconOnApplicationDetails);
            List<String> leftGraphOptions = dataPageObject.leftGraphOptions.stream()
                .map(option -> option.getText().trim()).collect(Collectors.toList());
            List<String> expectedOptions = Arrays.asList("Duration", "I/O", "Apps", "Events");
            Assert.assertEquals(leftGraphOptions, expectedOptions, "Incorrect options displayed.");
            loggingUtils.pass("Verified Duration, I/O, Apps, Events options are displayed", test);
            dataTablesHelper.closeApplicationDetailsPage();
        } finally {
            dataTablesHelper.backToTablesPage();
        }
    }
}
