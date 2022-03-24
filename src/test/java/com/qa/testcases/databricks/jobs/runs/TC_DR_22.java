package com.qa.testcases.databricks.jobs.runs;

import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.enums.UserAction;
import com.qa.pagefactory.DatePickerPageObject;
import com.qa.pagefactory.databricks.DbxSubTopPanelModulePageObject;
import com.qa.scripts.DatePicker;
import com.qa.scripts.databricks.jobs.DbAllApps;
import com.qa.utils.LoggingUtils;
import com.qa.utils.WaitExecuter;
import com.qa.utils.actions.UserActions;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

@Marker.DbxRuns
@Marker.All
public class TC_DR_22 extends BaseClass {
    private final LoggingUtils loggingUtils = new LoggingUtils(TC_DR_18.class);

    @Test()
    public void validateCustomDateFunctionality() {
        test = extent.startTest("TC_DR_22.validateCustomDateFunctionality",
                "Verify the user can select custom date ranges");
        test.log(LogStatus.INFO, "Login to the application");
        // Initialize all classes objects
        test.log(LogStatus.INFO, "Initialize all class objects");
        loggingUtils.info("Initialize all class objects", test);
        WaitExecuter waitExecuter = new WaitExecuter(driver);
        DbxSubTopPanelModulePageObject dbpageObject = new DbxSubTopPanelModulePageObject(driver);
        DatePickerPageObject datePickerPageObject = new DatePickerPageObject(driver);
        DbAllApps dballApps = new DbAllApps(driver);
        DatePicker datePicker = new DatePicker(driver);
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        UserActions userActions = new UserActions(driver);
        // Navigate to Runs tab from header
        test.log(LogStatus.INFO, "Navigate to Runs tab from header");
        dballApps.navigateToJobsTab("Runs");
        try {
            test.log(LogStatus.INFO, "Navigate to jobs tab from header");
            test.log(LogStatus.INFO, "Select custom range in datepicker");
            waitExecuter.waitUntilElementClickable(dbpageObject.resetButton);
            datePicker.clickOnDatePicker();
            waitExecuter.waitUntilElementClickable(dbpageObject.resetButton);
            datePicker.selectCustomRange();
            waitExecuter.waitUntilElementClickable(dbpageObject.resetButton);
            datePicker.setStartAndEndDateFromCurrentDate(-3, -1);
            datePicker.clickOnCustomDateApplyBtn();
            waitExecuter.waitUntilElementClickable(dbpageObject.resetButton);
            // Click on user searchbox and get all usernames.
            test.log(LogStatus.INFO, "Click on user searchbox and get all usernames.");
            loggingUtils.info("Click on user searchbox and get all usernames.", test);
            executor.executeScript("arguments[0].scrollIntoView();", dbpageObject.userSearchBox);

            waitExecuter.waitUntilElementClickable(dbpageObject.userExpandableHeader);
            waitExecuter.waitUntilElementClickable(dbpageObject.userSearchBox);
            userActions.performActionWithPolling(dbpageObject.userSearchBox, UserAction.CLICK);
            //applicationsPageObject.userSearchBox.sendKeys(usernameFromTable);
            waitExecuter.waitUntilElementClickable(dbpageObject.userSearchBox);
            List<WebElement> userList = dbpageObject.getNamesFromDropDown;
            waitExecuter.sleep(1000);
            String usernameSelected = userList.get(0).getText();
            waitExecuter.waitUntilElementClickable(userList.get(0));
            userList.get(0).click();
            waitExecuter.waitUntilElementClickable(dbpageObject.resetButton);
            waitExecuter.sleep(2000);
            loggingUtils.info("Selected username from dropdown " + usernameSelected, test);
            executor.executeScript("arguments[0].scrollIntoView();", dbpageObject.globalSearchBox);
            int totalCount = Integer
                    .parseInt(dbpageObject.getTotalAppCount.getText().replaceAll("[^\\dA-Za-z ]", "").trim());
            if (totalCount > 0) {
                String usernameFromTable = dbpageObject.getUsernameFromTable.getAttribute("title");
                loggingUtils.info("Username displayed in table " + usernameFromTable, test);
                Assert.assertTrue(usernameSelected.contains(usernameFromTable),
                        "The application in table contains username other than that of " + usernameFromTable);
                test.log(LogStatus.PASS, "The application in table matches username: " + usernameFromTable);
            } else
                Assert.assertTrue(dbpageObject.whenApplicationPresent.isDisplayed(),
                        "The clusterId has application under it and displays available data for it");
            executor.executeScript("arguments[0].scrollIntoView();", dbpageObject.resetButton);
            waitExecuter.waitUntilElementClickable(dbpageObject.resetButton);
            userActions.performActionWithPolling(dbpageObject.resetButton, UserAction.CLICK);
            waitExecuter.waitUntilElementClickable(dbpageObject.resetButton);

        } catch (NoSuchElementException ex) {
            loggingUtils.error("No app present by this name", test);
            loggingUtils.error("Error- " + ex, test);
        }
    }
}
