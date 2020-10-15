package com.qa.testcases.cluster.overview;

import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.pagefactory.HomePageObject;
import com.qa.scripts.DatePicker;
import com.qa.scripts.HomePage;
import com.qa.utils.JavaScriptExecuter;
import com.qa.utils.WaitExecuter;
import com.relevantcodes.extentreports.LogStatus;
import org.testng.Assert;
import org.testng.annotations.Test;
/**
 * @author Birender Kumar
 */
@Marker.ClusterOverview
@Marker.All
public class TC_CO_17 extends BaseClass {

    private WaitExecuter waitExecuter;
    private DatePicker datePicker;

    @Test(dataProvider = "clusterid-data-provider")
    public void TC_CO_17_VerifyAlertsFunc(String clusterId) {
        test = extent.startTest("TC_CO_17_VerifyAlertsFunc: "+clusterId, "Verify alerts functionality.");
        test.assignCategory("4620 - Cluster Overview");

        HomePage homePage = new HomePage(driver);
        homePage.selectMultiClusterId(clusterId);

        WaitExecuter executer = new WaitExecuter(driver);
        executer.sleep(2000);

        waitExecuter = new WaitExecuter(driver);
        datePicker = new DatePicker(driver);

        // Click on datepicker button
        DatePicker datePicker = new DatePicker(driver);
        waitExecuter.sleep(3000);

        datePicker.clickOnDatePicker();
        waitExecuter.sleep(1000);

        // Click on custom range
        datePicker.selectCustomRange();
        waitExecuter.sleep(1000);

        // Set Start date by substracting days from current date and end date as currentdate
        datePicker.setCurrentAndPastDate(-30);
        waitExecuter.sleep(1000);

        // Click on apply button of Cluster
        datePicker.clickOnCustomDateApplyBtn();
        waitExecuter.sleep(1000);

        //TBD check for Alerts: Should display all the auto action alerts that is violated.
        HomePageObject hpo = new HomePageObject(driver);
        Assert.assertEquals(hpo.alertsText.getText(), "Alerts","Alerts not displayed.");

        //Check alert displayed at homepage
        //JavaScriptExecuter.clickOnElement(driver,hpo.firstAlertRow);
        Boolean boolAlertRow =hpo.firstAlertRow.isDisplayed();
        Assert.assertTrue(boolAlertRow,"Alerts are displayed.");
        test.log(LogStatus.PASS, "Successfully verified Alerts is displayed.");

    }
}
