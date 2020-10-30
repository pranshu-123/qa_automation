package com.qa.scripts.data;

import com.qa.pagefactory.data.ForecastingPageObject;
import com.qa.utils.JavaScriptExecuter;
import com.qa.utils.MouseActions;
import com.qa.utils.WaitExecuter;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;

public class Forecasting {

    private WaitExecuter waitExecuter;
    private WebDriver driver;
    ForecastingPageObject forecastingPageObject;

    /**
     * Constructor to initialize wait, driver and necessary objects
     *
     * @param driver - WebDriver instance
     */
    public Forecasting(WebDriver driver) {
        waitExecuter = new WaitExecuter(driver);
        this.driver = driver;
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
        MouseActions.clickOnElement(driver, forecastingPageObject.modalRunButton);
    }

    public void setForecastingDays(String numOfDays){
        forecastingPageObject.numOfDaysForForecasting.sendKeys(numOfDays);
    }

}
