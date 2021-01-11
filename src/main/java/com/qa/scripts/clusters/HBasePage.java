package com.qa.scripts.clusters;

import com.qa.pagefactory.clusters.HBasePageObject;
import com.qa.scripts.DatePicker;
import com.qa.utils.MouseActions;
import com.qa.utils.WaitExecuter;
import com.qa.utils.actions.UserActions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
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

    //Method to select hbase cluster from drop down
    public void selectHBaseCluster(String hBaseClusterName){
        waitExecuter.waitUntilElementClickable(hBasePageObject.hBaseClusterDropDown);
        MouseActions.clickOnElement(driver, hBasePageObject.hBaseClusterDropDown);
        waitExecuter.waitUntilPageFullyLoaded();
        List<WebElement> hBaseClusterElementList = hBasePageObject.hBaseClusters;
        Assert.assertFalse(hBaseClusterElementList.isEmpty(), "HBase clusters not found");
        for(int i=0 ; i< hBaseClusterElementList.size() ; i++){
            if(hBaseClusterElementList.get(i).getText().equals(hBaseClusterName)){
                hBaseClusterElementList.get(i).click();
            }
        }
    }

    // Method to verify the HBase cluster Metrics
    public void verifyHBaseClustersMetrics(String hBaseClusterName){
        selectHBaseCluster(hBaseClusterName);
        datePicker.clickOnDatePicker();
        waitExecuter.sleep(1000);
        datePicker.selectLast30Days();
        waitExecuter.sleep(3000);
        waitExecuter.waitUntilPageFullyLoaded();
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
}
