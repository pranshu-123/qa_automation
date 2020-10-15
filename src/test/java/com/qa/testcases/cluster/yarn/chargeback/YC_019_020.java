package com.qa.testcases.cluster.yarn.chargeback;

import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.constants.PageConstants;
import com.qa.scripts.HomePage;
import com.qa.scripts.clusters.yarn.ChargeBackYarn;
import com.relevantcodes.extentreports.LogStatus;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * @author Birender Kumar
 */

@Marker.YarnChargeback
@Marker.All
public class YC_019_020 extends BaseClass {

    /**
     * Verify User is able to access Yarn chargeback page and Verify the cluster functionality
     */
    @Test(dataProvider = "clusterid-data-provider")
    public void YC_019_verifyYarnChargeBackPageAndClustersId(String clusterId) {
        test = extent.startTest("YC_019_verifyYarnChargeBackPageAndClustersId: "+clusterId,"Verify User is able to access Yarn chrageback page and Verify the cluster functionality.");
        test.assignCategory("4620 Cluster - Yarn Chargeback");

        HomePage homePage = new HomePage(driver);
        homePage.selectMultiClusterId(clusterId);

        ChargeBackYarn chargeBackYarn = new ChargeBackYarn(driver);
        chargeBackYarn.selectYarnChargeback();

        Assert.assertTrue(driver.getCurrentUrl().contains("clusters/chargeback"), "User is not " +
                "directed to the cluster chargeback page.");
        test.log(LogStatus.PASS, "User is redirected to to cluster chargeback page");

        Assert.assertEquals(chargeBackYarn.getHeading(), PageConstants.Clusters.CHARGEBACK_YARN_HEADING,
                "Chargeback Yarn is not displayed as page heading.");
        test.log(LogStatus.PASS, "Chargeback Yarn is displayed as page heading.");

        chargeBackYarn.checkForListOfClusterAvailable();
        test.log(LogStatus.PASS, "Chargeback Yarn page, contains list of cluster in dropdown.");

    }
}
