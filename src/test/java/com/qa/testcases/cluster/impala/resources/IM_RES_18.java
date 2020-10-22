package com.qa.testcases.cluster.impala.resources;

import java.util.logging.Logger;

import com.qa.annotations.Marker;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.base.BaseClass;
import com.qa.pagefactory.TopPanelPageObject;
import com.qa.pagefactory.clusters.ImpalaPageObject;
import com.qa.scripts.DatePicker;
import com.qa.scripts.HomePage;
import com.qa.scripts.clusters.impala.Impala;
import com.qa.utils.JavaScriptExecuter;
import com.qa.utils.WaitExecuter;
import com.relevantcodes.extentreports.LogStatus;

/* 
 * @author - Ojasvi Pandey 
*/

@Marker.ImpalaResources
@Marker.All
public class IM_RES_18 extends BaseClass {
	private static final Logger LOGGER = Logger.getLogger(IM_RES_18.class.getName());

	@Test(dataProvider = "clusterid-data-provider")
	public void verifyDataDisplayedAsFilteredQueue(String clusterId) {
		test = extent.startTest("IM_RES_18.verifyDataDisplayedAsFilteredQueue (" + clusterId + ")",
				"Validate the \"Group By\" filter for Queue.");
		test.assignCategory(" Cluster/Impala Resources");
		test.log(LogStatus.INFO, "Login to the application");

		// Initialize all classes objects
		test.log(LogStatus.INFO, "Initialize all class objects");
		LOGGER.info("Initialize all class objects");
		WaitExecuter executer = new WaitExecuter(driver);
		ImpalaPageObject impalaPageObject = new ImpalaPageObject(driver);
		TopPanelPageObject topPanelPageObject = new TopPanelPageObject(driver);
		HomePage homePage = new HomePage(driver);
		Impala impala = new Impala(driver);
		DatePicker datePicker = new DatePicker(driver);

		// Click on Impala tab
		test.log(LogStatus.INFO, "Go to impala page");
		LOGGER.info("Clicking on Impala tab");
		executer.waitUntilElementClickable(topPanelPageObject.impalaTab);
		JavaScriptExecuter.clickOnElement(driver, topPanelPageObject.impalaTab);
		executer.sleep(1000);
		executer.waitUntilElementPresent(impalaPageObject.getImpalaPageHeader);

		// Select the cluster
		test.log(LogStatus.INFO, "Select cluster : " + clusterId);
		LOGGER.info("Select cluster : " + clusterId);
		homePage.selectMultiClusterId(clusterId);
		executer.waitUntilPageFullyLoaded();

		// Select 30 days from date picker
		test.log(LogStatus.INFO, "Select 30 days from date picker");
		LOGGER.info("Select 30 days from date picker");
		datePicker.clickOnDatePicker();
		executer.sleep(1000);
		datePicker.selectLast30Days();
		executer.sleep(1000);
		executer.waitUntilPageFullyLoaded();
		executer.sleep(1000);

		// Click on group by dropdown and select queue in filter
		test.log(LogStatus.INFO, "Click on group by dropdown and select queue in filter");
		LOGGER.info("Click on group by dropdown and select queue in filter");
		executer.waitUntilElementClickable(impalaPageObject.groupByDropdownButton);
		executer.sleep(3000);
		impalaPageObject.groupByDropdownButton.click();
		executer.sleep(1000);
		impalaPageObject.groupByQueueList.click();
		executer.sleep(1000);
		impala.clearFilter();
		executer.sleep(1000);
		executer.waitUntilPageFullyLoaded();
		executer.sleep(1000);

		for (int i = 0; i < impalaPageObject.filterElements.size(); i++) {
			String queueName = impalaPageObject.filterElements.get(i).getText();
			impalaPageObject.filterElements.get(i).click();
			executer.sleep(1000);
			executer.waitUntilPageFullyLoaded();
			test.log(LogStatus.INFO, "Selecting the queue: " + queueName + " in filter.");
			LOGGER.info("Selecting the queue: " + queueName + " in filter.");
			executer.sleep(2000);
			boolean isTagPresent = false;
			for (String graphTag : impala.getQueriesGraphLabels()) {
				if (graphTag.equals(queueName)) {
					isTagPresent = true;
				} else if (graphTag.contains("...") && queueName.contains(graphTag.split("...")[0])) {
					isTagPresent = true;
				} else {
					isTagPresent = false;
				}
			}
			Assert.assertTrue(isTagPresent, "Filter user not displayed for queue: " + queueName);
			test.log(LogStatus.PASS, "Graph displayed the user based on filter for queue : " + queueName);
			impalaPageObject.filterInput.click();
			executer.sleep(1000);
		}
		impalaPageObject.filterInput.click();
		executer.sleep(1000);
	}
}
