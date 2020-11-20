package com.qa.testcases.appdetails.tez;

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

public class TEZ_123 extends BaseClass {

    Logger logger = LoggerFactory.getLogger(TEZ_124.class);

    @Test(dataProvider = "clusterid-data-provider")
    public void TEZ_124_verifyKPI(String clusterId) {
        test = extent.startTest("TEZ_124_verifyKPI: " + clusterId,
                "Verify Tez apps should have Cluster IDs for all the states of application ");
        test.assignCategory(" Apps Details-Tez");
        Log.startTestCase("TEZ_124_verifyKPI");

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

        test.log(LogStatus.INFO, "Verify that the left pane has tez check box and the apps number");
        int appCount = tezDetailsPage.clickOnlyLink("Tez");

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
        Assert.assertTrue(listOfStatusTypes.equals(existingStatusTypes),
                "Status types displayed does not match the expected status list");
        test.log(LogStatus.PASS, "Status types displayed match the expected status list");
        // Select single app and assert that table contain its data.
        test.log(LogStatus.INFO, "Select single app and assert that table contain its data.");
        logger.info("Select single app and assert that table contain its data.");
        List<WebElement> clickOnIndividualStatus = applicationsPageObject.selectSingleStatusType;
        waitExecuter.sleep(2000);
        List<WebElement> appStatusCounts = applicationsPageObject.getEachStatusTypeJobCount;
        waitExecuter.sleep(2000);
        for (int i = 0; i < clickOnIndividualStatus.size(); i++) {
            waitExecuter.sleep(2000);
            clickOnIndividualStatus.get(i).click();
            waitExecuter.sleep(2000);
            int TezCount = Integer.parseInt(appStatusCounts.get(i).getText().replaceAll("[^\\dA-Za-z ]", "").trim());
            waitExecuter.sleep(1000);
            int totalCount = Integer
                    .parseInt(applicationsPageObject.getTotalAppCount.getText().replaceAll("[^\\dA-Za-z ]", "").trim());
            waitExecuter.sleep(1000);

            Assert.assertEquals(TezCount, totalCount, "The app count of " + statusTypes.get(i).getText().trim()
                    + " is not equal to the total count of heading.");
            test.log(LogStatus.PASS, "The status count matches the total count");
        }

    }
}