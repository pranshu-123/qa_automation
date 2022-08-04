package com.qa.testcases.cloud.databricks.data.tables;

import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.scripts.cloud.databricks.DataTablesHelper;
import com.qa.utils.LoggingUtils;
import org.testng.annotations.Test;

import java.util.List;

/**
 * @author Ankur Jaiswal
 */
@Marker.DbxDataTables
@Marker.GCPDataTables
public class TC_DBX_DT_12 extends BaseClass {
    private final LoggingUtils loggingUtils = new LoggingUtils(TC_DBX_DT_12.class);

    @Test(description = "Validate whether user is able to change the setting of label by making changes in Age")
    public void verifyTablesDisplayedIfChangedAge() {
        test = extent.startTest("TC_DBX_DT_12.verifyTablesDisplayedIfChangedAge", "Validate whether user is able" +
                " to change the setting of label by making changes in Age");
        test.assignCategory("Databricks - Data");
        DataTablesHelper dataTablesHelper = new DataTablesHelper(driver, test);
        dataTablesHelper.clickOnDataTab();
        dataTablesHelper.clickOnDataTablesTab();
        dataTablesHelper.selectWorkspaceForConfiguredMetastore();
        dataTablesHelper.changeAppliedStateSettings("age");
        List<Integer> tableStateFilterSetting = dataTablesHelper.getAppliedStateFilter();
        loggingUtils.info("Changed the setting for age", test);
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
        loggingUtils.pass("Data is displayed as per applied table state: Warm", test);
    }
}
