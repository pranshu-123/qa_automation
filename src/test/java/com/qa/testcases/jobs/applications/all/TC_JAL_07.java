package com.qa.testcases.jobs.applications.all;

import com.qa.base.BaseClass;
import com.qa.pagefactory.jobs.ApplicationsPageObject;
import com.qa.scripts.jobs.applications.AllApps;
import com.qa.utils.WaitExecuter;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;
import java.util.logging.Logger;

public class TC_JAL_07 extends BaseClass {
    private static final Logger LOGGER = Logger.getLogger(TC_JAL_07.class.getName());

    @Test(dataProvider = "clusterid-data-provider")
    public void validateGlobalSearch(String clusterId) {
        test = extent.startTest("TC_JAL_07.validateGlobalSearch",
                "Verify that global search is able to search by application type, queue name, user name or app Id");
        test.assignCategory("Jobs - Applications");
        test.log(LogStatus.INFO, "Login to the application");
        // Initialize all classes objects
        test.log(LogStatus.INFO, "Initialize all class objects");
        LOGGER.info("Initialize all class objects");
        WaitExecuter waitExecuter = new WaitExecuter(driver);
        ApplicationsPageObject applicationsPageObject = new ApplicationsPageObject(driver);
        AllApps allApps = new AllApps(driver);
        // Navigate to Jobs tab select cluster and last 7 days
        test.log(LogStatus.INFO, "Navigate to jobs tab from header");
        test.log(LogStatus.INFO, "Select last 7 days");
        test.log(LogStatus.INFO, "Select clusterId : " + clusterId);
        allApps.inJobsSelectClusterAndLast7Days(clusterId);
        List<WebElement> applicationsClusterIds = applicationsPageObject.getApplicationClusterId;
        // Assert if the application are as per the filter applied on global search
        test.log(LogStatus.INFO, "Assert if the application listed are as per the filter applied on global search");
        LOGGER.info("Assert if the application are as per the filter applied on global search");
        if (applicationsClusterIds.size() > 0) {
            test.log(LogStatus.INFO, "Get application type of first application listed in table");
            LOGGER.info("Get application type of first application listed in table");
            String applicationTypeToSearch = applicationsPageObject.getTypeFromTable.getText();
            applicationsPageObject.globalSearchBox.clear();
            waitExecuter.sleep(1000);
            applicationsPageObject.globalSearchBox.sendKeys(applicationTypeToSearch);
            waitExecuter.sleep(1000);
            applicationsPageObject.globalSearchBox.sendKeys(Keys.RETURN);
            waitExecuter.sleep(3000);
            List<WebElement> tableData = applicationsPageObject.getTableData;
            Assert.assertTrue(tableData.size() > 0,
                    "Table does not contain data according to the Application type " + applicationTypeToSearch);
            test.log(LogStatus.INFO, "Get username of first application listed in table and search ");
            LOGGER.info("Get username of first application listed in table and search");
            waitExecuter.sleep(1000);
            applicationsPageObject.globalSearchBox.clear();
            waitExecuter.sleep(1000);
            applicationsPageObject.userSearchBox.click();
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
            LOGGER.info("Get application Queue name of first application listed in table and search");
            waitExecuter.sleep(1000);
            applicationsPageObject.globalSearchBox.clear();
            String queuenameToSearch = applicationsPageObject.getQueueNameTable.getText();
            waitExecuter.sleep(1000);
            applicationsPageObject.globalSearchBox.sendKeys(queuenameToSearch);
            waitExecuter.sleep(1000);
            applicationsPageObject.globalSearchBox.sendKeys(Keys.RETURN);
            waitExecuter.sleep(3000);
            Assert.assertTrue(tableData.size() > 0,
                    "Table does not contain data according to the Queue name type " + queuenameToSearch);
            test.log(LogStatus.PASS, "Table contain data according to the Queue name type ");
        } else
            Assert.assertTrue(applicationsPageObject.whenNoApplicationPresent.isDisplayed(),
                    "The clusterId does not have any application under it and also does not display 'No Data Available' for it"
                            + clusterId);
    }
}
