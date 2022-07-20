package com.qa.testcases.emr.cluster.chargeback;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;
import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.scripts.DatePicker;
import com.qa.scripts.emr.clusters.EMRChargeback;
import com.qa.utils.WaitExecuter;
import com.relevantcodes.extentreports.LogStatus;

@Marker.emrClusterChargeback
public class TC_EMR_05 extends BaseClass {

	Logger logger = LoggerFactory.getLogger(TC_EMR_05.class);

	@Test
	public void TC_EMR_05_VerifyEmrChargeBackDatePicker() {
		test = extent.startTest("TC_EMR_05_VerifyEmrChargeBackDatePicker", "Verify that the UI should display data for the all the date ranges");
		test.assignCategory("Cluster - EMR Chargeback");
		EMRChargeback emrChargeback = new EMRChargeback(driver);
		WaitExecuter waitExecuter = new WaitExecuter(driver);
		DatePicker datePicker = new DatePicker(driver);

		emrChargeback.selectClusterChargeback();
		waitExecuter.sleep(2000);
		datePicker.clickOnDatePicker();
		waitExecuter.sleep(1000);

		//select 'Last 1 Hour'
		datePicker.selectLastOneHour();
		waitExecuter.sleep(2000);

		if(emrChargeback.isJobsFromGraphHeaderDisplayed()){
			test.log(LogStatus.PASS, "Jobs count displayed for Last 1 Hour Data range.");
		}else{
			emrChargeback.retreiveNoDataMsg().contains("No Data Available.");
			test.log(LogStatus.PASS, "No Data Available for Last 1 Hour Data range.");
		}

		//select 'Last 2 Hour'
		datePicker.clickOnDatePicker();
		waitExecuter.sleep(1000);

		datePicker.selectLastTwoHour();
		waitExecuter.sleep(2000);

		if(emrChargeback.isJobsFromGraphHeaderDisplayed()){
			test.log(LogStatus.PASS, "Jobs count displayed for Last 2 Hour Data range.");
		}else{
			emrChargeback.retreiveNoDataMsg().contains("No Data Available.");
			test.log(LogStatus.PASS, "No Data Available for Last 2 Hour Data range.");
		}

		//select 'Last 6 Hour'
		datePicker.clickOnDatePicker();
		waitExecuter.sleep(1000);

		datePicker.selectLastSixHour();
		waitExecuter.sleep(2000);

		if(emrChargeback.isJobsFromGraphHeaderDisplayed()){
			test.log(LogStatus.PASS, "Jobs count displayed for Last 6 Hour Data range.");
		}else{
			emrChargeback.retreiveNoDataMsg().contains("No Data Available.");
			test.log(LogStatus.PASS, "No Data Available for Last 6 Hour Data range.");
		}

		//select 'Last 12 Hour'
		datePicker.clickOnDatePicker();
		waitExecuter.sleep(1000);

		datePicker.selectLast12Hour();
		waitExecuter.sleep(2000);

		if(emrChargeback.isJobsFromGraphHeaderDisplayed()){
			test.log(LogStatus.PASS, "Jobs count displayed for Last 12 Hour Data range.");
		}else{
			emrChargeback.retreiveNoDataMsg().contains("No Data Available.");
			test.log(LogStatus.PASS, "No Data Available for Last 12 Hour Data range.");
		}

		//select 'Today'
		datePicker.clickOnDatePicker();
		waitExecuter.sleep(1000);

		datePicker.selectToday();
		waitExecuter.sleep(2000);

		if(emrChargeback.isJobsFromGraphHeaderDisplayed()){
			test.log(LogStatus.PASS, "Jobs count displayed for Today Data range.");
		}else{
			emrChargeback.retreiveNoDataMsg().contains("No Data Available.");
			test.log(LogStatus.PASS, "No Data Available for Today Data range.");
		}

		//select 'Yesterday'
		datePicker.clickOnDatePicker();
		waitExecuter.sleep(1000);

		datePicker.selectYesterday();
		waitExecuter.sleep(2000);

		if(emrChargeback.isJobsFromGraphHeaderDisplayed()){
			test.log(LogStatus.PASS, "Jobs count displayed for Yesterday Data range.");
		}else{
			emrChargeback.retreiveNoDataMsg().contains("No Data Available.");
			test.log(LogStatus.PASS, "No Data Available for Yesterday Data range.");
		}

		//select 'Last 7 Days'
		datePicker.clickOnDatePicker();
		waitExecuter.sleep(1000);

		datePicker.selectLast7Days();
		waitExecuter.sleep(2000);

		if(emrChargeback.isJobsFromGraphHeaderDisplayed()){
			test.log(LogStatus.PASS, "Jobs count displayed for Last 7 Days Data range.");
		}else{
			emrChargeback.retreiveNoDataMsg().contains("No Data Available.");
			test.log(LogStatus.PASS, "No Data Available for Last 7 Days Data range.");
		}

		//select 'Last 30 Days'
		datePicker.clickOnDatePicker();
		waitExecuter.sleep(1000);

		datePicker.selectLast30Days();
		waitExecuter.sleep(2000);

		if(emrChargeback.isJobsFromGraphHeaderDisplayed()){
			test.log(LogStatus.PASS, "Jobs count displayed for Last 30 Days Data range.");
		}else{
			emrChargeback.retreiveNoDataMsg().contains("No Data Available.");
			test.log(LogStatus.PASS, "No Data Available for Last 30 Days Data range.");
		}

		//select 'Last 90 Days'
		datePicker.clickOnDatePicker();
		waitExecuter.sleep(1000);

		datePicker.selectLast90Days();
		waitExecuter.sleep(2000);

		if(emrChargeback.isJobsFromGraphHeaderDisplayed()){
			test.log(LogStatus.PASS, "Jobs count displayed for Last 90 Days Data range.");
		}else{
			emrChargeback.retreiveNoDataMsg().contains("No Data Available.");
			test.log(LogStatus.PASS, "No Data Available for Last 90 Days Data range.");
		}

		//select 'This Month'
		datePicker.clickOnDatePicker();
		waitExecuter.sleep(1000);

		datePicker.selectThisMonth();
		waitExecuter.sleep(2000);

		if(emrChargeback.isJobsFromGraphHeaderDisplayed()){
			test.log(LogStatus.PASS, "Jobs count displayed for This Month Data range.");
		}else{
			emrChargeback.retreiveNoDataMsg().contains("No Data Available.");
			test.log(LogStatus.PASS, "No Data Available for This Month Data range.");
		}

		//select 'Last Month'
		datePicker.clickOnDatePicker();
		waitExecuter.sleep(1000);

		datePicker.selectLastMonth();
		waitExecuter.sleep(2000);

		if(emrChargeback.isJobsFromGraphHeaderDisplayed()){
			test.log(LogStatus.PASS, "Jobs count displayed for Last Month Data range.");
		}else{
			emrChargeback.retreiveNoDataMsg().contains("No Data Available.");
			test.log(LogStatus.PASS, "No Data Available for Last Month Data range.");
		}
	}
}

