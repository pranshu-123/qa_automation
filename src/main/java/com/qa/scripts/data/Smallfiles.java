package com.qa.scripts.data;

import com.qa.pagefactory.data.ForecastingPageObject;
import com.qa.pagefactory.data.SmallfilesPageObject;
import com.qa.utils.JavaScriptExecuter;
import com.qa.utils.MouseActions;
import com.qa.utils.WaitExecuter;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class Smallfiles {

    SmallfilesPageObject smallfilesPageObject;
    private WaitExecuter waitExecuter;
    private WebDriver driver;

    /**
     * Constructor to initialize wait, driver and necessary objects
     *
     * @param driver - WebDriver instance
     */
    public Smallfiles(WebDriver driver) {
        waitExecuter = new WaitExecuter(driver);
        this.driver = driver;
        smallfilesPageObject = new SmallfilesPageObject(driver);
    }

    /**
     * Verify Confirmation Message Notification
     */
    public void closeConfirmationMessageNotification() {
        if (smallfilesPageObject.confirmationMessageElementClose.size() > 0) {
            waitExecuter.waitUntilElementClickable(smallfilesPageObject.confirmationMessageElementClose.get(0));
            JavaScriptExecuter.clickOnElement(driver, smallfilesPageObject.confirmationMessageElementClose.get(0));
        }
    }

    public List<WebElement> getClustersList() {
        return smallfilesPageObject.clusterList;
    }

    /**
     * Method to click on 'RunButton'
     */
    public void clickOnRunButton() {
        try {
            MouseActions.clickOnElement(driver, smallfilesPageObject.runButton);
        } catch (TimeoutException te) {
            MouseActions.clickOnElement(driver, smallfilesPageObject.runNowButton);
        }
    }

    public void clickOnModalRunButton() {
        MouseActions.clickOnElement(driver, smallfilesPageObject.modalRunButton);
    }

    public void minimumFileSize(String FileSize) {
        smallfilesPageObject.minFileSize.sendKeys(FileSize);
    }

    public void maximumFileSize(String FileSize) {
        smallfilesPageObject.maxiFileSize.sendKeys(FileSize);
    }

    public void minimumSmallFile(String FileSize) {
        smallfilesPageObject.minimumSmallFile.sendKeys(FileSize);
    }

    public void clickonminParent(String FileSize) {
        smallfilesPageObject.onminParentDirectory.sendKeys(FileSize);
    }

    public void clickonmaxParent(String FileSize) {
        smallfilesPageObject.maxParentDirectory.sendKeys(FileSize);
    }

    public void directoriesToShow(String FileSize) {
        smallfilesPageObject.directoriestoShow.sendKeys(FileSize);
    }

    /**
     * Method to click on 'advancedOptions'
     */
    public void clickOnadvancedOptions() {
        try {
            MouseActions.clickOnElement(driver, smallfilesPageObject.advancedOptions);
        } catch (TimeoutException te) {
            MouseActions.clickOnElement(driver, smallfilesPageObject.advancedOptions);
        }
    }

}
