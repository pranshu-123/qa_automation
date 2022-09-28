package com.qa.scripts.databricks.reports;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import com.qa.pagefactory.appsDetailsPage.AppDetailsPageObject;
import com.qa.pagefactory.databricks.reports.ReportSparkPageObject;
import com.qa.scripts.appdetails.SparkAppsDetailsPage;
import com.qa.utils.TestUtils;
import com.qa.utils.WaitExecuter;

public class SparkDetails {

	private WaitExecuter waitExecuter;
	private WebDriver driver;
	private AppDetailsPageObject appDetailsPageObject;
	private ReportSparkPageObject sparkPageObject;

	Logger logger = Logger.getLogger(SparkAppsDetailsPage.class.getName());

	/**
	 * Constructor to initialize wait, driver and necessary objects
	 *
	 * @param driver - WebDriver instance
	 */
	public SparkDetails(WebDriver driver) {
		this.driver = driver;
		waitExecuter = new WaitExecuter(driver);
		sparkPageObject = new ReportSparkPageObject(driver);
		appDetailsPageObject = new AppDetailsPageObject(driver);
	}

	public void navigateToSparkPage() {

		Set<String> handles = driver.getWindowHandles();
		System.out.println("Handles" + handles);

		String parentHandle = driver.getWindowHandle();
		System.out.println("parentHandle" + parentHandle);

		Iterator<String> itr = handles.iterator();
		while (itr.hasNext()) {
			String childHandle = itr.next();
			if (!parentHandle.equals(childHandle)) {
				System.out.println("childHandle" + childHandle);

				driver.switchTo().window(childHandle);

				System.out.println("Child windows Title: " + driver.getTitle());
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
			}
		}
	}

	public List<String> fetchAppDetails() {
		List<String> values = sparkPageObject.appsDetails.stream()
				.map(el -> el.getText()).collect(Collectors.toList());
		return values;
	}

	public void loadDiagnostics() {
		waitExecuter.sleep(1000);
		sparkPageObject.actionMenu.click();
		sparkPageObject.loadDiagnostics.click();
	}

	public List<String> fetchDiagnosticHead() {
		List<String> values = sparkPageObject.diagnosticsHead.stream()
				.map(el -> el.getText()).collect(Collectors.toList());
		return values;
	}

	public void validateDiagnosticsValue() {
		Assert.assertTrue(sparkPageObject.diagnosticValues.size() > 0, "Diagnostics Values displayed correctly");
	}

	public void cancelDiagnostics() {
		sparkPageObject.cancelDiagnostics.click();
	}

	public String returnJobDuration() {
		return sparkPageObject.duration.getText();
	}

	public String returnStagesCount() {
		return sparkPageObject.stagesCount.getText();
	}

	public String returnAppId() {
		return sparkPageObject.getHeaderAppId.getText();
	}

	public List<String> fetchSummaryTabsValues() {
		List<String> values = sparkPageObject.summaryTabs.stream()
				.map(el -> el.getText()).collect(Collectors.toList());
		return values;
	}

	public void validateAppNavigationTab() {
		sparkPageObject.navigationTab.click();
		Assert.assertTrue(sparkPageObject.appNavigationHeader.size() > 0, "App Navigation Header displayed correctly");
		Assert.assertTrue(sparkPageObject.appNavigationValue.size() > 0, "App Navigation Values displayed correctly");
	}

	public void validateGanttChartTab() {
		sparkPageObject.ganttChartTab.click();
		Assert.assertTrue(sparkPageObject.ganttChartHeader.size() > 0, "App Navigation Header displayed correctly");
		Assert.assertTrue(sparkPageObject.ganttChartValue.size() > 0, "App Navigation Values displayed correctly");
	}

	public void selectJobToPopulateGanttChart() {
		sparkPageObject.ganttChartTab.click();
		sparkPageObject.ganttChartValue.get(0).click();
		sparkPageObject.ganttChartStage.click();
	}

	public void validateGanttChart() {
		Assert.assertTrue(sparkPageObject.taskAttempt.isDisplayed(), "Task Attempt chart listed");
		Assert.assertTrue(sparkPageObject.program.isDisplayed(), "Program codes are listed");
		Assert.assertTrue(sparkPageObject.timeline.isDisplayed(), "Timelines are listed");
		Assert.assertTrue(sparkPageObject.timings.isDisplayed(), "Timings are listed");
	}

	public void validateSummaryTabValues() {
		String[] actualSummaryTabsValues = {"Analysis","Resources","Errors","Configuration","Logs","Tags","Program","Timings"};
		List<String> actualSummaryTabsValueList =new ArrayList<>(Arrays.asList(actualSummaryTabsValues));
		List<String> summaryTabs = fetchSummaryTabsValues();
		if(summaryTabs.size()==9) {
			actualSummaryTabsValueList.add("SQL");
		}
		for(String summaryDetails : actualSummaryTabsValueList) {
			Assert.assertTrue(summaryTabs.contains(summaryDetails), summaryDetails + " tab not listed in Spark Page");
		}
	}

	public void validateSparkDetailsPage(LinkedHashMap<String, String> appValues) {
		waitExecuter.sleep(1000);
		String appId = returnAppId();
		Assert.assertTrue(appValues.containsValue(appId), "App Id is either incorrect or not captured in spark page");
		logger.info("App ID is displayed in Spark details page.");
		logger.info("Stage Count is displayed in Spark details page.");
	}

	public void validateUserAndClusterSparkDetails(LinkedHashMap<String, String> appValues) {
		List<String> sparkAppDetails = fetchAppDetails();
		Assert.assertTrue(sparkAppDetails.contains("Owner : "+appValues.get("User")+" |"),appValues.get("User") + " not listed in Spark Page");
		Assert.assertTrue(sparkAppDetails.contains("Cluster: "+appValues.get("Cluster Name")+" |"),appValues.get("Cluster") + " not listed in Spark Page");
	}

	public void validateInsightsAndRecommendationForSpark() {
		Assert.assertTrue(sparkPageObject.insights.isDisplayed());
	}
	
	public void navigateToAppMenu(String menu) {
		if(menu.equalsIgnoreCase("resources")) {
			sparkPageObject.resources.click();
		}
	}
	
	public void validateResourcesGraph() {
		Assert.assertTrue(sparkPageObject.taskAttemptGraph.isDisplayed());
		Assert.assertTrue(sparkPageObject.vcoresGraph.isDisplayed());
		Assert.assertTrue(sparkPageObject.memoryGraph.isDisplayed());
	}
}
