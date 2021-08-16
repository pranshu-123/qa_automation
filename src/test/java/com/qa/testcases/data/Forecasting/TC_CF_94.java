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

import static org.testng.Assert.assertEquals;

@Marker.DataForecasting
@Marker.All
public class TC_CF_94 extends BaseClass {
    private static final Logger LOGGER = Logger.getLogger(TC_CF_04.class.getName());

    @Test(dataProvider = "clusterid-data-provider")
    public void validateForecastingReportGeneratedForInvalidEmailAddress(String clusterId) {
        test = extent.startTest("TC_CF_94.validateForecastingReportGeneratedForInvalidEmailAddress: " + clusterId,
                "Verify user fails to enter a invalid email address in the schedule report page.");
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
        forecasting.clickOnScheduleButton();
        LOGGER.info("Clicked on Schedule Button");
        test.log(LogStatus.INFO, "Clicked on Schedule Button");
        try {
            String forecastingNoOfDays = "2";
            forecasting.setForecastingDays(forecastingNoOfDays);
            LOGGER.info("Set Forecasting days as: " + forecastingNoOfDays);
            test.log(LogStatus.INFO, "Set Forecasting days as: " + forecastingNoOfDays);
            String scheduleName = "Queue_An_Test1";

            test.log(LogStatus.INFO, "Schedule Notification text box marked in red");
            LOGGER.info("Schedule Notification text box marked in red");
            // Define day of the week and time
            test.log(LogStatus.INFO, "Define day of the week as- Thursday and time as- 17:30");
            LOGGER.info("Define day of the week as- Thursday and time as- 17:30");
            forecasting.selectDayTime("Daily", "10", "30");
            waitExecuter.waitUntilPageFullyLoaded();
            List<String> email = Arrays.asList("test");
            // Schedule with e-mails
            test.log(LogStatus.INFO, "Schedule with wrong e-mails");
            LOGGER.info("Schedule with wrong e-mails");
            forecasting.scheduleWithEmail(scheduleName, email);
            waitExecuter.sleep(2000);
            String colorString = forecastingPageObject.verifyColorCode.getAttribute("class");
            String[] arrColor = colorString.split("");
            assertEquals(arrColor[1].toLowerCase(), "a", "Color code for email do not match ");
            waitExecuter.waitUntilPageFullyLoaded();
            forecasting.clickOnModalScheduleButton();
            waitExecuter.waitUntilPageFullyLoaded();
            LOGGER.info("Clicked on modal Schedule Button");
            test.log(LogStatus.INFO, "Clicked on modal Schedule Button");
            String scheduleSuccessMsg = "please, make sure valid inputs.";
            forecasting.verifyScheduleErrorMsg(scheduleSuccessMsg);
            waitExecuter.waitUntilElementPresent(forecastingPageObject.closeButton);
            MouseActions.clickOnElement(driver, forecastingPageObject.closeButton);
            waitExecuter.sleep(1000);
            test.log(LogStatus.PASS, "Verified schedule with multi email for daily.");
        } catch (VerifyError te) {
            throw new AssertionError("Forecasting schedule  not completed successfully for " +
                    " days: "+te);
        }
    }
}