package com.qa.testcases.cluster.yarn.chargeback;

import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.scripts.DatePicker;
import com.qa.scripts.HomePage;
import com.qa.scripts.clusters.impala.ChargeBackImpala;
import com.qa.scripts.clusters.yarn.ChargeBackYarn;
import com.qa.utils.WaitExecuter;
import com.relevantcodes.extentreports.LogStatus;
import org.testng.annotations.Test;

@Marker.YarnChargeback
@Marker.All
public class YC_031 extends BaseClass {

    /**
     * Verify Yarn Jobs Table on Yarn chrageback page, should contain the list of yarn jobs and the details
     */

    @Test(dataProvider = "clusterid-data-provider",description="P0-Verify that the yarn jobs table should contain the list of yarn jobs and the details.")
    public void YC_031_verifyYarnJobsTableOnYarnChargeBackPage(String clusterId) {
        test = extent.startTest("YC_031_verifyYarnJobsTableOnYarnChargeBackPage: " + clusterId, "Verify yarn jobs table and the details on Yarn chrageback page.");
        test.assignCategory(" Cluster - Yarn Chargeback");

        ChargeBackYarn chargeBackYarn = new ChargeBackYarn(driver);
        chargeBackYarn.selectYarnChargeback();
        test.log(LogStatus.PASS, "User is redirected to cluster yarn chargeback page");
        WaitExecuter waitExecuter = new WaitExecuter(driver);
        //ClusterId selection
        HomePage homePage = new HomePage(driver);
        homePage.selectMultiClusterId(clusterId);

        ChargeBackImpala chargeBackImpala = new ChargeBackImpala(driver);
        chargeBackImpala.selectImpalaChargeback("Yarn");

        // Click on datepicker button
        DatePicker datePicker = new DatePicker(driver);
        datePicker.clickOnDatePicker();
        datePicker.selectLast30Days();
        test.log(LogStatus.PASS, "Selected 30 Days date picker for Yarn chargeback page");

        // Select Yarn Charge back
        chargeBackYarn.clickOnGroupBySearchBox();
        String optionName = "User";
        chargeBackYarn.selectOptionsInGroupBy(optionName);
        test.log(LogStatus.PASS, "Selected " + optionName + " as option in Group By filter, yarn chargeback page");

        //Checking Yarn Jobs Table Records populated
        chargeBackYarn.getYarnJobsTableRecord();
        test.log(LogStatus.PASS, "Verified Yarn Jobs Table is available on Yarn chargeback page");

    }
}
