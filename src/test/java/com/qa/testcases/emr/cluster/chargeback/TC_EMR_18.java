package com.qa.testcases.emr.cluster.chargeback;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.scripts.DatePicker;
import com.qa.scripts.emr.clusters.ClusterInsights;
import com.qa.scripts.emr.clusters.EMRChargeback;
import com.qa.utils.WaitExecuter;
import com.relevantcodes.extentreports.LogStatus;

@Marker.emrClusterChargeback
public class TC_EMR_18 extends BaseClass {

	Logger logger = LoggerFactory.getLogger(TC_EMR_18.class);

	@Test
	public void TC_EMR_18_verifySearchFunctionalityWithValidParameters() {
		test = extent.startTest("TC_EMR_18_verifySearchFunctionalityWithValidParameters", "Verify Search functionality for Chargeback Report with valid parameter");
		test.assignCategory("Cluster - EMR Chargeback");
		EMRChargeback emrChargeback = new EMRChargeback(driver);
		WaitExecuter waitExecuter = new WaitExecuter(driver);
		DatePicker datePicker = new DatePicker(driver);
		String type = "EMR_Release";
		emrChargeback.selectClusterChargeback();
		waitExecuter.sleep(2000);
		datePicker.clickOnDatePicker();
		datePicker.selectLast90Days();
		String search = emrChargeback.fetchResultSetValues(type);
		emrChargeback.searchChargebackReport(search);
		logger.info("Searched: "+search);
		List<String> resultRows = emrChargeback.getResultsGroupedByTableRecords();
		String result = emrChargeback.fetchResultSetValues(type);
		Assert.assertTrue(result.equals(search),"Search was not successful");
		Assert.assertTrue(resultRows.size()==1);
		test.log(LogStatus.PASS,"Search resulted in single result row.");
	}

}
