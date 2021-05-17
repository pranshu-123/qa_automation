package com.qa.testcases.jobs.applications.all;

import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.enums.UserAction;
import com.qa.pagefactory.jobs.ApplicationsPageObject;
import com.qa.scripts.jobs.applications.AllApps;
import com.qa.utils.WaitExecuter;
import com.qa.utils.actions.UserActions;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

/*
 * @author - Ojasvi Pandey
 */


@Marker.AllApps
@Marker.All
public class TC_JAL_10 extends BaseClass {
    private static final Logger LOGGER = Logger.getLogger(TC_JAL_10.class.getName());

    @Test(dataProvider = "clusterid-data-provider")
    public void verifyEachApplicationTypeFilter(String clusterId) {
        test = extent.startTest("TC_JAL_10.verifyEachApplicationTypeFilter",
                "Verify that each application type count matches the header count.");
        test.assignCategory("Jobs - Applications");
        test.log(LogStatus.INFO, "Login to the application");
        // Initialize all classes objects
        test.log(LogStatus.INFO, "Initialize all class objects");
        LOGGER.info("Initialize all class objects");
        WaitExecuter waitExecuter = new WaitExecuter(driver);
        ApplicationsPageObject applicationsPageObject = new ApplicationsPageObject(driver);
        UserActions userAction = new UserActions(driver);
        AllApps allApps = new AllApps(driver);

        // Navigate to Jobs tab from header
        test.log(LogStatus.INFO, "Navigate to jobs tab from header");
        test.log(LogStatus.INFO, "Select last 7 days");
        test.log(LogStatus.INFO, "Select clusterId : " + clusterId);
        allApps.inJobsSelectClusterAndLast7Days(clusterId);
        // To apply filter - De-select all application types
        test.log(LogStatus.INFO, "To apply filter - De-select all application types");
        LOGGER.info("To apply filter - De-select all application types");
        allApps.deselectAllAppTypes();
        waitExecuter.sleep(2000);
        // Assert app types are -- "Hive","Impala","Map Reduce","Spark","Tez"
        test.log(LogStatus.INFO, "Validate the app types present");
        LOGGER.info("Validate the app types present");
        List<String> existingAppTypes = new ArrayList<>(Arrays.asList("Hive", "Map Reduce", "Spark", "Tez"));
        List<WebElement> appTypes = applicationsPageObject.getApplicationTypes;
        List<String> listOfAppTypes = new ArrayList<>();
        waitExecuter.sleep(2000);
        for (int i = 0; i < appTypes.size(); i++) {
            listOfAppTypes.add(appTypes.get(i).getText().trim());
        }

        Assert.assertTrue(listOfAppTypes.containsAll(existingAppTypes),
                "Application types displayed does not match the expected list");
        test.log(LogStatus.PASS, "Application types displayed match the expected list ");
        // Select individual app and assert that table contain its data.
        test.log(LogStatus.INFO, "Select individual app and assert that table contain its data");
        LOGGER.info("Select individual app and assert that table contain its data");
        List<WebElement> clickOnIndividualApp = applicationsPageObject.selectOneApplicationType;
        waitExecuter.sleep(2000);
        // Get Job count of selected App
        test.log(LogStatus.INFO, "Get job count of selected app");
        LOGGER.info("Get job count of selected app");
        List<WebElement> appJobCounts = applicationsPageObject.getEachApplicationTypeJobCounts;
        waitExecuter.sleep(2000);
        for (int i = 0; i < clickOnIndividualApp.size(); i++) {
            waitExecuter.waitUntilElementClickable(clickOnIndividualApp.get(i));
            userAction.performActionWithPolling(clickOnIndividualApp.get(i), UserAction.CLICK);
            waitExecuter.waitUntilElementClickable(applicationsPageObject.resetButton);
            int appCount = Integer.parseInt(appJobCounts.get(0).getText().replaceAll("[^\\dA-Za-z ]", "").trim());
            waitExecuter.sleep(1000);
            int totalCount = Integer
                    .parseInt(applicationsPageObject.getTotalAppCount.getText().replaceAll("[^\\dA-Za-z ]", "").trim());
            waitExecuter.sleep(1000);
            Assert.assertEquals(appCount, totalCount, "The app count of " + appTypes.get(i).getText().trim()
                    + " is not equal to the total count of heading.");
            test.log(LogStatus.PASS, "The app count of is equal to the total count of heading.");
            if (appCount > 0) {
                String getAppTypeFromTable = applicationsPageObject.getTypeFromTable.getText();
                waitExecuter.sleep(2000);
                if (appTypes.get(i).getText().trim().toLowerCase().equals("map reduce")) {
                    Assert.assertEquals(getAppTypeFromTable.toLowerCase(), "mr",
                            "The Jobs displayed in tables contains application other than that of MR App Type");
                    test.log(LogStatus.PASS, "The Jobs displayed in table matches application of MR App Type");
                } else
                    Assert.assertEquals(getAppTypeFromTable.toLowerCase(),
                            appTypes.get(i).getText().trim().toLowerCase(),
                            "The Jobs displayed in tables contains application other than that of selected App Type");
                waitExecuter.sleep(1000);
            } else
                Assert.assertTrue(applicationsPageObject.whenNoApplicationPresent.isDisplayed(),
                        "The clusterId does not have any application under it and also does not display 'No Data Available' for it"
                                + clusterId);
            waitExecuter.waitUntilElementClickable(clickOnIndividualApp.get(i));
            userAction.performActionWithPolling(clickOnIndividualApp.get(i), UserAction.CLICK);
            waitExecuter.waitUntilElementClickable(applicationsPageObject.resetButton);
        }
        // Click on show-all to view all type of applications
        test.log(LogStatus.INFO, "Click on show-all to reset the selection of app");
        LOGGER.info("Click on show-all to reset the selection of app");
        waitExecuter.waitUntilElementClickable(applicationsPageObject.showAll);
        userAction.performActionWithPolling(applicationsPageObject.showAll, UserAction.CLICK);
        waitExecuter.waitUntilElementClickable(applicationsPageObject.resetButton);
    }
}
