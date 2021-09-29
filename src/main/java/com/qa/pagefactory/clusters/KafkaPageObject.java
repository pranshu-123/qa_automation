package com.qa.pagefactory.clusters;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class KafkaPageObject {

    @FindBy(xpath = "(//ul[contains(@class,'sub-menu')]//span[text()='Kafka'])")
    public WebElement kafkaTab;

    @FindBy(xpath = "//div[@class='dashboard-container']//a[contains(text(),'Broker')]")
    public WebElement brokerTab;

    @FindBy(xpath = "//div[@class='dashboard-container']//a[contains(text(),'Topic')]")
    public WebElement topicTab;

    @FindBy(xpath = "(//span[contains(@class,'select2-selection__rendered')])")
    public WebElement kafkaClusterDropDown;

    @FindBy(xpath = "//*[@class='select2-results__options']//li")
    public List<WebElement> kafkaClusters;

    @FindBy(xpath = "//*[contains(@id,'column-')]/span")
    public List<WebElement> topicCol;

    @FindBy(xpath = "//*[@id='kafkaTopicList-body']")
    public List<WebElement> topicRows;

    @FindBy(xpath ="//*[@id='kafkaTopicList-body']/tr")
    public List<WebElement> getLatestMetricsList;

    @FindBy(css = "div#topContainer-kafkaTopicList>nav>p")
    public List<WebElement> paginationLatMetricsTable;

    @FindBy(css = "div#topContainer-kafkaTopicList>nav>p")
    public WebElement getNumberOfMetricsPagesOfReports;

    @FindBy(css = "div#topContainer-kafkaTopicList>nav>p>input")
    public WebElement enterLatestMetricsPageNavigation;

    @FindBy(xpath = "//*[@id=\"custom-tbl\"]/div[3]/div/table/tbody")
    public List<WebElement> getAverageMetricsList;

    @FindBy(xpath = "//div[contains(@class,'component-score-improvement-module')]//" +
            "div[contains(@class, 'col score-improvement-box') and //div[@class='col']]//span")
    public List<WebElement> kafkaClusterKPIs;

    @FindBy(xpath = "//div[contains(@class,'component-score-improvement-module')]//" +
            "div[contains(@class, 'col score-improvement-box') and //div[@class='col-8']]//h2")
    public List<WebElement> kafkaClusterKPIValues;

    @FindBy(xpath = "//div[contains(@class,'component-section-header')]/h1")
    public WebElement kafkaHeaderTab;

    @FindBy(xpath = "//div[ contains (@class, 'col score-improvement-box')]")
    public List<WebElement> kafkaMetrics;

    @FindBy(xpath = "//div[ contains (@class, 'row no-gutters')]/div[contains (@class,'col')]/span")
    public List<WebElement> kafkaMetricsHeader;

    @FindBy(xpath = "//*[name()='g' and @class='highcharts-series-group']")
    public List<WebElement> kafkaMetricsGraph;

    @FindBy(xpath = "//div[ contains (@class, 'row no-gutters')]/div[contains (@class,'col')]/h2")
    public List<WebElement> kafkaMetricsFooter;

    @FindBy(xpath = "//*[@id='app']//div[@class='dashboard-section']/h2")
    public WebElement metricsTitle;

    @FindBy(xpath = "//div[@class='col-md-2']/input")
    public WebElement topicSearchBox;

    @FindBy(xpath = "//div[@class='filterclass']/input")
    public WebElement brokerSearchBox;

    @FindBy(xpath = "//*[@id=\"custom-tbl\"]/div[3]/div/table/thead/tr/th")
    public List<WebElement> topicTableCol;

    @FindBy(xpath = "//tbody/tr")
    public List<WebElement> topicTableRows;

    @FindBy(xpath = "//*[@id='custom-tbl']//table/tbody/tr")
    public List<WebElement> topicTableRowData;

    @FindBy(xpath = "//*[contains(@id,'column-')]/span")
    public List<WebElement> brokerCol;

    @FindBy(xpath = "//tbody[@id='undefined-body']/tr")
    public List<WebElement> brokerRows;

    @FindBy(xpath = "//a[contains(@class,'sorting icon-sort')]")
    public List<WebElement> brokerColSortingIcon;

    @FindBy(xpath = "//div[@id='topContainer-undefined']//tr/th/span")
    public List<WebElement> brokerMetricsHeader;

    @FindBy(xpath = "//tbody[@id='kafkaTopicList-body']/tr")
    public List<WebElement> topicMetricRows;

    @FindBy(xpath = "//a[contains(@class,'sorting icon-sort')]")
    public List<WebElement> topicColSortingIcon;

    @FindBy(xpath = "//*[@class='component-dashboard']")
    public WebElement componentDashboard;

    @FindBy(xpath = "//*[@class='dashboard-container']/div/div/h2")
    public WebElement latestMetricsInfo;

    @FindBy(xpath = "//*[@id='undefined-body']//td[@class='active-td']")
    public List<WebElement> selectedBrokerRowColor;

    @FindBy(xpath = "//*[@id='kafkaTopicList-body']//td[@class='active-td']")
    public List<WebElement> selectedTopicRowColor;


    public KafkaPageObject(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }
}
