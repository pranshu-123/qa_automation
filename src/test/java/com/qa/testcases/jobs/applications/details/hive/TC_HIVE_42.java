package com.qa.testcases.jobs.applications.details.hive;

import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.constants.PageConstants;
import com.qa.enums.UserAction;
import com.qa.pagefactory.SubTopPanelModulePageObject;
import com.qa.pagefactory.jobs.ApplicationsPageObject;
import com.qa.scripts.DatePicker;
import com.qa.scripts.appdetails.SparkAppsDetailsPage;
import com.qa.scripts.jobs.applications.AllApps;
import com.qa.utils.WaitExecuter;
import com.qa.utils.actions.UserActions;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

@Marker.AppDetailsHive
@Marker.EMRHive
@Marker.GCPAppDetailsHive
@Marker.All
public class TC_HIVE_42 extends BaseClass {
    private static final Logger LOGGER = Logger.getLogger(TC_HIVE_42.class.getName());

    @Test
    public void VerifyFilterByStatus() {
        test = extent.startTest("TC_HIVE_42.VerifyFilterByStatus",
                "Verify that All the hive apps with different status must be listed in the jobs page (Killed, Failed, running, success, unknown).");
        test.assignCategory("App Details - Hive");
        test.log(LogStatus.INFO, "Login to the application");

        // Initialize all classes objects
        test.log(LogStatus.INFO, "Initialize all class objects");
        LOGGER.info("Initialize all class objects");
        WaitExecuter waitExecuter = new WaitExecuter(driver);
        SubTopPanelModulePageObject topPanelComponentPageObject = new SubTopPanelModulePageObject(driver);
        ApplicationsPageObject applicationsPageObject = new ApplicationsPageObject(driver);
        DatePicker datePicker = new DatePicker(driver);
        AllApps allApps = new AllApps(driver);
        SparkAppsDetailsPage sparkApp = new SparkAppsDetailsPage(driver);
        UserActions userActions = new UserActions(driver);
        // Navigate to Jobs tab from header
        test.log(LogStatus.INFO, "Navigate to jobs tab from header");
        LOGGER.info("Navigate to jobs tab from header");
        waitExecuter.waitUntilElementClickable(topPanelComponentPageObject.jobs);
        userActions.performActionWithPolling(topPanelComponentPageObject.jobs, UserAction.CLICK);
        waitExecuter.waitUntilElementClickable(applicationsPageObject.resetButton);
        waitExecuter.waitUntilPageFullyLoaded();
        // Select last 30 days from date picker
        test.log(LogStatus.INFO, "Select last 30 days");
        LOGGER.info("Select last 30 days");
        datePicker.clickOnDatePicker();
        waitExecuter.sleep(1000);
        datePicker.selectLast30Days();
        waitExecuter.sleep(3000);
        // Select 'Only' hive type and get its jobs count
        test.log(LogStatus.INFO, "Select 'Only' hive from app types and get its jobs count");
        LOGGER.info("Select 'Only' hive from app types and get its jobs count");
        sparkApp.clickOnlyLink("Hive");

        // Expand status filter on left pane
        test.log(LogStatus.INFO, "Expand status filter on left pane");
        LOGGER.info("Expand status filter on left pane");
        applicationsPageObject.expandStatus.click();
        waitExecuter.sleep(2000);
        // To apply filter - De-select all status types
        test.log(LogStatus.INFO, "To apply status filter - De-select all status types");
        LOGGER.info("To apply status filter - De-select all status types");
        allApps.deselectAllStatusTypes();
        waitExecuter.sleep(2000);

        /*
         * Validate that status types are --
         * "Killed","Failed","Running","Success","Pending","Unknown", "Waiting"
         */
        test.log(LogStatus.INFO,
                "Assert status types - 'Killed','Failed','Running','Success','Pending','Unknown', 'Waiting'");
        LOGGER.info("Assert status types - 'Killed','Failed','Running','Success','Pending','Unknown', 'Waiting'");
        List<String> existingStatusTypes = new ArrayList<>(Arrays.asList(PageConstants.JobsStatusType.STATUSTYPE));
        List<WebElement> statusTypes = applicationsPageObject.getStatusTypes;
        List<String> listOfStatusTypes = new ArrayList<String>();
        waitExecuter.sleep(2000);
        for (int i = 0; i < statusTypes.size(); i++) {
            listOfStatusTypes.add(statusTypes.get(i).getText().trim());
        }
        waitExecuter.sleep(2000);
        LOGGER.info("List of status type actual - " + listOfStatusTypes);
        LOGGER.info("List of status type expected - " + existingStatusTypes);
        Assert.assertTrue(listOfStatusTypes.equals(existingStatusTypes),
                "Status types displayed does not match the expected status list");
        test.log(LogStatus.PASS, "Status types displayed match the expected status list");
        // Select single app and assert that table contain its data.
        test.log(LogStatus.INFO, "Select single app and assert that table contain its data.");
        LOGGER.info("Select single app and assert that table contain its data.");
        List<WebElement> clickOnIndividualStatus = applicationsPageObject.selectSingleStatusType;
        waitExecuter.sleep(2000);
        for (int i = 0; i < clickOnIndividualStatus.size(); i++) {
            waitExecuter.sleep(2000);
            clickOnIndividualStatus.get(i).click();
            waitExecuter.sleep(2000);
            int appCount = Integer
                    .parseInt(applicationsPageObject.statusJobCount.getText().replaceAll("[^\\dA-Za-z ]", "").trim());
            LOGGER.info("Status app count- " + appCount);
            waitExecuter.sleep(2000);
            int totalCount = Integer
                    .parseInt(applicationsPageObject.getTotalAppCount.getText().replaceAll("[^\\dA-Za-z ]", "").trim());
            LOGGER.info("Total app count- " + totalCount);
            waitExecuter.sleep(1000);

            Assert.assertEquals(appCount, totalCount, "The app count of " + statusTypes.get(i).getText().trim()
                    + " is not equal to the total count of heading.");
            test.log(LogStatus.PASS, "The status count matches the total count");

            if (appCount > 0) {
                String getStatusTypeFromTable = applicationsPageObject.getStatusFromTable.getText();
                waitExecuter.sleep(2000);
                Assert.assertEquals(getStatusTypeFromTable.toLowerCase(),
                        statusTypes.get(i).getText().trim().toLowerCase(),
                        "The Jobs displayed in tables contains application other than that of selected App Type");
                test.log(LogStatus.PASS, "The Jobs displayed in tables contains application of selected App Type");
                waitExecuter.sleep(1000);
            } else {
                Assert.assertTrue(applicationsPageObject.whenNoApplicationPresent.isDisplayed(),
                        "The cluster does not have any application under it and also does not display 'No Data Available' for it");
            }
            waitExecuter.sleep(1000);
            clickOnIndividualStatus.get(i).click();
            waitExecuter.sleep(2000);
        }
        // Click on show-all to view all type of applications
        applicationsPageObject.resetButton.click();
        waitExecuter.sleep(3000);
    }
}