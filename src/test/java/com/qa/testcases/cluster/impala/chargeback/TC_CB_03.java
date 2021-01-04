package com.qa.testcases.cluster.impala.chargeback;

import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.pagefactory.clusters.ChargebackImpalaPageObject;
import com.qa.scripts.DatePicker;
import com.qa.scripts.HomePage;

import com.qa.scripts.clusters.impala.ChargeBackImpala;

import com.qa.utils.JavaScriptExecuter;
import com.qa.utils.WaitExecuter;
import com.relevantcodes.extentreports.LogStatus;
import org.testng.Assert;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

@Marker.All
@Marker.ImpalaChargeback
public class TC_CB_03 extends BaseClass {
    private WaitExecuter waitExecuter;
    private ChargeBackImpala chargebackImpala;
    private ChargebackImpalaPageObject chargebackImpalaPageObject;
    private DatePicker picker;

    @Test(dataProvider = "clusterid-data-provider")
    public void TC_CB_03_Verifytheusercanselectacluster(String clusterId) {

        test = extent.startTest("TC_CB_03_Verifytheusercanselectacluster " + clusterId, "Verify the user can select a cluster ");
        test.assignCategory(" Cluster - Impala Chargeback");

        WaitExecuter waitExecuter = new WaitExecuter(driver);
        test.log(LogStatus.PASS, "verify Clusterid : " + clusterId);

        HomePage homePage = new HomePage(driver);
        homePage.selectMultiClusterId(clusterId);
        // Click on datepicker button
        chargebackImpala = new ChargeBackImpala(driver);
        picker = new DatePicker(driver);
        // Intialize impala page objects
        chargebackImpalaPageObject = new ChargebackImpalaPageObject(driver);
        // Click on Chargeback tab
        waitExecuter.waitUntilElementClickable(chargebackImpalaPageObject.clusterChargeBackTab);
        JavaScriptExecuter.clickOnElement(driver, chargebackImpalaPageObject.clusterChargeBackTab);
        // Click on chargeback dropdown
        waitExecuter.sleep(1000);
        JavaScriptExecuter.clickOnElement(driver, chargebackImpalaPageObject.chargeBackDropdownOptionsButton);
        // Selecting Impala chargeback
        waitExecuter.waitUntilElementClickable(chargebackImpalaPageObject.chargeBackDropdownImpalaOption);
        waitExecuter.sleep(1000);
        chargebackImpalaPageObject.chargeBackDropdownImpalaOption.click();
        waitExecuter.sleep(1000);
        picker.clickOnDatePicker();
        waitExecuter.sleep(1000);
        picker.selectLast12Hour();
        waitExecuter.sleep(1000);

        waitExecuter.sleep(1000);
        String memoryHourInSeconds = chargebackImpala.getMemoryGraphHeader();
        Assert.assertTrue(chargebackImpala.getMemoryGraphHeader().matches(memoryHourInSeconds),
            "verify the total memory in graph heading");
        test.log(LogStatus.PASS, "verify the total memory in graph heading");

        String cpuHours = chargebackImpala.getCpuGraphHeader();
        Assert.assertTrue(chargebackImpala.getCpuGraphHeader().matches(cpuHours),
            "verify the total CPU hours in graph heading");
        test.log(LogStatus.PASS, "verify the total CPU hours in graph heading");

        String donutchart = chargebackImpala.getdonutchartHeader();
        Assert.assertTrue(chargebackImpala.getdonutchartHeader().matches(donutchart),
            "verify the total Jobs donut chart in graph heading");
        test.log(LogStatus.PASS, "verify the total Jobs donut chart in graph heading");

        String impalaqueriestable = chargebackImpala.getImpalatableHeader();
        Assert.assertTrue(chargebackImpala.getImpalatableHeader().matches(impalaqueriestable),
            "verify the total Jobs donut chart in graph heading");
        test.log(LogStatus.PASS, "verify the total Impala queries table in graph heading");

        String chargebacktable = chargebackImpala.getchargebacktableHeader();
        Assert.assertTrue(chargebackImpala.getchargebacktableHeader().matches(chargebacktable),
            "verify the total Chargeback table grouped by user chart in graph heading");
        test.log(LogStatus.PASS, "verify the total Chargeback table grouped by user chart in graph heading");


    }
}
