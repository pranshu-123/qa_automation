package com.qa.scripts.appdetails;

import com.qa.pagefactory.TopPanelComponentPageObject;
import com.qa.pagefactory.UserReportPageObject;
import com.qa.pagefactory.appsDetailsPage.SparkAppsDetailsPageObject;
import com.qa.pagefactory.appsDetailsPage.TezAppsDetailsPageObject;
import com.qa.pagefactory.jobs.ApplicationsPageObject;
import com.qa.scripts.DatePicker;
import com.qa.scripts.jobs.applications.AllApps;
import com.qa.utils.MouseActions;
import com.qa.utils.WaitExecuter;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;

import java.util.List;

public class TezAppsDetailsPage {

    private static final java.util.logging.Logger LOGGER = java.util.logging.Logger.getLogger(TezAppsDetailsPage.class.getName());
    private WaitExecuter waitExecuter;
    private WebDriver driver;
    private TezAppsDetailsPageObject tezApps;

    Logger logger = LoggerFactory.getLogger(SparkAppsDetailsPage.class);

    /**
     * Constructor to initialize wait, driver and necessary objects
     *
     * @param driver - WebDriver instance
     */
    public TezAppsDetailsPage(WebDriver driver) {
        waitExecuter = new WaitExecuter(driver);
        this.driver = driver;
    }

    /**
     * Common steps to navigate to the Jobs page from header.
     * Clicks on jobs tab
     * Selects a specific cluster
     * Selects 90 days time interval
     * Deselsects all the selected apps from the left pane on jobs page.
     */
    public void navigateToJobsTabFromHeader(TopPanelComponentPageObject topPanelObj, AllApps allApps,
                                            DatePicker datePicker, ApplicationsPageObject appPageObj,
                                            String clusterId) {
        logger.info("Navigate to jobs tab from header");
        waitExecuter.sleep(3000);
        waitExecuter.waitUntilElementClickable(topPanelObj.jobs);
        waitExecuter.sleep(1000);
        topPanelObj.jobs.click();
        waitExecuter.sleep(3000);
        waitExecuter.waitUntilElementPresent(appPageObj.jobsPageHeader);
        waitExecuter.waitUntilPageFullyLoaded();
        waitExecuter.sleep(2000);

        //Select cluster
        logger.info("Select Cluster: " + clusterId);
        allApps.selectCluster(clusterId);
        waitExecuter.sleep(3000);

        datePicker.clickOnDatePicker();
        waitExecuter.sleep(1000);
        datePicker.selectLast90Days();
        waitExecuter.sleep(3000);
        waitExecuter.waitUntilPageFullyLoaded();


    }

    /**
     * Method to click on 'Only' and select a specific checkbox from job pages left pane
     *
     * @param types Types can be appType | status Type
     */
    public int clickOnlyLink(String types) {
        Actions action = new Actions(driver);
        WebElement we = driver.findElement(By.xpath("(//label[contains(@class,'checkbox')])/span[contains(text(),'" + types + "')]"));
        action.moveToElement(we).moveToElement(driver.findElement(By.xpath("(//label[contains(@class,'checkbox')])" +
                "/span[contains(text(),'" + types + "')]/following-sibling::span[2]"))).click().build().perform();
        WebElement ele = driver.findElement(By.xpath("(//label[contains(@class,'checkbox')])" + "/span[contains(text(),'" + types + "')]/following-sibling::span[1]"));
        int appCount = Integer.parseInt(ele.getText().replaceAll("[^\\dA-Za-z ]",
                "").trim());
        waitExecuter.sleep(3000);
        return appCount;
    }

    /**
     * Method to verify if the component tabs like navigation | Gantt chart | jobs is present
     * and contains data if job count is > 0
     * if validateCompData = true validate each component tab data
     * if validateExecutorTab = true validate jobs execution tabs data.
     */
    public void verifyAppsComponent(TezAppsDetailsPageObject tezApps, Boolean validateCompData,
                                    Boolean validateExecutorTab) {
        List<WebElement> componentList = tezApps.component_element;
        logger.info("ComponentList is " + componentList.size());
        int navigationRows = 0;
        String tabName = "";
        for (int j = 0; j < componentList.size(); j++) {
            if (j != 3)
                tabName = componentList.get(j).getText();
            switch (j) {
                case 0:
                    Assert.assertEquals(tabName, "Navigation", "Navigation tab not present");
                    List<WebElement> navigationRowList = tezApps.navigationTableRows;
                    navigationRows = navigationRowList.size();
                    logger.info("Navigation Rows are " + navigationRows);
                    if (validateCompData) {
                        List<WebElement> headerList = tezApps.navigationHeaders;
                        Assert.assertFalse(headerList.isEmpty(), "No headers for Navigation table for application");
                        for (int i = 0; i < headerList.size(); i++) {
                            logger.info("The header is " + headerList.get(i).getText());
                            Assert.assertNotSame("", headerList.get(i).getText());
                        }
                        if (navigationRows > 0) {
                            for (int rows = 1; rows <= navigationRows; rows++) {
                                for (int col = 1; col <= headerList.size(); col++) {
                                    String data = driver.findElement(By.xpath("//*[@id='appNavigation-body']/" +
                                            "tr[" + rows + "]/td[" + col + "]/span")).getText();
                                    logger.info("The data is " + data);
                                    Assert.assertNotSame("", data);
                                }
                            }
                        }
                    }
                    validateStageAndStageData(navigationRows, navigationRowList, tezApps, validateExecutorTab);
                    break;
                case 1:
                    //The component is Gantt Chart ,click it and then verify the no. rows in the table
                    Assert.assertEquals(tabName, "Gantt Chart", "Gantt Chart tab not present");
                    MouseActions.clickOnElement(driver, componentList.get(j));
                    //  componentList.get(j).click();
                    List<WebElement> ganttChartTableRows = tezApps.ganttChartTable;
                    logger.info("No. of rows in Gantt Chart tables are " + ganttChartTableRows.size());
                    if (validateCompData) {
                        List<WebElement> headerList = tezApps.ganttChartHeaders;
                        Assert.assertFalse(headerList.isEmpty(), "No headers for Gantt Chart table for application");
                        for (int i = 0; i < headerList.size(); i++) {
                            logger.info("The header is " + headerList.get(i).getText());
                            Assert.assertNotSame("", headerList.get(i).getText());
                        }
                        if (ganttChartTableRows.size() > 0) {
                            for (int rows = 0; rows < ganttChartTableRows.size(); rows++) {
                                String jobId = tezApps.ganttChartJobId.get(rows).getText();
                                String startTime = tezApps.ganttChartStartTime.get(rows).getText();
                                String duration = tezApps.ganttChartDuration.get(rows).getText();
                                logger.info("Duration = " + duration + " JobId = " + jobId + " starttime = " + startTime);
                                Assert.assertNotSame("", jobId, "Value for jobId missing");
                                Assert.assertNotSame("", startTime, "Value for startTime missing");
                                Assert.assertNotSame("", duration, "Value for duration missing");
                            }
                        }
                    }
                    break;
                case 2:
                    Assert.assertEquals(tabName, navigationRows + " Jobs", "Jobs text not present");
                    String[] jobCountArr = componentList.get(j).getText().split("\\s+");
                    int jobCnt = Integer.parseInt(jobCountArr[0]);
                    Assert.assertEquals(jobCnt, navigationRows, "JobCnt and navigation rows donot match");
                    logger.info("JobCount is " + jobCnt);
                    break;
            }
        }
    }

    /**
     * Method to validate the stage table header and the data.
     * if validateExecutorTab = true, validate each jobs execution tabs data
     */
    public void validateStageAndStageData(int navigationRows, List<WebElement> navigationRowList,
                                          TezAppsDetailsPageObject tezApps, Boolean validateExecutorTab) {
        if (navigationRows > 0) {
            String[] expectedHeader = {"Stage ID", "Start Time", "Duration", "Tasks", "Shuffle Read",
                    "Shuffle Write", "Input", "Output"};
            //click the jobId to sort it .
            driver.findElement(By.xpath("(//thead[@id='appNavigation-head'])/tr/th[1]")).click();
            for (int rows = 0; rows < navigationRows; rows++) {
                navigationRowList.get(rows).click();
                waitExecuter.sleep(2000);
                if (validateExecutorTab) {
                    Assert.assertTrue(tezApps.DAGData.isDisplayed(), "Dag data is not displayed ");
                    logger.info("Dags data is displayed ");
                    List<WebElement> rddBlockList = tezApps.rddBlockList;
                    Assert.assertFalse(rddBlockList.isEmpty(), "No DAGs data");
                    logger.info("No. of RDD blocks in the flow chart is " + rddBlockList.size());
                    for (int i = 0; i < rddBlockList.size(); i++) {
                        Assert.assertTrue(rddBlockList.get(i).isDisplayed(), "FlowChart doesnot have the RDD blocks displayed");
                    }
                }
                List<WebElement> stageHeaderList = tezApps.stageHeaders;
                for (int i = 0; i < stageHeaderList.size(); i++) {
                    Assert.assertNotSame("", stageHeaderList.get(i).getText());
                    Assert.assertEquals(expectedHeader[i], stageHeaderList.get(i).getText(),
                            "expected stage header do not match to the one in the UI");
                }
            }
        }
    }

    /**
     * Method to click the first app in jobs table , navigate to the details page.
     * and verify app Id .
     */
    public String verifyAppId(TezAppsDetailsPageObject tezApps, ApplicationsPageObject appPageObj) {
        String appId = tezApps.getAppId.getText().trim();
        logger.info("Tez application Id is " + appId);
        appPageObj.getTypeFromTable.click();
        waitExecuter.sleep(5000);
        waitExecuter.waitUntilPageFullyLoaded();
        String headerAppId = tezApps.getHeaderAppId.getText().trim();
        Assert.assertNotSame("", headerAppId, "Spark Application Id is not displayed in the Header");
        return headerAppId;
    }



}
