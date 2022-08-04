package com.qa.testcases.cloud.databricks.data.overview;

import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.scripts.cloud.databricks.DataOverviewHelper;
import com.qa.scripts.cloud.databricks.DataTablesHelper;
import com.qa.scripts.jobs.applications.AllApps;
import com.qa.utils.LoggingUtils;
import com.qa.utils.WaitExecuter;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * @author Ankur Jaiswal
 */
@Marker.DbxDataOverview
@Marker.EmrDataOverview
@Marker.GCPDataOverview
public class TC_DBX_DO_12 extends BaseClass {
    private final LoggingUtils loggingUtils = new LoggingUtils(this.getClass());

    @Test(dataProvider = "clusterid-data-provider",description = "Validate whether user is able to change the setting of label of Table state graph")
    public void verifyTableStatesByChangeAge(String clusterId) {
        test = extent.startTest("TC_DBX_DO_10.verifyTableStatesByChangeAge", "Validate whether user" +
                " is able to change the setting of label of Table state graph");
        test.assignCategory("Databricks - Data");
        DataTablesHelper dataTablesHelper = new DataTablesHelper(driver, test);
        DataOverviewHelper dataOverviewHelper = new DataOverviewHelper(driver, test);
        AllApps allApps = new AllApps(driver);
        WaitExecuter waitExecuter = new WaitExecuter(driver);
        dataTablesHelper.clickOnDataTab();
        allApps.selectWorkSpaceId(clusterId);
        waitExecuter.sleep(3000);
        Boolean changeAppSetting = dataOverviewHelper.changeAppliedStateSettings("age");
        Assert.assertTrue(changeAppSetting, "User is not able to change table state setting by changing \"Age\"");
        loggingUtils.pass("User is able to change setting of table state by changing \"Age\"", test);
    }
}
