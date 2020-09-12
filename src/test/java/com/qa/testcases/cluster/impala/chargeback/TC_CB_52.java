package com.qa.testcases.cluster.impala.chargeback;

import com.qa.base.BaseClass;
import com.qa.pagefactory.CommonPageObject;
import com.qa.pagefactory.clusters.ChargebackImpalaPageObject;
import com.qa.scripts.DatePicker;
import com.qa.scripts.HomePage;
import com.qa.scripts.clusters.impala.ChargeBackImpala;
import com.qa.utils.DateUtils;
import com.qa.utils.JavaScriptExecuter;
import com.qa.utils.WaitExecuter;
import com.relevantcodes.extentreports.LogStatus;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.*;

public class TC_CB_52 extends BaseClass {
    private WaitExecuter waitExecuter;
    private ChargeBackImpala chargebackImpala;
    private ChargebackImpalaPageObject chargebackImpalaPageObject;
    private DatePicker picker;

    @Test(dataProvider = "clusterid-data-provider")
    public void TC_CB_52_VerifyValidatethecostsfordecimalnumbers(String clusterId) {
        test = extent.startTest("TC_CB_52_VerifyValidatethecostsfordecimalnumbers"+clusterId, "Verify  the costs for decimal numbers");
        test.assignCategory("4620 Cluster - Impala Chargeback");
        WaitExecuter waitExecuter = new WaitExecuter(driver);
        test.log(LogStatus.PASS, "verify Clusterid : " + clusterId);

        HomePage homePage = new HomePage(driver);
        homePage.selectMultiClusterId(clusterId);
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

        // Set vcore and memory per hour values
        waitExecuter.sleep(1000);
        chargebackImpalaPageObject.setChargebackVcorePerHour.sendKeys("0.987");
        waitExecuter.sleep(1000);
        chargebackImpalaPageObject.setChargebackMemoryPerHour.sendKeys("0.987");
        waitExecuter.sleep(1000);
        chargebackImpalaPageObject.applyButton.click();

        // Get CPU hours list from chargeback table
        waitExecuter.sleep(1000);
        List<Double> cpuHoursList = chargebackImpala.getUsersCPUHoursFromTable();

        // Get CPU cost list from chargeback table
        waitExecuter.sleep(1000);
        List<Double> cpuCostsList = chargebackImpala.getUsersCPUHoursCostFromTable();

        // Validate CPU calculated cost and cost from chargeback table
        waitExecuter.sleep(1000);
        Assert.assertTrue(chargebackImpala.compareCPUCostToCalculatedCost(cpuCostsList, cpuHoursList, 0.987),
                "The table cost do not match with calculated cost of CPU");
        test.log(LogStatus.PASS, "The table cost match with calculated cost of CPU.");

    }

    @Test(dataProvider = "clusterid-data-provider")
    public void verifyMemoryCost(String clusterId) {
        // Select the cluster
        waitExecuter.sleep(1000);
        HomePage homePage = new HomePage(driver);
        homePage.selectMultiClusterId(clusterId);
        // Get Memory usage in hours from chargeback table
        waitExecuter.sleep(1000);
        List<Double> memoryHoursList = chargebackImpala.getUsersMemoryHoursFromTable();

        // Get CPU cost list from chargeback table
        waitExecuter.sleep(1000);
        List<Double> memoryCostsList = chargebackImpala.getUsersMemoryHoursCostFromTable();

        // Validate Memory calculated cost and cost from chargeback table
        Assert.assertTrue(chargebackImpala.compareCPUCostToCalculatedCost(memoryCostsList, memoryHoursList, 0.987),
                "The table cost do not match with calculated cost of Memory.");
        test.log(LogStatus.PASS, "The table cost do match with calculated cost of Memory.");
    }
}