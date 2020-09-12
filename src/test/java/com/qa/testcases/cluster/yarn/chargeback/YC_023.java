package com.qa.testcases.cluster.yarn.chargeback;

import com.qa.base.BaseClass;
import com.qa.constants.PageConstants;
import com.qa.scripts.HomePage;
import com.qa.scripts.clusters.yarn.ChargeBackYarn;
import com.relevantcodes.extentreports.LogStatus;
import org.testng.Assert;
import org.testng.annotations.Test;

public class YC_023 extends BaseClass {

    /**
     * Verify Click on Group By editbox field on Yarn chrageback page
     */
    @Test(dataProvider = "clusterid-data-provider")
    public void YC_023_verifyYarnChargeBackPageClickOnGroupBy(String clusterId) {
        test = extent.startTest("YC_023_verifyYarnChargeBackPageClickOnGroupBy: "+clusterId, "Verify Click on groupby edit field, Yarn chrageback page.");
        test.assignCategory("4620 Cluster - Yarn Chargeback");

        HomePage homePage = new HomePage(driver);
        homePage.selectMultiClusterId(clusterId);

        ChargeBackYarn chargeBackYarn = new ChargeBackYarn(driver);
        chargeBackYarn.selectYarnChargeback();
        test.log(LogStatus.PASS, "User is redirected to cluster chargeback page");

        chargeBackYarn.clickOnGroupBySearchBox();
        test.log(LogStatus.PASS, "Successfully clicked on group by serach box on yarn chargeback page");

        chargeBackYarn.verifyNumberOfOptionsInGroupBy();
        test.log(LogStatus.PASS, "Successfully verified number of options in group by on yarn chargeback page");

        // Verify the names of options available in group by options
        Assert.assertTrue(chargeBackYarn.validateGroupByOptions(),
                "The displayed option does not smatch the group by actual options");
        test.log(LogStatus.PASS, "Successfully verified name of options available in group by on yarn " +
                "chargeback page");

    }

}
