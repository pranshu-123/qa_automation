package com.qa.testcases.data.Forecasting;

import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.enums.UserAction;
import com.qa.pagefactory.DatePickerPageObject;
import com.qa.pagefactory.SubTopPanelModulePageObject;
import com.qa.pagefactory.TopPanelPageObject;
import com.qa.pagefactory.data.ForecastingPageObject;
import com.qa.scripts.DatePicker;
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
public class TC_CF_16 extends BaseClass {
    private static final Logger LOGGER = Logger.getLogger(TC_CF_16.class.getName());

    @Test(dataProvider = "clusterid-data-provider")
    public void validateShouldCanceledForecastingReport(String clusterId) {
        test = extent.startTest("TC_CF_16.validateShouldCanceledForecastingReport: " + clusterId,
                "Verify User is able to clicks on Cancel button and the Mini Window should close.");
        test.assignCategory(" Data - Forecasting ");
        LOGGER.info("Passed Parameter Is : " + clusterId);

        //Initialize all require objects
        Forecasting forecasting = new Forecasting(driver);
        ForecastingPageObject forecastingPageObject = new ForecastingPageObject(driver);
        WaitExecuter waitExecuter = new WaitExecuter(driver);
        forecasting.generateForecastingReportHeaderTab();
        test.log(LogStatus.INFO, "Verified the Data Forecasting header tab");

        //Get the previous report data generated
        String previousReportData = forecasting.getReportData();
        LOGGER.info("Previous report generated data: " + previousReportData);
        forecasting.clickOnScheduleButton();
        LOGGER.info("Clicked on Schedule Button");
        try {
            String forecastingNoOfDays = "2";
            forecasting.setForecastingDays(forecastingNoOfDays);
            LOGGER.info("Set Forecasting days as: " + forecastingNoOfDays);
            test.log(LogStatus.INFO, "Set Forecasting days as: " + forecastingNoOfDays);
            String scheduleName = "Forecasting_Test1";
            List<String> email = Arrays.asList("test@unravel.com","test1@unravel.com","test2@unravel.com");
            // Schedule with e-mails
            test.log(LogStatus.INFO, "Schedule with e-mails");
            LOGGER.info("Schedule with e-mails");
            forecasting.scheduleWithEmail(scheduleName, email);
            // Define day of the week and time
            test.log(LogStatus.INFO, "Define day of the week as- Thursday and time as- 17:30");
            LOGGER.info("Define day of the week as- Thursday and time as- 17:30");
            forecasting.selectDayTime("Daily", "10", "30");
            waitExecuter.waitUntilPageFullyLoaded();
            waitExecuter.waitUntilElementPresent(forecastingPageObject.modalCancelButton);
            forecasting.clickOnCancelButton();
            test.log(LogStatus.INFO, "Clicked on CancelButton");

            //Get the previous report data generated
            String reportDataAfterCancelled = forecasting.getReportData();
            LOGGER.info("Report generated data after cancelled: " + reportDataAfterCancelled);
            test.log(LogStatus.INFO, "Report generated data after cancelled: " + reportDataAfterCancelled);

            //Validate both the report data
            Assert.assertEquals(previousReportData, reportDataAfterCancelled);
            test.log(LogStatus.PASS, "Verified Schedule Forecasting report after user cancelled.");
            LOGGER.info("Verified Schedule Forecasting report after user cancelled.");
        } catch (VerifyError te) {
            throw new AssertionError("Forecasting schedule Report not completed successfully for " +
                    " days: "+te);
        }
    }
}
