package com.qa.pagefactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * @author Ojasvi Pandey All KPI related webelements of unravel ui is present in
 *         this class. Wherever you need to access these page object create an
 *         instance of this class and access the members with that object.
 */

public class KPIPageObject {

	@FindBy(xpath = "//span[contains(text(),'Nodes')]")
	public WebElement check_nodes_title;

	@FindBy(xpath = "//span[contains(text(),'Available Vcores')]")
	public WebElement check_vcores_title;

	@FindBy(xpath = "//span[text()='Available  Memory']")
	public WebElement check_memory_title;

	@FindBy(xpath = "//span[text()='Alerts']")
	public WebElement check_alert_title;

	@FindBy(xpath = "//span[contains(text(),'Running')]")
	public WebElement check_running_title;

	@FindBy(xpath = "//span[contains(text(),'Pending')]")
	public WebElement check_pending_title;

	@FindBy(xpath = "//div[contains(@class,'title-pad')]/div/span[contains(text(),'Success')]")
	public WebElement check_success_title;

	@FindBy(xpath = "//div[contains(@class,'title-pad')]/div/span[contains(text(),'Failed')]")
	public WebElement check_failed_title;

	@FindBy(xpath = "//div[contains(@class,'title-pad')]/div/span[contains(text(),'Killed')]")
	public WebElement check_killed_title;

	@FindBy(xpath = "//span[contains(text(),'Inefficient Events')]")
	public WebElement check_inefficientEvents_title;

	@FindBy(xpath = "//div//span[text()='Nodes'] //following::div[1]//h2")
	public WebElement get_node_value;

	@FindBy(xpath = "//div//span[text()='Available Vcores'] //following::div[1]//h2")
	public WebElement get_vcore_value;

	@FindBy(xpath = "//div//span[text()='Available  Memory'] //following::div[1]//h2")
	public WebElement get_memory_value;

	@FindBy(xpath = "//div//span[text()='Alerts'] //following::div[1]//h2")
	public WebElement get_alerts_value;

	@FindBy(xpath = "//span[contains(text(),'Running')]//following::div[1]//h2")
	public WebElement get_running_value;

	@FindBy(xpath = "//span[contains(text(),'Pending')]//following::div[1]//h2")
	public WebElement get_pending_value;

	@FindBy(xpath = "//div[contains(@class,'title-pad')]/div/span[contains(text(),'Success')]//following::div[1]//h2")
	public WebElement get_success_value;

	@FindBy(xpath = "//div[contains(@class,'title-pad')]/div/span[contains(text(),'Failed')]//following::div[1]//h2")
	public WebElement get_failed_value;

	@FindBy(xpath = "//div[contains(@class,'title-pad')]/div/span[contains(text(),'Killed')]//following::div[1]//h2")
	public WebElement get_killed_value;

	@FindBy(xpath = "//span[contains(text(),'Inefficient Events')]//following::div[1]//h2")
	public WebElement get_inefficientEvents_value;

	public KPIPageObject(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

}
