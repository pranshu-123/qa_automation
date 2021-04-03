package com.qa.testcases.appdetails.tez;

import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.pagefactory.SubTopPanelModulePageObject;
import com.qa.pagefactory.appsDetailsPage.TezAppsDetailsPageObject;
import com.qa.pagefactory.appsDetailsPage.TezLlapAppsDetailsPageObject;
import com.qa.pagefactory.jobs.ApplicationsPageObject;
import com.qa.scripts.DatePicker;
import com.qa.scripts.appdetails.TezAppsDetailsPage;
import com.qa.scripts.appdetails.TezLlapAppsDetailsPage;
import com.qa.scripts.jobs.applications.AllApps;
import com.qa.utils.Log;
import com.qa.utils.MouseActions;
import com.qa.utils.WaitExecuter;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.interactions.Actions;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.logging.Logger;

@Marker.AppDetailsTez
@Marker.All
public class TEZ_133 extends BaseClass {

    private static final java.util.logging.Logger LOGGER = Logger.getLogger(TEZ_133.class.getName());

    @Test(dataProvider = "clusterid-data-provider")
    public void TEZ_133_verifyDatabase(String clusterId) {
        test = extent.startTest("TEZ_133_verifyDatabase: " + clusterId,
                "Verify Name of the database must be listed in the UI");
        test.assignCategory(" Apps Details-Tez");
        // Initialize all classes objects
        test.log(LogStatus.INFO, "Initialize all class objects");
        LOGGER.info("Initialize all class objects");
        WaitExecuter waitExecuter = new WaitExecuter(driver);
        SubTopPanelModulePageObject topPanelComponentPageObject = new SubTopPanelModulePageObject(driver);
        ApplicationsPageObject applicationsPageObject = new ApplicationsPageObject(driver);
        TezAppsDetailsPageObject tezApps = new TezAppsDetailsPageObject(driver);
        TezAppsDetailsPage tezDetailsPage = new TezAppsDetailsPage(driver);
        DatePicker datePicker = new DatePicker(driver);
        Actions actions = new Actions(driver);
        AllApps allApps = new AllApps(driver);

        test.log(LogStatus.INFO, "Navigate to jobs tab from header");
        tezDetailsPage.navigateToJobsTabFromHeader(topPanelComponentPageObject, allApps, datePicker,
                applicationsPageObject, clusterId);
        test.log(LogStatus.INFO, "Verify that the left pane has Hive check box and the apps number");
        int appCount = tezDetailsPage.clickOnlyLink("Tez");
        int totalCount = Integer.parseInt(applicationsPageObject.getTotalAppCount.getText().
                replaceAll("[^\\dA-Za-z ]", "").trim());
        LOGGER.info("AppCount is " + appCount + " total count is " + totalCount);
        Assert.assertEquals(appCount, totalCount, "The Hive app count of tezllapp is not equal to " +
                "the total count of heading.");
        test.log(LogStatus.PASS, "The left pane has Hive check box and the app counts match to that " +
                "displayed in the header");

        applicationsPageObject.expandStatus.click();
        int statusCount = tezDetailsPage.clickOnlyLink("Success");
        test.log(LogStatus.PASS, "Selected success Count is  " + statusCount + " as Status, In Applications page");
        waitExecuter.waitUntilPageFullyLoaded();
        applicationsPageObject.expandQueue.click();
        waitExecuter.waitUntilPageFullyLoaded();


        /*
         * Validate the username types are --
         */
        if (appCount > 0) {
            String Appname = tezDetailsPage.verifyDbname(tezApps);
            test.log(LogStatus.PASS, "Tez Database is displayed in the Header: " + Appname);

        } else {
            test.log(LogStatus.SKIP, "No Tez Application present");
            LOGGER.severe("No Tez Application present in the " + clusterId + " cluster for the time span " +
                    "of 90 days");
            //Close apps details page
            MouseActions.clickOnElement(driver, tezApps.homeTab);
        }
    }
}

