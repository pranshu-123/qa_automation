package com.qa.testcases.cluster.impala.chargeback;

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

import java.util.List;

public class TC_CB_29 extends BaseClass {
    private WaitExecuter waitExecuter;
    private ChargeBackImpala chargebackImpala;
    private ChargebackImpalaPageObject chargebackImpalaPageObject;
    private DatePicker picker;

    @Test(dataProvider = "clusterid-data-provider")
    public void TC_CB_29_Datavalidation(String clusterId) {

        test = extent.startTest("TC_CB_29_Datavalidation " + clusterId, "Verify the number of applications displayed in the chargeback report");
        test.assignCategory("4620 Cluster - Impala Chargeback");

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
        picker.selectLastOneHour();
        waitExecuter.sleep(1000);
        chargebackImpalaPageObject.groupBySearchBox.click();
        waitExecuter.sleep(1000);
        chargebackImpala.selectgroupby();

        test.log(LogStatus.PASS, "Jobs count displayed.");
        System.out.println("Jobs count displayed");

        String donutchart = chargebackImpala.getdonutchartHeader();
        String donutchartvalue = donutchart.replaceAll("[^\\d-]", "");
        Assert.assertTrue(chargebackImpala.getdonutchartHeader().matches(donutchart),
                "verify the total Jobs donut chart in graph heading");
        test.log(LogStatus.PASS, "verify the total Jobs donut chart in graph heading" + donutchart);

        String impalaqueriestable = chargebackImpala.getImpalatableHeader();
        String impalaqueriestablevalue = impalaqueriestable.replaceAll("[^\\d-]", "");
        Assert.assertEquals(donutchartvalue, impalaqueriestablevalue, "Jobs Count does not matches.");
        Assert.assertTrue(chargebackImpala.getImpalatableHeader().matches(impalaqueriestable),
                "verify the total Jobs donut chart in graph heading");
        test.log(LogStatus.PASS, "verify the total Impala queries table in graph heading" + impalaqueriestable);

        String chargebacktable = chargebackImpala.getchargebacktableHeader();
        test.log(LogStatus.PASS, "verify the total Chargeback table grouped by user chart in graph heading" + chargebacktable);

    }
}