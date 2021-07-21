package com.qa.scripts.data;

import com.qa.enums.UserAction;
import com.qa.pagefactory.data.ForecastingPageObject;
import com.qa.utils.JavaScriptExecuter;
import com.qa.utils.MouseActions;
import com.qa.utils.WaitExecuter;
import com.qa.utils.actions.UserActions;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class Forecasting {

    private WaitExecuter waitExecuter;
    private WebDriver driver;
    ForecastingPageObject forecastingPageObject;
    private UserActions actions;
    private static final Logger LOGGER = Logger.getLogger(Forecasting.class.getName());

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

    public void setForecastingDays(String numOfDays){
        actions.performActionWithPolling(forecastingPageObject.numOfDaysForForecasting,
            UserAction.SEND_KEYS, numOfDays);
    }

    public void clickOnCancelButton(){
        try{
            actions.performActionWithPolling(forecastingPageObject.modalCancelButton, UserAction.CLICK);

        }catch (TimeoutException te) {
            actions.performActionWithPolling(forecastingPageObject.modalCancelButton, UserAction.CLICK);
        }
    }

    public String getReportData(){
        if(forecastingPageObject.previousReportData.isDisplayed()){
            return forecastingPageObject.previousReportData.getText();
        }
        return null;
    }

    public void clickOnHistoryDateRange(){
        actions.performActionWithPolling(forecastingPageObject.historyDateRangeDropDown, UserAction.CLICK);
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

    public List<String> getAllHistoryRanges(){

        clickOnHistoryDateRange();
        int dateRangeCount = forecastingPageObject.listDateRange.size();
        if(dateRangeCount > 0){
            List<String> dateRange = new ArrayList<String>();
            for(WebElement e : forecastingPageObject.listDateRange){
                dateRange.add(e.getText());
            }
            LOGGER.info("All History Date ranges from UI are: "+dateRange);
            return dateRange;
        }
        return null;
    }


}
