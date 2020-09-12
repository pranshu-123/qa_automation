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

public class TC_CB_33 extends BaseClass {
    private WaitExecuter waitExecuter;
    private ChargeBackImpala chargebackImpala;
    private ChargebackImpalaPageObject chargebackImpalaPageObject;
    private DatePicker picker;

    @Test(dataProvider = "clusterid-data-provider")
    public void TC_CB_33_ChargebackTable(String clusterId) {

        test = extent.startTest("TC_CB_33_ChargebackTable " + clusterId, "Verify the user can further filter the contents in the chargeback table ");
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
        picker.selectThisMonth();
        waitExecuter.sleep(1000);
        chargebackImpalaPageObject.groupBySearchBox.click();
        waitExecuter.sleep(1000);
        chargebackImpala.selectgroupby();
        waitExecuter.sleep(3000);
        test.log(LogStatus.PASS, "verify the user clicks on each row the User is further group by realUser");
        chargebackImpala.selecttable();
        waitExecuter.sleep(1000);
        test.log(LogStatus.PASS, "verify grouped by User table");
        chargebackImpala.selectQueuetable();
        test.log(LogStatus.PASS, "verify the Group by Queue table");
        waitExecuter.sleep(1000);
        chargebackImpala.selectdepttable();
        test.log(LogStatus.PASS, "verify the Group by dept table");
        waitExecuter.sleep(1000);
        chargebackImpala.selectgrouptable();
        test.log(LogStatus.PASS, "verify the Group by group table");
        waitExecuter.sleep(1000);
        chargebackImpala.selectrealUsertable();
        test.log(LogStatus.PASS, "verify the Group by realUser table");
        waitExecuter.sleep(1000);
        chargebackImpala.selectdbstable();
        test.log(LogStatus.PASS, "verify the Group by dbs table");
        waitExecuter.sleep(1000);
        chargebackImpala.selectinputtables();
        test.log(LogStatus.PASS, "verify the Group by input table");
        waitExecuter.sleep(1000);

    }
}
