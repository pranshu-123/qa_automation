package com.qa.testcases.emr.jobs.applications;

import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.enums.UserAction;
import com.qa.pagefactory.emr.jobs.EmrApplicationsPageObject;
import com.qa.scripts.emr.jobs.EMRAllApps;
import com.qa.utils.LoggingUtils;
import com.qa.utils.WaitExecuter;
import com.qa.utils.actions.UserActions;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@Marker.EMRAllApps
public class TC_EMR_JAL_07 extends BaseClass {
    private final LoggingUtils LOGGER = new LoggingUtils(TC_EMR_JAL_06.class);

    @Test()
    public void verifyEachApplicationTypeFilter() {
        test = extent.startTest("TC_EMR_JAL_07.verifyEachApplicationTypeFilter",
                "Verify that each application type count matches the header count.");
        test.assignCategory("Jobs - Applications");
        test.log(LogStatus.INFO, "Login to the application");
        // Initialize all classes objects
        test.log(LogStatus.INFO, "Initialize all class objects");
        LOGGER.info("Initialize all class objects",test);
        WaitExecuter waitExecuter = new WaitExecuter(driver);
        EmrApplicationsPageObject applicationsPageObject = new EmrApplicationsPageObject(driver);
        UserActions userAction = new UserActions(driver);
        EMRAllApps allApps = new EMRAllApps(driver);

        // Navigate to Jobs tab from header
        test.log(LogStatus.INFO, "Navigate to jobs tab from header");
        test.log(LogStatus.INFO, "Select last 7 days");
        allApps.inJobsSelectLast7Days();
        // To apply filter - De-select all application types
        test.log(LogStatus.INFO, "To apply filter - De-select all application types");
        LOGGER.info("To apply filter - De-select all application types",test);
        allApps.deselectAllAppTypes();
        waitExecuter.sleep(2000);
        // Assert app types are -- "Hive","Map Reduce","Spark","Tez"
        test.log(LogStatus.INFO, "Validate the app types present");
        LOGGER.info("Validate the app types present",test);
        List<String> existingAppTypes = new ArrayList<>(Arrays.asList("Hive", "MapReduce", "Spark", "Tez"));
        List<WebElement> appTypes = applicationsPageObject.getApplicationTypes;
        List<String> listOfAppTypes = new ArrayList<>();
        waitExecuter.sleep(2000);
        for (int i = 0; i < appTypes.size(); i++) {
            listOfAppTypes.add(appTypes.get(i).getText().trim());
        }
        LOGGER.info("Actual list of apps- " + listOfAppTypes,test);
        LOGGER.info("Expected list of apps- " + existingAppTypes,test);
        Assert.assertTrue(listOfAppTypes.containsAll(existingAppTypes),
                "Application types displayed does not match the expected list");
        test.log(LogStatus.PASS, "Application types displayed match the expected list ");
        // Select individual app and assert that table contain its data.
        test.log(LogStatus.INFO, "Select individual app and assert that table contain its data");
        LOGGER.info("Select individual app and assert that table contain its data",test);
        List<WebElement> clickOnIndividualApp = applicationsPageObject.selectOneApplicationType;
        waitExecuter.sleep(2000);
        // Get Job count of selected App
        test.log(LogStatus.INFO, "Get job count of selected app");
        LOGGER.info("Get job count of selected app",test);
        List<WebElement> appJobCounts = applicationsPageObject.getEachApplicationTypeJobCounts;
        waitExecuter.sleep(3000);
        for (int i = 0; i < clickOnIndividualApp.size(); i++) {
            waitExecuter.waitUntilElementClickable(clickOnIndividualApp.get(i));
            userAction.performActionWithPolling(clickOnIndividualApp.get(i), UserAction.CLICK);
            waitExecuter.waitUntilElementClickable(applicationsPageObject.resetButton);

            int appCount = Integer.parseInt(appJobCounts.get(i).getText().replaceAll("[^\\dA-Za-z ]", "").trim());
            waitExecuter.sleep(2000);
            applicationsPageObject.verifyApplicationType.click();
            waitExecuter.sleep(2000);
            int totalCount = Integer
                    .parseInt(applicationsPageObject.getTotalAppCount.getText().replaceAll("[^\\dA-Za-z ]", "").trim());
            waitExecuter.sleep(2000);
            Assert.assertEquals(appCount, totalCount, "The app count of " + appTypes.get(i).getText().trim()
                    + " is not equal to the total count of heading.");
            test.log(LogStatus.PASS, "The app count of is equal to the total count of heading.");
            if (appCount > 0) {
                String getAppTypeFromTable = applicationsPageObject.getTypeFromTable.getText();
                waitExecuter.sleep(2000);
                if (appTypes.get(i).getText().trim().equalsIgnoreCase("map reduce")) {
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
                        "The clusterId does not have any application under it and also does not display 'No Data Available' for it");
            waitExecuter.waitUntilElementClickable(clickOnIndividualApp.get(i));
            userAction.performActionWithPolling(clickOnIndividualApp.get(i), UserAction.CLICK);
            waitExecuter.waitUntilElementClickable(applicationsPageObject.resetButton);
        }
        // Click on show-all to view all type of applications
        test.log(LogStatus.INFO, "Click on show-all to reset the selection of app");
        LOGGER.info("Click on show-all to reset the selection of app",test);
        waitExecuter.waitUntilElementClickable(applicationsPageObject.showAll);
        userAction.performActionWithPolling(applicationsPageObject.showAll, UserAction.CLICK);
        waitExecuter.waitUntilElementClickable(applicationsPageObject.resetButton);
    }
}
