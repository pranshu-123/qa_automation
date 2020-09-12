package com.qa.testcases.cluster.yarn.chargeback;

import com.qa.base.BaseClass;
import com.qa.scripts.DatePicker;
import com.qa.scripts.HomePage;
import com.qa.scripts.clusters.yarn.ChargeBackYarn;
import com.relevantcodes.extentreports.LogStatus;
import org.testng.Assert;
import org.testng.annotations.Test;

public class YC_028 extends BaseClass {

    /**
     * Verify Group By Table, Should have mandatory columns[Job count, CPU and Memory Hours] on Yarn chrageback page
     */
    @Test(dataProvider = "clusterid-data-provider")
    public void YC_028_verifyGroupByTableForMandatoryColumnsOnYarnChargeBackPage(String clusterId) {
        test = extent.startTest("YC_028_verifyGroupByTableForMandatoryColumnsOnYarnChargeBackPage: " + clusterId, "Verify Click on groupby edit field, Yarn chrageback page.");
        test.assignCategory("4620 Cluster - Yarn Chargeback");

        ChargeBackYarn chargeBackYarn = new ChargeBackYarn(driver);
        chargeBackYarn.selectYarnChargeback();
        test.log(LogStatus.PASS, "User is redirected to cluster yarn chargeback page");

        //ClusterId selection
        HomePage homePage = new HomePage(driver);
        homePage.selectMultiClusterId(clusterId);

        // Click on datepicker button
        DatePicker datePicker = new DatePicker(driver);
        datePicker.clickOnDatePicker();
        datePicker.selectLast30Days();
        test.log(LogStatus.PASS, "Selected 30 Days date picker for Yarn chargeback page");

        chargeBackYarn.clickOnGroupBySearchBox();
        String optionName = "User";
        chargeBackYarn.selectOptionsInGroupBy(optionName);
        test.log(LogStatus.PASS, "Selected "+ optionName + " as option in Group By filter, yarn chargeback page");

        chargeBackYarn.getResultsGroupedByTableRecords();
        test.log(LogStatus.PASS, "Verified Group By Table is available on Yarn chargeback page");
        Assert.assertTrue(chargeBackYarn.validateHeaderCoulmnNameInGroupByTable(),"Any one columns from ['Job Count', 'CPU Hours', 'Memory Hours'] is not present.");
        test.log(LogStatus.PASS, "Verified columns ['Job Count', 'CPU Hours', 'Memory Hours'] are present in Group By Table.");

    }
}
