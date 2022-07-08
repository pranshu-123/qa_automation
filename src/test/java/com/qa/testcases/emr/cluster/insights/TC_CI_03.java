package com.qa.testcases.emr.cluster.insights;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.scripts.emr.clusters.ClusterInsights;
import com.qa.utils.FileUtils;
import com.qa.utils.Log;
import com.qa.utils.WaitExecuter;
import com.relevantcodes.extentreports.LogStatus;

@Marker.emrClusterinsights
public class TC_CI_03  extends BaseClass {

	Logger logger = LoggerFactory.getLogger(TC_CI_03.class);

	@Test
	public void TC_CI_03_validateDownloadFunctionality() {
		test = extent.startTest("TC_CI_03_validateDownloadFunctionality",
				"Download Result set");
		test.assignCategory("Cluster-Insights");
		Log.startTestCase("TC_CI_03_validateDownloadFunctionality");
		WaitExecuter waitExecuter = new WaitExecuter(driver);
		ClusterInsights clusterInsights = new ClusterInsights(driver);
		clusterInsights.navigateToClusterInsights();
		waitExecuter.sleep(2000);
		clusterInsights.validateResultSet();
		clusterInsights.downloadResultSet();
		waitExecuter.sleep(2000);
		Assert.assertTrue(FileUtils.checkForFileNameInDownloadsFolder("Clusters.csv"), "File is not downloaded " +
				"or size of file is zero bytes.");
		test.log(LogStatus.PASS, "File download working correctly");
		logger.info("File download working correctly");
	}

}
