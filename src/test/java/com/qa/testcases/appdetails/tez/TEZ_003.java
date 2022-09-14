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
import com.qa.utils.WaitExecuter;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;


@Marker.AppDetailsTez
@Marker.EMRTez
@Marker.GCPAppDetailsTez
@Marker.All
public class TEZ_003 extends BaseClass {

    Logger logger = LoggerFactory.getLogger(TEZ_003.class);

    @Test(dataProvider = "clusterid-data-provider")
    public void TEZ_003_verifyTezApplicationInUnravelUI(String clusterId) throws InterruptedException {
        test = extent.startTest("TEZ_003_verifyTezApplicationInUnravelUI: " + clusterId,
                "Verify App type should be tez");
        test.assignCategory(" Apps Details-Tez");
        Log.startTestCase("TEZ_003_verifyKPIsarelistedandhavedata");

        // Initialize all classes objects
        test.log(LogStatus.INFO, "Initialize all class objects");
        logger.info("Initialize all class objects");
        SubTopPanelModulePageObject topPanelComponentPageObject = new SubTopPanelModulePageObject(driver);
        ApplicationsPageObject applicationsPageObject = new ApplicationsPageObject(driver);
        TezAppsDetailsPageObject tezApps = new TezAppsDetailsPageObject(driver);
        WaitExecuter waitExecuter = new WaitExecuter(driver);
        TezAppsDetailsPage tezDetailsPage = new TezAppsDetailsPage(driver);
        DatePicker datePicker = new DatePicker(driver);
        AllApps allApps = new AllApps(driver);

        test.log(LogStatus.INFO, "Navigate to jobs tab from header");
        tezDetailsPage.navigateToJobsTabFromHeader(topPanelComponentPageObject, allApps, datePicker,
                applicationsPageObject, clusterId);
        test.log(LogStatus.INFO, "Verify that the left pane has tez check box and the apps number");

        int appCount = tezDetailsPage.clickOnlyLink("Tez");

        int totalCount = Integer.parseInt(applicationsPageObject.getTotalAppCount.getText().
                replaceAll("[^\\dA-Za-z ]", "").trim());
        logger.info("AppCount is " + appCount + " total count is " + totalCount);
        Assert.assertEquals(appCount, totalCount, "The tez app count of tezApp is not equal to " +
                "the total count of heading.");
        waitExecuter.waitUntilPageFullyLoaded();
        test.log(LogStatus.PASS, "The left pane has tez check box and the app counts match to that " +
                "displayed in the header");

        test.log(LogStatus.INFO, "Assert if in all application Tez application are present");
        logger.info("Assert if in all application TEZ application are present");
        Assert.assertTrue(allApps.getAllApplicationTypes().contains("Tez"),
                "The list of applications does not contains 'Tez' apps");
        test.log(LogStatus.PASS, "Verified that in all application Tez application are present.");


    }


}

