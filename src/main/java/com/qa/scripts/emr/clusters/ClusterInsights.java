package com.qa.scripts.emr.clusters;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import com.qa.pagefactory.emr.clusters.ClusterInsightsPageObject;
import com.qa.utils.WaitExecuter;

public class ClusterInsights {

	private final WaitExecuter waitExecuter;
	private final WebDriver driver;
	private final ClusterInsightsPageObject clusterInsightsPageObject;

	/**
	 * Constructor to initialize wait, driver and necessary objects
	 *
	 * @param driver - WebDriver instance
	 */
	public ClusterInsights(WebDriver driver) {
		this.driver = driver;
		waitExecuter = new WaitExecuter(driver);
		clusterInsightsPageObject = new ClusterInsightsPageObject(driver);
	}

	public void navigateToClusterInsights() {
		clusterInsightsPageObject.clusterInsights.click();
	}

	public List<String> retrieveResultSetHeaders() {
		List<String> headers = clusterInsightsPageObject.clusterResultTableHeader.stream()
				.map(map -> map.getText()).collect(Collectors.toList());
		return headers;
	}

	public void validateResultSet() {
		Assert.assertTrue(clusterInsightsPageObject.clusterResultTableValues.stream().count()>0);
		Assert.assertTrue(clusterInsightsPageObject.clusterResultSetRows.stream().count()>0);
	}

	public void downloadResultSet() {
		clusterInsightsPageObject.downloadMenu.click();
		clusterInsightsPageObject.downloadCSV.click();
	}

	public void searchClusterJob(String type) {
		String clusterValue="";
		if(type=="name") {
			clusterValue = clusterInsightsPageObject.clusterResultTableValues.get(1).getText();
		} 
		else {
			clusterValue = clusterInsightsPageObject.clusterResultTableValues.get(3).getText();

		}
		clusterInsightsPageObject.search.sendKeys(clusterValue);
	}

	public String enterIncorrectSearchParam() {
		clusterInsightsPageObject.search.sendKeys("Test Search");
		return clusterInsightsPageObject.noDataMsg.getText();
	}
	
	public void validateSearchResult() {
		Assert.assertTrue(clusterInsightsPageObject.clusterResultTableValues.stream().count()>0);
		Assert.assertTrue(clusterInsightsPageObject.clusterResultSetRows.stream().count()==1);
	}

	public void validateSorting(String sortingType) {
		TreeSet<String> resultant = new TreeSet<String>();
		TreeSet<String> initial = new TreeSet<String>();

		if(sortingType.equalsIgnoreCase("Cluster Id")) {
			initial = storeTokenList(sortingType);
			driver.findElement(By.xpath(String.format(clusterInsightsPageObject.sort, sortingType))).click();
			waitExecuter.sleep(1000);
			resultant = storeTokenList(sortingType);
		}
		else if(sortingType.equalsIgnoreCase("Cluster Name")) {
			initial = storeTokenList(sortingType);
			driver.findElement(By.xpath(String.format(clusterInsightsPageObject.sort, sortingType))).click();
			waitExecuter.sleep(1000);
			resultant = storeTokenList(sortingType);
		}

		else if(sortingType.equalsIgnoreCase("EMR Cost")) {
			initial = storeTokenList(sortingType);
			driver.findElement(By.xpath(String.format(clusterInsightsPageObject.sort, sortingType))).click();
			waitExecuter.sleep(1000);
			resultant = storeTokenList(sortingType);
		}

		else if(sortingType.equalsIgnoreCase("EBS Cost")) {
			initial = storeTokenList(sortingType);
			driver.findElement(By.xpath(String.format(clusterInsightsPageObject.sort, sortingType))).click();
			waitExecuter.sleep(1000);
			resultant = storeTokenList(sortingType);
		}

		else if(sortingType.equalsIgnoreCase("Total Cost")) {
			initial = storeTokenList(sortingType);
			driver.findElement(By.xpath(String.format(clusterInsightsPageObject.sort, sortingType))).click();
			waitExecuter.sleep(1000);
			resultant = storeTokenList(sortingType);
		}
		Assert.assertEquals(resultant, initial);
	}

	public TreeSet<String> storeTokenList(String sortingType){
		Set<String> initialSet = new HashSet<String>();
		TreeSet<String> up = null;
		if(sortingType.equalsIgnoreCase("Cluster Name")) {
			for(int i = 1;i < clusterInsightsPageObject.clusterResultSetRowsValues.size();i=i+10) {
				initialSet.add(clusterInsightsPageObject.clusterResultSetRowsValues.get(i).getText());
				up = new TreeSet<String>(initialSet);
			}
		}
		if(sortingType.equalsIgnoreCase("Cluster Id")) {
			for(int i = 0;i < clusterInsightsPageObject.clusterResultSetRowsValues.size();i=i+10) {
				initialSet.add(clusterInsightsPageObject.clusterResultSetRowsValues.get(i).getText());
				up = new TreeSet<String>(initialSet);
			}
		}
		if(sortingType.equalsIgnoreCase("EMR Cost")) {
			for(int i = 6;i < clusterInsightsPageObject.clusterResultSetRowsValues.size();i=i+10) {
				initialSet.add(clusterInsightsPageObject.clusterResultSetRowsValues.get(i).getText());
				up = new TreeSet<String>(initialSet);
			}
		}
		if(sortingType.equalsIgnoreCase("EBS Cost")) {
			for(int i = 6;i < clusterInsightsPageObject.clusterResultSetRowsValues.size();i=i+10) {
				initialSet.add(clusterInsightsPageObject.clusterResultSetRowsValues.get(i).getText());
				up = new TreeSet<String>(initialSet);
			}
		}
		if(sortingType.equalsIgnoreCase("Total CostCluster Name")) {
			for(int i = 7;i < clusterInsightsPageObject.clusterResultSetRowsValues.size();i=i+10) {
				initialSet.add(clusterInsightsPageObject.clusterResultSetRowsValues.get(i).getText());
				up = new TreeSet<String>(initialSet);
			}
		}

		return up;
	}

	public void filterByCluster(String clusterId) {
		clusterInsightsPageObject.clusterTags.sendKeys(clusterId);
		clusterInsightsPageObject.clusterTags.sendKeys(Keys.ENTER);	
	}

	public void validateLandingPage() {
		Assert.assertTrue(clusterInsightsPageObject.clusterResultTableValues.stream().count()>0);
		Assert.assertTrue(clusterInsightsPageObject.clusterResultSetRows.stream().count()>0);
		Assert.assertTrue(clusterInsightsPageObject.search.isDisplayed());
		Assert.assertTrue(clusterInsightsPageObject.insightsBody.isDisplayed());
	}

	public String retrieveEmptyMessage() {
		return clusterInsightsPageObject.noDataMsg.getText();
	}

        public void validateInsightsDetails() {
		String header = clusterInsightsPageObject.clusterResultTableValues.get(0).getText();
		Assert.assertTrue(clusterInsightsPageObject.insightsHeader.getText().contains("of "+header));
		Assert.assertTrue(clusterInsightsPageObject.insightsBody.isDisplayed());
		Assert.assertTrue(clusterInsightsPageObject.instanceName.stream().count()==2);
	}

	public void validateClusterInsightCostWithTotalCost() {
		List<WebElement> elList = clusterInsightsPageObject.instancesCost;
		String total = "";
		Double sum = 0.00;
		for(WebElement e : elList) {
			total = e.getText().substring(1).trim();
			sum = sum + Double.parseDouble(total);
		}
		String instanceTotal = "$ "+ String.valueOf(sum);
		String totalCost =	clusterInsightsPageObject.clusterResultSetRowsValues.get(8).getText();
		Assert.assertEquals(instanceTotal, totalCost);
	}
}
