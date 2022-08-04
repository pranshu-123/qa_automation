package com.qa.testcases.jobs.applications.inefficientapps;

import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.scripts.jobs.applications.InefficientApps;
import com.qa.utils.WaitExecuter;
import com.relevantcodes.extentreports.LogStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

@Marker.InefficientApps
@Marker.GCPInefficientApps
@Marker.All
public class TC_JIA06 extends BaseClass {

    Logger LOGGER = LoggerFactory.getLogger(TC_JIA06.class);

    /**
     * Verify Application with Inefficient events are listed for different clusters
     */
    @Test(dataProvider = "clusterid-data-provider")
    public void TC_JIA06_verifyWithDifferentClusterFilter(String clusterId) {
        test = extent.startTest("TC_JIA06_verifyWithDifferentClusterFilter" + clusterId, "Verify Application " +
                "with Inefficient events are listed for different clusters");
        test.assignCategory(" Jobs / InEfficient Apps");

        WaitExecuter waitExecuter = new WaitExecuter(driver);
        InefficientApps inefficientApps = new InefficientApps(driver);
        test.log(LogStatus.INFO, "Passed Parameter Is : " + clusterId);

        // Get setup done for InefficientApps page
        inefficientApps.setupInefficientApps();
        test.log(LogStatus.INFO, "setup for InefficientApps done.");

        //Get all the cluster Ids
        List<String> listClusterIds = inefficientApps.getAllClusterIds();
        LOGGER.info("List all cluster Ids are: " + listClusterIds);

        // Select cluster
        test.log(LogStatus.INFO, "Select clusterid : " + clusterId);
        LOGGER.info("Select clusterId : " + clusterId);
        inefficientApps.selectCluster(clusterId);
        waitExecuter.sleep(1000);

        Assert.assertTrue(inefficientApps.verifyInefficientTbl(), "Inefficient Apps table is missing.");
        test.log(LogStatus.PASS, "Verified Application with Inefficient events are " +
                "listed for different clusters");
    }
}
