package com.qa.scripts.clusters;

import com.qa.pagefactory.clusters.TopXPageObject;
import com.qa.pagefactory.clusters.TuningPageObject;
import com.qa.utils.JavaScriptExecuter;
import com.qa.utils.MouseActions;
import com.qa.utils.WaitExecuter;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;

public class Tuning {
    private WebDriver driver;
    private WaitExecuter waitExecuter;
    private TuningPageObject tuningPageObject;

    public Tuning(WebDriver driver) {
        this.driver = driver;
        waitExecuter = new WaitExecuter(driver);
        tuningPageObject = new TuningPageObject(driver);
    }

    public void closeConfirmationMessageNotification() {
        if (tuningPageObject.confirmationMessageElementClose.size() > 0) {
            waitExecuter.waitUntilElementClickable(tuningPageObject.confirmationMessageElementClose.get(0));
            JavaScriptExecuter.clickOnElement(driver, tuningPageObject.confirmationMessageElementClose.get(0));
        }
    }

    public void clickOnRunButton() {
        try {
            MouseActions.clickOnElement(driver, tuningPageObject.runButton);
        } catch (TimeoutException te) {
            MouseActions.clickOnElement(driver, tuningPageObject.runButton);
        }
    }

    public void clickOnModalRunButton() {
        MouseActions.clickOnElement(driver, tuningPageObject.modalRunButton);
    }

}