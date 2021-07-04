package com.qa.testcases.cluster.impala.resources;

import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.pagefactory.TopPanelPageObject;
import com.qa.pagefactory.clusters.ImpalaPageObject;
import com.qa.scripts.DatePicker;
import com.qa.scripts.HomePage;
import com.qa.scripts.clusters.impala.Impala;
import com.qa.utils.GraphUtils;
import com.qa.utils.JavaScriptExecuter;
import com.qa.utils.WaitExecuter;
import com.relevantcodes.extentreports.LogStatus;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;
import java.util.logging.Logger;

/**
 * @author Ojasvi Pandey
 */

@Marker.ImpalaResources
@Marker.All
public class IM_RES_14 extends BaseClass {
	private static final Logger LOGGER = Logger.getLogger(IM_RES_14.class.getName());

	@Test(dataProvider = "clusterid-data-provider",description ="P0-Verify the user hovers the mouse over the Query graph should display the tooltip for Memory graph at the same data point.")
	public void IM_RES_14_verifyTooltipOfMemoryGraphOnHoveringQueryGraph(String clusterId) {
		test = extent.startTest("IM_RES_14.verifyTooltipOfMemoryGraphOnHoveringQueryGraph : " + clusterId,
				"Verify on hovering on Query graph, Memory graph tool tip should be visible too.)");
		test.assignCategory(" Cluster/Impala Resources");

		test.log(LogStatus.INFO, "Login to the application");
		WaitExecuter waitExecuter = new WaitExecuter(driver);
		Impala impala = new Impala(driver);
		//Select impala tab
		test.log(LogStatus.INFO, "Go to resource page");
		LOGGER.info("Select impala from dropdown");
		impala.selectImpalaResource();
		waitExecuter.sleep(2000);
		
		// Select the cluster
		LOGGER.info("Selecting the cluster");
		waitExecuter.sleep(1000);
		HomePage homePage = new HomePage(driver);
		homePage.selectMultiClusterId(clusterId);

		impala.selectImpalaType("Impala");
		waitExecuter.sleep(3000);

		ImpalaPageObject impalaPageObject = new ImpalaPageObject(driver);
		impalaPageObject.groupByDropdownButton.click();
		waitExecuter.waitUntilElementClickable(impalaPageObject.resourceUsagePointer);
		impalaPageObject.groupByQueueList.click();
		waitExecuter.waitUntilElementClickable(impalaPageObject.resourceUsagePointer);
		test.log(LogStatus.INFO, "Select Queue in Group by option.");

		DatePicker datePicker = new DatePicker(driver);
		datePicker.clickOnDatePicker();
		waitExecuter.waitUntilElementClickable(impalaPageObject.resourceUsagePointer);
		datePicker.selectLast30Days();
		waitExecuter.waitUntilElementClickable(impalaPageObject.resourceUsagePointer);
		test.log(LogStatus.INFO, "Select this month in date picker");
		test.log(LogStatus.INFO, "Navigate different section in memory graph");
		GraphUtils graphUtils = new GraphUtils();
		graphUtils.navigateDifferentPointOnGraph(driver, impalaPageObject.queryHighChartContainer);
		List<String> memoryTooltipValues = graphUtils.getMemoryTooltipValues();
		List<String> queriesTooltipValues = graphUtils.getQueriesTooltipValues();

		for (int i = 0; i < memoryTooltipValues.size(); i++) {
			Assert.assertNotNull(memoryTooltipValues.get(i), "Tooltip value displayed null value for memory graph");
			Assert.assertNotEquals(memoryTooltipValues.get(i), "",
					"Tooltip value displayed blank value for memory graph");
			Assert.assertNotNull(queriesTooltipValues.get(i), "Tooltip value displayed null value for query graph");
			Assert.assertNotEquals(queriesTooltipValues.get(i), "",
					"Tooltip value not displayed null value for query graph");
		}
		test.log(LogStatus.PASS, "Validate When the user hovers the mouse over the Query graph"
				+ " it should simultaneously display the tool tip for Memory graph at the same data point");
	}
}
