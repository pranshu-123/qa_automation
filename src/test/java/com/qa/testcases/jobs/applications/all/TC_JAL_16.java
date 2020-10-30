package com.qa.testcases.jobs.applications.all;

import com.qa.base.BaseClass;
import com.qa.pagefactory.SubTopPanelModulePageObject;
import com.qa.pagefactory.jobs.ApplicationsPageObject;
import com.qa.scripts.DatePicker;
import com.qa.scripts.jobs.applications.AllApps;
import com.qa.utils.WaitExecuter;
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

public class TC_JAL_16 extends BaseClass {
    private static final Logger LOGGER = Logger.getLogger(TC_JAL_16.class.getName());

    @Test(dataProvider = "clusterid-data-provider")
    public void VerifyTagsDropdownOptions(String clusterId) {
        test = extent.startTest("TC_JAL_16.VerifyTagsDropdownOptions",
                "Verify that on expanding tags the option available are 'dept, priority, team, project, real users, dbs and inputTables'");
        test.assignCategory("Jobs - Applications");
        test.log(LogStatus.INFO, "Login to the application");
        // Initialize all classes objects
        test.log(LogStatus.INFO, "Initialize all class objects");
        LOGGER.info("Initialize all class objects");
        WaitExecuter waitExecuter = new WaitExecuter(driver);
        SubTopPanelModulePageObject subTopPanelModulePageObject = new SubTopPanelModulePageObject(driver);
        ApplicationsPageObject applicationsPageObject = new ApplicationsPageObject(driver);
        DatePicker datePicker = new DatePicker(driver);
        AllApps allApps = new AllApps(driver);
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        // Navigate to Jobs tab from header select cluster and clisk on last 7 days
        test.log(LogStatus.INFO, "Navigate to jobs tab from header");
        test.log(LogStatus.INFO, "Select last 7 days");
        test.log(LogStatus.INFO, "Select clusterId : " + clusterId);
        allApps.inJobsSelectClusterAndLast7Days(clusterId);
        // Scroll to view Tags filter
        test.log(LogStatus.INFO, "Scroll to view Tags filter.");
        LOGGER.info("Scroll to view Tags filter.");
        executor.executeScript("arguments[0].scrollIntoView();", applicationsPageObject.userSearchBox);
        waitExecuter.sleep(2000);
        List<String> existingTagTypes = new ArrayList<>(
                Arrays.asList("dept", "project", "realuser", "dbs", "inputtables", "outputtables"));
        List<WebElement> tagTypesFromUI = applicationsPageObject.getTagTypes;
        List<String> tagTypesInString = new ArrayList<>();
        for (int i = 0; i < tagTypesFromUI.size(); i++) {
            tagTypesInString.add(tagTypesFromUI.get(i).getText().trim().toLowerCase());
        }
        Assert.assertTrue(tagTypesInString.equals(existingTagTypes),
                "The elements of tags does not match the expected list " + tagTypesInString);
        test.log(LogStatus.PASS, "The elements of tags match the expected list: " + tagTypesInString);

    }
}
