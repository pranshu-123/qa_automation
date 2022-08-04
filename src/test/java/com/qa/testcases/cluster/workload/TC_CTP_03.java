package com.qa.testcases.cluster.workload;

import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.pagefactory.TopPanelPageObject;
import com.qa.pagefactory.clusters.WorkloadPageObject;

import com.qa.scripts.HomePage;
import com.qa.scripts.clusters.Workload;
import com.qa.utils.MouseActions;
import com.qa.utils.WaitExecuter;
import com.relevantcodes.extentreports.LogStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

/**
 * @author Sarbashree Ray
 */
@Marker.All
@Marker.ClusterWorkload
@Marker.GCPClusterWorkload
public class TC_CTP_03 extends BaseClass {

    Logger logger = LoggerFactory.getLogger(TC_CTP_03.class);

    @Test(dataProvider = "clusterid-data-provider")
    public void validateAllTheClustersListed(String clusterId) {
        test = extent.startTest("TC_CTP_03.validateAllTheClustersListed", "Verify Cluster workload report should be generated as per selected view by filter");
        test.assignCategory("Cluster - Workload");
        WaitExecuter waitExecuter = new WaitExecuter(driver);
        WorkloadPageObject workloadPageObject = new WorkloadPageObject(driver);
        Workload workload = new Workload(driver);
        workload.selectWorkloadTab();
        waitExecuter.sleep(2000);

        test.log(LogStatus.PASS, "verify Clusterid : " + clusterId);

        HomePage homePage = new HomePage(driver);
        homePage.selectMultiClusterIdClusterPage(clusterId);
        waitExecuter.sleep(2000);
        waitExecuter.waitUntilPageFullyLoaded();

        workload.selectWorkloadType("Yarn");
        waitExecuter.sleep(2000);

        List<String> listOfClusters = workload.getListOfClusters(workloadPageObject.clusterList);
        test.log(LogStatus.PASS, "List of all cluster are: " + listOfClusters);

        test.log(LogStatus.PASS, "Verify all Cluster is displayed in dropdown.");
    }
}