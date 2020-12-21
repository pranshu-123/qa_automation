package com.qa.scripts.data;

import com.qa.constants.PageConstants;
import com.qa.enums.UserAction;
import com.qa.pagefactory.CommonPageObject;
import com.qa.pagefactory.SubTopPanelModulePageObject;
import com.qa.pagefactory.TopPanelPageObject;
import com.qa.pagefactory.data.SmallfilesPageObject;
import com.qa.pagefactory.reports.ReportsArchiveScheduledPageObject;
import com.qa.utils.JavaScriptExecuter;
import com.qa.utils.MouseActions;
import com.qa.utils.WaitExecuter;
import com.qa.utils.actions.UserActions;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.Keys;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

public class Smallfiles {

    private static final java.util.logging.Logger LOGGER = java.util.logging.Logger.getLogger(Smallfiles.class.getName());
    SmallfilesPageObject smallfilesPageObject;
    private WaitExecuter waitExecuter;
    private WebDriver driver;
    private UserActions actions;

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

    /**
     * Method to click on 'SheduleButton'
     */
    public void clickOnScheduleButton() {
        try {
            MouseActions.clickOnElement(driver, smallfilesPageObject.SheduleButton);
        } catch (TimeoutException te) {
            MouseActions.clickOnElement(driver, smallfilesPageObject.SheduleButton);
        }
    }


    public List<String> getClusterOptions(SmallfilesPageObject smallfilesPageObject) {
        List<String> list = new ArrayList<>();
        for (WebElement element : smallfilesPageObject.clustersList) {
            list.add(element.getText());
        }
        return list;
    }

    public void clickOnModalRunButton() {
        MouseActions.clickOnElement(driver, smallfilesPageObject.modalRunButton);
    }

    public void clickOnModalScheduleButton() {
        MouseActions.clickOnElement(driver,
                smallfilesPageObject.runSheduleButton);

    }

    public static class TuningScheduleRun {
        public static String[] SCHEDULE_RUN = {"Daily","Sunday", "Monday", "Tuesday", "Wednesday", "Thursday",
                "Friday","Saturday","Every 2 Weeks","Every Month"};
        public static String[] SCHEDULE_CLUSTERID = {"tnode28-HDP315-TLS-Kerb-Ranger", "tnode3-CDH633-TLS-Kerb-Sentry",
                "tnode40-CDH5162"};
    }

    /**
     * Method to validate the Small File report Page
     */
    public void validateReportPage(SmallfilesPageObject smallfilesPageObject) {
        List<WebElement> runPathList = smallfilesPageObject.pathName;
        Assert.assertFalse(runPathList.isEmpty(), "There are no schedule report path");
        List<WebElement> runFileList = smallfilesPageObject.fileName;
        Assert.assertFalse(runFileList.isEmpty(), "There are no schedule report file");
        List<WebElement> runAvgFileSizeList = smallfilesPageObject.avgFileSize;
        Assert.assertFalse(runAvgFileSizeList.isEmpty(), "There are no reports scheduled report Avg File Size");
        List<WebElement> runTotalFileSize = smallfilesPageObject.totalFileSize;
        Assert.assertFalse(runTotalFileSize.isEmpty(), "There are no schedule scheduled report Total File Size");
        List<WebElement> runMinFileSize = smallfilesPageObject.minFileSizeName;
        Assert.assertFalse(runMinFileSize.isEmpty(), "There are no schedule scheduled report Min File Size");
        List<WebElement> runMaxFileSize = smallfilesPageObject.maxFileSize;
        Assert.assertFalse(runMaxFileSize.isEmpty(), "There are no schedule scheduled report Max File Size");
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
        test.log(LogStatus.INFO, "Set maximum FileSize as: " + maximumFileSize);

        smallfilesPageObject.minimumSmallFile.sendKeys(minimumSmallFile);
        LOGGER.info("Set minimum SmallFile as: " + minimumSmallFile);
        test.log(LogStatus.INFO, "Set minimum SmallFile as: " + minimumSmallFile);


        smallfilesPageObject.directoriestoShow.sendKeys(directoriesToShow);
        LOGGER.info("Set directories To Show as: " + directoriesToShow);
        test.log(LogStatus.INFO, "Set directories To Show as: " + directoriesToShow);
    }


    /**
     * Common steps to validate minimumFileSize,maximumFileSize,minimumSmallFile,directoriesToShow
     */
    public void navigateToAdvancedOptions(SmallfilesPageObject smallfilesPageObject, ExtentTest test, String onminParentDirectory, String maxParentDirectory) {
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.pollingEvery(Duration.ofMillis(10));
        smallfilesPageObject.onminParentDirectory.sendKeys(Keys.CONTROL + "a");
        smallfilesPageObject.onminParentDirectory.sendKeys(Keys.DELETE);
        smallfilesPageObject.onminParentDirectory.sendKeys(onminParentDirectory);
        LOGGER.info("Set Min Parent Directory Depth as: " + onminParentDirectory);
        test.log(LogStatus.INFO, "Set Min Parent Directory Depth as: " + onminParentDirectory);

        smallfilesPageObject.maxParentDirectory.sendKeys(Keys.CONTROL + "a");
        smallfilesPageObject.maxParentDirectory.sendKeys(Keys.DELETE);
        smallfilesPageObject.maxParentDirectory.sendKeys(maxParentDirectory);
        LOGGER.info("Set Max Parent Directory Depth as: " + maxParentDirectory);
        test.log(LogStatus.INFO, "Set Max Parent Directory Depth as: " + maxParentDirectory);

    }

    /**
     * Common steps to validate schedule name,daily,email
     */
    public void scheduleAdvancedOptions(SmallfilesPageObject smallfilesPageObject, ExtentTest test, String scheduleName, String email) {

        smallfilesPageObject.scheduleNameTextbox.sendKeys(scheduleName);
        LOGGER.info("Set directories To Show as: " + scheduleName);
        test.log(LogStatus.INFO, "Set directories To Show as: " + scheduleName);

        smallfilesPageObject.emailNotification.sendKeys(email);
        LOGGER.info("Set directories To Show as: " + email);
        test.log(LogStatus.INFO, "Set directories To Show as: " + email);
    }

    /**
     * Method to validate the sorting option on Next Scheduled Run for Scheduled Page
     */
    public void verifyScheduleToRun(){
        List<String> expectedSchedule = new ArrayList<>(Arrays.asList(TuningScheduleRun.SCHEDULE_RUN));
        waitExecuter.waitUntilElementPresent(smallfilesPageObject.scheduleToRun);
        Select scheduleTorunDropDown = new Select(smallfilesPageObject.scheduleToRun);
        List <WebElement> elementScheduleTorunDropDown = scheduleTorunDropDown.getOptions();
        if(elementScheduleTorunDropDown.size() > 0 ){
            for(int i=0; i<elementScheduleTorunDropDown.size(); i++){
                WebElement e = elementScheduleTorunDropDown.get(i);
                e.click();
                LOGGER.info("Schedule date: "+ e.getText());
                Assert.assertEquals(e.getText().trim(), expectedSchedule.get(i), "Mismatch in schedule run date.");
            }
        }
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