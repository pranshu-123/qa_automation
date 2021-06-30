package com.qa.testcases.cluster.yarn.resources;

import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.pagefactory.CommonPageObject;
import com.qa.pagefactory.clusters.YarnPageObject;
import com.qa.scripts.DatePicker;
import com.qa.scripts.HomePage;
import com.qa.scripts.clusters.yarn.Yarn;
import com.qa.utils.Log;
import com.qa.utils.MouseActions;
import com.qa.utils.WaitExecuter;
import com.relevantcodes.extentreports.LogStatus;
import org.testng.annotations.Test;

import java.util.List;

@Marker.YarnResources
@Marker.All
public class YR_017 extends BaseClass {

    /**
     * Verify the cluster Tab should show the list of cluster
     */
    @Test(dataProvider = "clusterid-data-provider",description="P0-Verify that the cluster tab should be shown the list of cluster.")
    public void YR_017_verifyClusterTab(String clusterId) {
        test = extent.startTest("YR_017_verifyclusterTabAndEntries: " + clusterId,
                "Verify the cluster Tab should show the list of cluster.");
        test.assignCategory(" Cluster - Yarn Resources");
        Log.startTestCase("YR_017_verifyClusterTab");

        Yarn yarn = new Yarn(driver);
        yarn.verifyYarnResourceHeaderisDisplayed();
        Log.info("Yarn Resource Header is displayed.");
        test.log(LogStatus.INFO, "Yarn Resource Header is displayed.");

        YarnPageObject yarnPageObject = new YarnPageObject(driver);
        WaitExecuter waitExecuter = new WaitExecuter(driver);
        waitExecuter.sleep(2000);
        waitExecuter.waitUntilPageFullyLoaded();
        yarn.selectImpalaType("Yarn");
        waitExecuter.waitUntilPageFullyLoaded();
        CommonPageObject commonPageObject = new CommonPageObject(driver);
        MouseActions.clickOnElement(driver, commonPageObject.clusterDropdown);
        waitExecuter.sleep(2000);
        System.out.println("Size of cluster in dropdown: "+commonPageObject.clustersList.size());

        HomePage homePage = new HomePage(driver);
        List<String> listOfClusters = homePage.getListOfClusters(commonPageObject.clustersList);
        System.out.println("List of all cluster are: "+ listOfClusters);
        test.log(LogStatus.PASS,"List of all cluster are: "+ listOfClusters);


    }

}
