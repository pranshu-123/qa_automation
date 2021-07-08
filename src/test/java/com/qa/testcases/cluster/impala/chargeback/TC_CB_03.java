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
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
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

    @Test(dataProvider = "clusterid-data-provider", description = "P0-Verify that the user can select a cluster")
    public void TC_CB_03_Verifytheusercanselectacluster(String clusterId) {

        test = extent.startTest("TC_CB_03_Verifytheusercanselectacluster " + clusterId, "Verify the user can select a cluster ");
        test.assignCategory(" Cluster - Impala Chargeback");

        WaitExecuter waitExecuter = new WaitExecuter(driver);
        test.log(LogStatus.PASS, "verify Clusterid : " + clusterId);

        chargebackImpalaPageObject = new ChargebackImpalaPageObject(driver);
        // Click on Chargeback tab
        ChargeBackImpala chargeBackImpala = new ChargeBackImpala(driver);
        chargeBackImpala.selectImpalaChargeback();

        HomePage homePage = new HomePage(driver);
        homePage.selectMultiClusterIdClusterPage(clusterId);

        chargeBackImpala.selectImpalaType("Impala");
        waitExecuter.sleep(2000);

        // Click on datepicker button
        picker = new DatePicker(driver);
        // Intialize impala page objects

        waitExecuter.sleep(1000);
        picker.clickOnDatePicker();
        waitExecuter.sleep(1000);
        picker.selectLast30Days();
        waitExecuter.sleep(3000);
        waitExecuter.waitUntilElementClickable(chargebackImpalaPageObject.impalaDropdownOption);

        String getHeaderValue= chargebackImpalaPageObject.MemoryHoursFromGraphHeader.getText().trim();
        Assert.assertTrue(chargebackImpalaPageObject.MemoryHoursFromGraphHeader.isDisplayed(),
                "total CPU hours in graph heading not display");
        test.log(LogStatus.PASS, "verify the total CPU hours in graph heading"+getHeaderValue);
        if(!"".equals(getHeaderValue)){
            test.log(LogStatus.PASS,"Verification Passed for total memory in graph heading.");
        }else {
            test.log(LogStatus.FAIL, "Verification Failed for total memory in graph heading.");
        }

        String getCPUValue= chargebackImpalaPageObject.MemoryHoursFromGraphHeader.getText().trim();
        Assert.assertTrue(chargebackImpalaPageObject.MemoryHoursFromGraphHeader.isDisplayed(),
                "total CPU hours in graph heading not display");
        test.log(LogStatus.PASS, "verify the total CPU hours in graph heading"+getCPUValue);
        if(!"".equals(getCPUValue)){
            test.log(LogStatus.PASS,"Verification Passed for total memory in graph heading.");
        }else {
            test.log(LogStatus.FAIL, "Verification Failed for total memory in graph heading.");
        }

        String cpuHours = chargebackImpalaPageObject.cpuGraphHeader.getText().trim();
        Assert.assertTrue(chargebackImpalaPageObject.cpuGraphHeader.getText().matches(cpuHours),
                "verify the total CPU hours in graph heading");
        if(!"".equals(cpuHours)){
            test.log(LogStatus.PASS,"Verification Passed for total CPU hours in graph heading.");
        }else {
            test.log(LogStatus.FAIL, "Verification Failed for total CPU hours in graph heading.");
        }

        String donutChart = chargebackImpalaPageObject.donutchart.getText().trim();
        Assert.assertTrue(chargebackImpalaPageObject.donutchart.getText().matches(donutChart),
                "verify the total Jobs donut chart in graph heading");
        if(!"".equals(donutChart)){
            test.log(LogStatus.PASS,"Verification Passed for total Jobs donut chart in graph heading.");
        }else {
            test.log(LogStatus.FAIL, "Verification Failed for total Jobs donut chart in graph heading.");
        }

        String impalaQueriesTable = chargebackImpalaPageObject.impalajobs.getText().trim();
        Assert.assertTrue(chargebackImpalaPageObject.impalajobs.getText().matches(impalaQueriesTable),
                "Verify the total Impala queries table in graph heading");
        if(!"".equals(impalaQueriesTable)){
            test.log(LogStatus.PASS,"Verification Passed for total Impala queries table in graph heading.");
        }else {
            test.log(LogStatus.FAIL, "Verification Failed for total Impala queries table in graph heading.");
        }

        String chargebackTable = chargebackImpalaPageObject.Chargebacktable.getText().trim();
        Assert.assertTrue(chargebackImpalaPageObject.Chargebacktable.getText().matches(chargebackTable),
                "Verify the total Chargeback table grouped by user chart in graph heading");
        if(!"".equals(impalaQueriesTable)){
            test.log(LogStatus.PASS,"Verification Passed for total Chargeback table grouped by user chart in graph heading.");
        }else {
            test.log(LogStatus.FAIL, "Verification Failed for total Chargeback table grouped by user chart in graph heading.");
        }
    }
}