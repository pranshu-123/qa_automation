package com.qa.testcases.data.Forecasting;

import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.scripts.data.Forecasting;
import com.qa.utils.WaitExecuter;
import com.relevantcodes.extentreports.LogStatus;
import org.testng.annotations.Test;

import java.util.logging.Logger;

@Marker.DataForecasting
@Marker.All
public class TC_CF_15 extends BaseClass {
    private static final Logger LOGGER = Logger.getLogger(TC_CF_15.class.getName());

    @Test(dataProvider = "clusterid-data-provider")
    public void validateForecastingReportGeneratedForSpecialCharaectors(String clusterId) {
        test = extent.startTest("TC_CF_15.validateForecastingReportGeneratedForDiffForecastingDays: " + clusterId,
                "Verify User is able to generate forecasting report for special characters.");
        test.assignCategory(" Data - Forecasting ");
        LOGGER.info("Passed Parameter Is : " + clusterId);

        //Initialize all require objects
        Forecasting forecasting = new Forecasting(driver);
        WaitExecuter waitExecuter = new WaitExecuter(driver);
        forecasting.generateForecastingReportHeaderTab();
        test.log(LogStatus.INFO, "Verified the Data Forecasting header tab");

        //Get the previous report data generated
        String previousReportData = forecasting.getReportData();
        LOGGER.info("Previous report generated data: " + previousReportData);
        forecasting.clickOnScheduleButton();
        LOGGER.info("Clicked on Schedule Button");
        forecasting.generateForecastingReport(forecasting, test);
    }
}
