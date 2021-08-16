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
import com.qa.utils.DateUtils;
import com.qa.utils.MouseActions;
import com.qa.utils.WaitExecuter;
import com.qa.utils.actions.UserActions;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import java.util.List;
import java.util.logging.Logger;

@Marker.DataForecasting
@Marker.All
public class TC_CF_03 extends BaseClass {
    private static final Logger LOGGER = Logger.getLogger(TC_CF_03.class.getName());

    @Test(dataProvider = "clusterid-data-provider")
    public void validateForecastingReportGeneratedForAllHistoryRange(String clusterId) {
        test = extent.startTest("TC_CF_03.validateForecastingReportGeneratedForAllHistoryRange: " + clusterId,
            "Verify User is able to generate forecasting report for different history ranges.");
        test.assignCategory(" Data - Forecasting ");
        LOGGER.info("Passed Parameter Is : " + clusterId);

        //Initialize all require objects
        DatePickerPageObject datePickerPageObject = new DatePickerPageObject(driver);
        DatePicker datePicker = new DatePicker(driver);
        //Initialize all require objects
        Forecasting forecasting = new Forecasting(driver);
        WaitExecuter waitExecuter = new WaitExecuter(driver);
        ForecastingPageObject forecastingPageObject = new ForecastingPageObject(driver);
        forecasting.generateForecastingReportHeaderTab();
        test.log(LogStatus.INFO, "Verified the Data Forecasting header tab");
        forecasting.clickOnRunButton();
        LOGGER.info("Clicked on Run Button");
        test.log(LogStatus.INFO, "Clicked on Run Button");

        List<String> expectedDateRange = forecasting.getAllHistoryRanges();
        forecasting.clickOnCancelButton();
        test.log(LogStatus.INFO, "Clicked on CancelButton, after getting all history date range.");
        String forecastingNoOfDays = "2";

        int expectedDateCount = expectedDateRange.size();

        for (int i = 0; i < expectedDateCount; i++) {
            forecasting.clickOnRunButton();
            LOGGER.info("Clicked on Run Button");
            test.log(LogStatus.INFO, "Clicked on Run Button");
            datePicker.clickOnDatePicker();
            waitExecuter.sleep(3000);
            WebElement datePickerElement = datePickerPageObject.dateRangeOptions.get(i);
            String datePickerOption = datePickerElement.getText();
            if (datePickerElement.getText().equalsIgnoreCase("Custom Range")) {
                waitExecuter.waitUntilPageFullyLoaded();
                datePickerElement.click();
                datePicker.setCurrentAndPastDate(-30);
                waitExecuter.sleep(1000);
                // Click on apply button of Cluster
                datePicker.clickOnCustomDateApplyBtn();
                waitExecuter.sleep(1000);
                waitExecuter.waitUntilPageFullyLoaded();
            }
            else{
                    datePickerElement.click();
                }
            waitExecuter.waitUntilPageFullyLoaded();
            forecasting.setForecastingDays(forecastingNoOfDays);
            LOGGER.info("Set Forecasting days as: " + forecastingNoOfDays
                + " and History date range as :" + datePickerOption);
            test.log(LogStatus.INFO, "Set Forecasting days as: " + forecastingNoOfDays
                + " and History date range as :" + datePickerOption);
            forecasting.clickOnModalRunButton();
            LOGGER.info("Clicked on Modal Run Button");
            test.log(LogStatus.INFO, "Clicked on Modal Run Button");
            try {
                waitExecuter.waitUntilTextNotToBeInWebElement(forecastingPageObject.modalAfterRunButton,
                    "Please Wait");
                waitExecuter.waitUntilTextToBeInWebElement(forecastingPageObject.confirmationMessageElement,
                    "Capacity Forecasting completed successfully.");
                test.log(LogStatus.INFO, "Verified Forecasting report is loaded properly for date: "
                    + datePickerOption);
                LOGGER.info("Verified Forecasting report is loaded properly");
            } catch (TimeoutException te) {
                throw new AssertionError("Forecasting Report not completed successfully for" +
                    " date: " + datePickerOption);
            }
            waitExecuter.waitUntilElementPresent(forecastingPageObject.runButton);
            forecasting.closeConfirmationMessageNotification();
            waitExecuter.sleep(7000);
        }
    }
}
