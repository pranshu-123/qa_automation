package com.qa.testcases.jobs.applications.all;

import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.enums.UserAction;
import com.qa.pagefactory.jobs.ApplicationsPageObject;
import com.qa.scripts.jobs.applications.AllApps;
import com.qa.utils.WaitExecuter;
import com.qa.utils.actions.UserActions;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.JavascriptExecutor;
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
@Marker.GCPAllApps
@Marker.All
public class TC_JAL_16 extends BaseClass {
    private static final Logger LOGGER = Logger.getLogger(TC_JAL_16.class.getName());

    @Test(dataProvider = "clusterid-data-provider")
    public void VerifyTagNames(String clusterId) {
        test = extent.startTest("TC_JAL_16.VerifyTagNames",
                "Verify that on expanding tags the option available are 'dept, priority, team, project, real users, dbs and inputTables'");
        test.assignCategory("Jobs - Applications");
        test.log(LogStatus.INFO, "Login to the application");
        // Initialize all classes objects
        test.log(LogStatus.INFO, "Initialize all class objects");
        LOGGER.info("Initialize all class objects");
        WaitExecuter waitExecuter = new WaitExecuter(driver);
        ApplicationsPageObject applicationsPageObject = new ApplicationsPageObject(driver);
        AllApps allApps = new AllApps(driver);
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        UserActions userActions = new UserActions(driver);
        // Navigate to Jobs tab from header select cluster and click on last 7 days
        test.log(LogStatus.INFO, "Navigate to jobs tab from header");
        test.log(LogStatus.INFO, "Select last 7 days");
        test.log(LogStatus.INFO, "Select clusterId : " + clusterId);
        allApps.inJobsSelectClusterAndLast7Days(clusterId);
        // Scroll to view Tags filter
        test.log(LogStatus.INFO, "Scroll to view Tags filter.");
        LOGGER.info("Scroll to view Tags filter.");
        executor.executeScript("arguments[0].scrollIntoView();", applicationsPageObject.userSearchBox);
        waitExecuter.waitUntilElementClickable(applicationsPageObject.tagExpandableHeader);
        userActions.performActionWithPolling(applicationsPageObject.tagExpandableHeader, UserAction.CLICK);
        waitExecuter.waitUntilElementClickable(applicationsPageObject.tagExpandableHeader);
        List<String> expectedTagTypes = new ArrayList<>(
                Arrays.asList("dept", "project", "realuser", "dbs", "inputtables", "outputtables"));

        List<WebElement> tagTypesFromUI = applicationsPageObject.getTagTypes;
        List<String> tagTypesInString = new ArrayList<>();
        for (int i = 0; i < tagTypesFromUI.size(); i++) {
            tagTypesInString.add(tagTypesFromUI.get(i).getText().trim().toLowerCase());
        }
        test.log(LogStatus.INFO, "Expected tags- " + expectedTagTypes);
        LOGGER.info("Expected tags- " + expectedTagTypes);
        test.log(LogStatus.INFO, "Actual tags- " + tagTypesInString);
        LOGGER.info("Actual tags- " + tagTypesInString);
        Assert.assertTrue(tagTypesInString.retainAll(expectedTagTypes),
                "The elements of tags does not match the expected list- " + tagTypesInString);
        test.log(LogStatus.PASS, "The elements of tags match the expected list: " + tagTypesInString);
        executor.executeScript("arguments[0].scrollIntoView();", applicationsPageObject.resetButton);
        waitExecuter.waitUntilElementClickable(applicationsPageObject.resetButton);
        userActions.performActionWithPolling(applicationsPageObject.resetButton, UserAction.CLICK);
        waitExecuter.waitUntilElementClickable(applicationsPageObject.resetButton);
    }
}
