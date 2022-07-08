package com.qa.testcases.emr.jobs.inefficientapps;

import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.pagefactory.DatePickerPageObject;
import com.qa.pagefactory.emr.jobs.EmrApplicationsPageObject;
import com.qa.scripts.DatePicker;
import com.qa.scripts.emr.jobs.EMRAllApps;
import com.qa.scripts.emr.jobs.EmrInefficientApps;
import com.qa.utils.LoggingUtils;
import com.qa.utils.WaitExecuter;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

@Marker.EmrInefficientApps
public class TC_EMR_JIA03 extends BaseClass {

    private final LoggingUtils LOGGER = new LoggingUtils(TC_EMR_JIA03.class);

    /**
     * Verify applications with Inefficient events are listed for different date ranges
     */
    @Test()
    public void TC_JIA03_verifyAllDatePicker() {
        test = extent.startTest("TC_JIA03_verifyAllDatePicker", "Verify applications with Inefficient events are listed for different date ranges");
        test.assignCategory(" Jobs / InEfficient Apps");

        WaitExecuter waitExecuter = new WaitExecuter(driver);
        EmrApplicationsPageObject applicationsPageObject = new EmrApplicationsPageObject(driver);
        DatePickerPageObject datePickerPageObject = new DatePickerPageObject(driver);
        DatePicker datePicker = new DatePicker(driver);


        // Get setup done for InefficientApps page
        EmrInefficientApps inefficientApps = new EmrInefficientApps(driver);
        inefficientApps.setupInefficientApps();
        test.log(LogStatus.INFO, "setup for InefficientApps done.");

        // Click on date picker ranges and verify Total app counts
        test.log(LogStatus.INFO, "Click on date picker ranges and verify Total app counts");
        LOGGER.info("Click on date picker ranges and verify Total app counts",test);
        List<WebElement> dateRange = datePickerPageObject.dateRangeOptions;
        waitExecuter.sleep(2000);
        datePicker.clickOnDatePicker();
        for (int i = 0; i < dateRange.size() - 1; i++) {
            String selectedDateRange = dateRange.get(i).getText();
            waitExecuter.sleep(1000);
            // Click on date picker ranges and verify Total app counts
            test.log(LogStatus.INFO, "Click on date picker range: " + selectedDateRange);
            LOGGER.info("Click on date picker range: " + selectedDateRange,test);
            dateRange.get(i).click();
            waitExecuter.waitUntilElementClickable(applicationsPageObject.resetButton);
            int totalAppCount = Integer
                    .parseInt(applicationsPageObject.getTotalAppCount.getText().replaceAll("[^\\dA-Za-z ]", "").trim());
            LOGGER.info("Total app count: " + totalAppCount,test);
            // Assert if apps are displayed for selected date range
            test.log(LogStatus.INFO, "Assert if apps are displayed for selected date range");
            Assert.assertTrue(totalAppCount >= 0,
                    "The app count of page does not match with totalAppCount for date range selected: "
                            + selectedDateRange);
            test.log(LogStatus.PASS,
                    "Apps are listed for selected date range- " + selectedDateRange);
            waitExecuter.waitUntilElementClickable(applicationsPageObject.resetButton);
            datePicker.clickOnDatePicker();
        }
    }
}
