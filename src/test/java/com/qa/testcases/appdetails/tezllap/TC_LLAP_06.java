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
public class TC_LLAP_06 extends BaseClass {

    Logger logger = LoggerFactory.getLogger(TC_LLAP_06.class);

    @Test(dataProvider = "clusterid-data-provider")
    public void TC_LLAP_06_verifyStatusKilled(String clusterId) {
        test = extent.startTest("TC_LLAP_06_verifyStatusKilled: " + clusterId,
                "Verify the application \"Status\" displayed in the Application Tab should be - \"Killed\"");
        test.assignCategory(" Apps Details-TezLlap");
        Log.startTestCase("TC_LLAP_06_verifyStatusKilled");

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

        int appCount=tezLlapApps.clickOnlyLink("Tez");
        applicationsPageObject.expandStatus.click();
        int failedCount = tezLlapApps.clickOnlyLink("Killed");
        test.log(LogStatus.PASS, "Selected "+ appCount + " as option in Group By filter, yarn chargeback page");
        int totalCount = Integer.parseInt(applicationsPageObject.getTotalAppCount.getText().
                replaceAll("[^\\dA-Za-z ]", "").trim());
        logger.info("AppCount is " + appCount + " total count is " + totalCount);

        Assert.assertEquals(appCount, totalCount, "The tez app count of tezApp is not equal to " +
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
         * Validate that status types  success are --
         */
        if (appCount > 0) {
            String getStatusTypeFromTable = tezLlapPage.getStatusFromTable.getText();
            Assert.assertEquals(getStatusTypeFromTable.toLowerCase(),
                    "The Jobs displayed in tables contains application other than that of selected App Type");
            test.log(LogStatus.PASS, "The Jobs displayed in tables contains application of selected App Type");
        } else {
            Assert.assertTrue(tezLlapPage.whenNoApplicationPresent.isDisplayed(),
                    "The cluster does not have any application under it and also does not display 'No Data Available' for it");
        }
    }
}

