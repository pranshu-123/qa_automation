package com.qa.pagefactory.manage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

/**
 * @author Birender Kumar
 */
public class ManagePageObject {

    @FindBy(xpath="//*[@id=\"app\"]//div[1]/nav//div[2]/div/ul/li")
    public List<WebElement> allManageTabList;

    //@FindBy(css = "div#daemons-template h1")
    @FindBy(xpath="//span[normalize-space()='Daemons']")
    public WebElement daemonsHeader;

    @FindBy(xpath = "(//a[@class='menu'])[2]")
    public WebElement gear;

    @FindBy(xpath = "(//ul[@class='sub-menu']/li[contains(@class,'router-link-active')]/span[@class='selected'])[7]")
    public WebElement daemonsTab;

    @FindBy(xpath="//a[@href='#/manage/stats']")
    public WebElement statsTab;



    @FindBy(xpath="//span[normalize-space()='Stats']")
    public WebElement statsHeader;

    @FindBy(xpath = "//input[@placeholder='Search']")
    public WebElement searchField;

    @FindBy(xpath = "//table[@class='component-data-tables row-hover']/thead/tr/th")
    public List<WebElement> searchTableHeader;

    @FindBy(xpath = "//table[@class='component-data-tables row-hover']/tbody/tr")
    public List<WebElement> searchFileTableRows;

    @FindBy(xpath = "//table[@class='component-data-tables row-hover']/tbody")
    public WebElement rowData;

    @FindBy(xpath = "//table[@class='component-data-tables row-hover']/tbody/tr")
    public List<WebElement> fileTableRows;

    @FindBy(xpath = "(//div[@class='component-tabs-primary']//a)[1]")
    public WebElement dbStatsTab;

    @FindBy(xpath="//table[contains(@class,'component-data-tables')]/tbody/tr")
    public List<WebElement> dbStatsTable;

    @FindBy(xpath = "(//div[@class='component-tabs-primary']//a)[2]")
    public WebElement  elasticSearchTab;

    @FindBy(xpath = "//input[@placeholder='Search']")
    public WebElement  AuditSearch;
    @FindBy(xpath = "//a[normalize-space()='Elasticsearch']")
    public WebElement elasticSearchStatus;

    @FindBy(xpath = "(//div[@class='container-fluid']//label)[1]/parent::div/following-sibling::div/input")
    public WebElement elasticSearchStatusValue;

    @FindBy(xpath = "(//div[@class='container-fluid']//label)[2]")
    public WebElement elasticSearchTotalSize;

    @FindBy(xpath = "(//div[@class='container-fluid']//label)[2]/parent::div/following-sibling::div/input")
    public WebElement elasticSearchTotalSizeValue;

    @FindBy(xpath = "(//div[@class='container-fluid']//label)[3]")
    public WebElement elasticSearchNumOfIndexes;

    @FindBy(xpath = "(//div[@class='container-fluid']//label)[3]/parent::div/following-sibling::div/input")
    public WebElement elasticSearchNumOfIndexesValue;

    @FindBy(css = "a.active")
    public WebElement sensorHeartbeatTab;

    @FindBy(xpath = "//table[contains(@class,'component-data-tables')]/tbody/tr")
    public List<WebElement> sensorHeartbeatTblRows;

    @FindBy(xpath="//span[text()='Run Diagnostics']")
    public WebElement runDiagnosticsTab;

    @FindBy(xpath="(//span[contains(text(),'Run Diagnostics')])")
    public WebElement runDiagnosticsHeader;

    @FindBy(xpath="(//div/h1[contains(text(),'Run Diagnostics')])")
    public WebElement runDiagnosticsHeader1;

    @FindBy(xpath = "//ul[@class='diagnostic-ul']/li[contains(text(),'Load Latest Diagnostics')]")
    public WebElement  loadLatestDiagnosticsBtn;

    @FindBy(xpath = "//a[@class='logo']")
    public WebElement resetButton;

    @FindBy(css = ".diagnostic-cnt")
    public WebElement latestDiagnosticsContent;

    @FindBy(xpath = "//div[@class='basicWrapper']/h2")
    public WebElement latestDiagnosticsContentHeader;

    @FindBy(xpath = "//div[@class='basicWrapper']/h2/span")
    public WebElement latestDiagnosticsContentHeaderTimeStamp;

    @FindBy(xpath="//ul[@class='diagnostic-ul']/li[2]")
    public WebElement  downloadSupportBundleBtn;

    @FindBy(xpath="//span[text()='Monitoring']")
    public WebElement monitoringTab;


    @FindBy(xpath="//span[normalize-space()='Monitoring']")
    public WebElement monitoringHeader;

    @FindBy(xpath="//span[text()='Audit']")
    public WebElement AuditTab;

    @FindBy(xpath="//span[text()='Unravel Billing']")
    public WebElement billingTab;

    @FindBy(xpath="//span[normalize-space()='Audit']")
    public WebElement AuditHeader;

    @FindBy(xpath="(//a[contains(text(),'Partition Info')])")
    public WebElement partitionInfoTab;

    @FindBy(xpath="//table[contains(@class,'component-data-tables')]/thead/tr/th")
    public List<WebElement> listPartitionInfoTableHeaders;

    @FindBy(xpath="(//div[@class='pull-right'])[1]")
    public  WebElement partitionInfoTSAndDataAge;

    @FindBy(xpath="(//a[contains(text(),'DB Status')])")
    public WebElement monitoringDBStatusTab ;

    @FindBy(xpath="//table[contains(@class,'component-data-tables')]/thead/tr/th")
    public List<WebElement> monitoringDBStatusTableHeaders;

    @FindBy(xpath = "//div[contains(@class,'active')]/div[@class='pull-right']")
    public WebElement dbStatusTimeStampAndDataAge;

    @FindBy(xpath="//div[@class='col-auto']/ul/li/a[contains(@class,'active')]/parent::li/following-sibling::li/a[contains(text(),'DB Performance')]")
    public WebElement monitoringDBPerformanceTab;

    @FindBy(xpath="//table[contains(@class,'component-data-tables')]/thead/tr/th")
    public List<WebElement> monitoringDBPerformanceTableHeaders;

    @FindBy(xpath = "//div[contains(@class,'active')]/div[@class='pull-right']")
    public WebElement timeStampAndDataAge;

    @FindBy(xpath="//div[@class='col-auto']/ul/li/a[contains(@class,'active')]/parent::li/following-sibling::li/a[contains(text(),'Zookeeper')]")
    public WebElement monitoringZookeeperTab;

    @FindBy(xpath="//table[contains(@class,'component-data-tables')]/thead/tr/th")
    public List<WebElement> monitoringZookeeperTableHeaders;

    @FindBy(xpath="//div[@class='col-auto']/ul/li/a[contains(@class,'active')]/parent::li/following-sibling::li/a[contains(text(),'Kafka')]")
    public WebElement monitoringKafkaTab;

    @FindBy(xpath="(//div[@class='pull-right']//following-sibling::div/div/div/h2)[1]")
    public WebElement kafkaBasicData;

    @FindBy(xpath="(//div[@class='pull-right']//following-sibling::div/div/div/h2)[2]")
    public WebElement kafkaConsumerGroups;

    @FindBy(xpath="(//div[@class='pull-right']//following-sibling::div/div/div/h2)[3]")
    public WebElement kafkaTopicList;

    @FindBy(xpath="(//div[@class='pull-right']//following-sibling::div/div/div/h2)[4]")
    public WebElement  kafkaTopicWithoutConsumer;

    @FindBy(xpath="//div[@class='col-auto']/ul/li/a[contains(@class,'active')]/parent::li/following-sibling::li/a[contains(text(),'Elastic')]")
    public WebElement monitoringElasticTab;

    @FindBy(xpath="(//div[@class='pull-right']//following-sibling::div/h2)[1]")
    public WebElement  monitoringElasticNodeHeader;

    @FindBy(xpath="(//div[@class='pull-right']//following-sibling::div/h2)[2]")
    public WebElement  monitoringElasticClusterHealthHeader;

    @FindBy(xpath ="//td[@class='border-success']")
    public List<WebElement> listDaemons;

    @FindBy(xpath ="//span[text()='API Tokens']")
    public WebElement apiTokenMenuManager;

    @FindBy(xpath = "//a[contains(text(),'New API Token')]")
    public WebElement newApiTokenButton;

    @FindBy(xpath = "//section[contains(@class,'icon-success')]/div")
    public WebElement successTextMessage;

    public String sortOption = "//span[contains(text(),'%s')]/..//a[contains(@class,'icon-sort')]";

    @FindBy(xpath = "//a[contains(@class,'icon-copy')]")
    public List<WebElement> copyButton;

    @FindBy(xpath = "//td[contains(@class,'key token-txt')]")
    public List<WebElement> apiKeyList;

    @FindBy(xpath = "//label[contains(text(),'Client Id')]/../input")
    public WebElement clientIdText;

    @FindBy(xpath = "//td[contains(@class,'key')][1]/span")
    public List<WebElement> tokenNameList;

    public String deleteOption = "//span[contains(text(),'%s')]/../..//span[contains(@class,'icon-delete')]";

    @FindBy(xpath = "//a[contains(text(),'Create')]")
    public WebElement createButton;

    @FindBy(xpath = "//a/span[contains(text(),'API Tokens')]")
    public WebElement apiTokenLink;

    @FindBy(xpath = "//div[contains(@class,'text-fatal')]")
    public WebElement errorTextMessage;

    @FindBy(xpath ="//td[@class='key text-left']")
    public List<WebElement> listAudits;

    @FindBy(xpath ="//td[@class='key text-left']/span")
    public WebElement SearchSample;

    @FindBy(xpath ="//label[@class='checkbox' and @content!='NA']")
    public List<WebElement> listFilterOptions;
    @FindBy(xpath= "//a[@class='icon-sort' or @class='icon-sort sort-top']")
    public List<WebElement> iconSort;

    @FindBy(xpath= "//span[contains(@class,'icon-download pointer')]")
    public WebElement downloadCSVButton;

    public String sortType ="//a[@class='icon-sort' or @class='icon-sort sort-top']//parent::th";

    @FindBy(xpath= "//a[@class=\"filter-select\"]")
    public List<WebElement> iconFilter;

    @FindBy(xpath="(//tbody/tr/td[14])[1]")
    public WebElement viewLink;

    @FindBy(xpath="//a[@class='btn-sm']")
    public List<WebElement> viewLogCategory;

    @FindBy(xpath="//table[@id='inner-tbl']//tbody/tr")
    public List<WebElement> viewTblRows;

    @FindBy(xpath = "//input[@class='search']")
    public WebElement viewSearch;

    public ManagePageObject(WebDriver driver) {
        PageFactory.initElements(driver,this);
    }
}
