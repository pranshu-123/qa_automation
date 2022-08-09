package com.qa.testcases.emr.cluster.jobtrends;

import java.util.logging.Logger;

import org.testng.Assert;
import org.testng.annotations.Test;
import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.scripts.DatePicker;
import com.qa.scripts.emr.clusters.ClusterJobTrends;
import com.qa.utils.FileUtils;
import com.qa.utils.Log;
import com.qa.utils.WaitExecuter;
import com.relevantcodes.extentreports.LogStatus;

@Marker.emrClusterjobtrends
public class TC_CJT_10  extends BaseClass{

	private static final Logger LOGGER = Logger.getLogger(TC_CJT_10.class.getName());

	@Test
	public void TC_CJT_10_verifyDownloadGraphAsPng() {
		test = extent.startTest("TC_CJT_10_verifyDownloadGraphAsPng", "Download Graph as PNG");
		test.assignCategory("EMR/ClusterJobTrends");
		Log.startTestCase("TC_CJT_10_verifyDownloadGraphAsPng");
		ClusterJobTrends clusterJobTrends = new ClusterJobTrends(driver);
		WaitExecuter waitExecuter = new WaitExecuter(driver);
		DatePicker datePicker = new DatePicker(driver);
		clusterJobTrends.navigateToClusterInsights();
		waitExecuter.sleep(2000);
		datePicker.clickOnDatePicker();
		datePicker.selectLast90Days();
		String groupBy = "Application Type";
		clusterJobTrends.navigateToClusterInsights();
		waitExecuter.sleep(2000);
		datePicker.clickOnDatePicker();
		datePicker.selectLast90Days();
		clusterJobTrends.filterByGroup(groupBy);
		LOGGER.info("Job Trends filtered as per State group");
		clusterJobTrends.selectDownloadOption("PNG");
		waitExecuter.sleep(2000);
		Assert.assertTrue(FileUtils.checkForFileNameInDownloadsFolder("Jobs.png"), "File is not downloaded " +
				"or size of file is zero bytes.");
		test.log(LogStatus.PASS, "Successfully downloaded Nodes graph as PNG file.");
		LOGGER.info("Successfully downloaded graph as PNG file.");
	}
}