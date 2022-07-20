package com.qa.testcases.emr.cluster.chargeback;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.scripts.DatePicker;
import com.qa.scripts.emr.clusters.EMRChargeback;
import com.qa.utils.WaitExecuter;
import com.relevantcodes.extentreports.LogStatus;

@Marker.emrClusterChargeback
public class TC_EMR_19 extends BaseClass {

	Logger logger = LoggerFactory.getLogger(TC_EMR_19.class);

	@Test
	public void TC_EMR_19_verifySearchFunctionalityWithInValidParameters() {
		test = extent.startTest("TC_EMR_19_verifySearchFunctionalityWithInValidParameters", "Verify Search functionality for Chargeback Report with invalid parameter");
		test.assignCategory("Cluster - EMR Chargeback");
		EMRChargeback emrChargeback = new EMRChargeback(driver);
		WaitExecuter waitExecuter = new WaitExecuter(driver);
		DatePicker datePicker = new DatePicker(driver);
		emrChargeback.selectClusterChargeback();
		waitExecuter.sleep(2000);
		datePicker.clickOnDatePicker();
		datePicker.selectLast90Days();
		String search = "random";
		emrChargeback.searchChargebackReport(search);
		logger.info("Searched: "+search);
		String result = emrChargeback.retreiveNoDataMsg();
		Assert.assertTrue(result.equals("No data to display"),"Search was not successful");
		test.log(LogStatus.PASS,"Invalid Search resulted in No data to display output.");
	}

}
