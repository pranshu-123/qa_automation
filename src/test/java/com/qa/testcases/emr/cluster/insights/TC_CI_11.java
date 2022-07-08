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
public class TC_CI_11  extends BaseClass {

	Logger logger = LoggerFactory.getLogger(TC_CI_11.class);

	@Test
	public void TC_CI_11_filterByClusterTag() {
		test = extent.startTest("TC_CI_11_filterByClusterTag",
				"Filter By Cluster Tag");
		test.assignCategory("Cluster-Insights");
		Log.startTestCase("TC_CI_11_filterByClusterTag");
		WaitExecuter waitExecuter = new WaitExecuter(driver);
		ClusterInsights clusterInsights = new ClusterInsights(driver);
		String clusterId = "emr-6.6.0";
		clusterInsights.navigateToClusterInsights();
		waitExecuter.sleep(2000);
		clusterInsights.filterByCluster(clusterId);
		logger.info("Cluster "+ clusterId + " selected");
		clusterInsights.validateResultSet();
		logger.info("Filtering was successful");
	        test.log(LogStatus.PASS, "Filtering cluster jobs as per tag name successful");
		logger.info("Filtering cluster jobs as per tag name successful");
	}
}
