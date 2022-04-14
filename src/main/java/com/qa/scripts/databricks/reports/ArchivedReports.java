package com.qa.scripts.databricks.reports;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import com.qa.pagefactory.databricks.reports.ReportsArchivedPageObject;
import com.qa.pagefactory.databricks.reports.ReportsTopXPageObject;
import com.qa.scripts.DatePicker;
import com.qa.utils.WaitExecuter;

public class ArchivedReports {

	private static final Logger LOGGER = Logger.getLogger(ArchivedReports.class.getName());

	private final WaitExecuter waitExecuter;
	private final WebDriver driver;
	private final ReportsArchivedPageObject reportsArchivedPageObject;
	private final DatePicker datePicker;
	private final ReportsTopXPageObject reportsTopXPageObject;

	/**
	 * Constructor to initialize wait, driver and necessary objects
	 *
	 * @param driver - WebDriver instance
	 */
	public ArchivedReports(WebDriver driver) {
		waitExecuter = new WaitExecuter(driver);
		this.driver = driver;
		reportsArchivedPageObject = new ReportsArchivedPageObject(driver);
		datePicker = new DatePicker(driver);
		reportsTopXPageObject = new ReportsTopXPageObject(driver);
	}

	public void validateArchivedPageObjects() {
		Assert.assertTrue(reportsArchivedPageObject.searchBox.isDisplayed(), "Archived Page Search Box not displayed");
		LOGGER.info("Archived Page Search Box displayed");
		Assert.assertTrue(reportsArchivedPageObject.success.isDisplayed(), "Success button not displayed");
		LOGGER.info("Success button displayed and clickable");
		Assert.assertTrue(reportsArchivedPageObject.copyLatestReportUrl.isDisplayed(), "Copy Url button not displayed");
		LOGGER.info("Copy Url button displayed and clickable");
		Assert.assertTrue(reportsArchivedPageObject.addNewReport.isDisplayed(), "New Report button not displayed");
		LOGGER.info("New report button displayed and clickable");
		Assert.assertTrue(reportsArchivedPageObject.scheduledNewReport.isDisplayed(), "Scheduled new report button not displayed");
		LOGGER.info("Scheduled new report button displayed and clickable");
		Assert.assertTrue(reportsArchivedPageObject.viewReports.isDisplayed(), "View report button not displayed");
		LOGGER.info("View report button displayed and clickable");
	}

	public void selectViewReport() {
		reportsArchivedPageObject.viewReports.click();
	}

	public void selectReportList() {
		waitExecuter.sleep(2000);
		reportsArchivedPageObject.success.click();
	}

	public void selectScheduledReport() {
		reportsArchivedPageObject.scheduledNewReport.click();
	}

	public void downloadArchivedReport() {
		reportsArchivedPageObject.downloadList.get(0).click();
	}

	public void deleteArchivedReport() {
		reportsArchivedPageObject.deleteList.get(0).click();
		reportsArchivedPageObject.deleteYes.click();
	}

	public void selectCreateReports() {
		reportsArchivedPageObject.addNewReport.click();
	}

	public void validateSorting(String sortBy) {
		Set<String> initialSet = new HashSet<String>();
		Set<String> resultantSet = new HashSet<String>();
		TreeSet<String> sort = null;

		switch(sortBy) {
		case "Name":
			//Fetching data before applying sorting
			initialSet	= reportsArchivedPageObject.archivedTopXReportList
			.stream().map(el -> el.getText())
			.collect(Collectors.toSet());
			reportsArchivedPageObject.sortName.click();

			//Fetching data after applying sorting
			resultantSet = reportsArchivedPageObject.archivedTopXReportList
					.stream().map(el -> el.getText())
					.collect(Collectors.toSet());
			break;

		case "Created":
			//Fetching data before applying sorting
			initialSet	= reportsArchivedPageObject.archivedTopXReportCreatedDates
			.stream().map(el -> el.getText())
			.collect(Collectors.toSet());
			reportsArchivedPageObject.sortCreated.click();

			//Fetching data after applying sorting
			resultantSet = reportsArchivedPageObject.archivedTopXReportCreatedDates
					.stream().map(el -> el.getText())
					.collect(Collectors.toSet());
			break;

		}

		sort = new TreeSet<String>(initialSet);
		Assert.assertNotEquals(resultantSet, sort);

	}


	public String searchReport(String reportName) {
		reportsArchivedPageObject.searchBox.sendKeys(reportName);
		waitExecuter.sleep(2000);
		return reportsArchivedPageObject.archivedTopXReportList.get(0).getText();
	}

	public String returnReportStatus() {
		waitExecuter.sleep(2000);
		return reportsArchivedPageObject.success.getText();
	}

	public String createNewReportWithDefaultValues(String top) {
		reportsTopXPageObject.topxTextArea.sendKeys(top);
		reportsTopXPageObject.newReportRunBtn.click();
		waitExecuter.sleep(10000);
		return top;
	}

	public String returnLatestReportStatus() {
		waitExecuter.sleep(2000);
		return reportsArchivedPageObject.latestSuccessfulReport.getText();
	}

	public void validateReportList() {
		Assert.assertTrue(reportsArchivedPageObject.archivedTopXReportList
				.stream().iterator().next().getText().contains("Top X"));
	}

	public String copyUrlAndNavigate() {
		reportsArchivedPageObject.copyUrlList.get(0).click();
		waitExecuter.sleep(3000);
		//get copied string from clipboard
		Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
		String url = null;
		try {
			url = (String) clipboard.getContents(null).getTransferData(DataFlavor.stringFlavor);
		} catch (UnsupportedFlavorException | IOException e) {
			e.printStackTrace();
		}

		//open in separate tab
		driver.navigate().to(url);  
		return url;
	}

	public void selectReport() {
		reportsArchivedPageObject.archivedTopXReportList.get(0).click();
	}

	public String fetchReportName() {
		return 	reportsArchivedPageObject.archivedTopXReportList.get(0).getText();
	}
}
