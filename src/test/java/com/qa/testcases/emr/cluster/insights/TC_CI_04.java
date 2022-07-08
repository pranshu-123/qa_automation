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
public class TC_CI_04  extends BaseClass {

	Logger logger = LoggerFactory.getLogger(TC_CI_04.class);

	@Test
	public void TC_CI_04_validateSearchViaClusterName() {
		test = extent.startTest("TC_CI_04_validateSearchViaClusterName",
				"Search as per Cluster Name");
		test.assignCategory("Cluster-Insights");
		Log.startTestCase("TC_CI_04_validateSearchViaClusterName");
		WaitExecuter waitExecuter = new WaitExecuter(driver);
		ClusterInsights clusterInsights = new ClusterInsights(driver);
		clusterInsights.navigateToClusterInsights();
		waitExecuter.sleep(2000);
		clusterInsights.validateResultSet();
		clusterInsights.searchClusterJob("name");
		logger.info("Cluster searched");
		waitExecuter.sleep(2000);
		clusterInsights.validateSearchResult();
		test.log(LogStatus.PASS, "Cluster search via name working");
		logger.info("Cluster search via name working");
	}

}
