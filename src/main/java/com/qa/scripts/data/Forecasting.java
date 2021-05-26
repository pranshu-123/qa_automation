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
            MouseActions.clickOnElement(driver, forecastingPageObject.modalCancelButton);
        }catch (TimeoutException te) {
            MouseActions.clickOnElement(driver, forecastingPageObject.modalCancelButton);
        }
    }

    public String getReportData(){
        if(forecastingPageObject.previousReportData.isDisplayed()){
            return forecastingPageObject.previousReportData.getText();
        }
        return null;
    }

    public void clickOnHistoryDateRange(){
        MouseActions.clickOnElement(driver, forecastingPageObject.historyDateRangeDropDown);
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
