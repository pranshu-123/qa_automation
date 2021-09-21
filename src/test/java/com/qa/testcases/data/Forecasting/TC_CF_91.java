package com.qa.testcases.data.Forecasting;

import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.pagefactory.SubTopPanelModulePageObject;
import com.qa.pagefactory.TopPanelPageObject;
import com.qa.pagefactory.data.ForecastingPageObject;
import com.qa.scripts.data.Forecasting;
import com.qa.utils.MouseActions;
import com.qa.utils.WaitExecuter;
import com.qa.utils.actions.UserActions;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.TimeoutException;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

@Marker.DataForecasting
@Marker.All
public class TC_CF_91 extends BaseClass {
    private static final Logger LOGGER = Logger.getLogger(TC_CF_91.class.getName());

    @Test(dataProvider = "clusterid-data-provider")
    public void validateForecastingReportGeneratedForMinusOneDays(String clusterId) {
        test = extent.startTest("TC_CF_91.validateForecastingReportGeneratedForMinusOneDays: " + clusterId,
                "Verify User should not allow the user to enter negative values.");
        test.assignCategory(" Data - Forecasting ");
        LOGGER.info("Passed Parameter Is : " + clusterId);

        //Initialize all require objects
        Forecasting forecasting = new Forecasting(driver);
        WaitExecuter waitExecuter = new WaitExecuter(driver);
        ForecastingPageObject forecastingPageObject = new ForecastingPageObject(driver);
        forecasting.generateForecastingReportHeaderTab();
        test.log(LogStatus.INFO, "Verified the Data Forecasting header tab");

        //Get the previous report data generated
        String previousReportData = forecasting.getReportData();
        LOGGER.info("Previous report generated data: " + previousReportData);
        forecasting.clickOnRunButton();
        LOGGER.info("Clicked on Run Button");
        test.log(LogStatus.INFO, "Clicked on Run Button");
        try {
            String forecastingNoOfDays = "-1";
            forecasting.setForecastingDays(forecastingNoOfDays);
            LOGGER.info("Set Forecasting days as: " + forecastingNoOfDays);
            test.log(LogStatus.INFO, "Set Forecasting days as: " + forecastingNoOfDays);
            String scheduleErrorMsg = "please enter valid number.";
            forecasting.verifyScheduleErrorSuccessMsg(scheduleErrorMsg);
            test.log(LogStatus.PASS, "Verified Minimum number of days to predict is 1. Please enter valid number.");
            forecasting.clickOnModalRunButton();
            LOGGER.info("Clicked on Modal Run Button");
            test.log(LogStatus.INFO, "Clicked on Modal Run Button");
            waitExecuter.waitUntilElementPresent(forecastingPageObject.modalCancelButton);
            forecasting.clickOnCancelButton();
            test.log(LogStatus.INFO, "Clicked on CancelButton");

            //Get the previous report data generated
            String reportDataAfterCancelled = forecasting.getReportData();
            LOGGER.info("Report generated data after cancelled: " + reportDataAfterCancelled);
            test.log(LogStatus.INFO, "Report generated data after cancelled: " + reportDataAfterCancelled);

            //Validate both the report data
            Assert.assertEquals(previousReportData, reportDataAfterCancelled);
            test.log(LogStatus.PASS, "Verified Forecasting report after user cancelled.");
            LOGGER.info("Verified Forecasting report after user cancelled.");
        } catch (VerifyError te) {
            throw new AssertionError("Forecasting Report not completed successfully for " +
                    " days: " + te);
        }
    }
}