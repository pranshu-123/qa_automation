package com.qa.testcases.cluster.yarn.chargeback;

import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.pagefactory.clusters.ChargebackYarnPageObject;
import com.qa.scripts.DatePicker;
import com.qa.scripts.HomePage;
import com.qa.scripts.clusters.impala.ChargeBackImpala;
import com.qa.scripts.clusters.yarn.ChargeBackYarn;
import com.qa.utils.JavaScriptExecuter;
import com.qa.utils.Log;
import com.qa.utils.WaitExecuter;
import com.relevantcodes.extentreports.LogStatus;
import org.testng.annotations.Test;

@Marker.YarnChargeback
@Marker.All
public class YC_022 extends BaseClass {
    /**
     * Verify User is able to access Yarn chargeback page and Verify the cost edit fields
     */
    private WaitExecuter waitExecuter;
    private ChargeBackYarn ChargeBackYarn;
    private ChargebackYarnPageObject chargebackYarnPageObject;
    private DatePicker picker;

    @Test(dataProvider = "clusterid-data-provider",description="P0-Verify the cost edit fields on the yarn chargeback page")
    public void YC_022_Verifythecosteditfields(String clusterId) {
        test = extent.startTest("YC_021_Verifythecalender"+clusterId, "Verify the cost edit fields");
        test.assignCategory(" Cluster - Yarn Chargeback");
        Log.startTestCase("YC_022_Verifythecosteditfields");

        WaitExecuter waitExecuter = new WaitExecuter(driver);
        ChargeBackYarn chargeBackYarn = new ChargeBackYarn(driver);
        ChargeBackYarn = new ChargeBackYarn(driver);
        picker = new DatePicker(driver);
        chargeBackYarn.selectYarnChargeback();
        waitExecuter.sleep(2000);

        chargeBackYarn.selectMultiClusterId(clusterId);
        chargeBackYarn.selectChargebackType("Yarn");
        waitExecuter.sleep(2000);


        waitExecuter.sleep(1000);
        picker.clickOnDatePicker();
        Log.info("Click on DatePicker.");
        waitExecuter.sleep(1000);
        picker.selectThisMonth();
        Log.info("Click on This Month.");

        // Set vcore and memory per hour values
        waitExecuter.sleep(1000);
        chargebackYarnPageObject.setChargebackVcorePerHour.sendKeys("5");
        Log.info("Set Chargeback Vcore perHour.");
        waitExecuter.sleep(1000);
        chargebackYarnPageObject.setChargebackMemoryPerHour.sendKeys("5");
        Log.info("Set Chargeback Memory perHour.");
        waitExecuter.sleep(1000);
        chargebackYarnPageObject.applyButton.click();
        Log.info("Clicked on Apply Button.");
        Log.endTestCase("YC_022_Verifythecosteditfields");
    }
}
