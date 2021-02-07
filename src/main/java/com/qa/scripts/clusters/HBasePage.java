package com.qa.scripts.clusters;

import com.qa.enums.UserAction;
import com.qa.enums.hbase.HbaseRegionSvrTableColumn;
import com.qa.enums.hbase.HbaseTablesColumn;
import com.qa.pagefactory.clusters.HBasePageObject;
import com.qa.pagefactory.jobs.ApplicationsPageObject;
import com.qa.scripts.DatePicker;
import com.qa.utils.MouseActions;
import com.qa.utils.WaitExecuter;
import com.qa.utils.actions.UserActions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;

import java.util.*;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class HBasePage {
    private WebDriver driver;
    private WaitExecuter waitExecuter;
    private HBasePageObject hBasePageObject;
    private UserActions actions;
    private DatePicker datePicker;
    private Logger logger = Logger.getLogger(HBasePage.class.getName());

    String xAxis = "//*[name()='svg' and contains(@class,'highcharts-root')]//*[name()='g' and contains(@class,'highcharts-xaxis-labels')]/*[name()='text']";
    String yAxis = "//*[name()='svg' and contains(@class,'highcharts-root')]//*[name()='g' and contains(@class,'highcharts-yaxis-labels')]/*[name()='text']";

    public HBasePage(WebDriver driver){
        this.driver = driver;
        waitExecuter = new WaitExecuter(driver);
        hBasePageObject = new HBasePageObject(driver);
        actions = new UserActions(driver);
        datePicker = new DatePicker(driver);
    }

    //Method to get HBase header
    public String getHBaseHeader(){
        waitExecuter.waitUntilElementPresent(hBasePageObject.hbaseHeader);
        waitExecuter.waitUntilPageFullyLoaded();
        return hBasePageObject.hbaseHeader.getText().trim();
    }

    //Verify HBase cluster metrics title
    public String verifyHBaseClusterMetricsTitle(){
        waitExecuter.waitUntilElementPresent(hBasePageObject.hBaseMetricsTitle);
        String hBaseMetricsTitleText = hBasePageObject.hBaseMetricsTitle.getText();
        return hBaseMetricsTitleText;
    }

    //Method to get all the HBase clusters list
    public List<String> getAllHBaseClusters(){
        waitExecuter.waitUntilElementClickable(hBasePageObject.hBaseClusterDropDown);
        MouseActions.clickOnElement(driver, hBasePageObject.hBaseClusterDropDown);
        waitExecuter.waitUntilPageFullyLoaded();
        List<WebElement> hBaseClusterElementList = hBasePageObject.hBaseClusters;
        Assert.assertFalse(hBaseClusterElementList.isEmpty(), "HBase clusters not found");
        List<String> hBaseClusterList = new ArrayList<>();
        for(int i=0 ; i< hBaseClusterElementList.size() ; i++){
            hBaseClusterList.add(hBaseClusterElementList.get(i).getText().trim());
        }
        return hBaseClusterList;
    }

    public List<WebElement> getHBaseClustersElements(){
        waitExecuter.waitUntilElementClickable(hBasePageObject.hBaseClusterDropDown);
        MouseActions.clickOnElement(driver, hBasePageObject.hBaseClusterDropDown);
        waitExecuter.waitUntilPageFullyLoaded();
        List<WebElement> hBaseClusterElementList = hBasePageObject.hBaseClusters;
        Assert.assertFalse(hBaseClusterElementList.isEmpty(), "HBase clusters not found");
        return hBaseClusterElementList;
    }

    //Method to select hbase cluster from drop down
    public void selectHBaseCluster(String hBaseClusterName){
        waitExecuter.waitUntilElementClickable(hBasePageObject.hBaseClusterDropDown);
        MouseActions.clickOnElement(driver, hBasePageObject.hBaseClusterDropDown);
        waitExecuter.waitUntilPageFullyLoaded();
        waitExecuter.sleep(2000);
        ApplicationsPageObject applicationsPageObject = new ApplicationsPageObject(driver);
        applicationsPageObject.clusterIdsearchfield.sendKeys(hBaseClusterName);
        waitExecuter.sleep(1000);
        applicationsPageObject.select1stCluster.click();
    }

    public void selectHBaseDefaultCluster(){
        waitExecuter.waitUntilElementClickable(hBasePageObject.hBaseClusterDropDown);
        MouseActions.clickOnElement(driver, hBasePageObject.hBaseClusterDropDown);
        waitExecuter.waitUntilPageFullyLoaded();
        List<WebElement> hBaseClusterElementList = hBasePageObject.hBaseClusters;
        Assert.assertFalse(hBaseClusterElementList.isEmpty(), "HBase clusters not found");
        String hBaseClusterName = hBaseClusterElementList.get(0).getText();
        MouseActions.clickOnElement(driver, hBaseClusterElementList.get(0));
    }

    public void selectDateAsLast30Days(){
        datePicker.clickOnDatePicker();
        waitExecuter.sleep(1000);
        datePicker.selectLast30Days();
        waitExecuter.sleep(3000);
        waitExecuter.waitUntilPageFullyLoaded();
    }

    //Method is to verify the presence of tool tip for HBase cluster KPI's
    public boolean verifyMetricsToolTips(){
        List<WebElement> hBaseKPIList = hBasePageObject.hBaseKPIs;
        Assert.assertFalse(hBaseKPIList.isEmpty(),"HBase cluster KPIs not found.");
        List<String> toolTipList = new ArrayList<>();

        Actions builder = new Actions(driver);
        for(int i=0; i< hBaseKPIList.size(); i++){
            builder.moveToElement(hBaseKPIList.get(i)).build().perform();
            waitExecuter.sleep(1000);
            try{
                String toolTip = hBaseKPIList.get(i).getAttribute("aria-describedby");
                logger.info("ToolTips: "+ toolTip);
                waitExecuter.sleep(1000);
                if(toolTip.length() > 0){
                    logger.info("ToolTips: "+ toolTip);
                    toolTipList.add(toolTip);
                }
            }catch (Exception e){
                e.printStackTrace();
                return false;
            }

        }
        //Check for tooltips and total 7 KPI's in HBase
        if(toolTipList.size() == 7){
            return true;
        }
        return false;
    }

    // Method to verify the HBase cluster Metrics
    public void verifyHBaseClustersMetrics(){
        selectHBaseDefaultCluster();

        List<WebElement> hBaseKPIList = hBasePageObject.hBaseClusterKPIs;
        List<WebElement> hBaseKPIValueList = hBasePageObject.hBaseClusterKPIValues;

        Assert.assertFalse(hBaseKPIList.isEmpty() || hBaseKPIValueList.isEmpty(), "HBase cluster KPIs" +
                " not found.");
        List<String> expectedHBaseKPIs = Arrays.asList("Live Region Servers","Dead Region Servers", "Cluster Requests",
                "Average Load", "RIT Count", "RIT Over Threshold","RIT Oldest Age");
        if(expectedHBaseKPIs.size() == hBaseKPIList.size()){
            for(int i=0; i<hBaseKPIList.size(); i++){
                String kpiName = hBaseKPIList.get(i).getText();
                Assert.assertTrue(expectedHBaseKPIs.contains(kpiName), "The KPI :["+
                        kpiName+"] is not disaplyed in UI.");
                String kpiValue = hBaseKPIValueList.get(i).getText();
                //Check if data has only special charaters
                boolean onlySpecialChars = kpiValue.matches("[^a-zA-Z0-9]+");
                Assert.assertFalse(kpiValue.isEmpty() || onlySpecialChars, "Expected: AlphaNumeric " +
                        "value for " + kpiName + " Actual: " + kpiValue);
                logger.info("Found HBase KPI: AlphaNumeric " +
                        "value for " + kpiName + " Actual: " + kpiValue);
            }
        }else{
            Assert.assertFalse(true, "HBase KPIs count mismatch.");
        }
    }

    /***
     * Method to verify the hBase cluster dropdown
     * @return String
     */
    public String verifyClusterDropDown(HBasePageObject hBasePageObject){
        waitExecuter.waitUntilElementClickable(hBasePageObject.hBaseClusterDropDown);
        waitExecuter.sleep(2000);
        MouseActions.clickOnElement(driver, hBasePageObject.hBaseClusterDropDown);
        waitExecuter.sleep(2000);
        List<WebElement> hBaseClusterList = hBasePageObject.hBaseClusters;
        Assert.assertFalse(hBaseClusterList.isEmpty(), "The drop down list for hbase cluster is empty");
        String clustername = hBaseClusterList.get(0).getText();
        MouseActions.clickOnElement(driver, hBaseClusterList.get(0));

        datePicker.clickOnDatePicker();
        waitExecuter.sleep(1000);
        datePicker.selectLast30Days();
        waitExecuter.sleep(3000);
        waitExecuter.waitUntilPageFullyLoaded();

        return clustername;
    }

    /***
     * Method to verify the kpis of a connected hbase cluster
     */
    public void verifyHbaseClusterKPIs(HBasePageObject hBasePageObject, String[] expectedHBaseKPIs){
        List<WebElement> hBaseKPIList = hBasePageObject.hBaseClusterKPIs;
        List<WebElement> hBaseKPIValueList = hBasePageObject.hBaseClusterKPIValues;

        Assert.assertFalse(hBaseKPIList.isEmpty() || hBaseKPIValueList.isEmpty(), "HBase cluster KPIs" +
                " not found.");

        for (int i = 0; i < hBaseKPIList.size(); i++) {
            String kpiName = hBaseKPIList.get(i).getText();
            String kpiValue = hBaseKPIValueList.get(i).getText();
            logger.info("KPI Name: " + kpiName + "\n KPI Value: " + kpiValue);
            Assert.assertTrue(Arrays.asList(expectedHBaseKPIs).contains(kpiName), "The kpi: [" + kpiName + "] " +
                    "is not displayed in the UI");
            //Check if data has only special characters
            boolean onlySpecialChars = kpiValue.matches("[^a-zA-Z0-9]+");
            Assert.assertFalse(kpiValue.isEmpty() || onlySpecialChars, "Expected: AlphaNumeric " +
                    "value for " + kpiName + " Actual: " + kpiValue);
        }
    }

    /***
     * Method to verify the HBase Metrics KPI Graphs of a connected hbase cluster
     */
    public void verifyHBaseKPIGraphs(HBasePageObject hBasePageObject,String expectedMetricsName, String graphId){
        List<WebElement> metricsKpiList = hBasePageObject.hBaseMetrics;
        List<WebElement> metricsKpiHeaderList = hBasePageObject.hBaseMetricsHeader;
        List<WebElement> metricsKpiFooterList = hBasePageObject.hBaseMetricsFooter;
        List<WebElement> metricsKpiGraphList = hBasePageObject.hBaseMetricsGraph;
        Assert.assertFalse(metricsKpiList.isEmpty(), "Metrics for hbase cluster is empty");
        String xAxisPath = "//*[@id='" + graphId + "']" + xAxis;
        String yAxisPath = "//*[@id='" + graphId + "']" + yAxis;

        for (int i = 0; i < metricsKpiList.size(); i++) {
            String metricsName = metricsKpiHeaderList.get(i).getText();
            logger.info("Metrics Name: " + metricsName + " Expected Name: " + expectedMetricsName);
            if (metricsName.equals(expectedMetricsName)) {
                Assert.assertFalse(metricsName.isEmpty(), " Metrics Name not displayed");
                logger.info("Metrics Name: [" + metricsName + "] displayed in the header");
                waitExecuter.waitUntilElementPresent(metricsKpiGraphList.get(i));
                Assert.assertTrue(metricsKpiGraphList.get(i).isDisplayed(), "The graph for metrics " + metricsName + " is not displayed");
                logger.info("The graph for Metrics : [" + metricsName + "] is displayed");
                Assert.assertTrue(metricsKpiFooterList.get(i).isDisplayed(), "The footer for metrics " + metricsName + " is not displayed");
                logger.info("The footer for Metrics : [" + metricsName + "] is displayed");
                verifyAxis(xAxisPath, "X-Axis");
                verifyAxis(yAxisPath, "Y-Axis");
            }
        }
    }

    public void verifyAxis(String axisPath, String axisName) {
        List<WebElement> axisPathList = driver.findElements(By.xpath(axisPath));
        Assert.assertFalse(axisPathList.isEmpty(), "No points plotted on the " + axisName);
        HashSet<String> axisValSet = new HashSet<>();
        ArrayList<String> axisValArr = new ArrayList<>();
        for (int i = 0; i < axisPathList.size(); i++) {
            axisValArr.add(axisPathList.get(i).getText());
            axisValSet.add(axisPathList.get(i).getText());
        }
        logger.info("Expected " + axisName + " : " + axisValSet + "\n Actual " + axisName + " : " + axisValArr);
        Assert.assertEquals(axisValSet.size(), axisValArr.size(), "Duplicate values present in the " + axisName + "\n" +
                "Expected : " + axisValSet + " Actual : " + axisValArr);
    }

    public void verifyRegionMetricsChartsAndTables(){
        List<WebElement> hBaseKpiContainersList = hBasePageObject.hBaseKpiContainers;
        Assert.assertFalse(hBaseKpiContainersList.isEmpty(), "Region Metrics Charts And Tables not found.");
        List<String> regionMetricsChartsTablesList = new ArrayList<>();

        for(int i=0; i<hBaseKpiContainersList.size() ; i++){
            regionMetricsChartsTablesList.add(hBaseKpiContainersList.get(i).getText());
        }
        Assert.assertTrue(regionMetricsChartsTablesList.size() >0 , "Region Metrics Charts And Tables not found.");
        logger.info(""+regionMetricsChartsTablesList);
    }

    //verify Region server tab and click on it and also check region data table
    public void verifyRegionServer(){
        List<WebElement> hBaseKpiContainersList = hBasePageObject.hBaseKpiContainers;
        Assert.assertFalse(hBaseKpiContainersList.isEmpty(), "Region Metrics Charts And Tables not found.");

        for(int i=0; i<hBaseKpiContainersList.size() ; i++){
            if(hBaseKpiContainersList.get(i).getText().equals("Region Server")){
                MouseActions.clickOnElement(driver, hBaseKpiContainersList.get(i));
            }
        }
        waitExecuter.waitUntilElementPresent(hBasePageObject.hbaseRegionsDataTble);
        Assert.assertTrue(hBasePageObject.regionServerTblRows.size() > 0, "No data in region server table.");
    }

    public void verifyRegionSeverNames(){
        List<WebElement> hBaseRegionSvrNames = hBasePageObject.hBaseRegionSvrName;
        Assert.assertFalse(hBaseRegionSvrNames.isEmpty(), "No Region server name found.");

        List<String> regionSeverNamesList = new ArrayList<>();
        for(int i=0; i<hBaseRegionSvrNames.size() ; i++){
            regionSeverNamesList.add(hBaseRegionSvrNames.get(i).getText());
        }
        Assert.assertTrue(regionSeverNamesList.size() >0, "No Region server name found");
        logger.info("All Region Server Names: "+ regionSeverNamesList);
    }

    public void verifyTableNamesInRegionServer(){
        waitExecuter.waitUntilElementPresent(hBasePageObject.hBaseFirstRegionSvr);
        String regionName = hBasePageObject.hBaseFirstRegionSvr.getText();
        MouseActions.clickOnElement(driver,hBasePageObject.hBaseFirstRegionSvr);

        waitExecuter.waitUntilElementPresent(hBasePageObject.hBaseRegionSvrTable);
        List<WebElement> hBaseRegionSvrTableNames = hBasePageObject.hBaseRegionSvrTableNames;
        Assert.assertFalse(hBaseRegionSvrTableNames.isEmpty(), "No Tables found for region server.");

        waitExecuter.waitUntilElementPresent(hBasePageObject.hBaseRegionSvrTableHeaderName);
        String regionSvrTableName = hBasePageObject.hBaseRegionSvrTableHeaderName.getText();
        logger.info("Region server Table header name: "+ regionSvrTableName);

        List<String> regionSvrTableNames = new ArrayList<>();
        for(int i=0; i<hBaseRegionSvrTableNames.size(); i++){
            regionSvrTableNames.add(hBaseRegionSvrTableNames.get(i).getText());
        }
        Assert.assertTrue(regionSvrTableNames.size()> 0, "No Tables found for region server.");
        logger.info("All table names in region server: "+ regionName +" are - "+ regionSvrTableNames);
    }

    public void clickOnTableName(){
        waitExecuter.waitUntilElementPresent(hBasePageObject.hBaseFirstRegionSvr);
        String regionName = hBasePageObject.hBaseFirstRegionSvr.getText();
        MouseActions.clickOnElement(driver,hBasePageObject.hBaseFirstRegionSvr);

        waitExecuter.waitUntilElementPresent(hBasePageObject.hBaseRegionSvrTable);
        List<WebElement> hBaseRegionSvrTableNames = hBasePageObject.hBaseRegionSvrTableNames;
        Assert.assertFalse(hBaseRegionSvrTableNames.isEmpty(), "No Tables found for region server.");

        waitExecuter.waitUntilElementPresent(hBasePageObject.hBaseRegionSvrTableHeaderName);
        String regionSvrTableName = hBasePageObject.hBaseRegionSvrTableHeaderName.getText();
        logger.info("Region server Table header name: "+ regionSvrTableName);

        waitExecuter.waitUntilElementPresent(hBasePageObject.hBaseFirstRegionSvrTable);
        String tableName = hBasePageObject.hBaseFirstRegionSvrTable.getText();
        MouseActions.clickOnElement(driver, hBasePageObject.hBaseFirstRegionSvrTable);

        logger.info("Clicked on table name: "+ tableName);

        waitExecuter.waitUntilElementPresent(hBasePageObject.hBaseTableTab);
        String hBaseTblTabText = hBasePageObject.hBaseTableTab.getText();
        Assert.assertTrue(hBaseTblTabText.equals("Tables"),"HBase Table Tab not found");

    }

    public void verifyRegionServerHealth(){
        List<WebElement> hBaseRegionSvrHealth = hBasePageObject.hBaseRegionSvrHealth;
        Assert.assertFalse(hBaseRegionSvrHealth.isEmpty(), "No Health check column found.");

        if(hBaseRegionSvrHealth.get(0).getText().equals("Good")){
            hBaseRegionSvrHealth.get(0).click();
        }
        waitExecuter.waitUntilElementPresent(hBasePageObject.hBaseSvrHealthHeader);
        Assert.assertTrue(hBasePageObject.hBaseSvrHealthHeader.getText().equals("Server Health and Context"),
                "'Server Health and Context' header not found");

    }

    public void verifyTblRegionUIWithinRegionServer(){
        waitExecuter.waitUntilElementPresent(hBasePageObject.hBaseTableHostTbl);
        List<WebElement> hBaseTableHostTblRowsList = hBasePageObject.hBaseTableHostTblRows;
        Assert.assertFalse(hBaseTableHostTblRowsList.isEmpty(), "HBase Table Host Table Rows not generated.");

        waitExecuter.waitUntilElementPresent(hBasePageObject.getHbaseTableHostFirstRowRegionName);
        String regionName = hBasePageObject.getHbaseTableHostFirstRowRegionName.getText();
        Assert.assertFalse(regionName.isEmpty(),"Region name is empty.");

        waitExecuter.waitUntilElementPresent(hBasePageObject.getHbaseTableHostFirstRowRegionSvrName);
        String regionSvrName = hBasePageObject.getHbaseTableHostFirstRowRegionSvrName.getText();
        Assert.assertFalse(regionSvrName.isEmpty(),"Region server name is empty.");

        logger.info("Region name: "+regionName + ", Region server name: "+regionSvrName  );
    }

    public void verifyRegionServerKPIs(){
        verifyRegionServer();
        verifyRegionServerHealth();

        List<WebElement> regionSvrKpis = hBasePageObject.regionSvrKpis;
        Assert.assertFalse(regionSvrKpis.isEmpty(), "HBase region server kpi list is empty");
        String[] expectedKPIList = {"REQUEST", "STORE FILES", "COMPACT QUEUE LENGTH", "REGION COUNT",
                "SLOW DELETE", "SLOW GET", "SLOW PUT", "SLOW INCREMENT","SLOW APPEND"};

        for(int i=0; i<regionSvrKpis.size(); i++){
            String kpiName = regionSvrKpis.get(i).getText();
            if(kpiName.length()>0){
                Assert.assertTrue(Arrays.asList(expectedKPIList).contains(kpiName));
            }
        }
    }


    public void verifyTablesTabElements(){
        waitExecuter.waitUntilElementPresent(hBasePageObject.tablesTab);
        MouseActions.clickOnElement(driver,hBasePageObject.tablesTab);
        waitExecuter.waitUntilElementPresent(hBasePageObject.tablesTabTbl);
        waitExecuter.waitUntilElementPresent(hBasePageObject.hBaseTableHostTbl);
    }

    public void verifyTableAndRegion(){
        waitExecuter.waitUntilElementPresent(hBasePageObject.hBaseFirstTableElement);
        String tableName = hBasePageObject.hBaseFirstTableElement.getText();
        MouseActions.clickOnElement(driver, hBasePageObject.hBaseFirstTableElement);

        logger.info("Clicked on table name: "+ tableName);
        waitExecuter.waitUntilElementPresent(hBasePageObject.regionTableName);
        String regionTableName = hBasePageObject.regionTableName.getText();
        logger.info("Region table name: "+ regionTableName);
        //Verify Region name and Region Svr name
        verifyTblRegionUIWithinRegionServer();
    }

    /**
     * Click on the Region Server table heading
     * @param hbaseRegionSvrTableColumn - Hbase Region Server Tables
     */
    public void clickOnRegionSvrTable(HbaseRegionSvrTableColumn hbaseRegionSvrTableColumn){
        WebElement headingToBeClicked = hBasePageObject.hbaseRegionsDataTble.findElement(By.xpath("//th["+
                (hbaseRegionSvrTableColumn.index + 1) + "]/a"));
        MouseActions.clickOnElement(driver, headingToBeClicked);
        waitExecuter.sleep(2000);
    }

    public void sortColumnsInRegionSvrTableMetrics(){

        clickOnRegionSvrTable(HbaseRegionSvrTableColumn.REGION_SERVER_NAME);
        logger.info("Click on Region Server Name column.");
        Boolean isDataSortedForRegionSvrName = isRegionSvrTablesDataSorted(HbaseRegionSvrTableColumn.REGION_SERVER_NAME,
                false);
        Assert.assertTrue(isDataSortedForRegionSvrName, "Region Server Name Data is not sorted.");
        waitExecuter.sleep(2000);

        clickOnRegionSvrTable(HbaseRegionSvrTableColumn.READ_REQUEST_COUNT);
        logger.info("Click on Region Server Read Request Count column.");
        Boolean isDataSortedForRegionSvrReadReqCnt = isRegionSvrTablesDataSorted(
                HbaseRegionSvrTableColumn.READ_REQUEST_COUNT,true);
        Assert.assertTrue(isDataSortedForRegionSvrReadReqCnt, "Region Server Read Request Count Data is not sorted.");
        waitExecuter.sleep(2000);

        clickOnRegionSvrTable(HbaseRegionSvrTableColumn.WRITE_REQUEST_COUNT);
        logger.info("Click on Region Server Read Request Count column.");
        Boolean isDataSortedForRegionSvrWriteReqCnt = isRegionSvrTablesDataSorted(
                HbaseRegionSvrTableColumn.WRITE_REQUEST_COUNT,true);
        Assert.assertTrue(isDataSortedForRegionSvrReadReqCnt, "Region Server Read Request Count Data is not sorted.");
        waitExecuter.sleep(2000);

        clickOnRegionSvrTable(HbaseRegionSvrTableColumn.STORE_FILE_SIZE);
        logger.info("Click on Region Server store file size column.");
        Boolean isDataSortedForRegionSvrStoreFileSize = isRegionSvrTablesDataSorted(
                HbaseRegionSvrTableColumn.STORE_FILE_SIZE, true);
        Assert.assertTrue(isDataSortedForRegionSvrStoreFileSize, "Region Server store file size Data is not sorted.");
        waitExecuter.sleep(2000);

        clickOnRegionSvrTable(HbaseRegionSvrTableColumn.REGION_COUNT);
        logger.info("Click on Region Server 'Region Count' column.");
        Boolean isDataSortedForRegionSvrRegionCnt = isRegionSvrTablesDataSorted(
                HbaseRegionSvrTableColumn.REGION_COUNT,true);
        Assert.assertTrue(isDataSortedForRegionSvrRegionCnt, "Region Server Read Request Count Data is not sorted.");
        waitExecuter.sleep(2000);
    }

    /**
     * Validate whether data is sorted of hbase region server table column
     * @param hbaseRegionSvrTableColumn  - Region Server Table Column to be clicked
     * @param isReversed - Descending Order
     * @return true if data is sorted.
     */
    public Boolean isRegionSvrTablesDataSorted(HbaseRegionSvrTableColumn hbaseRegionSvrTableColumn, Boolean isReversed){

        List<String> actualDataString = new ArrayList<>();
        List<WebElement> tableRows = hBasePageObject.hBaseRegionSvrTableRecords;
        for(WebElement row: tableRows){
            //table[@id='hbaseRegionsDataTble']/tbody/tr/td[1]
            actualDataString.add(row.findElement(By.xpath("td["+ (hbaseRegionSvrTableColumn.index + 1) +
                    "]")).getText().trim());
            actualDataString = actualDataString.stream().map(data -> Arrays.asList(data.split("\n"))
                    .stream().reduce((first, second) -> second).get()).collect(Collectors.toList());
        }
        //System.out.println("actualDataString: "+actualDataString);

        if(hbaseRegionSvrTableColumn == HbaseRegionSvrTableColumn.READ_REQUEST_COUNT ||
                hbaseRegionSvrTableColumn == HbaseRegionSvrTableColumn.WRITE_REQUEST_COUNT ||
                hbaseRegionSvrTableColumn == HbaseRegionSvrTableColumn.REGION_COUNT){

            List<Integer> actualDataInteger = actualDataString.stream().map(data ->
                    convertToInteger(data)).collect(Collectors.toList());
            //System.out.println("actualDataInteger: "+actualDataInteger);
            List<String> sortedList = new ArrayList(actualDataInteger);
            if (isReversed) {
                sortedList.sort(Comparator.reverseOrder());
            } else {
                sortedList.sort(Comparator.naturalOrder());
            }
            if (actualDataInteger.equals(sortedList)) {
                return true;
            } else {
                return false;
            }

        }else if(hbaseRegionSvrTableColumn == HbaseRegionSvrTableColumn.STORE_FILE_SIZE){

            List<Double> actualDataDouble = actualDataString.stream().map(data ->
                    convertToDouble(data)).collect(Collectors.toList());
            //System.out.println("actualDataDouble: "+actualDataDouble);
            List<String> sortedList = new ArrayList(actualDataDouble);
            if (isReversed) {
                sortedList.sort(Comparator.reverseOrder());
            } else {
                sortedList.sort(Comparator.naturalOrder());
            }
            if (actualDataDouble.equals(sortedList)) {
                return true;
            } else {
                return false;
            }

        }else {

            List<String> sortedList = new ArrayList(actualDataString);
            if (isReversed) {
                sortedList.sort(Comparator.reverseOrder());
            } else {
                sortedList.sort(Comparator.naturalOrder());
            }
            if (actualDataString.equals(sortedList)) {
                return true;
            } else {
                return false;
            }
        }

    }

    /**
     * Click on the table heading of hbase tables
     * @param hbaseTablesColumn - Hbase Tables
     */
    public void clickOnTableHeading(HbaseTablesColumn hbaseTablesColumn){
        WebElement headingToBeClicked =
                hBasePageObject.tablesTabTbl.findElement(By.xpath("//th["+ (hbaseTablesColumn.index + 1) +
                        "]/a"));
        actions.performActionWithPolling(headingToBeClicked, UserAction.CLICK);
        waitExecuter.sleep(2000);
    }

    public void sortColumnsInTableMetrics(){
        clickOnTableHeading(HbaseTablesColumn.TABLE_NAME);
        logger.info("Click on Table Name column.");
        Boolean isDataSortedForTblName = isTablesDataSorted(HbaseTablesColumn.TABLE_NAME, false);
        Assert.assertTrue(isDataSortedForTblName, "Data is not sorted.");
        waitExecuter.sleep(2000);
        clickOnTableHeading(HbaseTablesColumn.TABLE_SIZE);
        logger.info("Click on Table Name column.");
        Boolean isDataSortedForTblSize = isTablesDataSorted(HbaseTablesColumn.TABLE_SIZE, true);
        Assert.assertTrue(isDataSortedForTblSize, "Data is not sorted.");
        waitExecuter.sleep(2000);
        clickOnTableHeading(HbaseTablesColumn.REGION_COUNT);
        logger.info("Click on Table Name column.");
        Boolean isDataSortedForRegionCnt = isTablesDataSorted(HbaseTablesColumn.REGION_COUNT, true);
        Assert.assertTrue(isDataSortedForRegionCnt, "Data is not sorted.");
        waitExecuter.sleep(2000);
        clickOnTableHeading(HbaseTablesColumn.READ_REQUEST_COUNT);
        logger.info("Click on Table Name column.");
        Boolean isDataSortedForReadReqCnt = isTablesDataSorted(HbaseTablesColumn.READ_REQUEST_COUNT, true);
        Assert.assertTrue(isDataSortedForReadReqCnt, "Data is not sorted.");
        waitExecuter.sleep(2000);
        clickOnTableHeading(HbaseTablesColumn.WRITE_REQUEST_COUNT);
        logger.info("Click on Table Name column.");
        Boolean isDataSortedForWriteReqCnt = isTablesDataSorted(HbaseTablesColumn.WRITE_REQUEST_COUNT, true);
        Assert.assertTrue(isDataSortedForWriteReqCnt, "Data is not sorted.");
    }

    //convert String data to integer
    public int convertToInteger(String data){

        String[] newData = data.split(" ");
        return Integer.parseInt(newData[0]);
    }

    //convert String data to double
    public double convertToDouble(String data){

        String[] newData = data.split(" ");
        return Double.parseDouble(newData[0]);
    }

    /**
     * Validate whether data is sorted of hbase table column
     * @param hbaseTablesColumn  - Table Column to be clicked
     * @param isReversed - Descending Order
     * @return true if data is sorted.
     */
    public boolean isTablesDataSorted(HbaseTablesColumn hbaseTablesColumn, Boolean isReversed){

        List<String> actualDataString = new ArrayList<>();
        List<WebElement> tableRows = hBasePageObject.tablesTabTblRecords;
        for (WebElement row : tableRows) {
            actualDataString.add(row.findElement(By.xpath("td[" + (hbaseTablesColumn.index + 1) + "]")).getText().trim());
            actualDataString = actualDataString.stream().map(data -> Arrays.asList(data.split("\n"))
                    .stream().reduce((first, second) -> second).get()).collect(Collectors.toList());
        }
        //System.out.println("actualDataString: "+actualDataString);

        if(hbaseTablesColumn == HbaseTablesColumn.TABLE_SIZE ||
                hbaseTablesColumn == HbaseTablesColumn.REGION_COUNT ||
                hbaseTablesColumn == HbaseTablesColumn.READ_REQUEST_COUNT ||
                hbaseTablesColumn == HbaseTablesColumn.WRITE_REQUEST_COUNT )
        {

            List<Integer> actualDataInteger = actualDataString.stream().map(data ->
                    convertToInteger(data)).collect(Collectors.toList());
            //System.out.println("actualDataInteger: "+actualDataInteger);
            List<String> sortedList = new ArrayList(actualDataInteger);
            if (isReversed) {
                sortedList.sort(Comparator.reverseOrder());
            } else {
                sortedList.sort(Comparator.naturalOrder());
            }
            if (actualDataInteger.equals(sortedList)) {
                return true;
            } else {
                return false;
            }

        }else{
            List<String> sortedList = new ArrayList(actualDataString);
            if (isReversed) {
                sortedList.sort(Comparator.reverseOrder());
            } else {
                sortedList.sort(Comparator.naturalOrder());
            }

            if (actualDataString.equals(sortedList)) {
                return true;
            } else {
                return false;
            }

        }

    }
}
