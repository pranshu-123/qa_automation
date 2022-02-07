package com.qa.scripts.databricks.cost;

import com.qa.pagefactory.clusters.ChargebackImpalaPageObject;
import com.qa.pagefactory.databricks.cost.ChargebackClusterPageObject;
import com.qa.scripts.DatePicker;
import com.qa.scripts.clusters.impala.ChargeBackImpala;
import com.qa.utils.WaitExecuter;
import com.qa.utils.actions.UserActions;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import java.util.Date;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class ChargeBackCluster {
	private static final Logger LOGGER = Logger.getLogger(ChargeBackImpala.class.getName());
	private final WaitExecuter waitExecuter;
	private final WebDriver driver;
	private final ChargebackImpalaPageObject chargebackImpalaPageObject;
	private final UserActions userActions;
	private final ChargebackClusterPageObject chargebackClusterPageObject;
	private final DatePicker datePicker;

	/**
	 * Constructer to initialize wait, driver and necessary objects
	 *
	 * @param driver - WebDriver instance
	 */
	public ChargeBackCluster(WebDriver driver) {
		waitExecuter = new WaitExecuter(driver);
		this.driver = driver;
		chargebackImpalaPageObject = new ChargebackImpalaPageObject(driver);
		userActions = new UserActions(driver);
		chargebackClusterPageObject = new ChargebackClusterPageObject(driver);
		datePicker = new DatePicker(driver);
	}

	public void navigateToCostTab(String tab) {
		waitExecuter.sleep(2000);
		waitExecuter.waitUntilPageFullyLoaded();
		if(tab.equalsIgnoreCase("trends")) {
			chargebackClusterPageObject.costTrendsTab.click();
		}
		else if(tab.equalsIgnoreCase("chargeback")) {
			waitExecuter.waitUntilElementClickable(chargebackClusterPageObject.costChargeBackTab);
			chargebackClusterPageObject.costChargeBackTab.click();
		}
		else {
			chargebackClusterPageObject.costBudgetTab.click();
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
	}

	public void validateGeneratedPieChartValues() {
		waitExecuter.sleep(1500);
		List<String> pieChartValues = chargebackClusterPageObject.pieChartValues.stream()
				.map(values -> values.getText()).distinct().skip(1)
				.collect(Collectors.toList());

		List<String> resultSet =chargebackClusterPageObject.resultSetValues.stream()
				.map(a-> a.getText())
				.collect(Collectors.toList());
		for(String s : pieChartValues) {
			Assert.assertTrue(resultSet.contains(s));
		}
	}

	public void validateResultSet(String[] str) {
		List<String> list =chargebackClusterPageObject.resultSetValues.stream()
				.map(a-> a.getText())
				.collect(Collectors.toList());
		for(String s : str) {
			Assert.assertTrue(list.contains(s));
		}
	}

	public void validateResultSetIsDisplayedWithValues(String groupBy) {
		String header = chargebackClusterPageObject.lblResultSetHeader.getText();
		Assert.assertTrue(header.equalsIgnoreCase(groupBy));
		chargebackClusterPageObject.dbu.isDisplayed();
		chargebackClusterPageObject.cost.isDisplayed();
		chargebackClusterPageObject.clusterCount.isDisplayed();
		chargebackClusterPageObject.resultSetValues.stream().iterator().next().isDisplayed();
	}

	public void selectOptimize() {
		chargebackClusterPageObject.optimize.click();
	}

	public void validateDate() {
		String date = datePicker.getDate(30);
		//String datefromCostPage = date.toString();
		String selectedDate = chargebackClusterPageObject.selectedDates.getText();
		Assert.assertTrue(selectedDate.contains(date));
	}

	public void selectDownloadOption(String type,String format) {
		if(type.equalsIgnoreCase("dbu")) {
			chargebackClusterPageObject.graphsThreeDots.get(0).click();
			driver.findElement(By.xpath(String.format(chargebackClusterPageObject.dbuDownloadFormat,format))).click();}
		else if(type.equalsIgnoreCase("cost")) {
			chargebackClusterPageObject.graphsThreeDots.get(1).click();
		}
		else {
			chargebackClusterPageObject.graphsThreeDots.get(2).click();	
		}
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
}


