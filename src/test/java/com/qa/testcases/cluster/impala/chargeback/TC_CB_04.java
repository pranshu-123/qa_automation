package com.qa.testcases.cluster.impala.chargeback;

import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.io.UnravelConfigYamlReader;
import com.qa.scripts.HomePage;
import com.qa.scripts.clusters.impala.ChargeBackImpala;
import com.qa.utils.LoggingUtils;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Ankur Jaiswal
 */
@Marker.All
@Marker.ImpalaChargeback
public class TC_CB_04 extends BaseClass {

    private final LoggingUtils LOGGER = new LoggingUtils(TC_CB_04.class);
    /**
     * Verify the unsupported clusters are displayed
     */
    @Test(description ="P0-Verify that the Chargeback report is blank for an unsupported platform like HDP.")
    public void VerifyUnsupportedCluster() {
        test = extent.startTest("TC_CB_04.VerifyUnsupportedCluster", "Verify Unsupported clusters on " +
            "Impala chrageback page");
        test.assignCategory(" Cluster - Impala Chargeback");
        ChargeBackImpala chargeBackImpala = new ChargeBackImpala(driver);
        chargeBackImpala.selectImpalaChargeback("Yarn");
        LOGGER.info("Navigate to impala chargeback page",test);
        HomePage homePage = new HomePage(driver);
        List<WebElement> clusters = homePage.getClusterListFromDropdown();
        LOGGER.info("Get list of clusters",test);
        UnravelConfigYamlReader unravelConfigYamlReader = new UnravelConfigYamlReader();
        Object[] expectedClusters = unravelConfigYamlReader.getClusterList(true).next();
        List<WebElement> nonCDHClusters =
            clusters.stream().filter(cluster -> !(Arrays.asList(expectedClusters).contains(cluster.getText()))).collect(Collectors.toList());
        Assert.assertTrue(nonCDHClusters.size()==0, "Non CDH clusters displayed for impala.");
        LOGGER.pass("No HDP clusters displayed for impala.", test);
    }
}
