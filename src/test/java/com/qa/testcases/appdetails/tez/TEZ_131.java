package com.qa.testcases.appdetails.tez;

import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.pagefactory.SubTopPanelModulePageObject;
import com.qa.pagefactory.appsDetailsPage.TezAppsDetailsPageObject;
import com.qa.pagefactory.jobs.ApplicationsPageObject;
import com.qa.scripts.DatePicker;
import com.qa.scripts.appdetails.TezAppsDetailsPage;
import com.qa.scripts.jobs.applications.AllApps;
import com.qa.utils.Log;
import com.qa.utils.MouseActions;
import com.qa.utils.WaitExecuter;
import com.relevantcodes.extentreports.LogStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

@Marker.AppDetailsTez
@Marker.All
public class TEZ_131 extends BaseClass {

    Logger logger = LoggerFactory.getLogger(TEZ_130.class);

    @Test(dataProvider = "clusterid-data-provider")
    public void TEZ_131_VerifyTheTags(String clusterId) {
        test = extent.startTest("TEZ_131_VerifyTheTags: " + clusterId,
                "Verify Tags needs to be populated randomly");
        test.assignCategory(" Apps Details-Tez");
        Log.startTestCase("TEZ_131_VerifyTheTags");
        SubTopPanelModulePageObject topPanelComponentPageObject = new SubTopPanelModulePageObject(driver);
        ApplicationsPageObject applicationsPageObject = new ApplicationsPageObject(driver);
        TezAppsDetailsPageObject tezApps = new TezAppsDetailsPageObject(driver);
        TezAppsDetailsPage tezDetailsPage = new TezAppsDetailsPage(driver);
        DatePicker datePicker = new DatePicker(driver);
        WaitExecuter waitExecuter = new WaitExecuter(driver);
        AllApps allApps = new AllApps(driver);

        // Navigate to Jobs tab from header
        test.log(LogStatus.INFO, "Navigate to jobs tab from header");
        tezDetailsPage.navigateToJobsTabFromHeader(topPanelComponentPageObject, allApps, datePicker,
                applicationsPageObject, clusterId);


        //Verify that the left pane has Tez check box and the apps number
        test.log(LogStatus.INFO, "Verify that the left pane has Tez check box and the apps number");
        logger.info("Select individual app and assert that table contain its data");

        int appCount=tezDetailsPage.clickOnlyLink("Tez");
        int totalCount = Integer.parseInt(applicationsPageObject.getTotalAppCount.getText().
                replaceAll("[^\\dA-Za-z ]", "").trim());
        logger.info("AppCount is " + appCount + " total count is " + totalCount);
        Assert.assertEquals(appCount, totalCount, "The tez app count of TezApp is not equal to " +
                "the total count of heading.");
        test.log(LogStatus.PASS, "The left pane has tez check box and the app counts match to that " +
                "displayed in the header");

        applicationsPageObject.expandStatus.click();
        appCount = tezDetailsPage.clickOnlyLink("Success");
        waitExecuter.waitUntilPageFullyLoaded();
        try {
            //Clicking on the Tez app must go to apps detail page
            if (appCount > 0) {
                tezApps.getTypeFromTable.click();
                waitExecuter.waitUntilPageFullyLoaded();
                waitExecuter.sleep(1000);
                waitExecuter.waitUntilElementPresent(tezApps.tagsTab);
                MouseActions.clickOnElement(driver, tezApps.tagsTab);
                waitExecuter.waitUntilPageFullyLoaded();
                String tagValue = tezDetailsPage.validateTagsTab(tezApps);
                test.log(LogStatus.PASS, "Tags tab is populated"+tagValue);
                test.log(LogStatus.PASS, "Verified Tags value populated in the app details page successfully");
                //Close apps details page
            } else {
                test.log(LogStatus.SKIP, "No Tez Application present");
                logger.error("No Tez Application present in the " + clusterId + " cluster for the time span " +
                        "of 90 days");
            }
            MouseActions.clickOnElement(driver, tezApps.closeAppsPageTab);
            waitExecuter.sleep(3000);

        } catch (VerifyError te) {
            throw new AssertionError("No Tez Application present in the \" + clusterId + \" cluster for the time span \" +\n" +
                    " \"of 90 days\"" + te);
        }
    }
}
