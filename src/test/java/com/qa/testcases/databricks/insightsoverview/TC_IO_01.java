package com.qa.testcases.databricks.insightsoverview;

import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.scripts.databricks.Insightsoverview.DbxInsightsOverview;
import com.qa.utils.LoggingUtils;
import com.qa.utils.WaitExecuter;
import org.testng.Assert;
import org.testng.annotations.Test;
@Marker.DbxInsightsOverview
public class TC_IO_01 extends BaseClass
{
    private final LoggingUtils loggingUtils = new LoggingUtils(TC_IO_01.class);

    @Test(description = "Verify whether Insight Overview tab is accessible")
    public void verifyInsightOverviewTabIsAccessible() {
        test = extent.startTest("TC_IO_01.verifyInsightOverviewTabIsAccessible", "Verify whether Insight Overview tab is accessible");
        test.assignCategory("Databricks - Insights Overview");
        DbxInsightsOverview insightsOverview = new DbxInsightsOverview(driver, test);
        insightsOverview.navigateToInsightsTab();
        WaitExecuter waitExecuter = new WaitExecuter(driver);
        waitExecuter.waitUntilPageFullyLoaded();

        waitExecuter.sleep(3000);
        Assert.assertTrue(driver.getCurrentUrl().contains("/value_dashboard"), "Insights Overview Page is not opened.");
        loggingUtils.pass("Insights Overview is opened.", test);
    }
}
