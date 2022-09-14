package com.qa.pagefactory.emr;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class EmrDateTimePicker {

	@FindBy(css = "div.component-date-picker")
	public WebElement dateRange;

	@FindBy(xpath = "//button[contains(text(),'Today')]")
	public WebElement today;

	@FindBy(xpath = "//button[contains(text(),'14D')]")
	public WebElement last14Days;

	@FindBy(xpath = "//button[contains(text(),'7D')]")
	public WebElement last7Days;

	@FindBy(xpath = "//button[contains(text(),'1M')]")
	public WebElement last1M;

	@FindBy(xpath = "//button[contains(text(),'3M')]")
	public WebElement last3M;

	@FindBy(xpath = "//button[contains(text(),'6M')]")
	public WebElement last6M;

	@FindBy(xpath = "//button[contains(text(),'1Y')]")
	public WebElement last1Y;

	@FindBy(xpath = "//i[@class='calendar-icon']")
	public WebElement customRange;

	@FindBy(xpath = "//select[@class='monthselect col']")
	public List<WebElement> customRangeDateMonth;

	@FindBy(xpath = "//input[@class='yearselect col']")
	public List<WebElement> customRangeDateYear;

	@FindBy(xpath = "(//button[contains( @class, 'applyBtn')])[last()]")
	public WebElement applyBtn;
	
	@FindBy(xpath = "//div[@class='form-control reportrange-text']/span")
	public WebElement previousDate;


	/**
	 * @param driver The driver that will be used to look up the elements
	 */
	public EmrDateTimePicker(WebDriver driver) {
		PageFactory.initElements(driver, this);

	}
}
