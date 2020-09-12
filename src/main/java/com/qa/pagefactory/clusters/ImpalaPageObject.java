package com.qa.pagefactory.clusters;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ImpalaPageObject {

  @FindBy(css = "g.highcharts-label.highcharts-tooltip.highcharts-color-0 > text")
  public WebElement memoryTooltip;

  @FindBy(css = "svg > g.highcharts-label.highcharts-tooltip.highcharts-color-undefined > text")
  public WebElement queryTooltip;

  @FindBy(xpath = "(//div[contains(@class,'highcharts-container')])[1]")
  public WebElement memoryHighChartContainer;

//  @FindBy(xpath = "//label[contains(text(), 'Group By')]/following-sibling::span")
  @FindBy(xpath = "//label[contains(text(), 'Group By')]/following-sibling::span/span/span/span[contains(@class,'select2-selection__arrow')]")
  public WebElement groupByDropdownButton;

  @FindBy(xpath = "//li[contains(text(),'Queue')]")
  public WebElement groupByQueueList;

	@FindBy(xpath = "//li[contains(text(),'User')]")
	public WebElement groupByUserList;

  	@FindBy(xpath = "(//div[contains(@class,'highcharts-container')])[2]")
	public WebElement queryHighChartContainer;

	public By graphGContents = By.cssSelector("svg > g.highcharts-series-group");

	@FindBy(xpath = "//div[contains(@class,'dashboard-module')]//div/h4[text()='Memory Consumption']//following::div[contains(@class,'footer')][1]//label/span[1]")
	public List<WebElement> memoryGraphToggleCheckboxList;

	@FindBy(xpath = "//div[contains(@class,'dashboard-module')]//div/h4[text()='Memory Consumption']//following::div[contains(@class,'footer')][2]//label/span[1]")
	public List<WebElement> queryGraphToggleCheckboxList;

	@FindBy(xpath = "(//div[contains(@class,'dashboard-module')]//div[contains(@id,'highcharts')])[1]")
	public WebElement parentMemoryConsumptionGraph;

	public By checkNoDataOnGraph = By.xpath(
			"(.//*[local-name() = 'svg']/*[local-name() = 'g']/*[local-name() = 'text']/*[local-name() = 'tspan'])[1]");

	@FindBy(xpath = "(//div[contains(@class,'dashboard-module')]//div[contains(@id,'highcharts')])[2]")
	public WebElement parentQueryGraph;

	@FindBy(xpath = "(.//*[local-name() = 'svg']/*[local-name() = 'g']/*[local-name() = 'text']/*[local-name() = 'tspan'])[6]")
	public WebElement childQueriesGraph;

	@FindBy(xpath = "//li[contains(@class,'active')]/ul[contains(@class,'sub-menu')]//li/span[contains(text(),'Impala')]")
	public WebElement clusterImpalaTab;

	@FindBy(xpath = "//div[contains(@class,'component-section-header')]/h1[text()='Impala']")
	public WebElement getImpalaPageHeader;

	@FindBy(xpath = "(//div[contains(@class,'daterangepicker_input')]//input[contains(@name,'daterangepicker_start')])[last()]")
	public WebElement customRangeStartDate;

	@FindBy(xpath = "(//div[contains(@class,'daterangepicker_input')]//input[contains(@name,'daterangepicker_end')])[last()]")
	public WebElement customRangeEndDate;

	@FindBy(xpath = "(//div[contains(@class,'range_inputs')]/button[contains(@class,'applyBtn')])[2]")
	public WebElement applyBtnImpalaDatePicker;
	
	@FindBy(xpath = "(//div[contains(@class,'range_inputs')]/button[contains(@class,'applyBtn')])")
	public WebElement applyBtn;

	@FindBy(xpath = "//input[contains(@class,'select2-search__field')]")
	public WebElement filterInput;

	@FindBy(xpath = "//span[contains(@class,'select2-results')]/ul/li")
	public List<WebElement> filterElements;

	@FindBy(xpath = "//div[@id=\"queryGraph\"]//label")
	public List<WebElement> queriesFooterLabels;

	@FindBy(xpath = "//span[contains(@class,'select2-selection__choice__remove')]")
	public List<WebElement> filterRemoveElements;

	@FindBy(css = "svg > g.highcharts-axis-labels.highcharts-xaxis-labels")
	public List<WebElement> graphXAxisDateLabels;
	
	@FindBy(xpath = "(//div[contains(@class,'footer')])[1]//label")
	public List<WebElement> memoryFooterLabels;

	@FindBy(css = "svg > g.highcharts-axis-labels.highcharts-yaxis-labels")
	public List<WebElement> graphYAxisLabels;

	@FindBy(xpath= "/html/body/div[1]/div/div/div/div/div[3]/div[2]/div/h2")
	public  WebElement impalaQueriesHeader;

	@FindBy(xpath="//table[@id='runningImpalaQueriesTable']/thead/tr/th")
	public  List<WebElement> listImpalaQueriesTableHeaderColumn;

	@FindBy(xpath = "//table[@id='runningImpalaQueriesTable']/tbody/tr")
	public List<WebElement> impalaQueriesTableRecords;

	public ImpalaPageObject(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	// Get child elements with reference of parent element
	public List<WebElement> getChildElement(WebElement parentElement, By childElement) {
		return parentElement.findElements(childElement);
	}
}
