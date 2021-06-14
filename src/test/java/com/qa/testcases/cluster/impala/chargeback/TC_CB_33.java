package com.qa.testcases.cluster.impala.chargeback;

import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.enums.chargeback.GroupByOptions;
import com.qa.pagefactory.clusters.ChargebackImpalaPageObject;
import com.qa.scripts.DatePicker;
import com.qa.scripts.HomePage;
import com.qa.scripts.clusters.impala.ChargeBackImpala;
import com.qa.utils.JavaScriptExecuter;
import com.qa.utils.WaitExecuter;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

@Marker.All
@Marker.ImpalaChargeback
public class TC_CB_33 extends BaseClass {
    private WaitExecuter waitExecuter;
    private ChargeBackImpala chargebackImpala;
    private ChargebackImpalaPageObject chargebackImpalaPageObject;
    private DatePicker picker;

    @Test(dataProvider = "clusterid-data-provider",description ="P0-Validate that the user can further filter the chargeback contents in the Donut Chart, Chargeback table, and Impala Queries should match.")
    public void TC_CB_33_ChargebackTable(String clusterId) {

        test = extent.startTest("TC_CB_33_ChargebackTable " + clusterId, "Verify the user can further filter the contents in the chargeback table ");
        test.assignCategory(" Cluster - Impala Chargeback");

        WaitExecuter waitExecuter = new WaitExecuter(driver);
        test.log(LogStatus.PASS, "verify Clusterid : " + clusterId);
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

        HomePage homePage = new HomePage(driver);
        homePage.selectMultiClusterId(clusterId);
        waitExecuter.sleep(1000);
      
        picker.clickOnDatePicker();
        waitExecuter.sleep(1000);
        picker.selectLast30Days();
        waitExecuter.sleep(1000);
        chargebackImpalaPageObject.groupBySearchBox.click();
        waitExecuter.sleep(3000);
        test.log(LogStatus.PASS, "verify the user clicks on each row the User is further group by realUser");
        if (!chargebackImpala.selecttable())
        {
            test.log(LogStatus.PASS, "verify grouped by User table");
        }
        else{
            test.log(LogStatus.FAIL, "Test Failed grouped by User table");
        }

        if (!chargebackImpala.selectQueuetable())
        {
            test.log(LogStatus.PASS, "verify the Group by Queue table");
        }
        else{
            test.log(LogStatus.FAIL, "Test Failed the Group by Queue table");
        }
        if (!chargebackImpala.selectdepttable())
        {
            test.log(LogStatus.PASS, "verify the Group by dept table");
        }
        else{
            test.log(LogStatus.FAIL, "Test Failed the Group by dept table");
        }
        if (!chargebackImpala.selectgrouptable())
        {
            test.log(LogStatus.PASS, "verify the Group by group table");
        }
        else{
            test.log(LogStatus.FAIL, "Test Failed the Group by group table");
        }
        if (!chargebackImpala.selectrealUsertable())
        {
            test.log(LogStatus.PASS, "verify the Group by realUser table");
        }
        else{
            test.log(LogStatus.FAIL, "Test Failed the Group by realUser table");
        }

        if(!chargebackImpala.selectdbstable())
        {
            test.log(LogStatus.PASS, "verify the Group by dbs table");
        }
        else{
            test.log(LogStatus.FAIL, "Test Failed the Group by dbs table");
        }
        }
}
