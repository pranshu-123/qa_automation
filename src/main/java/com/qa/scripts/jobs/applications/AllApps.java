package com.qa.scripts.jobs.applications;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import com.qa.pagefactory.jobs.ApplicationsPageObject;
import com.qa.utils.WaitExecuter;

public class AllApps {

	private WaitExecuter waitExecuter;
	private WebDriver driver;
	private ApplicationsPageObject applicationsPageObject;
	private Actions action;
	private static final Logger LOGGER = Logger.getLogger(AllApps.class.getName());

	/**
	 * Constructer to initialize wait, driver and necessary objects
	 * 
	 * @param driver
	 *            - WebDriver instance
	 */
	public AllApps(WebDriver driver) {
		waitExecuter = new WaitExecuter(driver);
		applicationsPageObject = new ApplicationsPageObject(driver);
		action = new Actions(driver);
		this.driver = driver;
	}

	public List<String> getCalendarRanges() {
		List<WebElement> getCalendarRangeElements = applicationsPageObject.dateRanges;
		waitExecuter.sleep(1000);
		List<String> listOfCaledarRanges = new ArrayList<>();
		LOGGER.info("Total number of ranges in datepicker: " + getCalendarRangeElements.size());
		for (int i = 0; i < getCalendarRangeElements.size(); i++) {
			LOGGER.info("The range in the calendar " + getCalendarRangeElements.get(i).getText());
			listOfCaledarRanges.add(getCalendarRangeElements.get(i).getText());
		}

		return listOfCaledarRanges;
	}

	/* Select cluster from list */
	public void selectCluster(String clusterId) {
		removeClusterIfPresent();
		applicationsPageObject.clusterSearchBox.click();
		waitExecuter.sleep(1000);
		LOGGER.info("Search for clusrer: " + clusterId);
		applicationsPageObject.clusterSearchBox.sendKeys(clusterId);
		waitExecuter.sleep(1000);
		applicationsPageObject.select1stCluster.click();
		waitExecuter.sleep(1000);
	}

	/* Check and remove cluster from searchbox */
	public void removeClusterIfPresent() {
		if (applicationsPageObject.removeCluster != null) {
			applicationsPageObject.clusterSearchBox.sendKeys(Keys.BACK_SPACE);
			waitExecuter.sleep(1000);
			applicationsPageObject.clusterSearchBox.clear();
			waitExecuter.sleep(1000);
		} else
			LOGGER.info("No cluster to remove");
	}

}
