package com.qa.testcases.cluster.yarn.chargeback;

import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.scripts.DatePicker;
import com.qa.scripts.HomePage;
import com.qa.scripts.clusters.yarn.ChargeBackYarn;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

@Marker.YarnChargeback
@Marker.All
public class YC_027 extends BaseClass {

    /**
     * Verify Group By Table on Yarn chrageback page
     */
    @Test(dataProvider = "clusterid-data-provider")
    public void YC_027_verifyGroupByTableColumnCountOnYarnChargeBackPage(String clusterId) {
        test = extent.startTest("YC_027_verifyGroupByTableColumnCountOnYarnChargeBackPage: "+clusterId, "Verify Click on groupby edit field, Yarn chrageback page.");
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
        System.out.println("Size of Headers: "+ chargeBackYarn.getResultsGroupedByTableHeaderNames().size());
        Assert.assertTrue(chargeBackYarn.getResultsGroupedByTableHeaderNames().size() == 5 || chargeBackYarn.getResultsGroupedByTableHeaderNames().size() == 4);
        test.log(LogStatus.PASS, "Verified total columns found in Groupby table are: "+chargeBackYarn.getResultsGroupedByTableHeaderNames().size());
        test.log(LogStatus.PASS, "Verified Group By Table Header consist of minimun 4 cloumns or max 5 columns on Yarn chargeback page");

    }

}
