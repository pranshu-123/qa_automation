package com.qa.testcases.jobs.applications.all;

import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.pagefactory.jobs.ApplicationsPageObject;
import com.qa.scripts.DatePicker;
import com.qa.scripts.jobs.applications.AllApps;
import com.qa.utils.WaitExecuter;
import com.relevantcodes.extentreports.LogStatus;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.logging.Logger;

@Marker.AllApps
@Marker.All
public class TC_JAL_08 extends BaseClass {
    private static final Logger LOGGER = Logger.getLogger(TC_JAL_08.class.getName());

    @Test(dataProvider = "clusterid-data-provider")
    public void validateResetFunctionality(String clusterId) {
        test = extent.startTest("TC_JAL_08.validateResetFunctionality",
                "Selection of reset option should reset all the filters which are applied");
        test.assignCategory("Jobs - Applications");
        test.log(LogStatus.INFO, "Login to the application");
        // Initialize all classes objects
        test.log(LogStatus.INFO, "Initialize all class objects");
        LOGGER.info("Initialize all class objects");
        WaitExecuter waitExecuter = new WaitExecuter(driver);
        ApplicationsPageObject applicationsPageObject = new ApplicationsPageObject(driver);
        AllApps allApps = new AllApps(driver);
        DatePicker datePicker = new DatePicker(driver);
        // Navigate to Jobs tab from header
        test.log(LogStatus.INFO, "Navigate to jobs tab from header");
        test.log(LogStatus.INFO, "Select custom range in datepicker");
        test.log(LogStatus.INFO, "Select clusterId : " + clusterId);
        allApps.navigateToJobsTab();
        waitExecuter.waitUntilElementClickable(applicationsPageObject.resetButton);
        datePicker.clickOnDatePicker();
        waitExecuter.waitUntilElementClickable(applicationsPageObject.resetButton);
        datePicker.selectCustomRange();
        waitExecuter.waitUntilElementClickable(applicationsPageObject.resetButton);
        datePicker.setStartAndEndDateFromCurrentDate(-3,-1);
        datePicker.clickOnCustomDateApplyBtn();
        waitExecuter.waitUntilElementClickable(applicationsPageObject.resetButton);
        // Get total app counts before applying filter
        waitExecuter.sleep(1000);
        int totalCountBeforeFilter = Integer
                .parseInt(applicationsPageObject.getTotalAppCount.getText().replaceAll("[^\\dA-Za-z ]", "").trim());
        waitExecuter.sleep(2000);
        test.log(LogStatus.INFO, "Get total app counts before applying filter " + totalCountBeforeFilter);
        LOGGER.info("Get total app counts before applying filter " + totalCountBeforeFilter);
        // Select cluster
        test.log(LogStatus.INFO, "Select clusterid : " + clusterId);
        LOGGER.info("Select clusterId : " + clusterId);
        /*allApps.selectCluster(clusterId);*/
        waitExecuter.waitUntilElementClickable(applicationsPageObject.resetButton);
        // To apply filter - De-select all application types
        test.log(LogStatus.INFO, "To apply filter - De-select all application types");
        LOGGER.info("To apply filter - De-select all application types");
        allApps.deselectAllAppTypes();
        waitExecuter.waitUntilElementClickable(applicationsPageObject.resetButton);
        waitExecuter.sleep(7000);
        // After de-selecting app type check the app counts
        int totalCountAfterFilter = Integer
                .parseInt(applicationsPageObject.getTotalAppCount.getText().replaceAll("[^\\dA-Za-z ]", "").trim());
        waitExecuter.sleep(2000);
        test.log(LogStatus.INFO, "After de-selecting app type check the app counts " + totalCountAfterFilter);
        LOGGER.info("After de-selecting app type check the app counts " + totalCountAfterFilter);
        Assert.assertEquals(totalCountAfterFilter, totalCountBeforeFilter, "After de-selecting all app types is not equal to " +
                "the total count of heading.");
        waitExecuter.waitUntilPageFullyLoaded();
        // Click on reset button
        test.log(LogStatus.INFO, "Click on reset button");
        LOGGER.info("Click on reset button");
        applicationsPageObject.resetButton.click();
        waitExecuter.sleep(2000);

        // Get total app count after clicking on reset button
        int totalCountAfterReset = Integer
                .parseInt(applicationsPageObject.getTotalAppCount.getText().replaceAll("[^\\dA-Za-z ]", "").trim());
        waitExecuter.sleep(2000);
        test.log(LogStatus.INFO, "Get total app count after clicking on reset button " + totalCountAfterReset);
        LOGGER.info("Get total app count after clicking on reset button " + totalCountAfterReset);
        Assert.assertEquals(totalCountBeforeFilter, totalCountAfterReset,
                "Total count of application are not same before and after filter ");
        test.log(LogStatus.PASS, "Total count of application are same before and after filter ");
    }
}

