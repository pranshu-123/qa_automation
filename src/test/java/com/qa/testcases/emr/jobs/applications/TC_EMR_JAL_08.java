package com.qa.testcases.emr.jobs.applications;

import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.pagefactory.emr.jobs.EmrApplicationsPageObject;
import com.qa.scripts.emr.jobs.EMRAllApps;
import com.qa.utils.LoggingUtils;
import com.qa.utils.WaitExecuter;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@Marker.EMRAllApps
public class TC_EMR_JAL_08 extends BaseClass {
    private final LoggingUtils LOGGER = new LoggingUtils(TC_EMR_JAL_06.class);

    @Test()
    public void verifyStatusTypeFilter() {
        test = extent.startTest("TC_EMR_JAL_08.verifyStatusTypeFilter",
                "Verify both app status count and showing result displayed at top are same");
        test.assignCategory("Jobs - Applications");
        test.log(LogStatus.INFO, "Login to the application");
        // Initialize all classes objects
        test.log(LogStatus.INFO, "Initialize all class objects");
        LOGGER.info("Initialize all class objects",test);
        WaitExecuter waitExecuter = new WaitExecuter(driver);
        EmrApplicationsPageObject applicationsPageObject = new EmrApplicationsPageObject(driver);
        EMRAllApps allApps = new EMRAllApps(driver);
        // Navigate to Jobs tab from header
        test.log(LogStatus.INFO, "Navigate to jobs tab from header");
        test.log(LogStatus.INFO, "Select last 7 days");
        allApps.inJobsSelectLast7Days();
        // Expand status filter on left pane
        applicationsPageObject.expandStatus.click();
        waitExecuter.sleep(2000);
        // To apply filter - De-select all status types
        test.log(LogStatus.INFO, "To apply status filter - De-select all status types");
        LOGGER.info("To apply status filter - De-select all status types",test);
        allApps.deselectAllStatusTypes();
        waitExecuter.sleep(2000);
        List<String> existingStatusTypes = new ArrayList<>(
                Arrays.asList("Killed", "Failed", "Running", "Success", "Pending", "Unknown", "Waiting"));
        List<WebElement> statusTypes = applicationsPageObject.getStatusTypes;
        List<String> listOfStatusTypes = new ArrayList<>();
        waitExecuter.sleep(2000);
        for (int i = 0; i < statusTypes.size(); i++) {
            listOfStatusTypes.add(statusTypes.get(i).getText().trim());
        }
        Assert.assertTrue(listOfStatusTypes.equals(existingStatusTypes),
                "Status types displayed does not match the expected status list");
        test.log(LogStatus.PASS, "Status types displayed match the expected status list");

        // Select single app and assert that table contain its data.
        List<WebElement> clickOnIndividualStatus = applicationsPageObject.selectSingleStatusType;
        waitExecuter.sleep(2000);
        List<WebElement> appStatusCounts = applicationsPageObject.getEachStatusTypeJobCount;
        waitExecuter.sleep(2000);
        for (int i = 0; i < clickOnIndividualStatus.size(); i++) {
            waitExecuter.sleep(2000);
            clickOnIndividualStatus.get(i).click();
            waitExecuter.sleep(2000);
            int appCount = Integer.parseInt(appStatusCounts.get(i).getText().replaceAll("[^\\dA-Za-z ]", "").trim());
            waitExecuter.sleep(1000);
            int totalCount = Integer
                    .parseInt(applicationsPageObject.getTotalAppCount.getText().replaceAll("[^\\dA-Za-z ]", "").trim());
            waitExecuter.sleep(1000);
            Assert.assertEquals(appCount, totalCount, "The app count of " + statusTypes.get(i).getText().trim()
                    + " is not equal to the total count of heading.");
            test.log(LogStatus.PASS, "The app count is  equal to the total count of heading.");
            if (appCount > 0) {
                String getStatusTypeFromTable = applicationsPageObject.getStatusFromTable.getText();
                waitExecuter.sleep(2000);
                Assert.assertEquals(getStatusTypeFromTable.toLowerCase(),
                        statusTypes.get(i).getText().trim().toLowerCase(),
                        "The Jobs displayed in tables contains application other than that of selected App Type");
                test.log(LogStatus.PASS, "The Jobs displayed in tables matches to that of selected App Type");
                waitExecuter.sleep(1000);
            } else
                Assert.assertTrue(applicationsPageObject.whenNoApplicationPresent.isDisplayed(),
                        "No Application present in the Application page'."+
                                "Manually check to see if the data on the Application page is present.");
            waitExecuter.sleep(1000);
            clickOnIndividualStatus.get(i).click();
            waitExecuter.sleep(2000);
        }
        // Click on show-all to view all type of applications
        applicationsPageObject.resetButton.click();
        waitExecuter.sleep(2000);
    }

}
