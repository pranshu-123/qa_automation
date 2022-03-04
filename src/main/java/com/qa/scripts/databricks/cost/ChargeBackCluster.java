package com.qa.scripts.databricks.cost;

import com.qa.pagefactory.clusters.ChargebackImpalaPageObject;
import com.qa.pagefactory.databricks.cost.ChargebackClusterPageObject;
import com.qa.scripts.DatePicker;
import com.qa.utils.WaitExecuter;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class ChargeBackCluster {

	private static final Logger LOGGER = Logger.getLogger(ChargeBackCluster.class.getName());

	private final WaitExecuter waitExecuter;
	private final WebDriver driver;
	private final ChargebackImpalaPageObject chargebackImpalaPageObject;
	private final ChargebackClusterPageObject chargebackClusterPageObject;
	private final DatePicker datePicker;

	/**
	 * Constructor to initialize wait, driver and necessary objects
	 *
	 * @param driver - WebDriver instance
	 */
	public ChargeBackCluster(WebDriver driver) {
		waitExecuter = new WaitExecuter(driver);
		this.driver = driver;
		chargebackImpalaPageObject = new ChargebackImpalaPageObject(driver);
		chargebackClusterPageObject = new ChargebackClusterPageObject(driver);
		datePicker = new DatePicker(driver);
	}

	public void navigateToCostTab(String tab) {
		waitExecuter.sleep(3000);
		waitExecuter.waitUntilPageFullyLoaded();
		waitExecuter.waitUntilElementClickable(chargebackClusterPageObject.costChargeBackTab);
		try {
			if(tab.equalsIgnoreCase("Trends")) {
				chargebackClusterPageObject.costTrendsTab.click();
			}
			else if(tab.equalsIgnoreCase("Chargeback")) {
				chargebackClusterPageObject.costChargeBackTab.click();
			}
			else {
				chargebackClusterPageObject.costBudgetTab.click();
			}
		}
		catch(ElementClickInterceptedException e) {
			e.printStackTrace();
		}
	}

	public List<String> fetchAllGroupByFilterValues(){
		waitExecuter.sleep(3000);
		chargebackClusterPageObject.groupBy.click();
		List<String> list =chargebackClusterPageObject.groupByValues.stream()
				.map(a-> a.getText())
				.collect(Collectors.toList());
		return list;
	}

	public void validatePieChartGraph(String[] headers) {
		List<String> list = chargebackClusterPageObject.graphsHeader.stream()
				.map(graph -> graph.getText()).collect(Collectors.toList());
		for(String s : headers) {
			list.contains(s);
		}
		chargebackClusterPageObject.pieGraph.stream()
		.forEach(graph -> graph.isDisplayed());
		LOGGER.info("Pie Chart is displyed");
	}

	public void validateGeneratedPieChartValues() {
		waitExecuter.sleep(1500);
		List<String> pieChartValues = chargebackClusterPageObject.pieChartValues.stream()
				.map(values -> values.getText()).distinct().skip(1)
				.collect(Collectors.toList());

		//This is match result set data with generated graph values
		List<String> resultSet =chargebackClusterPageObject.resultSetValues.stream()
				.map(a-> a.getText())
				.collect(Collectors.toList());
		for(String s : pieChartValues) {
			Assert.assertTrue(resultSet.contains(s));
			LOGGER.info("Pie Chart is populated with " +s+" result values");
		}
	}

	public void validateResultSet() {
		waitExecuter.sleep(1000);
		List<String> list =chargebackClusterPageObject.resultSetValues.stream()
				.map(a-> a.getText())
				.collect(Collectors.toList());
		Assert.assertTrue(list.size()>0);
	}

	public void validateResultSetIsDisplayedWithValues(String groupBy) {
		String header = chargebackClusterPageObject.lblResultSetHeader.getText();
		Assert.assertTrue(header.equalsIgnoreCase(groupBy));
		chargebackClusterPageObject.dbu.isDisplayed();
		chargebackClusterPageObject.cost.isDisplayed();
		chargebackClusterPageObject.clusterCount.isDisplayed();
		Assert.assertTrue(chargebackClusterPageObject.resultSetValues.stream().count()>0);
		chargebackClusterPageObject.resultSetValues.stream().iterator().next().isDisplayed();
		LOGGER.info("Result set is populaed with selected Group By: "+groupBy + " values");
	}

	public void selectOptimize() {
		waitExecuter.sleep(2000);
		chargebackClusterPageObject.optimize.click();
	}

	public String selectCopyUrl() {
		waitExecuter.sleep(2000);
		chargebackClusterPageObject.copyURL.get(0).click();
		return chargebackClusterPageObject.urlLinks.get(0).getAttribute("href");
	}

	public void validateDate() {
		String date = datePicker.getDate(30);
		String selectedDate = chargebackClusterPageObject.selectedDates.getText();
		Assert.assertTrue(selectedDate.contains(date));
		LOGGER.info("Selected date is displayed");
	}

	public void selectDownloadOption(String type,String format) {
		waitExecuter.sleep(2000);
		if(type.equalsIgnoreCase("dbu")) {
			chargebackClusterPageObject.graphsThreeDots.get(0).click();
			driver.findElement(By.xpath(String.format(chargebackClusterPageObject.dbuDownloadFormat,format))).click();
		}
		else if(type.equalsIgnoreCase("cost")) {
			chargebackClusterPageObject.graphsThreeDots.get(1).click();
			driver.findElement(By.xpath(String.format(chargebackClusterPageObject.costDownloadFormat,format))).click();
		}
		else {
			chargebackClusterPageObject.graphsThreeDots.get(2).click();	
			driver.findElement(By.xpath(String.format(chargebackClusterPageObject.clusterDownloadFormat,format))).click();
		}
		LOGGER.info(type+ " is selected to download resultant graph in "+ format + " format");
	}

	public String calculateDBUSumFromResultSet() {
		double sum =0.00;
		int size = chargebackClusterPageObject.resultSetValues.size();
		for(int i =1; i< size;i=i+7) {
			sum = sum + Double.parseDouble(chargebackClusterPageObject.resultSetValues.get(i).getText());
		}
		return String.valueOf(sum);

	}

	public String fetchDBUValueFromGraph() {
		return chargebackClusterPageObject.dbuValue.getText();
	}

	public void selectChargebackType(String type) {
		if(type.equalsIgnoreCase("Cluster")) {}
		else {
			chargebackClusterPageObject.chargeBackType.click();
			chargebackClusterPageObject.chargeBackTypeValues.get(1).click();
		}
	}

	public void filterBy(String filter) {
		waitExecuter.waitUntilElementClickable(chargebackClusterPageObject.filterByDropDown);
		waitExecuter.sleep(2500);
		chargebackClusterPageObject.filterByDropDown.click();
		driver.findElement(By.xpath(String.format(chargebackClusterPageObject.filterByValues,filter))).click();
	}

	public void filterByTagKey(String tagKey) {		
		waitExecuter.sleep(2500);
		chargebackClusterPageObject.tagKeyDropdown.click();
		chargebackClusterPageObject.tagKeySearchField.sendKeys(tagKey);
		chargebackClusterPageObject.tagKeySearchField.sendKeys(Keys.ENTER);
		LOGGER.info("Specified Tag Key selected: "+tagKey);
	}	

	public String calculateClusterSumFromResultSet() {
		double sum =0.00;
		int size = chargebackClusterPageObject.resultSetValues.size();
		for(int i =5; i< size;i=i+7) {
			sum = sum + Double.parseDouble(chargebackClusterPageObject.resultSetValues.get(i).getText());
		}
		String value = String.valueOf(sum);
		return (value.charAt(0) + "k");
	}

	public String fetchClusterValueFromGraph() {
		return chargebackClusterPageObject.clusterValue.getText();
	}


	public String calculateTotalCostFromResultSet() {
		double sum =0.00;
		int size = chargebackClusterPageObject.resultSetValues.size();
		for(int i =3; i< size;i=i+7) {
			sum = sum + Double.parseDouble(chargebackClusterPageObject.resultSetValues.get(i).getText().substring(2));
		}
		return ("$ "+String.valueOf(sum));

	}

	public String fetchTotalCostFromGraph() {
		return chargebackClusterPageObject.costValue.getText();
	}

	public String fetchJobRunsValueFromGraph() {
		return chargebackClusterPageObject.JobRunsValue.getText();
	}

	public void validateSorting(String sortingType) {
		Set<String> initialSet = new HashSet<String>();
		Set<String> resultantSet = new HashSet<String>();
		TreeSet<String> up = null;
		TreeSet<String> down = null ;

		int size = chargebackClusterPageObject.resultSetValues.size();

		for(int i =5; i< size;i=i+7) {
			initialSet.add(chargebackClusterPageObject.resultSetValues.get(i).getText());
		}

		if(sortingType.equalsIgnoreCase("up")) {
			up = new TreeSet<String>(initialSet);
			chargebackClusterPageObject.sortUp.click();
			waitExecuter.sleep(1000);
			for(int i =5; i< size;i=i+7) {
				resultantSet.add(chargebackClusterPageObject.resultSetValues.get(i).getText());
			}

			Assert.assertEquals(resultantSet, up);
		}
		else {
			down = new TreeSet<String>(initialSet);
			chargebackClusterPageObject.sortDown.click();
			waitExecuter.sleep(1000);
			for(int i =5; i< size;i=i+7) {
				resultantSet.add(chargebackClusterPageObject.resultSetValues.get(i).getText());
			}

			Assert.assertEquals(resultantSet, down);
		}

	}

}


