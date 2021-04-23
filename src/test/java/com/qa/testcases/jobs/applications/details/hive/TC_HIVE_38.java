package com.qa.testcases.jobs.applications.details.hive;

import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.enums.UserAction;
import com.qa.pagefactory.SubTopPanelModulePageObject;
import com.qa.pagefactory.jobs.ApplicationsPageObject;
import com.qa.scripts.DatePicker;
import com.qa.scripts.appdetails.SparkAppsDetailsPage;
import com.qa.scripts.clusters.impala.ChargeBackImpala;
import com.qa.scripts.jobs.applications.AllApps;
import com.qa.utils.WaitExecuter;
import com.qa.utils.actions.UserActions;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.Test;

import java.util.logging.Logger;

@Marker.AppDetailsHive
@Marker.All
public class TC_HIVE_38 extends BaseClass {
    private static final Logger LOGGER = Logger.getLogger(TC_HIVE_38.class.getName());

    @Test(dataProvider = "clusterid-data-provider")
    public void VerifyDurationSliderFilter(String clusterId) {
        test = extent.startTest("TC_HIVE_38.VerifyDurationSliderFilter",
                "Verify duration selected in slider matches the apps duration in table.");
        test.assignCategory("App Details - Hive");
        test.log(LogStatus.INFO, "Login to the application");

        // Initialize all classes objects
        test.log(LogStatus.INFO, "Initialize all class objects");
        LOGGER.info("Initialize all class objects");
        WaitExecuter waitExecuter = new WaitExecuter(driver);
        SubTopPanelModulePageObject topPanelComponentPageObject = new SubTopPanelModulePageObject(driver);
        ApplicationsPageObject applicationsPageObject = new ApplicationsPageObject(driver);
        DatePicker datePicker = new DatePicker(driver);
        AllApps allApps = new AllApps(driver);
        ChargeBackImpala impala = new ChargeBackImpala(driver);
        SparkAppsDetailsPage sparkApp = new SparkAppsDetailsPage(driver);
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
        waitExecuter.waitUntilElementClickable(applicationsPageObject.resetButton);
        // Select cluster
        test.log(LogStatus.INFO, "Select clusterid : " + clusterId);
        LOGGER.info("Select clusterId : " + clusterId);
        allApps.selectCluster(clusterId);
        waitExecuter.waitUntilElementClickable(applicationsPageObject.resetButton);
        // Select 'Only' hive type and get its jobs count
        test.log(LogStatus.INFO, "Select 'Only' hive from app types and get its jobs count");
        LOGGER.info("Select 'Only' hive from app types and get its jobs count");
        sparkApp.clickOnlyLink("Hive");
        waitExecuter.waitUntilElementClickable(applicationsPageObject.resetButton);
        int hiveAppCount = Integer.parseInt(applicationsPageObject.getEachApplicationTypeJobCounts.get(0).getText()
                .replaceAll("[^\\dA-Za-z ]", "").trim());

        // Slide the duration slider to get min and max value of duration
        test.log(LogStatus.INFO, "Slide the duration slider to get min and max value of duration");
        LOGGER.info("Slide the duration slider to get min and max value of duration");
        WebElement DurationSlider = applicationsPageObject.durationSlider;
        allApps.moveTheSlider(DurationSlider, 5);
        waitExecuter.waitUntilElementClickable(applicationsPageObject.resetButton);
        waitExecuter.sleep(2000);
        String SliderInputLeft = applicationsPageObject.durationSliderInputLeft.getAttribute("value");

        String SliderInputRight = applicationsPageObject.durationSliderInputRight.getAttribute("value");
        waitExecuter.waitUntilElementClickable(applicationsPageObject.resetButton);
        test.log(LogStatus.INFO, "Duration Slider From : " + SliderInputLeft + " To : " + SliderInputRight);
        LOGGER.info("Duration Slider From : " + SliderInputLeft + " To : " + SliderInputRight);
        hiveAppCount = Integer.parseInt(applicationsPageObject.getEachApplicationTypeJobCounts.get(0).getText()
                .replaceAll("[^\\dA-Za-z ]", "").trim());
        LOGGER.info("Hive count after filter " + hiveAppCount);
        waitExecuter.sleep(2000);
        if (hiveAppCount > 0) {
            // Convert slider min max value in seconds
            test.log(LogStatus.INFO, "Convert slider min max value in seconds");
            LOGGER.info("Convert slider min max value in seconds");
            int leftSliderInSeconds = impala.convertTimeToSeconds(SliderInputLeft);
            int rightSliderInSeconds = impala.convertTimeToSeconds(SliderInputRight);
            waitExecuter.sleep(2000);
            // Sort the application w.r.t min duration
            test.log(LogStatus.INFO, "Sort the application w.r.t min duration");
            LOGGER.info("Sort the application w.r.t min duration");
            applicationsPageObject.sortDuration.click();
            waitExecuter.sleep(2000);
            // Get min duration of first application from table and convert into seconds
            test.log(LogStatus.INFO, "Get min duration of first application from table and convert into seconds");
            LOGGER.info("Get min duration of first application from table and convert into seconds");
            String minDurationFromTable = applicationsPageObject.getDurationFromTable.getText();
            int minDurationInSec = impala.convertTimeToSeconds(minDurationFromTable);
            waitExecuter.sleep(2000);
            // Sort the application w.r.t max duration
            test.log(LogStatus.INFO, "Sort the application w.r.t max duration");
            LOGGER.info("Sort the application w.r.t max duration");
            applicationsPageObject.sortDuration.click();
            waitExecuter.sleep(2000);
            // Get max duration of first application from table and convert into seconds
            test.log(LogStatus.INFO, "Get max duration of first application from table and convert into seconds");
            LOGGER.info("Get max duration of first application from table and convert into seconds");
            String maxDurationFromTable2 = applicationsPageObject.getDurationFromTable.getText();
            int maxDurationInSec = impala.convertTimeToSeconds(maxDurationFromTable2);
            waitExecuter.sleep(1000);
            // Assert the duration count to max and min values of slider
            LOGGER.info("Assert the duration count to max and min values of slider.");
            test.log(LogStatus.INFO, "Assert the duration count to max and min values of slider.");
            Assert.assertTrue(minDurationInSec <= rightSliderInSeconds && minDurationInSec >= leftSliderInSeconds,
                    "The min duration does not does not match the slider duration");
            test.log(LogStatus.PASS, "The min duration match the slider duration.");
            Assert.assertTrue(maxDurationInSec <= rightSliderInSeconds && maxDurationInSec >= leftSliderInSeconds,
                    "The max duration does not does not match the slider duration");
            test.log(LogStatus.PASS, "The max duration match the slider duration.");
            waitExecuter.sleep(2000);
        } else {
            Assert.assertEquals(applicationsPageObject.whenNoApplicationPresent.getText(), "No Data Available",
                    "For 0 application hive 'No Data Available' is not displayed");
            test.log(LogStatus.SKIP, "Hive apps are not available for the selected duration and cluster.");
        }
        // Reset slider duration to default
        test.log(LogStatus.INFO, "Reset slider duration to default");
        LOGGER.info("Reset slider duration to default");
        waitExecuter.sleep(2000);
        applicationsPageObject.resetButton.click();
        waitExecuter.sleep(3000);
    }
}
