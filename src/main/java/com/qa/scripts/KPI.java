package com.qa.scripts;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.pagefactory.DatePickerPageObject;
import com.qa.pagefactory.KPIPageObject;
import com.qa.utils.JavaScriptExecuter;
import com.qa.utils.WaitExecuter;

/**
 * @author Ojasvi Pandey This class contains all KPI on UI related action
 *         methods
 */

public class KPI {
	private WebDriver driver;
	public DatePickerPageObject datePickerPageObject;
	public WaitExecuter waitExecuter;
	public KPIPageObject kpiPageObject;

	/**
	 * @param driver
	 *            - WebDriver instance Constructor method initialize driver,
	 *            datepicker object
	 */
	public KPI(WebDriver driver) {
		this.driver = driver;
		datePickerPageObject = new DatePickerPageObject(driver);
		waitExecuter = new WaitExecuter(driver);
		kpiPageObject = new KPIPageObject(driver);
	}

	/* Will return true if Node Title is displayed */
	public Boolean isNodeDisplayed() {
		Boolean calendarDatePresent = kpiPageObject.check_nodes_title.isDisplayed();
		if (calendarDatePresent)
			return true;
		else
			return false;
	}
	
	/* Get Node Value */
	public String getNodeValue() {
		String nodeValue = kpiPageObject.get_node_value.getText();
		return nodeValue;
	}
	
	/* Will return true if Vcore Title is displayed */ 
	public Boolean isVcoreDisplayed() {
		Boolean vcorePresent = kpiPageObject.check_vcores_title.isDisplayed();
		if (vcorePresent)
			return true;
		else
			return false;
	}
	
	/* Get Vcore Value */
	public String getVcoreValue() {
		String vcoreValue = kpiPageObject.get_vcore_value.getText();
		return vcoreValue;
	}
	
	/* Will return true if Memory Title is displayed */ 
	public Boolean isMemoryDisplayed() {
		Boolean memoryPresent = kpiPageObject.check_memory_title.isDisplayed();
		if (memoryPresent)
			return true;
		else
			return false;
	}
	
	/* Get Memory Value */
	public String getMemoryValue() {
		String memoryValue = kpiPageObject.get_memory_value.getText();
		return memoryValue;
	}
	
	/* Will return true if alert Title is displayed */ 
	public Boolean isAlertDisplayed() {
		Boolean alertPresent = kpiPageObject.check_alert_title.isDisplayed();
		if (alertPresent)
			return true;
		else
			return false;
	}
	
	/* Get Alert Value */
	public String getAlertValue() {
		String alertValue = kpiPageObject.get_alerts_value.getText();
		return alertValue;
	}
	
	
	/* Will return true if Running Title is displayed */ 
	public Boolean isRunningDisplayed() {
		Boolean runningPresent = kpiPageObject.check_running_title.isDisplayed();
		if (runningPresent)
			return true;
		else
			return false;
	}
	
	/* Get Running Value */
	public String getRunningValue() {
		String runningValue = kpiPageObject.get_running_value.getText();
		return runningValue;
	}
	
	/* Will return true if Pending Title is displayed */ 
	public Boolean isPendingDisplayed() {
		Boolean pendingPresent = kpiPageObject.check_pending_title.isDisplayed();
		if (pendingPresent)
			return true;
		else
			return false;
	}
	
	/* Get Pending Value */
	public String getPendingValue() {
		String pendingValue = kpiPageObject.get_pending_value.getText();
		return pendingValue;
	}
	
	/* Will return true if Success Title is displayed */ 
	public Boolean isSuccessDisplayed() {
		Boolean pendingSuccess = kpiPageObject.check_success_title.isDisplayed();
		if (pendingSuccess)
			return true;
		else
			return false;
	}
	
	/* Get Success Value */
	public String getSuccessValue() {
		String successValue = kpiPageObject.get_success_value.getText();
		return successValue;
	}
	
	/* Will return true if Failed Title is displayed */ 
	public Boolean isFailedDisplayed() {
		Boolean failedPresent = kpiPageObject.check_failed_title.isDisplayed();
		if (failedPresent)
			return true;
		else
			return false;
	}
	
	/* Get Failed Value */
	public String getFailedValue() {
		String failedValue = kpiPageObject.get_failed_value.getText();
		return failedValue;
	}
	
	/* Will return true if Killed Title is displayed */ 
	public Boolean isKilledDisplayed() {
		Boolean killedPresent = kpiPageObject.check_killed_title.isDisplayed();
		if (killedPresent)
			return true;
		else
			return false;
	}
	
	/* Get Killed Value */
	public String getKilledValue() {
		String killedValue = kpiPageObject.get_killed_value.getText();
		return killedValue;
	}
	
	/* Will return true if Inefficient Events Title is displayed */ 
	public Boolean isInefficientEventsDisplayed() {
		Boolean inefficientEventsPresent = kpiPageObject.check_inefficientEvents_title.isDisplayed();
		if (inefficientEventsPresent)
			return true;
		else
			return false;
	}
	
	/* Get Inefficient Events Value */
	public String getInefficientEventsValue() {
		String inefficientEventsValue = kpiPageObject.get_inefficientEvents_value.getText();
		return inefficientEventsValue;
	}

}
