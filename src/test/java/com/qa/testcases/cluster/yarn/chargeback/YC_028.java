package com.qa.testcases.cluster.yarn.chargeback;

import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.scripts.DatePicker;
import com.qa.scripts.HomePage;
import com.qa.scripts.clusters.impala.ChargeBackImpala;
import com.qa.scripts.clusters.yarn.ChargeBackYarn;
import com.qa.utils.WaitExecuter;
import com.relevantcodes.extentreports.LogStatus;
import org.testng.Assert;
import org.testng.annotations.Test;

@Marker.YarnChargeback
@Marker.All
public class YC_028 extends BaseClass {

    /**
     * Verify Group By Table, Should have mandatory columns[Job count, CPU and Memory Hours] on Yarn chrageback page
     */
    @Test(dataProvider = "clusterid-data-provider",description="P0-Verify that the user must be select the total values for each type of graph.")
    public void YC_028_verifyGroupByTableForMandatoryColumnsOnYarnChargeBackPage(String clusterId) {
        test = extent.startTest("YC_028_verifyGroupByTableForMandatoryColumnsOnYarnChargeBackPage: " + clusterId, "Verify Click on groupby edit field, Yarn chrageback page.");
        test.assignCategory(" Cluster - Yarn Chargeback");

        ChargeBackYarn chargeBackYarn = new ChargeBackYarn(driver);
        WaitExecuter waitExecuter = new WaitExecuter(driver);
        chargeBackYarn.selectYarnChargeback();
        waitExecuter.sleep(2000);

        // Select the cluster
        test.log(LogStatus.INFO, "Select clusterId : "+clusterId);
        HomePage homePage = new HomePage(driver);
        homePage.selectMultiClusterId(clusterId);
        waitExecuter.sleep(1000);
        chargeBackYarn.selectChargebackType("Yarn");
        waitExecuter.sleep(2000);

        // Click on datepicker button
        DatePicker datePicker = new DatePicker(driver);
        datePicker.clickOnDatePicker();
        datePicker.selectLast30Days();
        waitExecuter.waitUntilPageFullyLoaded();
        test.log(LogStatus.INFO, "Selected 30 Days date picker for Yarn chargeback page");

        chargeBackYarn.clickOnGroupBySearchBox();

        String optionName = "User";
        chargeBackYarn.selectOptionsInGroupBy(optionName);
        test.log(LogStatus.INFO, "Selected "+ optionName + " as option in Group By filter, yarn chargeback page");

        chargeBackYarn.getResultsGroupedByTableRecords();
        test.log(LogStatus.PASS, "Verified Group By Table is available on Yarn chargeback page");
        Assert.assertTrue(chargeBackYarn.validateHeaderCoulmnNameInGroupByTable(),"Any one columns from ['Job Count', 'CPU Hours', 'Memory Hours'] is not present.");
        test.log(LogStatus.PASS, "Verified columns ['Job Count', 'CPU Hours', 'Memory Hours'] are present in Group By Table.");

    }
}
