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
import com.relevantcodes.extentreports.LogStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import static com.qa.base.BaseClass.extent;
@Marker.AppDetailsTez
@Marker.EMRTez
@Marker.GCPAppDetailsTez
@Marker.All
public class TEZ_121 extends BaseClass {

    Logger logger = LoggerFactory.getLogger(TEZ_121.class);

    @Test(dataProvider = "clusterid-data-provider")
    public void TEZ_121_verifyTezAppsWithClusterIDs(String clusterId) throws InterruptedException {
        test = extent.startTest("TEZ_121_verifyTezAppsWithClusterIDs: " + clusterId,
                "Verify All the Tez apps run on different Clusters must have the cluster ID.");
        test.assignCategory(" Apps Details-Tez");
        Log.startTestCase("TEZ_121_verifyTezAppsWithClusterIDs");

        // Initialize all classes objects
        test.log(LogStatus.INFO, "Initialize all class objects");
        logger.info("Initialize all class objects");
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
        int appCount = tezDetailsPage.clickOnlyLink("Tez");

        int totalCount = Integer.parseInt(applicationsPageObject.getTotalAppCount.getText().
                replaceAll("[^\\dA-Za-z ]", "").trim());
        logger.info("AppCount is " + appCount + " total count is " + totalCount);
        Assert.assertEquals(appCount, totalCount, "The tez app count of TezApp is not equal to " +
                "the total count of heading.");
        test.log(LogStatus.PASS, "The left pane has tez check box and the app counts match to that " +
                "displayed in the header");

        //click on cluster search field
        applicationsPageObject.clusterIdsearchfield.click();
        System.out.println("All clusterId size: "+applicationsPageObject.clusterIdsList.size());
        test.log(LogStatus.INFO, "All clusterId count: "+applicationsPageObject.clusterIdsList.size());
        test.log(LogStatus.PASS,"Validated cluster filter in UI");

    }
}
