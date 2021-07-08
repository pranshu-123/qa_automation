package com.qa.testcases.cluster.yarn.chargeback;

import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.constants.PageConstants;
import com.qa.scripts.HomePage;
import com.qa.scripts.clusters.impala.ChargeBackImpala;
import com.qa.scripts.clusters.yarn.ChargeBackYarn;
import com.qa.utils.WaitExecuter;
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
    @Test(dataProvider = "clusterid-data-provider",description="P0-Verify that the user can access the Yarn chargeback page and check the functionality of the cluster.")
    public void YC_019_verifyYarnChargeBackPageAndClustersId(String clusterId) {
        test = extent.startTest("YC_019_verifyYarnChargeBackPageAndClustersId: "+clusterId,"Verify that the user can access the Yarn chargeback page and check the functionality of the cluster.");
        test.assignCategory(" Cluster - Yarn Chargeback");

        HomePage homePage = new HomePage(driver);
        WaitExecuter waitExecuter = new WaitExecuter(driver);


        ChargeBackYarn chargeBackYarn = new ChargeBackYarn(driver);
        waitExecuter.waitUntilPageFullyLoaded();
        chargeBackYarn.selectYarnChargeback();
        waitExecuter.sleep(2000);

        homePage.selectMultiClusterIdClusterPage(clusterId);
        chargeBackYarn.selectChargebackType("Yarn");
        waitExecuter.sleep(2000);

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
