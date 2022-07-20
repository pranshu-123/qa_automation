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
public class TC_EMR_22 extends BaseClass {

	Logger logger = LoggerFactory.getLogger(TC_EMR_22.class);

	@Test
	public void TC_EMR_22_DownloadGraphAsPDF() {
		test = extent.startTest("TC_EMR_22_DownloadGraphAsPDF", "Download Graph as PDF");
		test.assignCategory("Cluster - EMR Chargeback");
		EMRChargeback emrChargeback = new EMRChargeback(driver);
		WaitExecuter waitExecuter = new WaitExecuter(driver);
		DatePicker datePicker = new DatePicker(driver);
		String type = "PDF";
		emrChargeback.selectClusterChargeback();
		waitExecuter.sleep(2000);
		datePicker.clickOnDatePicker();
		datePicker.selectLast90Days();
		emrChargeback.downloadTotalCostPieCharts(type);
		waitExecuter.sleep(2000);
		Assert.assertTrue(FileUtils.checkForFileNameInDownloadsFolder("chart.pdf"), "File is not downloaded " +
				"or size of file is zero bytes.");
		test.log(LogStatus.PASS, "Successfully downloaded Nodes graph as PDF file.");
		logger.info("Successfully downloaded graph as PDF file.");
	}

}
