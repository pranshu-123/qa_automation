package com.qa.scripts.data;

import com.qa.pagefactory.SubTopPanelModulePageObject;
import com.qa.pagefactory.TopPanelPageObject;
import com.qa.pagefactory.data.ForecastingPageObject;
import com.qa.pagefactory.data.SmallfilesPageObject;
import com.qa.pagefactory.jobs.ApplicationsPageObject;
import com.qa.scripts.DatePicker;
import com.qa.scripts.appdetails.SparkAppsDetailsPage;
import com.qa.scripts.appdetails.TezAppsDetailsPage;
import com.qa.scripts.jobs.applications.AllApps;
import com.qa.utils.JavaScriptExecuter;
import com.qa.utils.MouseActions;
import com.qa.utils.WaitExecuter;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.logging.Logger;

public class Smallfiles {

    private static final java.util.logging.Logger LOGGER = java.util.logging.Logger.getLogger(Smallfiles.class.getName());
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


    public void clickonminParent(String FileSize) {
        smallfilesPageObject.maxParentDirectory.clear();
        smallfilesPageObject.onminParentDirectory.sendKeys(FileSize);
    }

    public void clickonmaxParent(String FileSize) {
        smallfilesPageObject.maxParentDirectory.clear();
        smallfilesPageObject.maxParentDirectory.sendKeys(FileSize);
        waitExecuter.sleep(2000);
    }

    /**
     * Common steps to validate minimumFileSize,maximumFileSize,minimumSmallFile,directoriesToShow
     */
    public void navigateToSmallFileReport(SmallfilesPageObject smallfilesPageObject, ExtentTest test, String minimumFileSize, String maximumFileSize,
                                          String minimumSmallFile, String directoriesToShow) {

        smallfilesPageObject.minFileSize.sendKeys(minimumFileSize);
        LOGGER.info("Set minimum FileSize as: " + minimumFileSize);
        test.log(LogStatus.INFO, "Set minimum FileSize as: " + minimumFileSize);


        smallfilesPageObject.maxiFileSize.sendKeys(maximumFileSize);
        LOGGER.info("Set maximum FileSize as: " + maximumFileSize);
        test.log(LogStatus.INFO, "Set minimum FileSize as: " + maximumFileSize);

        smallfilesPageObject.minimumSmallFile.sendKeys(minimumSmallFile);
        LOGGER.info("Set minimum SmallFile as: " + minimumSmallFile);
        test.log(LogStatus.INFO, "Set minimum FileSize as: " + minimumSmallFile);


        smallfilesPageObject.directoriestoShow.sendKeys(directoriesToShow);
        LOGGER.info("Set minimum SmallFile as: " + directoriesToShow);
        test.log(LogStatus.INFO, "Set minimum FileSize as: " + directoriesToShow);
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

    /***
     * verify common Panel data and smallFilesTab
     * */
    public void commonPanelTabValidation(ExtentTest test, Logger logger) {

        WaitExecuter waitExecuter = new WaitExecuter(driver);
        SubTopPanelModulePageObject topPanelComponentPageObject = new SubTopPanelModulePageObject(driver);
        waitExecuter.waitUntilElementPresent(topPanelComponentPageObject.data);
        waitExecuter.sleep(2000);
        MouseActions.clickOnElement(driver, topPanelComponentPageObject.data);
        logger.info("Clicked on Data Tab");
        test.log(LogStatus.INFO, "Clicked on Data Tab");

        TopPanelPageObject topPanelPageObject = new TopPanelPageObject(driver);
        waitExecuter.waitUntilElementPresent(topPanelPageObject.smallFilesTab);
        waitExecuter.waitUntilPageFullyLoaded();
        waitExecuter.waitUntilElementClickable(topPanelPageObject.smallFilesTab);
        waitExecuter.sleep(3000);
        MouseActions.clickOnElement(driver, topPanelPageObject.smallFilesTab);
        logger.info("Clicked on small FilesTab Tab");
        test.log(LogStatus.INFO, "Clicked on small FilesTab Tab");

    }
}