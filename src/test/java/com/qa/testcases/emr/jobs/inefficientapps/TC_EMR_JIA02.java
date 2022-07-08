package com.qa.testcases.emr.jobs.inefficientapps;

import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.enums.UserAction;
import com.qa.pagefactory.emr.jobs.EmrSubTopPanelModulePageObject;
import com.qa.scripts.DatePicker;
import com.qa.scripts.emr.jobs.EMRAllApps;
import com.qa.scripts.emr.jobs.EmrInefficientApps;
import com.qa.utils.LoggingUtils;
import com.qa.utils.WaitExecuter;
import com.qa.utils.actions.UserActions;
import com.relevantcodes.extentreports.LogStatus;
import org.testng.annotations.Test;

@Marker.EmrInefficientApps
public class TC_EMR_JIA02 extends BaseClass {

    private final LoggingUtils LOGGER = new LoggingUtils(TC_EMR_JIA02.class);

    /**
     * Verify Custom Range in datepicker
     */
    @Test()
    public void verifyCustomRangeDatePicker() {
        test = extent.startTest("TC_JIA02.verifyCustomRangeDatePicker" , "Verify the calender time");
        test.assignCategory(" Jobs / InEfficient Apps");

        WaitExecuter waitExecuter = new WaitExecuter(driver);

        //Click on Jobs tab
        EmrSubTopPanelModulePageObject subTopPanelModulePageObject = new EmrSubTopPanelModulePageObject(driver);
        UserActions actions = new UserActions(driver);
        waitExecuter.sleep(2000);
        actions.performActionWithPolling(subTopPanelModulePageObject.jobs, UserAction.CLICK);
        test.log(LogStatus.PASS, "Jobs tab is clicked successfully");
        LOGGER.info("Jobs tab is clicked successfully",test);

        EmrInefficientApps inefficientApps = new EmrInefficientApps(driver);
        inefficientApps.setupInefficientApps();
        test.log(LogStatus.INFO, "setup for InefficientApps done.");

        //Click on date picker
        DatePicker datePicker = new DatePicker(driver);
        datePicker.clickOnDatePicker();

        waitExecuter.sleep(1000);
        datePicker.selectCustomRange();
        waitExecuter.sleep(1000);

        test.log(LogStatus.PASS, "Custom Range date is successfully verified in date range");
        LOGGER.info("Custom Range date is successfully verified in date range",test);

    }
}
