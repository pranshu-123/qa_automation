package com.qa.testcases.cluster.tuning;

import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.constants.PageConstants;
import com.qa.pagefactory.DatePickerPageObject;
import com.qa.pagefactory.SubTopPanelModulePageObject;
import com.qa.pagefactory.TopPanelPageObject;
import com.qa.pagefactory.clusters.TuningPageObject;
import com.qa.pagefactory.reports.ReportsArchiveScheduledPageObject;
import com.qa.scripts.DatePicker;
import com.qa.scripts.HomePage;
import com.qa.scripts.clusters.Tuning;
import com.qa.scripts.reports.ReportsArchiveSchedulePage;
import com.qa.utils.DateUtils;
import com.qa.utils.MouseActions;
import com.qa.utils.WaitExecuter;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.logging.Logger;

@Marker.Tuning
@Marker.All
public class TC_CTR_03 extends BaseClass {
    private static final Logger LOGGER = Logger.getLogger(TC_CTR_03.class.getName());

    @Test(dataProvider = "clusterid-data-provider")
    public void validateTuningForDifferentDatePicker(String clusterId) {
        test = extent.startTest("TC_CTR_03.validateTuningForDifferentDatePicker: "+ clusterId,
                "Verify tuning report is generated for different date ranges.");
        test.assignCategory(" Cluster - Tuning ");
        LOGGER.info("Passed Parameter Is : " + clusterId);

        WaitExecuter waitExecuter = new WaitExecuter(driver);
//        TopPanelPageObject topPanelPageObject = new TopPanelPageObject(driver);
//        waitExecuter.waitUntilElementPresent(topPanelPageObject.tuningTab);
        waitExecuter.waitUntilPageFullyLoaded();
//        waitExecuter.waitUntilElementClickable(topPanelPageObject.tuningTab);
//        waitExecuter.sleep(3000);
//        MouseActions.clickOnElement(driver, topPanelPageObject.tuningTab);
//        LOGGER.info("Clicked on Tuning Tab");
//        test.log(LogStatus.INFO, "Clicked on Tuning Tab");

        test.log(LogStatus.INFO, "Initialize all class objects");
        LOGGER.info("Initialize all class objects");
        SubTopPanelModulePageObject topPanelComponentPageObject = new SubTopPanelModulePageObject(driver);
        MouseActions.clickOnElement(driver, topPanelComponentPageObject.reports);
        waitExecuter.sleep(3000);
        ReportsArchiveSchedulePage reportsPage = new ReportsArchiveSchedulePage(driver);
        ReportsArchiveScheduledPageObject reportPageObj = new ReportsArchiveScheduledPageObject(driver);
        LOGGER.info("Click on + button");
        String statusXpath = reportsPage.clickOnReportName(reportPageObj, PageConstants.ReportsArchiveNames.Tuning);
        LOGGER.info("Clicked on Tuning Tab + icon");
        test.log(LogStatus.INFO, "Clicked on Tuning Tab + icon");

        String[] expectedDateOptions = {"Last 7 Days", "Last 30 Days", "Last 60 Days", "Last 90 Days", "Custom Range"};

        DatePicker datePicker = new DatePicker(driver);
        HomePage homePage = new HomePage(driver);
        DatePickerPageObject datePickerPageObject = new DatePickerPageObject(driver);
        TuningPageObject tuningPageObject = new TuningPageObject(driver);

        for (String expectedDateOption : expectedDateOptions) {

            Tuning tuning = new Tuning(driver);
//            tuning.closeConfirmationMessageNotification();
//            tuning.clickOnRunButton();
            LOGGER.info("Clicked on Run button");
            test.log(LogStatus.INFO,"Clicked on Run button");
            datePicker.clickOnDatePickerForTopX();

            for (int i=0; i < expectedDateOptions.length; i++) {
                WebElement datePickerElement = datePickerPageObject.dateRangeOptions.get(i);
                String date = datePickerElement.getText();
                if(expectedDateOption.equalsIgnoreCase(date)){
                    if(date.equalsIgnoreCase("Custom Range")) {
                        datePickerElement.click();
                        datePicker.setStartDate(DateUtils.getFirstDateOfYear());
                        datePicker.setEndDate(DateUtils.getCurrentDate());
                        datePicker.clickOnCustomDateApplyBtn();
                        homePage.selectMultiClusterId(clusterId);
                        tuning.clickOnModalRunButton();
//                        waitExecuter.waitUntilElementPresent(tuningPageObject.runButton);
//                        waitExecuter.waitUntilElementClickable(tuningPageObject.runButton);
//                        try {
//                            waitExecuter.waitUntilTextToBeInWebElement(tuningPageObject.confirmationMessageElement,
//                                    "Cluster Tuning completed successfully.");
//                            test.log(LogStatus.PASS, "Verified Tuning report is completed successfully for date: " + date);
//                        } catch (TimeoutException te) {
//                            throw new AssertionError("Tuning Report not completed successfully.");
//                        }
                        WebElement statusElement = driver.findElement(By.xpath(statusXpath));
                        try{
                            waitExecuter.waitUntilTextToBeInWebElement(statusElement,
                                    "SUCCESS");
                        }catch (TimeoutException te) {
                            throw new AssertionError("Tuning Report not completed successfully.");
                        }
                        break;
                    }else{
                        datePickerElement.click();
                        homePage.selectMultiClusterId(clusterId);
                        tuning.clickOnModalRunButton();
//                        waitExecuter.waitUntilElementPresent(tuningPageObject.runButton);
//                        waitExecuter.waitUntilElementClickable(tuningPageObject.runButton);
//                        try {
//                            waitExecuter.waitUntilTextToBeInWebElement(tuningPageObject.confirmationMessageElement,
//                                    "Cluster Tuning completed successfully.");
//                            test.log(LogStatus.PASS, "Verified Tuning report is completed successfully for date: " + date);
//                        } catch (TimeoutException te) {
//                            throw new AssertionError("Tuning Report not completed successfully.");
//                        }
                        WebElement statusElement = driver.findElement(By.xpath(statusXpath));
                        try{
                            waitExecuter.waitUntilTextToBeInWebElement(statusElement,
                                    "SUCCESS");
                            test.log(LogStatus.PASS, "Verified Tuning report is completed successfully for date: " + date);
                        }catch (TimeoutException te) {
                            throw new AssertionError("Tuning Report not completed successfully.");
                        }
                        break;
                    }
                }

            }

        }

    }
}
