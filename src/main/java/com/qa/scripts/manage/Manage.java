package com.qa.scripts.manage;

import com.qa.base.BaseClass;
import com.qa.constants.DirectoryConstants;
import com.qa.enums.UserAction;
import com.qa.pagefactory.SubTopPanelModulePageObject;
import com.qa.pagefactory.manage.ManagePageObject;
import com.qa.scripts.databricks.jobs.DbAllApps;
import com.qa.utils.LoggingUtils;
import com.qa.utils.MouseActions;
import com.qa.utils.WaitExecuter;
import com.qa.utils.actions.UserActions;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.*;
import org.testng.Assert;

import java.io.File;
import java.util.*;
import java.util.logging.Logger;

/**
 * @author Birender Kumar
 */
public class Manage {
    private static final java.util.logging.Logger logger = Logger.getLogger(Manage.class.getName());
    private final ExtentTest test;
    private final UserActions userAction;
    private final LoggingUtils loggingUtils = new LoggingUtils(Manage.class);
    private final WebDriver driver;
    private final WaitExecuter waitExecuter;
    private final ManagePageObject managePageObject;
    private final SubTopPanelModulePageObject subTopPanelModulePageObject;

    public Manage(WebDriver driver, ExtentTest extentTest) {
        this.driver = driver;
        this.test = extentTest;
        waitExecuter = new WaitExecuter(driver);
        subTopPanelModulePageObject = new SubTopPanelModulePageObject(driver);
        managePageObject = new ManagePageObject(driver);
        userAction = new UserActions(driver);
    }

    public String validateDaemonHeader() {
        waitExecuter.sleep(2000);
        String headers = managePageObject.daemonsHeader.getText();
        logger.info("Daemons Header found:" + headers);
        return headers;
    }

    public void navigateManageTab(String ManageTab) {
        try {
            waitExecuter.waitUntilElementPresent(subTopPanelModulePageObject.gear);
            waitExecuter.waitUntilPageFullyLoaded();
            waitExecuter.waitUntilElementClickable(subTopPanelModulePageObject.gear);
            waitExecuter.sleep(3000);
            MouseActions.clickOnElement(driver, subTopPanelModulePageObject.gear);
            test.log(LogStatus.INFO, "Verified Manage Tab is clicked.");
            if (ManageTab.equalsIgnoreCase("API Tokens")) {
                userAction.performActionWithPolling(managePageObject.apiTokenMenuManager, UserAction.CLICK);
            } else if (ManageTab.equalsIgnoreCase("Audit")) {
                userAction.performActionWithPolling(managePageObject.AuditTab, UserAction.CLICK);
            } else if (ManageTab.equalsIgnoreCase("Unravel Billing")) {
                userAction.performActionWithPolling(managePageObject.billingTab, UserAction.CLICK);
                waitExecuter.waitUntilElementPresent(managePageObject.billingTab);
            }
        } catch (InvalidElementStateException e) {
            loggingUtils.error("Could not click on Manage tab" + e, test);
        } catch (WebDriverException e) {
            e.printStackTrace();
            loggingUtils.error("Unknown exception occured while click Manage tab" + e, test);
        }
    }

    /**
     * Method to validate the search option for different file types
     */
    public void verifySearchOption(int tablesRows, int tableCells) {
        List<WebElement> tableHeaderList = managePageObject.searchTableHeader;
        List<WebElement> tableRows = managePageObject.searchFileTableRows;
        String searchString = "";
        List<WebElement> rows = managePageObject.rowData.findElements(By.xpath("/tr[" + tablesRows + "]/td[" + tableCells + "]"));
        for (WebElement row : rows) {
            Assert.assertTrue(row.isDisplayed(), "No data under column: " + tableHeaderList.get(1).getText() + " for searchFile");
            searchString = row.getText();
            loggingUtils.info("The search string is " + searchString, test);
            managePageObject.searchField.sendKeys(searchString);
            waitExecuter.waitUntilPageFullyLoaded();
            List<WebElement> cols = row.findElements(By.xpath("/tr[" + row + "]/td[" + tableCells + "]"));
            for (WebElement col : cols) {
                Assert.assertTrue(col.isDisplayed(), "No data under column: File " + " for ");
                loggingUtils.info("Search String is " + searchString + " Search result is " + col.getText(), test);
                Assert.assertTrue(col.getText().contains(searchString), "The search result for " + " file type do not contain the search string\n Expected '" + searchString + "' to be present in '" + col.getText() + "' search result");
            }
        }
    }

    public void clickAuditTab() {
        waitExecuter.sleep(1000);
        MouseActions.clickOnElement(driver, managePageObject.gear);
        waitExecuter.waitUntilElementClickable(managePageObject.AuditTab);
        MouseActions.clickOnElement(driver, managePageObject.AuditTab);
        waitExecuter.waitUntilPageFullyLoaded();
    }

    /**
     * Click on download CSV button and return the File object of
     * downloaded file
     *
     * @return - Downloaded File Object
     */
    public File downloadAuditTable() {
        userAction.performActionWithPolling(managePageObject.downloadCSVButton, UserAction.CLICK);
        waitExecuter.sleep(5000);
        String downloadDirPath = DirectoryConstants.getDownloadsDir();
        File downloadedCSVFile = new File(downloadDirPath + File.separator + "data.csv");
        return downloadedCSVFile;
    }

    public void clickOnIconAuditSort() {
        int countOfIconSort = managePageObject.iconSort.size();
        logger.info("Number of total icon sort :" + countOfIconSort);
        if (countOfIconSort > 0) {
            for (int i = 0; i < countOfIconSort - 1; i++) {
                waitExecuter.sleep(2000);
                managePageObject.iconSort.get(i).click();
                waitExecuter.sleep(3000);
                Assert.assertTrue(validateAuditsCountInTbl());

            }
        }
    }

    public boolean validateAuditsCountInTbl() {
        int AuditsCount = managePageObject.listAudits.size();
        logger.info("Count of Audits :" + AuditsCount);
        return AuditsCount > 0;
    }

    public void validateAuditSorting(String sortOn) {
        Set<String> initialSet = new HashSet<String>();
        Set<String> resultantSet = new HashSet<String>();
        TreeSet<String> sort = null;
        int size = managePageObject.listAudits.size();
        int startCounter = 0;
        if (sortOn.equalsIgnoreCase("User")) {
            startCounter = 0;
        } else if (sortOn.equalsIgnoreCase("Client")) {
            startCounter = 7;
        } else if (sortOn.equalsIgnoreCase("Action")) {
            startCounter = 1;
        } else if (sortOn.equalsIgnoreCase("Object")) {
            startCounter = 2;
        } else {
            startCounter = 8;
        }
        for (int i = startCounter; i < size; i = i + 10) {
            initialSet.add(managePageObject.listAudits.get(i).getText());
        }

        sort = new TreeSet<String>(initialSet);
        driver.findElement(By.xpath(String.format(managePageObject.sortType, sortOn))).click();
        waitExecuter.sleep(1000);
        for (int i = startCounter; i < size; i = i + 10) {
            resultantSet.add(managePageObject.listAudits.get(i).getText());
        }
        Assert.assertNotEquals(resultantSet, sort, "Data not sorted");
    }

    public boolean validateAuditHeader() {
        waitExecuter.sleep(1000);
        MouseActions.clickOnElement(driver, managePageObject.gear);
        waitExecuter.sleep(1000);
        if (managePageObject.AuditHeader.isDisplayed()) {
            if (managePageObject.AuditHeader.getText().equals("Audit")) {
                logger.info("Audit Headers found: " + managePageObject.AuditHeader.getText());
                return true;
            }
        }
        return false;
    }

    public void selectCreateNewApiToken() {
        managePageObject.newApiTokenButton.click();
        loggingUtils.info("Create New Api Token option selected", test);
    }

    public String createEmptyToken() {
        managePageObject.createButton.click();
        loggingUtils.info("No New token created", test);
        return managePageObject.errorTextMessage.getText();
    }

    public String createNewToken(String token) {
        managePageObject.clientIdText.sendKeys(token);
        managePageObject.createButton.click();
        loggingUtils.info("New token created", test);
        return managePageObject.tokenNameList.get(0).getText();
    }

    public void deleteToken(String token) {
        driver.findElement(By.xpath(String.format(managePageObject.deleteOption, token))).click();
        loggingUtils.info("Api Token deleted", test);
    }

    public TreeSet<String> storeTokenList(String sortingType) {
        Set<String> initialSet = new HashSet<String>();
        TreeSet<String> up = null;
        if (sortingType.equalsIgnoreCase("Client Id")) {
            for (int i = 0; i < managePageObject.tokenNameList.size(); i++) {
                initialSet.add(managePageObject.tokenNameList.get(i).getText());
                up = new TreeSet<String>(initialSet);
            }
        } else {
            for (int i = 0; i < managePageObject.apiKeyList.size(); i++) {
                initialSet.add(managePageObject.apiKeyList.get(i).getText());
                up = new TreeSet<String>(initialSet);
            }
        }

        return up;
    }

    public void validateSorting(String sortingType) {
        TreeSet<String> resultant = new TreeSet<String>();
        TreeSet<String> initial = new TreeSet<String>();

        if (sortingType.equalsIgnoreCase("Client Id")) {
            initial = storeTokenList(sortingType);
            driver.findElement(By.xpath(String.format(managePageObject.sortOption, sortingType))).click();
            waitExecuter.sleep(1000);
            resultant = storeTokenList(sortingType);
        } else {
            initial = storeTokenList(sortingType);
            driver.findElement(By.xpath(String.format(managePageObject.sortOption, sortingType))).click();
            waitExecuter.sleep(1000);
            resultant = storeTokenList(sortingType);
        }
        Assert.assertEquals(resultant, initial);
    }

    public String retrieveSuccessMessage() {
        return managePageObject.successTextMessage.getText();
    }

    public String validateCopyTokenFunctionality() {
        waitExecuter.sleep(1500);
        managePageObject.copyButton.get(0).click();
        loggingUtils.info("Token copied", test);
        return managePageObject.successTextMessage.getText();
    }


    public boolean validateDaemonsCountInTbl() {
        int dameonsCount = managePageObject.listDaemons.size();
        logger.info("Count of Daemons :" + dameonsCount);
        return dameonsCount > 0;
    }

    public Boolean validateAllTabsPresent() {
        logger.info("Number of tabs on manage: " + managePageObject.allManageTabList.size());
        List<String> allTabsOnManagePage = new ArrayList<String>();
        for (int i = 0; i < managePageObject.allManageTabList.size(); i++) {
            allTabsOnManagePage.add(managePageObject.allManageTabList.get(i).getText());
        }
        System.out.println("Actual tabs: " + allTabsOnManagePage);
        String[] expectedTabsOnManagePage = {"Daemons", "Stats", "Run Diagnostics", "Monitoring"};
        Boolean boolAllTabs = true;
        for (String strTab : expectedTabsOnManagePage) {
            if (!allTabsOnManagePage.contains(strTab)) {
                boolAllTabs = false;
            }
        }
        return boolAllTabs;
    }

    public void clickOnIconSort() {
        int countOfIconSort = managePageObject.iconSort.size();
        logger.info("Number of total icon sort :" + countOfIconSort);
        if (countOfIconSort > 0) {
            for (int i = 0; i < countOfIconSort - 1; i++) {
                waitExecuter.sleep(2000);
                managePageObject.iconSort.get(i).click();
                waitExecuter.sleep(3000);
                Assert.assertTrue(validateDaemonsCountInTbl());

            }
        }
    }

    public void clickOnView() {
        waitExecuter.sleep(1000);
        managePageObject.viewLink.click();
        waitExecuter.sleep(3000);
    }

    public void verifyViewPage() {
        waitExecuter.sleep(1000);
        int viewLogCategoryCount = managePageObject.viewLogCategory.size();
        if (viewLogCategoryCount > 0) {
            for (int i = 0; i < viewLogCategoryCount - 1; i++) {
                waitExecuter.sleep(1000);
                String logLevel = managePageObject.viewLogCategory.get(i).getText();
                managePageObject.viewLogCategory.get(i).click();
                waitExecuter.sleep(3000);
                verifyViewInnerTbl(logLevel);
                waitExecuter.sleep(1000);
            }
        } else {
            logger.info("No View link displayed.");
            BaseClass.test.log(LogStatus.INFO, "No View link displayed.");
        }
    }

    public boolean verifyViewInnerTbl(String logLevel) {
        waitExecuter.sleep(1000);
        int viewTblRowsCount = managePageObject.viewTblRows.size();
        if (viewTblRowsCount > 0) {
            return true;
        } else {
            logger.info("View Inner Table is not displayed for log level: " + logLevel);
            BaseClass.test.log(LogStatus.INFO, "View Inner Table is not displayed for log level: " + logLevel);
        }
        return false;
    }

    public void clickStatsTab() {
        waitExecuter.sleep(1000);
        managePageObject.statsTab.click();
        waitExecuter.sleep(3000);
    }

    public boolean validateStatsHeader() {
        waitExecuter.sleep(1000);
        MouseActions.clickOnElement(driver, managePageObject.gear);
        waitExecuter.sleep(1000);
        logger.info("Stats Headers found: " + managePageObject.statsHeader.getText());
        return managePageObject.statsHeader.getText().equals("Stats");
    }

    public boolean validateStatsTablePresent() {
        waitExecuter.sleep(2000);
        int dbStatsTblSize = managePageObject.dbStatsTable.size();
        return dbStatsTblSize > 0;
    }

    public void clickElasticsearchTab() {
        waitExecuter.sleep(1000);
        managePageObject.elasticSearchTab.click();
        waitExecuter.sleep(3000);
    }

    public boolean verifyElasticSearchDetails() {
        waitExecuter.sleep(2000);

        if (managePageObject.elasticSearchStatus.isDisplayed()) {
            waitExecuter.sleep(1000);
            logger.info("Status Value: " + managePageObject.elasticSearchStatusValue.getText());

            if (managePageObject.elasticSearchTotalSize.isDisplayed()) {
                waitExecuter.sleep(1000);
                logger.info("TotalSize Value: " + managePageObject.elasticSearchTotalSizeValue.getText());
                if (managePageObject.elasticSearchNumOfIndexes.isDisplayed()) {
                    waitExecuter.sleep(1000);
                    logger.info("NumOfIndexes Value: " + managePageObject.elasticSearchNumOfIndexesValue.getText());
                    return true;
                }
            }
        }
        return false;
    }

    public void clickSensorHeartbeatTab() {
        waitExecuter.sleep(1000);
        managePageObject.sensorHeartbeatTab.click();
        waitExecuter.sleep(3000);
    }

    public boolean verifySensorHeartbeatDetails() {
        waitExecuter.sleep(2000);
        int sensorHeartbeatTblRowCount = managePageObject.sensorHeartbeatTblRows.size();
        logger.info("SensorHeartbeat table row count: " + sensorHeartbeatTblRowCount);

        return sensorHeartbeatTblRowCount > 0;
    }

    public void clickRunDiagnostics() {
        waitExecuter.sleep(1000);
        userAction.performActionWithPolling(managePageObject.runDiagnosticsTab, UserAction.CLICK);
        waitExecuter.sleep(3000);
    }

    public boolean validateRunDiagnosticsHeader() {
        waitExecuter.waitUntilElementPresent(managePageObject.runDiagnosticsHeader);
        logger.info("RunDiagnostics Headers found: " + managePageObject.runDiagnosticsHeader.getText());
        return managePageObject.runDiagnosticsHeader.getText().equals("Run Diagnostics");
    }

    public void clickOnLoadLatestDiagnostics() {
        waitExecuter.waitUntilElementClickable(managePageObject.loadLatestDiagnosticsBtn);
        try {
            userAction.performActionWithPolling(managePageObject.loadLatestDiagnosticsBtn, UserAction.CLICK);
        } catch (TimeoutException te) {
            userAction.performActionWithPolling(managePageObject.loadLatestDiagnosticsBtn, UserAction.CLICK);
        }
        waitExecuter.waitUntilPageFullyLoaded();
        waitExecuter.waitUntilElementClickable(managePageObject.loadLatestDiagnosticsBtn);

    }

    public boolean verifyLoadLatestDiagnosticsContent() {
        waitExecuter.sleep(1000);
        String latestDiagnoCtn = managePageObject.latestDiagnosticsContent.getText();
        return latestDiagnoCtn.length() > 0;
    }

    public boolean verifyLoadLatestDiagnosticsHeaderAndTimeStamp() {
        waitExecuter.sleep(12000);
        String runDiagnoHeader = managePageObject.latestDiagnosticsContentHeader.getText();
        runDiagnoHeader = runDiagnoHeader.substring(0, 15);
        logger.info("Latest Diagnostics Content Header found: " + runDiagnoHeader);

        if (runDiagnoHeader.equals("Diagnostics Log")) {
            waitExecuter.waitUntilElementPresent(managePageObject.latestDiagnosticsContentHeaderTimeStamp);
            String runDiagnoHeaderTS = managePageObject.latestDiagnosticsContentHeaderTimeStamp.getText();
            waitExecuter.sleep(2000);
            logger.info("Latest Diagnostics Content Header TimeStamp found: " + runDiagnoHeaderTS);
            return true;
        }
        return false;
    }

    public void clickOnDownloadSupportBundle() {
        UserActions actions = new UserActions(driver);
        waitExecuter.waitUntilElementClickable(managePageObject.downloadSupportBundleBtn);
        actions.performActionWithPolling(managePageObject.downloadSupportBundleBtn, UserAction.CLICK);
        waitExecuter.waitUntilPageFullyLoaded();
        waitExecuter.waitUntilElementClickable(managePageObject.downloadSupportBundleBtn);
    }

    public void clickMonitoringTab() {
        waitExecuter.sleep(1000);
        waitExecuter.waitUntilElementClickable(managePageObject.monitoringTab);
        MouseActions.clickOnElement(driver, managePageObject.monitoringTab);
        waitExecuter.waitUntilPageFullyLoaded();
    }

    public boolean validateMonitoringHeader() {
        waitExecuter.sleep(1000);
        MouseActions.clickOnElement(driver, managePageObject.gear);
        waitExecuter.sleep(1000);
        if (managePageObject.monitoringHeader.isDisplayed()) {
            if (managePageObject.monitoringHeader.getText().equals("Monitoring")) {
                logger.info("Monitoring Headers found: " + managePageObject.monitoringHeader.getText());
                return true;
            }
        }
        return false;
    }

    public boolean verifyPartitionInfoTab() {
        waitExecuter.sleep(3000);
        if (managePageObject.partitionInfoTab.isDisplayed()) {
            return managePageObject.partitionInfoTab.getText().equals("Partition Info");
        }
        return false;
    }

    public boolean validatePartitionInfoTableHeaders() {
        waitExecuter.sleep(2000);
        int partitionInfoTblColumnsCount = managePageObject.listPartitionInfoTableHeaders.size();
        List<String> listPartitionInfoTblColumns = new ArrayList<String>();
        boolean flagAllCols = true;
        if (partitionInfoTblColumnsCount > 0) {
            for (int i = 0; i < partitionInfoTblColumnsCount; i++) {
                listPartitionInfoTblColumns.add(managePageObject.listPartitionInfoTableHeaders.get(i).getText());
            }
            System.out.println("Actual PartitionsInfo headers : " + listPartitionInfoTblColumns);
            logger.info("Actual PartitionsInfo headers : " + listPartitionInfoTblColumns);
            String[] expectedHeaders = {"Partition", "Free Space", "Total Space", "Last Updated"};
            for (String strCol : expectedHeaders) {
                if (!listPartitionInfoTblColumns.contains(strCol)) {
                    flagAllCols = false;
                }
            }
        } else {
            flagAllCols = false;
        }
        return flagAllCols;
    }

    public boolean verifyPartitionInfoTSAndDataAge() {
        if (managePageObject.partitionInfoTSAndDataAge.isDisplayed()) {
            String TSAndDataAge = managePageObject.partitionInfoTSAndDataAge.getText();
            System.out.println("PartitionInfo TS And Data Age: " + TSAndDataAge);
            logger.info("PartitionInfo TS And Data Age: " + TSAndDataAge);
            return true;
        }
        return false;
    }

    public void clickDBStatusTab() {
        waitExecuter.sleep(4000);
        managePageObject.monitoringDBStatusTab.click();
        waitExecuter.sleep(2000);
    }

    public boolean validateDBStatusTableHeaders() {
        waitExecuter.sleep(1000);
        int dbstatusTblHeaderCount = managePageObject.monitoringDBStatusTableHeaders.size();
        List<String> listDBStatusTblColumns = new ArrayList<String>();
        boolean flagAllCols = true;
        if (dbstatusTblHeaderCount > 0) {
            for (int i = 0; i < dbstatusTblHeaderCount; i++) {
                listDBStatusTblColumns.add(managePageObject.monitoringDBStatusTableHeaders.get(i).getText());
            }
            System.out.println("Actual DB Status headers are: " + listDBStatusTblColumns);
            logger.info("Actual DB Status headers are: " + listDBStatusTblColumns);
            String[] expectedHeaders = {"Connection Ok", "Last Updated"};
            for (String strCols : expectedHeaders) {
                if (!listDBStatusTblColumns.contains(strCols)) {
                    flagAllCols = false;
                }
            }
        } else {
            flagAllCols = false;
        }
        return flagAllCols;
    }

    public boolean verifyDBStatusTSAndDataAge() {
        if (managePageObject.dbStatusTimeStampAndDataAge.isDisplayed()) {
            String TSAndDataAge = managePageObject.dbStatusTimeStampAndDataAge.getText();
            System.out.println("DB Status TS And Data Age: " + TSAndDataAge);
            logger.info("DB Status TS And Data Age: " + TSAndDataAge);
            return true;
        }
        return false;
    }

    public void clickWebElementTab(WebElement element) {
        element.click();
    }

    public void clickDBPerformanceTab() {
        waitExecuter.sleep(4000);
        clickWebElementTab(managePageObject.monitoringDBPerformanceTab);
        waitExecuter.sleep(2000);
    }

    public boolean validateDBPerformanceTableHeaders() {
        waitExecuter.sleep(1000);
        int dbPerformanceTblHeaderCount = managePageObject.monitoringDBPerformanceTableHeaders.size();
        List<String> listDBPerformanceTblColumns = new ArrayList<String>();
        boolean flagAllCols = true;
        if (dbPerformanceTblHeaderCount > 0) {
            for (int i = 0; i < dbPerformanceTblHeaderCount; i++) {
                listDBPerformanceTblColumns.add(managePageObject.monitoringDBPerformanceTableHeaders.get(i).getText());
            }
            System.out.println("Actual DB Performance headers are: " + listDBPerformanceTblColumns);
            logger.info("Actual DB Performance headers are: " + listDBPerformanceTblColumns);
            String[] expectedHeaders = {"Connection Ok", "Last Query Duration", "Query Timed Out", "Query Exception"};
            for (String strCols : expectedHeaders) {
                if (!listDBPerformanceTblColumns.contains(strCols)) {
                    flagAllCols = false;
                }
            }
        } else {
            flagAllCols = false;
        }
        return flagAllCols;
    }

    public boolean verifyDBPerfromanceTSAndDataAge() {
        if (managePageObject.timeStampAndDataAge.isDisplayed()) {
            String TSAndDataAge = managePageObject.timeStampAndDataAge.getText();
            System.out.println("DB Performance TS And Data Age: " + TSAndDataAge);
            logger.info("DB Performance TS And Data Age: " + TSAndDataAge);
            return true;
        }
        return false;
    }

    public void clickZookeperTab() {
        waitExecuter.sleep(4000);
        clickWebElementTab(managePageObject.monitoringZookeeperTab);
        waitExecuter.sleep(2000);
    }

    public boolean validateZookeperTableHeaders() {
        waitExecuter.sleep(1000);
        int zookeperTblHeaderCount = managePageObject.monitoringZookeeperTableHeaders.size();
        List<String> listZookeperTblColumns = new ArrayList<String>();
        boolean flagAllCols = true;
        if (zookeperTblHeaderCount > 0) {
            for (int i = 0; i < zookeperTblHeaderCount; i++) {
                listZookeperTblColumns.add(managePageObject.monitoringZookeeperTableHeaders.get(i).getText());
            }
            System.out.println("Actual Zookeper headers are: " + listZookeperTblColumns);
            logger.info("Actual Zookeper headers are: " + listZookeperTblColumns);
            String[] expectedHeaders = {"Host", "Port", "Is Running", "Mode", "Latency Avg", "Latency Max", "Latency Min", "Node Count", "Connections Count", "Outstanding Count", "Last Updated"};
            for (String strCols : expectedHeaders) {
                if (!listZookeperTblColumns.contains(strCols)) {
                    flagAllCols = false;
                }
            }
        } else {
            flagAllCols = false;
        }
        return flagAllCols;
    }

    public boolean verifyZookeperTSAndDataAge() {
        if (managePageObject.timeStampAndDataAge.isDisplayed()) {
            String TSAndDataAge = managePageObject.timeStampAndDataAge.getText();
            System.out.println("Zookeper TS And Data Age: " + TSAndDataAge);
            logger.info("Zookeper TS And Data Age: " + TSAndDataAge);
            return true;
        }
        return false;
    }

    public void clickKafkaTab() {
        waitExecuter.sleep(4000);
        clickWebElementTab(managePageObject.monitoringKafkaTab);
        waitExecuter.sleep(2000);
    }

    public boolean validateKafaDataDetails() {
        waitExecuter.sleep(1000);
        if (managePageObject.kafkaBasicData.getText().equals("Basic Data")) {
            waitExecuter.sleep(1000);
            if (managePageObject.kafkaConsumerGroups.getText().equals("Consumer Groups")) {
                waitExecuter.sleep(1000);
                if (managePageObject.kafkaConsumerGroups.getText().equals("Consumer Groups")) {
                    waitExecuter.sleep(1000);
                    if (managePageObject.kafkaTopicList.getText().equals("Topic List")) {
                        waitExecuter.sleep(1000);
                        return managePageObject.kafkaTopicWithoutConsumer.getText().equals("Topic Without Consumer");
                    }
                }
            }
        }

        return false;
    }

    public boolean verifyKafkaTSAndDataAge() {
        if (managePageObject.timeStampAndDataAge.isDisplayed()) {
            String TSAndDataAge = managePageObject.timeStampAndDataAge.getText();
            System.out.println("Kafka TS And Data Age: " + TSAndDataAge);
            logger.info("Kafka TS And Data Age: " + TSAndDataAge);
            return true;
        }
        return false;
    }

    public void clickElasticTab() {
        waitExecuter.sleep(4000);
        clickWebElementTab(managePageObject.monitoringElasticTab);
        waitExecuter.sleep(2000);
    }

    public boolean verifyElasticTSAndDataAge() {
        if (managePageObject.timeStampAndDataAge.isDisplayed()) {
            String TSAndDataAge = managePageObject.timeStampAndDataAge.getText();
            System.out.println("Elastic TS And Data Age: " + TSAndDataAge);
            logger.info("Elastic TS And Data Age: " + TSAndDataAge);
            return true;
        }
        return false;
    }

    public boolean validateElasticDataDetails() {
        waitExecuter.sleep(1000);
        if (managePageObject.monitoringElasticNodeHeader.getText().equals("Nodes")) {
            waitExecuter.sleep(1000);
            return managePageObject.monitoringElasticClusterHealthHeader.getText().equals("Cluster Health");
        }
        return false;
    }

}
