package com.qa.testcases.emr.jobs.applications;

import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.pagefactory.emr.jobs.EmrApplicationsPageObject;
import com.qa.pagefactory.emr.jobs.EmrSubTopPanelModulePageObject;
import com.qa.scripts.emr.jobs.EMRAllApps;
import com.qa.utils.LoggingUtils;
import com.qa.utils.WaitExecuter;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

@Marker.EMRAllApps
public class TC_EMR_JAL_04 extends BaseClass {
    private final LoggingUtils loggingUtils = new LoggingUtils(TC_EMR_JAL_04.class);

    @Test()
    public void validateGlobalSearch() {
        test = extent.startTest("TC_EMR_JAL_04.validateGlobalSearch", "Verify that global search is able to " +
                "search by application type, queue name, user name or app Id");
        test.assignCategory("Jobs - Applications");
        test.log(LogStatus.INFO, "Login to the application");
        // Initialize all classes objects
        test.log(LogStatus.INFO, "Initialize all class objects");
        loggingUtils.info("Initialize all class objects", test);
        WaitExecuter waitExecuter = new WaitExecuter(driver);
        EmrApplicationsPageObject applicationsPageObject = new EmrApplicationsPageObject(driver);
        EMRAllApps allApps = new EMRAllApps(driver);
        // Navigate to Jobs tab from header
        test.log(LogStatus.INFO, "Navigate to jobs tab from header");
        test.log(LogStatus.INFO, "Select last 7 days");
        allApps.inJobsSelectLast7Days();
        waitExecuter.waitUntilPageFullyLoaded();
        int totalCount = Integer
                .parseInt(applicationsPageObject.getTotalAppCount.getText().replaceAll("[^\\dA-Za-z ]", "").trim());
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
                Assert.assertTrue(tableData.size() > 0, "Table does not contain data according to the User type " + userTypeToSearch);
                test.log(LogStatus.INFO, "Get username of first User listed in table and search ");
                applicationsPageObject.globalSearchBox.clear();
                loggingUtils.info("Get username of first application listed in table and search", test);
                waitExecuter.sleep(1000);
                applicationsPageObject.globalSearchBox.clear();
                waitExecuter.sleep(1000);
                String usernameToSearch = applicationsPageObject.getNamesFromDropDown.get(0).getText();
                waitExecuter.sleep(1000);
                applicationsPageObject.globalSearchBox.sendKeys(usernameToSearch);
                waitExecuter.sleep(1000);
                applicationsPageObject.globalSearchBox.sendKeys(Keys.RETURN);
                waitExecuter.sleep(3000);
                Assert.assertTrue(tableData.size() > 0, "Table does not contain data according to the Username " + usernameToSearch);
                test.log(LogStatus.INFO, "Get Queue name of first application listed in table and search");
                loggingUtils.info("Get application Queue name of first application listed in table and search", test);
                waitExecuter.sleep(1000);
                applicationsPageObject.globalSearchBox.clear();
                String applicationIDSearch = applicationsPageObject.getApplicationIDTable.getText();
                waitExecuter.sleep(1000);
                applicationsPageObject.globalSearchBox.sendKeys(applicationIDSearch);
                waitExecuter.sleep(1000);
                applicationsPageObject.globalSearchBox.sendKeys(Keys.RETURN);
                waitExecuter.sleep(3000);
                Assert.assertTrue(tableData.size() > 0, "Table does not contain data according to the Application Id type " + applicationIDSearch);
                test.log(LogStatus.PASS, "Table contain data according to the Application Id type ");
            } else
                Assert.assertTrue(applicationsPageObject.whenNoApplicationPresent.isDisplayed(),
                        "No Application present in the Application page'." +
                                "Manually check to see if the data on the Application page is present.");

    }
}