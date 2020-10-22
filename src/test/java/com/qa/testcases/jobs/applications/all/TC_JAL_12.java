package com.qa.testcases.jobs.applications.all;

/* To test-
 * This testcase should list all the applications with duration selected in duration slider filter
 */

import com.qa.base.BaseClass;
import com.qa.pagefactory.jobs.ApplicationsPageObject;
import com.qa.scripts.clusters.impala.ChargeBackImpala;
import com.qa.scripts.jobs.applications.AllApps;
import com.qa.utils.WaitExecuter;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.logging.Logger;

/*
 * @author - Ojasvi Pandey
 */

public class TC_JAL_12 extends BaseClass {
    private static final Logger LOGGER = Logger.getLogger(TC_JAL_12.class.getName());

    @Test(dataProvider = "clusterid-data-provider")
    public void VerifyDurationSliderFilter(String clusterId) {
        test = extent.startTest("TC_JAL_12.VerifyDurationSliderFilter",
                "Verify duration selected in slider matches the apps duration in table.");
        test.assignCategory("Jobs - Applications");
        test.log(LogStatus.INFO, "Login to the application");
        // Initialize all classes objects
        test.log(LogStatus.INFO, "Initialize all class objects");
        LOGGER.info("Initialize all class objects");
        WaitExecuter waitExecuter = new WaitExecuter(driver);
        ApplicationsPageObject applicationsPageObject = new ApplicationsPageObject(driver);
        AllApps allApps = new AllApps(driver);
        ChargeBackImpala impala = new ChargeBackImpala(driver);
        // Navigate to Jobs tab from header
        test.log(LogStatus.INFO, "Navigate to jobs tab from header");
        test.log(LogStatus.INFO, "Select last 7 days");
        test.log(LogStatus.INFO, "Select clusterId : " + clusterId);
        allApps.inJobsSelectClusterAndLast7Days(clusterId);
        // Slide the duration slider to get min and max value of duration
        test.log(LogStatus.INFO, "Slide the duration slider to get min and max value of duration");
        LOGGER.info("Slide the duration slider to get min and max value of duration");
        WebElement DurationSlider = applicationsPageObject.durationSlider;
        allApps.moveTheSlider(DurationSlider, 50);
        String SliderInputLeft = applicationsPageObject.durationSliderInputLeft.getAttribute("value");
        waitExecuter.sleep(2000);
        String SliderInputRight = applicationsPageObject.durationSliderInputRight.getAttribute("value");
        waitExecuter.sleep(2000);
        test.log(LogStatus.INFO, "Duration Slider From : " + SliderInputLeft + " To : " + SliderInputRight);
        LOGGER.info("Duration Slider From : " + SliderInputLeft + " To : " + SliderInputRight);
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
                "The min duration does not match the slider duration");
        test.log(LogStatus.PASS, "The min duration match the slider duration");
        Assert.assertTrue(maxDurationInSec <= rightSliderInSeconds && maxDurationInSec >= leftSliderInSeconds,
                "The max duration does not match the slider duration");
        test.log(LogStatus.PASS, "The max duration does not match the slider duration");
        waitExecuter.sleep(2000);
        // Reset slider duration to default
        test.log(LogStatus.INFO, "Reset slider duration to default");
        LOGGER.info("Reset slider duration to default");
        applicationsPageObject.resetButton.click();
        waitExecuter.sleep(3000);

    }
}