package com.qa.testcases.jobs.applications.details.hive;

import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.enums.UserAction;
import com.qa.pagefactory.SubTopPanelModulePageObject;
import com.qa.pagefactory.jobs.ApplicationsPageObject;
import com.qa.scripts.DatePicker;
import com.qa.scripts.jobs.applications.AllApps;
import com.qa.utils.WaitExecuter;
import com.qa.utils.actions.UserActions;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.logging.Logger;

@Marker.AppDetailsHive
@Marker.EMRHive
@Marker.GCPAppDetailsHive
@Marker.All
public class TC_HIVE_50 extends BaseClass {
    private static final Logger LOGGER = Logger.getLogger(TC_HIVE_50.class.getName());

    @Test(dataProvider = "clusterid-data-provider")
    public void verifyPaginationByPageNumber(String clusterId) {
        test = extent.startTest("TC_HIVE_50.verifyPaginationByPageNumber",
                "Verify that pagination works properly by giving page number");
        test.assignCategory("App Details - Hive");
        test.log(LogStatus.INFO, "Login to the application");

        // Initialize all classes objects
        test.log(LogStatus.INFO, "Initialize all class objects");
        LOGGER.info("Initialize all class objects");
        WaitExecuter waitExecuter = new WaitExecuter(driver);
        SubTopPanelModulePageObject topPanelComponentPageObject = new SubTopPanelModulePageObject(driver);
        ApplicationsPageObject applicationsPageObject = new ApplicationsPageObject(driver);
        AllApps allApps = new AllApps(driver);
        DatePicker datePicker = new DatePicker(driver);
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        UserActions userActions = new UserActions(driver);
        // Navigate to Jobs tab from header
        test.log(LogStatus.INFO, "Navigate to jobs tab from header");
        LOGGER.info("Navigate to jobs tab from header");
        waitExecuter.waitUntilElementClickable(topPanelComponentPageObject.jobs);
        userActions.performActionWithPolling(topPanelComponentPageObject.jobs, UserAction.CLICK);
        waitExecuter.waitUntilElementClickable(applicationsPageObject.resetButton);
        waitExecuter.waitUntilPageFullyLoaded();
        // Select last 30 days from date picker
        test.log(LogStatus.INFO, "Select last 30 days");
        LOGGER.info("Select last 30 days");
        datePicker.clickOnDatePicker();
        waitExecuter.sleep(1000);
        datePicker.selectLast30Days();
        waitExecuter.sleep(2000);
        // Select cluster
        test.log(LogStatus.INFO, "Select clusterid : " + clusterId);
        LOGGER.info("Select clusterId : " + clusterId);
        allApps.selectCluster(clusterId);
        waitExecuter.sleep(3000);
        executor.executeScript("arguments[0].scrollIntoView();", applicationsPageObject.userSearchBox);
        if (applicationsPageObject.isPaginationPresent.isDisplayed()) {
            applicationsPageObject.lastPage.click();
            waitExecuter.sleep(3000);
            String totalPages = applicationsPageObject.getTotalNoOfPages.getText().trim();
            LOGGER.info("Total No. of pages- " + totalPages);
            String[] pageNumber = applicationsPageObject.getTotalNoOfPages.getText().trim().split("\\s+");
            int pageNo = Integer.parseInt(pageNumber[2]);
            // Search for particular page number
            test.log(LogStatus.INFO, "Search for particular page number");
            LOGGER.info("Search for particular page number");
            applicationsPageObject.getPageNumber.click();
            applicationsPageObject.getPageNumber.sendKeys(pageNumber[2]);
            applicationsPageObject.getPageNumber.sendKeys(Keys.ENTER);
            waitExecuter.sleep(2000);
            int pageValue = Integer.parseInt(applicationsPageObject.getPageNumber.getAttribute("value").trim());
            // Assert the page number
            test.log(LogStatus.INFO, "Assert the page number");
            LOGGER.info("Assert the page number");
            Assert.assertEquals(pageNo, pageValue, "The last page icon is not working");
            test.log(LogStatus.PASS, "Verified pagination by clicking on First-Last button of pagination.");
            executor.executeScript("arguments[0].scrollIntoView();", applicationsPageObject.globalSearchBox);
            // Reset username filter to default
            test.log(LogStatus.INFO, "Reset username filter");
            allApps.reset();
        } else {
            test.log(LogStatus.SKIP, "The pagination is not present for selected duration and clusterId.");
            executor.executeScript("arguments[0].scrollIntoView();", applicationsPageObject.globalSearchBox);
            // Reset username filter to default
            test.log(LogStatus.INFO, "Reset username filter");
            allApps.reset();
        }

    }
}