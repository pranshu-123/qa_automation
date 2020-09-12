package com.qa.testcases.cluster.yarn.chargeback;

import com.qa.base.BaseClass;
import com.qa.scripts.DatePicker;
import com.qa.scripts.HomePage;
import com.qa.scripts.clusters.yarn.ChargeBackYarn;
import com.relevantcodes.extentreports.LogStatus;
import org.testng.Assert;
import org.testng.annotations.Test;

public class YC_032 extends BaseClass {

    /**
     * Verify Yarn Jobs Table on Yarn chrageback page and table should contains all the fields By default :
     */

    @Test(dataProvider = "clusterid-data-provider")
    public void YC_032_verifyYarnJobsTableColumnsOnYarnChargeBackPage(String clusterId) {
        test = extent.startTest("YC_032_verifyYarnJobsTableColumnsOnYarnChargeBackPage: " + clusterId, "Verify yarn jobs table columns on Yarn chrageback page.");
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

        // Select Yarn Charge back
        chargeBackYarn.clickOnGroupBySearchBox();
        String optionName = "User";
        chargeBackYarn.selectOptionsInGroupBy(optionName);
        test.log(LogStatus.PASS, "Selected " + optionName + " as option in Group By filter, yarn chargeback page");

        //Checking Yarn Jobs Table Records populated
        chargeBackYarn.getYarnJobsTableRecord();
        test.log(LogStatus.PASS, "Verified Yarn Jobs Table is available on Yarn chargeback page");

        //Validate Header Column names in Yarn Jobs Table
        Assert.assertTrue(chargeBackYarn.validateHeaderColumnNameInYarnJobsTable(),"Validation failed for header column names from Yarn Jobs Table");
        test.log(LogStatus.PASS, "Verified Column names in Yarn Jobs Table successfully on Yarn chargeback page");
    }
}
