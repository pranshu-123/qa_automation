package com.qa.testcases.cloud.databricks.data.tables;

import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.scripts.cloud.databricks.DataTablesHelper;
import com.qa.scripts.jobs.applications.AllApps;
import com.qa.utils.LoggingUtils;
import org.testng.annotations.Test;

import java.util.List;

/**
 * @author Ankur Jaiswal
 */
@Marker.DbxDataTables
@Marker.GCPDataTables
public class TC_DBX_DT_11 extends BaseClass {
    private final LoggingUtils loggingUtils = new LoggingUtils(TC_DBX_DT_11.class);

    @Test(dataProvider = "clusterid-data-provider",description = "Validate the data table details are displayed based on applied filter for table state.")
    public void verifyTablesDisplayedAsStateFilter(String clusterId) {
        test = extent.startTest("TC_DBX_DT_11.verifyTablesDisplayedAsStateFilter", "Validate the data table details" +
            " are displayed based on applied filter for table state.");
        test.assignCategory("Databricks - Data");
        DataTablesHelper dataTablesHelper = new DataTablesHelper(driver, test);
        dataTablesHelper.clickOnDataTab();
        dataTablesHelper.clickOnDataTablesTab();
        AllApps allApps = new AllApps(driver);
        allApps.selectWorkSpaceId(clusterId);
        /*dataTablesHelper.selectWorkspaceForConfiguredMetastore(clusterId);*/
        List<Integer> tableStateFilterSetting = dataTablesHelper.getAppliedStateFilter();
        int hotByAge = tableStateFilterSetting.get(0);
        int coldByAge = tableStateFilterSetting.get(1);
        int hotByLatestAccess = tableStateFilterSetting.get(2);
        int coldByLatestAccess = tableStateFilterSetting.get(3);

        loggingUtils.info("Selecting only Hot", test);
        dataTablesHelper.checkIfTablesDisplayedBasedOnAppliedFilter("hot", new int[]{hotByAge, hotByLatestAccess});
        loggingUtils.pass("Data is displayed as per applied table state: Hot", test);

        loggingUtils.info("Selecting only Cold", test);
        dataTablesHelper.checkIfTablesDisplayedBasedOnAppliedFilter("cold", new int[]{coldByAge, coldByLatestAccess});
        loggingUtils.pass("Data is displayed as per applied table state: Cold", test);

        loggingUtils.info("Selecting only Warm", test);
        dataTablesHelper.checkIfTablesDisplayedBasedOnAppliedFilter("warm", new int[]{hotByAge, hotByLatestAccess,
                coldByAge, coldByLatestAccess});
        loggingUtils.pass("Data is displayed as per applied table state: Warm", test);
    }
}
