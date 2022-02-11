package com.qa.pagefactory.databricks.cost;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class BudgetPageObject {

	@FindBy(id = "btn-new-budget")
	public WebElement newBudget;

	@FindBy(xpath = "//h2[contains(text(),'Active')]/following-sibling::div//tr/td/div")
	public List<WebElement> activeBudgetTable;
	
	@FindBy(xpath = "//h2[contains(text(),'Active')]/following-sibling::div//tr/th")
	public List<WebElement> activeBudgetTableHeader;

	@FindBy(xpath = "//h2[contains(text(),'Upcoming')]/following-sibling::div//tr/td")
	public WebElement upcomingBudgetTable;

	@FindBy(xpath = "//h2[contains(text(),'Upcoming')]/following-sibling::div//tr/td")
	public WebElement expiredBudgetTable;

	@FindBy(xpath = "//button[contains(text(),'Yes')]")
	public WebElement yes;
	
	@FindBy(xpath = "//input[@name='name']")
	public WebElement addBudgetName;

	@FindBy(xpath = "//textarea")
	public WebElement addBudgetDescription;
	
	@FindBy(xpath = "//a[contains(text(),'Save')]")
	public WebElement addBudgetSave;
	
	@FindBy(xpath = "//a[contains(text(),'Close')]")
	public WebElement addBudgetClose;
	
	@FindBy(xpath = "//div[@class='vdp-datepicker']")
	public List<WebElement> dateWidget;
	
	@FindBy(xpath = "(//div[@class='vdp-datepicker__calendar'])[1]/span[contains(text(),'December')]")
	public WebElement futureDate;
	
	@FindBy(xpath = "//input[@type='number']")
	public WebElement addBudgetDBU;
	
	public String actionButtons = "//div[contains(text(),'Budget Test')]/../..//a/span[contains(text(),'%s')]"; //Chargeback,Trends,Optimize

	public String delete = "//div[contains(text(),'%s')]/../..//td//button[@title='Delete']";
	
	public String edit = "//div[contains(text(),'%s')]/../..//td//button[@title='Edit']";
	/**
	 * @param driver The driver that will be used to look up the elements
	 */
	public BudgetPageObject(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

}
