package com.qa.testcases.emr.jobs.inefficientapps;

import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.enums.UserAction;
import com.qa.pagefactory.SubTopPanelModulePageObject;
import com.qa.pagefactory.emr.jobs.EmrApplicationsPageObject;
import com.qa.pagefactory.emr.jobs.EmrSubTopPanelModulePageObject;
import com.qa.pagefactory.jobs.ApplicationsPageObject;
import com.qa.scripts.DatePicker;
import com.qa.scripts.emr.jobs.EMRAllApps;
import com.qa.scripts.emr.jobs.EmrInefficientApps;
import com.qa.scripts.jobs.applications.InefficientApps;
import com.qa.utils.LoggingUtils;
import com.qa.utils.MouseActions;
import com.qa.utils.WaitExecuter;
import com.qa.utils.actions.UserActions;
import com.relevantcodes.extentreports.LogStatus;
import org.testng.Assert;
import org.testng.annotations.Test;
@Marker.EmrInefficientApps
public class TC_EMR_JIA04 extends BaseClass {

    private final LoggingUtils LOGGER = new LoggingUtils(TC_EMR_JIA04.class);
    /**
     * Verify Application with Inefficient events are listed for Custom Range
     */
    @Test()
    public void TC_JIA04_verifyCustomRangeDatePicker() {
        test = extent.startTest("TC_JIA04_verifyCustomRangeDatePicker", "Verify " +
                "Application with Inefficient events are listed for Custom Range");
        test.assignCategory(" Jobs / InEfficient Apps");

        WaitExecuter waitExecuter = new WaitExecuter(driver);

        //Click on Jobs tab
        EmrSubTopPanelModulePageObject subTopPanelModulePageObject = new EmrSubTopPanelModulePageObject(driver);
        UserActions actions = new UserActions(driver);
        EmrApplicationsPageObject applicationsPageObject = new EmrApplicationsPageObject(driver);
        waitExecuter.sleep(2000);
        actions.performActionWithPolling(subTopPanelModulePageObject.jobs, UserAction.CLICK);

        // Get setup done for InefficientApps page
        EmrInefficientApps inefficientApps = new EmrInefficientApps(driver);
        inefficientApps.setupInefficientApps();
        test.log(LogStatus.INFO, "setup for InefficientApps done.");

        // Click on datepicker button
        DatePicker datePicker = new DatePicker(driver);
        datePicker.clickOnDatePicker();
        // Click on custom range
        datePicker.selectCustomRange();
        waitExecuter.sleep(3000);

        applicationsPageObject.startDatePicker.clear();
        applicationsPageObject.startDatePicker.sendKeys("08/07/2020");
        waitExecuter.sleep(2000);
        applicationsPageObject.applyBtn.click();
        test.log(LogStatus.INFO, "Successfully clicked on custom date range Apply button.");

        waitExecuter.sleep(2000);
        Assert.assertTrue(inefficientApps.verifyInefficientTbl(), "Inefficient Apps table is missing.");
        test.log(LogStatus.PASS, "Inefficient Apps table is present");
    }


}
