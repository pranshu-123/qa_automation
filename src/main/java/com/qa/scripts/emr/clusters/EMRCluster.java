package com.qa.scripts.emr.clusters;

import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import com.qa.pagefactory.emr.clusters.ClusterDetailsPageObjects;
import com.qa.pagefactory.emr.clusters.ClusterHomePageObjects;
import com.qa.utils.WaitExecuter;

public class EMRCluster {

	private static final Logger LOGGER = Logger.getLogger(EMRChargeback.class.getName());
	private final WaitExecuter waitExecuter;
	private final WebDriver driver;
	private final ClusterHomePageObjects clusterHomePageObjects;
	private final ClusterDetailsPageObjects clusterDetailsPageObjects;

	/**
	 * Constructer to initialize wait, driver and necessary objects
	 *
	 * @param driver - WebDriver instance
	 */
	public EMRCluster(WebDriver driver) {
		waitExecuter = new WaitExecuter(driver);
		this.driver = driver;
		clusterHomePageObjects = new ClusterHomePageObjects(driver);
		clusterDetailsPageObjects = new ClusterDetailsPageObjects(driver);
	}

	public void selectCluster() {
		WaitExecuter waitExecuter = new WaitExecuter(driver);
		waitExecuter.waitUntilElementClickable(clusterHomePageObjects.cluster);
		waitExecuter.sleep(2000);
		clusterHomePageObjects.cluster.click();
		waitExecuter.waitUntilPageFullyLoaded();
	}

	public void validateHomePage() {
		Assert.assertTrue(clusterHomePageObjects.idListBox.isDisplayed(), "ID dropdown not listed");
		Assert.assertTrue(clusterHomePageObjects.nameTextBox.isDisplayed(),"Name textbox not listed");
		Assert.assertTrue(clusterHomePageObjects.statusListBox.isDisplayed(),"Status dropdown not listed");
		Assert.assertTrue(clusterHomePageObjects.clusterWithoutUnravelChkBox.isDisplayed(),"Installation without unravel checkbox not listed");
		Assert.assertTrue(clusterHomePageObjects.clusterWithUnravelChkBox.isDisplayed(),"Installation with unravel checkbox not listed");
	}

	public List<String> fetchClusterDetailsTableHeader() {
		return clusterHomePageObjects.resultTableHeader.stream()
				.map( details -> details.getText())
				.collect(Collectors.toList());
	}

	public void validateClusterDetailsTableValue(String clusterId) {
		Assert.assertTrue(clusterHomePageObjects.resultTableValues.stream().count() > 0);
		List<String> clusterDetails = clusterHomePageObjects.resultTableValues.stream()
				.map(details -> details.getText())
				.collect(Collectors.toList());
		Assert.assertTrue(clusterDetails.contains(clusterId));
	}

	public List<String> retrieveClusterIds() {
		return clusterHomePageObjects.listOfClusterId.stream()
				.map( details -> details.getText())
				.collect(Collectors.toList());
	}

	public List<String> retrieveClusterNames() {
		return clusterHomePageObjects.listOfClusterNames.stream()
				.map( details -> details.getText())
				.collect(Collectors.toList());
	}

	public String filterByClusterId() {
		clusterHomePageObjects.idListBox.click();
		WebElement el = clusterHomePageObjects.listOfClusterId.get(0);
		el.click();
		return el.getText();
	}

	public void filterByName(String name) {
		clusterHomePageObjects.nameTextBox.sendKeys(name);
	}

	public void validateClusterDetailAsPerName(String name) {
		Assert.assertTrue(clusterHomePageObjects.listOfClusterNames.stream()
				.map( details -> details.getText())
				.collect(Collectors.toList()).contains(name));
	}

	public void selectStatus(String status) {
		clusterHomePageObjects.statusListBox.click();
		for(WebElement el : clusterHomePageObjects.listOfStatuses) {
			if(el.getText().equalsIgnoreCase(status)) {
				el.click();
			}
		}
	}

	public void validateClusterStatus(String status) {
		List<String> clusterDetails = clusterHomePageObjects.resultTableValues.stream()
				.map(details -> details.getText())
				.collect(Collectors.toList());
		Assert.assertTrue(clusterDetails.contains(status));
	}

	public String retrieveEmptyClusterDetails() {
		return clusterHomePageObjects.noDataPresent.getText();
	}

	public void selectInstallationType(String type) {
		if(type.matches("with")) {
			clusterHomePageObjects.clusterWithoutUnravelChkBox.click();
		}
		else {
			clusterHomePageObjects.clusterWithUnravelChkBox.click();
		}
	}

	public void validateWithWithoutUnravelClustersCount() {
		long totalClusters = clusterHomePageObjects.resultTableValues.stream().count();
		long totalUnRavelClusters = clusterHomePageObjects.unravelClusters.stream().count();
		Assert.assertEquals(totalClusters, totalUnRavelClusters);
	}

	public void selectJobs() {
		clusterHomePageObjects.viewJobs.get(0).click();
	}

	public void navigateToSecondaryPage() {

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

	public void validateJobsPage(String clusterId) {
		String url = driver.getCurrentUrl();
		Assert.assertTrue(url.contains("jobs/applications?clusterUid="+clusterId));
	}

	public String retrieveNodeDownsizingCount() {
		String count = clusterHomePageObjects.eventCount.getText();
		String nodeCount = count.substring(count.indexOf("("), count.indexOf(")"));
		return nodeCount;
	}

	public String retrieveClusterCount() {
		String count = clusterHomePageObjects.clusterCount.getText();
		String clusterCount = count.substring(count.indexOf(" "),count.indexOf(" ")+2);
		return clusterCount;
	}

}
