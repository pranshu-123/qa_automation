package com.qa.testcases.cluster.impala.chargeback;

import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.pagefactory.clusters.ChargebackImpalaPageObject;
import com.qa.scripts.DatePicker;
import com.qa.scripts.HomePage;
import com.qa.scripts.clusters.impala.ChargeBackImpala;
import com.qa.utils.WaitExecuter;
import com.relevantcodes.extentreports.LogStatus;
import org.testng.Assert;
import org.testng.annotations.Test;

@Marker.All
@Marker.ImpalaChargeback
public class TC_CB_03 extends BaseClass {


    @Test(dataProvider = "clusterid-data-provider", description = "P0-Verify that the user can select a cluster")
    public void TC_CB_03_Verifytheusercanselectacluster(String clusterId) {

        test = extent.startTest("TC_CB_03_Verifytheusercanselectacluster " + clusterId, "Verify the user can select a cluster ");
        test.assignCategory(" Cluster - Impala Chargeback");

        WaitExecuter waitExecuter = new WaitExecuter(driver);
        DatePicker picker = new DatePicker(driver);
        test.log(LogStatus.PASS, "verify Clusterid : " + clusterId);

        ChargebackImpalaPageObject chargebackImpalaPageObject = new ChargebackImpalaPageObject(driver);
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

        // Get CPU hours from table
        double totalCPUHoursFromTable = chargeBackImpala.getTotalCPUHoursFromTable();
        waitExecuter.sleep(3000);

        double headerValue = chargeBackImpala.getCPUHoursFromGraphHeader();
        if (!chargebackImpalaPageObject.CPUHoursFromGraphHeader.getText().equals("0.00")) {

            int avgSecondDiff = chargeBackImpala.getCPUHourListFromTable().size() / 2;
            double differenceInSeconds = headerValue - totalCPUHoursFromTable;
            // Compare header CPU hours to total table hours
            Assert.assertTrue(differenceInSeconds < avgSecondDiff,
                    "The seconds difference is greater than that of average seconds exemption ");
            test.log(LogStatus.PASS, "The seconds difference is less than the expected value.");

        } else
            Assert.assertEquals(headerValue, totalCPUHoursFromTable,
                    "Total CPU hours from table does not match header CPU hours when converted to seconds");
        test.log(LogStatus.PASS, "Total CPU hours from table match the header value of CPU graph.");


        String donutchart = chargeBackImpala.getdonutchartHeader();
        String donutchartvalue = donutchart.replaceAll("[^\\d-]", "");
        Assert.assertTrue(chargeBackImpala.getdonutchartHeader().matches(donutchart),
                "verify the total Jobs donut chart in graph heading");
        test.log(LogStatus.PASS, "verify the total Jobs donut chart in graph heading" + donutchart);

        String impalaqueriestable = chargeBackImpala.getImpalatableHeader();
        String impalaqueriestablevalue = impalaqueriestable.replaceAll("[^\\d-]", "");
        Assert.assertEquals(donutchartvalue, impalaqueriestablevalue, "Jobs Count does not matches.");
        Assert.assertTrue(chargeBackImpala.getImpalatableHeader().matches(impalaqueriestable),
                "verify the total Jobs donut chart in graph heading");
        test.log(LogStatus.PASS, "verify the total Impala queries table in graph heading" + impalaqueriestable);
    }
}