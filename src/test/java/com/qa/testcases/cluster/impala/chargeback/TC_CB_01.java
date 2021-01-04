package com.qa.testcases.cluster.impala.chargeback;

import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.constants.PageConstants;
import com.qa.scripts.HomePage;
import com.qa.scripts.clusters.impala.ChargeBackImpala;
import com.relevantcodes.extentreports.LogStatus;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * @author Ankur Jaiswal
 */

@Marker.All
@Marker.ImpalaChargeback
public class TC_CB_01 extends BaseClass {
    /**
     * Verify User is able to access Impala chargeback page
     */
    @Test(dataProvider = "clusterid-data-provider")
    public void TC_CB_01_VerifyImpalaChargeBackPage(String clusterId) {
        test = extent.startTest("TC_CB_01_VerifyImpalaChargeBackPage: " + clusterId, "Verify User is able to access Impala chrageback page");
        test.assignCategory(" Cluster - Impala Chargeback");

        ChargeBackImpala chargeBackImpala = new ChargeBackImpala(driver);
        chargeBackImpala.selectImpalaChargeback();

        Assert.assertTrue(driver.getCurrentUrl().contains("clusters/chargeback"), "User is not " +
            "directed to the cluster chargeback page.");
        test.log(LogStatus.PASS, "User is redirected to to cluster chargeback page");

        Assert.assertEquals(chargeBackImpala.getHeading(), PageConstants.Clusters.CHARGEBACK_IMPALA_HEADING,
            "Chargeback Impala is not displayed as page heading.");
        test.log(LogStatus.PASS, "Chargeback Impala is displayed as page heading.");
    }
}
