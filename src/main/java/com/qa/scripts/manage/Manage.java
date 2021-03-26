package com.qa.scripts.manage;

import com.qa.base.BaseClass;
import com.qa.enums.UserAction;
import com.qa.pagefactory.manage.ManagePageObject;
import com.qa.utils.MouseActions;
import com.qa.utils.WaitExecuter;
import com.qa.utils.actions.UserActions;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * @author Birender Kumar
 */
public class Manage {
    private WebDriver driver;
    private WaitExecuter waitExecuter;
    private ManagePageObject managePageObject;
    private final UserActions userAction;

    private static final java.util.logging.Logger logger = Logger.getLogger(Manage.class.getName());

    public Manage(WebDriver driver) {
        this.driver = driver;
        waitExecuter = new WaitExecuter(driver);
        managePageObject = new ManagePageObject(driver);
        userAction = new UserActions(driver);
    }

    public String validateDaemonHeader(){
        waitExecuter.sleep(2000);
        String headers = managePageObject.daemonsHeader.getText();
        logger.info("Daemons Header found:"+headers);
        return headers;
    }

    public boolean validateDaemonsCountInTbl(){
        int dameonsCount = managePageObject.listDaemons.size();
        logger.info("Count of Daemons :"+dameonsCount);
        if(dameonsCount > 0){
            return true;
        }
        return false;
    }

    public Boolean validateAllTabsPresent(){
        logger.info("Number of tabs on manage: "+managePageObject.allManageTabList.size());
        List<String> allTabsOnManagePage = new ArrayList<String>();
        for(int i=0; i<managePageObject.allManageTabList.size(); i++){
            allTabsOnManagePage.add(managePageObject.allManageTabList.get(i).getText());
        }
        System.out.println("Actual tabs: "+ allTabsOnManagePage );
        String[] expectedTabsOnManagePage = {"Daemons","Stats", "Run Diagnostics", "Monitoring"};
        Boolean boolAllTabs=true;
        for(String strTab: expectedTabsOnManagePage){
           if(!allTabsOnManagePage.contains(strTab)){
               boolAllTabs= false;
           }
        }
        return boolAllTabs;
    }

    public void clickOnIconSort(){
        int countOfIconSort = managePageObject.iconSort.size();
        logger.info("Number of total icon sort :"+ countOfIconSort);
        if(countOfIconSort > 0 ){
            for(int i=0; i<countOfIconSort-1 ;i++){
                waitExecuter.sleep(2000);
                managePageObject.iconSort.get(i).click();
                waitExecuter.sleep(3000);
                Assert.assertTrue(validateDaemonsCountInTbl());

            }
        }
    }

    public void clickOnView(){
        waitExecuter.sleep(1000);
        managePageObject.viewLink.click();
        waitExecuter.sleep(3000);
    }

    public void verifyViewPage(){
        waitExecuter.sleep(1000);
        int viewLogCategoryCount = managePageObject.viewLogCategory.size();
        if(viewLogCategoryCount>0){
            for(int i=0; i<viewLogCategoryCount-1; i++){
                waitExecuter.sleep(1000);
                String logLevel = managePageObject.viewLogCategory.get(i).getText();
                managePageObject.viewLogCategory.get(i).click();
                waitExecuter.sleep(3000);
                verifyViewInnerTbl(logLevel);
                waitExecuter.sleep(1000);
            }
        }else{
            logger.info("No View link displayed.");
            BaseClass.test.log(LogStatus.INFO,"No View link displayed.");
        }
    }

    public boolean verifyViewInnerTbl(String logLevel){
        waitExecuter.sleep(1000);
        int viewTblRowsCount = managePageObject.viewTblRows.size();
        if(viewTblRowsCount >0){
            return true;
        }else{
            logger.info("View Inner Table is not displayed for log level: "+ logLevel);
            BaseClass.test.log(LogStatus.INFO,"View Inner Table is not displayed for log level: "+ logLevel);
        }
        return false;
    }

    public void clickStatsTab(){
        waitExecuter.sleep(1000);
        managePageObject.statsTab.click();
        waitExecuter.sleep(3000);
    }

    public boolean validateStatsHeader(){
        waitExecuter.sleep(3000);
        logger.info("Stats Headers found: "+managePageObject.statsHeader.getText());
        return managePageObject.statsHeader.getText().equals("Stats");
    }

    public boolean validateStatsTablePresent(){
        waitExecuter.sleep(2000);
        int dbStatsTblSize = managePageObject.dbStatsTable.size();
        if(dbStatsTblSize > 0){
            return true;
        }
        return false;
    }

    public void clickElasticsearchTab(){
        waitExecuter.sleep(1000);
        managePageObject.elasticSearchTab.click();
        waitExecuter.sleep(3000);
    }

    public boolean verifyElasticSearchDetails(){
        waitExecuter.sleep(2000);

        if(managePageObject.elasticSearchStatus.isDisplayed()) {
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

    public void clickSensorHeartbeatTab(){
        waitExecuter.sleep(1000);
        managePageObject.sensorHeartbeatTab.click();
        waitExecuter.sleep(3000);
    }

    public boolean verifySensorHeartbeatDetails(){
        waitExecuter.sleep(2000);
        int sensorHeartbeatTblRowCount = managePageObject.sensorHeartbeatTblRows.size();
        logger.info("SensorHeartbeat table row count: "+sensorHeartbeatTblRowCount);

        if(sensorHeartbeatTblRowCount > 0){
            return true;
        }
        return false;
    }

    public void clickRunDiagnostics(){
        waitExecuter.sleep(1000);
        managePageObject.runDiagnosticsTab.click();
        waitExecuter.sleep(3000);
    }
    public boolean validateRunDiagnosticsHeader(){
        waitExecuter.waitUntilElementPresent(managePageObject.loadLatestDiagnosticsBtn);
        logger.info("RunDiagnostics Headers found: "+managePageObject.runDiagnosticsHeader.getText());
        return managePageObject.runDiagnosticsHeader.getText().equals("Diagnostic");
    }

    public void clickOnLoadLatestDiagnostics(){
        waitExecuter.waitUntilElementClickable(managePageObject.loadLatestDiagnosticsBtn);
        try{
            userAction.performActionWithPolling(managePageObject.loadLatestDiagnosticsBtn, UserAction.CLICK);
        }catch (TimeoutException te){
            userAction.performActionWithPolling(managePageObject.loadLatestDiagnosticsBtn, UserAction.CLICK);
        }
        waitExecuter.waitUntilPageFullyLoaded();
        waitExecuter.waitUntilElementClickable(managePageObject.loadLatestDiagnosticsBtn);

    }

    public boolean verifyLoadLatestDiagnosticsContent(){
        waitExecuter.sleep(1000);
        String latestDiagnoCtn = managePageObject.latestDiagnosticsContent.getText();
        if(latestDiagnoCtn.length() > 0){
            return true;
        }
        return false;
    }

    public boolean verifyLoadLatestDiagnosticsHeaderAndTimeStamp(){
        waitExecuter.sleep(12000);
        String runDiagnoHeader = managePageObject.latestDiagnosticsContentHeader.getText();
        runDiagnoHeader = runDiagnoHeader.substring(0,15);
        logger.info("Latest Diagnostics Content Header found: "+runDiagnoHeader);

        if(runDiagnoHeader.equals("Diagnostics Log")){
            String runDiagnoHeaderTS = managePageObject.latestDiagnosticsContentHeaderTimeStamp.getText();
            logger.info("Latest Diagnostics Content Header TimeStamp found: "+runDiagnoHeaderTS);
            return true;
        }
        return false;
    }

    public void clickOnDownloadSupportBundle(){
        UserActions actions = new UserActions(driver);
        waitExecuter.waitUntilElementClickable(managePageObject.downloadSupportBundleBtn);
        actions.performActionWithPolling(managePageObject.downloadSupportBundleBtn, UserAction.CLICK);
        waitExecuter.waitUntilPageFullyLoaded();
        waitExecuter.waitUntilElementClickable(managePageObject.downloadSupportBundleBtn);
    }

    public void clickMonitoringTab(){
        UserActions actions = new UserActions(driver);
        waitExecuter.waitUntilElementClickable(managePageObject.monitoringTab);
        actions.performActionWithPolling(managePageObject.monitoringTab,UserAction.CLICK);
        waitExecuter.waitUntilElementPresent(managePageObject.monitoringHeader);
    }

    public boolean validateMonitoringHeader(){
        waitExecuter.sleep(2000);
        if(managePageObject.monitoringHeader.isDisplayed()){
            if(managePageObject.monitoringHeader.getText().equals("Monitoring")){
                logger.info("Monitoring Headers found: "+managePageObject.monitoringHeader.getText());
                return true;
            }
        }
        return false;
    }

    public boolean verifyPartitionInfoTab(){
        waitExecuter.sleep(3000);
        if(managePageObject.partitionInfoTab.isDisplayed()){
            return managePageObject.partitionInfoTab.getText().equals("Partition Info");
        }
        return false;
    }

    public boolean validatePartitionInfoTableHeaders(){
        waitExecuter.sleep(2000);
        int partitionInfoTblColumnsCount = managePageObject.listPartitionInfoTableHeaders.size();
        List<String> listPartitionInfoTblColumns = new ArrayList<String>();
        boolean flagAllCols = true;
        if(partitionInfoTblColumnsCount > 0){
            for(int i=0; i<partitionInfoTblColumnsCount; i++) {
                listPartitionInfoTblColumns.add(managePageObject.listPartitionInfoTableHeaders.get(i).getText());
            }
            System.out.println("Actual PartitionsInfo headers : "+ listPartitionInfoTblColumns );
            logger.info("Actual PartitionsInfo headers : "+ listPartitionInfoTblColumns );
            String[] expectedHeaders = {"Partition","Free Space", "Total Space", "Last Updated"};
            for(String strCol: expectedHeaders) {
                if (!listPartitionInfoTblColumns.contains(strCol)) {
                    flagAllCols = false;
                }
            }
        }else{
            flagAllCols = false;
        }
        return flagAllCols;
    }

    public boolean verifyPartitionInfoTSAndDataAge(){
        if(managePageObject.partitionInfoTSAndDataAge.isDisplayed()){
            String TSAndDataAge = managePageObject.partitionInfoTSAndDataAge.getText();
            System.out.println("PartitionInfo TS And Data Age: "+ TSAndDataAge );
            logger.info("PartitionInfo TS And Data Age: "+ TSAndDataAge );
            return true;
        }
        return false;
    }

    public void clickDBStatusTab(){
        waitExecuter.sleep(4000);
        managePageObject.monitoringDBStatusTab.click();
        waitExecuter.sleep(2000);
    }

    public boolean validateDBStatusTableHeaders(){
        waitExecuter.sleep(1000);
        int dbstatusTblHeaderCount = managePageObject.monitoringDBStatusTableHeaders.size();
        List<String> listDBStatusTblColumns = new ArrayList<String>();
        boolean flagAllCols = true;
        if(dbstatusTblHeaderCount > 0){
            for(int i=0; i< dbstatusTblHeaderCount ; i++){
                listDBStatusTblColumns.add(managePageObject.monitoringDBStatusTableHeaders.get(i).getText());
            }
            System.out.println("Actual DB Status headers are: "+ listDBStatusTblColumns );
            logger.info("Actual DB Status headers are: "+ listDBStatusTblColumns );
            String[] expectedHeaders = {"Connection Ok","Last Updated"};
            for(String strCols : expectedHeaders){
                if(!listDBStatusTblColumns.contains(strCols)){
                    flagAllCols= false;
                }
            }
        }else{
            flagAllCols = false;
        }
        return flagAllCols;
    }
    public boolean verifyDBStatusTSAndDataAge(){
        if(managePageObject.dbStatusTimeStampAndDataAge.isDisplayed()){
            String TSAndDataAge = managePageObject.dbStatusTimeStampAndDataAge.getText();
            System.out.println("DB Status TS And Data Age: "+ TSAndDataAge );
            logger.info("DB Status TS And Data Age: "+ TSAndDataAge );
            return true;
        }
        return false;
    }

    public void clickWebElementTab(WebElement element){
        element.click();
    }

    public void clickDBPerformanceTab(){
        waitExecuter.sleep(4000);
        clickWebElementTab(managePageObject.monitoringDBPerformanceTab);
        waitExecuter.sleep(2000);
    }

    public boolean validateDBPerformanceTableHeaders() {
        waitExecuter.sleep(1000);
        int dbPerformanceTblHeaderCount = managePageObject.monitoringDBPerformanceTableHeaders.size();
        List<String> listDBPerformanceTblColumns = new ArrayList<String>();
        boolean flagAllCols = true;
        if(dbPerformanceTblHeaderCount > 0) {
            for(int i=0; i<dbPerformanceTblHeaderCount ; i++){
                listDBPerformanceTblColumns.add(managePageObject.monitoringDBPerformanceTableHeaders.get(i).getText());
            }
            System.out.println("Actual DB Performance headers are: "+ listDBPerformanceTblColumns );
            logger.info("Actual DB Performance headers are: "+ listDBPerformanceTblColumns );
            String[] expectedHeaders = {"Connection Ok","Last Query Duration","Query Timed Out","Query Exception"};
            for(String strCols : expectedHeaders) {
                if (!listDBPerformanceTblColumns.contains(strCols)) {
                    flagAllCols = false;
                }
            }
        }else{
            flagAllCols = false;
        }
        return flagAllCols;
   }

    public boolean verifyDBPerfromanceTSAndDataAge(){
        if(managePageObject.timeStampAndDataAge.isDisplayed()){
            String TSAndDataAge = managePageObject.timeStampAndDataAge.getText();
            System.out.println("DB Performance TS And Data Age: "+ TSAndDataAge );
            logger.info("DB Performance TS And Data Age: "+ TSAndDataAge );
            return true;
        }
        return false;
    }

    public void clickZookeperTab(){
        waitExecuter.sleep(4000);
        clickWebElementTab(managePageObject.monitoringZookeeperTab);
        waitExecuter.sleep(2000);
    }

    public boolean validateZookeperTableHeaders() {
        waitExecuter.sleep(1000);
        int zookeperTblHeaderCount = managePageObject.monitoringZookeeperTableHeaders.size();
        List<String> listZookeperTblColumns = new ArrayList<String>();
        boolean flagAllCols = true;
        if(zookeperTblHeaderCount > 0) {
            for(int i=0; i<zookeperTblHeaderCount ; i++){
                listZookeperTblColumns.add(managePageObject.monitoringZookeeperTableHeaders.get(i).getText());
            }
            System.out.println("Actual Zookeper headers are: "+ listZookeperTblColumns );
            logger.info("Actual Zookeper headers are: "+ listZookeperTblColumns );
            String[] expectedHeaders = {"Host","Port","Is Running","Mode","Latency Avg","Latency Max","Latency Min",
                    "Node Count","Connections Count","Outstanding Count","Last Updated"};
            for(String strCols : expectedHeaders) {
                if (!listZookeperTblColumns.contains(strCols)) {
                    flagAllCols = false;
                }
            }
        }else{
            flagAllCols = false;
        }
        return flagAllCols;
    }

    public boolean verifyZookeperTSAndDataAge(){
        if(managePageObject.timeStampAndDataAge.isDisplayed()){
            String TSAndDataAge = managePageObject.timeStampAndDataAge.getText();
            System.out.println("Zookeper TS And Data Age: "+ TSAndDataAge );
            logger.info("Zookeper TS And Data Age: "+ TSAndDataAge );
            return true;
        }
        return false;
    }

    public void clickKafkaTab(){
        waitExecuter.sleep(4000);
        clickWebElementTab(managePageObject.monitoringKafkaTab);
        waitExecuter.sleep(2000);
    }

    public boolean validateKafaDataDetails(){
        waitExecuter.sleep(1000);
        if(managePageObject.kafkaBasicData.getText().equals("Basic Data")){
            waitExecuter.sleep(1000);
            if(managePageObject.kafkaConsumerGroups.getText().equals("Consumer Groups")){
                waitExecuter.sleep(1000);
                if(managePageObject.kafkaConsumerGroups.getText().equals("Consumer Groups")){
                    waitExecuter.sleep(1000);
                    if(managePageObject.kafkaTopicList.getText().equals("Topic List")){
                        waitExecuter.sleep(1000);
                        if(managePageObject.kafkaTopicWithoutConsumer.getText().equals("Topic Without Consumer")){
                            return true;
                        }
                    }
                }
            }
        }

        return false;
    }

    public boolean verifyKafkaTSAndDataAge(){
        if(managePageObject.timeStampAndDataAge.isDisplayed()){
            String TSAndDataAge = managePageObject.timeStampAndDataAge.getText();
            System.out.println("Kafka TS And Data Age: "+ TSAndDataAge );
            logger.info("Kafka TS And Data Age: "+ TSAndDataAge );
            return true;
        }
        return false;
    }

    public void clickElasticTab(){
        waitExecuter.sleep(4000);
        clickWebElementTab(managePageObject.monitoringElasticTab);
        waitExecuter.sleep(2000);
    }

    public boolean verifyElasticTSAndDataAge(){
        if(managePageObject.timeStampAndDataAge.isDisplayed()){
            String TSAndDataAge = managePageObject.timeStampAndDataAge.getText();
            System.out.println("Elastic TS And Data Age: "+ TSAndDataAge );
            logger.info("Elastic TS And Data Age: "+ TSAndDataAge );
            return true;
        }
        return false;
    }

    public boolean validateElasticDataDetails(){
        waitExecuter.sleep(1000);
        if(managePageObject.monitoringElasticNodeHeader.getText().equals("Nodes")){
            waitExecuter.sleep(1000);
            if(managePageObject.monitoringElasticClusterHealthHeader.getText().equals("Cluster Health")){
                return true;
            }
        }
        return false;
    }

}
