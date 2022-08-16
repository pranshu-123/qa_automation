package com.qa.testcases.data.overview;

import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.scripts.data.DataOverview;
import com.qa.scripts.jobs.applications.AllApps;
import com.qa.utils.LoggingUtils;
import com.qa.utils.WaitExecuter;
import org.testng.annotations.Test;
@Marker.DataOverview
public class TC_DO_2 extends BaseClass {
    private final LoggingUtils loggingUtils = new LoggingUtils(TC_DO_2.class);

    @Test(dataProvider = "clusterid-data-provider",description = "Verify workspace on data page")
    public void TC_DO_2_verifyWorkspaceOnDataPage(String clusterId) {
        test = extent.startTest("TC_DO_2.verifyWorkspaceOnDataPage", "Verify workspace on data page");
        test.assignCategory("Data/Overview");
        AllApps allApps = new AllApps(driver);
        WaitExecuter waitExecuter = new WaitExecuter(driver);
        DataOverview dataOverview = new DataOverview(driver, test);
        dataOverview.clickOnDataTab();
        allApps.selectWorkSpaceId(clusterId);
        waitExecuter.sleep(3000);
        dataOverview.selectWorkspaceForConfiguredMetastore();
        loggingUtils.pass("Selected the workspace which has configured metastore", test);
    }
}
