package com.qa.testcases.cluster.impala.resources;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import com.qa.annotations.Marker;
import com.qa.scripts.clusters.impala.Impala;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.qa.base.BaseClass;
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
public class IM_RES_06 extends BaseClass {
	private static final Logger LOGGER = Logger.getLogger(IM_RES_06.class.getName());

	@Test(dataProvider = "clusterid-data-provider",description ="P0-Verify that the Memory Chart displays the memory limit and allocation for each node")
	public void verifyQueryGraphForUserGroup(String clusterId) {
		test = extent.startTest("IM_RES_06.verifyMemoryGraphForUserGroup", "Verify tool tips of Query graph on mouse hover.)");
		test.assignCategory(" Cluster/Impala Resources");
		test.log(LogStatus.INFO, "Login to the application");
		
		//Initialize all classes objects
		test.log(LogStatus.INFO, "Initialize all class objects");
		LOGGER.info("Initialize all class objects");
		WaitExecuter waitExecuter = new WaitExecuter(driver);
		ImpalaPageObject impalaPageObject = new ImpalaPageObject(driver);
		TopPanelPageObject topPanelPageObject = new TopPanelPageObject(driver);
		DatePicker datePicker = new DatePicker(driver);
		HomePage homePage = new HomePage(driver);
		GraphUtils graphUtils = new GraphUtils();
		Impala impala = new Impala(driver);

		//Select impala tab
		test.log(LogStatus.INFO, "Go to resource page");
		LOGGER.info("Select impala from dropdown");
		impala.selectImpalaResource();
		waitExecuter.sleep(2000);

		// Set multi cluster
		test.log(LogStatus.INFO, "Select cluster : " + clusterId);
		LOGGER.info("Select cluster : " + clusterId);
		homePage.selectMultiClusterId(clusterId);
		waitExecuter.waitUntilElementClickable(impalaPageObject.resourceUsagePointer);

		impala.selectImpalaType("Impala");
		waitExecuter.sleep(3000);

		// Select last 30 days from date picker
		test.log(LogStatus.INFO, "Select Last 30 days in date picker");
		LOGGER.info("Select Last 30 days in date picker");
		datePicker.clickOnDatePicker();
		waitExecuter.waitUntilElementClickable(impalaPageObject.resourceUsagePointer);
		datePicker.selectLast30Days();
		waitExecuter.waitUntilElementClickable(impalaPageObject.resourceUsagePointer);

		// Navigating on different points of memory graph and collect tooltips
		test.log(LogStatus.INFO, "Navigate different section in memory graph");
		LOGGER.info("Navigate different section in memory graph");
		waitExecuter.waitUntilElementClickable(impalaPageObject.resourceUsagePointer);
		graphUtils.navigateDifferentPointOnGraph(driver, impalaPageObject.memoryHighChartContainer);
		List<String> memoryTooltipValues = graphUtils.getMemoryTooltipValues();
		waitExecuter.waitUntilElementClickable(impalaPageObject.resourceUsagePointer);
		LOGGER.info("Memory tooltip values : " +memoryTooltipValues);

		// Check if tool-tip contains 'Total' and 'Allocated' as value in it
		test.log(LogStatus.INFO, "Validate if 'Total' and 'Allocated' nodes are present");
		LOGGER.info("Validate if 'Total' and 'Allocated' nodes are present");	
		List<String> nodeValues = new ArrayList<String>();
		for (int i = 0; i < memoryTooltipValues.size(); i++) {
			if (memoryTooltipValues.get(i).contains("-")) {
				test.log(LogStatus.INFO,"Before split " + memoryTooltipValues.get(i));
				String[] splitByHyphen = memoryTooltipValues.get(i).split("-");
				for (int j = 0; j < splitByHyphen.length; j++) {
					if (splitByHyphen[j].contains("Total") || splitByHyphen[j].contains("Allocated")) {
						String[] splitByColon = splitByHyphen[j].split(":");
						nodeValues.add(splitByColon[0]);
						LOGGER.info("split by colon " + splitByColon[0]);
						test.log(LogStatus.INFO,"split by colon " + splitByColon[0]);
					}
				}
			}
		}

		//Assertion checks
		test.log(LogStatus.INFO, "Check with Assertions");
		LOGGER.info("Check with Assertions");
		Assert.assertTrue(nodeValues.contains("Total"), "The tootip in memory graph does not contain 'Total' keyword");
		Assert.assertTrue(nodeValues.contains("Allocated"),
				"The tootip in memory graph does not contain 'Allocated' keyword");

	}
}
