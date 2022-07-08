package com.qa.testcases.emr.cluster.insights;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.scripts.emr.clusters.ClusterInsights;
import com.qa.utils.Log;
import com.qa.utils.WaitExecuter;
import com.relevantcodes.extentreports.LogStatus;

@Marker.emrClusterinsights
public class TC_CI_01  extends BaseClass {
	
    Logger logger = LoggerFactory.getLogger(TC_CI_01.class);

    @Test
    public void TC_CI_01_validateClusterInsightsLandingPage() {
        test = extent.startTest("TC_CI_01_validateClusterInsightsLandingPage",
                "Validate Cluster Insights landing page");
        test.assignCategory("Cluster-Insights");
    	Log.startTestCase("TC_CI_01_validateClusterInsightsLandingPage");
        WaitExecuter waitExecuter = new WaitExecuter(driver);
        ClusterInsights clusterInsights = new ClusterInsights(driver);
        clusterInsights.navigateToClusterInsights();
        waitExecuter.sleep(2000);
        clusterInsights.validateLandingPage();
        test.log(LogStatus.PASS, "Cluster Insight page validated");
        logger.info("Cluster Insight page validated");
    }

}
