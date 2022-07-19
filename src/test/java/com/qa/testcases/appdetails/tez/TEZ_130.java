package com.qa.testcases.appdetails.tez;

import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.pagefactory.SubTopPanelModulePageObject;
import com.qa.pagefactory.appsDetailsPage.TezAppsDetailsPageObject;
import com.qa.pagefactory.jobs.ApplicationsPageObject;
import com.qa.scripts.DatePicker;
import com.qa.scripts.appdetails.TezAppsDetailsPage;
import com.qa.scripts.jobs.applications.AllApps;
import com.qa.utils.MouseActions;
import com.qa.utils.WaitExecuter;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Marker.AppDetailsTez
@Marker.EMRTez
@Marker.All
public class TEZ_130 extends BaseClass {

    Logger logger = LoggerFactory.getLogger(TEZ_130.class);

    @Test(dataProvider = "clusterid-data-provider")
    public void TEZ_130_verifytheTablesTab(String clusterId) {
        test = extent.startTest("TEZ_130_verifytheTablesTab: " + clusterId,
                "Verify Tables accessed by the query must be populated");
        test.assignCategory(" Apps Details-Tez");
        // Initialize all classes objects
        test.log(LogStatus.INFO, "Initialize all class objects");
        logger.info("Initialize all class objects");
        SubTopPanelModulePageObject topPanelComponentPageObject = new SubTopPanelModulePageObject(driver);
        ApplicationsPageObject applicationsPageObject = new ApplicationsPageObject(driver);
        TezAppsDetailsPageObject tezApps = new TezAppsDetailsPageObject(driver);
        TezAppsDetailsPage tezDetailsPage = new TezAppsDetailsPage(driver);
        DatePicker datePicker = new DatePicker(driver);
        WaitExecuter waitExecuter = new WaitExecuter(driver);
        Actions actions = new Actions(driver);
        AllApps allApps = new AllApps(driver);

        test.log(LogStatus.INFO, "Navigate to jobs tab from header");
        tezDetailsPage.navigateToJobsTabFromHeader(topPanelComponentPageObject, allApps, datePicker,
                applicationsPageObject, clusterId);
        test.log(LogStatus.INFO, "Verify that the left pane has tez check box and the apps number");

        test.log(LogStatus.INFO, "Verify that the left pane has tez check box and the apps number");
        int appCount = tezDetailsPage.clickOnlyLink("Tez");

        int totalCount = Integer.parseInt(applicationsPageObject.getTotalAppCount.getText().
                replaceAll("[^\\dA-Za-z ]", "").trim());
        logger.info("AppCount is " + appCount + " total count is " + totalCount);
        Assert.assertEquals(appCount, totalCount, "The tez app count of tezApp is not equal to " +
                "the total count of heading.");
        test.log(LogStatus.PASS, "The left pane has tez check box and the app counts match to that " +
                "displayed in the header");
        try {
            if (appCount > 0) {
                // Get MR app id from the first row
                test.log(LogStatus.INFO, "Get MR app id from the first row");
                logger.info("Get MR app id from the first row");
                WebElement name = applicationsPageObject.getAppNameFromTable;
                actions.moveToElement(name).perform();
                waitExecuter.sleep(1000);
                actions.moveToElement(applicationsPageObject.copyAppName).perform();
                waitExecuter.sleep(1000);
                applicationsPageObject.copyAppName.click();
                List<String> QueryTabs = Arrays.asList("query");
                List<String> actualTabs = new ArrayList<>();

                // Click on first app in table to navigate to app details page
                test.log(LogStatus.INFO, "Click on first app in table to navigate to app details page");
                logger.info("Click on first app in table to navigate to app details page");
                waitExecuter.waitUntilElementClickable(applicationsPageObject.clickOnAppId);
                applicationsPageObject.clickOnAppId.click();
                waitExecuter.waitUntilPageFullyLoaded();
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

