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
@Marker.DbxRuns
@Marker.All
public class TC_DR_73 extends BaseClass {
    private final LoggingUtils loggingUtils = new LoggingUtils(TC_DR_73.class);

    @Test()
    public void searchJobsByClusterNameInRunningTab() {
        test = extent.startTest("TC_DR_73.searchJobsByClusterNameInRunningTab",
                "Verify search must populate desired job by Cluster Name");
        test.assignCategory("Jobs-Runs/Running");
        test.log(LogStatus.INFO, "Initialize all class objects");
        loggingUtils.info("Initialize all class objects", test);
        WaitExecuter waitExecuter = new WaitExecuter(driver);
        DbxSubTopPanelModulePageObject dbpageObject = new DbxSubTopPanelModulePageObject(driver);
        DbAllApps dballApps = new DbAllApps(driver);
        dballApps.navigateToJobsTab("Runs");
        dballApps.selectTab("Running");
        waitExecuter.waitUntilPageFullyLoaded();
        // Navigate to Runs tab select cluster and last 7 days
        test.log(LogStatus.INFO, "Navigate to jobs tab from header");
        test.log(LogStatus.INFO, "Select last 7 days");
        dballApps.inJobsSelectClusterAndLast7Days();
        waitExecuter.sleep(2000);
        try {
            // Assert if the application are as per the filter applied on global search
            test.log(LogStatus.INFO, "Assert if the application listed are as per the filter applied on search");
            loggingUtils.info("Assert if the application are as per the filter applied on search", test);

            test.log(LogStatus.INFO, "Get application type of first application listed in table");
            loggingUtils.info("Get application type of first application listed in table", test);
            String applicationTypeToSearch = dbpageObject.getClusterNameFromTable.getText();
            dbpageObject.searchBox.clear();
            waitExecuter.sleep(1000);
            dbpageObject.searchBox.sendKeys(applicationTypeToSearch);
            waitExecuter.sleep(1000);
            dbpageObject.searchBox.sendKeys(Keys.RETURN);
            waitExecuter.sleep(3000);
            List<WebElement> tableData = dbpageObject.getTableData;
            Assert.assertTrue(tableData.size() > 0,
                    "Table does not contain data according to the Application type " + applicationTypeToSearch);
            test.log(LogStatus.INFO, "Get Cluster Name of first application listed in table and search ");
            dbpageObject.searchBox.clear();


        } catch (NoSuchElementException ex) {
            loggingUtils.error("No app present by this name", test);
            loggingUtils.error("Error- " + ex, test);
        }
    }
}
