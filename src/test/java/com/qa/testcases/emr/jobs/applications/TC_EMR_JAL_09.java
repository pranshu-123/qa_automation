package com.qa.testcases.emr.jobs.applications;

import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.pagefactory.emr.jobs.EmrApplicationsPageObject;
import com.qa.scripts.clusters.impala.ChargeBackImpala;
import com.qa.scripts.emr.jobs.EMRAllApps;
import com.qa.utils.LoggingUtils;
import com.qa.utils.WaitExecuter;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

@Marker.EMRAllApps
public class TC_EMR_JAL_09 extends BaseClass {
    private final LoggingUtils LOGGER = new LoggingUtils(TC_EMR_JAL_09.class);

    @Test()
    public void VerifyDurationSliderFilter() {
        test = extent.startTest("TC_EMR_JAL_09.VerifyDurationSliderFilter",
                "Verify duration selected in slider matches the apps duration in table.");
        test.assignCategory("Jobs - Applications");
        test.log(LogStatus.INFO, "Login to the application");
        // Initialize all classes objects
        test.log(LogStatus.INFO, "Initialize all class objects");
        LOGGER.info("Initialize all class objects",test);
        WaitExecuter waitExecuter = new WaitExecuter(driver);
        EmrApplicationsPageObject applicationsPageObject = new EmrApplicationsPageObject(driver);
        EMRAllApps allApps = new EMRAllApps(driver);
        ChargeBackImpala impala = new ChargeBackImpala(driver);
        // Navigate to Jobs tab from header
        test.log(LogStatus.INFO, "Navigate to jobs tab from header");
        test.log(LogStatus.INFO, "Select last 7 days");
        allApps.inJobsSelectLast7Days();
        // Slide the duration slider to get min and max value of duration
        test.log(LogStatus.INFO, "Slide the duration slider to get min and max value of duration");
        LOGGER.info("Slide the duration slider to get min and max value of duration",test);
        WebElement DurationSlider = applicationsPageObject.durationSlider;
        waitExecuter.waitUntilElementClickable(applicationsPageObject.resetButton);
        allApps.moveTheSlider(DurationSlider, 50);
        waitExecuter.waitUntilElementClickable(applicationsPageObject.resetButton);
        String SliderInputLeft = applicationsPageObject.durationSliderInputLeft.getAttribute("value");
        waitExecuter.waitUntilElementClickable(applicationsPageObject.resetButton);
        String SliderInputRight = applicationsPageObject.durationSliderInputRight.getAttribute("value");
        waitExecuter.waitUntilElementClickable(applicationsPageObject.resetButton);
        test.log(LogStatus.INFO, "Duration Slider From : " + SliderInputLeft + " To : " + SliderInputRight);
        LOGGER.info("Duration Slider From : " + SliderInputLeft + " To : " + SliderInputRight,test);
        // Convert slider min max value in seconds
        test.log(LogStatus.INFO, "Convert slider min max value in seconds");
        LOGGER.info("Convert slider min max value in seconds",test);
        int leftSliderInSeconds = impala.convertTimeToSeconds(SliderInputLeft);
        int rightSliderInSeconds = impala.convertTimeToSeconds(SliderInputRight);
        waitExecuter.sleep(2000);
        // Sort the application w.r.t min duration
        test.log(LogStatus.INFO, "Sort the application w.r.t min duration");
        LOGGER.info("Sort the application w.r.t min duration",test);
        waitExecuter.waitUntilElementClickable(applicationsPageObject.sortDuration);
        applicationsPageObject.sortDuration.click();
        waitExecuter.waitUntilElementClickable(applicationsPageObject.resetButton);
        waitExecuter.sleep(1000);
        // Get min duration of first application from table and convert into seconds
        test.log(LogStatus.INFO, "Get min duration of first application from table and convert into seconds");
        LOGGER.info("Get min duration of first application from table and convert into seconds",test);
        String minDurationFromTable = applicationsPageObject.getDurationFromTable.getText();
        int minDurationInSec = impala.convertTimeToSeconds(minDurationFromTable);
        waitExecuter.waitUntilElementClickable(applicationsPageObject.resetButton);
        // Sort the application w.r.t max duration
        test.log(LogStatus.INFO, "Sort the application w.r.t max duration");
        LOGGER.info("Sort the application w.r.t max duration",test);
        waitExecuter.waitUntilElementClickable(applicationsPageObject.resetButton);
        applicationsPageObject.sortDuration.click();
        waitExecuter.waitUntilElementClickable(applicationsPageObject.resetButton);
        waitExecuter.sleep(1000);
        // Get max duration of first application from table and convert into seconds
        test.log(LogStatus.INFO, "Get max duration of first application from table and convert into seconds");
        LOGGER.info("Get max duration of first application from table and convert into seconds",test);
        String maxDurationFromTable2 = applicationsPageObject.getDurationFromTable.getText();
        int maxDurationInSec = impala.convertTimeToSeconds(maxDurationFromTable2);
        waitExecuter.sleep(1000);
        // Assert the duration count to max and min values of slider
        LOGGER.info("Assert the duration count to max and min values of slider.",test);
        test.log(LogStatus.INFO, "Assert the duration count to max and min values of slider.");
        Assert.assertTrue(minDurationInSec <= rightSliderInSeconds && minDurationInSec >= leftSliderInSeconds,
                "The min duration does not match the slider duration");
        test.log(LogStatus.PASS, "The min duration match the slider duration");
        Assert.assertTrue(maxDurationInSec <= rightSliderInSeconds && maxDurationInSec >= leftSliderInSeconds,
                "The max duration does not match the slider duration");
        test.log(LogStatus.PASS, "The max duration does not match the slider duration");
        waitExecuter.waitUntilElementClickable(applicationsPageObject.resetButton);
        // Reset slider duration to default
        test.log(LogStatus.INFO, "Reset slider duration to default");
        LOGGER.info("Reset slider duration to default",test);
        applicationsPageObject.resetButton.click();
        waitExecuter.waitUntilElementClickable(applicationsPageObject.resetButton);

    }
}
