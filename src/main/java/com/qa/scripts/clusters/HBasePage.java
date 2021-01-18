package com.qa.scripts.clusters;

import com.qa.pagefactory.clusters.HBasePageObject;
import com.qa.pagefactory.jobs.ApplicationsPageObject;
import com.qa.scripts.DatePicker;
import com.qa.utils.MouseActions;
import com.qa.utils.WaitExecuter;
import com.qa.utils.actions.UserActions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

public class HBasePage {
    private WebDriver driver;
    private WaitExecuter waitExecuter;
    private HBasePageObject hBasePageObject;
    private UserActions actions;
    private DatePicker datePicker;
    private Logger logger = Logger.getLogger(HBasePage.class.getName());

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
        selectDateAsLast30Days();

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
}
