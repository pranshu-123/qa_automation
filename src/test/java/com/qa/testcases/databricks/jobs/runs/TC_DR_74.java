package com.qa.testcases.databricks.jobs.runs;

import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.pagefactory.databricks.DbxSubTopPanelModulePageObject;
import com.qa.pagefactory.databricks.jobs.DbxApplicationsPageObject;
import com.qa.scripts.databricks.jobs.DbAllApps;
import com.qa.utils.LoggingUtils;
import com.qa.utils.WaitExecuter;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;
@Marker.DbxRuns
@Marker.All
public class TC_DR_74 extends BaseClass {
    private final LoggingUtils loggingUtils = new LoggingUtils(TC_DR_74.class);

    @Test()
    public void validateGlobalSearch() {
        test = extent.startTest("TC_DR_74.validateGlobalSearch",
                "Verify that global search is able to search by application type, workspace name, user name or app Id");
        test.assignCategory("Jobs-Runs/Running");
        test.log(LogStatus.INFO, "Login to the application");
        // Initialize all classes objects
        test.log(LogStatus.INFO, "Initialize all class objects");
        loggingUtils.info("Initialize all class objects", test);
        WaitExecuter waitExecuter = new WaitExecuter(driver);
        DbxApplicationsPageObject applicationsPageObject = new DbxApplicationsPageObject(driver);
        DbxSubTopPanelModulePageObject dbpageObject = new DbxSubTopPanelModulePageObject(driver);
        DbAllApps dballApps = new DbAllApps(driver);
        // Navigate to Runs tab from header
        test.log(LogStatus.INFO, "Navigate to Runs tab from header");
        dballApps.navigateToJobsTab("Runs");
        dballApps.selectTab("Running");
        try {
            int totalCount = Integer
                    .parseInt(dbpageObject.getTotalAppCount.getText().replaceAll("[^\\dA-Za-z ]", "").trim());
            test.log(LogStatus.INFO, "Assert if the application listed are as per the filter applied on global search");
            loggingUtils.info("Assert if the application are as per the filter applied on global search", test);
            if (totalCount > 0) {
                test.log(LogStatus.INFO, "Get application type of first application listed in table");
                loggingUtils.info("Get application type of first application listed in table", test);
                String userTypeToSearch = applicationsPageObject.getUserFromTable.getText();
                applicationsPageObject.globalSearchBox.clear();
                waitExecuter.sleep(1000);
                applicationsPageObject.globalSearchBox.sendKeys(userTypeToSearch);
                waitExecuter.sleep(1000);
                applicationsPageObject.globalSearchBox.sendKeys(Keys.RETURN);
                waitExecuter.sleep(3000);
                List<WebElement> tableData = applicationsPageObject.getTableData;
                Assert.assertTrue(tableData.size() > 0,
                        "Table does not contain data according to the User type " + userTypeToSearch);
                test.log(LogStatus.INFO, "Get username of first User listed in table and search ");
                applicationsPageObject.globalSearchBox.clear();
                loggingUtils.info("Get username of first application listed in table and search",test);
                waitExecuter.sleep(1000);
                applicationsPageObject.globalSearchBox.clear();
                waitExecuter.sleep(1000);
                String usernameToSearch = applicationsPageObject.getNamesFromDropDown.get(0).getText();
                waitExecuter.sleep(1000);
                applicationsPageObject.globalSearchBox.sendKeys(usernameToSearch);
                waitExecuter.sleep(1000);
                applicationsPageObject.globalSearchBox.sendKeys(Keys.RETURN);
                waitExecuter.sleep(3000);
                Assert.assertTrue(tableData.size() > 0,
                        "Table does not contain data according to the Username " + usernameToSearch);
                test.log(LogStatus.INFO, "Get Queue name of first application listed in table and search");
                loggingUtils.info("Get application Queue name of first application listed in table and search", test);
                waitExecuter.sleep(1000);
                applicationsPageObject.globalSearchBox.clear();
                String workspacenameToSearch = applicationsPageObject.getWorkspaceNameTable.getText();
                waitExecuter.sleep(1000);
                applicationsPageObject.globalSearchBox.sendKeys(workspacenameToSearch);
                waitExecuter.sleep(1000);
                applicationsPageObject.globalSearchBox.sendKeys(Keys.RETURN);
                waitExecuter.sleep(3000);
                Assert.assertTrue(tableData.size() > 0,
                        "Table does not contain data according to the Workspace name type " + workspacenameToSearch);
                test.log(LogStatus.PASS, "Table contain data according to the Workspace name type ");
            } else
                Assert.assertTrue(applicationsPageObject.whenNoApplicationPresent.isDisplayed(),
                        "The application display 'No Data Available'");

        } catch (NoSuchElementException ex) {
            loggingUtils.error("No Data picker present by this name", test);
            loggingUtils.error("Error- " + ex, test);
        }
    }
}
