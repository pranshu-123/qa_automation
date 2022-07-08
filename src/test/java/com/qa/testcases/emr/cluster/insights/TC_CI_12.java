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
public class TC_CI_12  extends BaseClass {

	Logger logger = LoggerFactory.getLogger(TC_CI_12.class);

	@Test
	public void TC_CI_12_validateSortAsPerClusterId() {
		test = extent.startTest("TC_CI_12_validateSortAsPerClusterId",
				"Sort the result as per Cluster Id");
		test.assignCategory("Cluster-Insights");
		Log.startTestCase("TC_CI_12_validateSortAsPerClusterId");
		WaitExecuter waitExecuter = new WaitExecuter(driver);
		ClusterInsights clusterInsights = new ClusterInsights(driver);
		clusterInsights.navigateToClusterInsights();
		waitExecuter.sleep(2000);
		clusterInsights.validateResultSet();
		clusterInsights.validateSorting("Cluster Id");
		test.log(LogStatus.PASS, "Sorting test as per cluster Id was successful");
		logger.info("Sorting test as per cluster Id was successful");
	}
}
