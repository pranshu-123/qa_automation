package com.qa.testcases.cloud.databricks.data.overview;

import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
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
public class TC_DBX_DO_1 extends BaseClass {
    private final LoggingUtils loggingUtils = new LoggingUtils(TC_DBX_DO_1.class);

    @Test(description = "Verify whether Data tab is accessible")
    public void verifyDataTabIsAccessible() {
        test = extent.startTest("TC_DBX_DO_1.verifyDataTabIsAccessible", "Verify whether Data tab is accessible");
        test.assignCategory("Databricks - Data");
        AllApps allApps = new AllApps(driver);
        DataTablesHelper dataTablesHelper = new DataTablesHelper(driver, test);
        dataTablesHelper.clickOnDataTab();
        WaitExecuter waitExecuter = new WaitExecuter(driver);
        waitExecuter.waitUntilPageFullyLoaded();

        waitExecuter.sleep(3000);
        Assert.assertTrue(driver.getCurrentUrl().contains("/data/overview"), "Data Overview Page is not opened.");
        loggingUtils.pass("Data Overview is opened.", test);
    }
}
