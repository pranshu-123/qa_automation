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
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

@Marker.EmrInefficientApps
public class TC_EMR_JIA01 extends BaseClass {
    private final LoggingUtils LOGGER = new LoggingUtils(TC_EMR_JIA01.class);
    @Test
    public void TC_JIA01_verifyDatePicker() {
        test = extent.startTest("TC_EMR_JIA01_verifyDatePicker", "Verify the calender time");
        test.assignCategory(" Jobs / InEfficient Apps");

        WaitExecuter waitExecuter = new WaitExecuter(driver);

        //Click on Jobs tab
        EmrSubTopPanelModulePageObject subTopPanelModulePageObject = new EmrSubTopPanelModulePageObject(driver);
        UserActions actions = new UserActions(driver);
        waitExecuter.sleep(2000);
        actions.performActionWithPolling(subTopPanelModulePageObject.jobs, UserAction.CLICK);

        EmrInefficientApps inefficientApps = new EmrInefficientApps(driver);
        inefficientApps.setupInefficientApps();
        test.log(LogStatus.INFO, "setup for InefficientApps done.");


        //Click on date picker
        DatePicker datePicker = new DatePicker(driver);
        datePicker.clickOnDatePicker();

        String [] expectedDatePicker = {"Last 1 Hour","Last 2 Hours","Last 7 Days", "Last 30 Days", "Last 90 Days",
                "This Month","Last Month","Custom Range"};
        List<String> datePickerOptions =datePicker.getDatePickerOptions();
        System.out.println("Actual datepicker list: "+datePickerOptions);

        Boolean boolDate=true;
        for(String strDate: expectedDatePicker){
            if(!datePickerOptions.contains(strDate)){
                boolDate= false;
            }
        }

        Assert.assertTrue(boolDate,"Some of date picker not found.");
        test.log(LogStatus.PASS, "Verified Date Picker successfully.");
        LOGGER.info("Verified Date Picker successfully.",test);
    }
}
