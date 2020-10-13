package com.qa.testcases.jobs.applications.inefficientapps;

import com.qa.base.BaseClass;
import com.qa.pagefactory.TopPanelComponentPageObject;
import com.qa.pagefactory.jobs.ApplicationsPageObject;
import com.qa.scripts.DatePicker;
import com.qa.scripts.jobs.applications.InefficientApps;
import com.qa.utils.MouseActions;
import com.qa.utils.WaitExecuter;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC_JIA04 extends BaseClass {

    /**
     * Verify Application with Inefficient events are listed for Custom Range
     */
    @Test(dataProvider = "clusterid-data-provider")
    public void TC_JIA04_verifyCustomRangeDatePicker(String clusterId) {
        test = extent.startTest("TC_JIA04_verifyCustomRangeDatePicker" + clusterId, "Verify " +
                "Application with Inefficient events are listed for Custom Range");
        test.assignCategory(" Jobs / InEfficient Apps");

        WaitExecuter waitExecuter = new WaitExecuter(driver);
        test.log(LogStatus.PASS, "Passed Parameter Is : " + clusterId);

        //Click on Jobs tab
        TopPanelComponentPageObject topPanelComponentPageObject = new TopPanelComponentPageObject(driver);
        waitExecuter.sleep(2000);
        MouseActions.clickOnElement(driver, topPanelComponentPageObject.jobs);

        //Click on inefficientApps tab
        ApplicationsPageObject applicationsPageObject = new ApplicationsPageObject(driver);
        waitExecuter.sleep(2000);
        MouseActions.clickOnElement(driver, applicationsPageObject.inefficientApps);

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

        InefficientApps inefficientApps = new InefficientApps(driver);
        waitExecuter.sleep(2000);
        Assert.assertTrue(inefficientApps.verifyInefficientTbl(), "Inefficient Apps table is missing.");
        test.log(LogStatus.PASS, "Inefficient Apps table is present");
    }


}
