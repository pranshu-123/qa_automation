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
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import java.util.logging.Logger;

@Marker.DataForecasting
@Marker.All
public class TC_CF_04 extends BaseClass {
    private static final Logger LOGGER = Logger.getLogger(TC_CF_04.class.getName());

    @Test(dataProvider = "clusterid-data-provider")
    public void validateForecastingReportGeneratedForDiffForecastingDays(String clusterId) {
        test = extent.startTest("TC_CF_04.validateForecastingReportGeneratedForDiffForecastingDays: " + clusterId,
            "Verify User is able to generate forecasting report for different forecasting days.");
        test.assignCategory(" Data - Forecasting ");
        LOGGER.info("Passed Parameter Is : " + clusterId);

        //Initialize all require objects
        Forecasting forecasting = new Forecasting(driver);
        WaitExecuter waitExecuter = new WaitExecuter(driver);
        UserActions userActions = new UserActions(driver);
        DatePicker datePicker = new DatePicker(driver);
        ForecastingPageObject forecastingPageObject = new ForecastingPageObject(driver);
        forecasting.generateForecastingReportHeaderTab();
        test.log(LogStatus.INFO, "Verified the Data Forecasting header tab");

        String[] daysOfForecasting = {"1", "179"};
        for (String days : daysOfForecasting) {
            forecasting.closeConfirmationMessageNotification();
            forecasting.clickOnRunButton();
            LOGGER.info("Clicked on Run Button");
            test.log(LogStatus.INFO, "Clicked on Run Button");

            datePicker.clickOnDatePicker();
            datePicker.selectLast90Days();
            forecasting.setForecastingDays(days);
            LOGGER.info("Set Forecasting days as: " + days);
            test.log(LogStatus.INFO, "Set Forecasting days as: " + days);
            forecasting.clickOnModalRunButton();
            LOGGER.info("Clicked on Modal Run Button");
            test.log(LogStatus.INFO, "Clicked on Modal Run Button");
            waitExecuter.waitUntilElementPresent(forecastingPageObject.runButton);
            userActions.performActionWithPolling(forecastingPageObject.runButton, UserAction.CLICK);
            try {
                waitExecuter.waitUntilTextToBeInWebElement(forecastingPageObject.confirmationMessageElement,
                    "Capacity Forecasting completed successfully.");
                test.log(LogStatus.INFO, "Verified Forecasting report is loaded properly for days : "
                    + days);
                LOGGER.info("Verified Forecasting report is loaded properly");
                break;
            } catch (TimeoutException te) {
                throw new AssertionError("Forecasting Report not completed successfully for " +
                    " days: " + days);
            }
        }
    }
}
