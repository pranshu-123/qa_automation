package com.qa.testcases.databricks.jobs.runs;

import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.pagefactory.databricks.DbxSubTopPanelModulePageObject;
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

@Marker.DbxJobsRuns
@Marker.All
public class TC_DR_03 extends BaseClass {
    private final LoggingUtils loggingUtils = new LoggingUtils(TC_DR_03.class);

    @Test()
    public void validateSearchJobsById() {
        test = extent.startTest("TC_DR_03.validateSearchJobsById",
                "VVerify search must populate desired job by id");
        test.log(LogStatus.INFO, "Login to the application");
        // Initialize all classes objects
        WaitExecuter waitExecuter = new WaitExecuter(driver);
        DbxSubTopPanelModulePageObject dbpageObject = new DbxSubTopPanelModulePageObject(driver);
        DbAllApps allApps = new DbAllApps(driver);
        allApps.navigateToJobsTab("Runs");
        try {
            // Navigate to Runs tab select cluster and last 7 days
            test.log(LogStatus.INFO, "Navigate to jobs tab from header");
            test.log(LogStatus.INFO, "Select last 7 days");
            allApps.inJobsSelectClusterAndLast7Days();

            // Assert if the application are as per the filter applied on global search
            test.log(LogStatus.INFO, "Assert if the application listed are as per the filter applied on global search");
            loggingUtils.info("Assert if the application are as per the filter applied on search", test);

            test.log(LogStatus.INFO, "Get application type of first application listed in table");
            loggingUtils.info("Get application type of first application listed in table", test);
            String applicationTypeToSearch = dbpageObject.getIdeFromTable.getText();
            dbpageObject.searchBox.clear();
            waitExecuter.sleep(1000);
            dbpageObject.searchBox.sendKeys(applicationTypeToSearch);
            waitExecuter.sleep(1000);
            dbpageObject.searchBox.sendKeys(Keys.RETURN);
            waitExecuter.sleep(3000);
            List<WebElement> tableData = dbpageObject.getTableData;
            Assert.assertTrue(tableData.size() > 0,
                    "Table does not contain data according to the Application type " + applicationTypeToSearch);
            test.log(LogStatus.INFO, "Get username of first application listed in table and search ");
            dbpageObject.searchBox.clear();

        } catch (NoSuchElementException ex) {
            loggingUtils.error("No app present by this name", test);
            loggingUtils.error("Error- " + ex, test);
        }
    }
}