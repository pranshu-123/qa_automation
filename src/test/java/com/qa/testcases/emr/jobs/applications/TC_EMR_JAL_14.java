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
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
@Marker.EMRAllApps
public class TC_EMR_JAL_14 extends BaseClass {
    private final LoggingUtils LOGGER = new LoggingUtils(TC_EMR_JAL_14.class);
    @Test()
    public void VerifyTagNames() {
        test = extent.startTest("TC_EMR_JAL_14.VerifyTagNames", "Verify that on expanding tags the option available are 'dept, project, team, queue, job Type and Db'");
        test.assignCategory("Jobs - Applications");
        test.log(LogStatus.INFO, "Login to the application");
        // Initialize all classes objects
        test.log(LogStatus.INFO, "Initialize all class objects");
        LOGGER.info("Initialize all class objects", test);
        WaitExecuter waitExecuter = new WaitExecuter(driver);
        EmrApplicationsPageObject applicationsPageObject = new EmrApplicationsPageObject(driver);
        EMRAllApps allApps = new EMRAllApps(driver);
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        UserActions userActions = new UserActions(driver);
        // Navigate to Jobs tab from header select cluster and click on last 7 days
        test.log(LogStatus.INFO, "Navigate to jobs tab from header");
        test.log(LogStatus.INFO, "Select last 7 days");
        allApps.inJobsSelectLast7Days();
        // Scroll to view Tags filter
        test.log(LogStatus.INFO, "Scroll to view Tags filter.");
        LOGGER.info("Scroll to view Tags filter.", test);
        executor.executeScript("arguments[0].scrollIntoView();", applicationsPageObject.userSearchBox);
        waitExecuter.waitUntilElementClickable(applicationsPageObject.tagExpandableHeader);
        userActions.performActionWithPolling(applicationsPageObject.tagExpandableHeader, UserAction.CLICK);
        waitExecuter.waitUntilElementClickable(applicationsPageObject.tagExpandableHeader);
        List<String> expectedTagTypes = new ArrayList<>
                (Arrays.asList("dept","project", "team", "queue", "job Type","Db"));

        List<WebElement> tagTypesFromUI = applicationsPageObject.getTagTypes;
        List<String> tagTypesInString = new ArrayList<>();
        for (int i = 0; i < tagTypesFromUI.size(); i++) {
            tagTypesInString.add(tagTypesFromUI.get(i).getText().trim().toLowerCase());
        }
        test.log(LogStatus.INFO, "Expected tags- " + expectedTagTypes);
        LOGGER.info("Expected tags- " + expectedTagTypes, test);
        test.log(LogStatus.INFO, "Actual tags- " + tagTypesInString);
        LOGGER.info("Actual tags- " + tagTypesInString, test);
        Assert.assertTrue(tagTypesInString.retainAll(expectedTagTypes),
                "The elements of tags does not match the expected list- " + tagTypesInString);
        test.log(LogStatus.PASS, "The elements of tags match the expected list: " + tagTypesInString);
        executor.executeScript("arguments[0].scrollIntoView();", applicationsPageObject.resetButton);
        waitExecuter.waitUntilElementClickable(applicationsPageObject.resetButton);
        userActions.performActionWithPolling(applicationsPageObject.resetButton, UserAction.CLICK);
        waitExecuter.waitUntilElementClickable(applicationsPageObject.resetButton);
    }
}
