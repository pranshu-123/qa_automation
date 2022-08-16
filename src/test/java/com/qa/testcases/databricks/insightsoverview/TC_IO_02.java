package com.qa.testcases.databricks.insightsoverview;

import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.scripts.databricks.Insightsoverview.DbxInsightsOverview;
import com.qa.utils.LoggingUtils;
import com.qa.utils.WaitExecuter;
import com.relevantcodes.extentreports.LogStatus;
import org.testng.Assert;
import org.testng.annotations.Test;
@Marker.DbxInsightsOverview
public class TC_IO_02 extends BaseClass
{
    private final LoggingUtils LOGGER = new LoggingUtils(TC_IO_02.class);

    @Test(description = "Verify whether Insight Overview Page should be visible COST,RESOURCE EFFICIENCY,APP ACCELERATION")
    public void verifyInsightTabPage() {
        test = extent.startTest("TC_IO_02.verifyInsightTabPage", "Verify whether Insight Overview Page should be visible COST,RESOURCE EFFICIENCY,APP ACCELERATION");
        test.assignCategory("Databricks - Insights Overview");
        DbxInsightsOverview insightsOverview = new DbxInsightsOverview(driver, test);
        insightsOverview.navigateToInsightsTab();
        WaitExecuter waitExecuter = new WaitExecuter(driver);
        waitExecuter.waitUntilPageFullyLoaded();
        insightsOverview.validateInsightsTabPresence();
        test.log(LogStatus.PASS, "All the Insights tabs are listed");
        LOGGER.info("All the Insights tabs are listed",test);
        waitExecuter.sleep(3000);

    }
}

