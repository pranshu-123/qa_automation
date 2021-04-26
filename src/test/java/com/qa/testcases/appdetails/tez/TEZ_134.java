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
public class TEZ_134 extends BaseClass {

    Logger logger = LoggerFactory.getLogger(TEZ_134.class);

    @Test(dataProvider = "clusterid-data-provider")
    public void TEZ_134_verifyLeftPane(String clusterId) {
        test = extent.startTest("TEZ_134_verifyLeftPane: " + clusterId,
                "Verify left pane in the app details page");
        test.assignCategory(" Apps Details-Tez");
        Log.startTestCase("TEZ_134_verifyLeftPane");
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
        waitExecuter.waitUntilElementClickable(applicationsPageObject.resetButton);

        test.log(LogStatus.INFO, "Navigate to jobs tab from header");
        tezDetailsPage.navigateToJobsTabFromHeader(topPanelComponentPageObject, allApps, datePicker,
                applicationsPageObject, clusterId);
        test.log(LogStatus.INFO, "Verify that the left pane has Hive check box and the apps number");
        int appCount = tezDetailsPage.clickOnlyLink("Tez");
        waitExecuter.sleep(2000);
        int totalCount = Integer.parseInt(applicationsPageObject.getTotalAppCount.getText().
                replaceAll("[^\\dA-Za-z ]", "").trim());
        logger.info("AppCount is " + appCount + " total count is " + totalCount);
        Assert.assertEquals(appCount, totalCount, "The Hive app count of tezllapp is not equal to " +
                "the total count of heading.");
        test.log(LogStatus.PASS, "The left pane has Hive check box and the app counts match to that " +
                "displayed in the header");
        tezApps.sortByReadApp.click();
        waitExecuter.waitUntilPageFullyLoaded();
        tezApps.sortUp.click();
        waitExecuter.sleep(2000);
        applicationsPageObject.expandStatus.click();
        int statusCount = tezDetailsPage.clickOnlyLink("Success");
        test.log(LogStatus.PASS, "Selected success Count is  " + statusCount + " as Status, In Applications page");
        waitExecuter.waitUntilPageFullyLoaded();
        //Clicking on the Tez app must go to apps detail page
        if (appCount > 0) {
            tezApps.getTypeFromTable.click();
            waitExecuter.waitUntilPageFullyLoaded();
            tezDetailsPage.verifyDagsComponent(tezApps, true, false, false);
            waitExecuter.sleep(2000);
            test.log(LogStatus.PASS, "Verified left pane in the app details page successfully");
            //Close apps details page
            MouseActions.clickOnElement(driver, tezApps.closeAppsPageTab);
            waitExecuter.sleep(3000);
        } else {
            test.log(LogStatus.SKIP, "No Tez Application present");
            logger.error("No Tez Application present in the " + clusterId + " cluster for the time span " +
                    "of 90 days");
        }
        waitExecuter.sleep(3000);
        MouseActions.clickOnElement(driver, tezApps.homeTab);
    }
}
