package com.qa.testcases.cluster.impala.resources;

import java.util.List;
import java.util.logging.Logger;

import com.qa.annotations.Marker;
import com.qa.scripts.clusters.impala.Impala;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.qa.base.BaseClass;
import com.qa.pagefactory.DatePickerPageObject;
import com.qa.pagefactory.TopPanelPageObject;
import com.qa.pagefactory.clusters.ImpalaPageObject;
import com.qa.scripts.DatePicker;
import com.qa.scripts.HomePage;
import com.qa.utils.GraphUtils;
import com.qa.utils.JavaScriptExecuter;
import com.qa.utils.WaitExecuter;
import com.relevantcodes.extentreports.LogStatus;

/* 
 * @author - Ojasvi Pandey 
*/
@Marker.ImpalaResources
@Marker.All
public class IM_RES_04 extends BaseClass {
	private static final Logger LOGGER = Logger.getLogger(IM_RES_04.class.getName());

	@Test(dataProvider = "clusterid-data-provider",description ="P0-Verify the tool tip of the graph should display the Total Memory and Allocated Memory of all the Nodes")
	public void IM_RES_04_verifyMemoryQueryGraphForUserGroup(String clusterId) {
		test = extent.startTest("IM_RES_04.verifyMemoryQueryGraphForUserGroup : "+clusterId, "Verify Query graph tooltip on hovering over Memory graph.)");
		test.assignCategory(" Cluster/Impala Resources");
		test.log(LogStatus.INFO, "Login to the application");
		
		//Initialize all classes objects
		test.log(LogStatus.INFO, "Initialize all class objects");
		LOGGER.info("Initialize all class objects");
		WaitExecuter waitExecuter = new WaitExecuter(driver);
		TopPanelPageObject topPanelPageObject = new TopPanelPageObject(driver);
		DatePickerPageObject datePickerPageObject = new DatePickerPageObject(driver);
		HomePage homePage = new HomePage(driver);
		DatePicker datePicker = new DatePicker(driver);
		ImpalaPageObject impalaPageObject = new ImpalaPageObject(driver);
		Impala impala = new Impala(driver);
		
		//Select impala tab
		test.log(LogStatus.INFO, "Go to resource page");
		LOGGER.info("Select impala from dropdown");
		impala.selectImpalaResource();
		
		// Select the cluster
		test.log(LogStatus.INFO, "Selecting the cluster");
		LOGGER.info("Selecting the cluster");		
		homePage.selectMultiClusterId(clusterId);
		waitExecuter.sleep(1000);
		
		//Select last 30 days
		test.log(LogStatus.INFO, "Select last 30 days in date picker");
		LOGGER.info("Selecting last 30 days");	
		datePicker.clickOnDatePicker();
		waitExecuter.sleep(3000);
		JavaScriptExecuter.clickOnElement(driver, datePickerPageObject.last30Days);
		waitExecuter.sleep(3000);
		
		//Navigate in graph and collect tool tips
		test.log(LogStatus.INFO, "Navigate different section in memory graph");
		LOGGER.info("Navigate different section in memory grap");
		GraphUtils graphUtils = new GraphUtils();
		graphUtils.navigateDifferentPointOnGraph(driver, impalaPageObject.memoryHighChartContainer);
		waitExecuter.sleep(1000);
		List<String> memoryTooltipValues = graphUtils.getMemoryTooltipValues();
		waitExecuter.sleep(1000);
		List<String> queriesTooltipValues = graphUtils.getQueriesTooltipValues();
		waitExecuter.sleep(1000);

		//Assertion checks
		test.log(LogStatus.INFO, "Starting assertions");
		LOGGER.info("Starting assertions");
		for (int i = 0; i < memoryTooltipValues.size(); i++) {
			Assert.assertNotNull(memoryTooltipValues.get(i), "Tooltip value displayed as null for memory graph");
			Assert.assertNotEquals(memoryTooltipValues.get(i), "",
					"Tooltip value displayed blank for memory graph");
			Assert.assertNotNull(queriesTooltipValues.get(i), "Tooltip value displayed as null for query graph");
			Assert.assertNotEquals(queriesTooltipValues.get(i), "",
					"Tooltip value not displayed null value for query graph");
		}
		test.log(LogStatus.PASS, "Validate When the user hovers the mouse over the Query graph"
				+ " it should simultaneously display the tool tip for Memory graph at the same data point");
	}
}

