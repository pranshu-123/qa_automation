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
import java.util.HashMap;
import java.util.List;
@Marker.EMRAllApps
public class TC_EMR_JAL_13 extends BaseClass {
    private final LoggingUtils LOGGER = new LoggingUtils(TC_EMR_JAL_13.class);

    @Test()
    public void VerifyTagsDropdownOptions() {
        test = extent.startTest("TC_EMR_JAL_13.VerifyTagsDropdownOptions",
                "Verify that on expanding tags the option available are 'dept, project, team, queue, job Type and Db'");
        test.assignCategory("Jobs - Applications");
        test.log(LogStatus.INFO, "Login to the application");
        // Initialize all classes objects
        test.log(LogStatus.INFO, "Initialize all class objects");
        LOGGER.info("Initialize all class objects",test);
        WaitExecuter waitExecuter = new WaitExecuter(driver);
        EmrApplicationsPageObject applicationsPageObject = new EmrApplicationsPageObject(driver);
        EMRAllApps allApps = new EMRAllApps(driver);
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        UserActions userActions = new UserActions(driver);
        // Navigate to Jobs tab from header select cluster and clisk on last 7 days
        test.log(LogStatus.INFO, "Navigate to jobs tab from header");
        test.log(LogStatus.INFO, "Select last 7 days");
        allApps.inJobsSelectLast7Days();
        executor.executeScript("arguments[0].scrollIntoView();", applicationsPageObject.userSearchBox);
        waitExecuter.waitUntilElementClickable(applicationsPageObject.tagExpandableHeader);
        userActions.performActionWithPolling(applicationsPageObject.tagExpandableHeader, UserAction.CLICK);
        waitExecuter.waitUntilElementClickable(applicationsPageObject.tagExpandableHeader);
        List<WebElement> checkBoxTags = applicationsPageObject.tagsCheckboxes;
        List<WebElement> tagsType = applicationsPageObject.getTagTypes;
        List<String> selectedTagType = new ArrayList<String>();
        List<String> selectedFilterUnderTag = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            waitExecuter.waitUntilElementClickable(checkBoxTags.get(i));
            userActions.performActionWithPolling(checkBoxTags.get(i), UserAction.CLICK);
            waitExecuter.waitUntilElementClickable(checkBoxTags.get(i));
            selectedTagType.add(tagsType.get(i).getText().trim().toLowerCase());
            LOGGER.info(
                    "Selected type- " + tagsType.get(i).getText().trim().toLowerCase(),test);
            waitExecuter.waitUntilElementClickable(applicationsPageObject.tagTypeSearchbox.get(i));
            userActions.performActionWithPolling(applicationsPageObject.tagTypeSearchbox.get(i), UserAction.CLICK);
            waitExecuter.waitUntilElementClickable(applicationsPageObject.tagExpandableHeader);
            selectedFilterUnderTag
                    .add(applicationsPageObject.select1stOptionInTagsSearchBox.get(i).getText().trim().toLowerCase());
            LOGGER.info("Name of selected tag- "
                    + applicationsPageObject.select1stOptionInTagsSearchBox.get(i).getText().trim().toLowerCase(),test);

            waitExecuter.waitUntilElementClickable(applicationsPageObject.select1stOptionInTagsSearchBox.get(i));
            userActions.performActionWithPolling(applicationsPageObject.select1stOptionInTagsSearchBox.get(i), UserAction.CLICK);
            waitExecuter.waitUntilElementClickable(applicationsPageObject.tagExpandableHeader);
        }
        // Click on the first app in table to get efficiency
        test.log(LogStatus.INFO, "Click on the first app in table to get efficiency");
        LOGGER.info("Click on the first app in table to get efficiency",test);
        waitExecuter.waitUntilElementClickable(applicationsPageObject.clickOnAppId);
        userActions.performActionWithPolling(applicationsPageObject.clickOnAppId, UserAction.CLICK);
        waitExecuter.waitUntilElementClickable(applicationsPageObject.closeIcon);

        driver.getWindowHandle();
        waitExecuter.waitUntilElementClickable(applicationsPageObject.tagsTab);
        userActions.performActionWithPolling(applicationsPageObject.tagsTab, UserAction.CLICK);
        waitExecuter.waitUntilElementClickable(applicationsPageObject.closeIcon);
        List<WebElement> getTagNamesFromTagTable = applicationsPageObject.tagsInTable;
        List<WebElement> getTagsDescriptionFromTagTable = applicationsPageObject.descriptionInTable;
        HashMap<String, String> map = new HashMap<>();
        for (int j = 0; j < getTagNamesFromTagTable.size(); j++) {
            map.put(getTagNamesFromTagTable.get(j).getText().trim().toLowerCase(),
                    getTagsDescriptionFromTagTable.get(j).getText().trim().toLowerCase());
        }
        waitExecuter.sleep(1000);

        waitExecuter.waitUntilElementClickable(applicationsPageObject.closeIcon);
        userActions.performActionWithPolling(applicationsPageObject.closeIcon, UserAction.CLICK);
        waitExecuter.waitUntilElementClickable(applicationsPageObject.resetButton);
        for (String tag : selectedTagType) {
            Assert.assertTrue(map.containsKey(tag),
                    "Table does not contain the tag selected " + map.keySet() + " ---" + selectedTagType);
            test.log(LogStatus.PASS, "Table contains the tag selected " + selectedTagType);
        }
        for (String tagFilter : selectedFilterUnderTag) {
            Assert.assertTrue(map.containsValue(tagFilter),
                    "Table does not contain the tag filter selected " + map.values());
            test.log(LogStatus.PASS, "Table contain the tag filter selected " + map.values());
        }
        executor.executeScript("arguments[0].scrollIntoView();", applicationsPageObject.resetButton);
        waitExecuter.waitUntilElementClickable(applicationsPageObject.resetButton);
        userActions.performActionWithPolling(applicationsPageObject.resetButton, UserAction.CLICK);
        waitExecuter.waitUntilElementClickable(applicationsPageObject.resetButton);
    }
}
