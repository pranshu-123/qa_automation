package com.qa.testcases.data.Forecasting;

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

public class TC_CF_13 extends BaseClass {
    private static final Logger LOGGER = Logger.getLogger(TC_CF_13.class.getName());

    @Test(dataProvider = "clusterid-data-provider")
    public void validateForecastingReportGeneratedForOnceInTwoWeeks(String clusterId) {
        test = extent.startTest("TC_CF_13.validateForecastingReportGeneratedForOnceInTwoWeeks: " + clusterId,
                "Verify User is able to generate forecasting report for Once in two weeks.");
        test.assignCategory(" Data - Forecasting ");
        LOGGER.info("Passed Parameter Is : " + clusterId);

        //Initialize all require objects
        WaitExecuter waitExecuter = new WaitExecuter(driver);
        SubTopPanelModulePageObject topPanelComponentPageObject = new SubTopPanelModulePageObject(driver);
        waitExecuter.waitUntilElementPresent(topPanelComponentPageObject.data);
        waitExecuter.sleep(2000);
        MouseActions.clickOnElement(driver, topPanelComponentPageObject.data);
        LOGGER.info("Clicked on Data Tab");

        TopPanelPageObject topPanelPageObject = new TopPanelPageObject(driver);
        waitExecuter.waitUntilElementPresent(topPanelPageObject.dataForecastingTab);
        waitExecuter.waitUntilPageFullyLoaded();
        waitExecuter.waitUntilElementClickable(topPanelPageObject.dataForecastingTab);
        waitExecuter.sleep(3000);
        MouseActions.clickOnElement(driver, topPanelPageObject.dataForecastingTab);
        LOGGER.info("Clicked on Forecasting Tab");
        test.log(LogStatus.INFO, "Clicked on Forecasting Tab");

        ForecastingPageObject forecastingPageObject = new ForecastingPageObject(driver);

        Forecasting forecasting = new Forecasting(driver);
        forecasting.closeConfirmationMessageNotification();

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
            String scheduleName = "Forecasting_Test3";
            List<String> email = Arrays.asList("test@unravel.com,test1@unravel.com,test2@unravel.com");
            // Schedule with e-mails
            test.log(LogStatus.INFO, "Schedule with e-mails");
            LOGGER.info("Schedule with e-mails");
            forecasting.scheduleWithEmail(scheduleName, email);
            // Define day of the week and time
            test.log(LogStatus.INFO, "Define day of the week as- Thursday and time as- 17:30");
            LOGGER.info("Define day of the week as- Thursday and time as- 17:30");
            forecasting.selectDayTime("Every two weeks", "22", "00");
            waitExecuter.waitUntilPageFullyLoaded();
            forecasting.clickOnModalScheduleButton();
            LOGGER.info("Clicked on modal Schedule Button");
            test.log(LogStatus.INFO, "Clicked on modal Schedule Button");
            String scheduleSuccessMsg = "the report has been scheduled successfully.";
            forecasting.verifyScheduleSuccessMsg(scheduleSuccessMsg);
            test.log(LogStatus.PASS, "Verified schedule with multi email for daily.");
        } catch (VerifyError te) {
            throw new AssertionError("Forecasting schedule Report not completed successfully for " +
                    " days: "+te);
        }
    }
}