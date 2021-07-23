package com.qa.scripts.data;

import com.qa.enums.UserAction;
import com.qa.pagefactory.SubTopPanelModulePageObject;
import com.qa.pagefactory.TopPanelPageObject;
import com.qa.pagefactory.data.ForecastingPageObject;
import com.qa.utils.JavaScriptExecuter;
import com.qa.utils.MouseActions;
import com.qa.utils.WaitExecuter;
import com.qa.utils.actions.UserActions;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

public class Forecasting {

    private static final Logger LOGGER = Logger.getLogger(Forecasting.class.getName());
    ForecastingPageObject forecastingPageObject;
    private final WaitExecuter waitExecuter;
    private final WebDriver driver;
    private final UserActions actions;

    /**
     * Constructor to initialize wait, driver and necessary objects
     *
     * @param driver - WebDriver instance
     */
    public Forecasting(WebDriver driver) {
        waitExecuter = new WaitExecuter(driver);
        this.driver = driver;
        this.actions = new UserActions(driver);
        forecastingPageObject = new ForecastingPageObject(driver);

    }

    public void closeConfirmationMessageNotification() {
        if (forecastingPageObject.confirmationMessageElementClose.size() > 0) {
            waitExecuter.waitUntilElementClickable(forecastingPageObject.confirmationMessageElementClose.get(0));
            JavaScriptExecuter.clickOnElement(driver, forecastingPageObject.confirmationMessageElementClose.get(0));
        }
    }

    public void generateForecastingReportHeaderTab() {
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

        Forecasting forecasting = new Forecasting(driver);
        forecasting.closeConfirmationMessageNotification();
    }

    public void clickOnRunButton() {
        try {
            MouseActions.clickOnElement(driver, forecastingPageObject.runButton);
        } catch (TimeoutException te) {
            MouseActions.clickOnElement(driver, forecastingPageObject.runNowButton);
        }
    }

    public void clickOnScheduleButton() {
        try {
            MouseActions.clickOnElement(driver, forecastingPageObject.scheduleButton);
        } catch (TimeoutException te) {
            MouseActions.clickOnElement(driver, forecastingPageObject.scheduleButton);
        }
    }

    public void clickOnModalScheduleButton() {
        waitExecuter.waitUntilElementPresent(forecastingPageObject.modalScheduleButton);
        actions.performActionWithPolling(forecastingPageObject.modalScheduleButton, UserAction.CLICK);
    }


    public void clickOnModalRunButton() {
        waitExecuter.waitUntilElementPresent(forecastingPageObject.modalRunButton);
        actions.performActionWithPolling(forecastingPageObject.modalRunButton, UserAction.CLICK);
    }

    public void setForecastingDays(String numOfDays) {
        actions.performActionWithPolling(forecastingPageObject.numOfDaysForForecasting,
                UserAction.SEND_KEYS, numOfDays);
    }

    public void clickOnCancelButton() {
        try {
            actions.performActionWithPolling(forecastingPageObject.modalCancelButton, UserAction.CLICK);

        } catch (TimeoutException te) {
            actions.performActionWithPolling(forecastingPageObject.modalCancelButton, UserAction.CLICK);
        }
    }

    public String getReportData() {
        if (forecastingPageObject.previousReportData.isDisplayed()) {
            return forecastingPageObject.previousReportData.getText();
        }
        return null;
    }

    public void clickOnHistoryDateRange() {
        actions.performActionWithPolling(forecastingPageObject.historyDateRangeDropDown, UserAction.CLICK);
    }

    /* Validate success message on report creation */
    public void verifyScheduleErrorSuccessMsg(String errorMsg) {
        waitExecuter.waitUntilElementPresent(forecastingPageObject.ForecastingErrorMessageElement);
        Assert.assertEquals(forecastingPageObject.ForecastingErrorMessageElement.getText().toLowerCase(), errorMsg,
                "The Schedule success " + "message mismatch");
    }

    /* Click on schedule button and assign  e-mails */
    public void scheduleWithEmail(String name, List<String> multiEmail) {
        waitExecuter.waitUntilElementPresent(forecastingPageObject.scheduleName);
        actions.performActionWithPolling(forecastingPageObject.scheduleName, UserAction.SEND_KEYS, name);
        for (String email : multiEmail) {
            actions.performActionWithPolling(forecastingPageObject.email, UserAction.SEND_KEYS, email);
            waitExecuter.waitUntilElementPresent(forecastingPageObject.addEmail);
            MouseActions.clickOnElement(driver, forecastingPageObject.addEmail);
        }
    }

    public void generateForecastingReport(Forecasting forecasting, ExtentTest test) {
        try {
            String forecastingNoOfDays = "2";
            forecasting.setForecastingDays(forecastingNoOfDays);
            LOGGER.info("Set Forecasting days as: " + forecastingNoOfDays);
            test.log(LogStatus.INFO, "Set Forecasting days as: " + forecastingNoOfDays);
            String scheduleName = "Queue_An_Test2";
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

    /* Define day and Time to select from drop-down */
    public void selectDayTime(String day, String hour, String min) {
        selectByDays(day);
        forecastingPageObject.displayTime.click();
        selectByHour(hour);
        selectByMin(min);
    }

    /* Select day from drop-down */
    public void selectByDays(String dayToRun) {
        waitExecuter.waitUntilElementClickable(forecastingPageObject.scheduleDays);
        Select scheduleTorunDropDown = new Select(forecastingPageObject.scheduleDays);
        scheduleTorunDropDown.selectByVisibleText(dayToRun);

    }

    /* Select hour from drop-down */
    public void selectByHour(String hour) {
        waitExecuter.waitUntilElementClickable(forecastingPageObject.hoursDropdown);
        Select scheduleTorunDropDown = new Select(forecastingPageObject.hoursDropdown);
        scheduleTorunDropDown.selectByVisibleText(hour);

    }

    /* Select minute from drop-down */
    public void selectByMin(String min) {
        waitExecuter.waitUntilElementClickable(forecastingPageObject.minutesDropdown);
        Select scheduleTorunDropDown = new Select(forecastingPageObject.minutesDropdown);
        scheduleTorunDropDown.selectByVisibleText(min);

    }

    /* Validate success message on report creation */
    public void verifyScheduleSuccessMsg(String successMsg) {
        waitExecuter.waitUntilElementPresent(forecastingPageObject.scheduleSuccessMsg);
        Assert.assertEquals(forecastingPageObject.scheduleSuccessMsg.getText().toLowerCase(), successMsg,
                "The Schedule success " + "message mismatch");
    }

    public List<String> getAllHistoryRanges() {

        clickOnHistoryDateRange();
        int dateRangeCount = forecastingPageObject.listDateRange.size();
        if (dateRangeCount > 0) {
            List<String> dateRange = new ArrayList<String>();
            for (WebElement e : forecastingPageObject.listDateRange) {
                dateRange.add(e.getText());
            }
            LOGGER.info("All History Date ranges from UI are: " + dateRange);
            return dateRange;
        }
        return null;
    }


}
