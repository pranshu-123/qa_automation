package com.qa.testcases.emr.cluster.insights;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.scripts.emr.clusters.ClusterInsights;
import com.qa.utils.Log;
import com.qa.utils.WaitExecuter;
import com.relevantcodes.extentreports.LogStatus;

@Marker.emrClusterinsights
public class TC_CI_02  extends BaseClass {
	
    Logger logger = LoggerFactory.getLogger(TC_CI_02.class);

    @Test
    public void TC_CI_02_validateClusterInsighsResultTable() {
        test = extent.startTest("TC_CI_02_validateClusterInsighsResultTable",
                "Validate Cluster Insights tabular result set");
        test.assignCategory("Cluster-Insights");
    	Log.startTestCase("TC_CI_02_validateClusterInsighsResultTable");
        WaitExecuter waitExecuter = new WaitExecuter(driver);
        ClusterInsights clusterInsights = new ClusterInsights(driver);
        clusterInsights.navigateToClusterInsights();
        waitExecuter.sleep(2000);
        clusterInsights.validateResultSet();
        String[] expectedHeaderOptions = {"Cluster Id", "Cluster Name", "Created Date","Status",
        		"Cost Type","EC2 Cost","EMR Cost","EBS Cost","Total Cost","Actions"};
        for (String expectedDateOption : expectedHeaderOptions) {
            Assert.assertTrue(clusterInsights.retrieveResultSetHeaders().contains(expectedDateOption),
                    "Cluster Insight does not contain: " + expectedDateOption);
            test.log(LogStatus.PASS, "Cluster Insight contains option: " + expectedDateOption);
        }
        test.log(LogStatus.PASS, "Cluster Insight result table validated");
        logger.info("Cluster Insight result table validated");
    }
}
