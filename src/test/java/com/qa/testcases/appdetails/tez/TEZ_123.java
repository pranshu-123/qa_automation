package com.qa.testcases.appdetails.tez;

import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.constants.PageConstants;
import com.qa.pagefactory.SubTopPanelModulePageObject;
import com.qa.pagefactory.appsDetailsPage.TezAppsDetailsPageObject;
import com.qa.pagefactory.jobs.ApplicationsPageObject;
import com.qa.scripts.DatePicker;
import com.qa.scripts.appdetails.TezAppsDetailsPage;
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
@Marker.AppDetailsTez
@Marker.EMRTez
@Marker.GCPAppDetailsTez
@Marker.All
public class TEZ_123 extends BaseClass {

    Logger logger = LoggerFactory.getLogger(TEZ_123.class);

    @Test(dataProvider = "clusterid-data-provider")
    public void TEZ_123_verifyKPI(String clusterId) throws InterruptedException {
        test = extent.startTest("TEZ_123_verifyKPI: " + clusterId,
                "Verify Tez apps should have Cluster IDs for all the states of application ");
        test.assignCategory(" Apps Details-Tez");
        Log.startTestCase("TEZ_123_verifyKPI");

        // Initialize all classes objects
        test.log(LogStatus.INFO, "Initialize all class objects");
        logger.info("Initialize all class objects");
        WaitExecuter waitExecuter = new WaitExecuter(driver);
        SubTopPanelModulePageObject topPanelComponentPageObject = new SubTopPanelModulePageObject(driver);
        ApplicationsPageObject applicationsPageObject = new ApplicationsPageObject(driver);
        TezAppsDetailsPageObject tezApps = new TezAppsDetailsPageObject(driver);
        TezAppsDetailsPage tezDetailsPage = new TezAppsDetailsPage(driver);
        DatePicker datePicker = new DatePicker(driver);
        AllApps allApps = new AllApps(driver);

        test.log(LogStatus.INFO, "Navigate to jobs tab from header");
        tezDetailsPage.navigateToJobsTabFromHeader(topPanelComponentPageObject, allApps, datePicker,
                applicationsPageObject, clusterId);
        test.log(LogStatus.INFO, "Verify that the left pane has tez check box and the apps number");

        tezDetailsPage.clickOnlyLink("Tez");
        int tezAppCount = Integer.parseInt(applicationsPageObject.getEachApplicationTypeJobCounts.get(0).getText()
                .replaceAll("[^\\dA-Za-z ]", "").trim());
        // Expand status filter on left pane
        test.log(LogStatus.INFO, "Expand status filter on left pane");
        logger.info("Expand status filter on left pane");
        applicationsPageObject.expandStatus.click();
        waitExecuter.waitForSeconds(5);
        // To apply filter - De-select all status types
        test.log(LogStatus.INFO, "To apply status filter - De-select all status types");
        logger.info("To apply status filter - De-select all status types");
        allApps.deselectAllStatusTypes();
        waitExecuter.waitForSeconds(5);

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
            waitExecuter.waitForSeconds(3);
            int totalCount = Integer
                    .parseInt(applicationsPageObject.getTotalAppCount.getText().replaceAll("[^\\dA-Za-z ]", "").trim());
            logger.info("Total app count- " + totalCount);
            waitExecuter.waitForSeconds(3);

            Assert.assertEquals(appCount, totalCount, "The app count of " + statusTypes.get(i).getText().trim()
                    + " is not equal to the total count of heading.");
            test.log(LogStatus.PASS, "The status count matches the total count");

            if (appCount > 0) {
                String getStatusTypeFromTable = applicationsPageObject.getStatusFromTable.getText();
                waitExecuter.waitForSeconds(3);
                Assert.assertEquals(getStatusTypeFromTable.toLowerCase(),
                        statusTypes.get(i).getText().trim().toLowerCase(),
                        "The Jobs displayed in tables contains application other than that of selected App Type");
                test.log(LogStatus.PASS, "The Jobs displayed in tables contains application of selected App Type: "+getStatusTypeFromTable);
                waitExecuter.waitForSeconds(3);
            } else {
                Assert.assertTrue(applicationsPageObject.whenNoApplicationPresent.isDisplayed(),
                        "The clusterId does not have any application under it and also does not display 'No Data Available' for it"
                                + clusterId);
                test.log(LogStatus.SKIP, "The clusterId does not have any application under it.");
            }
            waitExecuter.waitForSeconds(2);
            clickOnIndividualStatus.get(i).click();
            waitExecuter.waitForSeconds(3);
        }
        // Click on show-all to view all type of applications
        applicationsPageObject.resetButton.click();
        waitExecuter.waitForSeconds(3);
    }
}
