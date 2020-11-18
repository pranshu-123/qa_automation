package com.qa.testcases.cluster.tuning;

import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.pagefactory.TopPanelPageObject;
import com.qa.scripts.DatePicker;
import com.qa.scripts.clusters.Tuning;
import com.qa.utils.MouseActions;
import com.qa.utils.WaitExecuter;
import com.relevantcodes.extentreports.LogStatus;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.logging.Logger;


@Marker.Tuning
@Marker.All
public class TC_CTR_02 extends BaseClass {
    private static final Logger LOGGER = Logger.getLogger(TC_CTR_02.class.getName());

    @Test(dataProvider = "clusterid-data-provider")
    public void validateTuningDatePickerList(String clusterId) {
        test = extent.startTest("TC_CTR_02.validateTuningDatePickerList", "Verify datepicker list");
        test.assignCategory(" Cluster - Tuning ");
        LOGGER.info("Passed Parameter Is : " + clusterId);

        WaitExecuter waitExecuter = new WaitExecuter(driver);
        TopPanelPageObject topPanelPageObject = new TopPanelPageObject(driver);
        waitExecuter.waitUntilElementPresent(topPanelPageObject.tuningTab);
        waitExecuter.waitUntilPageFullyLoaded();
        waitExecuter.waitUntilElementClickable(topPanelPageObject.tuningTab);
        waitExecuter.sleep(3000);
        MouseActions.clickOnElement(driver, topPanelPageObject.tuningTab);
        LOGGER.info("Clicked on Tuning Tab");

        Tuning tuning = new Tuning(driver);
        tuning.closeConfirmationMessageNotification();
        tuning.clickOnRunButton();
        LOGGER.info("Clicked on Run button");

        DatePicker datePicker = new DatePicker(driver);
        datePicker.clickOnDatePicker();
        String[] expectedDateOptions = {"Last 7 Days", "Last 30 Days", "Last 60 Days", "Last 90 Days", "Custom Range"};

        for (String expectedDateOption : expectedDateOptions) {
            Assert.assertTrue(datePicker.getDatePickerOptions().contains(expectedDateOption),
                    "Date list does not contain: " + expectedDateOption);
            test.log(LogStatus.PASS, "Date list contains option: " + expectedDateOption);
        }
        datePicker.clickOnDatePicker();
    }
}
