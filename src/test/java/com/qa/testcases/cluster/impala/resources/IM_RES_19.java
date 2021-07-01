package com.qa.testcases.cluster.impala.resources;

import java.io.File;
import java.util.List;
import java.util.logging.Logger;

import com.qa.annotations.Marker;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.qa.base.BaseClass;
import com.qa.constants.DirectoryConstants;
import com.qa.constants.GraphColorConstants;
import com.qa.pagefactory.TopPanelPageObject;
import com.qa.pagefactory.clusters.ImpalaPageObject;
import com.qa.scripts.DatePicker;
import com.qa.scripts.HomePage;
import com.qa.scripts.clusters.impala.Impala;
import com.qa.utils.JavaScriptExecuter;
import com.qa.utils.ScreenshotHelper;
import com.qa.utils.WaitExecuter;
import com.relevantcodes.extentreports.LogStatus;

/* 
 * @author - Ojasvi Pandey 
*/

@Marker.ImpalaResources
@Marker.All
public class IM_RES_19 extends BaseClass {
	private static final Logger LOGGER = Logger.getLogger(IM_RES_19.class.getName());

	@Test(dataProvider = "clusterid-data-provider",description ="P0-Verify the Memory and Query graphs should generated for selected and deselected node")
	public void validateUserCheckbox(String clusterId) {
		test = extent.startTest("IM_RES_19.validateUserCheckbox (" + clusterId + ")",
				"Validate the \"Group By\" filter for Queue.");
		test.assignCategory(" Cluster/Impala Resources");
		test.log(LogStatus.INFO, "Login to the application");

		// Initialize all classes objects
		test.log(LogStatus.INFO, "Initialize all class objects");
		LOGGER.info("Initialize all class objects");
		WaitExecuter waitExecuter = new WaitExecuter(driver);
		ImpalaPageObject impalaPageObject = new ImpalaPageObject(driver);
		TopPanelPageObject topPanelPageObject = new TopPanelPageObject(driver);
		HomePage homePage = new HomePage(driver);
		DatePicker datePicker = new DatePicker(driver);
		Impala impala = new Impala(driver);

		//Select impala tab
		test.log(LogStatus.INFO, "Go to resource page");
		LOGGER.info("Select impala from dropdown");
		impala.selectImpalaResource("Impala");
		waitExecuter.sleep(2000);

		// Select the cluster
		test.log(LogStatus.INFO, "Select cluster : " + clusterId);
		LOGGER.info("Select cluster : " + clusterId);
		homePage.selectMultiClusterId(clusterId);
		waitExecuter.waitUntilElementClickable(impalaPageObject.resourceUsagePointer);

		// Select 30 days from date picker
		test.log(LogStatus.INFO, "Select 30 days from date picker");
		LOGGER.info("Select 30 days from date picker");
		datePicker.clickOnDatePicker();
		waitExecuter.waitUntilElementClickable(impalaPageObject.resourceUsagePointer);
		datePicker.selectLast30Days();
		waitExecuter.waitUntilElementClickable(impalaPageObject.resourceUsagePointer);
		JavaScriptExecuter.scrollViewWithYAxis(driver, -100);

		// Navigate to Y axis
		test.log(LogStatus.INFO, "Navigate to Y axis");
		LOGGER.info("Navigate to Y axis");
		int scrollY = 150;
		JavaScriptExecuter.scrollViewWithYAxis(driver, scrollY);
		waitExecuter.sleep(2000);

		// De-selecting all the nodes
		test.log(LogStatus.INFO, "Deselecting all node checkbox ");
		LOGGER.info("Deselecting all nodes checkbox");
		waitExecuter.waitUntilElementClickable(impalaPageObject.memoryFooterLabels.get(0));
		waitExecuter.sleep(1000);
		List<WebElement> listOfMemoryFooterCheckbox = impalaPageObject.memoryFooterLabels;
		for (int i = 0; i < listOfMemoryFooterCheckbox.size(); i++) {
			listOfMemoryFooterCheckbox.get(i).click();
			waitExecuter.sleep(1000);
		}

		// Take the screenshot after deselecting nodes from footer of memory graph
		test.log(LogStatus.INFO, "Take the screenshot after deselecting nodes from footer of memory graph ");
		LOGGER.info("Take the screenshot after deselecting nodes from footer of memory graph");
		File screenshot = ScreenshotHelper.takeScreenshotOfElement(driver, impalaPageObject.memoryHighChartContainer,
				scrollY);
		ScreenshotHelper.saveFileToLocation(screenshot, DirectoryConstants.getScreenshotDir() + screenshot.getName());
		test.log(LogStatus.INFO, test.addScreenCapture(DirectoryConstants.getScreenshotDir() + screenshot.getName()));

		Assert.assertFalse(
				ScreenshotHelper.isContainColor(screenshot, GraphColorConstants.MemoryGraph.FIRST_NODE_COLOR),
				"Node is displayed when node checkbox is deselected.");
		test.log(LogStatus.PASS, "Successfully validated node is not displayed when node checkbox is deselected.");

		waitExecuter.waitUntilElementClickable(impalaPageObject.resourceUsagePointer);

		// Selecting one of the nodes
		test.log(LogStatus.INFO, "Selecting first node from footer ");
		LOGGER.info("Selecting first node from footer");
		waitExecuter.waitUntilElementClickable(impalaPageObject.memoryFooterLabels.get(0));
		impalaPageObject.memoryFooterLabels.get(0).click();

		// Take the screenshot after selecting first node from footer of memory graph
		test.log(LogStatus.INFO, "Take the screenshot after selecting first node from footer of memory graph ");
		LOGGER.info("Take the screenshot after selecting first node from footer of memory graph");
		screenshot = ScreenshotHelper.takeScreenshotOfElement(driver, impalaPageObject.memoryHighChartContainer,
				scrollY);
		ScreenshotHelper.saveFileToLocation(screenshot, DirectoryConstants.getScreenshotDir() + screenshot.getName());
		test.log(LogStatus.INFO, test.addScreenCapture(DirectoryConstants.getScreenshotDir() + screenshot.getName()));

		//Compare if the screenshot contain required node value
		test.log(LogStatus.INFO, "Compare if the screenshot contain required node value ");
		LOGGER.info("Compare if the screenshot contain required node value");
		Assert.assertTrue(ScreenshotHelper.isContainColor(screenshot, GraphColorConstants.MemoryGraph.FIRST_NODE_COLOR),
				"Node is not displayed when node checkbox is selected.");
		test.log(LogStatus.PASS, "Successfully validated node is displayed when node checkbox is selected.");
	}

}
