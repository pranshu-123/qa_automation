package com.qa.testcases.databricks.insightsoverview;

import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.scripts.DatePicker;
import com.qa.scripts.databricks.Insightsoverview.DbxInsightsOverview;
import com.qa.utils.LoggingUtils;
import com.qa.utils.WaitExecuter;
import com.relevantcodes.extentreports.LogStatus;
import org.testng.Assert;
import org.testng.annotations.Test;

@Marker.DbxInsightsOverview
public class TC_IO_03 extends BaseClass
{
    private final LoggingUtils LOGGER = new LoggingUtils(TC_IO_03.class);

    @Test(description = "Verify whether Insight Overview Page should be visible COST,RESOURCE EFFICIENCY,APP ACCELERATION")
    public void verifyInsightTabPage() {
        test = extent.startTest("TC_IO_02.verifyInsightTabPage", "Verify whether Insight Overview Page should be visible COST,RESOURCE EFFICIENCY,APP ACCELERATION");
        test.assignCategory("Databricks - Insights Overview");
        DbxInsightsOverview insightsOverview = new DbxInsightsOverview(driver, test);
        insightsOverview.navigateToInsightsTab();
        WaitExecuter waitExecuter = new WaitExecuter(driver);
        insightsOverview.navigateToInsightsTab();
        waitExecuter.waitUntilPageFullyLoaded();
        insightsOverview.validateInsightsTabPresence();
        test.log(LogStatus.PASS, "All the Insights tabs are listed");
        DatePicker datePicker = new DatePicker(driver);
        datePicker.clickOnDatePicker();
        String[] expectedDateOptions = {"Yesterday","Last 7 Completed Days","Last 14 Completed Days","Last 30 Completed Days",
                                     "Custom Range"};

        for (String expectedDateOption : expectedDateOptions) {
            Assert.assertTrue(datePicker.getDatePickerOptions().contains(expectedDateOption),
                    "Date list does not contain: " + expectedDateOption);
            test.log(LogStatus.PASS, "Date list contains option: " + expectedDateOption);
        }
        datePicker.clickOnDatePicker();
        LOGGER.info("Insights Overview Verify all the date pickers.",test);
        test.log(LogStatus.PASS, "Verified all the date pickers on Insights Overview.");
        waitExecuter.sleep(3000);

    }
}

