package com.qa.scripts.data;

import com.qa.enums.UserAction;
import com.qa.pagefactory.SubTopPanelModulePageObject;
import com.qa.pagefactory.TopPanelPageObject;
import com.qa.pagefactory.data.SmallfilesPageObject;
import com.qa.scripts.migration.ServicesAndVersionsCompatibility;
import com.qa.utils.JavaScriptExecuter;
import com.qa.utils.MouseActions;
import com.qa.utils.WaitExecuter;
import com.qa.utils.actions.UserActions;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;

public class Smallfiles {

    private static final java.util.logging.Logger LOGGER = java.util.logging.Logger.getLogger(Smallfiles.class.getName());
    private final UserActions userAction;
    Logger logger = Logger.getLogger(ServicesAndVersionsCompatibility.class.getName());
    SmallfilesPageObject smallfilesPageObject;
    private final WaitExecuter waitExecuter;
    private final WebDriver driver;
    UserActions userActions;

    /**
     * Constructor to initialize wait, driver and necessary objects
     *
     * @param driver - WebDriver instance
     */
    public Smallfiles(WebDriver driver) {
        waitExecuter = new WaitExecuter(driver);
        this.driver = driver;
        smallfilesPageObject = new SmallfilesPageObject(driver);
        userAction = new UserActions(driver);
    }

    /**
     * Verify Confirmation Message Notification
     */
    public void closeConfirmationMessageNotification() {
        if (smallfilesPageObject.confirmationMessageElementClose.size() > 0) {
            waitExecuter.waitUntilPageFullyLoaded();
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
            waitExecuter.waitUntilElementPresent(smallfilesPageObject.runButton);
            userActions.performActionWithPolling(smallfilesPageObject.runButton, UserAction.CLICK);
        } catch (TimeoutException te) {
            MouseActions.clickOnElement(driver, smallfilesPageObject.runNowButton);
        }
    }

    /**
     * Method to click on 'SheduleButton'
     */
    public void clickOnScheduleButton() {
        try {
            userActions.performActionWithPolling(smallfilesPageObject.SheduleButton, UserAction.CLICK);
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
        userActions.performActionWithPolling(smallfilesPageObject.modalRunButton, UserAction.CLICK);
        waitExecuter.waitUntilPageFullyLoaded();
    }

    /**
     * Method to validate the  Schedule Button
     */
    public void clickOnModalScheduleButton() {
        try {
            userActions.performActionWithPolling(smallfilesPageObject.runSheduleButton, UserAction.CLICK);
        } catch (TimeoutException te) {
            MouseActions.clickOnElement(driver, smallfilesPageObject.runSheduleButton);
        }
    }


    /* Validate success message on report creation */
    public void verifyScheduleSuccessMsg(String successMsg) {
        waitExecuter.waitUntilElementPresent(smallfilesPageObject.confirmationMessageElement);
        Assert.assertEquals(smallfilesPageObject.confirmationMessageElement.getText(), successMsg,
                "The Schedule success " + "message mismatch");
    }



    /**
     * Method to validate report, download, delete, view report from actions tab
     */
    public void verifyReportsArchived(String name, String reportAction) {
        List<WebElement> reportNameList = smallfilesPageObject.reportNames;
        List<WebElement> reportCntList = smallfilesPageObject.reportCnt;
        Assert.assertFalse(reportNameList.isEmpty(), "There are no reports listed.");

        for (int i = 0; i < reportNameList.size(); i++) {
            int reportCnt = Integer.parseInt(reportCntList.get(i).getText().trim());
            logger.info("ReportCnt is " + reportCnt);
            String reportName = reportNameList.get(i).getText().trim();
            logger.info("The report name is " + reportName);
            if (reportName.equals(name) && reportCnt > 0) {
                switch (reportAction) {
                    case "checkReport":
                        logger.info("Checking report");
                        userActions.performActionWithPolling( reportCntList.get(i), UserAction.CLICK);
                        waitExecuter.waitUntilPageFullyLoaded();
                        waitExecuter.waitUntilElementPresent(smallfilesPageObject.archiveReportSVCHeader);
                        List<WebElement> reportTblRows = smallfilesPageObject.tableRows;
                        Assert.assertFalse(reportTblRows.isEmpty(), "No reports archived.");
                        break;
                    case "downloadReport":
                        userActions.performActionWithPolling( reportCntList.get(i), UserAction.CLICK);
                        waitExecuter.waitUntilPageFullyLoaded();
                        waitExecuter.waitUntilElementPresent(smallfilesPageObject.archiveReportSVCHeader);
                        waitExecuter.waitUntilPageFullyLoaded();
                        userActions.performActionWithPolling(smallfilesPageObject.downloadReportIcon, UserAction.CLICK);
                        MouseActions.clickOnElement(driver, smallfilesPageObject.downloadReportIcon);
                        logger.info("Downloading report");
                        waitExecuter.waitUntilPageFullyLoaded();
                        Assert.assertEquals(smallfilesPageObject.successfulMsgBanner.getText(), "Downloaded successfully",
                                " No downloaded successfully message received.");
                        break;
                    case "deleteReport":
                        userActions.performActionWithPolling( reportCntList.get(i), UserAction.CLICK);
                        waitExecuter.waitUntilPageFullyLoaded();
                        waitExecuter.waitUntilElementPresent(smallfilesPageObject.archiveReportSVCHeader);
                        userActions.performActionWithPolling( smallfilesPageObject.deleteReportIcon, UserAction.CLICK);
                        waitExecuter.waitUntilElementPresent(smallfilesPageObject.deletePopText);
                        String popText = smallfilesPageObject.deletePopText.getText();
                        //String alertText = confirmationAlert.getText();
                        logger.info("Alert text is " + popText);
                        //confirmationAlert.accept();
                        waitExecuter.waitUntilElementPresent(smallfilesPageObject.deleteOkBtn);
                        userActions.performActionWithPolling(smallfilesPageObject.deleteOkBtn, UserAction.CLICK);
                        logger.info("Deleted report");
                        Assert.assertEquals(smallfilesPageObject.successfulMsgBanner.getText(), "Removed successfully",
                                " Report not removed");
                        MouseActions.clickOnElement(driver,smallfilesPageObject.archives);
                        break;
                    case "viewReport":
                        userActions.performActionWithPolling( reportCntList.get(i), UserAction.CLICK);
                        waitExecuter.waitUntilPageFullyLoaded();
                        waitExecuter.waitUntilElementPresent(smallfilesPageObject.archiveReportSVCHeader);
                        MouseActions.clickOnElement(driver, smallfilesPageObject.viewReportIcon);
                        logger.info("Viewed report");
                        waitExecuter.waitUntilPageFullyLoaded();
                        Assert.assertTrue(smallfilesPageObject.viewReportDialogWin.isDisplayed(), "Report  view not present.");
                        MouseActions.clickOnElement(driver, smallfilesPageObject.closeTab);
                        waitExecuter.waitUntilPageFullyLoaded();
                        break;
                    case "searchReportByDate":
                        userActions.performActionWithPolling( reportCntList.get(i), UserAction.CLICK);
                        waitExecuter.waitUntilPageFullyLoaded();
                        waitExecuter.waitUntilElementPresent(smallfilesPageObject.archiveReportSVCHeader);
                        String dateFromElement = smallfilesPageObject.archiveReportDate.getText().trim();
                        String[] arrDate = dateFromElement.split(" ");
                        String date = arrDate[0];
                        System.out.println(date);
                        smallfilesPageObject.reportSearchBox.sendKeys(date);
                        List<WebElement> searchDateReportNameList = smallfilesPageObject.reportNames;
                        Assert.assertFalse(searchDateReportNameList.isEmpty(), "There are no reports listed");
                        Assert.assertTrue(searchDateReportNameList.size() > 0, "Expected search " +
                                "result not populated data by date.");
                        logger.info("Search report by date: " + date);
                        break;
                    case "searchReportByStatus":
                        userActions.performActionWithPolling( reportCntList.get(i), UserAction.CLICK);
                        waitExecuter.waitUntilPageFullyLoaded();
                        waitExecuter.waitUntilElementPresent(smallfilesPageObject.archiveReportSVCHeader);
                        String status = "fail";
                        smallfilesPageObject.reportSearchBox.sendKeys(status);
                        waitExecuter.waitUntilElementPresent(smallfilesPageObject.sortingReportNameIcon);
                        List<WebElement> searchStatusReportNameList = smallfilesPageObject.reportNames;
                        Assert.assertFalse(searchStatusReportNameList.isEmpty(), "There are no reports listed");
                        Assert.assertTrue(searchStatusReportNameList.size() > 0, "Expected search " +
                                "result not populated data by status .");
                        logger.info("Searched report for status as: " + status);
                        break;
                    case "searchReportByName":
                        userActions.performActionWithPolling( reportCntList.get(i), UserAction.CLICK);
                        waitExecuter.waitUntilPageFullyLoaded();
                        waitExecuter.waitUntilElementPresent(smallfilesPageObject.archiveReportSVCHeader);
                        //Give any name which shows in the UI
                        String searchReportName = smallfilesPageObject.archiveReportName.getText().trim();
                        smallfilesPageObject.reportSearchBox.sendKeys(searchReportName);
                        waitExecuter.waitUntilElementPresent(smallfilesPageObject.sortingReportNameIcon);
                        List<WebElement> searchNameReportNameList = smallfilesPageObject.reportNames;
                        Assert.assertFalse(searchNameReportNameList.isEmpty(), "There are no reports listed");
                        Assert.assertTrue(searchNameReportNameList.size() > 0, "Expected search " +
                                "result not populated data by report name.");
                        logger.info("Searched report by name as :" + searchReportName);
                        break;
                }
                break;
            }
        }
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
    public void navigateToSmallFileErrorReport(SmallfilesPageObject smallfilesPageObject, ExtentTest test, String minimumFileSize, String maximumFileSize,
                                          String minimumSmallFile, String directoriesToShow) {

        smallfilesPageObject.minFileSize.sendKeys(minimumFileSize);
        waitExecuter.waitUntilElementPresent(smallfilesPageObject.confirmationMessageMinFileSizeElement);
        Assert.assertEquals(smallfilesPageObject.confirmationMessageMinFileSizeElement.getText(),
                "Min File Size is between 0 B and 2.00 GB");
        test.log(LogStatus.INFO, "Set minimum FileSize as: " + smallfilesPageObject.confirmationMessageMinFileSizeElement.getText());

        smallfilesPageObject.maxiFileSize.sendKeys(maximumFileSize);
        LOGGER.info("Set directories To Show as: " + maximumFileSize);
        test.log(LogStatus.INFO, "Set directories To Show as: " + maximumFileSize);


        smallfilesPageObject.minimumSmallFile.sendKeys(minimumSmallFile);
        LOGGER.info("Set directories To Show as: " + minimumSmallFile);
        test.log(LogStatus.INFO, "Set directories To Show as: " + minimumSmallFile);


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

    /* Select hour from drop-down */
    public void selectByHour(String hour) {
        waitExecuter.waitUntilElementClickable(smallfilesPageObject.hoursDropdown);
        Select scheduleTorunDropDown = new Select(smallfilesPageObject.hoursDropdown);
        scheduleTorunDropDown.selectByVisibleText(hour);

    }

    /* Select minute from drop-down */
    public void selectByMin(String min) {
        waitExecuter.waitUntilElementClickable(smallfilesPageObject.minutesDropdown);
        Select scheduleTorunDropDown = new Select(smallfilesPageObject.minutesDropdown);
        scheduleTorunDropDown.selectByVisibleText(min);

    }


    /**
     * Method to validate the search option for different file types
     */
    public void verifyAllFileSizePathSearchOption(String clusterID, int tablesRows, int tableCells) {
        List<WebElement> tableHeaderList = smallfilesPageObject.searchTableHeader;
        List<WebElement> tableRows = smallfilesPageObject.searchFileTableRows;
        String searchString = "";
        List<WebElement> rows = smallfilesPageObject.rowData.findElements(By.xpath("/tr[" + tablesRows + "]/td[" + tableCells + "]"));
        for (WebElement row : rows) {
            Assert.assertTrue(row.isDisplayed(), "No data under column: " + tableHeaderList.get(1).getText() +
                    " for ");
            searchString = row.getText();
            LOGGER.info("The search string is " + searchString);
            smallfilesPageObject.searchField.sendKeys(searchString);
            waitExecuter.waitUntilPageFullyLoaded();
            List<WebElement> cols = row.findElements(By.xpath("/tr[" + row + "]/td[" + tableCells + "]"));
            for (WebElement col : cols) {
                Assert.assertTrue(col.isDisplayed(), "No data under column: File " +
                        " for ");
                LOGGER.info("Search String is " + searchString + " Search result is " + col.getText());
                Assert.assertTrue(col.getText().contains(searchString), "The search result for " +
                        " file type donot contain the search string\n Expected '" + searchString + "' to be present in '"
                        + col.getText() + "' search result");
            }
        }
    }

    /* Select day from drop-down */
    public void selectByDays(String dayToRun) {
        waitExecuter.waitUntilElementClickable(smallfilesPageObject.scheduleDays);
        Select scheduleTorunDropDown = new Select(smallfilesPageObject.scheduleDays);
        scheduleTorunDropDown.selectByVisibleText(dayToRun);

    }

    /* Define day and Time to select from drop-down */
    public void selectDayTime(String day, String hour, String min) {
        selectByDays(day);
        smallfilesPageObject.displayTime.click();
        selectByHour(hour);
        selectByMin(min);
    }

    /* This method is to Select cluster for schedule and run reports */
    public void selectCluster(String clusterId) {
        waitExecuter.waitUntilElementClickable(smallfilesPageObject.clusterDropdown);
        userAction.performActionWithPolling(smallfilesPageObject.clusterDropdown, UserAction.CLICK);
        userAction.performActionWithPolling(smallfilesPageObject.clusterSearchbox, UserAction.CLICK);
        userAction.performActionWithPolling(smallfilesPageObject.clusterSearchbox, UserAction.SEND_KEYS, clusterId);
        userAction.performActionWithPolling(smallfilesPageObject.select2stClusterOption, UserAction.CLICK);
    }


    /**
     * Common steps to validate schedule name,daily,email
     */
    public void scheduleAdvancedOptions(SmallfilesPageObject smallfilesPageObject, ExtentTest test,
                                        String scheduleName, String email) {

        smallfilesPageObject.scheduleNameTextbox.sendKeys(scheduleName);
        LOGGER.info("Set directories To Show as: " + scheduleName);
        test.log(LogStatus.INFO, "Set directories To Show as: " + scheduleName);

        smallfilesPageObject.emailNotification.sendKeys(email);
        LOGGER.info("Set directories To Show as: " + email);
        test.log(LogStatus.INFO, "Set directories To Show as: " + email);
    }

    /**
     * Method to click on 'advancedOptions'
     */
    public void clickOnadvancedOptions() {
        try {
            userActions.performActionWithPolling(smallfilesPageObject.advancedOptions, UserAction.CLICK);
        } catch (TimeoutException te) {
            userActions.performActionWithPolling(smallfilesPageObject.advancedOptions, UserAction.CLICK);
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
        userActions.performActionWithPolling(topPanelComponentPageObject.data, UserAction.CLICK);
        logger.info("Clicked on Data Tab");
        test.log(LogStatus.INFO, "Clicked on Data Tab");

        TopPanelPageObject topPanelPageObject = new TopPanelPageObject(driver);
        waitExecuter.waitUntilElementPresent(topPanelPageObject.smallFilesTab);
        waitExecuter.waitUntilPageFullyLoaded();
        userActions.performActionWithPolling(topPanelPageObject.smallFilesTab, UserAction.CLICK);
        waitExecuter.sleep(3000);
        userActions.performActionWithPolling(topPanelPageObject.smallFilesTab, UserAction.CLICK);
        logger.info("Clicked on small FilesTab Tab");
        test.log(LogStatus.INFO, "Clicked on small FilesTab Tab");

    }
}
