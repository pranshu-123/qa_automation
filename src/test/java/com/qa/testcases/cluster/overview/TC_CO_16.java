package com.qa.testcases.cluster.overview;

import java.util.logging.Logger;

import com.qa.annotations.Marker;
import org.openqa.selenium.NoSuchElementException;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.Test;
import com.qa.base.BaseClass;
import com.qa.scripts.DatePicker;
import com.qa.scripts.Graphs;
import com.qa.scripts.HomePage;
import com.qa.utils.WaitExecuter;
import com.relevantcodes.extentreports.LogStatus;

@Marker.ClusterOverview
@Marker.All
public class TC_CO_16 extends BaseClass {

	private WaitExecuter waitExecuter;
	private DatePicker datePicker;
	private Graphs graph;
	private static final Logger LOGGER = Logger.getLogger(TC_CO_16.class.getName());

	@Test(dataProvider = "clusterid-data-provider")
	public void TC_CO_16_verifyGraphKPICustomDate(String clusterId) {
		test = extent.startTest("TC_CO_16_verifyGraphKPICustomDate : "+clusterId, "Verify Custom Date KPIs under By Type Graph");
		test.assignCategory(" Cluster Overview");
		waitExecuter = new WaitExecuter(driver);
		datePicker = new DatePicker(driver);
		graph = new Graphs(driver);
		
		test.log(LogStatus.INFO, "This testcase is running for cluster: "+clusterId);
		//Select the cluster
		LOGGER.info("Selecting cluster from the list " +clusterId);
		HomePage homePage = new HomePage(driver);
	    homePage.selectMultiClusterId(clusterId);
		// Click on datepicker button
		LOGGER.info("Click on date picker");
		test.log(LogStatus.INFO, "Click on date picker");
		waitExecuter.sleep(3000);
		datePicker.clickOnDatePicker();
		waitExecuter.sleep(1000);

		// Click on custom range
		LOGGER.info("Click on date picker");
		test.log(LogStatus.INFO, "Click on date picker");
		datePicker.selectCustomRange();
		waitExecuter.sleep(1000);
		/*
		 * Set Start date by substracting days from current date and end date as
		 * currentdate
		 */
		LOGGER.info("Set current and past date");
		test.log(LogStatus.INFO, "Set current and past date");
		datePicker.setCurrentAndPastDate(-30);
		waitExecuter.sleep(1000);
		// Click on apply button of Cluster
		LOGGER.info("Click on apply button");
		test.log(LogStatus.INFO, "Click on apply button");
		datePicker.clickOnCustomDateApplyBtn();
		waitExecuter.sleep(1000);
		try {
			LOGGER.info("Verify By Type graph");
			test.log(LogStatus.INFO, "Verify by type graph");
			Assert.assertTrue(graph.validateByTypeGraphIsGenerated(), "The color code of graph does not match");
		} catch (NoSuchElementException error) {
			// Skip test case if By Type title is not present
			throw new SkipException("Testcase need to be skipped as By Type graph heading is not present");
		}
	}
}
