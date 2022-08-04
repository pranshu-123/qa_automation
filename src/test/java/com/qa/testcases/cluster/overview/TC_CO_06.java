package com.qa.testcases.cluster.overview;

import com.google.gson.JsonObject;
import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.pagefactory.CommonPageObject;
import com.qa.pagefactory.clusters.ChargebackImpalaPageObject;
import com.qa.parameters.Parameter;
import com.qa.scripts.HomePage;
import com.qa.scripts.clusters.impala.ChargeBackImpala;
import com.qa.utils.Log;
import com.qa.utils.NetworkManager;
import com.qa.utils.WaitExecuter;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;
/**
 * @author Sarbashree Ray
 */
@Marker.ClusterOverview
@Marker.GCPClusterOverview
@Marker.All
public class TC_CO_06 extends BaseClass {
    private CommonPageObject commonPageObject;

    @Test(description="P0-Verify the list of all clusters in the UI.")
    public void TC_CO_06_VerifyclusterfilterinUI() {
        test = extent.startTest("TC_CO_06_VerifyclusterfilterinUI" , "Verify cluster filter in UI ");
        test.assignCategory(" Cluster Overview");
        WaitExecuter waitExecuter = new WaitExecuter(driver);
        Log.info("Verify cluster filter in UI");
        HomePage homePage = new HomePage(driver);

        List<WebElement> clusterList = homePage.getClusterListFromDropdown();
        Log.info("Verify the clusterList"+clusterList);
        waitExecuter.sleep(3000);
        homePage.clickOnClusterDropDown();
        waitExecuter.sleep(3000);

        for (int i = 0; i < clusterList.size(); i++) {
            String clustervalues = clusterList.get(i).getText();
            Log.info("The list of cluster filter in UI"+clustervalues);
            test.log(LogStatus.PASS, "The list of cluster filter in UI." +clustervalues);
        }

    }

}
