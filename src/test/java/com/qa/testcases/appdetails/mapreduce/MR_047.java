package com.qa.testcases.appdetails.mapreduce;

import com.qa.base.BaseClass;
import com.qa.constants.PageConstants;
import com.qa.pagefactory.SubTopPanelModulePageObject;
import com.qa.pagefactory.appsDetailsPage.MrAppsDetailsPageObject;
import com.qa.pagefactory.jobs.ApplicationsPageObject;
import com.qa.scripts.DatePicker;
import com.qa.scripts.appdetails.MrAppsDetailsPage;
import com.qa.scripts.jobs.applications.AllApps;
import com.qa.utils.Log;
import com.qa.utils.WaitExecuter;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MR_047 extends BaseClass {

    Logger logger = LoggerFactory.getLogger(com.qa.testcases.appdetails.mapreduce.MR_047.class);

    @Test(dataProvider = "clusterid-data-provider")
    public void MR_047_verifyMRJobsSummary(String clusterId) {
        test = extent.startTest("MR_047_verifyMRJobsSummary: " + clusterId,
                "Verify job summary must be defined based the actual state of the apps");
        test.assignCategory(" Apps Details-Mr");
        Log.startTestCase("MR_047_verifyMRJobsSummary");

        // Initialize all classes objects
        test.log(LogStatus.INFO, "Initialize all class objects");
        logger.info("Initialize all class objects");
        SubTopPanelModulePageObject topPanelComponentPageObject = new SubTopPanelModulePageObject(driver);
        ApplicationsPageObject applicationsPageObject = new ApplicationsPageObject(driver);
        MrAppsDetailsPageObject mrApps = new MrAppsDetailsPageObject(driver);
        MrAppsDetailsPage mrDetailsPage = new MrAppsDetailsPage(driver);
        WaitExecuter waitExecuter = new WaitExecuter(driver);
        DatePicker datePicker = new DatePicker(driver);
        AllApps allApps = new AllApps(driver);

        test.log(LogStatus.INFO, "Navigate to jobs tab from header");
        mrDetailsPage.navigateToJobsTabFromHeader(topPanelComponentPageObject, allApps, datePicker,
                applicationsPageObject, clusterId);

        test.log(LogStatus.INFO, "Verify that the left pane has map reduce check box and the apps number");
        mrDetailsPage.clickOnlyLink("Map Reduce");
        // Expand status filter on left pane
        test.log(LogStatus.INFO, "Expand status filter on left pane");
        logger.info("Expand status filter on left pane");
        applicationsPageObject.expandStatus.click();
        waitExecuter.sleep(2000);
        // To apply filter - De-select all status types
        test.log(LogStatus.INFO, "To apply status filter - De-select all status types");
        logger.info("To apply status filter - De-select all status types");
        allApps.deselectAllStatusTypes();
        waitExecuter.sleep(2000);

        /*
         * Validate that status types are --
         * "Killed","Failed","Running","Success","Pending","Unknown", "Waiting"
         */
        test.log(LogStatus.INFO,
                "Assert status types - 'Killed','Failed','Running','Success','Pending','Unknown', 'Waiting'");
        logger.info("Assert status types - 'Killed','Failed','Running','Success','Pending','Unknown', 'Waiting'");
        List<String> existingStatusTypes = new ArrayList<>(Arrays.asList(PageConstants.JobsStatusType.STATUSTYPE));
        List<WebElement> statusTypes = applicationsPageObject.getStatusTypes;
        List<String> listOfStatusTypes = new ArrayList<String>();
        waitExecuter.sleep(2000);
        for (int i = 0; i < statusTypes.size(); i++) {
            listOfStatusTypes.add(statusTypes.get(i).getText().trim());
        }
        waitExecuter.sleep(2000);
        logger.info("List of status type actual - " + listOfStatusTypes);
        logger.info("List of status type expected - " + existingStatusTypes);
        Assert.assertTrue(listOfStatusTypes.equals(existingStatusTypes),
                "Status types displayed does not match the expected status list");
        test.log(LogStatus.PASS, "Status types displayed match the expected status list");
        // Select single app and assert that table contain its data.
        test.log(LogStatus.INFO, "Select single app and assert that table contain its data.");
        logger.info("Select single app and assert that table contain its data.");
        List<WebElement> clickOnIndividualStatus = applicationsPageObject.selectSingleStatusType;
        waitExecuter.sleep(2000);
        for (int i = 0; i < clickOnIndividualStatus.size(); i++) {
            waitExecuter.sleep(2000);
            clickOnIndividualStatus.get(i).click();
            waitExecuter.sleep(2000);
            int appCount = Integer.parseInt(applicationsPageObject.statusJobCount.getText().replaceAll("[^\\dA-Za-z ]", "").trim());
            logger.info("Status app count- " + appCount);
            waitExecuter.sleep(2000);
            int totalCount = Integer
                    .parseInt(applicationsPageObject.getTotalAppCount.getText().replaceAll("[^\\dA-Za-z ]", "").trim());
            logger.info("Total app count- " + totalCount);
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
                test.log(LogStatus.PASS, "The Jobs displayed in tables contains application of selected App Type: " + getStatusTypeFromTable);
                waitExecuter.sleep(1000);
            } else {
                Assert.assertTrue(applicationsPageObject.whenNoApplicationPresent.isDisplayed(),
                        "The clusterId does not have any application under it and also does not display 'No Data Available' for it"
                                + clusterId);
                test.log(LogStatus.SKIP, "The clusterId does not have any application under it.");
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

