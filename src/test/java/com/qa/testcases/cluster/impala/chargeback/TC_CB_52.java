package com.qa.testcases.cluster.impala.chargeback;

import com.qa.annotations.Marker;
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
import java.util.logging.Logger;

import static org.testng.Assert.*;

@Marker.All
@Marker.ImpalaChargeback
public class TC_CB_52 extends BaseClass {
    private WaitExecuter waitExecuter;
    private ChargeBackImpala chargebackImpala;
    private ChargebackImpalaPageObject chargebackImpalaPageObject;
    private DatePicker picker;
    private HomePage homePage;
    private static final Logger LOGGER = Logger.getLogger(TC_CB_52.class.getName());


    @Test(dataProvider = "clusterid-data-provider")
    public void TC_CB_52_VerifyValidatethecostsfordecimalnumbers(String clusterId) {
        test = extent.startTest("TC_CB_52_VerifyValidatethecostsfordecimalnumbers"+clusterId, "Verify  the costs for decimal numbers");
        test.assignCategory(" Cluster - Impala Chargeback");
        WaitExecuter waitExecuter = new WaitExecuter(driver);
        test.log(LogStatus.INFO, "verify Clusterid : " + clusterId);

        // Initialize all classes objects
        test.log(LogStatus.INFO, "Initialize all class objects");
        LOGGER.info("Initialize all class objects");
        waitExecuter = new WaitExecuter(driver);
        chargebackImpala = new ChargeBackImpala(driver);
        picker = new DatePicker(driver);
        homePage = new HomePage(driver);
        chargebackImpalaPageObject = new ChargebackImpalaPageObject(driver);

        // Click on Chargeback tab
        test.log(LogStatus.INFO, "Click on Chargeback tab");
        LOGGER.info("Click on Chargeback tab");
        waitExecuter.waitUntilElementClickable(chargebackImpalaPageObject.clusterChargeBackTab);
        JavaScriptExecuter.clickOnElement(driver, chargebackImpalaPageObject.clusterChargeBackTab);
        waitExecuter.sleep(1000);

        // Click on chargeback dropdown and select Impala
        test.log(LogStatus.INFO, "Click on chargeback dropdown and select Impala");
        LOGGER.info("Click on chargeback dropdown and select Impala");
        waitExecuter.sleep(1000);
        JavaScriptExecuter.clickOnElement(driver, chargebackImpalaPageObject.chargeBackDropdownOptionsButton);
        waitExecuter.sleep(1000);
        waitExecuter.waitUntilElementClickable(chargebackImpalaPageObject.chargeBackDropdownImpalaOption);
        chargebackImpalaPageObject.chargeBackDropdownImpalaOption.click();
        waitExecuter.sleep(1000);

        // Select the cluster
        test.log(LogStatus.INFO, "Select clusterId : " + clusterId);
        LOGGER.info("Select clusterId : " + clusterId);
        homePage.selectMultiClusterId(clusterId);
        waitExecuter.sleep(1000);

        // Select last 30 days from date picker
        test.log(LogStatus.INFO, "Select last 30 days from date picker");
        LOGGER.info("Select last 30 days from date picker");
        picker.clickOnDatePicker();
        waitExecuter.sleep(1000);
        picker.selectLast30Days();
        waitExecuter.sleep(1000);

        // Set vcore and memory per hour values
        test.log(LogStatus.INFO, "Set vcore and memory per hour values");
        LOGGER.info("Set vcore and memory per hour values");
        chargebackImpalaPageObject.setChargebackVcorePerHour.sendKeys("0.987");
        waitExecuter.sleep(2000);
        chargebackImpalaPageObject.setChargebackMemoryPerHour.sendKeys("0.987");
        waitExecuter.sleep(2000);
        chargebackImpalaPageObject.applyButton.click();
        waitExecuter.sleep(2000);

        // Get CPU hours list from chargeback table
        test.log(LogStatus.INFO, "Get CPU hours consumption of every user from chargeback table");
        LOGGER.info("Get CPU hours consumption of every user from chargeback table");
        List<Double> cpuHoursList = chargebackImpala.getUsersCPUHoursFromTable();
        waitExecuter.sleep(1000);

        // Get CPU cost list from chargeback table
        test.log(LogStatus.INFO, "Get CPU consumption cost of every user from chargeback table");
        LOGGER.info("Get CPU consumption cost of every user from chargeback table");
        List<Double> cpuCostsList = chargebackImpala.getUsersCPUHoursCostFromTable();
        waitExecuter.sleep(1000);

        // Validate CPU calculated cost and cost from chargeback table
        Assert.assertTrue(chargebackImpala.compareCPUCostToCalculatedCost(cpuCostsList, cpuHoursList, 0.987),
                "The table cost do not match with calculated cost of CPU");
        test.log(LogStatus.PASS, "The table cost match with calculated cost of CPU.");


    }
}