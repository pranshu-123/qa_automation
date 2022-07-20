package com.qa.testcases.emr.cluster.chargeback;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.scripts.DatePicker;
import com.qa.scripts.emr.clusters.EMRChargeback;
import com.qa.utils.FileUtils;
import com.qa.utils.WaitExecuter;
import com.relevantcodes.extentreports.LogStatus;

@Marker.emrClusterChargeback
public class TC_EMR_25 extends BaseClass {

	Logger logger = LoggerFactory.getLogger(TC_EMR_25.class);

	@Test
	public void TC_EMR_25_DownloadGraphAsJPEG() {
		test = extent.startTest("TC_EMR_25_DownloadGraphAsJPEG", "Download Graph as JPEG");
		test.assignCategory("Cluster - EMR Chargeback");
		EMRChargeback emrChargeback = new EMRChargeback(driver);
		WaitExecuter waitExecuter = new WaitExecuter(driver);
		DatePicker datePicker = new DatePicker(driver);
		String type = "JPEG";
		emrChargeback.selectClusterChargeback();
		waitExecuter.sleep(2000);
		datePicker.clickOnDatePicker();
		datePicker.selectLast90Days();
		emrChargeback.downloadClusterPieCharts(type);
		waitExecuter.sleep(2000);
		Assert.assertTrue(FileUtils.checkForFileNameInDownloadsFolder("chart.jpeg"), "File is not downloaded " +
				"or size of file is zero bytes.");
		test.log(LogStatus.PASS, "Successfully downloaded Nodes graph as JPEG file.");
		logger.info("Successfully downloaded graph as JPEG file.");
	}

}
