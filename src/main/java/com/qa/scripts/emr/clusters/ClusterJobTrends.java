package com.qa.scripts.emr.clusters;

import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import com.qa.pagefactory.emr.clusters.JobsTrendsPageObject;
import com.qa.utils.JavaScriptExecuter;
import com.qa.utils.TestUtils;
import com.qa.utils.WaitExecuter;

public class ClusterJobTrends {

	private final WaitExecuter waitExecuter;
	private final WebDriver driver;
	private final JobsTrendsPageObject jobtreJobsTrendsPageObject;

	/**
	 * Constructor to initialize wait, driver and necessary objects
	 *
	 * @param driver - WebDriver instance
	 */
	public ClusterJobTrends(WebDriver driver) {
		this.driver = driver;
		waitExecuter = new WaitExecuter(driver);
		jobtreJobsTrendsPageObject = new JobsTrendsPageObject(driver);
	}

	public void navigateToClusterInsights() {
		waitExecuter.sleep(3500);
		jobtreJobsTrendsPageObject.jobTrends.click();
	}

	public void filterByGroup(String filter) {
		jobtreJobsTrendsPageObject.groupByDropdownButton.click();
		waitExecuter.waitUntilElementClickable(jobtreJobsTrendsPageObject.groupByQueueList);
		if(filter.equalsIgnoreCase("queue"))
		{
			jobtreJobsTrendsPageObject.groupByQueueList.click();
		}
		else if(filter.equalsIgnoreCase("user"))
		{
			jobtreJobsTrendsPageObject.groupByUserList.click();
		}
		else if(filter.equalsIgnoreCase("Application Type"))
		{
			jobtreJobsTrendsPageObject.groupByAppTypeList.click();
		}
		else {
			jobtreJobsTrendsPageObject.groupByStateList.click();
		}
	}

	public void removeFilters(String type) {
		waitExecuter.sleep(2000);
		for(WebElement e : jobtreJobsTrendsPageObject.selectedFilterValues) {
			String filterText = e.getText().substring(2);
			if(type!=filterText) {
				JavaScriptExecuter.clickOnElement(driver, jobtreJobsTrendsPageObject.filterRemoveElements.get(0));
			}
		}
	}

	public void selectCluster() {
		jobtreJobsTrendsPageObject.clusterDropdown.click();
		jobtreJobsTrendsPageObject.listOfClusters.get(2).click();
	}

	public void validateLandingPage() {
		Assert.assertTrue(jobtreJobsTrendsPageObject.graphXAxisDateLabels.isDisplayed());
		Assert.assertTrue(jobtreJobsTrendsPageObject.graphYAxisLabels.isDisplayed());
		Assert.assertTrue(jobtreJobsTrendsPageObject.downloadMenu.isDisplayed());
		jobtreJobsTrendsPageObject.memoryFooterLabels.stream()
		.map(footer -> footer.isDisplayed());
	}

	public void validateGraphs(String type) {
		waitExecuter.sleep(2000);
		List<String> graphFilter = jobtreJobsTrendsPageObject.memoryFooterLabels.stream()
				.map( m -> m.getText()).collect(Collectors.toList());
		Assert.assertTrue(graphFilter.contains(type));
		Assert.assertTrue(jobtreJobsTrendsPageObject.graphXAxisDateLabels.isDisplayed());
		Assert.assertTrue(jobtreJobsTrendsPageObject.graphYAxisLabels.isDisplayed());
	}

	public void selectDownloadOption(String format) {
		waitExecuter.sleep(2000);
		jobtreJobsTrendsPageObject.downloadMenu.click();
		driver.findElement(By.xpath(String.format(jobtreJobsTrendsPageObject.downloadFormat,format))).click();
	}

	public void validateEmptyGraph() {
		Assert.assertFalse(TestUtils.isElementDisplayed(jobtreJobsTrendsPageObject.graphXAxisDateLabels));
		Assert.assertFalse(TestUtils.isElementDisplayed(jobtreJobsTrendsPageObject.graphYAxisLabels));
	}

	public void selectPointOnGraph() {
		jobtreJobsTrendsPageObject.graphXAxisDatePoints.get(1).click();
	}

	public void validateRunningJobs() {
		Assert.assertTrue(jobtreJobsTrendsPageObject.runningJobs.isDisplayed());
	}

	public void validateEmptyGraphData() {
		Assert.assertTrue(jobtreJobsTrendsPageObject.noDataHeader.getText().contains("No Data To Display"));
	}

	public void validateCluster() {
		if(!TestUtils.isElementDisplayed(jobtreJobsTrendsPageObject.graphXAxisDateLabels)) {
			validateEmptyGraphData();
		}
		else {
			Assert.assertTrue(jobtreJobsTrendsPageObject.graphXAxisDateLabels.isDisplayed());
			Assert.assertTrue(jobtreJobsTrendsPageObject.graphYAxisLabels.isDisplayed());
		}
	}
}
