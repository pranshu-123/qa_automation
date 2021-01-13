package com.qa.testcases.appdetails.tezllap;

import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.constants.PageConstants;
import com.qa.pagefactory.SubTopPanelModulePageObject;
import com.qa.pagefactory.appsDetailsPage.TezLlapAppsDetailsPageObject;
import com.qa.pagefactory.jobs.ApplicationsPageObject;
import com.qa.scripts.DatePicker;
import com.qa.scripts.appdetails.TezLlapAppsDetailsPage;
import com.qa.scripts.jobs.applications.AllApps;
import com.qa.utils.Log;
import com.qa.utils.MouseActions;
import com.qa.utils.WaitExecuter;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

@Marker.AppDetailsTezLlap
@Marker.All
public class TC_LLAP_02 extends BaseClass {

    Logger logger = LoggerFactory.getLogger(TC_LLAP_02.class);

    @Test(dataProvider = "clusterid-data-provider")
    public void TC_LLAP_02_verifyStarttimeAndDuration(String clusterId) {
        test = extent.startTest("TC_LLAP_02_verifyStarttimeAndDuration: " + clusterId,
                "Verify Start time and duration. must be available for all the apps.");
        test.assignCategory("Apps Details-TezLlap");
        Log.startTestCase("TC_LLAP_02_verifyStarttimeAndDuration");

        // Initialize all classes objects
        test.log(LogStatus.INFO, "Initialize all class objects");
        logger.info("Initialize all class objects");
        SubTopPanelModulePageObject topPanelComponentPageObject = new SubTopPanelModulePageObject(driver);
        ApplicationsPageObject applicationsPageObject = new ApplicationsPageObject(driver);
        TezLlapAppsDetailsPageObject tezLlapPage = new TezLlapAppsDetailsPageObject(driver);
        TezLlapAppsDetailsPage tezLlapApps = new TezLlapAppsDetailsPage(driver);
        WaitExecuter waitExecuter = new WaitExecuter(driver);
        DatePicker datePicker = new DatePicker(driver);
        AllApps allApps = new AllApps(driver);

        test.log(LogStatus.INFO, "Navigate to jobs tab from header");
        tezLlapApps.navigateToJobsTabFromHeader(topPanelComponentPageObject, allApps, datePicker,
                applicationsPageObject, clusterId);
        test.log(LogStatus.INFO, "Verify that the left pane has tez check box and the apps number");

        int appCount = tezLlapApps.clickOnlyLink("Tez");

        int totalCount = Integer.parseInt(applicationsPageObject.getTotalAppCount.getText().
                replaceAll("[^\\dA-Za-z ]", "").trim());
        logger.info("AppCount is " + appCount + " total count is " + totalCount);
        Assert.assertEquals(appCount, totalCount, "The tez app count of TezApp is not equal to " +
                "the total count of heading.");
        test.log(LogStatus.PASS, "The left pane has tez check box and the app counts match to that " +
                "displayed in the header");

        List<WebElement> typesInPage = tezLlapPage.getTypesColumnFromTable;
        List<String> nameOfTypesInPage = new ArrayList<>();
        int tableData = tezLlapPage.getTableData.size();
        if (tableData > 0) {
            for (int i = 0; i < typesInPage.size(); i++) {
                nameOfTypesInPage.add(typesInPage.get(i).getText().trim().toLowerCase());
            }
            waitExecuter.waitUntilPageFullyLoaded();
        }
        // listed
        Assert.assertTrue(nameOfTypesInPage.contains(PageConstants.AppQueue.LLAP), "Table does not contain app Queue 'llap'.");
        test.log(LogStatus.PASS, "Table contains app Queue 'llap'.");

        /*
         * Validate the start time types are --
         */
        if (appCount > 0) {
            String starttime = tezLlapApps.verifyStartTime(tezLlapPage);
            test.log(LogStatus.PASS, "Start time is displayed in the Tez Table: " + starttime);

            String duration = tezLlapApps.verifyDuration(tezLlapPage);
            test.log(LogStatus.PASS, "Duration is displayed in the Tez Table: " + duration);

        } else {
            test.log(LogStatus.SKIP, "No Tez Application present");
            logger.error("No Tez Application present in the " + clusterId + " cluster for the time span " +
                    "of 90 days");
            //Close apps details page
            MouseActions.clickOnElement(driver, tezLlapPage.homeTab);

        }

    }
}
