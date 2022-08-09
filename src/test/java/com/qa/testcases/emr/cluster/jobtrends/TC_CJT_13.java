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
public class TC_CJT_13  extends BaseClass{

	private static final Logger LOGGER = Logger.getLogger(TC_CJT_13.class.getName());

	@Test
	public void TC_CJT_13_verifyDownloadGraphAsXls() {
		test = extent.startTest("TC_CJT_13_verifyDownloadGraphAsXls", "Download Graph as XLS");
		test.assignCategory("EMR/ClusterJobTrends");
		Log.startTestCase("TC_CJT_13_verifyDownloadGraphAsXls");
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
		clusterJobTrends.selectDownloadOption("XLS");
		waitExecuter.sleep(2000);
		Assert.assertTrue(FileUtils.checkForFileNameInDownloadsFolder("Jobs.xls"), "File is not downloaded " +
				"or size of file is zero bytes.");
		test.log(LogStatus.PASS, "Successfully downloaded Nodes graph as XLS file.");
		LOGGER.info("Successfully downloaded graph as XLS file.");
	}
}